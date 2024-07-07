package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.combat.Combat;
import wildwyrd.game.combat.En_Dummy;

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
		if(!destroy && gp.playable.get(0).getWeapon_second().name != null) {
			//gp.combat.addEnemy(new En_Dummy(gp));
			//gp.combat.startCombat();
			enemies.add(new En_Dummy(gp));
			gp.combat = new Combat(gp, false, enemies);
		}
	}
}