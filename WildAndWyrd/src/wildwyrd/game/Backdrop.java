package wildwyrd.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.tile.UtilityTool;

public class Backdrop {
	public BufferedImage image;
	public Color color;
	GamePanel gp;
	Graphics2D g2;

	public Backdrop(GamePanel gp) {
		color = Color.black;
		this.gp = gp;
	}

	public void draw(Graphics2D g2, String url) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage background = null;
		try {
			background = ImageIO.read(getClass().getResourceAsStream(url + ".png"));
			background = uTool.scaleImage(background, gp.screenWidth, gp.screenHeight);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g2.drawImage(background, 0, 0, gp.screenWidth, gp.screenHeight, null);
	}
}
