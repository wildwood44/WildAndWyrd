package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Itm_Hazelnut;
import wildwyrd.game.rooms.Rm_Blank;
import wildwyrd.game.rooms.Rm_Kitchen;
import wildwyrd.game.tile.Map;

public class AssetSetter {
	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setMaps() {
		this.gp.maps[0] = new Map(gp, 0, 23, 12);
		this.gp.maps[1] = new Map(gp, 1, 17, 12);
	}

	public void setRooms() {
		this.gp.rm[0] = new Rm_Blank(this.gp);
		this.gp.rm[1] = new Rm_Kitchen(this.gp);
	}

	public void setObject() {
		this.gp.obj[0][0] = new Obj_Wooden_Bowl(this.gp, new Itm_Hazelnut(this.gp));
		Entity var10000 = this.gp.obj[0][0];
		this.gp.getClass();
		var10000.worldX = 4 * gp.tileSize;
		var10000 = this.gp.obj[0][0];
		this.gp.getClass();
		var10000.worldY = 6 * gp.tileSize;
		this.gp.obj[0][1] = new Obj_Table_Left(this.gp);
		var10000 = this.gp.obj[0][1];
		this.gp.getClass();
		var10000.worldX = 3 * gp.tileSize;
		var10000 = this.gp.obj[0][1];
		this.gp.getClass();
		var10000.worldY = 6 * gp.tileSize;
		this.gp.obj[0][2] = new Obj_Table_Right(this.gp);
		var10000 = this.gp.obj[0][2];
		this.gp.getClass();
		var10000.worldX = 5 * gp.tileSize;
		var10000 = this.gp.obj[0][2];
		this.gp.getClass();
		var10000.worldY = 6 * gp.tileSize;
		this.gp.obj[0][3] = new Obj_Cauldron(this.gp);
		var10000 = this.gp.obj[0][3];
		this.gp.getClass();
		var10000.worldX = 11 * gp.tileSize;
		var10000 = this.gp.obj[0][3];
		this.gp.getClass();
		var10000.worldY = 2 * gp.tileSize;
		this.gp.obj[0][4] = new Obj_Hearth(this.gp);
		var10000 = this.gp.obj[0][4];
		this.gp.getClass();
		var10000.worldX = 12 * gp.tileSize;
		var10000 = this.gp.obj[0][4];
		this.gp.getClass();
		var10000.worldY = 2 * gp.tileSize;
		this.gp.obj[0][5] = new Obj_Pots(this.gp);
		var10000 = this.gp.obj[0][5];
		this.gp.getClass();
		var10000.worldX = 2 * gp.tileSize;
		var10000 = this.gp.obj[0][5];
		this.gp.getClass();
		var10000.worldY = 3 * gp.tileSize;
		this.gp.obj[0][6] = new Obj_Pots(this.gp);
		var10000 = this.gp.obj[0][6];
		this.gp.getClass();
		var10000.worldX = 5 * gp.tileSize;
		var10000 = this.gp.obj[0][6];
		this.gp.getClass();
		var10000.worldY = 3 * gp.tileSize;
		this.gp.obj[0][7] = new Obj_Cupboard(this.gp);
		var10000 = this.gp.obj[0][7];
		this.gp.getClass();
		var10000.worldX = 1 * gp.tileSize;
		var10000 = this.gp.obj[0][7];
		this.gp.getClass();
		var10000.worldY = 4 * gp.tileSize;
		this.gp.obj[0][8] = new Obj_Cupboard(this.gp);
		var10000 = this.gp.obj[0][8];
		this.gp.getClass();
		var10000.worldX = 1 * gp.tileSize;
		var10000 = this.gp.obj[0][8];
		this.gp.getClass();
		var10000.worldY = 6 * gp.tileSize;
		this.gp.obj[0][9] = new Obj_Kitchen_Window(this.gp);
		var10000 = this.gp.obj[0][9];
		this.gp.getClass();
		var10000.worldX = 5 * gp.tileSize;
		var10000 = this.gp.obj[0][9];
		this.gp.getClass();
		var10000.worldY = 9 * gp.tileSize;
		this.gp.obj[0][10] = new Obj_Basin(this.gp);
		var10000 = this.gp.obj[0][10];
		this.gp.getClass();
		var10000.worldX = 6 * gp.tileSize;
		var10000 = this.gp.obj[0][10];
		this.gp.getClass();
		var10000.worldY = 9 * gp.tileSize;
		this.gp.obj[0][11] = new Obj_Larder(this.gp);
		var10000 = this.gp.obj[0][11];
		this.gp.getClass();
		var10000.worldX = 1 * gp.tileSize;
		var10000 = this.gp.obj[0][11];
		this.gp.getClass();
		var10000.worldY = 9 * gp.tileSize;
		this.gp.obj[0][12] = new Obj_Bookshelf(this.gp);
		var10000 = this.gp.obj[0][12];
		this.gp.getClass();
		var10000.worldX = 8 * gp.tileSize;
		var10000 = this.gp.obj[0][12];
		this.gp.getClass();
		var10000.worldY = 3 * gp.tileSize;
		this.gp.obj[0][13] = new Obj_Table(this.gp);
		var10000 = this.gp.obj[0][13];
		this.gp.getClass();
		var10000.worldX = 12 * gp.tileSize;
		var10000 = this.gp.obj[0][13];
		this.gp.getClass();
		var10000.worldY = 4 * gp.tileSize;
		this.gp.obj[0][14] = new Obj_Chair_Left(this.gp);
		var10000 = this.gp.obj[0][14];
		this.gp.getClass();
		var10000.worldX = 11 * gp.tileSize;
		var10000 = this.gp.obj[0][14];
		this.gp.getClass();
		var10000.worldY = 4 * gp.tileSize;
		this.gp.obj[0][15] = new Obj_Oven(this.gp);
		var10000 = this.gp.obj[0][15];
		this.gp.getClass();
		var10000.worldX = 3 * gp.tileSize;
		var10000 = this.gp.obj[0][15];
		this.gp.getClass();
		var10000.worldY = 4 * gp.tileSize;
		this.gp.obj[0][16] = new Obj_Window_Down(this.gp);
		var10000 = this.gp.obj[0][16];
		this.gp.getClass();
		var10000.worldX = 9 * gp.tileSize;
		var10000 = this.gp.obj[0][16];
		this.gp.getClass();
		var10000.worldY = 10 * gp.tileSize;
		this.gp.obj[0][17] = new Obj_Window_Down(this.gp);
		var10000 = this.gp.obj[0][17];
		this.gp.getClass();
		var10000.worldX = 14 * gp.tileSize;
		var10000 = this.gp.obj[0][17];
		this.gp.getClass();
		var10000.worldY = 10 * gp.tileSize;
		this.gp.obj[1][0] = new Obj_Alder_Bed(this.gp);
		var10000 = this.gp.obj[1][0];
		var10000.worldX = 5 * gp.tileSize;
		var10000 = this.gp.obj[1][0];
		var10000.worldY = 5 * gp.tileSize;
	}
}