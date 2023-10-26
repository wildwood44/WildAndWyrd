package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.combat.En_Dummy;
import wildwyrd.game.items.Itm_Bow;
import wildwyrd.game.items.Itm_Primative_Arrow;

public class Obj_Dummy extends Entity {
	GamePanel gp;
	public static final int objId = 25;
	public static final String objName = "Dummy";

	public Obj_Dummy(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/img_dummy",gp.tileSize,gp.tileSize);
		setDialogue();
		getImage(image);
	}

	public void setDialogue() {
	}

	public void interact() {
		gp.player.pickUpObject(new Itm_Primative_Arrow(gp), 5);
		//gp.player.pickUpObject(new Itm_Primative_Arrow(gp));
		//gp.player.pickUpObject(new Itm_Primative_Arrow(gp));
		//gp.player.pickUpObject(new Itm_Primative_Arrow(gp));
		//gp.player.pickUpObject(new Itm_Primative_Arrow(gp));
		gp.playable.get(0).setWeapon_second(new Itm_Bow(gp));
		gp.combat.addEnemy(new En_Dummy(gp));
		gp.combat.startCombat();
	}
}