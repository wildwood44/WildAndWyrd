package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Plant_1 extends Entity {
	GamePanel gp;
	public static final int objId = 22;
	public static final String objName = "Greater Plaintain";

	public Plant_1(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = objName;
		type = EntityType.Object;
		collision = true;

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/objects/img_greater_plantain.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"Various weeds, wildflowers and moss.",
				1);
	}

	public void interact() {
		startDialogue(this, 0);
		this.gp.keyH.enterPressed = false;
		
	}
}