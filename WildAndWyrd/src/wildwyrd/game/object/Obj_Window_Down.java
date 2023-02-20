package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Window_Down extends Entity {
	GamePanel gp;
	public Obj_Window_Down(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = "Window";
		this.type = 3;
		this.collision = false;
	
		try {
			this.image = ImageIO.read(this.getClass().getResourceAsStream("/res/objects/img_window_down.png"));
		} catch (IOException var3) {
			var3.printStackTrace();
		}
	}

	public void setDialogue() {
		this.dialogues[0][0] = new Dialoge(
				"There were four windows showing the clearing on either side of the cottage.", 1);
	}

	public void interact() {
		this.startDialogue(this, 0);
		this.gp.keyH.enterPressed = false;
	}
}
