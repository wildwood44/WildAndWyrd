package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
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
		name = objName;
		type = EntityType.Object;
		collision = true;

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/objects/img_dummy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setDialogue();
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