package wildwyrd.game.object;

import java.awt.image.BufferedImage;

enum character {
	Dilecto, Thay, Dean, Raplh, Plumm
}

public class Dialoge {
	public String speaker;
	public String text;
	public int type;
	public BufferedImage image;

	public Dialoge(String text, int type) {
		this.text = text;
		this.type = type;
	}
	
	public Dialoge(String speaker, String text, int type) {
		this(text, type);
		this.speaker = speaker; 
	}
	
	public Dialoge(String speaker, String text, BufferedImage image) {
		this(speaker, text, 1);
		this.image = image;
	}
	
	public Dialoge(String text, BufferedImage image) {
		this(text, 1);
		this.image = image;
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
}