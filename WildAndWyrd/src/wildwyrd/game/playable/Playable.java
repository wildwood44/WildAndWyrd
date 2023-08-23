package wildwyrd.game.playable;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.combat.CombatStatus;
import wildwyrd.game.items.Weapon;
import wildwyrd.game.items.WeaponType;

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
	protected boolean inRear = false;
	private Entity head = new Entity(gp);
	private Entity body = new Entity(gp);
	private Entity legs = new Entity(gp);
	private Weapon weapon_prime = new Weapon(gp);
	private Weapon weapon_second = new Weapon(gp);
	private Weapon loadedProjectile = null;
	private CombatStatus combatStatus = CombatStatus.Normal;
	protected int spriteCounter = 0;
	protected int actionLockCounter = 0;
	protected int invincibleCounter = 0;
	protected int dyingCounter = 0;
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
	public Weapon getWeapon_prime() {
		if (weapon_prime.name == null) {
			return null;
		}
		return weapon_prime;
	}
	public void setWeapon_prime(Weapon weapon_prime) {
		this.weapon_prime = weapon_prime;
	}
	public Weapon getWeapon_second() {
		return weapon_second;
	}
	public void setWeapon_second(Weapon weapon_second) {
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

	public boolean inRange() {
		return !inRear;
	}
	public void changePos() {
		if(!inRear) {
			inRear = true;
		} else {
			inRear = false;
		}
	}
	public void advance(Playable target) {
		if(!target.inRange()) {
			target.changePos();
		}
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
		if (inRear) {
			screenX -= (gp.tileSize*2);
		}
		if(projectileLoaded()) {
			g2.drawImage(weapon_second.combat_image, screenX, screenY, gp.tileSize*2, gp.tileSize*2, null);
		} else {
			g2.drawImage(weapon_prime.combat_image, screenX, screenY, gp.tileSize*2, gp.tileSize*2, null);
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize*2, gp.tileSize*2, null);

		if(dying) {
			dyingAnimation(g2);
		}
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
	
	public void changeAlpha(Graphics2D g2, float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}
	
	public void dyingAnimation(Graphics2D g2) {
		dyingCounter++;
		int i = 5;
		if(dyingCounter <= i) {changeAlpha(g2,0f);}
		if(dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2,1f);}
		if(dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2,0f);}
		if(dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2,1f);}
		if(dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2,0f);}
		if(dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2,1f);}
		if(dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2,0f);}
		if(dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2,1f);}
		if(dyingCounter > i*8) {
			dying = false;
			alive = false;
		}
	}
	
	public void loadProjectile(Weapon projectile) {
		if((weapon_second.getWpType() == WeaponType.Bow && projectile.getWpType() == WeaponType.Arrow)||
			(weapon_second.getWpType() == WeaponType.Crossbow && projectile.getWpType() == WeaponType.Bolt)||
			(weapon_second.getWpType() == WeaponType.Sling && projectile.getWpType() == WeaponType.Stone)) {
			loadedProjectile = projectile;
		} else {
			
		}
	}
	
	public boolean projectileLoaded() {
		if(loadedProjectile != null) {
			return true;
		}
		return false;
	}
	
	public int fireProjectile() {
		if(loadedProjectile != null) {
			int totalDamage = getWeapon_second().attackValue + loadedProjectile.attackValue;
			loadedProjectile = null;
			return totalDamage;
		}
		return 0;
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
	
	public int compareTo(Playable that) {
		return Integer.compare(that.getSpeed(), this.getSpeed());
	}
	
	public int compare(Playable p1, Playable p2) {
		if (p1.getSpeed() < p2.getSpeed()) return 1;
		if (p1.getSpeed() > p2.getSpeed()) return -1;
		return 0;
	}
}