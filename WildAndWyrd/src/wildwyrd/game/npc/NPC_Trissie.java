package wildwyrd.game.npc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Itm_Bow;
import wildwyrd.game.items.Itm_Primative_Arrow;
import wildwyrd.game.object.Dialoge;
import wildwyrd.game.objective.Quest;

public class NPC_Trissie extends NPC {
	public static final int npcId = 5;
	public static final String npcName = "Trissie";
	Quest sidequest = gp.objective.quests[1];
	private boolean climbing = false;
	public NPC_Trissie(GamePanel gp) {
		super(gp);
		//this.gp = gp;
		options = new String[2];
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
		dialogues[0][0] = new Dialoge("Trissie quickly set up a makeshift dummy out of leaves, sticks and a cheaply made old burlap sack that was intended for foraging before planting it in the ground so it would stand upright." ,1);
		dialogues[0][1] = new Dialoge("The finished product was crude and clearly rushed, but would make a good target.", 1);
		dialogues[0][2] = new Dialoge("Trissie", "That'll do.", 1);
		dialogues[0][3] = new Dialoge("Trissie", "Alder, wait there.", 1);
		dialogues[0][4] = new Dialoge("Trissie brought a bow and some arrows set aside for hunting from the shed. Alder thought she looked a little comical dragging the bow which was big even for him.", 1);
		dialogues[0][5] = new Dialoge("The bow was originally meant for Florence, but finding herself unskilled she never used it. Trissie handed them to Alder.", 1);
		dialogues[0][6] = new Dialoge("Trissie", "Now set the arrow in the bow, take aim and fire.", 1);
		dialogues[0][7] = new Dialoge("Trissie", "Let's begin.", 1);
		dialogues[1][0] = new Dialoge("Trissie", "It will be hard for me to leave these woods if I stay any longer!", 1);
		dialogues[1][1] = new Dialoge("Alder", "There’s one more thing I’d like to know Triss.", 1);
		dialogues[1][2] = new Dialoge("Trissie", "Yes?", 1);
		dialogues[1][3] = new Dialoge("", 3);
		dialogues[2][0] = new Dialoge("Trissie", "Well, if I’d have to give an example?", 1);
		dialogues[2][1] = new Dialoge("Trissie", "I have a brother in the Gowls.", 1);
		dialogues[2][2] = new Dialoge("Trissie pointed her thumb at her tail stump.", 1);
		dialogues[2][3] = new Dialoge("Trissie", "And because I helped a human, he did this to me.", 1);
		dialogues[2][4] = new Dialoge("Trissie moved swiftly towards the nearest tree and in an instant had climbed up and disappeared among the branches. It was as if she'd vanished. Leaving not even a rustle of leaves.", 1);
		dialogues[2][5] = new Dialoge("Alder returned to the cottage a little disappointed with Trissie gone so soon. But he had work to do and he set about his remaining chores for the day.", 1);
		dialogues[3][0] = new Dialoge("Trissie", "I’m not easy to catch but you must be careful and stay safe.", 1);
		dialogues[3][1] = new Dialoge("Trissie", "If you do, I'm sure we'll meet before long.", 1);
		dialogues[3][2] = new Dialoge("Trissie moved swiftly towards the nearest tree and in an instant had climbed up and disappeared among the branches. It was as if she'd vanished. Leaving not even a rustle of leaves.", 1);
		dialogues[3][3] = new Dialoge("Alder returned to the cottage a little disappointed with Trissie gone so soon. But he had work to do and he set about his remaining chores for the day.", 1);
	}
	
	public void setDialogueOptions() {
		options[0] = "What are the Gowls like?";
		options[1] = "Will we see each other again soon?";
	}

	public void choiceResponce() {
		dialogueIndex = 0;
		if(gp.s.chapter == 2) {
			if (gp.ui.choiceSlot == 0) {
				startDialogue(this, 2);
				gp.s.branchSwitch[0] = 0;
			}
			if (gp.ui.choiceSlot == 1) {
				startDialogue(this, 3);
				gp.s.branchSwitch[0] = 1;
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
		if(gp.s.chapter == 2) {
			if(gp.playable.get(0).getWeapon_second().name == null && gp.s.c2Switch[2]) {
				startDialogue(this, 0);
				gp.playable.get(0).setWeapon_second(new Itm_Bow(gp));
				gp.player.pickUpObject(new Itm_Primative_Arrow(gp), 5);
			} else if (!gp.s.c2Switch[2]) {
				startDialogue(this, 1);
			}
		}
		gp.keyH.enterPressed = false;
	}
}
