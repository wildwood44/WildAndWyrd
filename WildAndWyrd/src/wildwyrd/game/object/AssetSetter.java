package wildwyrd.game.object;

import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Itm_Bandage;
import wildwyrd.game.items.Itm_Bread;
import wildwyrd.game.items.Itm_Hazelnut;
import wildwyrd.game.items.Itm_Hunting_Knife;
import wildwyrd.game.items.Itm_P_Mushroom;
import wildwyrd.game.npc.NPC_Cricket;
import wildwyrd.game.npc.NPC_Thay;
import wildwyrd.game.rooms.Rm_Blank;
import wildwyrd.game.rooms.Rm_Cottage;
import wildwyrd.game.rooms.Rm_Forton;
import wildwyrd.game.rooms.Rm_Hall_Of_Scion;
import wildwyrd.game.rooms.Rm_Woods;
import wildwyrd.game.tile.Map;
import wildwyrd.game.tile.MapType;

public class AssetSetter {
	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setMaps() {
		gp.maps[0] = new Map(gp, MapType.INTERIOR, 0, 15, 10);
		gp.maps[1] = new Map(gp, MapType.OUTSIDE, 1, 19, 12);
		gp.maps[2] = new Map(gp, MapType.OUTSIDE, 2, 17, 11);
	}

	public void setRooms() {
		gp.rm[0] = new Rm_Blank(gp);
		gp.rm[1] = new Rm_Forton(gp);
		gp.rm[2] = new Rm_Hall_Of_Scion(gp);
		gp.rm[3] = new Rm_Cottage(gp);
		gp.rm[4] = new Rm_Woods(gp);
	}

	public void setObject() {
		int i = 0;
		gp.obj[0][i] = new Obj_Wooden_Bowl(gp);
		gp.obj[0][i].setLoot(new Itm_Hazelnut(gp));
		gp.obj[0][i].worldX = 3 * gp.tileSize;
		gp.obj[0][i].worldY = 5 * gp.tileSize;
		i++;
		gp.obj[0][i] = new Obj_Table_Left(gp);
		gp.obj[0][i].worldX = 2 * gp.tileSize;
		gp.obj[0][i].worldY = 5 * gp.tileSize;
		i++;
		gp.obj[0][i] = new Obj_Table_Right(gp);
		gp.obj[0][i].worldX = 4 * gp.tileSize;
		gp.obj[0][i].worldY = 5 * gp.tileSize;
		i++;
		//gp.obj[0][i] = new Obj_Cauldron(gp);
		//gp.obj[0][i].worldX = 8 * gp.tileSize;
		//gp.obj[0][i].worldY = 2 * gp.tileSize;
		//i++;
		gp.obj[0][i] = new Obj_Hearth(gp);
		gp.obj[0][i].worldX = 7 * gp.tileSize;
		gp.obj[0][i].worldY = 2 * gp.tileSize;
		i++;
		gp.obj[0][i] = new Obj_Pots(gp);
		gp.obj[0][i].worldX = 2 * gp.tileSize;
		gp.obj[0][i].worldY = 3 * gp.tileSize;
		i++;
		gp.obj[0][i] = new Obj_Cupboard(gp);
		gp.obj[0][i].worldX = 1 * gp.tileSize;
		gp.obj[0][i].worldY = 4 * gp.tileSize;
		i++;
		gp.obj[0][i] = new Obj_Cupboard(gp);
		gp.obj[0][i].worldX = 1 * gp.tileSize;
		gp.obj[0][i].worldY = 5 * gp.tileSize;
		i++;
		gp.obj[0][i] = new Obj_Kitchen_Window(gp);
		gp.obj[0][i].worldX = 3 * gp.tileSize;
		gp.obj[0][i].worldY = 7 * gp.tileSize;
		i++;
		gp.obj[0][i] = new Obj_Basin(gp);
		gp.obj[0][i].worldX = 4 * gp.tileSize;
		gp.obj[0][i].worldY = 7 * gp.tileSize;
		i++;
		gp.obj[0][i] = new Obj_Larder(gp);
		gp.obj[0][i].setLoot(new Itm_Bread(gp));
		gp.obj[0][i].worldX = 1 * gp.tileSize;
		gp.obj[0][i].worldY = 7 * gp.tileSize;
		i++;
		gp.obj[0][i] = new Obj_Bookshelf(gp);
		gp.obj[0][i].worldX = 6 * gp.tileSize;
		gp.obj[0][i].worldY = 2 * gp.tileSize;
		i++;
		gp.obj[0][i] = new Obj_Table(gp);
		gp.obj[0][i].worldX = 7 * gp.tileSize;
		gp.obj[0][i].worldY = 4 * gp.tileSize;
		i++;
		gp.obj[0][i] = new Obj_Oven(gp);
		gp.obj[0][i].worldX = 3 * gp.tileSize;
		gp.obj[0][i].worldY = 3 * gp.tileSize;
		i++;
		gp.obj[0][i] = new Obj_Window_Down(gp);
		gp.obj[0][i].worldX = 7 * gp.tileSize;
		gp.obj[0][i].worldY = 6 * gp.tileSize;
		i++;
		gp.obj[0][i] = new Obj_Window_Down(gp);
		gp.obj[0][i].worldX = 9 * gp.tileSize;
		gp.obj[0][i].worldY = 6 * gp.tileSize;
		i++;
		gp.obj[0][i] = new Obj_Pot(gp);
		gp.obj[0][i].worldX = 13 * gp.tileSize;
		gp.obj[0][i].worldY = 4 * gp.tileSize;
		gp.obj[0][i].setLoot(new Itm_Bandage(gp));
		i++;
		gp.obj[0][i] = new Obj_Crate(gp);
		gp.obj[0][i].worldX = 13 * gp.tileSize;
		gp.obj[0][i].worldY = 6 * gp.tileSize;
		i++;
		gp.obj[0][i] = new Obj_Worktable(gp);
		gp.obj[0][i].worldX = 12 * gp.tileSize;
		gp.obj[0][i].worldY = 4 * gp.tileSize;
		gp.obj[0][i].setLoot(new Itm_Hunting_Knife(gp));
		i++;
		gp.obj[0][i] = new Obj_Alder_Bed(gp);
		gp.obj[0][i].worldX = 10 * gp.tileSize;
		gp.obj[0][i].worldY = 2 * gp.tileSize;
		gp.obj[0][i].setLoot(getRandomNumber(2,3));
		i++;
		gp.obj[0][i] = new Obj_Cracked_Wall(gp);
		gp.obj[0][i].worldX = 11 * gp.tileSize;
		gp.obj[0][i].worldY = 6 * gp.tileSize;
		i = 0;
		gp.obj[1][i] = new Obj_P_Mushroom(gp);
		gp.obj[1][i].worldX = 10 * gp.tileSize;
		gp.obj[1][i].worldY = 7 * gp.tileSize;
		gp.obj[1][i].setLoot(new Itm_P_Mushroom(gp));
		i++;
		//gp.obj[1][1] = new Plant_1(gp);
		//gp.obj[1][1].worldX = 14 * gp.tileSize;
		//gp.obj[1][1].worldY = 7 * gp.tileSize;
		gp.obj[1][i] = new Obj_Tree(gp);
		gp.obj[1][i].worldX = 1 * gp.tileSize;
		gp.obj[1][i].worldY = 7 * gp.tileSize;
		i++;
		gp.obj[1][i] = new Obj_SilverBirch(gp);
		gp.obj[1][i].worldX = 15 * gp.tileSize;
		gp.obj[1][i].worldY = 4 * gp.tileSize;
		i++;
		gp.obj[1][i] = new Obj_Rock(gp, 0);
		gp.obj[1][i].worldX = 14 * gp.tileSize;
		gp.obj[1][i].worldY = 9 * gp.tileSize;
		i++;
		gp.obj[1][i] = new Obj_Rock(gp, 1);
		gp.obj[1][i].worldX = 20 * gp.tileSize;
		gp.obj[1][i].worldY = 5 * gp.tileSize;
		i++;
		gp.obj[1][i] = new Obj_Blackberry(gp);
		gp.obj[1][i].worldX = 3 * gp.tileSize;
		gp.obj[1][i].worldY = 4 * gp.tileSize;
		i++;
		gp.obj[1][i] = new Obj_Blackberry(gp);
		gp.obj[1][i].worldX = 4 * gp.tileSize;
		gp.obj[1][i].worldY = 3 * gp.tileSize;
		i = 0;
		gp.obj[2][i] = new Obj_SilverBirch(gp);
		gp.obj[2][i].worldX = 1 * gp.tileSize;
		gp.obj[2][i].worldY = 4 * gp.tileSize;
		i++;
		gp.obj[2][i] = new Obj_SilverBirch(gp);
		gp.obj[2][i].worldX = 5 * gp.tileSize;
		gp.obj[2][i].worldY = 8 * gp.tileSize;
		i++;
		gp.obj[2][i] = new Obj_SilverBirch(gp);
		gp.obj[2][i].worldX = 9 * gp.tileSize;
		gp.obj[2][i].worldY = 3 * gp.tileSize;
	}
	public void setNPC() {
		//gp.npc[1][0] = new NPC_Florence(gp);
		//gp.npc[1][0].worldX = gp.tileSize * 11;
		//gp.npc[1][0].worldY = gp.tileSize * 3;
		gp.npc[1][0] = new NPC_Thay(gp);
		gp.npc[1][0].worldX = gp.tileSize * 12;
		gp.npc[1][0].worldY = gp.tileSize * 8;
		//gp.npc[1][2] = new NPC_Dilecto(gp);
		//gp.npc[1][2].worldX = gp.tileSize * 12;
		//gp.npc[1][2].worldY = gp.tileSize * 4;
		gp.npc[2][0] = new NPC_Cricket(gp);
		gp.npc[2][0].worldX = gp.tileSize * 13;
		gp.npc[2][0].worldY = gp.tileSize * 5;
	}
	public void setInteractiveTile() {
		int i = 0;
		gp.iTile[1][i] = new IT_StoneDoor(gp, 12, 2);
		i++;
		gp.iTile[1][i] = new IT_StoneDoor(gp, 16, 2);
	}
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
}