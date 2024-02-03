package wildwyrd.game.npc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class NPC_Jeb extends NPC {
	public static final int npcId = 9;
	public static final String npcName = "Jeb";
	public NPC_Jeb(GamePanel gp) {
		super(gp);
		//this.gp = gp;
		id = npcId;
		name = npcName;
		type = EntityType.Sprite;
		direction = "down";
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
		up1 = getPlayerImage(7, 7);
		up2 = getPlayerImage(6, 7);
		up3 = getPlayerImage(8, 7);
		down1 = getPlayerImage(7, 4);
		down2 = getPlayerImage(6, 4);
		down3 = getPlayerImage(8, 4);
		left1 = getPlayerImage(7, 5);
		left2 = getPlayerImage(6, 5);
		left3 = getPlayerImage(8, 5);
		right1 = getPlayerImage(7, 6);
		right2 = getPlayerImage(6, 6);
		right3 = getPlayerImage(8, 6);
	}
	
	public void setAction() {
		
	}

	public void setDialogue() {
	}
	
	public void speak() {
	}
}
