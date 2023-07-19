package wildwyrd.game.combat;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.object.Dialoge;

public class En_Cricket extends Enemy {
	public En_Cricket(GamePanel gp) {
		super(gp,"Cricket",  20, 10, 0, 8, 90, 10, 23);
        String desc = "Big grasshoppers with long antenna. Can jump a fair distance.";
		type = EntityType.Sprite;
		//speed = 1;
		//setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(name + " stood wary." ,1);
		dialogues[1][0] = new Dialoge("Hunt succesful.",1);
		dialogues[1][1] = new Dialoge("Bzz!",1);
		dialogues[1][2] = new Dialoge("As Alder collected the slain cricket, a loud buzzing came at him from his side. Two hornets came at him.",1);
		dialogues[1][3] = new Dialoge("Ahhh!",1);
		dialogues[2][0] = new Dialoge("Come back you!",1);
		dialogues[2][1] = new Dialoge("Alder tried in vain to get the cricket, but it had already jumped out of reach.",1);
		dialogues[2][2] = new Dialoge("Bzz!",1);
		dialogues[2][3] = new Dialoge("The loud buzzing of insect wings came from Alder's side. Two hornets came at him.",1);
		dialogues[2][4] = new Dialoge("Ahhh!",1);
	}

	public void getImage() {
		image = setup("/res/sprite/combat/cricket",gp.tileSize*6,gp.tileSize*2);
	}
	
	public void action() {
		if(health <= 0) {
			gp.combat.enemyDeath(this);
		} else {
			gp.ui.choiceSlot = 0;
			gp.ui.firstValue = 0;
			gp.keyH.enterPressed = false;
			startDialogue(this, 0);
		}
	}
	
	public void defeated() {
		gp.keyH.enterPressed = false;
		startDialogue(this, 1);
	}
	
	public void combatResult() {
		gp.combat.addEnemy(new En_Wasp(gp), new En_Wasp(gp));
		gp.combat.startCombat();
	}
}
