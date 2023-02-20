package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Table_Right extends Entity {
	GamePanel gp;

	public Obj_Table_Right(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = "Tables";
		this.type = 3;
		this.collision = true;

		try {
			this.image = ImageIO.read(this.getClass().getResourceAsStream("/res/objects/Table_Tile_Right.png"));
		} catch (IOException var3) {
			var3.printStackTrace();
		}
		this.solidArea.width = 40;
		this.setDialogue();
	}

	public void setDialogue() {
		this.dialogues[0][0] = new Dialoge(
				"A rectangular wooden table stood in the :centre of the room while a thinner one with :a basin stood by the window.",
				1);
	}

	public void interact() {
		this.startDialogue(this, 0);
		this.gp.keyH.enterPressed = false;
	}
}