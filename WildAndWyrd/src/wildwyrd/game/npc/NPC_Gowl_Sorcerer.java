package wildwyrd.game.npc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class NPC_Gowl_Sorcerer extends NPC {
	public static final int npcId = 8;
	public static final String npcName = "Gowl Sorcerer";
	public NPC_Gowl_Sorcerer(GamePanel gp) {
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
			sprite = ImageIO.read(getClass().getResourceAsStream("/res/sprite/GowlSprites.png"));
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
		up1 = getPlayerImage(4, 7);
		up2 = getPlayerImage(3, 7);
		up3 = getPlayerImage(5, 7);
		down1 = getPlayerImage(4, 4);
		down2 = getPlayerImage(3, 4);
		down3 = getPlayerImage(5, 4);
		left1 = getPlayerImage(4, 5);
		left2 = getPlayerImage(3, 5);
		left3 = getPlayerImage(5, 5);
		right1 = getPlayerImage(4, 6);
		right2 = getPlayerImage(3, 6);
		right3 = getPlayerImage(5, 6);
	}
	
	public void setAction() {
		
	}

	public void setDialogue() {
	}
	
	public void speak() {
	}
}
