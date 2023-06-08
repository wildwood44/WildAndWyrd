package wildwyrd.game.playable;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Playable extends Entity {
	private int level;
	//private int health;
	//private int maxHealth;
	private int stamina;
	private int maxStamina;
	private int exp;
	private int nextLevelExp;
	private int strength;
	private int dexterity;
	//private int baseAttack;
	//private int baseDefence;
	//private int baseAccuracy;
	//private int baseEvasion;
	//private int baseSpeed;
	public BufferedImage combatSpt;
	private Entity head = new Entity(gp);
	private Entity body = new Entity(gp);
	private Entity legs = new Entity(gp);
	private Entity weapon_prime = new Entity(gp);
	private Entity weapon_second = new Entity(gp);
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
		try {
			combatSpt = ImageIO.read(getClass().getResourceAsStream("/res/sprite/combat/Alder_Combat_Base.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		if(this.weapon_second != null) {
			gp.player.inventory.add(this.weapon_second);
		}
		this.weapon_second = weapon_second;
	}
	public int getHealth() {
		return health;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public int getMaxStamina() {
		return maxStamina;
	}
	public int getBaseAttack() {
		return baseAttack;
	}
	public int getAttack() {
		if(weapon_prime == null) {
			return baseAttack;
		}
		return baseAttack + weapon_prime.attackValue;
	}
	public int getBaseDefence() {
		return baseDefence;
	}
	public int getDefence() {
		return baseDefence;
	}
	public int getAccuracy() {
		return baseAccuracy;
	}
	public int getEvasion() {
		return baseEvasion;
	}
	public int getSpeed() {
		return baseSpeed;
	}

	public BufferedImage getImage() {
		BufferedImage sprite = null;

		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/res/sprite/combat/Alder_Combat_Base.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sprite;
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = getImage();
		int screenX = gp.tileSize*4;
		int screenY = gp.tileSize*2;

		g2.drawImage(image, screenX, screenY, gp.tileSize*2, gp.tileSize*2, null);
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