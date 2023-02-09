package wildwyrd.game.rooms;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.GamePanel;

public class Rm_Kitchen extends Room {
	GamePanel gp;
	Graphics2D g2;

	public Rm_Kitchen(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.roomId = 1;
		this.room_width = 1600;
		this.room_height = 600;
	}

	public void getBackgroundImage() {
		try {
			this.image = ImageIO.read(this.getClass().getResourceAsStream("/res/backgrounds/bg_cottage_kitchen.png"));
		} catch (IOException var2) {
			var2.printStackTrace();
		}

		this.g2.drawImage(this.image, this.screenX, this.screenY, this.gp);
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