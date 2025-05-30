package wildwyrd.game;

import wildwyrd.game.cutscenes.CutsceneManager;
import wildwyrd.game.items.Itm_Bug_Meat;
import wildwyrd.game.items.Itm_Travelling_Cloak;
import wildwyrd.game.object.Dialoge;
import wildwyrd.game.object.IT_StoneDoor;
import wildwyrd.game.tile.InteractiveTile;
import wildwyrd.game.tile.Map;
import wildwyrd.game.tile.TileManager;

public class EventHandler {
	GamePanel gp;
	EventRect[][][] eventRect;
	Entity eventMaster;
	int previousEventX, previousEventY;
	int eventRectDefaultX;
	int eventRectDefaultY;
	boolean canTouchEvent = true;
	boolean cutsceneActive = false;

	public EventHandler(GamePanel gp) {
		this.gp = gp;
		eventMaster = new Entity(gp);
		eventRect = new EventRect[gp.maxMap][gp.currentMap.getMaxWorldCol()][gp.currentMap.getMaxWorldRow()];//new Rectangle();
		int map = 0;
		int col = 0;
		int row = 0;
		while(map < gp.maxMap && col < gp.currentMap.getMaxWorldCol() && row < gp.currentMap.getMaxWorldRow()) {
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 25;
			eventRect[map][col][row].y = 25;
			eventRect[map][col][row].width = 32;
			eventRect[map][col][row].height = 32;
			eventRect[map][col][row].eventRectDefaultX = this.eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = this.eventRect[map][col][row].y;
			col++;
			if(col == gp.currentMap.getMaxWorldCol()) {
				col = 0;
				row++;
				if(row == gp.currentMap.getMaxWorldRow()) {
					row = 0;
					map++;
				}
			}
		}
		setDialogue();
	}
	
	public void setDialogue() {
		eventMaster.dialogues[0][0] = new Dialoge("It's dangerous to leave the cottage grounds unarmed.", 1);
		eventMaster.dialogues[1][0] = new Dialoge("Alder once got lost after he strayed too far from the cottage.", 1);
		eventMaster.dialogues[1][1] = new Dialoge("He spent hours in the dark until Florence found him crying and scared.", 1);
		eventMaster.dialogues[1][2] = new Dialoge("Kyla had been indifferent to the incident.", 1);
		eventMaster.dialogues[2][0] = new Dialoge("The Gowls are right outside.", 1);
		eventMaster.dialogues[2][1] = new Dialoge("Going out would be a death sentence.", 1);
	}

	public void checkEvent() {
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.tileSize) {
			canTouchEvent = true;
		}
		if(canTouchEvent) {
			if(gp.currentMap.getId() == 0) {
				if(hit(0,8,7,"down")) {
					if(!gp.s.c3Switch[3]) {
						Entity item = new Itm_Travelling_Cloak(gp);
						if(gp.player.findItemInInventory(item) == 2) {
							gp.s.swh[14] = true;
						} else { obsticle(gp.maps[0]);}
					} else {
						teleport(gp.maps[1],12,3);
						gp.playSE(8);
						if(gp.s.swh[1]) {gp.s.part = 2;}
					}
				}
				if(hit(0,12,7,"down")) {
					if(!gp.s.c3Switch[3]) {obsticle(gp.maps[0]);} else {gp.playSE(8);teleport(gp.maps[1],16,3);}
				}
			}
			//else if(gp.currentMap.getId() == 1) {
			//	if(hit(1,15,4,"down")) {teleport(gp.maps[0],13,9);}
			//}
			else if(gp.currentMap.getId() == 1) {
				if(hit(1,12,2,"up")) {illusion(gp.iTile[gp.currentMap.getId()][0]);}
				if(hit(1,12,2,"up")) {
					gp.playSE(8);
					teleport(gp.maps[0],8,6); 
					if(gp.s.chapter == 2 && gp.s.swh[6]) {gp.s.part = 2;}}
				if(hit(1,16,2,"up")) {illusion(gp.iTile[gp.currentMap.getId()][1]);}
				if(hit(1,16,2,"up")) {gp.playSE(8);teleport(gp.maps[0],12,6);}
				if(hit(1,14,4,"down")) {}
				if(hitRow(1,11,"down")) {
					if(gp.playable.get(0).getWeapon_prime().name != null) {
						teleport(gp.maps[2],4,1);
					} else { obsticle(gp.maps[1]); }
				}
			}
			else if(gp.currentMap.getId() == 2) {
				if(hitRow(2,0,"up")) {teleport(gp.maps[1],10,11);}
				if(hitRow(2,10,"down")) {obsticle(gp.maps[2]);};
			}
		}
	}

	public void checkCutscene() {
		if (gp.s.chapter == 0 && gp.s.swh[0]) {
			prologueCutscene(0);
		} else if (gp.s.chapter == 1) {
			if (gp.s.swh[0]) {
				c1s_Cutscene(2);
			} else if (gp.s.swh[1] && gp.s.part == 2) {
				c1s_Cutscene(3);
			}else if (gp.s.swh[2] && gp.s.part == 2) {
				c1s_Cutscene(4);
			}else if (gp.s.swh[3] && gp.s.part == 3) {
				c1s_Cutscene(5);
			}else if (gp.s.swh[4] && gp.s.part == 4) {
				c1s_Cutscene(6);
			}
		} else if (gp.s.chapter == 2) {
			if (gp.s.swh[5] && gp.s.part == 1) {
				c1s_Cutscene(7);
			} else if (gp.s.swh[6] && gp.s.part == 2) {
				c1s_Cutscene(8);
			} else if (gp.s.swh[7] && gp.s.part == 3) {
				c1s_Cutscene(9);
			} else if (gp.s.swh[8] && gp.s.part == 4) {
				c1s_Cutscene(10);
			}
		} else if (gp.s.chapter == 3) {
			if (gp.s.swh[9] && gp.s.part == 1) {
				c1s_Cutscene(11);
			} else if (gp.s.swh[10] && gp.s.part == 1) {
				c1s_Cutscene(12);
			} else if (gp.s.swh[11] && gp.s.part == 2) {
				c1s_Cutscene(13);
			} else if (gp.s.swh[12] && gp.s.part == 2) {
				c1s_Cutscene(14);
			} else if (gp.s.swh[13] && gp.s.part == 2) {
				c1s_Cutscene(15);
			} else if (gp.s.swh[14] && gp.s.part == 3) {
				c1s_Cutscene(16);
			} else if (gp.s.swh[15] && gp.s.part == 3) {
				c1s_Cutscene(17);
			}
		}
	}

	public boolean hit(int map, int col, int row, String reqDirection) {
		boolean hit = false;
		if(map == gp.currentMap.getId()) {
			//try {
				gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
				gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
				eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
				eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;
				if(gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
					if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
						hit = true;
						previousEventX = gp.player.worldX;
						previousEventY = gp.player.worldY;
					}
				}
				gp.player.solidArea.x = gp.player.solidAreaDefaultX;
				gp.player.solidArea.y = gp.player.solidAreaDefaultY;
				eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
				eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
			//} catch(ArrayIndexOutOfBoundsException e) {
			//	e.printStackTrace();
			//}
		}
		return hit;
	}
	
	public boolean hitRow(int map, int row, String reqDirection) {
		boolean hit = false;
		if(map == gp.currentMap.getId()) {
			//try {
				gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
				gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
				int col = gp.player.solidArea.y/gp.tileSize;
				eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
				eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;
				if(gp.player.solidArea.y/gp.tileSize == eventRect[map][col][row].y/gp.tileSize && !eventRect[map][col][row].eventDone) {
					if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
						hit = true;
						previousEventX = gp.player.worldX;
						previousEventY = gp.player.worldY;
					}
				}
				gp.player.solidArea.x = gp.player.solidAreaDefaultX;
				gp.player.solidArea.y = gp.player.solidAreaDefaultY;
				eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
				eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
			//} catch(ArrayIndexOutOfBoundsException e) {
			//	e.printStackTrace();
			//}
		}
		return hit;
	}

	public void prologueCutscene(int read) {
		gp.gameState = GameState.cutsceneState;
		CutsceneManager cm = gp.csManager;
		cm.sceneNum = 1;
	}

	public void c1s_Cutscene(int read) {
		gp.gameState = GameState.cutsceneState;
		CutsceneManager cm = gp.csManager;
		cm.sceneNum = read;
	}

	public void message() {
		gp.gameState = GameState.messageState;
	}

	public void teleport(Map map, int col, int row) {
		gp.currentMap = map;
		gp.tileM = new TileManager(gp);
		gp.eHandler = new EventHandler(gp);
		gp.player.worldX = gp.tileSize * col;
		gp.player.worldY = gp.tileSize * row;
		previousEventX = gp.player.worldX;
		previousEventY = gp.player.worldY;
		canTouchEvent = false;
	}
	
	public void speak(Entity entity) {
		if(gp.keyH.enterPressed) {
			gp.gameState = GameState.dialogueState;
			entity.speak();
		}
	}
	
	public void illusion(InteractiveTile iTile) {
		if(iTile.id == IT_StoneDoor.intId) {}
	}
	
	public void obsticle(Map map) {
		gp.gameState = GameState.examineState;
		if(map.getId() == 0) {
			eventMaster.startDialogue(eventMaster, 2);
		} else if(map.getId() == 1) {
			eventMaster.startDialogue(eventMaster, 0);
		} else if(map.getId() == 2) {
			eventMaster.startDialogue(eventMaster, 1);
		}
		gp.keyH.enterPressed = false;
	}
}