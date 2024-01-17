package wildwyrd.game.npc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.object.Dialoge;

public class NPC_Florence extends NPC {
	public static final int npcId = 1;
	public static final String npcName = "Florence";
	public NPC_Florence(GamePanel gp) {
		super(gp);
		//this.gp = gp;
		options = new String[2];
		contConditions = new boolean[2];
		id = npcId;
		name = npcName;
		type = EntityType.Sprite;
		direction = "down";
		speed = 1;
		contConditions[0] = false;
		contConditions[1] = false;
		setDialogue();
		setDialogueOptions();
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
		if (image == null) {
			image = getSpriteSheet();
		}
		return image.getSubimage(xGrid * gp.tileSize, yGrid * gp.tileSize, gp.tileSize, gp.tileSize);
	}
	
	public void getImage() {
		up1 = getPlayerImage(7, 3);
		up2 = getPlayerImage(6, 3);
		up3 = getPlayerImage(8, 3);
		down1 = getPlayerImage(7, 0);
		down2 = getPlayerImage(6, 0);
		down3 = getPlayerImage(8, 0);
		left1 = getPlayerImage(7, 1);
		left2 = getPlayerImage(6, 1);
		left3 = getPlayerImage(8, 1);
		right1 = getPlayerImage(7, 2);
		right2 = getPlayerImage(6, 2);
		right3 = getPlayerImage(8, 2);
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(" " ,3);
		dialogues[1][0] = new Dialoge("Florence", "Potions probably.", port.image_Florence);
		dialogues[1][1] = new Dialoge("Florence", "He usually comes here for sanctuary or potions.", port.image_Florence);
		dialogues[2][0] = new Dialoge("Florence", "Well.", port.image_Florence);
		dialogues[2][1] = new Dialoge("Florence", "Kyla’s cast several illusions on the cottage, one of which makes it looks like a boulder from the outside.", port.image_Florence);
		dialogues[2][2] = new Dialoge("Florence", "She's also muted the rooms and made our scents smell somewhat grassy.", port.image_Florence);
		dialogues[2][3] = new Dialoge("Florence", "This place cannot be seen from the outside world, so we tend to call it the burrow.", port.image_Florence);
		dialogues[3][0] = new Dialoge("Florence", "The knife should be in the shed. You can go once you've got it." ,port.image_Florence);
		dialogues[4][0] = new Dialoge("Alder", "I found the knife!" ,port.image_Alder);
		dialogues[4][1] = new Dialoge("Florence", "Fantastic!" ,port.image_Florence);
		dialogues[4][2] = new Dialoge("Florence", "Just don't go too far." ,port.image_Florence);
		dialogues[5][0] = new Dialoge("Alder", "Florence! I'm done!" ,port.image_Alder);
		dialogues[5][1] = new Dialoge("Florence","Alder what happened? Are you ok!?" ,port.image_Florence);
		dialogues[5][2] = new Dialoge("Alder","Um..." ,port.image_Alder);
		dialogues[5][3] = new Dialoge("Alder","I got stung by wasps a few times but I'm alright!" ,port.image_Alder);
		dialogues[5][4] = new Dialoge("Florence","*Sigh*" ,port.image_Florence);
		dialogues[5][5] = new Dialoge("Florence","Give the bugs and the knife to me and go relax yourself." ,port.image_Florence);
		dialogues[6][0] = new Dialoge("Florence", "Triss is very brave." ,port.image_Florence);
		dialogues[6][1] = new Dialoge("Florence", "She risks everything to help people get around the Gowls." ,port.image_Florence);
		dialogues[7][0] = new Dialoge("Florence:","*Groan*", port.image_Florence);
		dialogues[7][1] = new Dialoge("Florence","Alder, it's really early, please don't yell.",port.image_Florence);
		dialogues[7][2] = new Dialoge("Florence gave Alder an annoyed look before she noticed the sword in his hands.",1);
		dialogues[7][3] = new Dialoge("Florence","Alder, what is that!!",port.image_Florence);
		dialogues[7][4] = new Dialoge("Alder","It’s a sword!",port.image_Alder);
		dialogues[7][5] = new Dialoge("Florence","Why do you have a sword and where did it come from!",port.image_Florence);
		dialogues[7][6] = new Dialoge("Alder","A mouse in my dreams gave it to me.",port.image_Alder);
		dialogues[7][7] = new Dialoge("Florence","What!?",port.image_Florence);
		dialogues[7][8] = new Dialoge("Kyla","Heh Heh.",port.image_Kyla);
		dialogues[7][9] = new Dialoge("Kyla","What's this about a sword?",port.image_Kyla);
	}
	
	public void setDialogueOptions() {
		options[0] = "So what's Thay here for?";
		options[1] = "How does the magic on cottage work?";
	}
	
	public void checkConditions() {
		if(dialogueSet == 1 || dialogueSet == 2) {
			if (dialogues[dialogueSet][dialogueIndex] == null) {
				for (boolean checkCondition: contConditions) {
					if(checkCondition == false) {
						dialogueIndex = 0;
						speak();
					}
				} if (dialogues[dialogueSet][dialogueIndex] == null) {
					//dialogueIndex = 0;
					gp.s.swh[2] = true;
				}
			}
		} else if (dialogueSet == 5) {
			gp.s.swh[4] = true;
			gp.s.part = 4;
		} else if (dialogueSet == 7) {
			gp.s.swh[10] = true;
		}
	}
	
	public void choiceResponce() {
		if (gp.ui.choiceSlot == 0) {
			startDialogue(this, 1);
			contConditions[0] = true;
		}
		if (gp.ui.choiceSlot == 1) {
			startDialogue(this, 2);
			contConditions[1] = true;
		}
	}
	
	public void speak() {
		facePlayer();
		gp.ui.choiceSlot = 0;
		gp.ui.firstValue = 0;
		if(gp.s.chapter == 1) {
			if(gp.s.c1Switch[1] == true) {
				startDialogue(this, 0);
			} else if(gp.s.c1Switch[3] == true) {
				if(gp.playable.get(0).getWeapon_prime().name == null) {
					startDialogue(this, 3);
				} else {
					startDialogue(this, 4);
				}
			} else if(gp.s.c1Switch[4] == true) {
				startDialogue(this, 5);
			}
		} else if(gp.s.chapter == 2) {
			startDialogue(this, 6);
		} else if(gp.s.chapter == 3) {
			startDialogue(this, 7);
		}
		gp.keyH.enterPressed = false;
	}
}
