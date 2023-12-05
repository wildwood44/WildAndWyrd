package wildwyrd.game.cutscenes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import wildwyrd.game.EventHandler;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.items.Item;
import wildwyrd.game.items.Itm_Bug_Meat;
import wildwyrd.game.items.Weapon;
import wildwyrd.game.npc.NPC;
import wildwyrd.game.npc.NPC_Florence;
import wildwyrd.game.npc.NPC_Kyla;
import wildwyrd.game.npc.NPC_Thay;
import wildwyrd.game.npc.NPC_Trissie;
import wildwyrd.game.object.Obj_Alder_Bed;
import wildwyrd.game.object.Obj_Dummy;
import wildwyrd.game.playable.PlayerDummy;
import wildwyrd.game.tile.TileManager;

public class CutsceneManager {
	GamePanel gp;
	Graphics2D g2;
	public int sceneNum;
	public int scenePhase;
	public int counter = 0;
	public float alpha = 0f; 
	int y;
	public String endCredit = "Written and developed by wildwood44";
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
				scene_c2_1();
				break;
			case 8 :
				scene_c2_2();
				break;
			case 9 :
				scene_c2_3();
				break;
			case 10 :
				scene_c2_4();
				break;
			case 11 :
				scene_ending();
				break;
		}

	}

	private void scene_prologue() {
		if (scenePhase == 0) {
			gp.rm[gp.currentRoom].draw(g2);
			gp.cutsceneOn = true;
			gp.ui.drawHeadingScreen("Prologue");
			gp.ui.drawMessageScreen(
					"The game will now begin. Press ENTER to continue. You may skip dialogue by pressing Q.");
			gp.c.setSprites(0);
		} else if (scenePhase == 1) {
			//gp.ui.drawBackground("/res/backgrounds/Forton_Backgound.png");
			//gp.currentRoom = new Rm_Forton(gp);
			gp.currentRoom = 1;
			gp.rm[gp.currentRoom].draw(g2);
			gp.c.setCutscene(0, read);
			gp.ui.selectedObject = gp.c;
			scenePhase++;
		} else if (scenePhase == 2) {
			if(gp.c.dialogueIndex < 59) {
				gp.ui.drawDialogueScreen();
			} else {
				gp.rm[gp.currentRoom].draw(g2);
				scenePhase++;
			}
		} else if (scenePhase == 3) {
			gp.rm[gp.currentRoom].scrollDown();
		} else if (scenePhase == 4) {
			//gp.rm[gp.currentRoom].draw(g2);
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 5) {
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
			gp.c.setSprites(1);
		} else if (scenePhase == 1) {
			gp.cutsceneOn = true;
			gp.c.setCutscene(1, read);
			gp.c.dialogueSet = 1;
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 2) {
			gp.glossary.unlock("mammal", "wood mouse");
			gp.glossary.unlock("mammal", "field vole");
			gp.glossary.unlock("mammal", "european hedgehog");
			gp.s.swh[read] = false;
			gp.s.swh[1] = true;
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
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
			gp.c.setSprites(gp.c.dialogueSet);
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
			gp.c.setSprites(gp.c.dialogueSet);
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
			gp.glossary.unlock("folklore", "witch");
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
			gp.c.setSprites(gp.c.dialogueSet);
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
			gp.c.setSprites(gp.c.dialogueSet);
			scenePhase++;
		} else if (scenePhase == 1) {
			if(fadeOut(0.005f)) {
				scenePhase++;
			}
		} else if (scenePhase == 2) {
			changeActorDirection(PlayerDummy.npcName, "right");
			changeActorDirection(NPC_Florence.npcName, "right");
			changeActorDirection(NPC_Thay.npcName, "left");
			scenePhase++;
		} else if (scenePhase == 3) {
			drawRoom();
			drawBlackBackground(alpha);
			if(fadeIn(0.05f)) {
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
			if(gp.c.dialogueIndex >= 7){
				moveActor(actor.name, "down", 7);
				if(fadeOut(0.005f)) {
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
			if(fadeIn(0.05f)) {
				scenePhase++;
			}
		} else if (scenePhase == 7) {
			drawRoom();
			actor = getActor(NPC_Thay.npcName);
			if(moveActor(actor.name, "down", 7)) {
				scenePhase++;
			}
		} else if (scenePhase == 8) {
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 9) {
			drawRoom();
			if(fadeOut(0.005f)) {
				gp.currentMap = gp.maps[2];
				actor = getActor(PlayerDummy.npcName);
				gp.player.worldX = actor.worldX;
				gp.player.worldY = actor.worldY;
				scenePhase++;
			}
		} else if (scenePhase == 10) {
			if(fadeIn(0.05f)) {
				scenePhase++;
			}
		} else if (scenePhase == 11) {
			createActor(new NPC_Kyla(gp), 0, gp.tileSize * 11, gp.tileSize * 5, "down");
			createActor(new NPC_Florence(gp), 0, gp.tileSize * 3, gp.tileSize * 5, "down");
			gp.playable.get(0).setWeapon_prime(new Weapon(gp));
			Item selectedItem = gp.player.inventory.get(gp.player.searchItemInInventory(Itm_Bug_Meat.itemId));
			gp.player.removeFromInventory(selectedItem);
			gp.player.removeFromInventory(selectedItem);
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
	
	private void scene_c2_1() {
		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			read = 5;
			gp.c.setCutscene(6, read);
			gp.c.dialogueSet = 6;
			gp.player.drawing = false;
			//createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			//drawRoom();
			gp.c.setSprites(gp.c.dialogueSet);
			scenePhase++;
		} else if (scenePhase == 1) {
			gp.ui.selectedObject = new Obj_Alder_Bed(gp);
			scenePhase++;
		} else if (scenePhase == 2) {
			gp.ui.selectedObject.dialogueSet = 2;
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 3) {
			if(fadeOut(0.005f)) {
				scenePhase++;
			}
		} else if (scenePhase == 4) {
			gp.currentMap = gp.maps[2];
			gp.player.worldX = gp.tileSize * 16;
			gp.player.worldY = gp.tileSize * 7;
			gp.tileM = new TileManager(gp);
			gp.eHandler = new EventHandler(gp);
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			scenePhase++;
		} else if (scenePhase == 5) {
			drawRoom();
			if(fadeIn(0.05f)) {
				scenePhase++;
			}
			drawBlackBackground(alpha);
			gp.ui.drawHeadingScreen("Chapter " + gp.s.chapter);
		} else if (scenePhase == 6) {
			gp.ui.selectedObject = gp.c;
			if(gp.c.dialogueIndex < 5) {
				gp.ui.drawDialogueScreen();
			} else {
				createActor(new NPC_Trissie(gp), (int) (gp.tileSize * 16.1), gp.tileSize * 5, "down");
				actor = getActor(NPC_Trissie.npcName);
				actor.climbing(true);
				actor.getImage();
				scenePhase++;
			}
		} else if (scenePhase == 7) {
			drawRoom();
			if(moveActor(actor.name, "down", 6)) {
				actor.climbing(false);
				actor.getImage();
				actor = getActor(PlayerDummy.npcName);
				changeActorDirection(actor.name, "up");
				drawRoom();
				scenePhase++;
			}
		} else if (scenePhase == 8) {
			if(gp.c.dialogueIndex < 20) {
				gp.ui.drawDialogueScreen();
			} else {
				drawRoom();
				scenePhase++;
			}
		} else if (scenePhase == 9) {
			drawRoom();
			actor = getActor(NPC_Trissie.npcName);
			if(moveActor(actor.name, "left", 14)) {
				scenePhase++;
			}
		} else if (scenePhase == 10) {
			drawRoom();
			if(moveActor(actor.name, "up", 5));
			gp.player.worldY -= 2;
			if(gp.player.worldY < gp.tileSize * 5) {
				createActor(new NPC_Florence(gp), gp.tileSize * 14, gp.tileSize * 3, "down");
				drawRoom();
				scenePhase++;
			}
		} else if (scenePhase == 11) {
			gp.ui.drawDialogueScreen();
			destroyActor(NPC_Florence.npcName);
		} else if (scenePhase == 12) {
			drawRoom();
			if(moveActor(actor.name, "up", 3)) {
				destroyActor(actor.name);
				scenePhase++;
			}
		} else if (scenePhase == 13) {
			gp.glossary.unlock("mammal", "red squirrel");
			gp.s.swh[read] = false;
			gp.s.swh[6] = true;
			gp.ui.resetSlots();
			destroyPlayerDummy();
			gp.player.drawing = true;
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			//gp.s.part = 2;
			gp.gameState = GameState.playState;
		}
	}
	
	private void scene_c2_2() {
		if (scenePhase == 0) {
			if(fadeOut(0.005f)) {
				scenePhase++;
			}
		} else if (scenePhase == 1) {
			gp.cutsceneOn = true;
			read = 6;
			gp.c.setCutscene(7, read);
			gp.c.dialogueSet = 7;
			gp.player.worldX =  gp.tileSize*10;
			gp.player.worldY =  gp.tileSize*3;
			gp.player.direction = "down";
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			createActor(new NPC_Trissie(gp), gp.tileSize * 9, gp.tileSize * 5, "right");
			changeActorDirection(NPC_Kyla.npcName, "left");
			destroyActor(NPC_Florence.npcName);
			createActor(new NPC_Florence(gp), gp.tileSize * 9, gp.tileSize * 3, "down");
			gp.player.drawing = false;
			//drawRoom();
			gp.c.setSprites(gp.c.dialogueSet);
			scenePhase++;
		} else if (scenePhase == 2) {
			//drawRoom();
			gp.player.worldY = gp.tileSize * 5;
			drawRoom();
			if(fadeIn(0.05f)) {
				scenePhase++;
			}
			drawBlackBackground(alpha);
		} else if (scenePhase == 3) {
			gp.ui.selectedObject = gp.c;
			if(gp.c.dialogueIndex < 20) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 4) {
			changeActorDirection(PlayerDummy.npcName, "left");
			changeActorDirection(NPC_Florence.npcName, "right");
			drawRoom();
			scenePhase++;
		} else if (scenePhase == 5) {
			if(gp.c.dialogueIndex < 26) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 6) {
			changeActorDirection(PlayerDummy.npcName, "down");
			changeActorDirection(NPC_Florence.npcName, "down");
			drawRoom();
			scenePhase++;
		} else if (scenePhase == 7) {
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 8) {
			drawRoom();
			if(moveActor(NPC_Trissie.npcName, "down", 9)) {
				destroyActor(NPC_Trissie.npcName);
				//createActor(NPC_Trissie.npcName, 3, NA, y, endCredit);
				scenePhase++;
			}
		} else if (scenePhase == 9) {
			if(moveCamera("up", 4, 1)) {
				scenePhase++;
			}
		} else if (scenePhase == 10) {
			createActor(new NPC_Trissie(gp), 2, gp.tileSize * 9, gp.tileSize * 5, "left");
			gp.obj[2][8] = new Obj_Dummy(gp);
			gp.obj[2][8].worldX = 8 * gp.tileSize;
			gp.obj[2][8].worldY = 5 * gp.tileSize;
			gp.s.swh[read] = false;
			gp.ui.resetSlots();
			destroyPlayerDummy();
			gp.player.drawing = true;
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.s.part = 3;
			gp.s.c2Switch[0] = false;
			gp.gameState = GameState.playState;
		}
	}
	
	private void scene_c2_3() {
		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			read = 7;
			gp.c.setCutscene(8, read);
			
			gp.c.dialogueSet = 8;
			gp.player.drawing = false;
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			drawRoom();
			gp.c.setSprites(gp.c.dialogueSet);
			actor = getActor(NPC_Trissie.npcName);
			scenePhase++;
		} else if (scenePhase == 1) {
			drawRoom();
			gp.ui.selectedObject = gp.c;
			if(gp.c.dialogueIndex < 2) {
				gp.ui.drawDialogueScreen();
				if(moveActor(actor.name, "right", (int)(16.1))) {};
				if(moveCamera("right", 16, 1)) {};
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 2) {
			actor = getActor(NPC_Trissie.npcName);
			scenePhase++;
		}  else if (scenePhase == 3) {
			actor.climbing(true);
			actor.getImage();
			if(moveActor(actor.name, "up", 5)) {
				destroyActor(actor.name);
				scenePhase++;
			}
		} else if (scenePhase == 4) {
			if(fadeOut(0.005f)) {
				destroyPlayerDummy();
				scenePhase++;
			}
		} else if (scenePhase == 5) {
			if(fadeIn(0.05f)) {
				gp.currentMap = gp.maps[1];
				gp.player.worldX = gp.tileSize * 7;
				gp.player.worldY = gp.tileSize * 4;
				gp.tileM = new TileManager(gp);
				gp.eHandler = new EventHandler(gp);
				createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
				//drawRoom();
				scenePhase++;
			}
		} else if (scenePhase == 6) {
			drawRoom();
			if(gp.c.dialogueIndex < 4) {
				gp.ui.drawDialogueScreen();
				if(moveActor(PlayerDummy.npcName, "left", 5)) {}
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 7) {
			if(fadeOut(0.005f)) {
				scenePhase++;
			}
		} else if (scenePhase == 8) {
			gp.currentRoom = 2;
			gp.rm[gp.currentRoom].draw(g2);
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 9) {
			gp.s.swh[read] = false;
			gp.s.swh[8] = true;
			gp.ui.resetSlots();
			destroyPlayerDummy();
			gp.player.drawing = true;
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.s.part = 4;
			gp.s.c2Switch[4] = false;
			gp.gameState = GameState.playState;
		}
		
	}
	
	private void scene_c2_4() {
		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			read = 8;
			gp.c.setCutscene(9, read);
			gp.c.dialogueSet = 9;
			gp.c.setSprites(gp.c.dialogueSet);
			gp.currentRoom = 2;
			gp.rm[gp.currentRoom].draw(g2);
			scenePhase++;
		} else if (scenePhase == 1) {
			gp.ui.selectedObject = gp.c;
			if(gp.c.dialogueIndex < 4) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 2) {
			gp.rm[gp.currentRoom].alpha += 0.05f;
			if(gp.rm[gp.currentRoom].alpha > 1f) {
				gp.rm[gp.currentRoom].alpha = 1f;
			}
			gp.rm[gp.currentRoom].draw(g2);
			if(gp.rm[gp.currentRoom].alpha == 1f) {
				scenePhase++;
			}
		} else if (scenePhase == 3) {
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 4) {
			gp.c.dialogueSet = 10;
			gp.c.dialogueIndex = 0;
			gp.c.setSprites(gp.c.dialogueSet);
			gp.cutsceneOn = false;
			gp.keyH.enterPressed = false;
			scenePhase++;
		} else if (scenePhase == 5) {
			gp.ui.selectedObject = gp.rm[gp.currentRoom];
			if(gp.ui.choiceSlot == 0) {
				gp.ui.selectedObject.interact();
			} else {
				gp.ui.selectedObject.choiceResponce();
			}
		} else if (scenePhase == 6) {
			gp.s.swh[read] = false;
			read = 9;
			gp.s.swh[read] = true;
			gp.s.part = 5;
			gp.c.setCutscene(10, read);
			gp.ui.selectedObject.dialogueIndex = 0;
			scenePhase++;
		} else if (scenePhase == 7) {
			gp.ui.selectedObject = gp.c;
			gp.ui.drawDialogueScreen();
			alpha = 0f;
		} else if (scenePhase == 8) {
			if(fadeOut(0.005f)) {
				scenePhase++;
			}
		} else if (scenePhase == 9) {
			gp.s.swh[read] = false;
			gp.ui.resetSlots();
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.s.chapter = 3;
			gp.s.part = 1;
			gp.gameState = GameState.playState;
		}
	}
	
	private void scene_ending() {
		if (scenePhase == 0) {
			if(counterReached(300) == true) {
				scenePhase++;
			}
		} else if (scenePhase == 1) {
			if(fadeOut(0.005f)) {
				scenePhase++;
			}
		} else if (scenePhase == 2) {
			drawBlackBackground(alpha);
			alpha += 0.005f;
			if(alpha > 1f) {
				alpha = 1f;
			}
			String text = "Thank you for playing.\nThere is more coming soon.";
			drawString(alpha, 30f, 200, text, 70);
			if(counterReached(300)) {
				scenePhase++;
			}
		} else if (scenePhase == 3) {
			drawBlackBackground(1f);
			drawString(1f, 90f, gp.screenHeight/2, "Wild and Wyrd", 40);
			if(counterReached(300)) {
				scenePhase++;
			}
		} else if (scenePhase == 4) {
			drawBlackBackground(1f);
			y = gp.screenHeight/2;
			drawString(1f, 30f, y, endCredit, 40);
			if(counterReached(400)) {
				scenePhase++;
			}
		} else if (scenePhase == 5) {
			drawBlackBackground(1f);
			y--; 
			drawString(1f, 30f, y, endCredit, 40);
			if(counterReached(400)) {
				scenePhase++;
			}
		} else if (scenePhase == 6) {
			System.exit(0);
		}
	}
	
	//Create npc
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
	
	//Create npc on another map
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
	
	//Select npc
	private NPC getActor(String npcName) {
		for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
			if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == npcName) {
				return gp.npc[gp.currentMap.getId()][i];
			}
		}
		return null;
	}
	
	//Destroy npc
	private void destroyActor(String npcName) {
		for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
			if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == npcName) {
				gp.npc[gp.currentMap.getId()][i] = null;
				break;
			}
		}
	}
	
	//Destroy dummy player
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
	
	//Change direction npc faces
	private void changeActorDirection(String npcName, String direction) {
		NPC actor = getActor(npcName);
		actor.direction = direction;
		actor.update();
	}
	
	//Move a npc in a direction to tile * tileSize
	private boolean moveActor(String npcName, String direction, int move) {
		boolean moveComplete = false;
		actor = getActor(npcName);
		actor.direction = direction;
		switch(actor.direction) {
		case "up":
			if(actor.worldY < gp.tileSize * move){
				moveComplete = true;
			} else {actor.worldY -= 1;}
			break;
		case "down":
			if(actor.worldY > gp.tileSize * move){
				moveComplete = true;
			} else {actor.worldY += 1;}
			break;
		case "left":
			if(actor.worldX < gp.tileSize * move){
				moveComplete = true;
			} else {actor.worldX -= 1;}
			break;
		case "right":
			if(actor.worldX > gp.tileSize * move){
				moveComplete = true;
			} else {actor.worldX += 1;}
			break;
		}
		if(!moveComplete) {
			actor.update();
		}
		//drawRoom();
		return moveComplete;
	}
	
	private boolean moveCamera(String direction, int move, int speed) {
		boolean moveComplete = false;
		//actor = getActor(npcName);
		gp.player.direction = direction;
		switch(gp.player.direction) {
		case "up":
			gp.player.worldY -= speed;
			if(gp.player.worldY < gp.tileSize * move){
				moveComplete = true;
			}
			break;
		case "down":
			gp.player.worldY += speed;
			if(gp.player.worldY > gp.tileSize * move){
				moveComplete = true;
			}
			break;
		case "left":
			gp.player.worldX -= speed;
			if(gp.player.worldX < gp.tileSize * move){
				moveComplete = true;
			}
			break;
		case "right":
			gp.player.worldX += speed;
			if(gp.player.worldX > gp.tileSize * move){
				moveComplete = true;
			}
			break;
		}
		gp.player.update();
		//drawRoom();
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
				gp.obj[gp.currentMap.getId()][i].draw(g2);
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
	
	public boolean fadeOut(float fadeSpeed) {
		boolean fadeComp = false;
		alpha += fadeSpeed;
		if(alpha > 1f) {
			alpha = 1f;
		}
		drawBlackBackground(alpha);
		if(alpha == 1f) {
			fadeComp = true;
		}
		return fadeComp;
	}
	
	public boolean fadeIn(float fadeSpeed) {
		boolean fadeComp = false;
		alpha -= fadeSpeed;
		if(alpha < 0f) {
			alpha = 0f;
		}
		drawBlackBackground(alpha);
		if(alpha == 0f) {
			fadeComp = true;
		}
		drawBlackBackground(alpha);
		return fadeComp;
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