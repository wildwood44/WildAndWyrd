package wildwyrd.game.npc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.object.Dialoge;

public class NPC_Thay extends NPC {
	public static final int npcId = 2;
	public static final String npcName = "Thay";
	public NPC_Thay(GamePanel gp) {
		super(gp);
		//this.gp = gp;
		options = new String[3];
		contConditions = new boolean[4];
		id = npcId;
		name = npcName;
		type = EntityType.Sprite;
		direction = "down";
		speed = 1;
		//Size
		
		//Conditions
		contConditions[0] = false;
		contConditions[1] = false;
		contConditions[2] = false;
		contConditions[3] = false;
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
		up1 = getPlayerImage(1, 7);
		up2 = getPlayerImage(0, 7);
		up3 = getPlayerImage(2, 7);
		down1 = getPlayerImage(1, 4);
		down2 = getPlayerImage(0, 4);
		down3 = getPlayerImage(2, 4);
		left1 = getPlayerImage(1, 5);
		left2 = getPlayerImage(0, 5);
		left3 = getPlayerImage(2, 5);
		right1 = getPlayerImage(1, 6);
		right2 = getPlayerImage(0, 6);
		right3 = getPlayerImage(2, 6);
	}
	
	public void setAction() {
		
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(" " ,3);
		dialogues[1][0] = new Dialoge("Thay", "It was quite lovely.", port.image_Thay);
		dialogues[1][1] = new Dialoge("Thay", "This summer has been good to us all.", port.image_Thay);
		dialogues[2][0] = new Dialoge("Thay", "The usual, gave her the herbs she wanted in exchange for the potions I need.", port.image_Thay);
		dialogues[2][1] = new Dialoge("Thay reached into his bag and pulled out a vial of dark-purple potion.", 1);
		dialogues[3][0] = new Dialoge("Thay", "Well, to the north, there are more woodlands.", port.image_Thay);
		dialogues[3][1] = new Dialoge("Thay", "But to the south is the settlement in the valley where mice and other small beasts live.", port.image_Thay);
		dialogues[3][2] = new Dialoge("Thay", "West you'll run into the river and if you climb to the treetops you can see Hare Hill east from here.",port.image_Thay);
		dialogues[3][3] = new Dialoge("Thay", "Wait!?",port.image_Thay);
		dialogues[3][4] = new Dialoge("Thay", "Alder, you aren't thinking of going to these places are you!?",port.image_Thay);
		dialogues[3][5] = new Dialoge("Alder","I...",port.image_Alder);
		dialogues[3][6] = new Dialoge("Alder","think, it would be nice to see new places.",port.image_Alder);
		dialogues[3][7] = new Dialoge("Thay", "No!",port.image_Thay);
		dialogues[3][8] = new Dialoge("Thay", "No, Alder!",port.image_Thay);
		dialogues[3][9] = new Dialoge("Thay", "It's too dangerous!",port.image_Thay);
		dialogues[3][10] = new Dialoge("Thay", "If anyone sees you, the Gowl's will come after you!!",port.image_Thay);
		dialogues[3][11] = new Dialoge("Thay", "I cannot even express how much humans are hated these days!!",port.image_Thay);
		dialogues[3][12] = new Dialoge("Alder","Ok! Ok!",port.image_Alder);
		dialogues[3][13] = new Dialoge("Alder","I won't go wandering!!",port.image_Alder);
		dialogues[3][14] = new Dialoge("Alder","I'm sorry!",port.image_Alder);
		dialogues[3][15] = new Dialoge("Thay", "I hope not!",port.image_Thay);
		dialogues[3][16] = new Dialoge("Thay", "I'd never forgive myself if anything were to happen to you.",port.image_Thay);
		dialogues[4][0] = new Dialoge("Thay", "Fuller woods is where we are right now.",port.image_Thay);
		dialogues[4][1] = new Dialoge("Thay", "This ancient woodland goes on for miles.",port.image_Thay);
		dialogues[4][2] = new Dialoge("Thay", "There are many birds in these woods and unfortunately that includes birds of prey.",port.image_Thay);
		dialogues[4][3] = new Dialoge("Thay", "Most go after small creatures so you'll be alright.",port.image_Thay);
		dialogues[5][0] = new Dialoge("Alder","It's not that I want to go there, I'd just like to know.",port.image_Alder);
		dialogues[5][1] = new Dialoge("Thay", "It's called Forton.",port.image_Thay);
		dialogues[5][2] = new Dialoge("Thay", "It's known for its great library, taking in orphans within the region and being the resting place of the hero, Agrimus.",port.image_Thay);
		dialogues[5][3] = new Dialoge("Thay", "It is also one of the few mouse settlements that is not controlled by the church.",port.image_Thay);
		dialogues[6][0] = new Dialoge("Alder","Florence and I sometimes go there to get water.",port.image_Alder);
		dialogues[6][1] = new Dialoge("Alder","With caution of course.",port.image_Alder);
		dialogues[6][2] = new Dialoge("Alder","We caught a fish in our bucket once.",port.image_Alder);
		dialogues[6][3] = new Dialoge("Thay", "Hmm. You sure that's safe?",port.image_Thay);
		dialogues[6][4] = new Dialoge("Thay", "There are many creatures drifting on the river.",port.image_Thay);
		dialogues[6][5] = new Dialoge("Thay", "Swimming in it too.",port.image_Thay);
		dialogues[6][6] = new Dialoge("Alder","It's alright.",port.image_Alder);
		dialogues[6][7] = new Dialoge("Alder","Florence has always kept us hidden.",port.image_Alder);
		dialogues[6][8] = new Dialoge("Thay", "Err, ok then.",port.image_Thay);
		dialogues[7][0] = new Dialoge("Alder","It's not that I want to go there, I'd just like to know.",port.image_Alder);
		dialogues[7][1] = new Dialoge("Thay", "Hare Hill is the largest hill in this region.",port.image_Thay);
		dialogues[7][2] = new Dialoge("Thay", "It is home to many hare and rabbit villages.",port.image_Thay);
		dialogues[7][3] = new Dialoge("Thay", "At the top is the capital, Breabuck.",port.image_Thay);
		dialogues[7][4] = new Dialoge("Thay", "But it is a difficult and exhausting hike. Some routes lead to rock walls travelers need to scramble up.",port.image_Thay);
		dialogues[7][5] = new Dialoge("Thay", "Unless they go through the rabbits tunnels of course.",port.image_Thay);
		dialogues[8][0] = new Dialoge("Thay", "Well. There was a certain king who did very, very bad things.",port.image_Thay);
		dialogues[8][1] = new Dialoge("Alder","What kinds of things?",port.image_Alder);
		dialogues[8][2] = new Dialoge("Thay", "You're too young to know.",port.image_Thay);
		dialogues[8][3] = new Dialoge("Thay", "But all you need to know is that he was cruel and unforgivable.",port.image_Thay);
		dialogues[8][4] = new Dialoge("Thay", "So unforgivable, that along with his underling, all humans became hated by creatures great and small.",port.image_Thay);
		dialogues[8][5] = new Dialoge("Alder","But why?",port.image_Alder);
		dialogues[8][6] = new Dialoge("Alder","What did we do?",port.image_Alder);
		dialogues[8][7] = new Dialoge("Thay", "For your part.",1);
		dialogues[8][8] = new Dialoge("Thay", "Nothing.",port.image_Thay);
		dialogues[8][9] = new Dialoge("Thay", "But that didn't matter to those who had lost friends and family in the conflict.",port.image_Thay);
		dialogues[8][10] = new Dialoge("Thay", "Best for you to stay within the burrows borders.",port.image_Thay);
		dialogues[8][11] = new Dialoge("Thay", "If you are seen.",port.image_Thay);
		dialogues[8][12] = new Dialoge("Thay", "You will be assumed aligned with the kings ideals and killed.",port.image_Thay);
		dialogues[9][0] = new Dialoge("Thay", "You alright, Alder.",port.image_Thay);
		dialogues[9][1] = new Dialoge("Alder", "I was attacked by wasps while I was out.",port.image_Alder);
		dialogues[9][2] = new Dialoge("Thay", "Wasps huh, horrible things.",port.image_Thay);
		dialogues[9][3] = new Dialoge("Thay", "Then todays lesson will be on plantain, it might ease your discomfort.",port.image_Thay);
	}
	
	public void setDialogueOptions() {
		options[0] = "How was your journey?";
		options[1] = "How did it go with Madame Kyla?";
		options[2] = "What's it like beyond the burrow?";
		if(contConditions[2] == true) {
			options[3] = "About the woods?";
			options[4] = "About the the mouse village?";
			options[5] = "About the river?";
			options[6] = "About the hill?";
			options[7] = "Why are humans so hated?";
		}
	}
	
	public void checkConditions() {
		if(gp.s.c1Switch[2] == true) {
			if (dialogues[dialogueSet][dialogueIndex] == null) {
				for (boolean checkCondition: contConditions) {
					if(checkCondition == false) {
						dialogueIndex = 0;
						speak();
					}
				} if (dialogues[dialogueSet][dialogueIndex] == null) {
					//dialogueIndex = 0;
					gp.s.swh[3] = true;
					gp.s.part = 3;
				}
			}
		}
	}
	public void choiceResponce() {
		if (gp.ui.choiceSlot == 0) {
			startDialogue(this, 1);
			contConditions[0] = true;
		}
		else if (gp.ui.choiceSlot == 1) {
			startDialogue(this, 2);
			contConditions[1] = true;
		}
		else if (gp.ui.choiceSlot == 2) {
			startDialogue(this, 3);
			options = new String[8];
			contConditions[2] = true;
			setDialogueOptions();
		}
		else if (gp.ui.choiceSlot == 3) {
			startDialogue(this, 4);
		}
		else if (gp.ui.choiceSlot == 4) {
			startDialogue(this, 5);
		}
		else if (gp.ui.choiceSlot == 5) {
			startDialogue(this, 6);
		}
		else if (gp.ui.choiceSlot == 6) {
			startDialogue(this, 7);
		}
		else if (gp.ui.choiceSlot == 7) {
			startDialogue(this, 8);
			contConditions[3] = true;
		}
	}
	
	public void speak() {
		facePlayer();
		gp.ui.choiceSlot = 0;
		gp.ui.firstValue = 0;
		if(gp.s.c1Switch[2] == true) {
			startDialogue(this, 0);
		} else if(gp.s.c1Switch[3] == true) {
			//startDialogue(this, 9);
		} else if(gp.s.c1Switch[4] == true) {
			startDialogue(this, 9);
			gp.glossary.unlock("plant", "plantain");
			gp.playable.get(0).heal(5);
		}
		gp.keyH.enterPressed = false;
	}
}
