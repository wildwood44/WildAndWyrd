package wildwyrd.game.playable;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Playable extends Entity {
	private int level;
	private int health;
	private int maxHealth;
	private int stamina;
	private int maxStamina;
	private int exp;
	private int nextLevelExp;
	private int strength;
	private int dexterity;
	private int baseAttack;
	private int baseDefence;
	private int baseAccuracy;
	private int baseEvasion;
	private int baseSpeed;
	public Playable(GamePanel gp, String name, int health, int stamina,
			int baseAttack, int baseDefence, int baseAccuracy, int baseEvasion, int baseSpeed) {
		super(gp);
		this.level = 1;
		this.name = name;
		this.maxHealth = health;
		this.health = this.maxHealth;
		this.maxStamina = stamina;
		this.stamina = this.maxStamina;
		this.baseAttack = baseAttack;
		this.baseDefence = baseDefence;
		this.baseAccuracy = baseAccuracy;
		this.baseEvasion = baseEvasion;
		this.baseSpeed = baseSpeed;
	}
	@Override
	public String toString() {
		return name + " - " + level + 
				"£ £Health:  " + health + "\\" + maxHealth +
				"£Stamina: " + stamina + "\\" + maxStamina +
				"£Attack: " + baseAttack +
				"£Defence: " + baseDefence +
				"£Accuracy: " + baseAccuracy +
				"£Evasion: " + baseEvasion +
				"£Speed: " + baseSpeed;
	}
}