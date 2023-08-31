package wildwyrd.game.object;

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
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/img_greater_plantain",gp.tileSize,gp.tileSize);
		setDialogue();
		getImage(image);
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"Various weeds, wildflowers and moss.",
				1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
		
	}
}