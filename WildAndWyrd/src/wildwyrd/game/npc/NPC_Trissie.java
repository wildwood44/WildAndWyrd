package wildwyrd.game.npc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.object.Dialoge;
import wildwyrd.game.object.Obj_Blackberry;
import wildwyrd.game.object.Obj_Dummy;
import wildwyrd.game.objective.Quest;

public class NPC_Trissie extends NPC {
	public static final int npcId = 5;
	public static final String npcName = "Trissie";
	Quest sidequest = gp.objective.quests[1];
	private boolean climbing = false;
	public NPC_Trissie(GamePanel gp) {
		super(gp);
		//this.gp = gp;
		id = npcId;
		name = npcName;
		type = EntityType.Sprite;
		direction = "down";
		speed = 1;
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
		if (image != getSpriteSheet()) {
			image = getSpriteSheet();
		}
		return image.getSubimage(xGrid * 48, yGrid * 48, 48, 48);
	}
	
	public void getImage() {
		System.out.println("Climbing: " + climbing);
		if(climbing) {
			up1 = getSpecialImage(0, 1);
			up2 = getSpecialImage(1, 1);
			up3 = getSpecialImage(1, 1);
			down1 = getSpecialImage(0, 0);
			down2 = getSpecialImage(1, 0);
			down3 = getSpecialImage(1, 0);
		} else {
			up1 = getPlayerImage(4, 7);
			up2 = getPlayerImage(3, 7);
			up3 = getPlayerImage(5, 7);
			down1 = getPlayerImage(4, 4);
			down2 = getPlayerImage(3, 4);
			down3 = getPlayerImage(5, 4);
		}
		left1 = getPlayerImage(4, 5);
		left2 = getPlayerImage(3, 5);
		left3 = getPlayerImage(5, 5);
		right1 = getPlayerImage(4, 6);
		right2 = getPlayerImage(3, 6);
		right3 = getPlayerImage(5, 6);
		unique = getSpecialImage(0, 1);
	}
	
	public void setAction() {
		
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("Madam Kyla was relaxed in her chair with her nose in a red book titled \"A sorcerers guide to Dragons and Wyverns\". She peered from the book to Alder." ,1);
		dialogues[0][1] = new Dialoge("Kyla", "I take it Mr Prickle has gone?", 1);
		dialogues[0][2] = new Dialoge("Alder", "Yes, Madam.", 1);
		dialogues[0][3] = new Dialoge("Kyla", "Making you free boy?", 1);
		dialogues[0][4] = new Dialoge("Alder", "Yes, Madam.", 1);
		dialogues[0][5] = new Dialoge("Kyla", "Then clean the fireplace, wash the pots in the kitchen until they shine and...", 1);
		dialogues[0][6] = new Dialoge("Kyla", "Ah!", 1);
		dialogues[0][7] = new Dialoge("Kyla", "I need you to grind some bramble leaves.", 1);
		dialogues[0][8] = new Dialoge("Kyla", "Use the mortar in the shed to grind them into dust.", 1);
		dialogues[0][9] = new Dialoge("Kyla", "Leave the dust in the pot on the far end next to the shed table.", 1);
		dialogues[0][10] = new Dialoge("Kyla", "That should keep you busy!", 1);
		dialogues[0][10] = new Dialoge("Accept quest", 2);
		dialogues[1][0] = new Dialoge("Alder", "Yes, Madam.", 1);
		dialogues[1][1] = new Dialoge("Kyla", "Excellent!", 1);
		dialogues[1][2] = new Dialoge("Alder accepted " + sidequest.name, 1);
		dialogues[2][0] = new Dialoge("Alder", "...", 1);
		dialogues[2][1] = new Dialoge("Kyla", "Don't you glare at me!", 1);
		dialogues[2][2] = new Dialoge("Kyla", "I'm not permitting you here out of charity!", 1);
		dialogues[2][3] = new Dialoge("Kyla", "Work for your sanctuary!", 1);
		dialogues[2][4] = new Dialoge("Alder", "U-Um.", 1);
		dialogues[2][5] = new Dialoge("Alder", "Y-Yes!", 1);
		dialogues[2][6] = new Dialoge("Alder", "Sorry madam.", 1);
		dialogues[2][7] = new Dialoge("Alder knew better than to argue back. Alder accepted " + sidequest.name, 1);
		dialogues[3][0] = new Dialoge("Kyla", "Clean the fireplace, scrub the pots and grind the leaves.", 1);
		dialogues[4][0] = new Dialoge("Kyla", "Have you finished then?",1);
		dialogues[4][1] = new Dialoge("Alder", "Yes madam.",1);
		dialogues[4][2] = new Dialoge("Kyla", "Good.",1);
		dialogues[4][3] = new Dialoge("Kyla","I have no futher need for you.",1);
		dialogues[4][4] = new Dialoge("Kyla","Do as you please.",1);
		dialogues[5][0] = new Dialoge("Kyla","I have no futher need for you.",1);
		dialogues[5][1] = new Dialoge("Kyla","Do as you please.",1);
	}
	
	public void setDialogueOptions() {}

	public void choiceResponce() {
		dialogueIndex = 0;
		if(gp.s.chapter == 2) {
			if (gp.ui.choiceSlot == 0) {
				startDialogue(this, 1);
				sidequest.acceptQuest();
			}
			if (gp.ui.choiceSlot == 1) {
				startDialogue(this, 2);
				sidequest.acceptQuest();
			}
			if (!gp.objectExists(Obj_Dummy.objId, 2)) {
				for(int i = 0; i < gp.obj[2].length; i++) {
					if(gp.obj[2][i] == null) {
						gp.obj[2][i] = new Obj_Dummy(gp);
						gp.obj[2][i].worldX = gp.tileSize * 3;
						gp.obj[2][i].worldY = gp.tileSize * 4;
					}
				}
			}
		}
	}
	
	public void climbing(boolean isClimbing) {
		climbing = isClimbing;
	}
	
	public void speak() {
		facePlayer();
		gp.ui.choiceSlot = 0;
		gp.ui.firstValue = 0;
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
		gp.keyH.enterPressed = false;
	}
}