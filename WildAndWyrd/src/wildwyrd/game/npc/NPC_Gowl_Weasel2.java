package wildwyrd.game.npc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class NPC_Gowl_Weasel2 extends NPC {
	public static final int npcId = 7;
	public static final String npcName = "Gowl Weasel 2";
	public NPC_Gowl_Weasel2(GamePanel gp) {
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
		up1 = getPlayerImage(1, 3);
		up2 = getPlayerImage(0, 3);
		up3 = getPlayerImage(2, 3);
		down1 = getPlayerImage(1, 0);
		down2 = getPlayerImage(0, 0);
		down3 = getPlayerImage(2, 0);
		left1 = getPlayerImage(1, 1);
		left2 = getPlayerImage(0, 1);
		left3 = getPlayerImage(2, 1);
		right1 = getPlayerImage(1, 2);
		right2 = getPlayerImage(0, 2);
		right3 = getPlayerImage(2, 2);
	}
	
	public void setAction() {
		
	}

	public void setDialogue() {
	}
	
	public void speak() {
	}
}
