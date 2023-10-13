package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.npc.NPC_Florence;

public class Obj_Pots extends Entity {
	GamePanel gp;
	public static final int objId = 4;
	public static final String objName = "Pots";

	public Obj_Pots(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/img_pots", gp.tileSize, gp.tileSize);

		solidArea.height = 60;
		solidArea.y = 5;
		solidAreaDefaultY = solidArea.y;
		setDialogue();
		getImage(image);
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("Various pots and pans were hung on hooks on the wall.", 1);
		dialogues[1][0] = new Dialoge("Alder got a wet cloth and started scrubbing the pots. Florence peeked into the living room at Kyla and started on the others", 1);
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