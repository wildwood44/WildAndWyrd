package wildwyrd.game.rooms;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import wildwyrd.game.GamePanel;

public class Rm_Forton extends Room {
	GamePanel gp;
	Graphics2D g2;

	public Rm_Forton(GamePanel gp) {
		super(gp);
		this.gp = gp;
		roomId = 1;
		room_width = gp.screenWidth;
		room_height = gp.screenHeight;	
		x = 0;
	}

	public void drawObjects() {
		image = setup("/res/backgrounds/animated/tapestry-1", 400, 800);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		image2 = printScroll(x, y, 400,600);
		g2.drawImage(image2, (room_width / 4), 0, gp);
	}

	public void getBackgroundImage() {
		image = setup("/res/backgrounds/Forton_Backgound", room_width, room_height);
		g2.drawImage(image, screenX, screenY, gp);	
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		getBackgroundImage();
		//scrollImage();
		//drawObjects();
	}
}