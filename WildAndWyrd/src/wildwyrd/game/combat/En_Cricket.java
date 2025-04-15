package wildwyrd.game.combat;

import java.util.Collections;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.object.Dialoge;

public class En_Cricket extends Enemy {
	Entity cricket = gp.npc[3][0];
	public En_Cricket(GamePanel gp) {
		super(gp,"Cricket",  20, 10, 0, 8, 90, 10, 23);
        desc = "Big grasshoppers with long antenna. Can jump a fair distance.";
		type = EntityType.Sprite;
		Collections.addAll(enemies, new En_Wasp(gp), new En_Wasp(gp));
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(name + " stood wary." ,1);
		dialogues[1][0] = new Dialoge(name + " fled." ,1);
		dialogues[2][0] = new Dialoge("Hunt succesful.",1);
		dialogues[2][1] = new Dialoge("Wasp","Bzz!",1);
		dialogues[2][2] = new Dialoge("As Alder collected the slain cricket, a loud buzzing came at him from his side. Two hornets came at him.",1);
		dialogues[2][3] = new Dialoge("Alder","Ahhh!",port.image_Alder);
		dialogues[2][4] = new Dialoge("",4);
		dialogues[3][0] = new Dialoge("Alder","No, come back!",port.image_Alder);
		dialogues[3][1] = new Dialoge("Alder tried in vain to get the cricket, but it had already jumped out of reach.",1);
		dialogues[3][2] = new Dialoge("Wasp","Bzz!",1);
		dialogues[3][3] = new Dialoge("The loud buzzing of insect wings came from Alder's side. Two wasps came at him.",1);
		dialogues[3][4] = new Dialoge("Alder","Ahhh!",port.image_Alder);
		dialogues[3][5] = new Dialoge("",4);
		dialogues[5][0] = new Dialoge("",1);
	}

	public void getImage() {
		image = setup("/res/sprite/combat/cricket",gp.tileSize*6,gp.tileSize*2);
	}
	
	public void action() {
		if(health <= 0 || !isAlive()) {
			gp.combat.enemyDeath(this);
		} else {
			gp.ui.choiceSlot = 0;
			gp.ui.firstValue = 0;
			gp.keyH.enterPressed = false;
			if(gp.playable.get(0).getCombatStatus() == CombatStatus.Attacking) {
				startDialogue(this, 1);
				setCombatStatus(CombatStatus.Escaping);
				gp.combat.cr.addFrame(getCombatStatus(), id, this);
				gp.combat.win = false;
			} else {
				startDialogue(this, 0);
			}
		}
	}
	
	public void defeated() {
		if(health <= 0) {
			gp.combat.win = true;
		} else {
			gp.combat.win = false;
		}
		combatResult();
	}
	
	public void combatResult() {
		gp.combat.cleanup();
		if(cricket == null) {
			cricket = gp.npc[3][0];
		}
		System.out.println(cricket);
		cricket.combatResponce();
	}
}
