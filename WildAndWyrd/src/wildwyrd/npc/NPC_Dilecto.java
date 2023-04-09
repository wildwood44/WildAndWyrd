package wildwyrd.npc;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class NPC_Dilecto extends Entity {
	public NPC_Dilecto(GamePanel gp) {
		super(gp);
		//this.gp = gp;
		direction = "down";
		speed = 1;
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
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			switch(direction) {
			case "up":
				if(spriteNum == 1) {
					image = getPlayerImage(7, 3);
				} else if(spriteNum == 2) {
					image = getPlayerImage(6, 3);
				} else if(spriteNum == 3) {
					image = getPlayerImage(7, 3);
				} else if(spriteNum == 4) {
					image = getPlayerImage(8, 3);
				}
				break;
			case "down":
				if(spriteNum == 1) {
					image = getPlayerImage(1, 0);
				} else if(spriteNum == 2) {
					image = getPlayerImage(0, 0);
				} else if(spriteNum == 3) {
					image = getPlayerImage(1, 0);
				} else if(spriteNum == 4) {
					image = getPlayerImage(2, 0);
				}
				break;
			case "left":
				if(spriteNum == 1) {
					image = getPlayerImage(7, 1);
				} else if(spriteNum == 2) {
					image = getPlayerImage(6, 1);
				} else if(spriteNum == 3) {
					image = getPlayerImage(7, 1);
				} else if(spriteNum == 4) {
					image = getPlayerImage(8, 1);
				}
				break;
			case "right":
				if(spriteNum == 1) {
					image = getPlayerImage(7, 2);
				} else if(spriteNum == 2) {
					image = getPlayerImage(6, 2);
				} else if(spriteNum == 3) {
					image = getPlayerImage(7, 2);
				} else if(spriteNum == 4) {
					image = getPlayerImage(8, 2);
				}
				break;
			}
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
	
	public void setAction() {
		
	}
}
