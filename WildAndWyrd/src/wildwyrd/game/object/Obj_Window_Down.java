package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Window_Down extends Entity {
	GamePanel gp;
	public static final int objId = 17;
	public static final String objName = "Window";
	public Obj_Window_Down(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = false;
	
		image = setup("/res/objects/img_window_down", gp.tileSize, gp.tileSize);
		setDialogue();
		getImage(image);
		solidArea.y = 64;
		solidAreaDefaultY = solidArea.y;
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"There were four windows showing the clearing on either side of the cottage.", 1);
	}

	public void interact() {
		if (!gp.s.c3Switch[3] && gp.s.c3Switch[4]) {
			gp.s.swh[13] = true;
		} else {
			startDialogue(this, 0);
		}
		gp.keyH.enterPressed = false;
	}
}
