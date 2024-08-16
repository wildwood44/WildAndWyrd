package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Alder_Bed extends Entity {
	GamePanel gp;
	public static final int objId = 19;
	public static final String objName = "Alder's Bed";

	public Obj_Alder_Bed(GamePanel gp) {
		//Change to a box bed
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;
		solidArea.y = 40;
		solidAreaDefaultY = solidArea.y;
		image = setup("/res/objects/Img_Box_Bed-1",gp.tileSize,gp.tileSize*2);
		shill = 2;
		getImage(image);
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("The box bed contained partridge feathers. It was powdered with herbs to stop it smelling. Thay and another guest helped Alder make it.", 1);
		dialogues[1][0] = new Dialoge("Among the feathers were Alder's savings.",1);
		dialogues[1][1] = new Dialoge("Take them? ", 2);
		dialogues[2][0] = new Dialoge("Alder got into his crampt bed and drifted to sleep for the night.",1);
	}

	public void choiceResponce() {
		if (gp.ui.choiceSlot == 0) {
			gp.player.pickUpShillings(shill);
			opened = true;
		}

	}

	public void interact() {
		image = setup("/res/objects/Img_Box_Bed-2",gp.tileSize,gp.tileSize*2);
		gp.glossary.unlock("constructs", "box-bed");
		getImage(image);
		if(!gp.s.c1Switch[4] && gp.s.chapter == 1) {
			gp.player.restoreHealthAndStamina();
			startDialogue(this, 2);
			gp.s.swh[5] = true;
			gp.s.chapter = 2;
			gp.s.part = 1;
		} else {
			startDialogue(this, 0);
			if (!opened) {
				startDialogue(this, 1);
			}
		}
		gp.keyH.enterPressed = false;
	}
}