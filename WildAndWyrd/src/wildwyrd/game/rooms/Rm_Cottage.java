package wildwyrd.game.rooms;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Item;
import wildwyrd.game.object.Dialoge;

public class Rm_Cottage extends Room {
	GamePanel gp;
	Graphics2D g2;

	public Rm_Cottage(GamePanel gp) {
		super(gp);
		this.gp = gp;
		roomId = 3;
		room_width = gp.screenWidth;
		room_height = gp.screenHeight;
		props = new Prop[1];
		actors = new Prop[1];
		props[0] = new Prop(this.gp, screenX, screenY, room_width, room_height);
		props[0].setImage("/res/backgrounds/animated/Hall_of_the_Scion_eyes");
		actors[0] = new Prop(this.gp, 300, 0, 200, 400);
		actors[0].setImage("/res/character/chr_Agrimus");
		alpha = 0;
		setDialogue();
	}
	
	public void setLoot(Item loot) {
		this.loot = loot;
		setDialogue();	
	}

	public void getBackgroundImage() {
		image = setup("/res/backgrounds/bg_cottage", room_width, room_height);
		g2.drawImage(image, screenX, screenY, gp);
		//g2.setComposite(AlphaComposite.SrcOver.derive(alpha));

	}

	public void drawObjects() {
		image = setup("/res/backgrounds/animated/Hall_of_the_Scion_eyes", room_width, room_height);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.drawImage(image, screenX, screenY, gp);
	}
	
	public void drawActor(String file, int x, int y, float alpha) {
		image = setup("/res/character/chr_Agrimus", 200, 400);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.drawImage(image, x, y, gp);
	}

	public void choiceResponce() {
		if (gp.ui.choiceSlot == 0) {
			gp.csManager.scenePhase++;
		} else if (gp.ui.choiceSlot == 1) {
			startDialogue(this, 1);
		}

	}
	
	public void setDialogueOptions() {
		options[0] = "Take sword?";
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("Take sword" ,2);
		dialogues[1][0] = new Dialoge("The mouse insisted." ,1);
		dialogues[1][1] = new Dialoge("Take sword" ,2);
	}
	
	public void interact() {
		if(gp.s.chapter == 2) {
			startDialogue(this, 0);
		}
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		getBackgroundImage();
		//props[0].draw(g2);
		//actors[0].draw(g2);
	}
}