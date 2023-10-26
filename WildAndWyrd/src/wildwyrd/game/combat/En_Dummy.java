package wildwyrd.game.combat;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.items.Itm_Bug_Meat;
import wildwyrd.game.object.Dialoge;

public class En_Dummy extends Enemy {
	public En_Dummy(GamePanel gp) {
		super(gp,"Dummy",  19, 5, 0, 150, 0, 0, 0);
        String desc = "Durable but falling apart.";
		type = EntityType.Sprite;
		inRear = true;
	}
	
	public void setAction() {
		gp.combat.dealDamage(this, gp.playable.get(0), getAttack());
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("To use a ranged weapon like the bow select a supported projectile from items." ,1);
		dialogues[1][0] = new Dialoge("Now select attack to fire.",1);
	}

	public void getImage() {
		image = setup("/res/sprite/combat/dummy",gp.tileSize*6,gp.tileSize*2);
	}
	
	public void action() {
		if(health <= 0) {
			gp.combat.enemyDeath(this);
		} else {
			gp.combat.incrementTurn();
		}
	}
	
	public void checkDrop() {
		//int i = new Random().nextInt(100)+1;
		//dropItem(new Itm_Bug_Meat(gp));
	}
	
	public void defeated() {
		gp.gameState = GameState.rewardState;
	}
	
	public void combatResult() {
		gp.gameState = GameState.playState;
		//startDialogue(this,1);
	}
}
