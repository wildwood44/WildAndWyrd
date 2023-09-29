package wildwyrd.game.combat;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.items.Itm_Bug_Meat;
import wildwyrd.game.object.Dialoge;

public class En_Wasp extends Enemy {
	public En_Wasp(GamePanel gp) {
		super(gp,"Wasp",  19, 10, 5, 5, 90, 40, 45);
        String desc = "More hostile than usual this year.";
		type = EntityType.Sprite;
	}
	
	public void setAction() {
		gp.combat.dealDamage(this, gp.playable.get(0), getAttack());
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("" ,1);
		dialogues[1][0] = new Dialoge("Did I anger them?",1);
		dialogues[1][1] = new Dialoge("Alder was puzzled by the attack but regardless it was time to return to Florence.",1);
	}

	public void getImage() {
		image = setup("/res/sprite/combat/wasp",gp.tileSize*6,gp.tileSize*2);
	}
	
	public void action() {
		if(health <= 0) {
			gp.combat.enemyDeath(this);
		} else {
			gp.combat.dealDamage(this, gp.playable.get(0), getAttack());
		}
	}
	
	public void checkDrop() {
		//int i = new Random().nextInt(100)+1;
		dropItem(new Itm_Bug_Meat(gp));
	}
	
	public void defeated() {
		gp.gameState = GameState.rewardState;
	}
	
	public void combatResult() {
		gp.gameState = GameState.playState;
		gp.s.c1Switch[3] = false;
		startDialogue(this,1);
	}
}
