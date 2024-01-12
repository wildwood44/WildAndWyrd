package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.cutscenes.Portrate;

public class Obj_Kitchen_Window extends Entity {
	GamePanel gp;
	public static final int objId = 9;
	public static final String objName = "Window";
	public Portrate port = new Portrate();

	public Obj_Kitchen_Window(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/Table_Tile_Window", gp.tileSize, gp.tileSize);

		setDialogue();
		getImage(image);
		solidArea.y = 20;
		solidArea.height = 30;
		solidAreaDefaultY = solidArea.y;
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"The clearing at the front of of the cottage could be seen through the window.", 1);
		dialogues[1][0] = new Dialoge("Florence and Thay were talking outside the window.", 1);
		dialogues[1][1] = new Dialoge("Alder","I'm coming out", port.image_Alder);
	}

	public void interact() {
		gp.gameState = GameState.examineState;
		if (gp.s.c1Switch[0]) {
			startDialogue(this, 1);
		} else {
			startDialogue(this, 0);
		}

		gp.keyH.enterPressed = false;
	}
}