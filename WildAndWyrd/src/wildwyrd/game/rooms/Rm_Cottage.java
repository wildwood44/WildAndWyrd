package wildwyrd.game.rooms;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Item;

public class Rm_Cottage extends Room {
	GamePanel gp;
	Graphics2D g2;

	public Rm_Cottage(GamePanel gp) {
		super(gp);
		this.gp = gp;
		roomId = 3;
		room_width = gp.screenWidth;
		room_height = gp.screenHeight;
		//props = new Prop[1];
		actors = new Prop[1];
		//props[0] = new Prop(this.gp, screenX, screenY, room_width, room_height);
		//props[0].setImage("/res/backgrounds/animated/prop_Kyla1");
		actors[0] = new Prop(this.gp, 300, 0, gp.tileSize*4, gp.tileSize*4);
		actors[0].setImage("/res/backgrounds/animated/prop_Kyla1");
		actors[0].animation[0] = setup("/res/backgrounds/animated/prop_Kyla1", gp.tileSize*4, gp.tileSize*4);
		actors[0].animation[1] = setup("/res/backgrounds/animated/prop_Kyla2", gp.tileSize*4, gp.tileSize*4);
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
		image = setup("/res/backgrounds/animated/prop_Kyla1", room_width, room_height);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.drawImage(image, screenX, screenY, gp);
	}
	
	public void drawActor( int x, int y, float alpha) {
		//getBackgroundImage();
		image = actors[0].playAnimation(20);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.drawImage(image, x, y, gp);
	}
	
	public void setDialogueOptions() {
		
	}

	public void setDialogue() {
		
	}
	
	public void interact() {
		getBackgroundImage();
		if(gp.s.chapter == 2) {
			startDialogue(this, 0);
		}
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		getBackgroundImage();
		if(gp.s.chapter == 3) {
			//actors[0].playAnimation();
			//actors[0].draw(g2);
			drawActor(gp.screenWidth/3, 30, 1);
		}
	}
}