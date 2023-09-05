package wildwyrd.game.playable;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class PlayerDummy extends Entity {
	public static final String npcName = "Dummy";

	public PlayerDummy(GamePanel gp) {
		super(gp);
		name = npcName;
		getImage();
		getPlayerImage(3, 3);
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

	public void getImage() {
		up1 = getPlayerImage(4, 3);
		up2 = getPlayerImage(3, 3);
		up3 = getPlayerImage(5, 3);
		down1 = getPlayerImage(4, 0);
		down2 = getPlayerImage(3, 0);
		down3 = getPlayerImage(5, 0);
		left1 = getPlayerImage(4, 1);
		left2 = getPlayerImage(3, 1);
		left3 = getPlayerImage(5, 1);
		right1 = getPlayerImage(4, 2);
		right2 = getPlayerImage(3, 2);
		right3 = getPlayerImage(5, 2);
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		image = getPlayerImage(4, 0);
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			} else if(spriteNum == 2) {
				image = up2;
			} else if(spriteNum == 3) {
				image = up1;
			} else if(spriteNum == 4) {
				image = up3;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			} else if(spriteNum == 2) {
				image = down2;
			} else if(spriteNum == 3) {
				image = down1;
			} else if(spriteNum == 4) {
				image = down3;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			} else if(spriteNum == 2) {
				image = left2;
			} else if(spriteNum == 3) {
				image = left1;
			} else if(spriteNum == 4) {
				image = left3;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			} else if(spriteNum == 2) {
				image = right2;
			} else if(spriteNum == 3) {
				image = right1;
			} else if(spriteNum == 4) {
				image = right3;
			}
			break;
		}
		if(!gp.player.drawing) {
			g2.drawImage(image, screenX, screenY, null);
		}
	}
}
