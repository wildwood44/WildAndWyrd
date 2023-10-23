package wildwyrd.game.npc;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class NPC extends Entity {

	public NPC(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
	}
	


	public BufferedImage getSpecialSpriteSheet() {
		BufferedImage sprite = null;

		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/res/sprite/WildWyrdSpecialSprites.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sprite;
	}

	public BufferedImage getSpecialImage(int xGrid, int yGrid) {
		if (image == null) {
			image = getSpecialSpriteSheet();
		}
		return image.getSubimage(xGrid * 48, yGrid * 48, 48, 48);
	}
}
