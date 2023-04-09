package wildwyrd.game;

import wildwyrd.game.cutscenes.CutsceneManager;
import wildwyrd.game.tile.Map;
import wildwyrd.game.tile.TileManager;

public class EventHandler {
	GamePanel gp;
	EventRect[][][] eventRect;
	int previousEventX, previousEventY;
	int eventRectDefaultX;
	int eventRectDefaultY;
	boolean canTouchEvent = true;

	public EventHandler(GamePanel gp) {
		this.gp = gp;
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
				if(hit(0,14,9,"up")) {teleport(gp.maps[1],15,3);}
				if(hit(0,11,11,"down")) {teleport(gp.maps[2],15,4);}
				if(hit(0,17,10,"down")) {teleport(gp.maps[2],24,3);}
			}
			else if(gp.currentMap.getId() == 1) {
				if(hit(1,15,4,"down")) {teleport(gp.maps[0],14,10);}
			}
			else if(gp.currentMap.getId() == 2) {
				if(hit(2,15,3,"up")) {teleport(gp.maps[0],11,10);}
				if(hit(2,24,2,"up")) {teleport(gp.maps[0],17,9);}
				if(hit(2,12,16,"down")) {teleport(gp.maps[3],3,1);}
				if(hit(2,13,16,"down")) {teleport(gp.maps[3],4,1);}
			}
			else if(gp.currentMap.getId() == 3) {
				if(hit(3,3,0,"up")) {teleport(gp.maps[2],12,18);}
				if(hit(3,4,0,"up")) {teleport(gp.maps[2],13,18);}
			}
		}
		//System.out.println(gp.player.worldX + " " + gp.player.worldY);

	}

	public void checkCutscene() {
		if (this.gp.s.chapter == 0 && this.gp.s.swh[0]) {
			this.prologueCutscene(0);
		}

		if (this.gp.s.chapter == 1 && this.gp.s.swh[0]) {
			this.c1s_Cutscene(0);
		}

	}

	public boolean hit(int map, int col, int row, String reqDirection) {
		boolean hit = false;
		if(map == gp.currentMap.getId()) {
			//System.out.println(gp.currentMap.getMaxWorldCol());
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
		}
		return hit;
	}

	public void prologueCutscene(int read) {
		GamePanel gp = this.gp;
		gp.gameState = GameState.cutsceneState;
		CutsceneManager var2 = this.gp.csManager;
		var2.sceneNum = 1;
	}

	public void c1s_Cutscene(int read) {
		GamePanel gp = this.gp;
		gp.gameState = GameState.cutsceneState;
		//System.out.println("Get Chapter 1");
		CutsceneManager var2 = gp.csManager;
		var2.sceneNum = 2;
	}

	public void message() {
		GamePanel gp = this.gp;
		gp.gameState = GameState.messageState;
	}

	public void teleport(Map map, int col, int row) {
		gp.currentMap = map;
		gp.tileM = new TileManager(gp);
		gp.eHandler = new EventHandler(gp);
		//System.out.println(gp.player.worldX + " " + gp.player.worldY);
		gp.player.worldX = gp.tileSize * col;
		gp.player.worldY = gp.tileSize * row;
		//System.out.println(col + " " + row);
		//System.out.println(gp.player.worldX + " " + gp.player.worldY);
		previousEventX = gp.player.worldX;
		previousEventY = gp.player.worldY;
		canTouchEvent = false;
	}
}