package wildwyrd.game.cutscenes;

import java.awt.image.BufferedImage;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Lightning extends Entity {

	GamePanel gp;
	public static final String objName = "Lightning";
	
	public Obj_Lightning(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = objName;
		direction = "down";
		width = gp.tileSize/2;
		height = gp.tileSize/2;
		image = setup("/res/projectile/lightning1", width, height);
		getImage(image);
		alpha = 1;
		moving = true;
	}

	public void getImage(BufferedImage image) {
		down1 = setup("/res/projectile/lightning1", width, height);
		down2 = setup("/res/projectile/lightning2", width, height);
		down3 = setup("/res/projectile/lightning2", width, height);
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
			if (spriteCounter > 2) {
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
	
}
