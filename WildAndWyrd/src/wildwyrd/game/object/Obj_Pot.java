package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Item;

public class Obj_Pot extends Entity {
	GamePanel gp;
	public static final int objId = 16;
	public static final String objName = "Clay Pots";

	public Obj_Pot(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;
		image = setup("/res/objects/img_clay_pot_four", gp.tileSize, gp.tileSize);
		getImage(image);
	}
	
	public void setLoot(Item loot) {
		this.loot = loot;
		setDialogue();	
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("The pots contained potion ingredients, left in the shed to save space. One pot was completely filled with eyeballs.", 1);
		dialogues[0][1] = new Dialoge("There was something odd in one of them.", 2);
		dialogues[1][0] = new Dialoge("The pots contained potion ingredients, left in the shed to save space. One pot was completely filled with eyeballs.", 1);
		dialogues[2][0] = new Dialoge("Alder bond the lids tightly and placed each of the pots into the sack.",1);
		dialogues[2][1] = new Dialoge("Thee bag barely gained any weight.",1);
	}

	public void choiceResponce() {
		if (gp.ui.choiceSlot == 0) {
			gp.player.inventory.add(loot);
			opened = true;
		}

	}

	public void interact() {
		if(gp.objective.quests[2].isAccepted()) {
			startDialogue(this, 2);
			destroy = true;
			for(int i = 0; i < gp.obj[gp.currentMap.getId()].length; i++) {
				if(gp.obj[gp.currentMap.getId()][i] != null &&
						gp.obj[gp.currentMap.getId()][i].name == name && destroy) {
					gp.obj[gp.currentMap.getId()][i] = null;
					break;
				}
			}
			gp.objective.quests[2].progress(1);
		} else if(loot != null) {
			if (!opened) {
				startDialogue(this, 0);
			} else {
				startDialogue(this, 1);
			}
		} else {
			startDialogue(this, 1);
		}
		

		gp.keyH.enterPressed = false;
	}
}