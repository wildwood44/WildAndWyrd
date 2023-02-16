package wildwyrd.game.object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class SuperObject {
	public BufferedImage image;
	Graphics2D g2;
	GamePanel gp;
	public int x;
	public int y;
	public int width;
	public int height;
	public String name;
	public int worldX;
	public int worldY;
	public Boolean selected;
	public String[][] dialogues = new String[5][20];
	public int dialogueSet = 0;
	public int dialogueIndex = 0;

	public SuperObject(GamePanel gp) {
		this.gp = gp;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void draw(Graphics2D g2, GamePanel gp) {
		g2.drawImage(this.image, this.x, this.y, this.width, this.height, (ImageObserver) null);
	}

	public void startDialogue(Entity object, int setNum) {
		this.gp.ui.selectedObject = object;
		this.dialogueSet = setNum;
	}

	public void interact() {
	}
}