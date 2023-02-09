package wildwyrd.game.rooms;

import java.awt.Color;
import java.awt.Graphics2D;
import wildwyrd.game.GamePanel;

public class Rm_Blank extends Room {
	GamePanel gp;
	Graphics2D g2;

	public Rm_Blank(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.roomId = 0;
	}

	public void getBackgroundImage() {
		this.g2.setColor(Color.black);
		Graphics2D var10000 = this.g2;
		this.gp.getClass();
		this.gp.getClass();
		var10000.fillRect(0, 0, 768, 512);
	}

	public void setObjects() {
	}

	public void drawObjects() {
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		this.getBackgroundImage();
		this.setObjects();
	}
}