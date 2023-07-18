package wildwyrd.game.combat;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.playable.Playable;

public class Enemy extends Playable {
	private int spriteCounter = 0;
	private int actionLockCounter = 0;
	private int invincibleCounter = 0;
	private int dyingCounter = 0;
	public Enemy(GamePanel gp, String name, int maxHealth, int maxStamina, int baseAttack, int baseDefence, int baseAccuracy, int baseSpeed, int baseEvasion) {
		super(gp, name, maxHealth, maxStamina, baseAttack, baseDefence, baseAccuracy, baseSpeed, baseEvasion);
		setDialogue();
		getImage();
	}
	
	public void draw(Graphics2D g2) {
		//BufferedImage image = getImage();
		int screenX = gp.tileSize*6;
		int screenY = gp.tileSize*2;
		if(dying) {
			dyingAnimation(g2);
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize*2, gp.tileSize*2, null);
		
		//Health
		double oneScale = (double)gp.tileSize/maxHealth;
		double healthValue = oneScale * health;
		g2.setColor(new Color(35,35,35));
		g2.fillRect(screenX - 2, screenY - 2, gp.tileSize+2, 12);
		g2.setColor(new Color(255,0,0));
		g2.fillRect(screenX, screenY, (int)healthValue, 10);
	}
	
	public void update() {
		//draw(g2)
	}
	
	public void setAction() {
		
	}

	public void setDialogue() {
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
	
	public void speak() {
		gp.ui.choiceSlot = 0;
		gp.ui.firstValue = 0;
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
	
	public void changeAlpha(Graphics2D g2, float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}
	
	public void dyingAnimation(Graphics2D g2) {
		dyingCounter++;
		int i = 5;
		if(dyingCounter <= i) {changeAlpha(g2,0f);}
		if(dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2,1f);}
		if(dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2,0f);}
		if(dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2,1f);}
		if(dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2,0f);}
		if(dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2,1f);}
		if(dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2,0f);}
		if(dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2,1f);}
		if(dyingCounter > i*8) {
			dying = false;
			alive = false;
		}
	}
	
	public void checkDrop() {
		
	}
	
	public void dropItem() {
		for(int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i] == null) {
				//gp.obj[i] = droppedItem;
				//gp.obj[i].worldX = worldX;
				//gp.obj[i].worldY = worldY;
				break;
			}
		}
	}
	
	public void defeated() {
		
	}
}
