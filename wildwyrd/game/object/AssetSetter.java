package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Itm_Hazelnut;
import wildwyrd.game.rooms.Rm_Blank;
import wildwyrd.game.rooms.Rm_Kitchen;

public class AssetSetter {
	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setRooms() {
		this.gp.rm[0] = new Rm_Blank(this.gp);
		this.gp.rm[1] = new Rm_Kitchen(this.gp);
	}

	public void setObject() {
		this.gp.obj[1][0] = new Obj_Wooden_Bowl(this.gp, new Itm_Hazelnut(this.gp));
		Entity var10000 = this.gp.obj[1][0];
		this.gp.getClass();
		var10000.worldX = 4 * 64;
		var10000 = this.gp.obj[1][0];
		this.gp.getClass();
		var10000.worldY = 6 * 64;
		this.gp.obj[1][1] = new Obj_Table_Left(this.gp);
		var10000 = this.gp.obj[1][1];
		this.gp.getClass();
		var10000.worldX = 3 * 64;
		var10000 = this.gp.obj[1][1];
		this.gp.getClass();
		var10000.worldY = 6 * 64;
		this.gp.obj[1][2] = new Obj_Table_Right(this.gp);
		var10000 = this.gp.obj[1][2];
		this.gp.getClass();
		var10000.worldX = 5 * 64;
		var10000 = this.gp.obj[1][2];
		this.gp.getClass();
		var10000.worldY = 6 * 64;
		this.gp.obj[1][3] = new Obj_Cauldron(this.gp);
		var10000 = this.gp.obj[1][3];
		this.gp.getClass();
		var10000.worldX = 3 * 64;
		var10000 = this.gp.obj[1][3];
		this.gp.getClass();
		var10000.worldY = 3 * 64;
		this.gp.obj[1][4] = new Obj_Hearth(this.gp);
		var10000 = this.gp.obj[1][4];
		this.gp.getClass();
		var10000.worldX = 4 * 64;
		var10000 = this.gp.obj[1][4];
		this.gp.getClass();
		var10000.worldY = 3 * 64;
		this.gp.obj[1][5] = new Obj_Pots(this.gp);
		var10000 = this.gp.obj[1][5];
		this.gp.getClass();
		var10000.worldX = 2 * 64;
		var10000 = this.gp.obj[1][5];
		this.gp.getClass();
		var10000.worldY = 3 * 64;
		this.gp.obj[1][6] = new Obj_Pots(this.gp);
		var10000 = this.gp.obj[1][6];
		this.gp.getClass();
		var10000.worldX = 5 * 64;
		var10000 = this.gp.obj[1][6];
		this.gp.getClass();
		var10000.worldY = 3 * 64;
		this.gp.obj[1][7] = new Obj_Cupboard(this.gp);
		var10000 = this.gp.obj[1][7];
		this.gp.getClass();
		var10000.worldX = 1 * 64;
		var10000 = this.gp.obj[1][7];
		this.gp.getClass();
		var10000.worldY = 4 * 64;
		this.gp.obj[1][8] = new Obj_Cupboard(this.gp);
		var10000 = this.gp.obj[1][8];
		this.gp.getClass();
		var10000.worldX = 1 * 64;
		var10000 = this.gp.obj[1][8];
		this.gp.getClass();
		var10000.worldY = 6 * 64;
		this.gp.obj[1][9] = new Obj_Kitchen_Window(this.gp);
		var10000 = this.gp.obj[1][9];
		this.gp.getClass();
		var10000.worldX = 5 * 64;
		var10000 = this.gp.obj[1][9];
		this.gp.getClass();
		var10000.worldY = 9 * 64;
		this.gp.obj[1][10] = new Obj_Basin(this.gp);
		var10000 = this.gp.obj[1][10];
		this.gp.getClass();
		var10000.worldX = 6 * 64;
		var10000 = this.gp.obj[1][10];
		this.gp.getClass();
		var10000.worldY = 9 * 64;
		this.gp.obj[1][11] = new Obj_Larder(this.gp);
		var10000 = this.gp.obj[1][11];
		this.gp.getClass();
		var10000.worldX = 1 * 64;
		var10000 = this.gp.obj[1][11];
		this.gp.getClass();
		var10000.worldY = 9 * 64;
	}
}