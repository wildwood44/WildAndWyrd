package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Hearth extends Entity {
	GamePanel gp;
	public static final int objId = 6;
	public static final String objName = "Hearth";

	public Obj_Hearth(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;
		image = setup("/res/objects/img_hearth",gp.tileSize*3,gp.tileSize);

		solidArea.height = 60;
		solidArea.width = 192;
		solidArea.y = 5;
		solidAreaDefaultY = solidArea.y;
		setDialogue();
		getImage(image);
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("The hearth warmed the cottage and cooked food.", 1);
		dialogues[1][0] = new Dialoge("Alder got to work cleaning the cauldron.", 1);
		dialogues[1][2] = new Dialoge("Make sure it shines.", 1); 
		dialogues[1][3] = new Dialoge("I don't want any grime in my next meal.", 1);
		dialogues[2][0] = new Dialoge("A small metal cauldron was suspended above the hearth by a chain. It was empty.", 1);
	}

	public void interact() {
		gp.glossary.unlock("constructs", "hearth");
		if(gp.objective.quests[1].isAccepted() && !gp.objective.quests[1].require[0]) {
			startDialogue(this, 1);
			gp.objective.quests[1].progress(0);
		} else {
			//if(gp.player.worldX)
			startDialogue(this, 0);
		}
		gp.keyH.enterPressed = false;
	}
}