package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Alder_Window extends Entity {
	GamePanel gp;
	public static final int objId = 18;
	public static final String objName = "Window";
	public Obj_Alder_Window(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = false;
	
		image = setup("/res/objects/img_window_2", gp.tileSize, gp.tileSize);

		setDialogue();
		getImage(image);
		solidArea.y = 56;
		solidAreaDefaultY = solidArea.y;
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"Through the small, curtainless window, Alder could see sunlight breaching the branches of the Fuller Woods near the edge of the clearing.", 1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}
