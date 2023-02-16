package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Basin extends Entity {
	GamePanel gp;

	public Obj_Basin(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = "Basin";
		this.type = 3;
		this.collision = true;

		try {
			this.image = ImageIO.read(this.getClass().getResourceAsStream("/res/objects/img_basin.png"));
		} catch (IOException var3) {
			var3.printStackTrace();
		}

		this.setDialogue();
		this.solidArea.x = 15;
		this.solidArea.y = 40;
		this.solidArea.height = 50;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
	}

	public void setDialogue() {
		this.dialogues[0][0] = new Dialoge(
				"The basin Alder was washing in was full of :water from Alder cleaning dishes. Clean :plates were lined up to the side.",
				1);
	}

	public void interact() {
		this.startDialogue(this, 0);
		this.gp.keyH.enterPressed = false;
	}
}