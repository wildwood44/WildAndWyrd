package wildwyrd.game.combat;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.object.Dialoge;
import wildwyrd.game.object.Obj_Dummy;

public class En_Dummy extends Enemy {
	public En_Dummy(GamePanel gp) {
		super(gp,"Dummy",  19, 5, 0, 150, 0, 0, 0);
        desc = "Durable but falling apart.";
		type = EntityType.Sprite;
		inRear = true;
	}
	
	public void setAction() {
		gp.combat.dealDamage(this, gp.playable.get(0), getAttack());
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("To use a ranged weapon like the bow select a supported projectile from items." ,1);
		dialogues[0][1] = new Dialoge("Now select attack to fire.",1);
		dialogues[1][0] = new Dialoge("Nice shot!", 1);
	}

	public void getImage() {
		image = setup("/res/sprite/combat/dummy",gp.tileSize*6,gp.tileSize*2);
	}
	
	public void action() {
		if(health <= 0) {
			gp.combat.enemyDeath(this);
		} else {
			System.out.println(gp.combat.getTurn());
			if(gp.combat.getTurn() > 5) {
				startDialogue(this, 0);
			}
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
		for (int i = 0; i < gp.obj[gp.currentMap.getId()].length; ++i) {
			if(gp.obj[gp.currentMap.getId()][i] != null && gp.obj[gp.currentMap.getId()][i].id == Obj_Dummy.objId) {
				System.out.println("Destroyed");
				gp.obj[gp.currentMap.getId()][i].destroy = true;
				gp.s.c2Switch[2] = false;
				gp.obj[gp.currentMap.getId()][i] = null;
			}
		}
		//startDialogue(this,1);
	}
}
