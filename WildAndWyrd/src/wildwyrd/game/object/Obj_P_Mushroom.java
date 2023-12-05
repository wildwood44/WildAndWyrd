package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Item;

public class Obj_P_Mushroom extends Entity {
	GamePanel gp;
	public static final int objId = 20;
	public static final String objName = "Parasol Mushroom";

	public Obj_P_Mushroom(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;

		image = setup("/res/items/Parasol_Mushroom", gp.tileSize, gp.tileSize);
		solidArea.x = 20;
		solidAreaDefaultX = solidArea.x;
		getImage(image);
	}
	
	public void setLoot(Item loot) {
		this.loot = loot;
		setDialogue();	
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"There were a variety of late-summer mushrooms growing around the area.",
				1);
		dialogues[0][1] = new Dialoge("Pick " + loot.name, 2);
		dialogues[1][0] = new Dialoge(
				"There were a variety of late-summer mushrooms growing around the area.",
				1);
	}

	public void choiceResponce() {
		if (gp.ui.choiceSlot == 0) {
			gp.player.pickUpObject(loot);
			opened = true;
			for (int i = 0; i < gp.obj[gp.currentMap.getId()].length; ++i) {
				if(gp.obj[gp.currentMap.getId()][i].id == objId) {
					gp.obj[gp.currentMap.getId()][i] = null;
					break;
				}
			}
		}
	}

	public void interact() {
		gp.ui.choiceSlot = 0;
		if (!opened) {
			startDialogue(this, 0);
		} else {
			startDialogue(this, 1);
		}
		gp.keyH.enterPressed = false;
	}
}