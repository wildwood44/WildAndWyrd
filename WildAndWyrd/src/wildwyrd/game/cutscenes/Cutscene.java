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
			dialogues[dialogueSet][13] = new Dialoge("Florence","Oh, Triss! Come in.", port.image_Florence);
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
			break;
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
			break;
		case 12:
			dialogues[dialogueSet][0] = new Dialoge("???","The rabbit seeks his burrow!",1);
			dialogues[dialogueSet][1] = new Dialoge("*Click*",1);
			dialogues[dialogueSet][2] = new Dialoge("*Ring*!",1);
			dialogues[dialogueSet][3] = new Dialoge("The door unlocked and the bell chimed in responce to the words of the voice outside. The ringing resounded throught the cottage but was prevelant in the already alerted living room.",1);
			dialogues[dialogueSet][4] = new Dialoge("Alder, Florence and Kyla","...!?",port.image_Alder);
			dialogues[dialogueSet][5] = new Dialoge("Florence","Who is that!?",port.image_Florence);
			dialogues[dialogueSet][6] = new Dialoge("Alder","Look!",port.image_Alder);
			dialogues[dialogueSet][7] = new Dialoge("Alder","There's someone outside!",port.image_Alder);
			dialogues[dialogueSet][8] = new Dialoge("Kyla","Hm?!", port.image_Kyla);
			dialogues[dialogueSet][9] = new Dialoge("Kyla","That armour!", port.image_Kyla);
			dialogues[dialogueSet][10] = new Dialoge("Kyla","Gowls!", port.image_Kyla);
			dialogues[dialogueSet][11] = new Dialoge("Florence","Two- No. Four creatures.", port.image_Florence);
			dialogues[dialogueSet][12] = new Dialoge("Florence","I'm certain at least one of them is a stoat or a weasel.", port.image_Florence);
			dialogues[dialogueSet][13] = new Dialoge("Rat","I heard a bell!", port.image_Gowl_Rat);
			dialogues[dialogueSet][14] = new Dialoge("Weasel","You certain?",port.image_Gowl_Weasel);
			dialogues[dialogueSet][15] = new Dialoge("Weasel","I don't see anything different about this rock.",port.image_Gowl_Weasel);
			dialogues[dialogueSet][16] = new Dialoge("Rat","There are human tracks all over, both old and new.",port.image_Gowl_Rat);
			dialogues[dialogueSet][17] = new Dialoge("Rat","There here!",port.image_Gowl_Rat);
			dialogues[dialogueSet][18] = new Dialoge("Weasel","Wait here, the rest of you form a perimeter.",port.image_Gowl_Weasel);
			dialogues[dialogueSet][19] = new Dialoge("Weasel","I'll fetch the a wizard.",port.image_Gowl_Weasel);
			dialogues[dialogueSet][20] = new Dialoge("Kyla","They know we're here we have to move.", port.image_Kyla);
			dialogues[dialogueSet][21] = new Dialoge("Florence","I agree. But where?", port.image_Florence);
			dialogues[dialogueSet][22] = new Dialoge("Kyla","Hmm.", port.image_Kyla);
			dialogues[dialogueSet][23] = new Dialoge("Kyla","The boy can go to Forton. With that sword they're sure to take him in.", port.image_Kyla);
			dialogues[dialogueSet][24] = new Dialoge("Kyla","It will benefit us all if the Scion can represent the plight of Marchland humans.", port.image_Kyla);
			dialogues[dialogueSet][25] = new Dialoge("Florence","By himself?", port.image_Florence);
			dialogues[dialogueSet][26] = new Dialoge("Florence","Shouldn't we go with him?", port.image_Florence);
			dialogues[dialogueSet][27] = new Dialoge("Kyla","I'm not stepping a foot in another town.", port.image_Kyla);
			dialogues[dialogueSet][28] = new Dialoge("Kyla","The boy can go by himself.", port.image_Kyla);
			dialogues[dialogueSet][29] = new Dialoge("Florence","Absolutely not!", port.image_Florence);
			dialogues[dialogueSet][30] = new Dialoge("Florence","He'll be killed!", port.image_Florence);
			dialogues[dialogueSet][31] = new Dialoge("Kyla","They are not going to kill him.", port.image_Kyla);
			dialogues[dialogueSet][32] = new Dialoge("Kyla","He's the Scion.", port.image_Kyla);
			dialogues[dialogueSet][33] = new Dialoge("Florence","What about the Gowls?!", port.image_Florence);
			dialogues[dialogueSet][34] = new Dialoge("Kyla","Again. Scion!", port.image_Kyla);
			dialogues[dialogueSet][35] = new Dialoge("Florence","Again! Gowls!", port.image_Florence);
			dialogues[dialogueSet][36] = new Dialoge("Kyla","Well we can't go with him!", port.image_Kyla);
			dialogues[dialogueSet][37] = new Dialoge("Florence","I will!", port.image_Florence);
			dialogues[dialogueSet][38] = new Dialoge("Kyla","...What?", port.image_Kyla);
			dialogues[dialogueSet][39] = new Dialoge("Florence","I'll go with him as his guardian!", port.image_Florence);
			dialogues[dialogueSet][40] = new Dialoge("Kyla","Absolutely not!", port.image_Kyla);
			dialogues[dialogueSet][41] = new Dialoge("Kyla","You will come with me!", port.image_Kyla);
			dialogues[dialogueSet][42] = new Dialoge("Florence","I am not leaving Alder alone.", port.image_Florence);
			dialogues[dialogueSet][43] = new Dialoge("Kyla","So you would choose our servant over the women who raised, taught and loved you over these long years?", port.image_Kyla);
			dialogues[dialogueSet][44] = new Dialoge("Florence","...", port.image_Florence);
			dialogues[dialogueSet][45] = new Dialoge("Kyla","*Groan*", port.image_Kyla);
			dialogues[dialogueSet][46] = new Dialoge("Kyla","We'll discuss this later, we need to get moving.", port.image_Kyla);
			dialogues[dialogueSet][47] = new Dialoge("Kyla","Tawie will have to make her own way.", port.image_Kyla);
			break;
		case 13:
			dialogues[dialogueSet][0] = new Dialoge("Rat","Who goes there!?",port.image_Gowl_Rat);
			dialogues[dialogueSet][1] = new Dialoge("Alder","...!",port.image_Alder);
			dialogues[dialogueSet][2] = new Dialoge("Rat","Don't Move!!",port.image_Gowl_Rat);
			dialogues[dialogueSet][3] = new Dialoge("Weasel","Please sir. I have nothing of value!",port.image_Jeb);
			dialogues[dialogueSet][4] = new Dialoge("Rat","We'll see about that.",port.image_Gowl_Rat);
			dialogues[dialogueSet][5] = new Dialoge("Rat","Show me what's in your bag.",port.image_Gowl_Rat);
			dialogues[dialogueSet][6] = new Dialoge("Weasel","Only cloth and my traveling supplies sir, I am just a penniless merchant.",port.image_Jeb);
			dialogues[dialogueSet][7] = new Dialoge("The rat took the sack and dug through it, keeping his eyes on the weasel.",1);
			dialogues[dialogueSet][8] = new Dialoge("Rat","A merchant?",port.image_Gowl_Rat);
			dialogues[dialogueSet][9] = new Dialoge("Rat","What are you doing out here?",port.image_Gowl_Rat);
			dialogues[dialogueSet][10] = new Dialoge("Rat","The Gowl knights have been tasked with finding spies in this wood.",port.image_Gowl_Rat);
			dialogues[dialogueSet][11] = new Dialoge("Rat","What brings a merchant into here?",port.image_Gowl_Rat);
			dialogues[dialogueSet][12] = new Dialoge("Rat","You're not trying to make a deal with them, are you?",port.image_Gowl_Rat);
			dialogues[dialogueSet][13] = new Dialoge("Weasel","No Sir!",port.image_Jeb);
			dialogues[dialogueSet][14] = new Dialoge("Weasel","I was just passing north, but I got lost.",port.image_Jeb);
			dialogues[dialogueSet][15] = new Dialoge("The rat pulled out a large cloak from the sack.",1);
			dialogues[dialogueSet][16] = new Dialoge("Rat","Oh, lost are we!",port.image_Gowl_Rat);
			dialogues[dialogueSet][17] = new Dialoge("Rat","What's with man-sized cloak?",port.image_Gowl_Rat);
			dialogues[dialogueSet][18] = new Dialoge("Rat","I think you can wait right there...",port.image_Gowl_Rat);
			dialogues[dialogueSet][19] = new Dialoge("Weasel","Wait!",port.image_Jeb);
			dialogues[dialogueSet][20] = new Dialoge("Weasel","Let me explain!",port.image_Jeb);
			dialogues[dialogueSet][21] = new Dialoge("Alder thought the rat was going to kill the weasel. Until...",1);
			dialogues[dialogueSet][22] = new Dialoge("Rat","Aagh!!",port.image_Gowl_Rat);
			dialogues[dialogueSet][23] = new Dialoge("Kyla","Come along!",port.image_Kyla);
			dialogues[dialogueSet][24] = new Dialoge("Kyla","Well?",port.image_Kyla);
			dialogues[dialogueSet][25] = new Dialoge("Kyla","What do you want?",port.image_Kyla);
			dialogues[dialogueSet][26] = new Dialoge("Kyla","Who are you?",port.image_Kyla);
			dialogues[dialogueSet][27] = new Dialoge("Weasel","Uh. *Ahem!*",port.image_Jeb);
			dialogues[dialogueSet][28] = new Dialoge("Jeb","M-My name is J-Jeb M-Ma'am.",port.image_Jeb);
			dialogues[dialogueSet][29] = new Dialoge("Jeb","I-I am a merchant.",port.image_Jeb);
			dialogues[dialogueSet][30] = new Dialoge("Jeb","I h-heard that there was a w-witch in these woods who might be in need of p-provisions to escape from the Gowls.",port.image_Jeb);
			dialogues[dialogueSet][31] = new Dialoge("Jeb","I-In exchange for something magical perhaps?",port.image_Jeb);
			dialogues[dialogueSet][32] = new Dialoge("Kyla","Hmmm.",port.image_Kyla);
			dialogues[dialogueSet][33] = new Dialoge("Kyla","I was going to throw you out but you may have brought us exactly what we needed.",port.image_Kyla);
			dialogues[dialogueSet][34] = new Dialoge("Kyla","Florence, take what you and the boy want.",port.image_Kyla);
			dialogues[dialogueSet][35] = new Dialoge("Kyla","I have an idea for his payment.",port.image_Kyla);
			dialogues[dialogueSet][36] = new Dialoge("Kyla","This vial contains golden dip. It can turn objects into pure gold; like so.",port.image_Kyla);
			dialogues[dialogueSet][37] = new Dialoge("Kyla took out a bowl and poured the contents of one of the vials into it. She then took a wooden spoon and dropped it in.",1);
			dialogues[dialogueSet][38] = new Dialoge("The liquid began subtley glowing and its volume dropped until only the once wooden spoon, now made of solid gold, remained. Jeb examined it greedily and Kyla held up the last two vials.",1);
			dialogues[dialogueSet][39] = new Dialoge("Kyla","I'll give you one in exchange for the cloaks.",port.image_Kyla);
			dialogues[dialogueSet][40] = new Dialoge("Jeb","Absolutely Ma'am!",port.image_Jeb);
			dialogues[dialogueSet][41] = new Dialoge("Jeb","You can have the rest of my provisions for the other!",port.image_Jeb);
			dialogues[dialogueSet][42] = new Dialoge("Kyla","No, that's what these coins are for.",port.image_Kyla);
			dialogues[dialogueSet][43] = new Dialoge("Kyla jangled the bag of coins and passed it to Alder.",1);
			dialogues[dialogueSet][44] = new Dialoge("Kyla","The other is for you to get this boy to Forton.",port.image_Kyla);
			dialogues[dialogueSet][45] = new Dialoge("Jeb","But, Ma'am.",port.image_Jeb);
			dialogues[dialogueSet][46] = new Dialoge("Jeb","The Gowls are all over in that direction, with a human they will almost certainly arrest me.",port.image_Jeb);
			dialogues[dialogueSet][47] = new Dialoge("Jeb","Even if we get to Forton, the mice propably won't be too eager to have him around.",port.image_Jeb);
			dialogues[dialogueSet][48] = new Dialoge("Kyla picks up Alder's sword by the scabbard and hands it to Jeb. He is confused at first but he assumes it is additional payment.",1);
			dialogues[dialogueSet][49] = new Dialoge("Jeb","No matter what you pay me that does not change the peril I would face if I took your request.",port.image_Jeb);
			dialogues[dialogueSet][50] = new Dialoge("Kyla","Draw it.",port.image_Kyla);
			dialogues[dialogueSet][51] = new Dialoge("Jeb does what he is asked and to his surprise the blade withers and falls off the hilt.",1);
			dialogues[dialogueSet][52] = new Dialoge("Kyla","Now pass it to the boy.",port.image_Kyla);
			dialogues[dialogueSet][53] = new Dialoge("Jeb now even more wide-eyed did as he was told and watched as a new blade grew from the hilt when Alder took a grip of it.",1);
			dialogues[dialogueSet][54] = new Dialoge("Jeb","Child...",port.image_Jeb);
			dialogues[dialogueSet][55] = new Dialoge("Jeb","You are the Scion!",port.image_Jeb);
			dialogues[dialogueSet][56] = new Dialoge("Jeb","...We might be able to get through.",port.image_Jeb);
			dialogues[dialogueSet][57] = new Dialoge("Jeb","But we will have to sneak around them.",port.image_Jeb);
			dialogues[dialogueSet][58] = new Dialoge("Jeb","Better safe than sorry.",port.image_Jeb);
			dialogues[dialogueSet][59] = new Dialoge("Kyla","I can create a diversion.",port.image_Kyla);
			dialogues[dialogueSet][60] = new Dialoge("Kyla","I can handle them and get away easily considering the competence of their soldiers this far north.",port.image_Kyla);
			dialogues[dialogueSet][61] = new Dialoge("Kyla","You two, buy what you need.",port.image_Kyla);
			dialogues[dialogueSet][62] = new Dialoge("Kyla","We leave when this sorcerer gets here.",port.image_Kyla);
			break;
		case 14:
			dialogues[dialogueSet][0] = new Dialoge("The sun was now setting. Outside the cottage within the dark shadows of the trees, the paw steps of the Gowls knights could be heard approaching. Alder was the first to notice.",1);
			dialogues[dialogueSet][1] = new Dialoge("Alder","They're here!",port.image_Alder);
			dialogues[dialogueSet][2] = new Dialoge("Florence","Then it's time to go.",port.image_Florence);
			dialogues[dialogueSet][3] = new Dialoge("Kyla","Are you sure that's them?",port.image_Kyla);
			dialogues[dialogueSet][4] = new Dialoge("Jeb","Must be.",port.image_Jeb);
			dialogues[dialogueSet][5] = new Dialoge("Jeb","I think I can see... three... no, four of them.",port.image_Jeb);
			dialogues[dialogueSet][6] = new Dialoge("Jeb and Florence looked concerned, Alder consciously stayed silent but Kyla started smirking, soon erupting into laughter.",1);
			dialogues[dialogueSet][7] = new Dialoge("Kyla","Hee hee.",port.image_Kyla);
			dialogues[dialogueSet][8] = new Dialoge("Kyla","Hee hee Ha HA!",port.image_Kyla);
			dialogues[dialogueSet][9] = new Dialoge("Kyla","That's it!",port.image_Kyla);
			dialogues[dialogueSet][10] = new Dialoge("Kyla","They must know not that it is I they seek!",port.image_Kyla);
			dialogues[dialogueSet][11] = new Dialoge("Kyla","Hee hee ha!",port.image_Kyla);
			dialogues[dialogueSet][12] = new Dialoge("Kyla","This will be easier than I expected!",port.image_Kyla);
			dialogues[dialogueSet][13] = new Dialoge("Kyla","...Florence.",port.image_Kyla);
			dialogues[dialogueSet][14] = new Dialoge("Kyla","Are you truly sure you want to leave with the boy?",port.image_Kyla);
			dialogues[dialogueSet][15] = new Dialoge("Florence","Yes.",port.image_Florence);
			dialogues[dialogueSet][16] = new Dialoge("Kyla","It won't be easy living in Forton.",port.image_Kyla);
			dialogues[dialogueSet][17] = new Dialoge("Kyla","The mice may not look at you favourably.",port.image_Kyla);
			dialogues[dialogueSet][18] = new Dialoge("Florence","I can live with that.",port.image_Florence);
			dialogues[dialogueSet][19] = new Dialoge("Kyla","...Fine then. Go.",port.image_Kyla);
			dialogues[dialogueSet][20] = new Dialoge("Kyla","It is time.",port.image_Kyla);
			dialogues[dialogueSet][21] = new Dialoge("Kyla","Everyone into the undergrowth.",port.image_Kyla);
			dialogues[dialogueSet][22] = new Dialoge("Kyla","I'll distract them until morning.",port.image_Kyla);
			dialogues[dialogueSet][23] = new Dialoge("Kyla","Keep out of sight until you're far from the cottage. And be wary of reinforcements within the woods!",port.image_Kyla);
			dialogues[dialogueSet][24] = new Dialoge("Weasel","This is the place.",port.image_Gowl_Weasel);
			dialogues[dialogueSet][25] = new Dialoge("Rat","Where's Midge?",port.image_Gowl_Rat);
			dialogues[dialogueSet][26] = new Dialoge("Weasel","He was here.",port.image_Gowl_Weasel);
			dialogues[dialogueSet][27] = new Dialoge("Weasel","Midge!",port.image_Gowl_Weasel);
			dialogues[dialogueSet][28] = new Dialoge("Weasel","MIDGE!!",port.image_Gowl_Weasel);
			dialogues[dialogueSet][29] = new Dialoge("Sorcerer Fox","There might very well be something here.",port.image_Gowl_Fox_Wiz);
			dialogues[dialogueSet][30] = new Dialoge("Sorcerer Fox","There are illusions on this rock.",port.image_Gowl_Fox_Wiz);
			dialogues[dialogueSet][31] = new Dialoge("Sorcerer Fox","I'm dispelling them.",port.image_Gowl_Fox_Wiz);
			dialogues[dialogueSet][32] = new Dialoge("Sorcerer Fox","Get ready!",port.image_Gowl_Fox_Wiz);
			dialogues[dialogueSet][33] = new Dialoge("The sorcerer chanted and tapped the illusion with his staff.",port.image_Gowl_Fox_Wiz);
			dialogues[dialogueSet][34] = new Dialoge("The stone cracked glass and fell apart revealing the cottage in its true form to the world. As soon as it fell, the fox tumbled backwards, a bright light enveloping him.",1);
			dialogues[dialogueSet][35] = new Dialoge("Sorcerer Fox","Aah!",port.image_Gowl_Fox_Wiz);
			dialogues[dialogueSet][36] = new Dialoge("In front of the shocked knights was Kyla, lightning covered her hands and she slammed it into the ground. As the escapees fled their home they could hear the screams of their would-be hunters.",1);
			dialogues[dialogueSet][37] = new Dialoge("Kyla","Hee hee Ha HA!",port.image_Kyla);
			dialogues[dialogueSet][38] = new Dialoge("Alder, Florence and Jeb donned their enchanted cloaks and moved to hide in a dense bush, dsappearing into the night.",1);
			break;
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