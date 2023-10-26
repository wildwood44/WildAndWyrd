package wildwyrd.game.items;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Itm_Bandage extends Entity {
	GamePanel gp;

	public Itm_Bandage(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = "Bandage";
		this.description = "A cloth bandage to treat :wounds.";

		try {
			this.image = ImageIO.read(this.getClass().getResourceAsStream("/res/items/img_bandage.png"));
		} catch (IOException var3) {
			var3.printStackTrace();
		}

	}

	public void use() {
	}
}