package wildwyrd.game.combat;

import java.util.ArrayList;
import java.util.Collections;
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
	public List<Enemy> enemies;
	public ArrayList<Playable> combatant;
	private int impact;
	public Boolean inCombat;
	public boolean win;
	private int turn = 0;
	
	public Combat(GamePanel gp) {
		super(gp);
		this.gp = gp;
		enemies = new ArrayList<Enemy>(5);
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
		//dialogues[0][0] = new Dialoge(user.name + " Attacked!",1);
		//dialogues[0][1] = new Dialoge(target.name + " took " + impact + " damage!",1);
		//dialogues[1][0] = new Dialoge(enemy.name + " was defeated!",1);
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
			//System.out.println(p);
			if(p != null) {
				combatant.add(p);
			}
		}
		for (Enemy e : enemies) {
			System.out.println(e);
			if(e != null) {
				combatant.add(e);
			}
		}
		Collections.sort(combatant);
	}
	
	public void endCombat() {
		gp.gameState = GameState.playState;
		gp.keyH.enterPressed = false;
		for(int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.remove(i);
			combatant.remove(enemy);
			enemy.defeated();
		}
		//gp.ui.drawCombatants(g2);
	}
	
	public boolean enemiesActive() {
		for (Enemy enemy : enemies) {
			System.out.println("Is Alive " + enemy.isAlive());
			if(enemy.isAlive()) {
				return true;
			}
		}
		return false;
	}
	
	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}
	
	public List<Enemy> getEnemies() {
		return enemies;
	}
	
	public void enemyDeath(Enemy enemy) {
		enemy.killed();
		gp.keyH.enterPressed = false;
		dialogues[0][0] = new Dialoge(enemy.name + " was defeated!",1);
		dialogues[0][1] = null;
		startDialogue(this, 0);
		/*if(!enemiesActive()) {
			inCombat = false;
			win = true;
		}*/
	}
	
	public void dealDamage(Playable user, Playable target, int damage) {
		user.setCombatStatus(CombatStatus.Attacking);
		user.loseStamina(5);
		gp.keyH.enterPressed = false;
		impact = damage * 100/(100 + target.baseDefence);
		dialogues[0][0] = new Dialoge(user.name + " Attacked!",1);
		dialogues[0][1] = new Dialoge(target.name + " took " + impact + " damage!",1);
		target.health -= impact;
		
		//if(target.health <= 0) {
		//	if(target instanceof Enemy) {
		//		enemyDeath((Enemy) target);
		//	}
		//} else {
		startDialogue(this, 0);
		//}
		//incrementTurn();
	}
	
	public void blockAttack() {
		gp.playable.get(0).setCombatStatus(CombatStatus.Blocking);
		//incrementTurn();
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
