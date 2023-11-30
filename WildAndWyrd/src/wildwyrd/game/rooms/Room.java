package wildwyrd.game.rooms;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Room extends Entity {
	public BufferedImage image;
	public Color color;
	public int roomId;
	public int roomX;
	public int roomY;
	public int room_width;
	public int room_height;
	public int x;
	public int y;
	public int screenX;
	public int screenY;
	GamePanel gp;
	Graphics2D g2;

	public Room(GamePanel gp) {
		super(gp);
		this.color = Color.yellow;
		this.roomX = 0;
		this.roomY = 0;
		this.gp = gp;
	}
	public void drawObjects() {}
	
	public void getBackgroundImage() {
		
	}
	
	public BufferedImage printScroll(int x, int y, int width, int height) {
		//System.out.println(x + " " + y + " " + width + " " + height);
		return image.getSubimage(x, y, image.getWidth() -1, image.getHeight() - (image.getHeight()/3));
	}
	
	public void scrollImage() {
		if(y < 170) {
			printScroll(x, y, 400, 400);
			y++;
		} else {
			gp.csManager.scenePhase++;
		}
		drawObjects();
	}


	public void draw(Graphics2D g2) {
		this.g2 = g2;
		//new Thread(() -> {
			//while(true) {
				if (image != null) {
					//continue;
					getBackgroundImage();
				}
			//}
		//}).start();
		
	}

	public void setDefaultValues() {
		this.x = this.roomX;
		this.y = this.roomY;
	}
}