package wildwyrd.game.rooms;

import java.awt.Graphics2D;
import wildwyrd.game.GamePanel;

public class Rm_Woods extends Room {
	GamePanel gp;
	Graphics2D g2;

	public Rm_Woods(GamePanel gp) {
		super(gp);
		this.gp = gp;
		roomId = 1;
		room_width = gp.screenWidth;
		room_height = gp.screenHeight;	
		x = 0;
	}

	public void drawObjects() {
	}

	public void getBackgroundImage() {
		image = setup("/res/backgrounds/bg_forest", room_width, room_height);
		g2.drawImage(image, screenX, screenY, gp);	
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		getBackgroundImage();
		//scrollImage();
		//drawObjects();
	}
}