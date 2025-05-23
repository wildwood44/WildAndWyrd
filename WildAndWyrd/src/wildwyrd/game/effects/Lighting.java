package wildwyrd.game.effects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import wildwyrd.game.GamePanel;

public class Lighting {
	GamePanel gp;
	float filterAlpha;
	BufferedImage darknessFilter;
	BufferedImage duskFilter;
	
	public Lighting(GamePanel gp) {
		this.gp = gp;
		setLightSource();
	}
	
	public void setLightSource() {
		darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
		if(gp.dayState == DayState.DUSK) {
			g2.setColor(new Color(0,0,0.1f,0.50f));
		} else if(gp.dayState == DayState.NIGHT) {
			// Get centre left x and y of light circle
			int centerX = gp.player.screenX + (gp.tileSize)/2;
			int centerY = gp.player.screenY + (gp.tileSize)/2;
			//Create a gradation effect within the light circle
			Color color[] = new Color[12];
			float fraction[] = new float[12];
			color[0] = new Color(0,0,0.1f,0.1f);
			color[1] = new Color(0,0,0.1f,0.42f);
			color[2] = new Color(0,0,0.1f,0.52f);
			color[3] = new Color(0,0,0.1f,0.61f);
			color[4] = new Color(0,0,0.1f,0.69f);
			color[5] = new Color(0,0,0.1f,0.76f);
			color[6] = new Color(0,0,0.1f,0.82f);
			color[7] = new Color(0,0,0.1f,0.87f);
			color[8] = new Color(0,0,0.1f,0.91f);
			color[9] = new Color(0,0,0.1f,0.94f);
			color[10] = new Color(0,0,0.1f,0.96f);
			color[11] = new Color(0,0,0.1f,0.98f);
			fraction[0] = 0f;
			fraction[1] = 0.4f;
			fraction[2] = 0.5f;
			fraction[3] = 0.6f;
			fraction[4] = 0.65f;
			fraction[5] = 0.7f;
			fraction[6] = 0.75f;
			fraction[7] = 0.8f;
			fraction[8] = 0.85f;
			fraction[9] = 0.9f;
			fraction[10] = 0.95f;
			fraction[11] = 1f;
			// Create a gradation paint setting for the light circle
			RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, /*(gp.player.currentLight.lightRadius)*/350/2, fraction, color);
			// Get the gradient data on g2
			g2.setPaint(gPaint);
		}
		// Draw the light circle
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.dispose();
	}
	
	public void update() {
		if(gp.dayState == DayState.DAY) {
			filterAlpha = 0f;
			setLightSource();
		} else if(gp.dayState == DayState.NIGHT) {
			filterAlpha = 1f;
			setLightSource();
		} else if(gp.dayState == DayState.DUSK) {
			filterAlpha = 0.5f;
			setLightSource();
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
		g2.drawImage(darknessFilter, 0, 0, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		String situation = "";
	}
}
