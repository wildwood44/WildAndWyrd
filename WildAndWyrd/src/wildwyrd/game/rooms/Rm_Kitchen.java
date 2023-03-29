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
		roomId = 1;
		room_width = 1600;
		room_height = 600;
	}

	public void getBackgroundImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/bg_cottage_kitchen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		g2.drawImage(image, screenX, screenY, gp);
	}

	public void setObjects() {
	}

	public void drawObjects() {
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		getBackgroundImage();
		setObjects();
	}
}