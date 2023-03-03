package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Alder_Bed extends Entity {
	GamePanel gp;

	public Obj_Alder_Bed(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = "Alder's bed";
		this.type = 3;
		this.collision = true;

		try {
			this.image = ImageIO.read(this.getClass().getResourceAsStream("/res/objects/Img_Alder_Bed.png"));
		} catch (IOException var3) {
			var3.printStackTrace();
		}
		this.setDialogue();
	}

	public void setDialogue() {
		this.dialogues[0][0] = new Dialoge("It was cheaply made from dry wood and partridge feathers. It was powdered with herbs to stop it smelling. Thay helped Alder make it.", 1);
	}

	public void interact() {
		this.startDialogue(this, 0);
		this.gp.keyH.enterPressed = false;
	}
}