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
	
	public void getBackgroundImage() {
		
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