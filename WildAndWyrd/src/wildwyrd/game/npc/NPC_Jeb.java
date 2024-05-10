package wildwyrd.game.npc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.items.Item;
import wildwyrd.game.items.Itm_Bandage;
import wildwyrd.game.items.Itm_Dried_Apple_Slice;
import wildwyrd.game.items.Itm_Hazelnut;
import wildwyrd.game.items.Itm_P_Mushroom;
import wildwyrd.game.items.Itm_Travelling_Cloak;
import wildwyrd.game.object.Dialoge;

public class NPC_Jeb extends NPC {
	public static final int npcId = 6;
	public static final String npcName = "Jeb";
	public NPC_Jeb(GamePanel gp)  {
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
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("Jeb","Care to buy something?" ,port.image_Jeb);
		dialogues[1][0] = new Dialoge("Jeb","Come again.",port.image_Jeb);
		dialogues[2][0] = new Dialoge("Jeb","You don't have what i want for that.",port.image_Jeb);
		dialogues[3][0] = new Dialoge("Jeb","You don't have enouth space in your bag.",port.image_Jeb);
		dialogues[4][0] = new Dialoge("Jeb","I just sold you that!",port.image_Jeb);
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
	
	public Item buy(Item buy) {
		switch(buy.name){
		case Itm_Bandage.itemName: return new Itm_P_Mushroom(gp);
		case Itm_Dried_Apple_Slice.itemName: return new Itm_Hazelnut(gp);
		default: return null;
		}
	}
	
	public Item sell(Item sell) {
		switch(sell.name){
		case Itm_Hazelnut.itemName: return inventory.get(findItemInInventory(new Itm_Dried_Apple_Slice(gp)));
		case Itm_P_Mushroom.itemName: return inventory.get(findItemInInventory(new Itm_Bandage(gp)));
		default: return null;
		}
	}
	
	public void speak() {
		facePlayer();
		gp.keyH.enterPressed = false;
		gp.gameState = GameState.tradeState;
		gp.ui.npc = this;
	}
}
