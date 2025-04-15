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
		sidequest[0] = gp.objective.quests[3];
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
		dialogues[8][0] = new Dialoge("Florence","I think we will need to have a long discussion about that sword Alder.", port.image_Florence);
		dialogues[9][0] = new Dialoge("Florence","Are you doing alright Alder?",port.image_Florence);
		dialogues[9][1] = new Dialoge("Alder","I'm fine.",port.image_Alder);
		dialogues[9][2] = new Dialoge("Alder","But are you sure you want to give up your apprenticeship?",port.image_Alder);
		dialogues[9][3] = new Dialoge("Florence","I'm not leaving you Alder!",port.image_Florence);
		dialogues[9][4] = new Dialoge("Florence","I'll protect you no matter what!",port.image_Florence);
		dialogues[10][0] = new Dialoge("Florence","What the matter Florence?", port.image_Alder);
		dialogues[10][1] = new Dialoge("Florence","We need provisions to get to Forton!", port.image_Florence);
		dialogues[10][2] = new Dialoge("Florence","Like what?", port.image_Alder);
		dialogues[10][3] = new Dialoge("Florence","Travelling Food.", port.image_Florence);
		dialogues[10][4] = new Dialoge("Florence","Something that won't get squished into mulch while we walk or run.", port.image_Florence);
		dialogues[10][5] = new Dialoge("Florence","Take what you can from the larder.", port.image_Florence);
		dialogues[11][0] = new Dialoge("Florence","I wish more time, but it is what it is",port.image_Florence);
		dialogues[12][0] = new Dialoge("Alder","This was all there was.",port.image_Alder);
		dialogues[12][1] = new Dialoge("Alder","Will it be enough?",port.image_Alder);
		dialogues[12][2] = new Dialoge("Florence","I hope so.",port.image_Florence);
		dialogues[12][3] = new Dialoge("Florence","Thank you, Alder.",port.image_Florence);
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
		if(gp.s.chapter == 1) {
			if (gp.ui.choiceSlot == 0) {
				startDialogue(this, 1);
				contConditions[0] = true;
			}
			if (gp.ui.choiceSlot == 1) {
				startDialogue(this, 2);
				contConditions[1] = true;
			}
		} else if (gp.s.chapter == 3) {
			if (gp.ui.choiceSlot == 0) {
				startDialogue(this, 1);
				gp.objective.quests[3].acceptQuest();
				hasQuest = false;
			}
			if (gp.ui.choiceSlot == 1) {
				startDialogue(this, 2);
				gp.objective.quests[3].acceptQuest();
				hasQuest = false;
			}
		}
	}
	
	public void speak() {
		facePlayer();
		gp.ui.choiceSlot = 0;
		gp.ui.firstValue = 0;
		if(gp.s.chapter == 1) {
			if(gp.s.c1Switch[1]) {
				startDialogue(this, 0);
			} else if(gp.s.c1Switch[3]) {
				if(gp.playable.get(0).getWeapon_prime().name == null) {
					startDialogue(this, 3);
				} else {
					startDialogue(this, 4);
				}
			} else if(gp.s.c1Switch[4]) {
				startDialogue(this, 5);
			}
		} else if(gp.s.chapter == 2) {
			startDialogue(this, 6);
		} else if(gp.s.chapter == 3) {
			if(gp.s.c3Switch[0]) {
				startDialogue(this, 7);
			} else if(gp.s.c3Switch[3]) {
				startDialogue(this, 8);
			} else if(!gp.s.c3Switch[3]) {
				System.out.println(hasQuest);
				if(hasQuest) {
					startDialogue(this, 10);
					gp.objective.quests[3].acceptQuest();
					hasQuest = false;
				} else if(!gp.objective.quests[3].isCompleted()) {
					startDialogue(this, 11);
				} else if(!gp.objective.quests[3].isSubmitted()) {
					startDialogue(this, 12);
					gp.objective.quests[3].submitQuest();
				} else {
					startDialogue(this, 9);
				}
			}
		}
		gp.keyH.enterPressed = false;
	}
	
	public boolean getQuest() {
		if(gp.s.chapter == 3 && !gp.objective.quests[3].isAccepted()) {
			if(!gp.s.c3Switch[3]) {
				hasQuest = true;
			}
		}
		return hasQuest;
	}
}
