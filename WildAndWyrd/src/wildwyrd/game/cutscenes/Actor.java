package wildwyrd.game.cutscenes;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Actor extends Entity {
	GamePanel gp;
	BufferedImage image_Dilecto;
	BufferedImage image_Thay;
	BufferedImage image_Dean;
	BufferedImage image_Ralph;
	BufferedImage image_Plumm;

	public Actor(GamePanel gp) {
		super(gp);
		this.gp = gp;
	}

	public void setImages() {
		try {
			image_Dilecto = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Dilecto.png"));
			image_Thay = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Thay.png"));
			image_Dean = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Dean.png"));
			image_Ralph = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Ralph.png"));
			image_Plumm = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Plumm.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//public BufferedImage getActor() {
		
	//}
}
