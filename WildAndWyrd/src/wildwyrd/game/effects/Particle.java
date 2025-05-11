package wildwyrd.game.effects;

import java.awt.Color;
import java.awt.Graphics2D;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Particle extends Entity {
	Entity generator;
	Color color;
	int size;
	int xd, yd;
	
	
	public Particle(GamePanel gp, Entity generator, Color color, int size, int speed, int maxLife, int xd, int yd) {
		super(gp);
		
		this.generator = generator;
		this.color = color;
		this.size = size;
		this.speed = speed;
		this.maxHealth = maxLife;
		this.xd = xd;
		this.yd = yd;
		
		health = maxLife;
		int offset = (gp.tileSize/2) - (size/2);
		worldX = generator.worldX + offset;
		worldY = generator.worldY + offset;
	}
	public void update() {
		health--;
		if(health<maxHealth/2) {
			yd++;
		}
		worldX += xd*speed;
		worldY += yd*speed;
		if(health==0) {
			alive = false;
		}
	}
	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		x = screenX;
		y = screenY;

		/*int rightOffset = gp.screenWidth - screenX;
		if (rightOffset > gp.currentMap.getWorldWidth() - gp.player.worldX) {
			x = gp.screenWidth - (gp.currentMap.getWorldWidth() - worldX);
		}

		int bottomOffset = gp.screenHeight - screenY;
		if (bottomOffset > gp.currentMap.getWorldHeight() - gp.player.worldY) {
			y = gp.screenHeight - (gp.currentMap.getWorldHeight() - worldY);
		}*/
		g2.setColor(color);
		g2.fillRect(x, y, size, size);
	}
}
