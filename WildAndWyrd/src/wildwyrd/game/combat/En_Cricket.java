package wildwyrd.game.combat;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.object.Dialoge;

public class En_Cricket extends Enemy {
	Entity cricket = gp.npc[3][0];
	public En_Cricket(GamePanel gp) {
		super(gp,"Cricket",  20, 10, 0, 8, 90, 10, 23);
        String desc = "Big grasshoppers with long antenna. Can jump a fair distance.";
		type = EntityType.Sprite;
		//speed = 1;
		//setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(name + " stood wary." ,1);
		dialogues[1][0] = new Dialoge(name + " fled." ,1);
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
			if(gp.playable.get(0).getCombatStatus() == CombatStatus.Attacking) {
				startDialogue(this, 1);
				setCombatStatus(CombatStatus.Escaping);
				gp.combat.win = false;
				//gp.combat.endCombat();
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
		cricket.combatResponce();
	}
}
