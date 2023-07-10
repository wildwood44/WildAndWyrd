package wildwyrd.game.playable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.KeyHandler;
import wildwyrd.game.items.Itm_Bandage;
import wildwyrd.game.items.Itm_Hazelnut;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public ArrayList<Entity> inventory = new ArrayList<Entity>();
	private int shillings;
	public final int inventorySize = 20;
	public Boolean approval;
	private long start;

	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.gp = gp;
		this.keyH = keyH;
		screenX = gp.screenWidth / 2;
		screenY = gp.screenHeight / 2;
		solidArea = new Rectangle(12, 12, gp.tileSize, gp.tileSize);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 24;
		solidArea.height = 40;
		setDefaultValues();
		getPlayerImage(3, 3);
	}

	public void setDefaultValues() {
		worldX = gp.tileSize * 5;
		worldY = gp.tileSize * 7;
		speed = 5;
		direction = "down";
	}

	public BufferedImage getSpriteSheet() {
		BufferedImage sprite = null;

		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/res/sprite/WildWyrdSprites.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sprite;
	}

	public BufferedImage getPlayerImage(int xGrid, int yGrid) {
		if (image == null) {
			image = getSpriteSheet();
		}
		return image.getSubimage(xGrid * 48, yGrid * 48, 48, 48);
	}

	public void update() {
		int objIndex;
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
			if (keyH.upPressed) {
				direction = "up";
			} else if (keyH.downPressed) {
				direction = "down";
			} else if (keyH.leftPressed) {
				direction = "left";
			} else if (keyH.rightPressed) {
				direction = "right";
			}
			
			collisionOn = false;
			takeDamage = false;
			gp.cChecker.checkTile(this);
			objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			int npcIndex = gp.cChecker.checkEntity(this,gp.npc);
			interactNPC(npcIndex);
			int iTileIndex = gp.cChecker.checkEntity(this,gp.iTile);
			changeInteractiveTile(iTileIndex);
			
			gp.eHandler.checkEvent();
			if (collisionOn == false && keyH.enterPressed == false) {
				switch (direction) {
					case "up":
						worldY -= speed;
						break;
					case "down":
						worldY += speed;
						break;
					case "left":
						worldX -= speed;
						break;
					case "right":
						worldX += speed;
				}
			} else {
				if(takeDamage) {
					gp.playable.get(0).takeDamage(1);
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
		} else {
			spriteNum = 1;
		}

		if (keyH.enterPressed) {
			objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
		}
	}
	
	private void drawHealth(Graphics2D g2, int screenX, int screenY) {
		double oneScale = (double)gp.tileSize/gp.playable.get(0).maxHealth;
		double healthValue = oneScale * gp.playable.get(0).health;
		g2.setColor(new Color(35,35,35));
		g2.fillRect(screenX - 2, screenY - 16, gp.tileSize+2, 12);
		g2.setColor(new Color(255,0,0));
		g2.fillRect(screenX, screenY - 14, (int)healthValue, 10);
	}
	
	public int pickUpShillings(int i) {
		shillings += i;
		return shillings;
	}
	
	public int spendShillings(int i) {
		shillings -= i;
		return shillings;
	}
	
	public int getShillings() {
		return shillings;
	}

	public void pickUpObject(int i) {
		if (i != 999) {
			if (gp.obj[gp.currentMap.getId()][i].type == EntityType.Object && keyH.enterPressed) {
				gp.obj[gp.currentMap.getId()][i].interact();
			}
		}
	}
	
	public void interactNPC(int i) {
		if (i != 999) {
			if (keyH.enterPressed) {
				gp.npc[gp.currentMap.getId()][i].speak();
			}
		}
	}
	
	public void selectedItem(){
		int itemIndex = gp.ui.getItemIndexOnSlot();
		if(itemIndex < inventory.size()) {
			Entity selectedItem = inventory.get(itemIndex);
			if(selectedItem.type == EntityType.Health) {
				if(gp.playable.get(0).getHealth() < gp.playable.get(0).getMaxHealth()) {
					gp.playable.get(0).heal(selectedItem.healthRcvd);
					gp.player.inventory.remove(selectedItem);
				}
			}
			else if(selectedItem.type == EntityType.Food) {
				if(gp.playable.get(0).getStamina() < gp.playable.get(0).getMaxStamina()) {
					gp.playable.get(0).eat(selectedItem.staminaRcvd);
					gp.player.inventory.remove(selectedItem);
				}
			}
			else if(selectedItem.type == EntityType.Primary) {
				gp.playable.get(0).setWeapon_prime(selectedItem);
				gp.player.inventory.remove(selectedItem);
			}
		}
	}

	public void setItems() {
		inventory.add(new Itm_Hazelnut(gp));
		inventory.add(new Itm_Bandage(gp));
	}
	
	public void changeInteractiveTile(int i) {
		//System.out.println(gp.iTile[gp.currentMap.getId()][i] + " " + gp.currentMap.getId() + " " + i);
		
		if(i != 999 && gp.iTile[gp.currentMap.getId()][i].transformable == true) {
			gp.iTile[gp.currentMap.getId()][i] = gp.iTile[gp.currentMap.getId()][i].uncoverIllusion();
		}
	}
	
	public boolean damageTaken() {
		long current = System.currentTimeMillis();
        if(takeDamage){
            start = current;
            takeDamage = false;
        }
        return start + 3000 < current;
	}

	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		image = getPlayerImage(4, 0);
		int x = screenX;
		int y = screenY;
		if (screenX > worldX) {
			x = worldX;
		}

		if (screenY > worldY) {
			y = worldY;
		}
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = getPlayerImage(4, 3);
			} else if(spriteNum == 2) {
				image = getPlayerImage(3, 3);
			} else if(spriteNum == 3) {
				image = getPlayerImage(4, 3);
			} else if(spriteNum == 4) {
				image = getPlayerImage(5, 3);
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = getPlayerImage(4, 0);
			} else if(spriteNum == 2) {
				image = getPlayerImage(3, 0);
			} else if(spriteNum == 3) {
				image = getPlayerImage(4, 0);
			} else if(spriteNum == 4) {
				image = getPlayerImage(5, 0);
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = getPlayerImage(4, 1);
			} else if(spriteNum == 2) {
				image = getPlayerImage(3, 1);
			} else if(spriteNum == 3) {
				image = getPlayerImage(4, 1);
			} else if(spriteNum == 4) {
				image = getPlayerImage(5, 1);
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = getPlayerImage(4, 2);
			} else if(spriteNum == 2) {
				image = getPlayerImage(3, 2);
			} else if(spriteNum == 3) {
				image = getPlayerImage(4, 2);
			} else if(spriteNum == 4) {
				image = getPlayerImage(5, 2);
			}
			break;
		}

		int rightOffset = gp.screenWidth - screenX;
		if (rightOffset > gp.currentMap.getWorldWidth() - gp.player.worldX) {
			x = gp.screenWidth - (gp.currentMap.getWorldWidth() - worldX);
		}

		int bottomOffset = gp.screenHeight - screenY;
		if (bottomOffset > gp.currentMap.getWorldHeight() - gp.player.worldY) {
			y = gp.screenHeight - (gp.currentMap.getWorldHeight() - worldY);
		}
		if(!damageTaken()) {
			drawHealth(g2, x, y);
		}

		g2.drawImage(image, x, y, (ImageObserver) null);
	}
}