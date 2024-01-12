package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Basin extends Entity {
	GamePanel gp;
	public static final int objId = 10;
	public static final String objName = "Basin";

	public Obj_Basin(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/Table_Tile_Basin", gp.tileSize, gp.tileSize);

		setDialogue();
		getImage(image);
		solidArea.y = 20;
		solidArea.height = 30;
		solidAreaDefaultY = solidArea.y;
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"The basin Alder was washing in was full of water from Alder cleaning dishes. Clean plates were lined up to the side.",
				1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}