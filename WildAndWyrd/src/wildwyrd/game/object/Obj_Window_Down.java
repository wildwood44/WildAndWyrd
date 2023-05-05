package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Window_Down extends Entity {
	GamePanel gp;
	public Obj_Window_Down(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Window";
		type = EntityType.Object;
		collision = false;
	
		image = setup("/res/objects/img_window_down", gp.tileSize, gp.tileSize);
		setDialogue();
		solidArea.y = 56;
		solidAreaDefaultY = solidArea.y;
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"There were four windows showing the clearing on either side of the cottage.", 1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}
