package wildwyrd.game.combat;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.object.Dialoge;

public class En_Wasp extends Enemy {
	public En_Wasp(GamePanel gp) {
		super(gp,"Wasp",  19, 10, 5, 5, 90, 40, 45);
		//imageUrl = "wasp";
        String desc = "More hostile than usual this year.";
		type = EntityType.Sprite;
		//setDialogue();
	}
	
	public void setAction() {
		gp.combat.dealDamage(this, gp.playable.get(0), getAttack());
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("" ,1);
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
	
	public void defeated() {
		
	}
}
