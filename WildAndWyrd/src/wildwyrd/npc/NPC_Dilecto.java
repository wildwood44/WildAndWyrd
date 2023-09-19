package wildwyrd.npc;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class NPC_Dilecto extends Entity {
	public static final String npcName = "Dilecto";
	public static final int npcId = 0;
	public NPC_Dilecto(GamePanel gp) {
		super(gp);
		//this.gp = gp;
		id = npcId;
		name = npcName;
		type = EntityType.Sprite;
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
		}else if(gp.player.worldX < gp.player.screenX ||
			    gp.player.worldY < gp.player.screenY ||
			    rightOffset > gp.currentMap.getWorldWidth() - gp.player.worldX ||
			    bottomOffset > gp.currentMap.getWorldHeight() - gp.player.worldY) {
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
