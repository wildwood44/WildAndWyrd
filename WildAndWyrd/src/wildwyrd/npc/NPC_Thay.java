package wildwyrd.npc;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.object.Dialoge;

public class NPC_Thay extends Entity {
	public NPC_Thay(GamePanel gp) {
		super(gp);
		//this.gp = gp;
		name = "Thay";
		type = EntityType.Sprite;
		direction = "down";
		speed = 1;
		contConditions[0] = false;
		contConditions[1] = false;
		contConditions[2] = false;
		contConditions[3] = false;
		setDialogue();
		setDialogueOptions();
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
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			switch(direction) {
			case "up":
				if(spriteNum == 1) {
					image = getPlayerImage(10, 3);
				} else if(spriteNum == 2) {
					image = getPlayerImage(9, 3);
				} else if(spriteNum == 3) {
					image = getPlayerImage(10, 3);
				} else if(spriteNum == 4) {
					image = getPlayerImage(11, 3);
				}
				break;
			case "down":
				if(spriteNum == 1) {
					image = getPlayerImage(10, 0);
				} else if(spriteNum == 2) {
					image = getPlayerImage(9, 0);
				} else if(spriteNum == 3) {
					image = getPlayerImage(10, 0);
				} else if(spriteNum == 4) {
					image = getPlayerImage(11, 0);
				}
				break;
			case "left":
				if(spriteNum == 1) {
					image = getPlayerImage(10, 1);
				} else if(spriteNum == 2) {
					image = getPlayerImage(9, 1);
				} else if(spriteNum == 3) {
					image = getPlayerImage(10, 1);
				} else if(spriteNum == 4) {
					image = getPlayerImage(11, 1);
				}
				break;
			case "right":
				if(spriteNum == 1) {
					image = getPlayerImage(10, 2);
				} else if(spriteNum == 2) {
					image = getPlayerImage(9, 2);
				} else if(spriteNum == 3) {
					image = getPlayerImage(10, 2);
				} else if(spriteNum == 4) {
					image = getPlayerImage(11, 2);
				}
				break;
			}
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
	
	public void setAction() {
		
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(" " ,3);
		dialogues[1][0] = new Dialoge("Thay", "It was quite lovely.", 1);
		dialogues[1][1] = new Dialoge("Thay", "This summer has been good to us all.", 1);
		dialogues[2][0] = new Dialoge("Thay", "The usual, gave her the herbs she wanted in exchange for the potions I need.", 1);
		dialogues[2][1] = new Dialoge("Thay reached into his bag and pulled out a vial of black liquid. It was one of the many potions that brought patrons to the cottage. Alder never asked what they needed them for.", 1);
		dialogues[3][0] = new Dialoge("Thay", "Well, to the north, there are more woodlands.", 1);
		dialogues[3][1] = new Dialoge("Thay", "But to the south is the settlement in the valley where mice and other small beasts live.", 1);
		dialogues[3][2] = new Dialoge("Thay", "West you’ll run into the river and if you climb to the treetops you can see Hare Hill east from here.",1);
		dialogues[3][3] = new Dialoge("Thay", "Wait!?",1);
		dialogues[3][4] = new Dialoge("Seeing growing interest and curiosity on Alder's face, Thay abruptly began to show concern.",1);
		dialogues[3][5] = new Dialoge("Thay", "Alder, you aren't thinking of going to these places are you!?",1);
		dialogues[3][6] = new Dialoge("Alder","I...",1);
		dialogues[3][7] = new Dialoge("Alder","think, it would be nice to see new places.",1);
		dialogues[3][8] = new Dialoge("Thay", "No!",1);
		dialogues[3][9] = new Dialoge("Thay", "No, Alder!",1);
		dialogues[3][10] = new Dialoge("Thay", "It’s too dangerous!",1);
		dialogues[3][11] = new Dialoge("Thay", "If anyone sees you, the Gowl's will come after you!!",1);
		dialogues[3][12] = new Dialoge("Thay", "I cannot even express how much humans are hated these days!!",1);
		dialogues[3][13] = new Dialoge("Alder","Ok! Ok!",1);
		dialogues[3][14] = new Dialoge("Alder","I won’t go wandering!!",1);
		dialogues[3][15] = new Dialoge("Alder","I'm sorry!",1);
		dialogues[3][16] = new Dialoge("Thay", "I hope not!",1);
		dialogues[3][17] = new Dialoge("Thay", "I’d never forgive myself if anything were to happen to you.",1);
		dialogues[3][18] = new Dialoge("Alder realised that the hedgehog was getting agitated and so decided to move on.",1);
		dialogues[4][0] = new Dialoge("Thay", "Fuller woods is where we are right now.",1);
		dialogues[4][1] = new Dialoge("Thay", "This ancient woodland goes on for miles.",1);
		dialogues[4][2] = new Dialoge("Thay", "There are many birds in these woods and unfortunately that includes birds of prey, :but most go after small creatures so you'll be alright.",1);
		dialogues[5][0] = new Dialoge("Alder","It’s not that I want to go there, I’d just like to know.",1);
		dialogues[5][1] = new Dialoge("Thay", "It’s called Forton.",1);
		dialogues[5][2] = new Dialoge("Thay", "It’s known for its great library, taking in orphans within the region and being the resting place of the hero, Agrimus.",1);
		dialogues[5][3] = new Dialoge("Thay", "It is also one of the few mouse settlements that is not controlled by the church.",1);
		dialogues[6][0] = new Dialoge("Alder","Florence and I sometimes go there to get water.",1);
		dialogues[6][1] = new Dialoge("Alder","With caution of course.",1);
		dialogues[6][2] = new Dialoge("Alder","We caught a fish in our bucket once.",1);
		dialogues[6][3] = new Dialoge("Thay", "Hmm. You sure that’s safe?",1);
		dialogues[6][4] = new Dialoge("Thay", "There are many creatures drifting on the river.",1);
		dialogues[6][5] = new Dialoge("Thay", "Swimming in it too.",1);
		dialogues[6][6] = new Dialoge("Alder","It's alright.",1);
		dialogues[6][7] = new Dialoge("Alder","Florence has always kept us hidden.",1);
		dialogues[6][8] = new Dialoge("Thay", "Err, ok then.",1);
		dialogues[7][0] = new Dialoge("Alder","It’s not that I want to go there, I’d just like to know.",1);
		dialogues[7][1] = new Dialoge("Thay", "Hare Hill is the largest hill in this region.",1);
		dialogues[7][2] = new Dialoge("Thay", "It is home to many hare and rabbit villages.",1);
		dialogues[7][3] = new Dialoge("Thay", "At the top is the capital, Breabuck.",1);
		dialogues[7][4] = new Dialoge("Thay", "But it is a difficult and exhausting hike. Some routes lead to rock walls travelers need to scramble up.",1);
		dialogues[7][5] = new Dialoge("Thay", "Unless they go through the rabbits tunnels of course.",1);
		dialogues[8][0] = new Dialoge("Thay", "Well. There was a certain king who did very, very bad things.",1);
		dialogues[8][1] = new Dialoge("Alder","What kinds of things?",1);
		dialogues[8][2] = new Dialoge("Thay", "You're too young to know.",1);
		dialogues[8][3] = new Dialoge("Thay", "But all you need to know is that he was cruel and unforgivable.",1);
		dialogues[8][4] = new Dialoge("Thay", "So unforgivable in fact that along with his followers, woodland folk even took vengeance on the humans who were against him.",1);
		dialogues[8][5] = new Dialoge("Alder","But why?",1);
		dialogues[8][6] = new Dialoge("Alder","What did they do?",1);
		dialogues[8][7] = new Dialoge("Thay", "Nothing.",1);
		dialogues[8][8] = new Dialoge("Thay", "But that didn’t matter to those who had lost friends and family in the conflict.",1);
		dialogues[8][9] = new Dialoge("Thay", "Best for you to stay within the burrows borders.",1);
		dialogues[8][10] = new Dialoge("Thay", "If you are seen.",1);
		dialogues[8][11] = new Dialoge("Thay", "You will be assumed aligned with the kings: ideals and killed.",1);
		dialogues[8][12] = new Dialoge("Alder gave a sad longing look to the deep wood. He’d like to see more than the tiny patch he called home. But alas as Thay said, the danger was too great.",1);
	}
	
	public void setDialogueOptions() {
		options[0] = "How was your journey?";
		options[1] = "How did it go with Madame Kyla?";
		options[2] = "What's it like beyond the burrow?";
		options[3] = "About the woods?";
		options[4] = "About the the mouse village?";
		options[5] = "About the river?";
		options[6] = "About the hill?";
		options[7] = "Why are humans so hated?";
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
			contConditions[2] = true;
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
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}
