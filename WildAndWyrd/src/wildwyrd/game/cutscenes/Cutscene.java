package wildwyrd.game.cutscenes;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.object.Dialoge;

public class Cutscene extends Entity {
	GamePanel gp;
	public Portrate port = new Portrate();

	public Cutscene(GamePanel gp) {
		super(gp);
		this.gp = gp;
	}
	
	public void setDialogue() {
		switch(dialogueSet) {
		case 0:
			dialogues[dialogueSet][0] = new Dialoge("Shrew", "My Lord", port.image_Dean);
			dialogues[dialogueSet][1] = new Dialoge("Shrew","*Pant* Excuse me!", port.image_Dean);
			dialogues[dialogueSet][2] = new Dialoge("Shrew","*Pant* *Pant* Lord Dilecto!",port.image_Dean);
			dialogues[dialogueSet][3] = new Dialoge("Dilecto","Hm?",port.image_Dilecto);
			dialogues[dialogueSet][4] = new Dialoge("Dilecto", "Yes, child?",port.image_Dilecto);
			dialogues[dialogueSet][5] = new Dialoge("Shrew", "*Ahem* Hazel is requesting a meeting.", port.image_Dean);
			dialogues[dialogueSet][6] = new Dialoge("Vole", "He has no authority to call a meeting involving the lord.", port.image_Ralph);
			dialogues[dialogueSet][7] = new Dialoge("Vole", "What is it for?", port.image_Ralph);
			dialogues[dialogueSet][8] = new Dialoge("Shrew", "The Gowls... they are... conducting patrols in Fuller Woods.", port.image_Dean);
			dialogues[dialogueSet][9] = new Dialoge("Vole", "That is unusual, but hardly of our concern.", port.image_Ralph);
			dialogues[dialogueSet][10] = new Dialoge("Vole", "We are not their target.", port.image_Ralph);
			dialogues[dialogueSet][11] = new Dialoge("Dilecto", "So who is?",port.image_Dilecto);
			dialogues[dialogueSet][12] = new Dialoge("Dilecto", "I approve and will be attending.",port.image_Dilecto);
			dialogues[dialogueSet][13] = new Dialoge("Vole", "But M'lord, surely they have their reasons.", port.image_Ralph);
			dialogues[dialogueSet][14] = new Dialoge("Dilecto", "Hunting some random human near our borders.", port.image_Ralph);
			dialogues[dialogueSet][15] = new Dialoge("Vole", "Can't blame them considering tensions with the kingdom of Gloria.", port.image_Ralph);
			dialogues[dialogueSet][16] = new Dialoge("Dilecto", "Not all humans are bloody kings fanatics, we had many as allies during the war.",port.image_Dilecto);
			dialogues[dialogueSet][17] = new Dialoge("Dilecto", "Times would have been far grimmer without them.",port.image_Dilecto);
			dialogues[dialogueSet][18] = new Dialoge("Shrew", "They fought along side Agrimus too?", port.image_Dean);
			dialogues[dialogueSet][19] = new Dialoge("Vole", "Why does every conversation we have with you end up involving the Scion, Dean?", port.image_Ralph);
			dialogues[dialogueSet][20] = new Dialoge("Dean", "I, um.", port.image_Dean);
			dialogues[dialogueSet][21] = new Dialoge("Dilecto", "Hahaha.",port.image_Dilecto);
			dialogues[dialogueSet][22] = new Dialoge("Dilecto", "It's alright Dean.",port.image_Dilecto);
			dialogues[dialogueSet][23] = new Dialoge("Dilecto", "It has been so long since his time.",port.image_Dilecto);
			dialogues[dialogueSet][24] = new Dialoge("Vole", "*Sighs* About thirty years now.", port.image_Ralph);
			dialogues[dialogueSet][25] = new Dialoge("Dean", "Um?", port.image_Dean);
			dialogues[dialogueSet][26] = new Dialoge("Dean", "Do you think the next one will appear soon?", port.image_Dean);
			dialogues[dialogueSet][27] = new Dialoge("Vole", "As momentous as that would be, Albion is at peace.", port.image_Ralph);
			dialogues[dialogueSet][28] = new Dialoge("Vole", "There is no need for a new Scion.", port.image_Ralph);
			dialogues[dialogueSet][29] = new Dialoge("Dilecto", "Despite the human cullings that is. ",port.image_Dilecto);
			dialogues[dialogueSet][30] = new Dialoge("Vole", "Sir Darrunt and his Gowls Knights do have every right to use any means necessary deal with those vermin.", port.image_Ralph);
			dialogues[dialogueSet][31] = new Dialoge("Dilecto", "Ralph!",port.image_Dilecto);
			dialogues[dialogueSet][32] = new Dialoge("Dilecto", "You go too far, no one has any right over another creatures life!",port.image_Dilecto);
			dialogues[dialogueSet][33] = new Dialoge("Ralph", "My lord, I meant-!", port.image_Ralph);
			dialogues[dialogueSet][34] = new Dialoge("Dilecto", "Enough!",port.image_Dilecto);
			dialogues[dialogueSet][35] = new Dialoge("Dilecto", "We shall discuss this later.",port.image_Dilecto);
			dialogues[dialogueSet][36] = new Dialoge("Dilecto", "Hm?",port.image_Dilecto);
			dialogues[dialogueSet][37] = new Dialoge("Dilecto", "Plumm, could you spare a moment?",port.image_Dilecto);
			dialogues[dialogueSet][38] = new Dialoge("Plumm", "Yes, M'lord?",port.image_Plumm);
			dialogues[dialogueSet][39] = new Dialoge("Dilecto", "I need you to let the other officials know that there is to be a meeting tomorrow at noon.",port.image_Dilecto);
			dialogues[dialogueSet][40] = new Dialoge("Dilecto", "Dean, be sure to invite Hazel. We will need to have a word with the Gowls.",port.image_Dilecto);
			dialogues[dialogueSet][41] = new Dialoge("Plumm", "The Gowls!?",port.image_Plumm);
			dialogues[dialogueSet][42] = new Dialoge("Plumm", "Why?",port.image_Plumm);
			dialogues[dialogueSet][43] = new Dialoge("Ralph", "They're sending patrols into Fuller Woods.", port.image_Ralph);
			dialogues[dialogueSet][44] = new Dialoge("Plumm", "Good heavens!",port.image_Plumm);
			dialogues[dialogueSet][45] = new Dialoge("Plumm", "Well, I'd best make haste!",port.image_Plumm);
			dialogues[dialogueSet][46] = new Dialoge("Ralph", "I'll help spread the word.", port.image_Ralph);
			dialogues[dialogueSet][47] = new Dialoge("Ralph", "Off with you Dean.", port.image_Ralph);
			dialogues[dialogueSet][48] = new Dialoge("Dean", "Yes sir!", port.image_Dean);
			dialogues[dialogueSet][49] = new Dialoge("As the others left Dilecto sombrely moved to face a tapestry.",1);
			dialogues[dialogueSet][50] = new Dialoge("Dilecto", "Agrimus.",port.image_Dilecto);
			dialogues[dialogueSet][51] = new Dialoge("Dilecto", "I wonder, what creature do you have in mind to successed you as Scion?",port.image_Dilecto);
			dialogues[dialogueSet][52] = new Dialoge("Dilecto", "Whoever you pick I shall always trust your judgement.",port.image_Dilecto);
			break;
		case 1:
			dialogues[dialogueSet][0] = new Dialoge("Hedgehog","The rabbit seeks his burrow.", port.image_Thay);
			dialogues[dialogueSet][1] = new Dialoge("Woman","Thay! It's good to see you!", port.image_Florence);
			dialogues[dialogueSet][2] = new Dialoge("Thay","And you Florence.", port.image_Thay);
			dialogues[dialogueSet][3] = new Dialoge("Thay","I'm here to see your mistress.", port.image_Thay);
			dialogues[dialogueSet][4] = new Dialoge("Florence","She's upstairs, should be down soon though.", port.image_Florence);
			dialogues[dialogueSet][5] = new Dialoge("Thay","And where's Alder?", port.image_Thay);
			dialogues[dialogueSet][6] = new Dialoge("Florence","He's inside, still cleaning the dishes.", port.image_Florence);
			dialogues[dialogueSet][7] = new Dialoge("Florence","Alder!!", port.image_Florence);
			dialogues[dialogueSet][8] = new Dialoge("Florence","Thay's here!", port.image_Florence);
			break;
		case 2:
			dialogues[dialogueSet][0] = new Dialoge("Thay","How've you been Alder?", port.image_Thay);
			dialogues[dialogueSet][1] = new Dialoge("Alder","I've been great!", port.image_Alder);
			dialogues[dialogueSet][2] = new Dialoge("Thay","You've gotten bigger too.", port.image_Thay);
			dialogues[dialogueSet][3] = new Dialoge("Thay","I remember when we were the same size.", port.image_Thay);
			dialogues[dialogueSet][4] = new Dialoge("Alder","I'm not a toddler anymore.", port.image_Alder);
			dialogues[dialogueSet][5] = new Dialoge("Thay","Well.", port.image_Thay);
			dialogues[dialogueSet][6] = new Dialoge("Thay","Better I'll be meeting with the witch.", port.image_Thay);
			dialogues[dialogueSet][7] = new Dialoge("Thay","I'll chat with you both soon.", port.image_Thay);
			break;
		case 3:
			dialogues[dialogueSet][0] = new Dialoge("Thay","Thank you, Madame Kyla.", port.image_Thay);
			dialogues[dialogueSet][1] = new Dialoge("Kyla","Yes yes. Come again.", port.image_Kyla);
			break;
		case 4:
			dialogues[dialogueSet][0] = new Dialoge("Florence","Alder!", port.image_Florence);
			dialogues[dialogueSet][1] = new Dialoge("Florence","I need you!", port.image_Florence);
			dialogues[dialogueSet][2] = new Dialoge("Thay","Don't worry about it lad.", port.image_Thay);
			dialogues[dialogueSet][3] = new Dialoge("Thay","I'll make sure you see me off.", port.image_Thay);
			dialogues[dialogueSet][4] = new Dialoge("Alder","*Sigh*", port.image_Alder);
			dialogues[dialogueSet][5] = new Dialoge("Alder","Yes Florence?", port.image_Alder);
			dialogues[dialogueSet][6] = new Dialoge("Florence","We're out of meat.", port.image_Florence);
			dialogues[dialogueSet][7] = new Dialoge("Florence","I need you to go hunt an insect or two.", port.image_Florence);
			dialogues[dialogueSet][8] = new Dialoge("Alder","Ok.", port.image_Alder);
			dialogues[dialogueSet][9] = new Dialoge("Alder","Where do you want me to look?", port.image_Alder);
			dialogues[dialogueSet][10] = new Dialoge("Florence","In the woods just outside the burrow.", port.image_Florence);
			dialogues[dialogueSet][11] = new Dialoge("Florence","There should be a hunting knife on the table inside the shed.", port.image_Florence);
			dialogues[dialogueSet][12] = new Dialoge("Alder","Thank you.", port.image_Alder);
			break;
		case 5:
			dialogues[dialogueSet][0] = new Dialoge("Thay","I'm heading off now lad.", port.image_Thay);
			dialogues[dialogueSet][1] = new Dialoge("Alder","Aw, already?!", port.image_Alder);
			dialogues[dialogueSet][2] = new Dialoge("Alder","Well, take care then.", port.image_Alder);
			dialogues[dialogueSet][3] = new Dialoge("Thay","I will.", port.image_Thay);
			dialogues[dialogueSet][4] = new Dialoge("Thay","Goodbye lad.", port.image_Thay);
			dialogues[dialogueSet][5] = new Dialoge("As the day came to an end the residents of the cottage were unaware of the shadows that came ever closer.",1);
			break;
		case 6:
			dialogues[dialogueSet][0] = new Dialoge("Alder","*Yawn*", port.image_Alder);
			dialogues[dialogueSet][1] = new Dialoge("Squirrel","Hi Alder!", port.image_Trissie);
			dialogues[dialogueSet][2] = new Dialoge("Alder","Trissie!", port.image_Alder);
			dialogues[dialogueSet][3] = new Dialoge("Trissie","Shh. Not so loud!", port.image_Trissie);
			dialogues[dialogueSet][4] = new Dialoge("Trissie","I need to see Kyla as soon as possible.", port.image_Trissie);
			dialogues[dialogueSet][5] = new Dialoge("Trissie","I have something you all should hear.", port.image_Trissie);
			dialogues[dialogueSet][6] = new Dialoge("Alder","Ok...", port.image_Alder);
			dialogues[dialogueSet][7] = new Dialoge("Trissie","Aw. Don't make that face.", port.image_Trissie);
			dialogues[dialogueSet][8] = new Dialoge("Trissie","Say.", port.image_Trissie);
			dialogues[dialogueSet][9] = new Dialoge("Trissie","Why don't I give you some archery lessons before I leave?", port.image_Trissie);
			dialogues[dialogueSet][10] = new Dialoge("Alder","Yes, please!", port.image_Alder);
			dialogues[dialogueSet][11] = new Dialoge("Trissie","You might need them.", port.image_Trissie);
			dialogues[dialogueSet][12] = new Dialoge("Alder","Huh?", port.image_Alder);
			dialogues[dialogueSet][13] = new Dialoge("Florence","Triss!", port.image_Florence);
			break;
		case 7:
			dialogues[dialogueSet][0] = new Dialoge("Trissie","I'll make this quick!", port.image_Trissie);
			dialogues[dialogueSet][1] = new Dialoge("Trissie","There are Gowl knights patrolling the woods!", port.image_Trissie);
			dialogues[dialogueSet][2] = new Dialoge("Florence","Do they know of the cottage?", port.image_Florence);
			dialogues[dialogueSet][3] = new Dialoge("Kyla","Not unless one of our clients has sold us out.", port.image_Kyla);
			dialogues[dialogueSet][4] = new Dialoge("Kyla","Even so, their search shall come up empty.", port.image_Kyla);
			dialogues[dialogueSet][5] = new Dialoge("Trissie","I don't know.", port.image_Trissie);
			dialogues[dialogueSet][6] = new Dialoge("Trissie","There's quite a few of them and they're setting up camps.", port.image_Trissie);
			dialogues[dialogueSet][7] = new Dialoge("Trissie","They seemed certain that they would find something.", port.image_Trissie);
			dialogues[dialogueSet][8] = new Dialoge("Kyla","My magic is strong.", port.image_Kyla);
			dialogues[dialogueSet][9] = new Dialoge("Kyla","They'll never find this place.", port.image_Kyla);
			dialogues[dialogueSet][10] = new Dialoge("Trissie","I hope so.", port.image_Trissie);
			dialogues[dialogueSet][11] = new Dialoge("Trissie","The woods are full of soldiers, most of whom appear to be new recruits.", port.image_Trissie);
			dialogues[dialogueSet][12] = new Dialoge("Kyla","That proves my point!", port.image_Kyla);
			dialogues[dialogueSet][13] = new Dialoge("Kyla","Whatever they know, they're sending saplings. They can't be very sure about it!", port.image_Kyla);
			dialogues[dialogueSet][14] = new Dialoge("Trissie","Being overseen by several captains apparently ordered by Darrunt himself!", port.image_Trissie);
			dialogues[dialogueSet][15] = new Dialoge("Alder","Who's Darrunt?", port.image_Alder);
			dialogues[dialogueSet][16] = new Dialoge("Florence","He's the Grand Marshal of the Gowls.", port.image_Florence);
			dialogues[dialogueSet][17] = new Dialoge("Florence","He wouldn't think to look this far north in the woods though.", port.image_Florence);
			dialogues[dialogueSet][18] = new Dialoge("Florence","Too barren of humans these days.", port.image_Florence);
			dialogues[dialogueSet][19] = new Dialoge("Kyla","We'll lay low for the next few days, food is plentiful and we have enough water to last us a few days.", port.image_Kyla);
			dialogues[dialogueSet][20] = new Dialoge("Kyla","Thank you for the warning, Trissie dear.", port.image_Kyla);
			dialogues[dialogueSet][21] = new Dialoge("Kyla","But I think we will be fine.", port.image_Kyla);
			dialogues[dialogueSet][22] = new Dialoge("Trissie","If you say so.", port.image_Trissie);
			dialogues[dialogueSet][23] = new Dialoge("Trissie","I think I'll scarper out of these woods for the time being until things quiet down.", port.image_Trissie);
			dialogues[dialogueSet][24] = new Dialoge("Trissie","Alder?", port.image_Trissie);
			dialogues[dialogueSet][25] = new Dialoge("Trissie","I promised you archery lessons?", port.image_Trissie);
			dialogues[dialogueSet][26] = new Dialoge("Alder","Yes!", port.image_Alder);
			dialogues[dialogueSet][27] = new Dialoge("Trissie","Then come meet me outside.", port.image_Trissie);
			break;
		case 8:
			dialogues[dialogueSet][0] = new Dialoge("Alder",".........", port.image_Alder);
			dialogues[dialogueSet][1] = new Dialoge("Alder","................hm?", port.image_Alder);
			dialogues[dialogueSet][2] = new Dialoge("Alder","Where...?", port.image_Alder);
			dialogues[dialogueSet][3] = new Dialoge("Alder","Who's there?", port.image_Alder);
			dialogues[dialogueSet][4] = new Dialoge("Alder","What is this place?", port.image_Alder);
			dialogues[dialogueSet][5] = new Dialoge("One the figures stepped out from the darkness towards Alder.",1);
			dialogues[dialogueSet][6] = new Dialoge("Alder","Uh, h-hello. Nice sword...", port.image_Alder);
			dialogues[dialogueSet][7] = new Dialoge("The mouse walked up to Alder, stopping a short distance in front of him, before digging a hole between them.",1);
			dialogues[dialogueSet][8] = new Dialoge("Alder","What are you digging for- Wha?", port.image_Alder);
			dialogues[dialogueSet][9] = new Dialoge("Alder","Why can't I move!?", port.image_Alder);
			dialogues[dialogueSet][10] = new Dialoge("To Alder's confusion he floated down to the ground and he realised he was in a corparial form.",1); 
			dialogues[dialogueSet][11] = new Dialoge("His body contorted and shrunk until he was small enough to fit in the hole.",1);
			dialogues[dialogueSet][12] = new Dialoge("The mouse looked over Alder.",1);
			dialogues[dialogueSet][13] = new Dialoge("Alder","Wait!", port.image_Alder);
			dialogues[dialogueSet][14] = new Dialoge("Alder","What's happening!?", port.image_Alder);
			dialogues[dialogueSet][15] = new Dialoge("Alder","What are you doing!?", port.image_Alder);
			dialogues[dialogueSet][16] = new Dialoge("Alder was gently set in the pit and before it began refilling with soil.",1);
			dialogues[dialogueSet][17] = new Dialoge("Leaning over the pit the mouse grabbed the handle for a moment Alder thought that the mouse would strike him.",1);
			dialogues[dialogueSet][18] = new Dialoge("Alder","Ahh!", port.image_Alder);
			dialogues[dialogueSet][19] = new Dialoge("The mouse expressed surprise and apologetically conveyed to Alder that he meant no harm.",1);
			dialogues[dialogueSet][20] = new Dialoge("He pulled out the scabbard, sheathed the sword and placed the hilt in with Alder.",1);
			break;
		case 9:
			dialogues[dialogueSet][0] = new Dialoge("Alder took the sword by the grip and to his surprise, it changed size to fit in his hands.",1);
			dialogues[dialogueSet][1] = new Dialoge("???","...",port.image_Agrimus);
			dialogues[dialogueSet][2] = new Dialoge("Alder could see the mouse gently smile before he was submerged in darkness.",1);
			dialogues[dialogueSet][3] = new Dialoge("Dispite not speaking Alder experienced the strange sensation of a voice speaking inside his head.",1);
			dialogues[dialogueSet][4] = new Dialoge("???","Good luck. My Scion.",port.image_Agrimus);
			break;
		case 10:
			dialogues[dialogueSet][0] = new Dialoge("Alder","Mmmm.", port.image_Alder);
			dialogues[dialogueSet][1] = new Dialoge("Alder","Oh!", port.image_Alder);
			dialogues[dialogueSet][2] = new Dialoge("Alder","It was a dream.", port.image_Alder);
			dialogues[dialogueSet][3] = new Dialoge("Alder","...", port.image_Alder);
			dialogues[dialogueSet][4] = new Dialoge("Alder","Huh!?", port.image_Alder);
			dialogues[dialogueSet][5] = new Dialoge("Alder","What is…!?", port.image_Alder);
			dialogues[dialogueSet][6] = new Dialoge("Alder","What!?", port.image_Alder);
			dialogues[dialogueSet][7] = new Dialoge("Alder","How!?", port.image_Alder);
			dialogues[dialogueSet][8] = new Dialoge("Alder","Florence!!", port.image_Alder);
		case 11:
			dialogues[dialogueSet][0] = new Dialoge("Alder","It's called \"Lief\", \"The sword of the seasons\".", port.image_Alder);
			dialogues[dialogueSet][1] = new Dialoge("Kyla","As I thought.", port.image_Kyla);
			dialogues[dialogueSet][2] = new Dialoge("Kyla","But there is only one way to confirm this.", port.image_Kyla);
			dialogues[dialogueSet][3] = new Dialoge("Kyla unsheathed that sword and held it in her hand. The blade darkened and bent and it was not long before it dropped off leaving only the handle.",1);
			dialogues[dialogueSet][4] = new Dialoge("Kyla","Boy you can have the sword back now, make sure you hold it by the grip.", port.image_Kyla);
			dialogues[dialogueSet][5] = new Dialoge("Alder said nothing as he picked up the sword. As he did, a new blade started to grow from the rain-guard until it was back to its original glory.",1);
			dialogues[dialogueSet][6] = new Dialoge("Florence","What did you do?", port.image_Florence);
			dialogues[dialogueSet][7] = new Dialoge("Kyla","The sword only sprouts for the Scion.", port.image_Kyla);
			dialogues[dialogueSet][8] = new Dialoge("Kyla","In order words...", port.image_Kyla);
			dialogues[dialogueSet][9] = new Dialoge("Kyla","You.", port.image_Kyla);
		}
	}

	public void setCutscene(int dialogueSet, int read) {
		this.dialogueSet = dialogueSet;
		int count = 0;
		try {
			InputStream f = getClass().getResourceAsStream("/res/dialogue/Cutscenes.txt");
			BufferedReader b = new BufferedReader(new InputStreamReader(f));

			for (String i = b.readLine(); i != null; i = b.readLine()) {
				String[] line = i.split("\\$ ", 6);
				//Check chapter, switch and part
				if (line[0].equals(Integer.toString(gp.s.chapter)) && line[1].equals(Integer.toString(read))
						&& line[2].equals(Integer.toString(gp.s.part))) {
					//Split dialogue
					String[] newline = line[4].split("£");
					String name = "";
					String text = "";
					
					if (newline.length > 1) {
						name = newline[0];
						text = newline[1];
					} else {
						text = line[4].trim();
					}
					if (name != "") {
						dialogues[dialogueSet][count] = new Dialoge(name, text, port.getImage(name));
						//System.out.println(getImage(name));
					} else {
						dialogues[dialogueSet][count] = new Dialoge(text, 1);
					}
					count++;
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public void nextDialogue(String currentDialogue) {
		Dialoge[][] dia = dialogues;
		if (dia[dialogueIndex] == null) {
			dialogueIndex = 0;
		}

		dialogueIndex++;
	}

	public void cutsceneDialog(String text) {
		if (gp.keyH.enterPressed) {
			gp.ui.currentDialogue = text;
		}

		gp.keyH.enterPressed = false;
	}
	
	public BufferedImage getImage(String name) {
		switch(name) {
		case "Alder": return port.image_Alder;
		case "Florence": return port.image_Florence;
		case "Dilecto": return port.image_Dilecto;
		case "Thay": return port.image_Thay;
		case "Dean": return port.image_Dean;
		case "Ralph": return port.image_Ralph;
		case "Plumm": return port.image_Plumm;
		case "Kyla": return port.image_Kyla;
		case "Trissie": return port.image_Trissie;
		case "Agrimus": return port.image_Agrimus;
		default : return null;
		}
	}

	public void setSprites(int spriteSet) {
		//setImages();
		switch(spriteSet) {
		case 9:
			sprites[9][0] = port.image_Alder;
			sprites[9][1] = port.image_Alder;
			sprites[9][2] = port.image_Alder;
			sprites[9][3] = port.image_Alder;
			sprites[9][4] = port.image_Alder;
			sprites[9][5] = port.image_Agrimus;
			sprites[9][6] = port.image_Alder;
			sprites[9][7] = port.image_Agrimus;
			sprites[9][8] = port.image_Alder;
			sprites[9][9] = port.image_Alder;
			sprites[9][10] = port.image_Alder;
			sprites[9][11] = port.image_Alder;
			sprites[9][12] = port.image_Agrimus;
			sprites[9][13] = port.image_Alder;
			sprites[9][14] = port.image_Alder;
			sprites[9][15] = port.image_Alder;
			sprites[9][17] = port.image_Agrimus;
			sprites[9][18] = port.image_Alder;
			sprites[9][19] = port.image_Agrimus;
			sprites[9][20] = port.image_Agrimus;
			break;
		case 10:
			sprites[10][2] = port.image_Agrimus;
			sprites[10][5] = port.image_Agrimus;
			break;
		}
	}
}