package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Larder extends Entity {
	GamePanel gp;
	public static final int objId = 8;
	public static final String objName = "Larder";

	public Obj_Larder(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/img_cupboard", gp.tileSize, gp.tileSize);

		setDialogue();
		getImage(image);
		solidArea.width = 20;
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"A small cool cupboard full of shelves and hooks to store food, mostly empty except for a loaf of bread.",
				1);
		dialogues[0][1] = new Dialoge(
				"Kyla’s familiar Tawie was out resupplying. Alder was not looking forward to her return.", 1);
		dialogues[1][0] = new Dialoge("There was only a single loaf of bread in the larder. It would have to do.",
				1);
		dialogues[2][0] = new Dialoge("The larder was empty.", 1);
	}

	public void interact() {
		gp.glossary.unlock("constructs", "larder");
		if(!gp.objective.quests[3].isAccepted()) {
			startDialogue(this, 0);
		} else if (!gp.objective.quests[3].isCompleted()) {
			startDialogue(this, 1);
			gp.player.pickUpObject(loot);
			gp.objective.quests[3].completeQuest();
		} else {
			startDialogue(this, 2);
		}
		gp.keyH.enterPressed = false;
	}
}