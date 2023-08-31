package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Cauldron extends Entity {
	GamePanel gp;
	public static final int objId = 5;
	public static final String objName = "Cauldron";

	public Obj_Cauldron(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = objName;
		id  = objId;
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/img_hearth_left", gp.tileSize, gp.tileSize);

		solidArea.height = 60;
		solidArea.y = 5;
		solidAreaDefaultY = this.solidArea.y;
		setDialogue();
		getImage(image);
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"A small metal cauldron was suspended above the hearth by a chain. It was empty.", 1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}