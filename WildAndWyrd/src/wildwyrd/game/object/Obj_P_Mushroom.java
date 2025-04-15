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
		type = EntityType.PickUp;
		collision = true;

		image = setup("/res/items/Parasol_Mushroom", gp.tileSize, gp.tileSize);
		solidArea.x = 20;
		solidAreaDefaultX = solidArea.x;
		getImage(image);
		//setDialogue();
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

	public void interact() {
		gp.glossary.unlock("plants", "parasol mushroom");
		gp.ui.choiceSlot = 0;
		startDialogue(this, 0);
		destroy = true;
		gp.player.pickUpObject(loot);
		gp.keyH.enterPressed = false;
	}
}