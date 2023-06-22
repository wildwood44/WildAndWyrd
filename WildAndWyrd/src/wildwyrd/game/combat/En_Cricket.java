package wildwyrd.game.combat;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.object.Dialoge;
import wildwyrd.game.playable.Playable;

public class En_Cricket extends Playable {
	public En_Cricket(GamePanel gp) {
		super(gp,"Cricket",  20, 10, 0, 8, 90, 10, 23);
        String desc = "Big grasshoppers with long antenna. Can jump a fair distance.";
		type = EntityType.Sprite;
		speed = 1;
		setDialogue();
	}

	public BufferedImage getImage() {
		BufferedImage sprite = null;

		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/res/sprite/combat/cricket.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sprite;
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = getImage();
		int screenX = gp.tileSize*6;
		int screenY = gp.tileSize*2;

		g2.drawImage(image, screenX, screenY, gp.tileSize*2, gp.tileSize*2, null);
		
		//Health
		double oneScale = (double)gp.tileSize/maxHealth;
		double healthValue = oneScale * health;
		g2.setColor(new Color(35,35,35));
		g2.fillRect(screenX - 2, screenY - 2, gp.tileSize+2, 12);
		g2.setColor(new Color(255,0,0));
		g2.fillRect(screenX, screenY, (int)healthValue, 10);
	}
	
	public void setAction() {
		
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(name + " stood wary." ,1);
	}

	public void startDialogue(Entity object, int setNum) {
		GamePanel gp = this.gp;
		gp.gameState = GameState.dialogueState;
		gp.ui.selectedObject = object;
		dialogueSet = setNum;
	}
	
	public void takeDamage() {
		gp.ui.choiceSlot = 0;
		gp.ui.firstValue = 0;
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
	
	public void action() {
		gp.ui.choiceSlot = 0;
		gp.ui.firstValue = 0;
		gp.keyH.enterPressed = false;
		startDialogue(this, 0);
		gp.combat.incrementTurn();
	}
}
