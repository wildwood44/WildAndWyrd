package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Cupboard extends Entity {
	GamePanel gp;
	public static final int objId = 7;
	public static final String objName = "Cupboard";

	public Obj_Cupboard(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = objName;
		type = EntityType.Object;
		collision = true;

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/objects/img_cupboard.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setDialogue();
		solidArea.width = 20;
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("The cupboard was full of plates, bowls and :other kitchen and dining ware.",
				1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}