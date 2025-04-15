package wildwyrd.game.rooms;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;

public class Room extends Entity {
	public BufferedImage image;
	public int roomId;
	public int roomX;
	public int roomY;
	public int room_width;
	public int room_height;
	public int screenX;
	public int screenY;
	public Prop props[];
	public Prop actors[];
	GamePanel gp;
	Graphics2D g2;

	public Room(GamePanel gp) {
		super(gp);
		this.roomX = 0;
		this.roomY = 0;
		this.gp = gp;
	}
	public void drawObjects() {}
	
	public void drawActor(String file, int x, int y, float alpha) {
		image = setup("/res/character/" + file, 200, 400);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.drawImage(image, x, y, gp);
	}
	
	public void getBackgroundImage() {
		
	}
	
	public BufferedImage printScroll(int x, int y, int width, int height) {
		//System.out.println(x + " " + y + " " + width + " " + height);
		return image.getSubimage(x, y, image.getWidth() -1, image.getHeight() - (image.getHeight()/3));
	}
	
	public void scrollDown() {
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
		if (image != null) {
			getBackgroundImage();
		}
	}

	public void setDefaultValues() {
		this.x = this.roomX;
		this.y = this.roomY;
	}
	
	public void setRooms(){
		
	}

	public void startDialogue(Entity object, int setNum) {
		GamePanel gp = this.gp;
		gp.gameState = GameState.dialogueState;
		gp.ui.selectedObject = object;
		dialogueSet = setNum;
	}
}