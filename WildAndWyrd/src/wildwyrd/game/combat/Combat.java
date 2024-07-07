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
	public CombatRecord cr;
	public List<Enemy> enemies;
	public ArrayList<Combatant> combatant;
	private int impact;
	public Boolean inCombat = false;
	public boolean win;
	private int turn = 0;
	public int target;
	private boolean canFlee = false;
	public StringBuilder sb;
	
	public Combat(GamePanel gp) {
		super(gp);
		this.gp = gp;
		cr = new CombatRecord();
		enemies = new ArrayList<Enemy>(5);
		combatant = new ArrayList<Combatant>(10);
		skippable = false;
	}
	
	public Combat(GamePanel gp, List<Enemy> enemies) {
		this(gp);
		addEnemy(enemies);
		startCombat();
	}
	
	public Combat(GamePanel gp, boolean canFlee, List<Enemy> enemies) {
		this(gp, enemies);
		this.canFlee = canFlee;
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

	public void setDialogue(Combatant user, Combatant target) {
		dialogues[0][0] = new Dialoge(user.name + " Attacked!",1);
		dialogues[0][1] = new Dialoge(target.name + " took " + impact + " damage!",1);
		dialogues[1][0] = new Dialoge(user.name + " was defeated!",1);
		dialogues[2][0] = new Dialoge(user.name + " fled!",1);
		dialogues[3][0] = new Dialoge(target.name + " was out of range!",1);
		dialogues[4][0] = new Dialoge("There is no running here!", 1);
	}

	public void startDialogue(Entity object, int setNum) {
		GamePanel gp = this.gp;
		gp.gameState = GameState.dialogueState;
		gp.ui.selectedObject = object;
		dialogueSet = setNum;
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
		setDialogue(combatant.get(0),enemies.get(0));
		Collections.sort(combatant);
		gp.playMusic(34);
	}
	//End Combat
	public void endCombat() {
		cr.playRecord();
		gp.ui.droppedItems.clear();
		gp.gameState = GameState.playState;
		gp.keyH.enterPressed = false;
		gp.stopMusic();
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
			//if(player.getCombatStatus() == CombatStatus.Escaping) {
				//return false;
			//}
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
	
	public void addEnemy(List<Enemy> e) {
		enemies.addAll(e);
	}
	
	public void addEnemy(Enemy e1, Enemy e2) {
		Collections.addAll(enemies,e1,e2);
	}
	
	public List<Enemy> getEnemies() {
		return enemies;
	}
	
	public void escapeFailed() {
		gp.keyH.enterPressed = false;
		startDialogue(this, 4);
	}
	
	public void playerDeath(Combatant p) {
		p.killed();
		gp.keyH.enterPressed = false;
		//dialogues[0][0] = new Dialoge(p.name + " was defeated!",1);
		//dialogues[0][1] = null;
		setDialogue(p, p);
		startDialogue(this, 1);
	}
	
	public void enemyDeath(Enemy enemy) {
		enemy.killed();
		gp.keyH.enterPressed = false;
		//dialogues[0][0] = new Dialoge(enemy.name + " was defeated!",1);
		//dialogues[0][1] = null;
		setDialogue(enemy, enemy);
		startDialogue(this, 1);
	}
	
	public Enemy getTarget() {
		return enemies.get(target);
	}
	
	public void dealDamage(Combatant user, Combatant target, int damage) {
		user.setCombatStatus(CombatStatus.Attacking);
		if((user.inRange() && target.inRange()) || user.projectileLoaded()) {
			gp.playSE(13);
			if(user.projectileLoaded()) {
				damage = gp.playable.get(0).fireProjectile();
			}
			user.loseStamina(5);
			gp.keyH.enterPressed = false;
			impact = damage * 100/(100 + target.baseDefence);
			if(target.getCombatStatus() == CombatStatus.Blocking) {
				impact = impact / 10;
				if(impact == 0) {
					impact = 1;
				}
			}
			//dialogues[0][0] = new Dialoge(user.name + " Attacked!",1);
			//dialogues[0][1] = new Dialoge(target.name + " took " + impact + " damage!",1);
			target.health -= impact;
			cr.addFrame(user.getCombatStatus(), damage, user, target);
			setDialogue(user, target);
			startDialogue(this, 0);
		}else {
			gp.combat.outOfRange(target);
		}
	}
	
	public void outOfRange(Combatant target) {
		gp.keyH.enterPressed = false;
		//dialogues[0][0] = new Dialoge(target.name + " was out of range!",1);
		//dialogues[0][1] = null;
		setDialogue(target, target);		
		startDialogue(this, 3);
	}
	
	public void blockAttack() {
		gp.playable.get(0).setCombatStatus(CombatStatus.Blocking);
		cr.addFrame(gp.playable.get(0).getCombatStatus(), 0, gp.playable.get(0));
		incrementTurn();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public void setCanFlee(boolean canFlee) {
		this.canFlee = canFlee;
	}
	
	public boolean canFlee() {
		return canFlee;
	}
	
	public void incrementTurn() {
		turn++;
		if(turn >= combatant.size()) {
			turn = 0;
		}
	}
}
