package wildwyrd.game.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Bookshelf extends Entity {
	GamePanel gp;
	public Obj_Bookshelf(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = "Bookshelf";
		this.type = 3;
		this.collision = true;

		try {
			this.image = ImageIO.read(this.getClass().getResourceAsStream("/res/objects/img_bookshelf.png"));
		} catch (IOException var3) {
			var3.printStackTrace();
		}

		this.solidArea.height = 60;
		this.solidArea.y = 5;
		this.solidAreaDefaultY = this.solidArea.y;
		this.setDialogue();
	}

	public void setDialogue() {
		
	}

	public void interact() {
		this.startDialogue(this, 0);
		this.gp.keyH.enterPressed = false;
	}

	
}
