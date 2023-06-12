package wildwyrd.game.combat;

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
	public Boolean inCombat;
	
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
			System.out.println(gp.gameState);
			gp.gameState = GameState.combatState;
			inCombat = true;
		}
	}
	
	public void endCombat() {
		gp.gameState = GameState.playState;
		gp.keyH.enterPressed = false;

	}
	
	public boolean enemiesActive() {
		for (Entity enemy : enemies) {
			if(enemy.health > 0) {
				return true;
			} else {
				return false;
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
	
	public void enemyDeath(Entity enemy) {
		gp.ui.choiceSlot = 0;
		gp.ui.firstValue = 0;

		gp.keyH.enterPressed = false;
		dialogues[0][1] = new Dialoge(enemy.name + " was defeated!",1);
		startDialogue(this, 0);
		enemies.remove(enemy);
		if(!enemiesActive()) {
			inCombat = false;
		}
	}
	
	public void dealDamage(Entity target, int damage) {
		gp.playable[0].setCombatStatus(CombatStatus.Attacking);
		gp.ui.choiceSlot = 0;
		gp.ui.firstValue = 0;
		gp.keyH.enterPressed = false;
		impact = damage * 100/(100 + target.baseDefence);
		dialogues[0][0] = new Dialoge(target.name + " took " + impact + " damage!",1);
		target.health -= impact;
		startDialogue(this, 0);
		if(target.health <= 0) {
			enemyDeath(target);
		}
	}
	
	public void blockAttack(Entity target) {
		gp.playable[0].setCombatStatus(CombatStatus.Blocking);
	}
	
	public void advRet() {
		
	}
}
