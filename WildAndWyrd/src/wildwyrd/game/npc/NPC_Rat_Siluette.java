package wildwyrd.game.npc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Itm_Bow;
import wildwyrd.game.items.Itm_Primative_Arrow;
import wildwyrd.game.object.Dialoge;

public class NPC_Rat_Siluette extends NPC {
	public static final int npcId = 6;
	public static final String npcName = "Gowl";
	public NPC_Rat_Siluette(GamePanel gp) {
		super(gp);
		//this.gp = gp;
		id = npcId;
		name = npcName;
		type = EntityType.Sprite;
		direction = "up";
		speed = 1;
		setDialogue();
		getImage();
	}

	public BufferedImage getSpriteSheet() {
		BufferedImage sprite = null;

		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/res/sprite/WildWyrdSprites.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sprite;
	}

	public BufferedImage getPlayerImage(int xGrid, int yGrid) {
		if (image != getSpriteSheet()) {
			image = getSpriteSheet();
		}
		return image.getSubimage(xGrid * gp.tileSize, yGrid * gp.tileSize, gp.tileSize, gp.tileSize);
	}
	
	public void getImage() {
		up1 = getPlayerImage(10, 7);
		up2 = getPlayerImage(9, 7);
		up3 = getPlayerImage(11, 7);
		down1 = getPlayerImage(10, 4);
		down2 = getPlayerImage(9, 4);
		down3 = getPlayerImage(11, 4);
		left1 = getPlayerImage(10, 5);
		left2 = getPlayerImage(9, 5);
		left3 = getPlayerImage(11, 5);
		right1 = getPlayerImage(10, 6);
		right2 = getPlayerImage(9, 6);
		right3 = getPlayerImage(11, 6);
	}
	
	public void setAction() {
		
	}

	public void setDialogue() {
	}
	
	public void speak() {
	}
}
