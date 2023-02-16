package wildwyrd.game.items;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Itm_Hazelnut extends Entity {
	GamePanel gp;

	public Itm_Hazelnut(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = "Hazelnut";
		this.description = "Seed of a hazel tree.";

		try {
			this.image = ImageIO.read(this.getClass().getResourceAsStream("/res/items/img_hazelnut.png"));
		} catch (IOException var3) {
			var3.printStackTrace();
		}

	}

	public void use() {
	}
}