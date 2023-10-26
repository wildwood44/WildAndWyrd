package wildwyrd.game.rooms;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.GamePanel;
import wildwyrd.game.tile.UtilityTool;

public class Rm_Forton extends Room {
	GamePanel gp;
	Graphics2D g2;

	public Rm_Forton(GamePanel gp) {
		super(gp);
		this.gp = gp;
		roomId = 1;
		room_width = 1600;
		room_height = 600;
	}

	public void getBackgroundImage() {		
		UtilityTool uTool = new UtilityTool();
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/Forton_Backgound.png"));
			image = uTool.scaleImage(image, gp.screenWidth, gp.screenHeight);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true) {
			g2.drawImage(image, 0, 0, gp);
		}
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		//if (image != null) {
		//getBackgroundImage();
		//}
		UtilityTool uTool = new UtilityTool();

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/backgrounds/Forton_Backgound.png"));
			image = uTool.scaleImage(image, gp.screenWidth, gp.screenHeight);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//while(true) {
			g2.drawImage(image, 0, 0, gp);
		//}
		//g2.dispose();
	}
}