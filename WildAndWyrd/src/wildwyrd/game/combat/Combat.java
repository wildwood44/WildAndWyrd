package wildwyrd.game.combat;

import java.awt.Container;
import java.util.ArrayList;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.object.Dialoge;

public class Combat extends Entity {
	GamePanel gp;
	//public Dialoge[][] dialogues = new Dialoge[10][20];
	public int dialogueSet = 0;
	public int dialogueIndex = 0;
	public ArrayList<Entity> enemies;
	private int impact;
	
	public Combat(GamePanel gp) {
		super(gp);
		this.gp = gp;
		enemies = new ArrayList<Entity>(5);
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
	}

	public void startCombat() {
		if(enemies.get(0) != null) {
			gp.gameState = GameState.combatState;
		}
	}
	
	public boolean enemiesActive() {
		for (Entity enemy : enemies) {
			if(enemy.health > 0) {
				return true;
			}
		}
		return false;
	}
	
	public void addEnemy(Entity enemy) {
		enemies.add(enemy);
	}
	
	public ArrayList<Entity> getEnemies() {
		return enemies;
	}
	
	public void dealDamage(Entity target, int damage) {
		gp.ui.choiceSlot = 0;
		gp.ui.firstValue = 0;
		//takeDamage();

		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
		impact = damage * 100/(100 + target.baseDefence);
		//impact * (100/(100 + e.defence()))
		//System.out.println(damage);
		dialogues[0][0] = new Dialoge(target.name + " took " + impact + " damage!",1);
		target.health -= impact;
		if(!enemiesActive()) {
			gp.gameState = GameState.playState;
		}
		
	}
}
