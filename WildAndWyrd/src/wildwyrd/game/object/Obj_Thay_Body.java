package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.npc.NPC;
import wildwyrd.game.npc.NPC_Thay;

public class Obj_Thay_Body extends Entity {
	GamePanel gp;
	//public static final int objId = 15;
	public static final String objName = "Thay's Body";

	public Obj_Thay_Body(GamePanel gp) {
		super(gp);
		this.gp = gp;
		//id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;
		NPC sourse = new NPC_Thay(gp);
		image = sourse.down1;

		setDialogue();
		getImage(image);
		solidArea.width = 60;
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("It's Thay.",1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}