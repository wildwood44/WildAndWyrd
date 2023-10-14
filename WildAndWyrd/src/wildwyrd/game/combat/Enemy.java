package wildwyrd.game.combat;

import java.awt.Color;
import java.awt.Graphics2D;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.playable.Combatant;

public class Enemy extends Combatant {
	protected Entity dropped;
	int screenX = gp.tileSize*6;
	int screenY = gp.tileSize*2;
	
	public Enemy(GamePanel gp, String name, int maxHealth, int maxStamina, int baseAttack, int baseDefence, int baseAccuracy, int baseSpeed, int baseEvasion) {
		super(gp, name, maxHealth, maxStamina, baseAttack, baseDefence, baseAccuracy, baseSpeed, baseEvasion);
		setDialogue();
		getImage();
	}
	
	public void draw(Graphics2D g2, int pos) {
		//BufferedImage image = getImage();
		screenX = gp.tileSize*6;
		screenY = gp.tileSize*2;
		if (pos == 1) {
			screenX = gp.tileSize*6;
			screenY = gp.tileSize*1;
		}
		if (inRear) {
			screenX += (gp.tileSize*2);
		}
		
		if(dying) {
			dyingAnimation(g2);
		} else if (getCombatStatus() == CombatStatus.Escaping) {
			escapingAnimation(g2);
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
	public void escapingAnimation(Graphics2D g2) {
		dyingCounter++;
		int i = 5;
		if(dyingCounter <= i) {changeAlpha(g2,0f);}
		if(dyingCounter > i && dyingCounter <= i*2) {screenX += 10;}
		if(dyingCounter > i*2 && dyingCounter <= i*3) {screenX += 20;changeAlpha(g2,0.8f);}
		if(dyingCounter > i*3 && dyingCounter <= i*4) {screenX += 30;changeAlpha(g2,0.6f);}
		if(dyingCounter > i*4 && dyingCounter <= i*5) {screenX += 40;changeAlpha(g2,0.4f);}
		if(dyingCounter > i*5 && dyingCounter <= i*6) {screenX += 50;changeAlpha(g2,0.2f);}
		if(dyingCounter > i*6 && dyingCounter <= i*7) {screenX += 60;changeAlpha(g2,0f);}
		if(dyingCounter > i*7) {
			dying = false;
			alive = false;
		}
	}
	
	public void checkDrop() {}
	
	public void dropItem(Entity droppedItem) {
		System.out.println(droppedItem);
		gp.ui.droppedItems.add(droppedItem);
		gp.player.pickUpObject(droppedItem);
		/*for(int i = 0; i < gp.obj[gp.currentMap.getId()].length; i++) {
			if(gp.obj[gp.currentMap.getId()][i] == null) {
				gp.obj[gp.currentMap.getId()][i] = droppedItem;
				gp.obj[gp.currentMap.getId()][i].worldX = worldX;
				gp.obj[gp.currentMap.getId()][i].worldY = worldY;
				break;
			}
		}*/
	}
	
	public void defeated() {
		
	}
	
	public void combatResult() {
		
	}
}
