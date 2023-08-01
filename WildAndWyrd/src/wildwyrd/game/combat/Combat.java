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
	public int dialogueSet = 0;
	public int dialogueIndex = 0;
	public List<Enemy> enemies;
	public ArrayList<Playable> combatant;
	private int impact;
	public Boolean inCombat;
	public boolean win;
	private int turn = 0;
	public int target;
	
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
			//System.out.println(e);
			if(e != null) {
				combatant.add(e);
			}
		}
		Collections.sort(combatant);
	}
	
	public void endCombat() {
		gp.gameState = GameState.playState;
		gp.keyH.enterPressed = false;
		/*for(int i = 0; i < gp.playable.size(); i++) {
			combatant.remove(gp.playable.get(i));
		}*/
		try {
			Enemy represent = enemies.get(0);
			for(Enemy enemy : enemies) {
				enemy.checkDrop();
			}
			represent.defeated();
		} catch (IndexOutOfBoundsException ex) {
			System.out.println(ex);
		}
	}
	
	public void cleanup() {
		enemies.clear();
		combatant.clear();
	}
	
	public boolean enemiesActive() {
		for (Enemy enemy : enemies) {
			if(enemy.isAlive()) {
				return true;
			}
		}
		return false;
	}
	
	public void addEnemy(Enemy e1) {
		enemies.add(e1);
	}
	
	public void addEnemy(Enemy e1, Enemy e2) {
		Collections.addAll(enemies,e1,e2);
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
	}
	
	public Enemy getTarget() {
		return enemies.get(target);
	}
	
	public void dealDamage(Playable user, Playable target, int damage) {
		//System.out.println(combatant);
		//gp.combat.findTarget();
		user.setCombatStatus(CombatStatus.Attacking);
		user.loseStamina(5);
		gp.keyH.enterPressed = false;
		impact = damage * 100/(100 + target.baseDefence);
		dialogues[0][0] = new Dialoge(user.name + " Attacked!",1);
		dialogues[0][1] = new Dialoge(target.name + " took " + impact + " damage!",1);
		target.health -= impact;
		
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
