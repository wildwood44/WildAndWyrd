package wildwyrd.game.items;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Itm_P_Mushroom extends Entity {
	GamePanel gp;

	public Itm_P_Mushroom(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Parasol Mushroom";
		description = "";

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/items/Parasol_Mushroom.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void use() {
	}
}