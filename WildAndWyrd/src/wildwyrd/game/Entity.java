package wildwyrd.game;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import wildwyrd.game.items.Item;
import wildwyrd.game.object.Dialoge;
import wildwyrd.game.tile.UtilityTool;

public class Entity {
	public static final long RUNNING_TIME = 2000;
	public int speed;
	public BufferedImage image;
	public BufferedImage image2;
	public GamePanel gp;
	public int x;
	public int y;
	public int width;
	public int height;
	public float alpha = 1f;
	public int id;
	public String name;
	public int expDrop;
	public int worldX;
	public int worldY;
	public Boolean selected;
	public Dialoge[][] dialogues = new Dialoge[80][80];
	public BufferedImage[][] sprites = new BufferedImage[100][100];
	public String[] options = new String[8];
	public boolean[] contConditions = new boolean[8];
	public int dialogueSet = 0;
	public int dialogueIndex = 0;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	public int attackValue;
	public int defenceValue;
	public int healthRcvd;
	public int staminaRcvd;
	public String description = "";
	public String direction = "down";
	public BufferedImage up1, up2, up3, left1, left2, left3, right1, right2, right3, down1, down2, down3, unique;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	//Inventory
	public ArrayList<Item> inventory = new ArrayList<Item>();
	public final int inventorySize = 20;
	//Status
	public boolean moving = false;
	public boolean collision = false;
	public boolean collisionOn = false;
	public boolean takeDamage = false;
	public boolean stackable = false;
	public boolean destroy = false;
	public boolean hasQuest = false;
	public int amount = 1;
	public EntityType type;
	public Timer timer;
	public long startTime = -1;
	public Item loot;
	public int shill = 0;
	public boolean opened = false;
	public boolean drawing = true;
	public boolean skippable = true;
	

	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public void update() {
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);
		if(moving) {
			if(!collisionOn) {
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			spriteCounter++;
			if (spriteCounter > 10) {
				if(spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 3;
				} else if (spriteNum == 3) {
					spriteNum = 4;
				} else if (spriteNum == 4) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
			moving = false;
		} else {
			spriteNum = 1;
		}
	}
	
	public void getImage(BufferedImage image) {
		down1 = image;
	}

	public BufferedImage setup(String imagePath, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public String getName() {
		if (name == null) {
			return "None";
		}
		return name;
	}
	
	public int getCenterX() {
		int centerX = worldX + left1.getWidth()/2; 
		return centerX;
	}
	
	public int getCenterY() {
		int centerY = worldY + left1.getWidth()/2; 
		return centerY;
	}
	
	public int getXDistance(Entity target) {
		int xDistance = Math.abs(worldX - target.worldX); 
		return xDistance;
	}
	
	public int getYDistance(Entity target) {
		int yDistance = Math.abs(worldY - target.worldX); 
		return yDistance;
	}

	public int getLeftX() {
		return worldX + solidArea.x;
	}

	public int getRightX() {
		return worldX + solidArea.x + solidArea.width;
	}

	public int getTopY() {
		return worldY + solidArea.y;
	}

	public int getBottomY() {
		return worldY + solidArea.y + solidArea.height;
	}

	public int getCol() {
		return (worldX + solidArea.x) / gp.tileSize;
	}

	public int getRow() {
		return (worldY + solidArea.y) / gp.tileSize;
	}
	
	public int GetCentreX() {
		int centreX = worldX + solidArea.width;
		return centreX;
	}
	
	public int GetCentreY() {
		int centreY = worldY + solidArea.height;
		return centreY;
	}

	public EntityType getType() {
		return type;
	}

	public void draw(Graphics2D g2) {
		getQuest();
		//BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		if (gp.player.worldX < gp.player.screenX) {
			screenX = worldX;
		}
		// STOP MOVING CAMERA
		if (gp.player.worldY < gp.player.screenY) {
			screenY = worldY;
		}
		int rightOffset = gp.screenWidth - gp.player.screenX;
		if (rightOffset > gp.currentMap.getWorldWidth() - gp.player.worldX) {
			screenX = gp.screenWidth - (gp.currentMap.getWorldWidth() - worldX);
		}
		int bottomOffset = gp.screenHeight - gp.player.screenY;
		if (bottomOffset > gp.currentMap.getWorldHeight() - gp.player.worldY) {
			screenY = gp.screenHeight - (gp.currentMap.getWorldHeight() - worldY);
		}
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			int tempScreenX = screenX;
			int tempScreenY = screenY;
			switch(direction) {
			case "up":
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
				if(spriteNum == 3) {image = up3;}
				break;
			case "left":
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				if(spriteNum == 3) {image = left3;}
				break;
			case "right":
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				if(spriteNum == 3) {image = right3;}
				break;
			case "down":
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
				if(spriteNum == 3) {image = down3;}
				break;
			case "unique":
				image = unique;
				break;
			}
			g2.setComposite(AlphaComposite.SrcOver.derive(alpha));
			g2.drawImage(image, tempScreenX, tempScreenY, null);
			if(hasQuest && gp.gameState == GameState.playState) {
				BufferedImage q_icon = setup("/res/icons/quest_icon",gp.originalTileSize,gp.originalTileSize);
				g2.drawImage(q_icon, tempScreenX + 10, tempScreenY, null);
			}
            g2.setComposite(AlphaComposite.SrcOver.derive(1f));
		}
		else if(gp.player.worldX < gp.player.screenX ||
			    gp.player.worldY < gp.player.screenY ||
			    rightOffset > gp.currentMap.getWorldWidth() - gp.player.worldX ||
			    bottomOffset > gp.currentMap.getWorldHeight() - gp.player.worldY) {
			int tempScreenX = screenX;
			int tempScreenY = screenY;
			switch(direction) {
			case "up":
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
				if(spriteNum == 3) {image = up3;}
				break;
			case "left":
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				if(spriteNum == 3) {image = left3;}
				break;
			case "right":
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				if(spriteNum == 3) {image = right3;}
				break;
			case "down":
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
				if(spriteNum == 3) {image = down3;}
				break;
			case "unique":
				image = unique;
				break;
			}
			g2.drawImage(image, tempScreenX, tempScreenY, null);
			if(hasQuest && gp.gameState == GameState.playState) {
				BufferedImage q_icon = setup("/res/icons/quest_icon",gp.originalTileSize,gp.originalTileSize);
				g2.drawImage(q_icon, tempScreenX + 10, tempScreenY, null);
			}
            g2.setComposite(AlphaComposite.SrcOver.derive(1f));
		}
	}

	public void startDialogue(Entity object, int setNum) {
		GamePanel gp = this.gp;
		gp.gameState = GameState.examineState;
		gp.ui.selectedObject = object;
		dialogueSet = setNum;
	}
	
	public boolean getQuest() {
		return hasQuest;
	}

	public void restartDialogue(Dialoge[] object, int getSize) {
		if(object.length == getSize) {
			speak();
		}
	}
	
	public void setLoot(Item loot) {
		this.loot = loot;
	}
	
	public void setLoot(int loot) {
		this.shill = loot;
	}
	
	public void removeFromInventory(Item selectedItem) {
		if(selectedItem.amount > 1) {
			selectedItem.amount--;
		} else {
			inventory.remove(selectedItem);
		}
	}
	
	public void interact() {}
	public void speak() {
		facePlayer();
		gp.ui.choiceSlot = 0;
		gp.ui.firstValue = 0;
		startDialogue(this, 0);

		gp.keyH.enterPressed = false;
	}
	
	public void facePlayer() {
		switch(gp.player.direction) {
		case "up": direction = "down"; break;
		case "down": direction = "up"; break;
		case "left": direction = "right"; break;
		case "right": direction = "left"; break;
		}
	}

	public int getDetected(Entity user, Entity[][] target, String targetName) {
		int index = 999;
		int nextWorldX = user.getLeftX();
		int nextWorldY = user.getTopY();
		switch (user.direction) {
			case "bottom": nextWorldY = user.getBottomY() + 1; break;
			case "up": nextWorldY = user.getTopY() - 1; break;
			case "left": nextWorldX = user.getLeftX() - 1; break;
			case "right": nextWorldX = user.getRightX() + 1; break;
		}
		int col = nextWorldX / gp.tileSize;
		int row = nextWorldY / gp.tileSize;

		for (int i = 0; i < target[gp.currentMap.getId()].length; ++i) {
			if (target[gp.currentMap.getId()][i] != null && target[gp.currentMap.getId()][i].getCol() == col && target[gp.currentMap.getId()][i].getRow() == row
					&& target[gp.currentMap.getId()][i].name.equals(targetName)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public void combatResponce() {}
	public void choiceResponce() {}
	public void checkConditions() {}
	public void takeDamage() {}
	public void action() {}
}