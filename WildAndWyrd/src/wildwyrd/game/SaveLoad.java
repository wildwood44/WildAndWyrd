package wildwyrd.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import wildwyrd.data.DataStorage;
import wildwyrd.game.object.Obj_Basin;
import wildwyrd.game.object.Obj_Cupboard;
import wildwyrd.game.object.Obj_Kitchen_Window;
import wildwyrd.game.object.Obj_Larder;
import wildwyrd.game.object.Obj_Oven;
import wildwyrd.game.object.Obj_Pots;
import wildwyrd.game.object.Obj_Table_Left;
import wildwyrd.game.object.Obj_Table_Right;
import wildwyrd.game.object.Obj_Wooden_Bowl;
import wildwyrd.game.playable.Playable;
import wildwyrd.game.tile.TileManager; 

public class SaveLoad {
	GamePanel gp;
	
	public SaveLoad(GamePanel gp) {
		this.gp = gp;
	}
	
	public void save() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			DataStorage ds = new DataStorage();
			ds.maxHealth = new int[gp.playable.size()];
			ds.health = new int[gp.playable.size()];
			ds.maxStamina = new int[gp.playable.size()];
			ds.stamina = new int[gp.playable.size()];
			ds.baseAttack = new int[gp.playable.size()];
			ds.baseDefence = new int[gp.playable.size()];
			ds.baseAccuracy = new int[gp.playable.size()];
			ds.baseEvasion = new int[gp.playable.size()];
			ds.baseSpeed = new int[gp.playable.size()];
			for(int i = 0; i < gp.playable.size(); i++) {
				ds.maxHealth[i] = gp.playable.get(i).maxHealth;
				ds.health[i] = gp.playable.get(i).health;
				ds.maxStamina[i] = gp.playable.get(i).getMaxStamina();
				ds.stamina[i] = gp.playable.get(i).getStamina();
				ds.baseAttack[i] = gp.playable.get(i).getBaseAttack();
				ds.baseDefence[i] = gp.playable.get(i).getBaseDefence();
				ds.baseAccuracy[i] = gp.playable.get(i).getBaseAccuracy();
				ds.baseEvasion[i] = gp.playable.get(i).getBaseEvasion();
				ds.baseSpeed[i] = gp.playable.get(i).getBaseSpeed();
			}
			ds.direction = gp.player.direction;
			//Player Position
			ds.story = gp.s;
			ds.currentMap = gp.currentMap.getId();
			ds.worldX = gp.player.worldX;
			ds.worldY = gp.player.worldY;
			//Object on map
			ds.mapObjectId = new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectLootIds = new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];
			for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
				System.out.println(gp.obj[1].length);
				for(int i = 0; i < gp.obj[1].length; i++) {
					if(gp.obj[mapNum][i] == null) {
						ds.mapObjectId[mapNum][i] = -1;
					} else {
						ds.mapObjectId[mapNum][i] = gp.obj[mapNum][i].id;
						System.out.println(gp.obj[mapNum][i] + " " + gp.obj[mapNum][i].id);
						ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
						ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
						if(gp.obj[mapNum][i].loot != null) {
							ds.mapObjectLootIds[mapNum][i] = gp.obj[mapNum][i].loot.id;
						}
						ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
					}
				}
			}
			oos.writeObject(ds);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Save Exception!");
		}
	}
	
	public void load() {
		try {
			//System.out.println("Loaded");
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			DataStorage ds = (DataStorage)ois.readObject();
			for(int i = 0; i < gp.playable.size(); i++) {
				gp.playable.set(i,new Playable(gp, "Alder", ds.maxHealth[i], ds.maxStamina[i],
						ds.baseAttack[i], ds.baseDefence[i], ds.baseAccuracy[i], ds.baseEvasion[i], ds.baseSpeed[i]));
				gp.playable.get(i).setHealthAndStamina(ds.health[i],ds.stamina[i]);
			}
		//	gp.player = ds.player;
			gp.player.direction = ds.direction;
			gp.s = ds.story;
			gp.currentMap = gp.maps[ds.currentMap];
			gp.player.worldX = ds.worldX;
			gp.player.worldY = ds.worldY;
			for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
				for(int i = 0; i < gp.obj[1].length; i++) {
					System.out.println(gp.obj[1]);
					if(ds.mapObjectId[mapNum][i] < 0) {
						gp.obj[mapNum][i] = null;
					} else {
						System.out.println(gp.obj[mapNum][i]);
						gp.obj[mapNum][i] = gp.eGenerator.getObject(ds.mapObjectId[mapNum][i]);
						gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
						gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
						if(gp.obj[mapNum][i].loot != null) {
							gp.obj[mapNum][i].loot = gp.eGenerator.getObject(ds.mapObjectLootIds[mapNum][i]);
						}
						gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
						if(gp.obj[mapNum][i].opened == true) {
							gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
						}
					}
				}
			}
			gp.tileM = new TileManager(gp);
		} catch (Exception e) {
			System.out.println("Load Exception!");
		}
	}
}
