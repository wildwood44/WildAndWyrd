package wildwyrd.game.items;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Secondary extends Weapon {

	public Secondary(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = EntityType.Secondary;
	}

	public WeaponType getWpType() {
		return wpType;
	}

	public void use() {
		gp.playSE(17);
		gp.playable.get(0).setWeapon_second(this);
		gp.player.removeFromInventory(this);
	}

}
