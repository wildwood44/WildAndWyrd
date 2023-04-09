package wildwyrd.game.object;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_StoneDoor extends Entity {
	GamePanel gp;

	public Obj_StoneDoor(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Door";
		type = 3;
		collision = true;

		image = setup("/res/objects/Rockwall_Door1", gp.tileSize, gp.tileSize);
		image2 = setup("/res/objects/Rockwall_Door4", gp.tileSize, gp.tileSize);
		timer = new Timer(20, (ActionListener) this);
		solidArea.height = 60;
		solidArea.y = 5;
		solidAreaDefaultY = solidArea.y;
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("The hearth was designed for cooking.", 1);
	}

	public void interact() {
		//startDialogue(this, 0);
		timer.start();
		gp.player.worldY -= 48;
		gp.eHandler.checkEvent();
		gp.keyH.enterPressed = false;
	}
	
	public void fade(ActionEvent e) {
		//this.draw(image);
	}
}