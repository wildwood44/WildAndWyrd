package wildwyrd.game.cutscenes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import wildwyrd.game.Entity;
import wildwyrd.game.EventHandler;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.effects.DayState;
import wildwyrd.game.items.Item;
import wildwyrd.game.items.Itm_Bug_Meat;
import wildwyrd.game.items.Itm_Leif;
import wildwyrd.game.items.Weapon;
import wildwyrd.game.npc.NPC;
import wildwyrd.game.npc.NPC_Florence;
import wildwyrd.game.npc.NPC_Gowl_Rat;
import wildwyrd.game.npc.NPC_Gowl_Sorcerer;
import wildwyrd.game.npc.NPC_Gowl_Weasel;
import wildwyrd.game.npc.NPC_Gowl_Weasel2;
import wildwyrd.game.npc.NPC_Jeb;
import wildwyrd.game.npc.NPC_Kyla;
import wildwyrd.game.npc.NPC_Rat_Siluette;
import wildwyrd.game.npc.NPC_Thay;
import wildwyrd.game.npc.NPC_Trissie;
import wildwyrd.game.object.Obj_Alder_Bed;
import wildwyrd.game.object.Obj_Dummy;
import wildwyrd.game.playable.PlayerDummy;
import wildwyrd.game.rooms.Prop;
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
	public Prop item;
	private NPC actor;
	public Entity projectile;

	public CutsceneManager(GamePanel gp) {
		this.gp = gp;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		switch (sceneNum) {
			case 1 : scene_prologue(); break;
			case 2 : scene_c1S(); break;
			case 3 : scene_c1_1(); break;
			case 4 : scene_c1_2(); break;
			case 5 : scene_c1_3(); break;
			case 6 : scene_c1_4(); break;
			case 7 : scene_c2_1(); break;
			case 8 : scene_c2_2(); break;
			case 9 : scene_c2_3(); break;
			case 10 : scene_c2_4(); break;
			case 11 : scene_c3_1(); break;
			case 12 : scene_c3_2(); break;
			case 13 : scene_c3_3(); break;
			case 14 : scene_c3_4(); break;
			case 15 : scene_c3_5(); break;
			case 16 : scene_c3_6(); break;
			case 17 : scene_ending(); break;
		}

	}
	//Prologue - introduce Dilecto and Dean
	private void scene_prologue() {
		if (scenePhase == 0) {
			gp.rm[gp.currentRoom].draw(g2);
			gp.cutsceneOn = true;
			gp.ui.drawHeadingScreen("Prologue");
			gp.ui.drawMessageScreen(
					"The game will now begin. Press ENTER to continue. You may skip dialogue by pressing Q.");
		} else if (scenePhase == 1) {
			gp.currentRoom = 1;
			gp.rm[gp.currentRoom].draw(g2);
			gp.c.setDialogue();
			gp.ui.selectedObject = gp.c;
			gp.playSE(20);
			scenePhase++;
		} else if (scenePhase == 2) {
			if(counterReached(100)) {
				scenePhase++;
			}
		} else if (scenePhase == 3) {
			if(gp.c.dialogueIndex < 50) {
				gp.ui.drawDialogueScreen();
			} else {
				gp.rm[gp.currentRoom].draw(g2);
				scenePhase++;
			}
		} else if (scenePhase == 4) {
			if(gp.c.dialogueIndex < 51) {
				gp.rm[gp.currentRoom].scrollDown();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 5) {
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 6) {
			if(fadeOut(0.005f)) {
				scenePhase++;
			}
		} else if (scenePhase == 7) {
			gp.currentRoom = 0;
			gp.s.chapter = 1;
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.gameState = GameState.playState;
		}

	}
	//Introduce Alder and Florence
	private void scene_c1S() {
		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			gp.player.drawing = false;
			drawStage();
			gp.ui.drawHeadingScreen("Chapter " + gp.s.chapter);
			gp.ui.drawMessageScreen("");
			// Forest rustling
		} else if (scenePhase == 1) {
			drawStage();
			gp.cutsceneOn = true;
			actor = getActor(NPC_Thay.npcName);
			changeActorDirection(actor.name, "left");
			if(counterReached(50)) {
				scenePhase++;
			}
		} else if (scenePhase == 2) {
			drawStage();
			changeActorDirection(actor.name, "right");
			if(counterReached(50)) {
				scenePhase++;
			}
		} else if (scenePhase == 3) {
			drawStage();
			if(moveActor(actor.name, "up", 4)) {
				actor.update();
				scenePhase++;
			}
			moveCamera("up", 4, 1);
		} else if (scenePhase == 4) {
			//gp.c.setCutscene(1, read);
			gp.c.dialogueSet = 1;
			gp.c.setDialogue();
			if(gp.c.dialogueIndex < 1) {
				gp.ui.drawDialogueScreen();
			} else {
				//Bird song
				//Illusion
				//Door
				gp.playSE(8);
				createActor(new NPC_Florence(gp), gp.tileSize * 12, gp.tileSize * 2, "down");
				drawStage();
				scenePhase++;
			}
		} else if (scenePhase == 5) {
			drawStage();
			actor = getActor(NPC_Florence.npcName);
			if(moveActor(actor.name, "down", 3)) {
				actor.update();
				scenePhase++;
			}
		} else if (scenePhase == 6) {
			drawStage();
			if(gp.c.dialogueIndex < 7) {
				gp.ui.drawDialogueScreen();
			} else {
				changeActorDirection(actor.name, "up");
				scenePhase++;
			}
		} else if (scenePhase == 7) {
			drawStage();
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 8) {
			actor.worldX = gp.tileSize * 11;
			actor.worldY = gp.tileSize * 3;
			actor.direction = "right";
			actor = getActor(NPC_Thay.npcName);
			actor.worldX = gp.tileSize * 13;
			actor.worldY = gp.tileSize * 3;
			actor.direction = "left";
			changeStage(0);
			gp.player.worldX = gp.tileSize * 4;
			gp.player.worldY = gp.tileSize * 6;
			gp.player.direction = "down";
			gp.glossary.unlock("mammal", "wood mouse");
			gp.glossary.unlock("mammal", "field vole");
			gp.glossary.unlock("mammal", "mole");
			gp.glossary.unlock("mammal", "european hedgehog");
			gp.s.swh[read] = false;
			gp.s.swh[1] = true;
			gp.cutsceneOn = false;
			gp.player.drawing = true;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.gameState = GameState.playState;
		}

	}
	//Alder meets Florence and Thay
	private void scene_c1_1() {
		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			read = 1;
			gp.c.dialogueSet = 2;
			gp.c.setDialogue();
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			actor = getActor(PlayerDummy.npcName);
			gp.player.drawing = false;
			drawStage();
			scenePhase++;
		} else if (scenePhase == 1) {
			if(counterReached(30)) {
				changeActorDirection(actor.getName(), "right");
				actor.update();
				drawStage();
				scenePhase++;
			}
		} else if (scenePhase == 2) {
			gp.ui.selectedObject = gp.c;
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 3) {
			drawStage();
			if(moveActor(actor.name, "down", 4)){
				actor.update();
				scenePhase++;
			}
		} else if (scenePhase == 4) {
			drawStage();
			actor = getActor(NPC_Thay.npcName);
			actor.direction = "left";
			actor.update();
			actor.worldX -= 1;
			if(actor.worldX < gp.tileSize * 12){
				scenePhase++;
			}
		} else if (scenePhase == 5) {
			drawStage();
			actor = getActor(NPC_Thay.npcName);
			actor.direction = "up";
			actor.update();
			actor.worldY -= 1;
			if(actor.worldY < gp.tileSize * 2){
				gp.playSE(8);
				scenePhase++;
			}
		} else if (scenePhase == 6) {
			drawStage();
			destroyActor(NPC_Thay.npcName);
			gp.player.worldY += 2;
			if(gp.player.worldY > gp.tileSize * 2){
				scenePhase++;
			}
		} else if (scenePhase == 7) {
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
	//Introduce Kyla
	private void scene_c1_2() {

		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			read = 2;
			//gp.c.setCutscene(3, read);
			gp.c.dialogueSet = 3;
			gp.c.setDialogue();
			createActor(new NPC_Thay(gp), gp.tileSize * 12, gp.tileSize * 2, "down");
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			gp.player.drawing = false;
			drawStage();
			gp.playSE(8);
			scenePhase++;
		} else if (scenePhase == 1) {
			drawStage();
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
			drawStage();
			actor = getActor(NPC_Thay.npcName);
			if(moveActor(actor.name, "down", 3)){
				scenePhase++;
			}
		} else if (scenePhase == 3) {
			createActor(new NPC_Kyla(gp), gp.tileSize * 12, gp.tileSize * 2, "down");
			gp.playSE(8);
			scenePhase++;
		} else if (scenePhase == 4) {
			drawStage();
			changeActorDirection(NPC_Thay.npcName, "up");
			drawStage();
			scenePhase++;
		} else if (scenePhase == 5) {
			gp.ui.selectedObject = gp.c;
			gp.ui.drawDialogueScreen();
			
		} else if (scenePhase == 6) {
			drawStage();
			actor = getActor(NPC_Thay.npcName);
			if(moveActor(actor.name, "right", 13)){
				actor.direction = "down";
				scenePhase++;
			}
		} else if (scenePhase == 7) {
			drawStage();
			destroyActor(NPC_Kyla.npcName);
			actor = getActor(NPC_Florence.npcName);
			if(moveActor(actor.name, "right", 12)){
				scenePhase++;
			}
		} else if (scenePhase == 8) {
			drawStage();
			actor = getActor(NPC_Florence.npcName);
			if(moveActor(actor.name, "up", 2)){
				gp.playSE(8);
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
	//Florence gives Alder a task
	private void scene_c1_3() {
		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			read = 3;
			gp.c.dialogueSet = 4;
			gp.c.setDialogue();
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			gp.player.drawing = false;
			drawStage();
			scenePhase++;
		} else if (scenePhase == 1) {
			createActor(new NPC_Florence(gp), gp.tileSize * 12, gp.tileSize * 2, "down");
			gp.playSE(8);
			scenePhase++;
		} else if (scenePhase == 2) {
			drawStage();
			actor = getActor(NPC_Florence.npcName);
			actor.worldY += 1;
			if(moveActor(actor.name, "down", 3)){
				actor.direction = "right";
				actor.spriteNum = 1;
				actor.update();
				scenePhase++;
			}
			actor.update();
		} else if (scenePhase == 3) {
			changeActorDirection(PlayerDummy.npcName, "left");
			changeActorDirection(NPC_Thay.npcName, "left");
			drawStage();
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
			drawStage();
		} else if (scenePhase == 6) {
			if(gp.c.dialogueIndex < 5) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 7) {
			changeActorDirection(PlayerDummy.npcName, "left");
			drawStage();
			scenePhase++;
		} else if (scenePhase == 8) {
			gp.ui.drawDialogueScreen();
		}  else if (scenePhase == 9) {
			drawStage();
			changeActorDirection(NPC_Thay.npcName, "down");
			actor = getActor(NPC_Florence.npcName);
			if(moveActor(actor.name, "left", 11)){
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
	//Thay leaves
	private void scene_c1_4() {
		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			read = 4;
			gp.c.dialogueSet = 5;
			gp.c.setDialogue();
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			gp.player.drawing = false;
			drawStage();
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
			drawStage();
			drawBlackBackground(alpha);
			if(fadeIn(0.05f)) {
				scenePhase++;
			}
		} else if (scenePhase == 4) {
			gp.ui.selectedObject = gp.c;
			if(gp.c.dialogueIndex < 4) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 5) {
			drawStage();
			NPC actor = getActor(NPC_Thay.npcName);
			if(gp.c.dialogueIndex >= 4){
				moveActor(actor.name, "down", 8);
				if(fadeOut(0.005f)) {
					destroyActor(NPC_Florence.npcName);
					scenePhase++;
				}
				
			} if(gp.c.dialogueIndex < 5) {
				gp.ui.drawDialogueScreen();
			}
		} else if (scenePhase == 6) {
			destroyActor(actor.name);
			createActor(new NPC_Thay(gp), 3, gp.tileSize * 12, gp.tileSize * 6, "down");
			gp.currentMap = gp.maps[3];
			gp.player.worldX = gp.tileSize * 11;
			gp.player.worldY = gp.tileSize * 7;
			drawStage();
			if(fadeIn(0.05f)) {
				createActor(new NPC_Rat_Siluette(gp), gp.tileSize * 12, gp.tileSize * 9, "up");
				scenePhase++;
			}
		} else if (scenePhase == 7) {
			drawStage();
			actor = getActor(NPC_Thay.npcName);
			if(moveActor(actor.name, "down", 7)) {
				scenePhase++;
			}
		} else if (scenePhase == 8) {
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 9) {
			drawStage();
			if(fadeOut(0.005f)) {
				destroyActor(actor.name);
				destroyActor(NPC_Rat_Siluette.npcName);
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
			createActor(new NPC_Kyla(gp), 0, gp.tileSize * 9, gp.tileSize * 5, "down");
			createActor(new NPC_Florence(gp), 0, gp.tileSize * 4, gp.tileSize * 4, "down");
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
	//Meet Trissie
	private void scene_c2_1() {
		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			read = 5;
			gp.c.dialogueSet = 6;
			gp.c.setDialogue();
			gp.player.drawing = false;
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
			gp.player.worldX = gp.tileSize * 15;
			gp.player.worldY = gp.tileSize * 6;
			gp.tileM = new TileManager(gp);
			gp.eHandler = new EventHandler(gp);
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			scenePhase++;
		} else if (scenePhase == 5) {
			drawStage();
			if(fadeIn(0.05f)) {
				scenePhase++;
			}
			drawBlackBackground(alpha);
			gp.ui.drawHeadingScreen("Chapter " + gp.s.chapter);
		} else if (scenePhase == 6) {
			gp.ui.selectedObject = gp.c;
			if(gp.c.dialogueIndex < 1) {
				gp.ui.drawDialogueScreen();
			} else {
				createActor(new NPC_Trissie(gp), (int) (gp.tileSize * 15.1), gp.tileSize * 4, "down");
				actor = getActor(NPC_Trissie.npcName);
				scenePhase++;
			}
		} else if (scenePhase == 7) {
			if(actor.climbing("down", 5)) {
				actor = getActor(PlayerDummy.npcName);
				changeActorDirection(actor.name, "up");
				drawStage();
				scenePhase++;
			}
			drawStage();
		} else if (scenePhase == 8) {
			if(gp.c.dialogueIndex < 13) {
				gp.ui.drawDialogueScreen();
			} else {
				drawStage();
				scenePhase++;
			}
		} else if (scenePhase == 9) {
			drawStage();
			actor = getActor(NPC_Trissie.npcName);
			if(moveActor(actor.name, "left", 12)) {
				scenePhase++;
			}
		} else if (scenePhase == 10) {
			drawStage();
			if(moveActor(actor.name, "up", 5));
			gp.player.worldY -= 2;
			if(gp.player.worldY < gp.tileSize * 5) {
				gp.playSE(8);
				createActor(new NPC_Florence(gp), gp.tileSize * 12, gp.tileSize * 2, "down");
				drawStage();
				scenePhase++;
			}
		} else if (scenePhase == 11) {
			gp.ui.drawDialogueScreen();
			//gp.playSE(8);
			destroyActor(NPC_Florence.npcName);
		} else if (scenePhase == 12) {
			drawStage();
			if(moveActor(actor.name, "up", 2)) {
				destroyActor(actor.name);
				gp.playSE(8);
				scenePhase++;
			}
		} else if (scenePhase == 13) {
			drawStage();
			gp.player.worldY += 2;
			if(gp.player.worldY > gp.tileSize * 6) {
				scenePhase++;
			}
		} else if (scenePhase == 14) {
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
			gp.gameState = GameState.playState;
		}
	}
	//Cottage meeting
	private void scene_c2_2() {
		if (scenePhase == 0) {
			if(fadeOut(0.005f)) {
				scenePhase++;
			}
		} else if (scenePhase == 1) {
			gp.cutsceneOn = true;
			read = 6;
			gp.c.dialogueSet = 7;
			gp.c.setDialogue();
			gp.player.worldX =  gp.tileSize*9;
			gp.player.worldY =  gp.tileSize*3;
			gp.player.direction = "down";
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			createActor(new NPC_Trissie(gp), gp.tileSize * 8, gp.tileSize * 5, "right");
			changeActorDirection(NPC_Kyla.npcName, "left");
			destroyActor(NPC_Florence.npcName);
			createActor(new NPC_Florence(gp), gp.tileSize * 8, gp.tileSize * 3, "down");
			gp.player.drawing = false;
			gp.c.setSprites(gp.c.dialogueSet);
			scenePhase++;
		} else if (scenePhase == 2) {
			drawStage();
			if(fadeIn(0.05f)) {
				scenePhase++;
			}
			drawBlackBackground(alpha);
		} else if (scenePhase == 3) {
			gp.ui.selectedObject = gp.c;
			if(gp.c.dialogueIndex < 14) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 4) {
			changeActorDirection(PlayerDummy.npcName, "left");
			changeActorDirection(NPC_Florence.npcName, "right");
			drawStage();
			scenePhase++;
		} else if (scenePhase == 5) {
			if(gp.c.dialogueIndex < 19) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 6) {
			changeActorDirection(PlayerDummy.npcName, "down");
			changeActorDirection(NPC_Florence.npcName, "down");
			drawStage();
			scenePhase++;
		} else if (scenePhase == 7) {
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 8) {
			drawStage();
			if(moveActor(NPC_Trissie.npcName, "down", 7)) {
				destroyActor(NPC_Trissie.npcName);
				gp.playSE(8);
				scenePhase++;
			}
		} else if (scenePhase == 9) {
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
	//Trissie leaves
	private void scene_c2_3() {
		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			read = 7;
			drawStage();
			gp.player.drawing = false;
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			actor = getActor(NPC_Trissie.npcName);
			scenePhase++;
		} else if (scenePhase == 1) {
			drawStage();
			gp.ui.selectedObject = gp.c;
			if(fadeOut(0.005f)) {
				scenePhase++;
			} else {
				if(moveActor(actor.name, "right", 15)) {};
				if(moveCamera("right", 15, 1)) {};
			}
		} else if (scenePhase == 2) {
			actor = getActor(NPC_Trissie.npcName);
			destroyActor(actor.name);
			destroyPlayerDummy();
			scenePhase++;
		}  else if (scenePhase == 3) {
			if(fadeIn(0.05f)) {
				gp.currentMap = gp.maps[0];
				gp.player.worldX = gp.tileSize * 9;
				gp.player.worldY = gp.tileSize * 3;
				gp.tileM = new TileManager(gp);
				gp.eHandler = new EventHandler(gp);
				createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
				destroyActor(NPC_Florence.npcName);
				destroyActor(NPC_Kyla.npcName);
				scenePhase++;
			}
		} else if (scenePhase == 4) {
			if(moveActor(PlayerDummy.npcName, "right", 10)) {}
			if(fadeOut(0.005f)) {
				scenePhase++;
			}
		} else if (scenePhase == 5) {
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
	//Hall of the Scion 1
	private void scene_c2_4() {
		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			read = 8;
			gp.c.dialogueSet = 8;
			gp.c.setDialogue();
			gp.c.setSprites(gp.c.dialogueSet);
			gp.currentRoom = 2;
			gp.rm[gp.currentRoom].draw(g2);
			gp.playMusic(33);
			scenePhase++;
		} else if (scenePhase == 1) {
			gp.ui.selectedObject = gp.c;
			if(gp.c.dialogueIndex < 3) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 2) {
			gp.rm[gp.currentRoom].props[0].alpha += 0.05f;
			if(gp.rm[gp.currentRoom].props[0].alpha > 1f) {
				gp.rm[gp.currentRoom].props[0].alpha = 1f;
			}
			gp.rm[gp.currentRoom].draw(g2);
			if(gp.rm[gp.currentRoom].props[0].alpha == 1f) {
				scenePhase++;
			}
		} else if (scenePhase == 3) {
			if(gp.c.dialogueIndex < 5) {
				gp.ui.drawDialogueScreen();
			} else {
				gp.rm[gp.currentRoom].actors[0].alpha += 0.05f;
				if(gp.rm[gp.currentRoom].actors[0].alpha > 1f) {
					gp.rm[gp.currentRoom].actors[0].alpha = 1f;
				}
				gp.rm[gp.currentRoom].draw(g2);
				if(gp.rm[gp.currentRoom].actors[0].alpha == 1f) {
					scenePhase++;
				}
			}
		} else if (scenePhase == 4) {
			gp.rm[gp.currentRoom].alpha += 0.05f;
			if(gp.rm[gp.currentRoom].alpha > 1f) {
				gp.rm[gp.currentRoom].alpha = 1f;
			}
			gp.rm[gp.currentRoom].draw(g2);
			if(gp.rm[gp.currentRoom].alpha == 1f) {
				scenePhase++;
			}
		} else if (scenePhase == 5) {
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 6) {
			gp.ui.selectedObject = gp.rm[gp.currentRoom];
			if(gp.ui.choiceSlot == 0) {
				gp.ui.selectedObject.interact();
			} else {
				gp.ui.selectedObject.choiceResponce();
			}
		} else if (scenePhase == 7) {
			gp.s.swh[read] = false;
			read = 9;
			gp.s.swh[read] = true;
			gp.s.part = 5;
			gp.c.dialogueSet = 9;
			gp.c.setDialogue();
			scenePhase++;
		} else if (scenePhase == 8) {
			gp.ui.selectedObject = gp.c;
			if(gp.c.dialogueIndex < 3) {
				gp.ui.drawDialogueScreen();
			} else {
				gp.ui.selectedObject = gp.rm[gp.currentRoom];
				alpha = 0f;
				scenePhase++;
			}
		} else if (scenePhase == 9) {
			if(fadeOut(0.05f)) {
				scenePhase++;
			}
		} else if (scenePhase == 10) {
			gp.ui.selectedObject = gp.c;
			gp.ui.drawDialogueScreen();
			alpha = 0f;
		} else if (scenePhase == 11) {
			if(fadeOut(0.005f)) {
				scenePhase++;
			}
		} else if (scenePhase == 12) {
			gp.stopMusic();
			gp.s.swh[read] = false;
			gp.s.swh[9] = true;
			gp.ui.resetSlots();
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.s.chapter = 3;
			gp.s.part = 1;
			gp.gameState = GameState.playState;
			gp.s.c3Switch[0] = true;
		}
	}
	//Alder has a sword
	private void scene_c3_1() {
		if (scenePhase == 0) {
			gp.player.worldY += (gp.tileSize / 2);
			gp.cutsceneOn = true;
			gp.c.dialogueSet = 10;
			gp.c.setDialogue();
			gp.player.drawing = false;
			scenePhase++;
		} else if (scenePhase == 1) {
			drawStage();
			if(fadeIn(0.05f)) {
				gp.ui.selectedObject = new Obj_Alder_Bed(gp);
				gp.ui.selectedObject.image = gp.ui.selectedObject.setup("/res/objects/Img_Box_Bed-2",gp.tileSize,gp.tileSize*2);
				gp.ui.selectedObject.getImage(gp.ui.selectedObject.image);
				gp.player.direction = "down";
				createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
				drawStage();
				scenePhase++;
			}
			drawBlackBackground(alpha);
			gp.ui.drawHeadingScreen("Chapter " + gp.s.chapter);
		} else if (scenePhase == 2) {
			gp.ui.selectedObject = gp.c;
			if(gp.c.dialogueIndex < 5) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 3) {
			item = new Prop(gp, gp.screenWidth/2 - gp.tileSize, gp.screenHeight/2 - gp.tileSize, gp.tileSize * 2, gp.tileSize * 2);
			item.setImage("/res/items/img_Leif1");
			drawImageWindow(item);
			scenePhase++;
		} else if (scenePhase == 4) {
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 5) {
			drawStage();
			if(counterReached(100)) {
				gp.playSE(20);
				createActor(new NPC_Florence(gp), gp.tileSize * 10, gp.tileSize * 5, "down");
				scenePhase++;
			}
		} else if (scenePhase == 6) {
			drawStage();
			if (moveActor(NPC_Florence.npcName, "down", 6)) {
				actor = getActor(NPC_Florence.npcName);
				actor.update();
				scenePhase++;
			}
		} else if (scenePhase == 7) {
			gp.glossary.unlock("constructs", "sword");
			destroyPlayerDummy();
			gp.player.drawing = true;
			gp.s.swh[9] = false;
			gp.ui.resetSlots();
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.gameState = GameState.playState;
		}
	}
	//Florence sees the sword
	private void scene_c3_2() {
		if (scenePhase == 0) {
			System.out.println(gp.s.c3Switch[3]);
			gp.cutsceneOn = true;
			gp.c.dialogueSet = 10;
			gp.c.setDialogue();
			gp.player.drawing = false;
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			createActor(new NPC_Kyla(gp), gp.tileSize * 10, gp.tileSize * 5, "down");
			scenePhase++;
		} else if (scenePhase == 1) {
			drawStage();
			moveActor(NPC_Kyla.npcName, "down", 6);
			moveActor(PlayerDummy.npcName, "up", 5);
			if (moveActor(NPC_Florence.npcName, "left", 8)) {
				changeActorDirection(PlayerDummy.npcName, "down");
				changeActorDirection(NPC_Florence.npcName, "right");
				changeActorDirection(NPC_Kyla.npcName, "left");
				actor = getActor(NPC_Florence.npcName);
				actor.update();
				scenePhase++;
			}
		} else if (scenePhase == 2) {
			destroyPlayerDummy();
			gp.player.drawing = true;
			gp.s.swh[10] = false;
			gp.ui.resetSlots();
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.s.c3Switch[0] = false;
			gp.gameState = GameState.playState;
		}
	}
	//Leif demonstration
	private void scene_c3_3() {
		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			gp.c.dialogueSet = 11;
			gp.c.setDialogue();
			gp.player.drawing = false;
			gp.ui.selectedObject = gp.c;
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			item = new Prop(gp, gp.screenWidth/2 - gp.tileSize, gp.screenHeight/2 - gp.tileSize, gp.tileSize * 2, gp.tileSize * 2);
			scenePhase++;
		} else if (scenePhase == 1) {
			if(gp.c.dialogueIndex < 3) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		}else if (scenePhase == 2) {
			drawStage();
			drawLeifWithering();
			if(gp.c.dialogueIndex < 5) {
				gp.ui.drawDialogueScreen();
			} else {
				item.spriteCounter = 0;
				scenePhase++;
			}
		} else if (scenePhase == 3) {
			drawLeifGrowing();
			if(gp.c.dialogueIndex < 10) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 4) {
			destroyPlayerDummy();
			gp.playable.get(0).setWeapon_prime(new Itm_Leif(gp));
			gp.s.c3Switch[2] = false;
			//gp.s.c3Switch[3] = true;
			gp.player.drawing = true;
			gp.s.swh[11] = false;
			gp.ui.resetSlots();
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.gameState = GameState.playState;
			drawStage();
			actor = getActor(NPC_Kyla.npcName);
			gp.ui.selectedObject = actor;
			actor.speak();
		}
	}
	//There are Gowls outside
	private void scene_c3_4() {
		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			gp.c.dialogueSet = 12;
			gp.c.setDialogue();
			gp.player.drawing = false;
			gp.ui.selectedObject = gp.c;
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			changeActorDirection(PlayerDummy.npcName, "down");
			changeActorDirection(NPC_Florence.npcName, "down");
			changeActorDirection(NPC_Kyla.npcName, "down");
			scenePhase++;
		} else if (scenePhase == 1) {
			if(gp.c.dialogueIndex < 1) {
				gp.ui.drawDialogueScreen();
			} else {
				gp.playSE(23);
				scenePhase++;
			}
		} else if (scenePhase == 2) {
			if(gp.c.dialogueIndex < 2) {
				gp.ui.drawDialogueScreen();
			} else {
				gp.playSE(22);
				scenePhase++;
			}
		} else if (scenePhase == 3) {
			if(gp.c.dialogueIndex < 6) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 4) {
			if(fadeOut(0.05f)) {
				scenePhase++;
			}
		} else if (scenePhase == 5) {
			gp.currentMap = gp.maps[2];
			gp.player.worldX = gp.tileSize * 11;
			gp.player.worldY = gp.tileSize * 5;
			gp.tileM = new TileManager(gp);
			gp.eHandler = new EventHandler(gp);
			createActor(new NPC_Gowl_Weasel(gp), gp.tileSize * 10, gp.tileSize * 3, "up");
			createActor(new NPC_Gowl_Weasel(gp), gp.tileSize * 11, gp.tileSize * 4, "up");
			createActor(new NPC_Gowl_Rat(gp), gp.tileSize * 12, gp.tileSize * 4, "up");
			createActor(new NPC_Gowl_Rat(gp), gp.tileSize * 13, gp.tileSize * 3, "up");
			gp.glossary.unlock("mammal", "weasel");
			gp.glossary.unlock("mammal", "brown rat");
			drawStage();
			scenePhase++;
		} else if (scenePhase == 6) {
			drawBlackBackground(alpha);
			drawStage();
			if(fadeIn(0.05f)) {
				scenePhase++;
			}
		} else if (scenePhase == 7) {
			if(gp.c.dialogueIndex < 20) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 8) {
			drawStage();
			if(fadeOut(0.05f)) {
				scenePhase++;
			}
		} else if (scenePhase == 9) {
			destroyActor(NPC_Gowl_Weasel.npcName);
			destroyActor(NPC_Gowl_Weasel.npcName);
			destroyActor(NPC_Gowl_Rat.npcName);
			destroyActor(NPC_Gowl_Rat.npcName);
			gp.currentMap = gp.maps[0];
			gp.player.worldX = gp.tileSize * 11;
			gp.player.worldY = gp.tileSize * 5;
			gp.tileM = new TileManager(gp);
			gp.eHandler = new EventHandler(gp);
			changeActorDirection(NPC_Florence.npcName, "right");
			changeActorDirection(NPC_Kyla.npcName, "left");
			drawStage();
			scenePhase++;
		} else if (scenePhase == 10) {
			drawStage();
			if(fadeIn(0.05f)) {
				scenePhase++;
			}
			drawBlackBackground(alpha);
		} else if (scenePhase == 11) {
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 12) {
			drawStage();
			actor = getActor(NPC_Kyla.npcName);
			if(moveActor(actor.name, "left", 9)) {
				if(moveActor(actor.name, "up", 5)){
					if(moveActor(actor.name, "left", 6)) {
						scenePhase++;
					}
				}
			}
		} else if (scenePhase == 13) {
			actor = getActor(PlayerDummy.npcName);
			gp.player.worldX = actor.worldX;
			gp.player.worldY = actor.worldY;
			destroyPlayerDummy();
			gp.player.drawing = true;
			gp.s.swh[12] = false;
			gp.s.c3Switch[3]= false;
			gp.ui.resetSlots();
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.gameState = GameState.playState;
		}
	}
	//Meet Jeb
	private void scene_c3_5() {
		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			gp.c.dialogueSet = 13;
			gp.c.setDialogue();
			gp.player.drawing = false;
			gp.ui.selectedObject = gp.c;
			gp.currentMap = gp.maps[2];
			createActor(new NPC_Gowl_Rat(gp), gp.tileSize * 12, gp.tileSize * 4, "down");
			createActor(new NPC_Jeb(gp), gp.tileSize * 12, gp.tileSize * 9, "up");
			gp.player.worldX = gp.tileSize * 11;
			gp.player.worldY = gp.tileSize * 5;
			gp.tileM = new TileManager(gp);
			gp.eHandler = new EventHandler(gp);
			drawStage();
			item = new Prop(gp, gp.screenWidth/2 - gp.tileSize, gp.screenHeight/2 - gp.tileSize, gp.tileSize * 2, gp.tileSize * 2);
			scenePhase++;
		} else if (scenePhase == 1) {
			if(gp.c.dialogueIndex < 3) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 2) {
			actor = getActor(NPC_Gowl_Rat.npcName);
			drawStage();
			moveActor(actor.getName(), "down", 8);
			if(moveCamera("down", 8, 1)){
				scenePhase++;
			}
		} else if (scenePhase == 3) {
			if(gp.c.dialogueIndex < 22) {
				gp.ui.drawDialogueScreen();
			} else {
				createActor(new NPC_Kyla(gp), gp.tileSize * 12, gp.tileSize * 3, "down");
				scenePhase++;
			}
		} else if (scenePhase == 4) {
			if(gp.c.dialogueIndex < 22) {
				gp.ui.drawDialogueScreen();
			} else {
				projectile = new Obj_Lightning(gp);
				projectile.worldX = (gp.tileSize * 12) + (gp.tileSize/4);
				projectile.worldY = gp.tileSize * 3;
				scenePhase++;
			}
		} else if (scenePhase == 5) {
			drawStage();
			projectile.worldY += 5;
			projectile.moving = true;
			if(projectile.worldY > 8 * gp.tileSize){
				actor = getActor(NPC_Kyla.npcName);
				scenePhase++;
			}
			projectile.draw(g2);
			projectile.update();
			
		} else if (scenePhase == 6) {
			if(gp.c.dialogueIndex < 23) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 7) {
			drawStage();
			if(moveActor(actor.getName(), "down", 6)){
				scenePhase++;
			}
		} else if (scenePhase == 8) {
			if(gp.c.dialogueIndex < 24) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 9) {
			if(fadeOut(0.005f)) {
				scenePhase++;
			}
		} else if (scenePhase == 10) {
			destroyActor(NPC_Jeb.npcName);
			destroyActor(NPC_Kyla.npcName);
			destroyActor(NPC_Gowl_Rat.npcName);
			gp.currentMap = gp.maps[0];
			gp.player.worldX = gp.tileSize * 8;
			gp.player.worldY = gp.tileSize * 6;
			gp.tileM = new TileManager(gp);
			gp.eHandler = new EventHandler(gp);
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			changeActorDirection(PlayerDummy.npcName, "up");
			createActor(new NPC_Jeb(gp), gp.tileSize * 8, gp.tileSize * 5, "left");
			actor = getActor(NPC_Kyla.npcName);
			actor.worldX = 7 * gp.tileSize;
			changeActorDirection(actor.name, "right");
			actor = getActor(NPC_Florence.npcName);
			actor.worldX = 9 * gp.tileSize;
			actor.worldY = 5 * gp.tileSize;
			changeActorDirection(actor.name, "left");
			scenePhase++;
		} else if (scenePhase == 11) {
			drawStage();
			if(fadeIn(0.05f)) {
				scenePhase++;
			}
			drawBlackBackground(alpha);
		} else if (scenePhase == 12) {
			drawStage();
			if(gp.c.dialogueIndex < 51) {
				gp.ui.drawDialogueScreen();
			} else {
				item.spriteCounter = 0;
				scenePhase++;
			}
		} else if (scenePhase == 13) {
			drawStage();
			drawLeifWithering();
			if(gp.c.dialogueIndex < 53) {
				gp.ui.drawDialogueScreen();
			} else {
				item.spriteCounter = 0;
				changeActorDirection(NPC_Jeb.npcName, "down");
				scenePhase++;
			}
		} else if (scenePhase == 14) {
			drawLeifGrowing();
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 15) {
			actor = getActor(PlayerDummy.npcName);
			gp.player.worldX = actor.worldX;
			gp.player.worldY = actor.worldY;
			gp.player.pickUpShillings(10);
			destroyPlayerDummy();
			gp.player.drawing = true;
			gp.s.swh[13] = false;
			gp.s.c3Switch[4]= false;
			gp.ui.resetSlots();
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.s.part = 3;
			gp.gameState = GameState.playState;
		}
	}
	//Fleeing the cottage
	private void scene_c3_6() {
		if (scenePhase == 0) {
			if(fadeOut(0.005f)) {
				scenePhase++;
			}
		} else if (scenePhase == 1) {
			gp.cutsceneOn = true;
			gp.c.dialogueSet = 14;
			gp.c.setDialogue();
			gp.player.drawing = false;
			gp.ui.selectedObject = gp.c;
			gp.player.worldX = 8 * gp.tileSize;
			gp.player.worldY = 5 * gp.tileSize;
			actor = getActor(NPC_Kyla.npcName);
			actor.worldX = 8 * gp.tileSize;
			actor.worldY = 6 * gp.tileSize;
			changeActorDirection(NPC_Kyla.npcName, "down");
			actor = getActor(NPC_Florence.npcName);
			actor.worldX = 7 * gp.tileSize;
			actor.worldY = 6 * gp.tileSize;
			changeActorDirection(NPC_Florence.npcName, "down");
			actor = getActor(NPC_Jeb.npcName);
			actor.worldX = 9 * gp.tileSize;
			actor.worldY = 6 * gp.tileSize;
			createActor(new PlayerDummy(gp), gp.player.worldX, gp.player.worldY, gp.player.direction);
			gp.player.worldX = 8 * gp.tileSize;
			gp.player.worldY = 6 * gp.tileSize;
			scenePhase++;
		} else if (scenePhase == 2) {
			drawStage();
			if(fadeIn(0.05f)) {
				scenePhase++;
			}
			drawBlackBackground(alpha);
		} else if (scenePhase == 3) {
			if(gp.c.dialogueIndex < 24) {
				gp.ui.drawDialogueScreen();
			} else {
				drawStage();
				scenePhase++;
			}
		} else if (scenePhase == 4) {
			if(fadeOut(0.005f)) {
				scenePhase++;
			}
		} else if (scenePhase == 5) {
			gp.currentMap = gp.maps[2];
			createActor(new NPC_Gowl_Rat(gp), gp.tileSize * 11, gp.tileSize * 7, "up");
			createActor(new NPC_Gowl_Weasel(gp), gp.tileSize * 12, gp.tileSize * 7, "up");
			createActor(new NPC_Gowl_Weasel2(gp), gp.tileSize * 13, gp.tileSize * 7, "up");
			createActor(new NPC_Gowl_Sorcerer(gp), gp.tileSize * 12, gp.tileSize * 8, "up");
			actor = getActor(NPC_Gowl_Sorcerer.npcName);
			gp.glossary.unlock("mammal", "red fox");
			gp.player.worldX = gp.tileSize * 11;
			gp.player.worldY = gp.tileSize * 5;
			gp.tileM = new TileManager(gp);
			gp.eHandler = new EventHandler(gp);
			gp.eManager.setDayState(DayState.DUSK);
			scenePhase++;
		} else if (scenePhase == 6) {
			drawStage();
			if(fadeIn(0.05f)) {
				scenePhase++;
			}
			drawBlackBackground(alpha);
		} else if (scenePhase == 7) {
			drawStage();
			//gp.eManager.draw(g2);
			moveActor(NPC_Gowl_Rat.npcName, "up", 3);
			moveActor(NPC_Gowl_Weasel.npcName, "up", 3);
			moveActor(NPC_Gowl_Weasel2.npcName, "up", 3);
			System.out.println(getActor(NPC_Gowl_Rat.npcName).worldY + " " + getActor(NPC_Gowl_Weasel.npcName).worldY + " " + getActor(NPC_Gowl_Weasel2.npcName).worldY);
			if (moveActor(NPC_Gowl_Sorcerer.npcName, "up", 4)) {
				scenePhase++;
			}
		} else if (scenePhase == 8) {
			drawStage();
			if(gp.c.dialogueIndex < 34) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 9) {
			gp.currentRoom = 3;
			gp.rm[gp.currentRoom].draw(g2);
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 10) {
			if(fadeOut(0.005f)) {
				scenePhase++;
			}
		} else if (scenePhase == 11) {
			actor = getActor(PlayerDummy.npcName);
			//gp.player.worldX = actor.worldX;
			//gp.player.worldY = actor.worldY;
			//destroyPlayerDummy();
			//gp.player.drawing = true;
			gp.s.swh[14] = false;
			gp.s.swh[15] = true;
			gp.s.c3Switch[5]= false;
			gp.ui.resetSlots();
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.gameState = GameState.playState;
		}
	}
	//End Credits
	private void scene_ending() {
		if (scenePhase == 0) {
			if(counterReached(300) == true) {
				gp.eManager.setDayState(DayState.DAY);
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
		actor.moving = true;
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
		actor.moving = false;
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
	
	private void drawStage() {
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
	
	public void changeStage(int i) {
		gp.currentMap = gp.maps[i];
		gp.tileM = new TileManager(gp);
		gp.eHandler = new EventHandler(gp);
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

	private void drawImageWindow(Prop i) {
		gp.ui.drawDialogueWindow(i.x, i.y, i.width, i.height);
		try {
			if (i != null) {
				g2.drawImage(i.image, i.x, i.y, i.width, i.height, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean drawLeifWithering() {
		item.setImage("/res/items/img_Leif1");
		item.withered = true;
		gp.ui.drawDialogueWindow(item.x, item.y, item.width, item.height);
		item.playLeifAnimation(g2);
		if (item.spriteCounter >  100) {return true;}
		return false;
	}

	private boolean drawLeifGrowing() {
		item.setImage("/res/items/img_Leif1");
		item.withered = false;
		gp.ui.drawDialogueWindow(item.x, item.y, item.width, item.height);
		item.playLeifAnimation(g2);
		if (item.spriteCounter >  100) {return true;}
		return false;
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