package wildwyrd.game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

import wildwyrd.game.object.Dialoge;
import wildwyrd.game.tile.UtilityTool;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Entity {
	public int speed;
	public BufferedImage image;
	public BufferedImage image2;
	public GamePanel gp;
	public int x;
	public int y;
	public int width;
	public int height;
	public String name;
	public int worldX;
	public int worldY;
	public Boolean selected;
	public Dialoge[][] dialogues = new Dialoge[5][20];
	public int dialogueSet = 0;
	public int dialogueIndex = 0;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	public int attackValue;
	public int defenceValue;
	public String description = "";
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public boolean collision = false;
	public boolean collisionOn = false;
	public int type;
	public final int type_player = 0;
	public final int type_object = 1;
	public final int type_npc = 2;
	public final int type_obstacle = 3;
	public Timer timer;

	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public void update() {
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);
		if(collisionOn == false) {
			switch(direction) {
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right": worldX += speed; break;
			}
		}
	}
	
	public BufferedImage setup(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
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
		int var10000 = worldX + solidArea.x;
		return var10000 / gp.tileSize;
	}

	public int getRow() {
		int var10000 = worldY + solidArea.y;
		return var10000 / gp.tileSize;
	}

	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		if (gp.player.worldX < gp.player.screenX) {
			screenX = worldX;
		}

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
		int var10000 = this.worldX;
		if (var10000 + gp.tileSize > gp.player.worldX - gp.player.screenX) {
			var10000 = this.worldX;
			if (var10000 - gp.tileSize < gp.player.worldX + gp.player.screenX) {
				var10000 = this.worldY;
				if (var10000 + gp.tileSize > gp.player.worldY - gp.player.screenY) {
					var10000 = this.worldY;
					if (var10000 - gp.tileSize < gp.player.worldY + gp.player.screenY) {
						BufferedImage var10001 = this.image;
						g2.drawImage(var10001, screenX, screenY, gp.tileSize, gp.tileSize, (ImageObserver) null);
					}
				}
			}
		}

	}

	public void startDialogue(Entity object, int setNum) {
		GamePanel gp = this.gp;
		gp.gameState = GameState.examineState;
		gp.ui.selectedObject = object;
		dialogueSet = setNum;
	}

	public void interact() {
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

	public void choiceResponce() {
	}
}