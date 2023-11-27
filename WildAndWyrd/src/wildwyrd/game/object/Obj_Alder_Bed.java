package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Alder_Bed extends Entity {
	GamePanel gp;
	public static final int objId = 19;
	public static final String objName = "Alder's Bed";

	public Obj_Alder_Bed(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;

		solidArea.x = 10;
		solidAreaDefaultX = solidArea.x;
		solidArea.y = 40;
		solidAreaDefaultY = solidArea.y;
		image = setup("/res/objects/Img_Alder_Bed",gp.tileSize,gp.tileSize);
		getImage(image);
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("It was cheaply made from dry wood and partridge feathers. It was powdered with herbs to stop it smelling. Thay helped Alder make it.", 1);
		dialogues[1][0] = new Dialoge("Among the feathers were Alder's savings.",1);
		dialogues[1][1] = new Dialoge("Take them? ", 2);
		dialogues[2][0] = new Dialoge("Alder got into his makeshift bed and drifted to sleep for the night.",1);
	}

	public void choiceResponce() {
		if (gp.ui.choiceSlot == 0) {
			gp.player.pickUpShillings(shill);
			opened = true;
		}

	}

	public void interact() {
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