package wildwyrd.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import wildwyrd.data.DataStorage;
import wildwyrd.game.items.Armour;
import wildwyrd.game.items.Weapon;
import wildwyrd.game.playable.Playable;

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
			ds.currentHat = new int[gp.playable.size()];
			ds.currentShirt = new int[gp.playable.size()];
			ds.currentTrousers = new int[gp.playable.size()];
			ds.currentPrimary = new int[gp.playable.size()];
			ds.currentSecondary = new int[gp.playable.size()];
			ds.found = new boolean[7][50];
			for(int i = 0; i < gp.playable.size(); i++) {
				//Stats
				ds.maxHealth[i] = gp.playable.get(i).maxHealth;
				ds.health[i] = gp.playable.get(i).health;
				ds.maxStamina[i] = gp.playable.get(i).getMaxStamina();
				ds.stamina[i] = gp.playable.get(i).getStamina();
				ds.baseAttack[i] = gp.playable.get(i).getBaseAttack();
				ds.baseDefence[i] = gp.playable.get(i).getBaseDefence();
				ds.baseAccuracy[i] = gp.playable.get(i).getBaseAccuracy();
				ds.baseEvasion[i] = gp.playable.get(i).getBaseEvasion();
				ds.baseSpeed[i] = gp.playable.get(i).getBaseSpeed();
				//Equipment
				if(gp.playable.get(i).getHead() == null) {
					ds.currentHat[i] = -1;
				} else {
					ds.currentHat[i] = gp.playable.get(i).getHead().id;
				} if(gp.playable.get(i).getBody() == null) {
					ds.currentShirt[i] = -1;
				} else {
					ds.currentShirt[i] = gp.playable.get(i).getBody().id;
				} if(gp.playable.get(i).getLegs() == null) {
					ds.currentTrousers[i] = -1;
				} else {
					ds.currentTrousers[i] = gp.playable.get(i).getLegs().id;
				} if(gp.playable.get(i).getWeapon_prime() == null) {
					ds.currentPrimary[i] = -1;
				} else {
					ds.currentPrimary[i] = gp.playable.get(i).getWeapon_prime().id;
				} if(gp.playable.get(i).getWeapon_second() == null) {
					ds.currentSecondary[i] = -1;
				} else {
					ds.currentSecondary[i] = gp.playable.get(i).getWeapon_second().id;
				}
			}
			ds.direction = gp.player.direction;
			//Player Position
			ds.story = gp.s;
			ds.currentMap = gp.currentMap.getId();
			ds.worldX = gp.player.worldX;
			ds.worldY = gp.player.worldY;
			//Inventory
			for(int i = 0; i < gp.player.inventory.size(); i++) {
				ds.itemId.add(gp.player.inventory.get(i).id);
				ds.itemAmount.add(gp.player.inventory.get(i).amount);
			}
			//Object on map
			ds.mapObjectId = new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectLootIds = new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];
			ds.mapNpcId = new int[gp.maxMap][gp.npc[1].length];
			ds.mapNpcWorldX = new int[gp.maxMap][gp.npc[1].length];
			ds.mapNpcWorldY = new int[gp.maxMap][gp.npc[1].length];
			ds.mapNpcDirection = new String[gp.maxMap][gp.npc[1].length];
			for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
				for(int i = 0; i < gp.obj[1].length; i++) {
					if(gp.obj[mapNum][i] == null) {
						ds.mapObjectId[mapNum][i] = -1;
					} else {
						ds.mapObjectId[mapNum][i] = gp.obj[mapNum][i].id;
						ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
						ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
						if(gp.obj[mapNum][i].loot != null) {
							ds.mapObjectLootIds[mapNum][i] = gp.obj[mapNum][i].loot.id;
						}
						ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
					}
				}
				//NPCs on map
				for(int i = 0; i < gp.npc[1].length; i++) {
					if(gp.npc[mapNum][i] == null) {
						ds.mapNpcId[mapNum][i] = -1;
					} else {
						ds.mapNpcId[mapNum][i] = gp.npc[mapNum][i].id;
						ds.mapNpcWorldX[mapNum][i] = gp.npc[mapNum][i].worldX;
						ds.mapNpcWorldY[mapNum][i] = gp.npc[mapNum][i].worldY;
						ds.mapNpcDirection[mapNum][i] = gp.npc[mapNum][i].direction;
					}
				}
				//Glossary
				for (int i = 0; i < gp.glossary.sections.length; i++) {
					for (int j = 0; j < gp.glossary.getSize(i); j++) {
						if (gp.glossary.page[i][j] != null && gp.glossary.page[i][j].isFound()) {
							ds.found[i][j] = gp.glossary.page[i][j].isFound();
						}
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
				if(ds.currentHat[i] >= 0 && gp.eGenerator.getObject(ds.currentHat[i]) instanceof Armour) {
					gp.playable.get(i).setHead(gp.eGenerator.getObject(ds.currentHat[i]));
				} if(ds.currentShirt[i] >= 0 && gp.eGenerator.getObject(ds.currentShirt[i]) instanceof Armour) {
					gp.playable.get(i).setBody(gp.eGenerator.getObject(ds.currentShirt[i]));
				} if(ds.currentTrousers[i] >= 0 && gp.eGenerator.getObject(ds.currentTrousers[i]) instanceof Armour) {
					gp.playable.get(i).setLegs(gp.eGenerator.getObject(ds.currentTrousers[i]));
				} if(ds.currentPrimary[i] >= 0 && gp.eGenerator.getObject(ds.currentPrimary[i]) instanceof Weapon) {
					gp.playable.get(i).setWeapon_prime((Weapon)gp.eGenerator.getObject(ds.currentPrimary[i]));
				} if(ds.currentSecondary[i] >= 0 && gp.eGenerator.getObject(ds.currentSecondary[i]) instanceof Weapon) {
					gp.playable.get(i).setWeapon_second((Weapon)gp.eGenerator.getObject(ds.currentSecondary[i]));
				}
			}
		//	gp.player = ds.player;
			gp.s = ds.story;
			//for(int i = 0; i < gp.s.c1Switch.length; i++) {
			//	System.out.println(gp.s.c1Switch[i]);
			//}
			gp.currentMap = gp.maps[ds.currentMap];
			gp.player.direction = ds.direction;
			gp.player.worldX = ds.worldX;
			gp.player.worldY = ds.worldY;
			gp.player.inventory.clear();
			for(int i = 0; i < ds.itemId.size(); i++) {
				gp.player.inventory.add(gp.eGenerator.getObject(ds.itemId.get(i)));
				gp.player.inventory.get(i).amount = ds.itemAmount.get(i);
			}
			for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
				for(int i = 0; i < gp.obj[1].length; i++) {
					if(ds.mapObjectId[mapNum][i] < 0) {
						gp.obj[mapNum][i] = null;
					} else {
						gp.obj[mapNum][i] = gp.eGenerator.getObject(ds.mapObjectId[mapNum][i]);
						gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
						gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
						if(ds.mapObjectLootIds[mapNum][i] > 0) {
							gp.obj[mapNum][i].loot = gp.eGenerator.getObject(ds.mapObjectLootIds[mapNum][i]);
						}
						gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
						if(gp.obj[mapNum][i].opened == true) {
							//gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
						}
					}
				}
				//NPCs on map
				for(int i = 0; i < gp.npc[1].length; i++) {
					if(ds.mapNpcId[mapNum][i] < 0) {
						gp.npc[mapNum][i] = null;
					} else {
						gp.npc[mapNum][i] = gp.eGenerator.getNpc(ds.mapNpcId[mapNum][i]);
						gp.npc[mapNum][i].worldX = ds.mapNpcWorldX[mapNum][i];
						gp.npc[mapNum][i].worldY = ds.mapNpcWorldY[mapNum][i];
						gp.npc[mapNum][i].direction = ds.mapNpcDirection[mapNum][i];
					}
				}
				//Glossary
				for (int i = 0; i < ds.found.length; i++) {
					for (int j = 0; j < ds.found[i].length; j++) {
						if (gp.glossary.page[i][j] != null && ds.found[i][j]) {
							gp.glossary.page[i][j].findGlossaryItem();
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Load Exception!");
		}
	}
}
