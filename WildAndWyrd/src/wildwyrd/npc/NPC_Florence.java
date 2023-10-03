package wildwyrd.npc;

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
		return image.getSubimage(xGrid * 48, yGrid * 48, 48, 48);
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
		dialogues[1][0] = new Dialoge("Florence", "Potions probably.", 1);
		dialogues[1][1] = new Dialoge("Florence", "He usually comes here for sanctuary or potions.", 1);
		dialogues[2][0] = new Dialoge("Florence", "Well.", 1);
		dialogues[2][1] = new Dialoge("Florence", "Kyla’s cast several illusions on the cottage, one of which makes it looks like a boulder from the outside.", 1);
		dialogues[2][2] = new Dialoge("Florence", "She's also muted the rooms and made our scents smell somewhat grassy.", 1);
		dialogues[2][3] = new Dialoge("Florence", "This place cannot be seen from the outside world, so we tend to call it the burrow.", 1);
		dialogues[3][0] = new Dialoge("Florence", "The knife should be in the shed. You can go once you've got it." ,1);
		dialogues[4][0] = new Dialoge("Alder", "I found the knife!" ,1);
		dialogues[4][1] = new Dialoge("Florence", "Fantastic!" ,1);
		dialogues[4][2] = new Dialoge("Florence", "Just don't go too far." ,1);
		dialogues[5][0] = new Dialoge("Alder", "Florence! I'm done!" ,1);
		dialogues[5][1] = new Dialoge("Florence was finishing a conversation with Thay when they looked at Alder they immediately noticed the swollen red marks where the hornets had stung his exposed skin." ,1);
		dialogues[5][2] = new Dialoge("Florence","Alder what happened? Are you ok!?" ,1);
		dialogues[5][3] = new Dialoge("Alder","Um..." ,1);
		dialogues[5][4] = new Dialoge("Alder was briefly put off by Florence's concern, but he knew she could get paranoid when it can to his safety." ,1);
		dialogues[5][5] = new Dialoge("Alder","I got stung by wasps a few times but I'm alright!" ,1);
		dialogues[5][6] = new Dialoge("Florence","*Sigh*" ,1);
		dialogues[5][7] = new Dialoge("Florence","Give the bugs and the knife to me and go relax yourself." ,1);
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
		
		gp.keyH.enterPressed = false;
	}
}
