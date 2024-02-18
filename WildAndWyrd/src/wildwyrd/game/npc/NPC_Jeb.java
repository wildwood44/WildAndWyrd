package wildwyrd.game.npc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.items.Itm_Bandage;
import wildwyrd.game.items.Itm_Dried_Apple_Slice;
import wildwyrd.game.items.Itm_Travelling_Cloak;
import wildwyrd.game.object.Dialoge;

public class NPC_Jeb extends NPC {
	public static final int npcId = 6;
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
		setItems();
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
		dialogues[0][0] = new Dialoge("Jeb","Care to buy something?" ,1);
		dialogues[1][0] = new Dialoge("Jeb","Come again.",1);
		dialogues[2][0] = new Dialoge("You need a better barter than that.",1);
		dialogues[2][0] = new Dialoge("You don't have enouth space in your bag.",1);
	}

	public void setDialogue() {
	}
	
	public void checkConditions() {
		if(dialogueSet == 0) {
			
		}
	}
	
	public void setItems() {
		inventory.add(new Itm_Bandage(gp));
		inventory.add(new Itm_Dried_Apple_Slice(gp));
		inventory.add(new Itm_Travelling_Cloak(gp));
	}
	
	public void speak() {
		facePlayer();
		gp.keyH.enterPressed = false;
		gp.gameState = GameState.tradeState;
		gp.ui.npc = this;
	}
}
