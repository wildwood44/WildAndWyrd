package wildwyrd.game.playable;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Weapon;

public class Playable extends Combatant {
	private int level = 1;
	private int exp;
	private int nextLevelExp;
	private int strength;
	private int dexterity;

	public Playable(GamePanel gp, String name, int health, int stamina, int baseAttack, int baseDefence,
			int baseAccuracy, int baseEvasion, int baseSpeed) {
		super(gp, name, health, stamina, baseAttack, baseDefence, baseAccuracy, baseEvasion, baseSpeed);
		// TODO Auto-generated constructor stub
	}

	public void levelUp() {
		if(exp > nextLevelExp) {
			level++;
		}
	}
	
	public void setDefaultValues() {
		level = 0;
		maxHealth = 60;
		maxStamina = 60;
		baseAttack = 10;
		baseDefence = 10;
		baseAccuracy = 5;
		baseEvasion = 10;
		baseSpeed = 5;
		head = new Entity(gp);
		body = new Entity(gp);
		legs = new Entity(gp);
		weapon_prime = new Weapon(gp);
		weapon_second = new Weapon(gp);
		loadedProjectile = null;
	}
	
	@Override
	public String toString() {
		return name + " - " + level + 
				"£ £Health:  " + health + "\\" + maxHealth +
				"£Stamina: " + stamina + "\\" + maxStamina +
				"£Attack: " + getAttack() +
				"£Defence: " + getDefence() +
				"£Accuracy: " + getAccuracy() +
				"£Evasion: " + getEvasion() +
				"£Speed: " + getSpeed();
	}
}
