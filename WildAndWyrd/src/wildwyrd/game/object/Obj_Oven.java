package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.npc.NPC_Florence;

public class Obj_Oven extends Entity {
	GamePanel gp;
	public static final int objId = 3;
	public static final String objName = "Oven";

	public Obj_Oven(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/img_Oven", gp.tileSize, gp.tileSize * 2);
		solidArea.width = 42;
		solidArea.height = 60;
		solidArea.x = 10;
		solidArea.y = 5;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		setDialogue();
		getImage(image);
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("The oven was unlit.", 1);
		dialogues[1][0] = new Dialoge("Alder got a brush and started swepping the oven. Florence noticed him and started helping.", 1);
		dialogues[1][1] = new Dialoge("Florence","Shh.", 1);
	}

	public void interact() {
		if(gp.objective.quests[1].isAccepted()) {
			startDialogue(this, 1);
			for(int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] != null &&
						gp.npc[gp.currentMap.getId()][i].id == NPC_Florence.npcId) {
					gp.npc[gp.currentMap.getId()][i].direction = "up";
				}
			}
			gp.objective.quests[1].progress(1);
		} else {
			startDialogue(this, 0);
		}
		gp.keyH.enterPressed = false;
	}
}