package wildwyrd.game.effects;

import java.awt.Graphics2D;

import wildwyrd.game.GamePanel;

public class EnvironmentManager {
	GamePanel gp;
	Lighting lighting;
	
	public EnvironmentManager(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setup() {
		lighting = new Lighting(gp);
	}
	
	public void setDayState(DayState dayState) {
		gp.dayState = dayState;
		update();
	}
	
	public void update() {
		lighting.update();
	}
	
	public void draw(Graphics2D g2) {
		lighting.draw(g2);
	}
}