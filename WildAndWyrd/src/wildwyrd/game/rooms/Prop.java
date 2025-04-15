package wildwyrd.game.rooms;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Prop extends Entity {
	GamePanel gp;
	Graphics2D g2;
	public boolean withered = false;
	public BufferedImage[] animation = new BufferedImage[5];

	public Prop(GamePanel gp, int x, int y, int width, int height) {
		super(gp);
		this.gp = gp;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		alpha = 0.0f;
	}
	
	public void setImage(String file) {
		image = setup(file, width, height);
	}

	public void getImage() {}
	
	public void playLeifAnimation(Graphics2D g2) {
		this.g2 = g2;
		if(withered) {
			spriteCounter++;
			int i = 30;
			if(spriteCounter <= i) {setImage("/res/items/img_Leif1");}
			if(spriteCounter > i && spriteCounter <= i*2) {setImage("/res/items/img_Leif2");}
			if(spriteCounter > i*2 && spriteCounter <= i*3) {setImage("/res/items/img_Leif3");}
			if(spriteCounter > i*3) {setImage("/res/items/img_Leif4");
			}
			try {
				g2.drawImage(image, x, y, gp);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		} else {
			//Sound
			if(spriteCounter==0) {
				gp.playSE(24);
			}
			spriteCounter++;
			int i = 30;
			if(spriteCounter <= i) {setImage("/res/items/img_Leif4");}
			if(spriteCounter > i && spriteCounter <= i*2) {setImage("/res/items/img_Leif5");}
			if(spriteCounter > i*2 && spriteCounter <= i*3) {setImage("/res/items/img_Leif6");}
			if(spriteCounter > i*3) {setImage("/res/items/img_Leif1");
			}
			try {
				g2.drawImage(image, x, y, gp);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		//g2.drawImage(image, width, height, null);
	}
	
	public BufferedImage playAnimation(int speed) {
		BufferedImage result = image;
		spriteCounter++;
		if(spriteCounter <= speed) {result = animation[0];} else {
		for(int j = 1; j <= animation.length; j++) {
			if(spriteCounter > speed && spriteCounter <= speed*2) {result = animation[1];} 
			else {spriteCounter = 0;}
		}}
		return result;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.drawImage(image, x, y, gp);
	}

}
