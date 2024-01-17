package wildwyrd.game.npc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.object.Dialoge;
import wildwyrd.game.object.Obj_Blackberry;
import wildwyrd.game.objective.Quest;

public class NPC_Kyla extends NPC {
	public static final int npcId = 3;
	public static final String npcName = "Kyla";
	Quest sidequest = gp.objective.quests[1];
	public NPC_Kyla(GamePanel gp) {
		super(gp);
		//this.gp = gp;
		id = npcId;
		name = npcName;
		type = EntityType.Sprite;
		direction = "down";
		speed = 1;
		options = new String[3];
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
		up1 = getPlayerImage(10, 3);
		up2 = getPlayerImage(9, 3);
		up3 = getPlayerImage(11, 3);
		down1 = getPlayerImage(10, 0);
		down2 = getPlayerImage(9, 0);
		down3 = getPlayerImage(11, 0);
		left1 = getPlayerImage(10, 1);
		left2 = getPlayerImage(9, 1);
		left3 = getPlayerImage(11, 1);
		right1 = getPlayerImage(10, 2);
		right2 = getPlayerImage(9, 2);
		right3 = getPlayerImage(11, 2);
		unique = getSpecialImage(0, 2);
	}
	
	public void setAction() {
		
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("Madam Kyla was relaxed in her chair with her nose in a red book titled \"A sorcerers guide to Dragons and Wyverns\". She peered from the book to Alder." ,1);
		dialogues[0][1] = new Dialoge("Kyla", "I take it Mr Prickle has gone?", port.image_Kyla);
		dialogues[0][2] = new Dialoge("Alder", "Yes, Madam.", port.image_Alder);
		dialogues[0][3] = new Dialoge("Kyla", "Making you free boy?", port.image_Kyla);
		dialogues[0][4] = new Dialoge("Alder", "Yes, Madam.", 1);
		dialogues[0][5] = new Dialoge("Kyla", "Then clean the fireplace, wash the pots in the kitchen until they shine and...", port.image_Kyla);
		dialogues[0][6] = new Dialoge("Kyla", "Ah!", port.image_Kyla);
		dialogues[0][7] = new Dialoge("Kyla", "I need you to grind some bramble leaves.", port.image_Kyla);
		dialogues[0][8] = new Dialoge("Kyla", "Use the mortar in the shed to grind them into dust.", port.image_Kyla);
		dialogues[0][9] = new Dialoge("Kyla", "Leave the dust in the pot on the far end next to the shed table.", port.image_Kyla);
		dialogues[0][10] = new Dialoge("Kyla", "That should keep you busy!", port.image_Kyla);
		dialogues[0][11] = new Dialoge("Accept quest", 2);
		dialogues[1][0] = new Dialoge("Alder", "Yes, Madam.", port.image_Alder);
		dialogues[1][1] = new Dialoge("Kyla", "Excellent!", port.image_Kyla);
		dialogues[1][2] = new Dialoge("Alder accepted " + sidequest.name, port.image_Kyla);
		dialogues[2][0] = new Dialoge("Alder", "...", port.image_Alder);
		dialogues[2][1] = new Dialoge("Kyla", "Don't you glare at me!", port.image_Kyla);
		dialogues[2][2] = new Dialoge("Kyla", "I'm not permitting you here out of charity!", port.image_Kyla);
		dialogues[2][3] = new Dialoge("Kyla", "Work for your sanctuary!", port.image_Kyla);
		dialogues[2][4] = new Dialoge("Alder", "U-Um.", port.image_Alder);
		dialogues[2][5] = new Dialoge("Alder", "Y-Yes!", port.image_Alder);
		dialogues[2][6] = new Dialoge("Alder", "Sorry madam.", port.image_Alder);
		dialogues[2][7] = new Dialoge("Alder knew better than to argue back. Alder accepted " + sidequest.name, port.image_Kyla);
		dialogues[3][0] = new Dialoge("Kyla", "Clean the fireplace, scrub the pots and grind the leaves.", port.image_Kyla);
		dialogues[4][0] = new Dialoge("Kyla", "Have you finished then?",port.image_Kyla);
		dialogues[4][1] = new Dialoge("Alder", "Yes madam.",port.image_Alder);
		dialogues[4][2] = new Dialoge("Kyla", "Good.",port.image_Kyla);
		dialogues[4][3] = new Dialoge("Kyla","I have no futher need for you.",port.image_Kyla);
		dialogues[4][4] = new Dialoge("Kyla","Do as you please.",port.image_Kyla);
		dialogues[5][0] = new Dialoge("Kyla","I have no futher need for you.",port.image_Kyla);
		dialogues[5][1] = new Dialoge("Kyla","Do as you please.",port.image_Kyla);
		dialogues[6][0] = new Dialoge("Kyla","I like the girl.",port.image_Kyla);
		dialogues[6][1] = new Dialoge("Kyla","Shame she never stays.",port.image_Kyla);
		dialogues[7][0] = new Dialoge("Kyla","Boy, let me see that sword!",port.image_Kyla);
		dialogues[7][1] = new Dialoge("Alder","Um?",port.image_Alder);
		dialogues[7][2] = new Dialoge("Alder","O-ok.",port.image_Alder);
		dialogues[7][3] = new Dialoge("Alder handed the sword over to Kyla",1);
		dialogues[7][4] = new Dialoge("Kyla","It can't be?",port.image_Kyla);
		dialogues[7][5] = new Dialoge("Kyla","Boy, in the bookshelf, there should be a blue book on magical artefacts, bring it here.",port.image_Kyla);
		dialogues[7][6] = new Dialoge("Alder","Right.",port.image_Alder);
		dialogues[8][0] = new Dialoge(" " ,3);
		dialogues[9][0] = new Dialoge("Kyla","What is…!?",port.image_Kyla);
		dialogues[9][0] = new Dialoge("Kyla","How do you not know what the Scion is!?",port.image_Kyla);
		dialogues[9][0] = new Dialoge("Florence","I thought it was more important that he knew herbs and how to read and write than history.",port.image_Florence);
		dialogues[9][0] = new Dialoge("Kyla","*Sigh*",port.image_Kyla);
		dialogues[9][0] = new Dialoge("Kyla","The Scion is the great hero of Avalon.",port.image_Kyla);
		dialogues[9][0] = new Dialoge("Kyla","They are able to pass on their knowledge and natural abilites after death.",port.image_Kyla);
		dialogues[9][0] = new Dialoge("Kyla","One previous Scion killed a dragon, while another saved countless innocents from slavers.",port.image_Kyla);
		dialogues[9][0] = new Dialoge("Kyla","And I knew one who put kings themselves in their rightful place.",port.image_Kyla);
		dialogues[9][0] = new Dialoge("Kyla","But I digress.",port.image_Kyla);
		dialogues[10][0] = new Dialoge("Kyla","That mouse was no doubt Agrimus; the Scion before you.",port.image_Kyla);
		dialogues[10][0] = new Dialoge("Kyla","He appeared to you to pass on his title and sword.",port.image_Kyla);
		dialogues[10][0] = new Dialoge("Kyla","I am shocked he chose you though.",port.image_Kyla);
		dialogues[10][0] = new Dialoge("Kyla","He was the hero of the war against the humans of Glorion.",port.image_Kyla);
		dialogues[10][0] = new Dialoge("Kyla","And out of countless potentials he goes for my servant.",port.image_Kyla);
		dialogues[11][0] = new Dialoge("Kyla","It’s in the book.",port.image_Kyla);
		dialogues[11][0] = new Dialoge("Kyla","The sword blade is like a plant and the grip its roots.",port.image_Kyla);
		dialogues[11][0] = new Dialoge("Kyla","When held by the Scion it grows and strengthens, when held by any other it weakens and dies.",port.image_Kyla);
		dialogues[12][0] = new Dialoge("???","The rabbit seeks his burrow!",1);
		dialogues[12][0] = new Dialoge("*Click*",1);
		dialogues[12][0] = new Dialoge("*Ring*!",1);
		dialogues[12][0] = new Dialoge("The door unlocked and the bell chimed in responce to the words of the voice outside. The ringing resounded throught the cottage but was prevelant in the already alerted living room.",1);
		dialogues[12][0] = new Dialoge("Alder, Florence and Kyla","...!?",port.image_Alder);
		dialogues[12][0] = new Dialoge("Florence","Who is that!?",port.image_Florence);
	}
	
	public void setDialogueOptions() {
		options[0] = "What is a Scion?";
		options[1] = "Does this have anything to do with the mouse I saw in my dream?";
		options[2] = "What's with the sword blade?";
	}

	public void choiceResponce() {
		if(gp.s.chapter == 1 || gp.s.chapter == 2) {
			dialogueIndex = 0;
			if (gp.ui.choiceSlot == 0) {
				startDialogue(this, 1);
				sidequest.acceptQuest();
			}
			if (gp.ui.choiceSlot == 1) {
				startDialogue(this, 2);
				sidequest.acceptQuest();
			}
			if (!gp.objectExists(Obj_Blackberry.objId, 2)) {
				for(int i = 0; i < gp.obj[2].length; i++) {
					if(gp.obj[2][i] == null) {
						gp.obj[2][i] = new Obj_Blackberry(gp);
						gp.obj[2][i].worldX = gp.tileSize * 3;
						gp.obj[2][i].worldY = gp.tileSize * 4;
					}
				}
			}
		} else if (gp.s.chapter == 3) {
			dialogueIndex = 0;
			if (gp.ui.choiceSlot == 0) {startDialogue(this, 9);}
			if (gp.ui.choiceSlot == 1) {startDialogue(this, 10);}
			if (gp.ui.choiceSlot == 2) {startDialogue(this, 11);}
		}
	}
	
	public void speak() {
		facePlayer();
		gp.ui.choiceSlot = 0;
		gp.ui.firstValue = 0;
		if(gp.s.chapter == 1) {
			if(!gp.objective.quests[1].isAccepted()) {
				startDialogue(this, 0);
			} else if (!gp.objective.quests[1].isCompleted()) {
				startDialogue(this, 3);
			} else if (!gp.objective.quests[1].isSubmitted()) {
				startDialogue(this, 4);
				gp.objective.quests[1].submitQuest();
			} else {
				startDialogue(this, 5);
			}
		} else if(gp.s.chapter == 2) {
			startDialogue(this, 6);
		} else if(gp.s.chapter == 3) {
			System.out.println(gp.s.c3Switch[1]);
			if(gp.s.c3Switch[1]) {
				startDialogue(this, 7);
				gp.s.c3Switch[1] = false;
			} else if (gp.s.c3Switch[2]) {
				gp.s.swh[11] = true;
			} else if (gp.s.c3Switch[3]) {
				startDialogue(this, 8);
			}
		}
		gp.keyH.enterPressed = false;
	}
}
