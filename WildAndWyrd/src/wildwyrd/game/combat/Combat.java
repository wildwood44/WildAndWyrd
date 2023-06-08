package wildwyrd.game.combat;

import java.awt.Container;
import java.util.ArrayList;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.object.Dialoge;

public class Combat {
	GamePanel gp;
	public Dialoge[][] dialogues = new Dialoge[10][20];
	public int dialogueSet = 0;
	public int dialogueIndex = 0;
	public ArrayList<Entity> enemies;
	
	public Combat(GamePanel gp) {
		this.gp = gp;
		enemies = new ArrayList<Entity>(5);
	}

	public void setDialogue() {
		
	}

	public void startDialogue(Entity object, int setNum) {
		GamePanel gp = this.gp;
		gp.gameState = GameState.examineState;
		gp.ui.selectedObject = object;
		dialogueSet = setNum;
	}

	public void startCombat() {
		if(enemies.get(0) != null) {
			gp.gameState = GameState.combatState;
		}
	}
	
	public void addEnemy(Entity enemy) {
		enemies.add(enemy);
	}
	
	public ArrayList<Entity> getEnemies() {
		return enemies;
	}
	
	public void dealDamage(Entity target, int damage) {
		dialogues[0][0] = new Dialoge(target.name + " took " + damage + " damage!",1);
		target.takeDamage();
		damage = damage * 100/(100 + target.baseDefence);
		//impact * (100/(100 + e.defence()))
		System.out.println(damage);
		target.health -= damage;
		gp.gameState = GameState.combatState;
	}
}
