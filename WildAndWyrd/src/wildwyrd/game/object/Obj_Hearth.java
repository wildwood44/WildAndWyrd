package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Hearth extends Entity {
	GamePanel gp;
	public static final int objId = 6;
	public static final String objName = "Hearth";

	public Obj_Hearth(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = objName;
		type = EntityType.Object;
		collision = true;

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/objects/img_hearth_right.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		solidArea.height = 60;
		solidArea.y = 5;
		solidAreaDefaultY = solidArea.y;
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("The hearth was designed for cooking.", 1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}