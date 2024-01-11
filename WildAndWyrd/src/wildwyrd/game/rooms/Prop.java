package wildwyrd.game.rooms;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Prop extends Entity {
	GamePanel gp;
	Graphics2D g2;

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

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		System.out.println(alpha);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.drawImage(image, x, y, gp);
	}

}
