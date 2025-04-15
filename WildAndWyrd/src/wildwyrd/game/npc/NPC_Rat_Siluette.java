package wildwyrd.game.npc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class NPC_Rat_Siluette extends NPC {
	public static final int npcId = 9;
	public static final String npcName = "Gowl_Rat";
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
		up1 = getPlayerImage(4, 3);
		up2 = getPlayerImage(3, 3);
		up3 = getPlayerImage(5, 3);
		down1 = getPlayerImage(4, 0);
		down2 = getPlayerImage(3, 0);
		down3 = getPlayerImage(5, 0);
		left1 = getPlayerImage(4, 1);
		left2 = getPlayerImage(3, 1);
		left3 = getPlayerImage(5, 1);
		right1 = getPlayerImage(4, 2);
		right2 = getPlayerImage(3, 2);
		right3 = getPlayerImage(5, 2);
	}
	
	public void setAction() {
		
	}

	public void setDialogue() {
	}
	
	public void speak() {
	}
}
