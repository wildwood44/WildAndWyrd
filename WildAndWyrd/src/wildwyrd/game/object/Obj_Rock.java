package wildwyrd.game.object;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.tile.UtilityTool;

public class Obj_Rock extends Entity {
	GamePanel gp;
	Entity loot;
	boolean opened = false;

	public Obj_Rock(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Tree";
		type = 3;
		collision = true;
		image = setup("/res/objects/img_rock", gp.tileSize, gp.tileSize);

		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("Various trees made up the woodland, the :majority were birch, rowen and holly.", 1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}