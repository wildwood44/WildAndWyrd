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
	private Entity head;
	private Entity body;
	private Entity legs;
	private Entity weapon_prime;
	private Entity weapon_second;
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
	public Entity getHead() {
		return head;
	}
	public void setHead(Entity head) {
		this.head = head;
	}
	public Entity getBody() {
		return body;
	}
	public void setBody(Entity body) {
		this.body = body;
	}
	public Entity getLegs() {
		return legs;
	}
	public void setLegs(Entity legs) {
		this.legs = legs;
	}
	public Entity getWeapon_prime() {
		return weapon_prime;
	}
	public void setWeapon_prime(Entity weapon_prime) {
		this.weapon_prime = weapon_prime;
	}
	public Entity getWeapon_second() {
		return weapon_second;
	}
	public void setWeapon_second(Entity weapon_second) {
		this.weapon_second = weapon_second;
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