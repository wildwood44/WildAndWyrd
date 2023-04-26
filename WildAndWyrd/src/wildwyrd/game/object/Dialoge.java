package wildwyrd.game.object;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

enum character {
	Dilecto, Thay, Dean, Raplh, Plumm
}

public class Dialoge {
	public String speaker;
	public String text;
	public int type;
	BufferedImage image_Dilecto;
	BufferedImage image_Thay;
	BufferedImage image_Dean;
	BufferedImage image_Ralph;
	BufferedImage image_Plumm;

	public Dialoge(String text, int type) {
		this.text = text;
		this.type = type;
	}
	
	public Dialoge(String speaker, String text, int type) {
		this.speaker = speaker; 
		this.text = text;
		this.type = type;
	}

	public String getText() {
		return this.text;
	}

	public int getType() {
		return this.type;
	}

	public String getSpeaker() {
		return speaker;
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
}