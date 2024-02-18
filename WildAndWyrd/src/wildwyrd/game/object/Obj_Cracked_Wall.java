package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Cracked_Wall extends Entity {
	GamePanel gp;
	public static final int objId = 27;
	public static final String objName = "Wall(Damaged)";

	public Obj_Cracked_Wall(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;
		//solidAreaDefaultX = solidArea.x;
		//solidAreaDefaultY = solidArea.y;
		//worldX = gp.tileSize * col;
		//worldY = gp.tileSize * row;
		image = setup("/res/objects/medieval_wall-damaged", gp.tileSize, gp.tileSize);
		setDialogue();
		getImage(image);
	}
	
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		return isCorrectItem;
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("The wall was cracked.",1);
		dialogues[1][0] = new Dialoge("Alder hacked through the cracked daub and broke apart a hole into the shed.",1);
		dialogues[1][1] = new Dialoge("There were coins in the wall!",1);
	}
	
	public void interact() {
		if(gp.objective.quests[2].isAccepted()) {
			destroy = true;
			for(int i = 0; i < gp.obj[gp.currentMap.getId()].length; i++) {
				if(gp.obj[gp.currentMap.getId()][i] != null &&
						gp.obj[gp.currentMap.getId()][i].name == name && destroy) {
					gp.obj[gp.currentMap.getId()][i] = null;
					gp.player.pickUpShillings(3);
					break;
				}
			}
			startDialogue(this, 1);
		} else {
			startDialogue(this, 0);
		}
		gp.keyH.enterPressed = false;
	}
}
