package wildwyrd.game.object;

import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Itm_Bandage;
import wildwyrd.game.items.Itm_Hazelnut;
import wildwyrd.game.items.Itm_Hunting_Knife;
import wildwyrd.game.items.Itm_P_Mushroom;
import wildwyrd.game.npc.NPC_Cricket;
import wildwyrd.game.npc.NPC_Florence;
import wildwyrd.game.npc.NPC_Thay;
import wildwyrd.game.rooms.Rm_Blank;
import wildwyrd.game.rooms.Rm_Forton;
import wildwyrd.game.rooms.Rm_Hall_Of_Scion;
import wildwyrd.game.tile.Map;
import wildwyrd.game.tile.MapType;

public class AssetSetter {
	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setMaps() {
		gp.maps[0] = new Map(gp, MapType.INTERIOR, 0, 19, 11);
		gp.maps[1] = new Map(gp, MapType.INTERIOR, 1, 17, 11);
		gp.maps[2] = new Map(gp, MapType.OUTSIDE, 2, 23, 12);
		gp.maps[3] = new Map(gp, MapType.OUTSIDE, 3, 17, 11);
	}

	public void setRooms() {
		gp.rm[0] = new Rm_Blank(gp);
		gp.rm[1] = new Rm_Forton(gp);
		gp.rm[2] = new Rm_Hall_Of_Scion(gp);
	}

	public void setObject() {
		gp.obj[0][0] = new Obj_Wooden_Bowl(gp);
		gp.obj[0][0].worldX = 3 * gp.tileSize;
		gp.obj[0][0].worldY = 6 * gp.tileSize;
		gp.obj[0][0].setLoot(new Itm_Hazelnut(gp));
		gp.obj[0][1] = new Obj_Table_Left(gp);
		gp.obj[0][1].worldX = 2 * gp.tileSize;
		gp.obj[0][1].worldY = 6 * gp.tileSize;
		gp.obj[0][2] = new Obj_Table_Right(gp);
		gp.obj[0][2].worldX = 4 * gp.tileSize;
		gp.obj[0][2].worldY = 6 * gp.tileSize;
		gp.obj[0][3] = new Obj_Cauldron(gp);
		gp.obj[0][3].worldX = 9 * gp.tileSize;
		gp.obj[0][3].worldY = 2 * gp.tileSize;
		gp.obj[0][4] = new Obj_Hearth(gp);
		gp.obj[0][4].worldX = 10 * gp.tileSize;
		gp.obj[0][4].worldY = 2 * gp.tileSize;
		gp.obj[0][5] = new Obj_Pots(gp);
		gp.obj[0][5].worldX = 2 * gp.tileSize;
		gp.obj[0][5].worldY = 3 * gp.tileSize;
		gp.obj[0][6] = new Obj_Pots(gp);
		gp.obj[0][6].worldX = 4 * gp.tileSize;
		gp.obj[0][6].worldY = 3 * gp.tileSize;
		gp.obj[0][7] = new Obj_Cupboard(gp);
		gp.obj[0][7].worldX = 1 * gp.tileSize;
		gp.obj[0][7].worldY = 4 * gp.tileSize;
		gp.obj[0][8] = new Obj_Cupboard(gp);
		gp.obj[0][8].worldX = 1 * gp.tileSize;
		gp.obj[0][8].worldY = 6 * gp.tileSize;
		gp.obj[0][9] = new Obj_Kitchen_Window(gp);
		gp.obj[0][9].worldX = 4 * gp.tileSize;
		gp.obj[0][9].worldY = 8 * gp.tileSize;
		gp.obj[0][10] = new Obj_Basin(gp);
		gp.obj[0][10].worldX = 5 * gp.tileSize;
		gp.obj[0][10].worldY = 8 * gp.tileSize;
		gp.obj[0][11] = new Obj_Larder(gp);
		gp.obj[0][11].worldX = 1 * gp.tileSize;
		gp.obj[0][11].worldY = 8 * gp.tileSize;
		gp.obj[0][12] = new Obj_Bookshelf(gp);
		gp.obj[0][12].worldX = 7 * gp.tileSize;
		gp.obj[0][12].worldY = 3 * gp.tileSize;
		gp.obj[0][13] = new Obj_Table(gp);
		gp.obj[0][13].worldX = 10 * gp.tileSize;
		gp.obj[0][13].worldY = 4 * gp.tileSize;
		gp.obj[0][14] = new Obj_Chair(gp);
		gp.obj[0][14].worldX = 9 * gp.tileSize;
		gp.obj[0][14].worldY = 4 * gp.tileSize;
		gp.obj[0][14].direction = "right";
		gp.obj[0][15] = new Obj_Chair(gp);
		gp.obj[0][15].worldX = 11 * gp.tileSize;
		gp.obj[0][15].worldY = 4 * gp.tileSize;
		gp.obj[0][15].direction = "left";
		gp.obj[0][16] = new Obj_Oven(gp);
		gp.obj[0][16].worldX = 3 * gp.tileSize;
		gp.obj[0][16].worldY = 4 * gp.tileSize;
		gp.obj[0][17] = new Obj_Window_Down(gp);
		gp.obj[0][17].worldX = 8 * gp.tileSize;
		gp.obj[0][17].worldY = 9 * gp.tileSize;
		gp.obj[0][18] = new Obj_Window_Down(gp);
		gp.obj[0][18].worldX = 12 * gp.tileSize;
		gp.obj[0][18].worldY = 9 * gp.tileSize;
		gp.obj[0][19] = new Obj_Pot(gp);
		gp.obj[0][19].worldX = 15 * gp.tileSize;
		gp.obj[0][19].worldY = 4 * gp.tileSize;
		gp.obj[0][20] = new Obj_Pot(gp);
		gp.obj[0][20].worldX = 15 * gp.tileSize;
		gp.obj[0][20].worldY = 5 * gp.tileSize;
		gp.obj[0][20].setLoot(new Itm_Bandage(gp));
		gp.obj[0][21] = new Obj_Crate(gp);
		gp.obj[0][21].worldX = 17 * gp.tileSize;
		gp.obj[0][21].worldY = 4 * gp.tileSize;
		gp.obj[0][22] = new Obj_Worktable(gp);
		gp.obj[0][22].worldX = 16 * gp.tileSize;
		gp.obj[0][22].worldY = 4 * gp.tileSize;
		gp.obj[0][22].setLoot(new Itm_Hunting_Knife(gp));
		gp.obj[1][0] = new Obj_Alder_Bed(gp);
		gp.obj[1][0].worldX = 5 * gp.tileSize;
		gp.obj[1][0].worldY = 5 * gp.tileSize;
		gp.obj[1][0].setLoot(getRandomNumber(2,3));
		gp.obj[1][1] = new Obj_Alder_Window(gp);
		gp.obj[1][1].worldX = 6 * gp.tileSize;
		gp.obj[1][1].worldY = 3 * gp.tileSize;
		gp.obj[2][0] = new Obj_P_Mushroom(gp);
		gp.obj[2][0].worldX = 10 * gp.tileSize;
		gp.obj[2][0].worldY = 8 * gp.tileSize;
		gp.obj[2][0].setLoot(new Itm_P_Mushroom(gp));
		gp.obj[2][1] = new Plant_1(gp);
		gp.obj[2][1].worldX = 14 * gp.tileSize;
		gp.obj[2][1].worldY = 7 * gp.tileSize;
		gp.obj[2][2] = new Obj_Tree(gp);
		gp.obj[2][2].worldX = 1 * gp.tileSize;
		gp.obj[2][2].worldY = 7 * gp.tileSize;
		gp.obj[2][3] = new Obj_SilverBirch(gp);
		gp.obj[2][3].worldX = 16 * gp.tileSize;
		gp.obj[2][3].worldY = 5 * gp.tileSize;
		gp.obj[2][4] = new Obj_Rock(gp, 0);
		gp.obj[2][4].worldX = 14 * gp.tileSize;
		gp.obj[2][4].worldY = 9 * gp.tileSize;
		gp.obj[2][5] = new Obj_Rock(gp, 1);
		gp.obj[2][5].worldX = 22 * gp.tileSize;
		gp.obj[2][5].worldY = 5 * gp.tileSize;
		gp.obj[2][6] = new Obj_Blackberry(gp);
		gp.obj[2][6].worldX = 3 * gp.tileSize;
		gp.obj[2][6].worldY = 4 * gp.tileSize;
		gp.obj[2][7] = new Obj_Blackberry(gp);
		gp.obj[2][7].worldX = 4 * gp.tileSize;
		gp.obj[2][7].worldY = 3 * gp.tileSize;
		//gp.obj[2][8] = new Obj_Dummy(gp);
		//gp.obj[2][8].worldX = 8 * gp.tileSize;
		//gp.obj[2][8].worldY = 5 * gp.tileSize;
		
		gp.obj[3][0] = new Obj_SilverBirch(gp);
		gp.obj[3][0].worldX = 1 * gp.tileSize;
		gp.obj[3][0].worldY = 4 * gp.tileSize;
		gp.obj[3][1] = new Obj_SilverBirch(gp);
		gp.obj[3][1].worldX = 5 * gp.tileSize;
		gp.obj[3][1].worldY = 8 * gp.tileSize;
		gp.obj[3][2] = new Obj_SilverBirch(gp);
		gp.obj[3][2].worldX = 9 * gp.tileSize;
		gp.obj[3][2].worldY = 3 * gp.tileSize;
	}
	public void setNPC() {
		gp.npc[2][0] = new NPC_Florence(gp);
		gp.npc[2][0].worldX = gp.tileSize * 13;
		gp.npc[2][0].worldY = gp.tileSize * 4;
		gp.npc[2][1] = new NPC_Thay(gp);
		gp.npc[2][1].worldX = gp.tileSize * 15;
		gp.npc[2][1].worldY = gp.tileSize * 4;
		//gp.npc[2][2] = new NPC_Dilecto(gp);
		//gp.npc[2][2].worldX = gp.tileSize * 12;
		//gp.npc[2][2].worldY = gp.tileSize * 4;
		gp.npc[3][0] = new NPC_Cricket(gp);
		gp.npc[3][0].worldX = gp.tileSize * 13;
		gp.npc[3][0].worldY = gp.tileSize * 5;
	}
	public void setInteractiveTile() {
		gp.iTile[2][0] = new IT_StoneDoor(gp, 14, 3);
		gp.iTile[2][1] = new IT_StoneDoor(gp, 20, 2);
	}
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
}