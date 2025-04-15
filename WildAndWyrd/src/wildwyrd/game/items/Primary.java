package wildwyrd.game.items;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Primary extends Weapon {

	public Primary(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = EntityType.Primary;
	}

	public WeaponType getWpType() {
		return wpType;
	}

	public void use() {
		gp.playSE(24);
		gp.playable.get(0).setWeapon_prime(this);
		gp.player.removeFromInventory(this);
	}

}
