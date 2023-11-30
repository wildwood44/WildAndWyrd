package wildwyrd.game.items;

import java.awt.image.BufferedImage;
import wildwyrd.game.GamePanel;

public class Armour extends Item {
	public ArmourType armType;
	public BufferedImage combat_image;

	public Armour(GamePanel gp) {
		super(gp);
		this.gp = gp;
	}

	public ArmourType getArmType() {
		return armType;
	}

}
