package wildwyrd.game.rooms;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import wildwyrd.game.GamePanel;

public class Room {
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
		this.color = Color.yellow;
		this.roomX = 0;
		this.roomY = 0;
		this.gp = gp;
	}

	public void draw(Graphics2D g2) {
		while (this.roomX < this.room_width && this.roomY < this.room_height) {
			int var10000 = this.roomX;
			this.gp.getClass();
			int worldX = var10000 * 64;
			var10000 = this.roomY;
			this.gp.getClass();
			int worldY = var10000 * 64;
			this.screenX = worldX - this.gp.player.worldX * this.gp.player.screenX;
			this.screenY = worldY - this.gp.player.worldY * this.gp.player.screenY;
			System.out.println(this.image);
			this.gp.getClass();
			if (worldX + 64 > this.gp.player.worldX - this.gp.player.screenX) {
				this.gp.getClass();
				if (worldX - 64 < this.gp.player.worldX + this.gp.player.screenX) {
					this.gp.getClass();
					if (worldY + 64 > this.gp.player.worldY - this.gp.player.screenY) {
						this.gp.getClass();
						var10000 = this.gp.player.worldY;
						var10000 = this.gp.player.screenY;
					}
				}
			}
		}

	}

	public void setDefaultValues() {
		this.x = this.roomX;
		this.y = this.roomY;
	}
}