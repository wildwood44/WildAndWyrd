package wildwyrd.game.combat;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.items.Itm_Bug_Meat;
import wildwyrd.game.npc.NPC_Florence;
import wildwyrd.game.npc.NPC_Thay;
import wildwyrd.game.object.Dialoge;

public class En_Wasp extends Enemy {
	public En_Wasp(GamePanel gp) {
		super(gp,"Wasp", 19, 10, 5, 5, 90, 40, 45);
        desc = "More hostile than usual this year.";
		type = EntityType.Sprite;
	}
	
	public void setAction() {
		gp.combat.dealDamage(this, gp.playable.get(0), getAttack());
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("" ,1);
		dialogues[1][0] = new Dialoge("Alder","Did I anger them?",port.image_Alder);
		dialogues[1][1] = new Dialoge("Alder was puzzled by the attack but regardless it was time to return to Florence.",1);
		dialogues[5][0] = new Dialoge("",1);
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
	
	public void combatResult() {
		gp.gameState = GameState.playState;
		gp.s.c1Switch[3] = false;
		for (int i = 0; i < gp.npc[2].length; i++) {
			if(gp.npc[2][i] != null && gp.npc[2][i].name == NPC_Thay.npcName) {
				gp.npc[2][i].worldX = gp.tileSize * 12;
				gp.npc[2][i].direction = "left";
				break;
			}
		}
		for (int i = 0; i < gp.npc[2].length; i++) {
			if(gp.npc[2][i] != null && gp.npc[2][i].name == NPC_Florence.npcName) {
				gp.npc[2][i].direction = "right";
				break;
			}
		}
		startDialogue(this,1);
	}
}
