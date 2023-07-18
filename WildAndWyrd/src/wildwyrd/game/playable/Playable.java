package wildwyrd.game.playable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.combat.CombatStatus;

public class Playable extends Entity implements Comparable<Playable> {
	protected String imageUrl;
	private int level;
	private int stamina;
	private int maxStamina;
	private int exp;
	private int nextLevelExp;
	private int strength;
	private int dexterity;
	protected boolean alive = true;
	protected boolean dying = false;
	private Entity head = new Entity(gp);
	private Entity body = new Entity(gp);
	private Entity legs = new Entity(gp);
	private Entity weapon_prime = new Entity(gp);
	private Entity weapon_second = new Entity(gp);
	private CombatStatus combatStatus = CombatStatus.Normal;
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
		getImage();
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
	public void heal(int restore) {
		health += restore;
		if(health > maxHealth) {
			health = maxHealth;
		}
	}
	public void takeDamage(int impact) {
		health -= impact;
		if(health <= 0) {
			gp.gameState = GameState.gameOverState;
		}
	}
	public int getStamina() {
		return stamina;
	}
	public int getMaxStamina() {
		return maxStamina;
	}
	public void eat(int restore) {
		stamina += restore;
		if(stamina > maxStamina) {
			stamina = maxStamina;
		}
	}
	public void loseStamina(int cost) {
		stamina -= cost;
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

	public boolean isAlive() {
		return alive;
	}

	public boolean isDying() {
		return dying;
	}

	public void killed() {
		dying = true;
	}

	public CombatStatus getCombatStatus() {
		return combatStatus;
	}
	public void setCombatStatus(CombatStatus combatStatus) {
		this.combatStatus = combatStatus;
	}
	public void getImage() {
		image = setup("/res/sprite/combat/Alder_Combat_Base",gp.tileSize*6,gp.tileSize*2);
	}
	
	public void draw(Graphics2D g2) {
		//BufferedImage image = getImage();
		int screenX = gp.tileSize*4;
		int screenY = gp.tileSize*2;

		g2.drawImage(image, screenX, screenY, gp.tileSize*2, gp.tileSize*2, null);

		//Health Bar
		drawHealth(g2, screenX, screenY);
		//Stamina  Bar
		drawStamina(g2, screenX, screenY);
	}
	
	private void drawHealth(Graphics2D g2, int screenX, int screenY) {
		double oneScale = (double)gp.tileSize/maxHealth;
		double healthValue = oneScale * health;
		g2.setColor(new Color(35,35,35));
		g2.fillRect(screenX - 2, screenY - 16, gp.tileSize+2, 12);
		g2.setColor(new Color(255,0,0));
		g2.fillRect(screenX, screenY - 14, (int)healthValue, 10);
	}
	
	private void drawStamina(Graphics2D g2, int screenX, int screenY) {
		double oneScale = (double)gp.tileSize/maxStamina;
		double staminaValue = oneScale * stamina;
		g2.setColor(new Color(35,35,35));
		g2.fillRect(screenX - 2, screenY - 2, gp.tileSize+2, 12);
		g2.setColor(new Color(255,255,0));
		g2.fillRect(screenX, screenY, (int)staminaValue, 10);
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
	
	public int compareTo(Playable that) {
		return Integer.compare(that.getSpeed(), this.getSpeed());
	}
	
	public int compare(Playable p1, Playable p2) {
		if (p1.getSpeed() < p2.getSpeed()) return 1;
		if (p1.getSpeed() > p2.getSpeed()) return -1;
		return 0;
	}
}