package wildwyrd.game.items;

import java.awt.image.BufferedImage;
import wildwyrd.game.GamePanel;

public class Weapon extends Item {
	public WeaponType wpType;
	public BufferedImage combat_image;

	public Weapon(GamePanel gp) {
		super(gp);
		this.gp = gp;
	}

	public WeaponType getWpType() {
		return wpType;
	}

}
