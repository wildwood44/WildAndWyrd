package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Itm_Bramble_Leaf;

public class Obj_Worktable extends Entity {
	GamePanel gp;
	public static final int objId = 17;
	public static final String objName = "Worktable";

	public Obj_Worktable(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/img_worktable", gp.tileSize, gp.tileSize);

		setDialogue();
		getImage(image);
		solidArea.width = 60;
	}
	
	public void choiceResponce() {
		System.out.println(gp.ui.choiceSlot);
		if (gp.ui.choiceSlot == 0) {
			gp.player.inventory.add(loot);
			System.out.println(loot);
			opened = true;
		}

	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"On the table was a mortar and pestle.",1);
		dialogues[1][0] = new Dialoge(
				"In the draw of the worktable was a hunting knife.",1);
		dialogues[1][1] = new Dialoge("Take it?",2);
		dialogues[2][0] = new Dialoge("Alder ground the leaves with the pestle until they were powder. He then put them in a nearby pot containing remnants of the same powder.",1);
	}

	public void interact() {
		gp.ui.choiceSlot = 0;
		if (!opened && !gp.s.c1Switch[2]) {
			startDialogue(this, 1);
		} else if (gp.player.itemIsInInventory(Itm_Bramble_Leaf.itemId)) {
			gp.glossary.unlock("constructs", "mortar and pestle");
			Entity selectedItem = gp.player.inventory.get(gp.player.searchItemInInventory(Itm_Bramble_Leaf.itemId));
			gp.player.removeFromInventory(selectedItem);
			gp.objective.quests[1].progress(2);
			startDialogue(this, 2);
		} else {
			startDialogue(this, 0);
		}
		gp.keyH.enterPressed = false;
	}
}