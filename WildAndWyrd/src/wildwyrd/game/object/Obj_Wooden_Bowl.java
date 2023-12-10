package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.items.Item;

public class Obj_Wooden_Bowl extends Entity {
	GamePanel gp;
	public static final int objId = 0;
	public static final String objName = "Wooden Bowl";

	public Obj_Wooden_Bowl(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = objName;
		id  = objId;
		type = EntityType.Object;
		collision = true;
		options = new String[2];
		solidArea.y = 20;
		solidArea.height = 30;
		solidAreaDefaultY = solidArea.y;

		image = setup("/res/objects/Table_Tile_Bowl", gp.tileSize, gp.tileSize);
		getImage(image);
		//setDialogue();	
		//setLoot();
	}
	
	public void setLoot(Item loot) {
		this.loot = loot;
		setDialogue();	
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("A wooden bowl.", 1);
		dialogues[0][1] = new Dialoge("It has hazelnuts in it.", 1);
		dialogues[0][2] = new Dialoge("Pick up " + loot.name, 2);
		dialogues[1][0] = new Dialoge("A wooden bowl.", 1);
		dialogues[1][1] = new Dialoge("It's empty.", 1);
	}

	public void choiceResponce() {
		if (gp.ui.choiceSlot == 0) {
			gp.player.pickUpObject(loot);
			opened = true;
		}

	}

	public void interact() {
		if (!opened) {
			startDialogue(this, 0);
		} else {
			startDialogue(this, 1);
		}
		gp.keyH.enterPressed = false;
	}
}