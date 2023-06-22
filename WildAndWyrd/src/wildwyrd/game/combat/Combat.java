package wildwyrd.game.combat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.object.Dialoge;
import wildwyrd.game.playable.Playable;

public class Combat extends Entity {
	GamePanel gp;
	//public Dialoge[][] dialogues = new Dialoge[10][20];
	public int dialogueSet = 0;
	public int dialogueIndex = 0;
	public List<Playable> enemies;
	public ArrayList<Playable> combatant;
	private int impact;
	public Boolean inCombat;
	private int turn = 0;
	
	public Combat(GamePanel gp) {
		super(gp);
		this.gp = gp;
		enemies = new ArrayList<Playable>(5);
		combatant = new ArrayList<Playable>(10);
		//combatant.sort(combatant.compareTo(combatant.get(1)));
	}
	
	public Playable getCombatant() {
		return combatant.get(turn);
	}
	
	public Playable getNextCombatant() {
		int index = turn + 1;
		if(index >= combatant.size()) {
			return combatant.get(0);
		}
		return combatant.get(index);
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
			gp.playable.get(0).setCombatStatus(CombatStatus.Normal);
			gp.gameState = GameState.combatState;
			inCombat = true;
		}
		for (Playable p : gp.playable) {
			System.out.println(p);
			if(p != null) {
				combatant.add(p);
			}
		}
		for (Playable e : enemies) {
			if(e != null) {
				combatant.add(e);
			}
		}
		Collections.sort(combatant);
	}
	
	public void endCombat() {
		gp.gameState = GameState.playState;
		gp.keyH.enterPressed = false;

	}
	
	public boolean enemiesActive() {
		for (Playable enemy : enemies) {
			if(enemy.health > 0) {
				return true;
			}
		}
		return false;
	}
	
	public void addEnemy(Playable enemy) {
		enemies.add(enemy);
	}
	
	public List<Playable> getEnemies() {
		return enemies;
	}
	
	public void enemyDeath(Playable enemy) {
		gp.keyH.enterPressed = false;
		dialogues[0][1] = new Dialoge(enemy.name + " was defeated!",1);
		startDialogue(this, 0);
		enemies.remove(enemy);
		if(!enemiesActive()) {
			inCombat = false;
		}
	}
	
	public void dealDamage(Playable target, int damage) {
		gp.playable.get(0).setCombatStatus(CombatStatus.Attacking);
		gp.playable.get(0).loseStamina(5);
		gp.keyH.enterPressed = false;
		impact = damage * 100/(100 + target.baseDefence);
		dialogues[0][0] = new Dialoge(target.name + " took " + impact + " damage!",1);
		target.health -= impact;
		startDialogue(this, 0);
		if(target.health <= 0) {
			enemyDeath(target);
		}
		incrementTurn();
	}
	
	public void blockAttack() {
		gp.playable.get(0).setCombatStatus(CombatStatus.Blocking);
		incrementTurn();
	}
	
	public void advRet() {
		
	}
	
	public int getTurn() {
		return turn;
	}
	
	public void incrementTurn() {
		turn++;
		if(turn >= combatant.size()) {
			turn = 0;
		}
	}
}
