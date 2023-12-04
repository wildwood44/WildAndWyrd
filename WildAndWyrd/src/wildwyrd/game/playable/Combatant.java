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
import wildwyrd.game.skill.Skill;

public class Combatant extends Entity implements Comparable<Combatant> {
	protected String imageUrl;
	public int maxHealth;
	public int health = maxHealth;
	protected int stamina;
	protected int maxStamina;
	public int baseAttack;
	public int baseDefence;
	public int baseAccuracy;
	public int baseSpeed;
	public int baseEvasion;
	protected boolean alive = true;
	protected boolean dying = false;
	protected boolean inRear = false;
	protected Entity head = new Entity(gp);
	protected Entity body = new Entity(gp);
	protected Entity legs = new Entity(gp);
	protected Weapon weapon_prime = new Weapon(gp);
	protected Weapon weapon_second = new Weapon(gp);
	private Skill automatic = new Skill(gp);
	private Skill offencive = new Skill(gp);
	private Skill supportive = new Skill(gp);
	private Skill selfie = new Skill(gp);
	protected Weapon loadedProjectile = null;
	private CombatStatus combatStatus = CombatStatus.Normal;
	protected int spriteCounter = 0;
	protected int actionLockCounter = 0;
	protected int invincibleCounter = 0;
	protected int dyingCounter = 0;
	public Combatant(GamePanel gp, String name, int health, int stamina,
			int baseAttack, int baseDefence, int baseAccuracy, int baseEvasion, int baseSpeed) {
		super(gp);
		//this.level = 1;
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
		return weapon_prime;
	}
	public void setWeapon_prime(Weapon weapon_prime) {
		this.weapon_prime = weapon_prime;
	}
	public Weapon getWeapon_second() {
		return weapon_second;
	}
	public void setWeapon_second(Weapon weapon_second) {
		if(this.weapon_second.name != null) {
			gp.player.inventory.add(weapon_second);
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
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	public int getMaxStamina() {
		return maxStamina;
	}
	public void setMaxStamina(int maxStamina) {
		this.maxStamina = maxStamina;
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
	public void setHealthAndStamina(int health, int stamina) {
		this.health = health;
		this.stamina = stamina;
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
	public int getBaseAccuracy() {
		return baseAccuracy;
	}
	public int getAccuracy() {
		return baseAccuracy;
	}
	public int getBaseEvasion() {
		return baseEvasion;
	}
	public int getEvasion() {
		return baseEvasion;
	}
	public int getBaseSpeed() {
		return baseSpeed;
	}
	public int getSpeed() {
		return baseSpeed;
	}

	public Skill getAutomatic() {
		if(automatic.getName() != null) {
			return automatic;
		}
		return null;
	}
	public Skill getOffencive() {
		if(offencive.getName() != null) {
			return offencive;
		}
		return null;
	}
	public Skill getSupportive() {
		if(supportive.getName() != null) {
			return supportive;
		}
		return null;
	}
	public Skill getSelfie() {
		if(selfie.getName() != null) {
			return selfie;
		}
		return null;
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
	public void advance(Combatant target) {
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
	
	public int compareTo(Combatant that) {
		return Integer.compare(that.getSpeed(), this.getSpeed());
	}
	
	public int compare(Combatant p1, Combatant p2) {
		if (p1.getSpeed() < p2.getSpeed()) return 1;
		if (p1.getSpeed() > p2.getSpeed()) return -1;
		return 0;
	}
}