package wildwyrd.game;

import wildwyrd.game.object.Obj_Basin;
import wildwyrd.game.object.Obj_Cupboard;
import wildwyrd.game.object.Obj_Kitchen_Window;
import wildwyrd.game.object.Obj_Larder;
import wildwyrd.game.object.Obj_Oven;
import wildwyrd.game.object.Obj_Pots;
import wildwyrd.game.object.Obj_Table_Left;
import wildwyrd.game.object.Obj_Table_Right;
import wildwyrd.game.object.Obj_Wooden_Bowl;

public class EntityGenerator {

	GamePanel gp;
	public EntityGenerator(GamePanel gp) {
		this.gp = gp;
	}
	
	public Entity getObject(int itemId) {
		Entity obj = null;
		switch(itemId) {
		case Obj_Wooden_Bowl.objId: obj = new Obj_Wooden_Bowl(gp);break;
		case Obj_Table_Left.objId: obj = new Obj_Table_Left(gp);break;
		case Obj_Table_Right.objId: obj = new Obj_Table_Right(gp);break;
		case Obj_Oven.objId: obj = new Obj_Oven(gp);break;
		case Obj_Pots.objId: obj = new Obj_Pots(gp);break;
		case Obj_Cupboard.objId: obj = new Obj_Cupboard(gp);break;
		case Obj_Larder.objId: obj = new Obj_Larder(gp);break;
		case Obj_Kitchen_Window.objId: obj = new Obj_Kitchen_Window(gp);break;
		case Obj_Basin.objId: obj = new Obj_Basin(gp);break;
		}
		return obj;
	}
}
