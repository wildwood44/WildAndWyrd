package wildwyrd.game.cutscenes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.object.Obj_Alder_Bed;
import wildwyrd.game.playable.PlayerDummy;
import wildwyrd.npc.NPC;
import wildwyrd.npc.NPC_Florence;
import wildwyrd.npc.NPC_Kyla;
import wildwyrd.npc.NPC_Thay;

public class CutsceneManager {
	GamePanel gp;
	Graphics2D g2;
	public int sceneNum;
	public int scenePhase;
	public int counter = 0;
	public float alpha = 0f; 
	int y;
	public String endCredit = "Written and developed by James Stockwell";
	public int read = 0;
	public final int NA = 0;
	public final int prologue = 1;
	public final int chapter1Start = 2;
	public BufferedImage background;
	private NPC actor;

	public CutsceneManager(GamePanel gp) {
		this.gp = gp;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		switch (sceneNum) {
			case 1 :
				scene_prologue();
				break;
			case 2 :
				scene_c1S();
				break;
			case 3 :
				scene_c1_1();
				break;
			case 4 :
				scene_c1_2();
				break;
			case 5 :
				scene_c1_3();
				break;
			case 6 :
				scene_c1_4();
				break;
			case 7 :
				scene_ending();
				break;
		}

	}

	private void scene_prologue() {
		System.out.println(gp.s.chapter + " " + gp.s.part + " " + read);
		if (scenePhase == 0) {
			gp.rm[gp.currentRoom].draw(g2);
			gp.cutsceneOn = true;
			gp.ui.drawHeadingScreen("Prologue");
			gp.ui.drawMessageScreen(
					"The game will now begin. Press ENTER to continue. You may skip the dialogue by pressing S.");
			gp.c.setSprites();
		} else if (scenePhase == 1) {
			//gp.ui.drawBackground("/res/backgrounds/Forton_Backgound.png");
			//gp.currentRoom = new Rm_Forton(gp);
			gp.currentRoom = 1;
			gp.rm[gp.currentRoom].draw(g2);
			gp.c.setCutscene(0, read);
			gp.ui.selectedObject = gp.c;
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 2) {
			gp.currentRoom = 0;
			gp.s.chapter = 1;
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.gameState = GameState.playState;
		}

	}

	private void scene_c1S() {
		if (scenePhase == 0) {
			gp.rm[gp.currentRoom].draw(g2);
			gp.cutsceneOn = true;
			gp.ui.drawHeadingScreen("Chapter " + gp.s.chapter);
			gp.ui.drawMessageScreen("");
		} else if (scenePhase == 1) {
			gp.cutsceneOn = true;
			gp.c.setCutscene(1, read);
			gp.c.dialogueSet = 1;
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 2) {
			gp.s.swh[read] = false;
			gp.s.swh[1] = true;
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			//gp.s.part = 2;
			gp.gameState = GameState.playState;
		}

	}
	
	private void scene_c1_1() {
		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			read = 1;
			gp.c.setCutscene(2, read);
			gp.c.dialogueSet = 2;
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			gp.player.drawing = false;
			drawRoom();
			scenePhase++;
		} else if (scenePhase == 1) {
			gp.ui.selectedObject = gp.c;
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 2) {
			drawRoom();
			actor = getActor(PlayerDummy.npcName);
			actor.update();
			actor.worldY += 1;
			if(actor.worldY > gp.tileSize * 5){
				scenePhase++;
			}
		} else if (scenePhase == 3) {
			drawRoom();
			actor = getActor(NPC_Thay.npcName);
			actor.direction = "left";
			actor.update();
			actor.worldX -= 1;
			if(actor.worldX < gp.tileSize * 14){
				scenePhase++;
			}
		} else if (scenePhase == 4) {
			drawRoom();
			actor = getActor(NPC_Thay.npcName);
			actor.direction = "up";
			actor.update();
			actor.worldY -= 1;
			if(actor.worldY < gp.tileSize * 3){
				scenePhase++;
			}
		} else if (scenePhase == 5) {
			drawRoom();
			destroyActor(NPC_Thay.npcName);
			gp.player.worldY += 2;
			if(gp.player.worldY > gp.tileSize * 5){
				scenePhase++;
			}
		} else if (scenePhase == 6) {
			gp.s.swh[read] = false;
			gp.player.drawing = false;
			gp.cutsceneOn = false;
			destroyPlayerDummy();
			gp.player.drawing = true;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.s.part = 2;
			gp.s.c1Switch[0] = false;
			gp.gameState = GameState.playState;
		}
	}
	
	private void scene_c1_2() {

		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			read = 2;
			gp.c.setCutscene(3, read);
			gp.c.dialogueSet = 3;
			createActor(new NPC_Thay(gp), gp.tileSize * 14, gp.tileSize * 3, "down");
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			gp.player.drawing = false;
			drawRoom();
			scenePhase++;
		} else if (scenePhase == 1) {
			drawRoom();
			actor = getActor(PlayerDummy.npcName);
			if((actor.worldX >= gp.tileSize * 13 &&
				actor.worldX <= gp.tileSize * 16) &&
				actor.worldY < gp.tileSize * 6) {
				if(moveActor(actor.name, "down", 4)){
					scenePhase++;
				}
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 2) {
			drawRoom();
			actor = getActor(NPC_Thay.npcName);
			if(moveActor(actor.name, "down", 4)){
				scenePhase++;
			}
		} else if (scenePhase == 3) {
			createActor(new NPC_Kyla(gp), gp.tileSize * 14, gp.tileSize * 3, "down");
			scenePhase++;
		} else if (scenePhase == 4) {
			drawRoom();
			changeActorDirection(NPC_Thay.npcName, "up");
			drawRoom();
			scenePhase++;
		} else if (scenePhase == 5) {
			gp.ui.selectedObject = gp.c;
			gp.ui.drawDialogueScreen();
			
		} else if (scenePhase == 6) {
			drawRoom();
			actor = getActor(NPC_Thay.npcName);
			if(moveActor(actor.name, "right", 15)){
				actor.direction = "down";
				scenePhase++;
			}
		} else if (scenePhase == 7) {
			drawRoom();
			destroyActor(NPC_Kyla.npcName);
			actor = getActor(NPC_Florence.npcName);
			if(moveActor(actor.name, "right", 14)){
				scenePhase++;
			}
		} else if (scenePhase == 8) {
			drawRoom();
			actor = getActor(NPC_Florence.npcName);
			if(moveActor(actor.name, "up", 3)){
				scenePhase++;
			}
		} else if (scenePhase == 9) {
			gp.s.swh[read] = false;
			gp.player.drawing = false;
			gp.cutsceneOn = false;
			destroyPlayerDummy();
			destroyActor(NPC_Florence.npcName);
			gp.player.drawing = true;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.s.part = 2;
			gp.s.c1Switch[1] = false;
			gp.gameState = GameState.playState;
		}
	}
	
	private void scene_c1_3() {
		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			read = 3;
			gp.c.setCutscene(4, read);
			gp.c.dialogueSet = 4;
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			gp.player.drawing = false;
			drawRoom();
			scenePhase++;
		} else if (scenePhase == 1) {
			createActor(new NPC_Florence(gp), gp.tileSize * 14, gp.tileSize * 3, "down");
			scenePhase++;
		} else if (scenePhase == 2) {
			drawRoom();
			actor = getActor(NPC_Florence.npcName);
			actor.worldY += 1;
			if(moveActor(actor.name, "down", 4)){
				actor.direction = "right";
				actor.spriteNum = 1;
				actor.update();
				scenePhase++;
			}
			actor.update();
		} else if (scenePhase == 3) {
			changeActorDirection(PlayerDummy.npcName, "left");
			changeActorDirection(NPC_Thay.npcName, "left");
			drawRoom();
			scenePhase++;
		} else if (scenePhase == 4) {
			gp.ui.selectedObject = gp.c;
			if(gp.c.dialogueIndex < 2) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 5) {
			actor = getActor(PlayerDummy.npcName);
			NPC actor2 = getActor(NPC_Thay.npcName);
			if(actor.worldX > actor2.worldX) {
				actor.direction = "left";
				actor2.direction = "right";
			} else if(actor.worldX < actor2.worldX) {
				actor.direction = "right";
				actor2.direction = "left";
			} else if(actor.worldY > actor2.worldY) {
				actor.direction = "up";
				actor2.direction = "down";
			} else if(actor.worldY < actor2.worldY) {
				actor.direction = "down";
				actor2.direction = "up";
			}
			scenePhase++;
			actor.update();
			drawRoom();
		} else if (scenePhase == 6) {
			if(gp.c.dialogueIndex < 6) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 7) {
			changeActorDirection(PlayerDummy.npcName, "left");
			drawRoom();
			scenePhase++;
		} else if (scenePhase == 8) {
			gp.ui.drawDialogueScreen();
		}  else if (scenePhase == 9) {
			drawRoom();
			changeActorDirection(NPC_Thay.npcName, "down");
			actor = getActor(NPC_Florence.npcName);
			if(moveActor(actor.name, "left", 13)){
				actor.direction = "down";
				actor.spriteNum = 1;
				actor.update();
				scenePhase++;
			}
			actor.update();
		} else if (scenePhase == 10) {
			gp.s.swh[read] = false;
			gp.ui.resetSlots();
			destroyPlayerDummy();
			gp.player.drawing = true;
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.s.c1Switch[2] = false;
			gp.gameState = GameState.playState;
		}
	}
	
	private void scene_c1_4() {
		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			read = 4;
			gp.c.setCutscene(5, read);
			gp.c.dialogueSet = 5;
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			gp.player.drawing = false;
			drawRoom();
			scenePhase++;
		} else if (scenePhase == 1) {
			alpha += 0.005f;
			if(alpha > 1f) {
				alpha = 1f;
			}
			drawBlackBackground(alpha);
			if(alpha == 1f) {
				//alpha = 0;
				scenePhase++;
			}
		} else if (scenePhase == 2) {
			changeActorDirection(PlayerDummy.npcName, "right");
			changeActorDirection(NPC_Florence.npcName, "right");
			changeActorDirection(NPC_Thay.npcName, "left");
			scenePhase++;
		} else if (scenePhase == 3) {
			drawRoom();
			alpha -= 0.05f;
			if(alpha < 0f) {
				alpha = 0f;
			}
			drawBlackBackground(alpha);
			if(alpha == 0f) {
				scenePhase++;
			}
		} else if (scenePhase == 4) {
			gp.ui.selectedObject = gp.c;
			if(gp.c.dialogueIndex < 5) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 5) {
			drawRoom();
			NPC actor = getActor(NPC_Thay.npcName);
			if(gp.c.dialogueIndex >= 7 &&
				moveActor(actor.name, "down", 7)){
				alpha += 0.005f;
				if(alpha > 1f) {
					alpha = 1f;
				}
				drawBlackBackground(alpha);
				if(alpha == 1f) {
					destroyActor(NPC_Florence.npcName);
					scenePhase++;
				}
				
			} if(gp.c.dialogueIndex < 7) {
				gp.ui.drawDialogueScreen();
			}
		} else if (scenePhase == 6) {
			destroyActor(actor.name);
			createActor(new NPC_Thay(gp), 3, gp.tileSize * 12, gp.tileSize * 6, "down");
			gp.currentMap = gp.maps[3];
			gp.player.worldX = gp.tileSize * 12;
			gp.player.worldY = gp.tileSize * 7;
			drawRoom();
			alpha -= 0.05f;
			if(alpha < 0f) {
				alpha = 0f;
			}
			drawBlackBackground(alpha);
			if(alpha == 0f) {
				scenePhase++;
			}
		} else if (scenePhase == 7) {
			actor = getActor(NPC_Thay.npcName);
			if(moveActor(actor.name, "down", 7)) {
				scenePhase++;
			}
		} else if (scenePhase == 8) {
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 9) {
			drawRoom();
			alpha += 0.005f;
			if(alpha > 1f) {
				alpha = 1f;
			}
			drawBlackBackground(alpha);
			if(alpha == 1f) {
				gp.currentMap = gp.maps[2];
				actor = getActor(PlayerDummy.npcName);
				gp.player.worldX = actor.worldX;
				gp.player.worldY = actor.worldY;
				scenePhase++;
			}
		} else if (scenePhase == 10) {
			alpha -= 0.05f;
			if(alpha < 0f) {
				alpha = 0f;
			}
			drawBlackBackground(alpha);
			if(alpha == 0f) {
				scenePhase++;
			}
		} else if (scenePhase == 11) {
			createActor(new NPC_Kyla(gp), 0, gp.tileSize * 11, gp.tileSize * 5, "down");
			createActor(new NPC_Florence(gp), 0, gp.tileSize * 3, gp.tileSize * 5, "down");
			gp.s.swh[read] = false;
			gp.ui.resetSlots();
			destroyPlayerDummy();
			gp.player.drawing = true;
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.s.c1Switch[4] = false;
			gp.gameState = GameState.playState;
		}
	}
	
	private void scene_ending() {
		if (scenePhase == 0) {
			gp.ui.selectedObject = new Obj_Alder_Bed(gp);
			scenePhase++;
		} else if (scenePhase == 1) {
			gp.ui.selectedObject.dialogueSet = 2;
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 2) {
			if(counterReached(300) == true) {
				scenePhase++;
			}
		} else if (scenePhase == 3) {
			alpha += 0.005f;
			if(alpha > 1f) {
				alpha = 1f;
			}
			drawBlackBackground(alpha);
			if(alpha == 1f) {
				alpha = 0;
				scenePhase++;
			}
		} else if (scenePhase == 4) {
			drawBlackBackground(alpha);
			alpha += 0.005f;
			if(alpha > 1f) {
				alpha = 1f;
			}
			String text = "Thank you for playing.\nThere is more coming soon.";
			drawString(alpha, 30f, 200, text, 70);
			if(counterReached(600)) {
				scenePhase++;
			}
		} else if (scenePhase == 5) {
			drawBlackBackground(1f);
			drawString(1f, 90f, gp.screenHeight/2, "Wild and Wyrd", 40);
			if(counterReached(400)) {
				scenePhase++;
			}
		} else if (scenePhase == 6) {
			drawBlackBackground(1f);
			y = gp.screenHeight/2;
			drawString(1f, 30f, y, endCredit, 40);
			if(counterReached(400)) {
				scenePhase++;
			}
		} else if (scenePhase == 7) {
			drawBlackBackground(1f);
			y--; 
			drawString(1f, 30f, y, endCredit, 40);
			if(counterReached(800)) {
				scenePhase++;
			}
		} else if (scenePhase == 8) {
			System.exit(0);
		}
	}
	
	private void createActor(NPC npc, int x, int y, String direction) {
		for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
			if(gp.npc[gp.currentMap.getId()][i] == null) {
				gp.npc[gp.currentMap.getId()][i] = npc;
				gp.npc[gp.currentMap.getId()][i].worldX = x;
				gp.npc[gp.currentMap.getId()][i].worldY = y;
				gp.npc[gp.currentMap.getId()][i].direction = direction;
				break;
			}
		}
	}
	
	private void createActor(NPC npc, int map, int x, int y, String direction) {
		for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
			if(gp.npc[map][i] == null) {
				gp.npc[map][i] = npc;
				gp.npc[map][i].worldX = x;
				gp.npc[map][i].worldY = y;
				gp.npc[map][i].direction = direction;
				break;
			}
		}
	}
	
	private NPC getActor(String npcName) {
		for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
			if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == npcName) {
				return gp.npc[gp.currentMap.getId()][i];
			}
		}
		return null;
	}
	
	private void destroyActor(String npcName) {
		for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
			if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == npcName) {
				gp.npc[gp.currentMap.getId()][i] = null;
				break;
			}
		}
	}
	
	private void destroyPlayerDummy() {
		for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
			if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == PlayerDummy.npcName) {
				gp.player.worldX = gp.npc[gp.currentMap.getId()][i].worldX;
				gp.player.worldY = gp.npc[gp.currentMap.getId()][i].worldY;
				gp.player.direction = gp.npc[gp.currentMap.getId()][i].direction;
				gp.npc[gp.currentMap.getId()][i] = null;
				break;
			}
		}
	}
	
	private void changeActorDirection(String npcName, String direction) {
		NPC actor = getActor(npcName);
		actor.direction = direction;
		actor.update();
	}
	
	private boolean moveActor(String npcName, String direction, int move) {
		boolean moveComplete = false;
		actor = getActor(npcName);
		actor.direction = direction;
		switch(actor.direction) {
		case "up":
			actor.worldY -= 1;
			if(actor.worldY < gp.tileSize * move){
				moveComplete = true;
			}
			break;
		case "down":
			actor.worldY += 1;
			if(actor.worldY > gp.tileSize * move){
				moveComplete = true;
			}
			break;
		case "left":
			actor.worldX -= 1;
			if(actor.worldX < gp.tileSize * move){
				moveComplete = true;
			}
			break;
		case "right":
			actor.worldX += 1;
			if(actor.worldX > gp.tileSize * move){
				moveComplete = true;
			}
			break;
		}
		actor.update();
		drawRoom();
		return moveComplete;
	}
	
	private void drawRoom() {
		gp.tileM.draw(g2);
		for(int i = 0; i < gp.iTile[gp.currentMap.getId()].length; i++) {
			if(gp.iTile[gp.currentMap.getId()][i] != null) {
				gp.iTile[gp.currentMap.getId()][i].draw(g2);
			}
		}
		//OBJECTS
		for (int i = 0; i < gp.obj[gp.currentMap.getId()].length; i++) {
			if (gp.obj[gp.currentMap.getId()][i] != null) {
				gp.entityList.add(gp.obj[gp.currentMap.getId()][i]);
				//this.obj[1][i].draw(g2, this);
			}
		}
		//NPC
		for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
			if (gp.npc[gp.currentMap.getId()][i] != null) {
				//entityList.add(npc[currentMap.getId()][i]);
				gp.npc[gp.currentMap.getId()][i].draw(g2);
			}
		}
	}
	
	public boolean counterReached(int target) {
		boolean counterReached = false;
		counter++;
		if(counter > target) {
			counterReached = true;
			counter = 0;
		}
		return counterReached;
	}
	
	public void drawBlackBackground(float alpha) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
	public void drawString(float alpha, float fontSize, int y, String text, int lineHeight) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(fontSize));
		for (String line : text.split("\n")) {
			int x = gp.ui.getXforCenteredText(line);
			g2.drawString(line, x, y);
			y += lineHeight;
		}
	}
}