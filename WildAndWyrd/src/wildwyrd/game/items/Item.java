package wildwyrd.game.items;

import java.awt.image.BufferedImage;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Item extends Entity {
	public int price;

	public Item(GamePanel gp) {
		super(gp);
		this.gp = gp;
		description = "";
		stackable = true;
	}
	public void selectedItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot();
	}
	
	public void use() {}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public BufferedImage transform() {return null;}
}
