package wildwyrd.game.combat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.object.Dialoge;
import wildwyrd.game.playable.Combatant;

public class Combat extends Entity {
	GamePanel gp;
	public int dialogueSet = 0;
	public int dialogueIndex = 0;
	public List<Enemy> enemies;
	public ArrayList<Combatant> combatant;
	private int impact;
	public Boolean inCombat;
	public boolean win;
	private int turn = 0;
	public int target;
	public StringBuilder sb;
	
	public Combat(GamePanel gp) {
		super(gp);
		this.gp = gp;
		enemies = new ArrayList<Enemy>(5);
		combatant = new ArrayList<Combatant>(10);
		//combatant.sort(combatant.compareTo(combatant.get(1)));
	}
	
	public Combatant getCombatant() {
		return combatant.get(turn);
	}
	
	public Combatant getNextCombatant() {
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
		turn = 0;
		if(enemies.get(0) != null) {
			gp.playable.get(0).setCombatStatus(CombatStatus.Normal);
			gp.gameState = GameState.combatState;
			inCombat = true;
		}
		for (Combatant p : gp.playable) {
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
		gp.ui.droppedItems.clear();
		gp.gameState = GameState.playState;
		gp.keyH.enterPressed = false;
		/*for(int i = 0; i < gp.playable.size(); i++) {
			combatant.remove(gp.playable.get(i));
		}*/
		try {
			//If enemies are all defeated
			if(!enemiesActive()) {
				Enemy represent = enemies.get(0);
				//Set enemy drops
				for(Enemy enemy : enemies) {
					enemy.checkDrop();
				}
				//
				represent.defeated();
			//If all playable characters are defeated
			} else if (!playableActive()) {
				cleanup();
				gp.gameState = GameState.gameOverState;
			} else {
				cleanup();
			}
		} catch (IndexOutOfBoundsException ex) {
			System.out.println(ex);
		}
	}
	//Reset stage for next combat
	public void cleanup() {
		enemies.clear();
		combatant.clear();
	}
	
	public boolean playableActive() {
		for (Combatant player : gp.playable) {
			if(player.getCombatStatus() == CombatStatus.Escaping) {
				return false;
			}
			if(player.isAlive()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean enemiesActive() {
		for (Enemy enemy : enemies) {
			if(enemy.getCombatStatus() == CombatStatus.Escaping) {
				continue;
			}
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
	
	public void playerDeath(Combatant p) {
		p.killed();
		gp.keyH.enterPressed = false;
		dialogues[0][0] = new Dialoge(p.name + " was defeated!",1);
		dialogues[0][1] = null;
		startDialogue(this, 0);
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
	
	public void dealDamage(Combatant user, Combatant target, int damage) {
		if((user.inRange() && target.inRange()) || user.projectileLoaded()) {
			gp.playSE(13);
			if(user.projectileLoaded()) {
				damage = gp.playable.get(0).fireProjectile();
			}
			user.setCombatStatus(CombatStatus.Attacking);
			user.loseStamina(5);
			gp.keyH.enterPressed = false;
			impact = damage * 100/(100 + target.baseDefence);
			if(target.getCombatStatus() == CombatStatus.Blocking) {
				impact = impact / 10;
				if(impact == 0) {
					impact = 1;
				}
			}
			dialogues[0][0] = new Dialoge(user.name + " Attacked!",1);
			dialogues[0][1] = new Dialoge(target.name + " took " + impact + " damage!",1);
			target.health -= impact;
			
			startDialogue(this, 0);
		}else {
			gp.combat.outOfRange(target);
		}
	}
	
	public void outOfRange(Combatant target) {
		gp.keyH.enterPressed = false;
		dialogues[0][0] = new Dialoge(target.name + " was out of range!",1);
		dialogues[0][1] = null;
		startDialogue(this, 0);
	}
	
	public void blockAttack() {
		gp.playable.get(0).setCombatStatus(CombatStatus.Blocking);
		incrementTurn();
	}
	
	public void openSpecial() {
		gp.playable.get(0).setCombatStatus(CombatStatus.Specializing);
	}
	
	public void openInventory() {
		gp.playable.get(0).setCombatStatus(CombatStatus.Using);
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
