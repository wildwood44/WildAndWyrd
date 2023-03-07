package wildwyrd.game.object;

import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Itm_Hazelnut;
import wildwyrd.game.items.Itm_P_Mushroom;
import wildwyrd.game.rooms.Rm_Blank;
import wildwyrd.game.rooms.Rm_Kitchen;
import wildwyrd.game.tile.Map;
import wildwyrd.game.tile.MapType;

public class AssetSetter {
	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setMaps() {
		gp.maps[0] = new Map(gp, MapType.INTERIOR, 0, 22, 12);
		gp.maps[1] = new Map(gp, MapType.INTERIOR, 1, 17, 12);
		gp.maps[2] = new Map(gp, MapType.OUTSIDE, 2, 31, 31);
	}

	public void setRooms() {
		gp.rm[0] = new Rm_Blank(gp);
		gp.rm[1] = new Rm_Kitchen(gp);
	}

	public void setObject() {
		gp.obj[0][0] = new Obj_Wooden_Bowl(gp, new Itm_Hazelnut(gp));
		gp.obj[0][0].worldX = 4 * gp.tileSize;
		gp.obj[0][0].worldY = 6 * gp.tileSize;
		gp.obj[0][1] = new Obj_Table_Left(gp);
		gp.obj[0][1].worldX = 3 * gp.tileSize;
		gp.obj[0][1].worldY = 6 * gp.tileSize;
		gp.obj[0][2] = new Obj_Table_Right(gp);
		gp.obj[0][2].worldX = 5 * gp.tileSize;
		gp.obj[0][2].worldY = 6 * gp.tileSize;
		gp.obj[0][3] = new Obj_Cauldron(gp);
		gp.obj[0][3].worldX = 11 * gp.tileSize;
		gp.obj[0][3].worldY = 2 * gp.tileSize;
		gp.obj[0][4] = new Obj_Hearth(gp);
		gp.obj[0][4].worldX = 12 * gp.tileSize;
		gp.obj[0][4].worldY = 2 * gp.tileSize;
		gp.obj[0][5] = new Obj_Pots(gp);
		gp.obj[0][5].worldX = 2 * gp.tileSize;
		gp.obj[0][5].worldY = 3 * gp.tileSize;
		gp.obj[0][6] = new Obj_Pots(gp);
		gp.obj[0][6].worldX = 5 * gp.tileSize;
		gp.obj[0][6].worldY = 3 * gp.tileSize;
		gp.obj[0][7] = new Obj_Cupboard(gp);
		gp.obj[0][7].worldX = 1 * gp.tileSize;
		gp.obj[0][7].worldY = 4 * gp.tileSize;
		gp.obj[0][8] = new Obj_Cupboard(gp);
		gp.obj[0][8].worldX = 1 * gp.tileSize;
		gp.obj[0][8].worldY = 6 * gp.tileSize;
		gp.obj[0][9] = new Obj_Kitchen_Window(gp);
		gp.obj[0][9].worldX = 5 * gp.tileSize;
		gp.obj[0][9].worldY = 9 * gp.tileSize;
		gp.obj[0][10] = new Obj_Basin(gp);
		gp.obj[0][10].worldX = 6 * gp.tileSize;
		gp.obj[0][10].worldY = 9 * gp.tileSize;
		gp.obj[0][11] = new Obj_Larder(gp);
		gp.obj[0][11].worldX = 1 * gp.tileSize;
		gp.obj[0][11].worldY = 9 * gp.tileSize;
		gp.obj[0][12] = new Obj_Bookshelf(gp);
		gp.obj[0][12].worldX = 8 * gp.tileSize;
		gp.obj[0][12].worldY = 3 * gp.tileSize;
		gp.obj[0][13] = new Obj_Table(gp);
		gp.obj[0][13].worldX = 12 * gp.tileSize;
		gp.obj[0][13].worldY = 4 * gp.tileSize;
		gp.obj[0][14] = new Obj_Chair_Left(gp);
		gp.obj[0][14].worldX = 11 * gp.tileSize;
		gp.obj[0][14].worldY = 4 * gp.tileSize;
		gp.obj[0][15] = new Obj_Oven(gp);
		gp.obj[0][15].worldX = 3 * gp.tileSize;
		gp.obj[0][15].worldY = 4 * gp.tileSize;
		gp.obj[0][16] = new Obj_Window_Down(gp);
		gp.obj[0][16].worldX = 9 * gp.tileSize;
		gp.obj[0][16].worldY = 10 * gp.tileSize;
		gp.obj[0][17] = new Obj_Window_Down(gp);
		gp.obj[0][17].worldX = 13 * gp.tileSize;
		gp.obj[0][17].worldY = 10 * gp.tileSize;
		gp.obj[1][0] = new Obj_Alder_Bed(gp, getRandomNumber(2,3));
		gp.obj[1][0].worldX = 5 * gp.tileSize;
		gp.obj[1][0].worldY = 5 * gp.tileSize;
		gp.obj[1][1] = new Obj_Alder_Window(gp);
		gp.obj[1][1].worldX = 6 * gp.tileSize;
		gp.obj[1][1].worldY = 3 * gp.tileSize;
		gp.obj[2][0] = new Obj_P_Mushroom(gp, new Itm_P_Mushroom(gp));
		gp.obj[2][0].worldX = 10 * gp.tileSize;
		gp.obj[2][0].worldY = 8 * gp.tileSize;
	}
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
}