package wildwyrd.game.cutscenes;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.playable.PlayerDummy;
import wildwyrd.npc.NPC_Florence;
import wildwyrd.npc.NPC_Kyla;
import wildwyrd.npc.NPC_Thay;

public class CutsceneManager {
	GamePanel gp;
	Graphics2D g2;
	public int sceneNum;
	public int scenePhase;
	public int read = 0;
	public final int NA = 0;
	public final int prologue = 1;
	public final int chapter1Start = 2;
	public BufferedImage background;

	public CutsceneManager(GamePanel gp) {
		this.gp = gp;
	}

	public void draw(Graphics2D g2) {
		System.out.println(gp.s.chapter + " " + gp.s.part + " " + read);
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
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] == null) {
					gp.npc[gp.currentMap.getId()][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap.getId()][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap.getId()][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap.getId()][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			drawRoom();
			scenePhase++;
		} else if (scenePhase == 1) {
			gp.ui.selectedObject = gp.c;
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 2) {
			drawRoom();
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				//System.out.println(i +" "+ gp.npc[gp.currentMap.getId()][i].name);
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == PlayerDummy.npcName) {
					gp.npc[gp.currentMap.getId()][i].update();
					gp.npc[gp.currentMap.getId()][i].worldY += 1;
					if(gp.npc[gp.currentMap.getId()][i].worldY > gp.tileSize * 5){
						scenePhase++;
					}
					
				}
			}
		} else if (scenePhase == 3) {
			drawRoom();
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				//System.out.println(i +" "+ gp.npc[gp.currentMap.getId()][i].name);
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == NPC_Thay.npcName) {
					gp.npc[gp.currentMap.getId()][i].direction = "left";
					gp.npc[gp.currentMap.getId()][i].update();
					gp.npc[gp.currentMap.getId()][i].worldX -= 1;
					if(gp.npc[gp.currentMap.getId()][i].worldX < gp.tileSize * 14){
						scenePhase++;
					}
				}
			}
		} else if (scenePhase == 4) {
			drawRoom();
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				//System.out.println(i +" "+ gp.npc[gp.currentMap.getId()][i].name);
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == NPC_Thay.npcName) {
					gp.npc[gp.currentMap.getId()][i].direction = "up";
					gp.npc[gp.currentMap.getId()][i].update();
					gp.npc[gp.currentMap.getId()][i].worldY -= 1;
					if(gp.npc[gp.currentMap.getId()][i].worldY < gp.tileSize * 3){
						scenePhase++;
					}
					
				}
			}
		} else if (scenePhase == 5) {
			drawRoom();
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				//System.out.println(i +" "+ gp.npc[gp.currentMap.getId()][i].name);
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == NPC_Thay.npcName) {
					gp.npc[gp.currentMap.getId()][i] = null;
					
				}
			}
			gp.player.worldY += 2;
			if(gp.player.worldY > gp.tileSize * 5){
				scenePhase++;
			}
		} else if (scenePhase == 6) {
			gp.s.swh[read] = false;
			gp.player.drawing = false;
			gp.cutsceneOn = false;
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				//System.out.println(i +" "+ gp.npc[gp.currentMap.getId()][i].name);
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == PlayerDummy.npcName) {
					gp.player.worldX = gp.npc[gp.currentMap.getId()][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap.getId()][i].worldY;
					gp.npc[gp.currentMap.getId()][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.s.part = 2;
			gp.gameState = GameState.playState;
		}
	}
	
	private void scene_c1_2() {

		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			read = 2;
			gp.c.setCutscene(3, read);
			gp.c.dialogueSet = 3;
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] == null) {
					gp.npc[gp.currentMap.getId()][i] = new NPC_Thay(gp);
					gp.npc[gp.currentMap.getId()][i].worldX = gp.tileSize * 14;
					gp.npc[gp.currentMap.getId()][i].worldY = gp.tileSize * 3;
					gp.npc[gp.currentMap.getId()][i].direction = "down";
					break;
				}
			}
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] == null) {
					gp.npc[gp.currentMap.getId()][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap.getId()][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap.getId()][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap.getId()][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			drawRoom();
			scenePhase++;
		} else if (scenePhase == 1) {
			drawRoom();
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				//System.out.println(i +" "+ gp.npc[gp.currentMap.getId()][i].name);
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == PlayerDummy.npcName) {
					if((gp.npc[gp.currentMap.getId()][i].worldX >= gp.tileSize * 13 &&
						gp.npc[gp.currentMap.getId()][i].worldX <= gp.tileSize * 16) &&
						gp.npc[gp.currentMap.getId()][i].worldY < gp.tileSize * 6) {
						gp.npc[gp.currentMap.getId()][i].update();
						gp.npc[gp.currentMap.getId()][i].worldY += 1;
						if(gp.npc[gp.currentMap.getId()][i].worldY > gp.tileSize * 4){
							scenePhase++;
						}
					} else {
						scenePhase++;
					}
				}
			}
		} else if (scenePhase == 2) {
			drawRoom();
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				//System.out.println(i +" "+ gp.npc[gp.currentMap.getId()][i].name);
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == NPC_Thay.npcName) {
					gp.npc[gp.currentMap.getId()][i].worldY += 2;
					gp.npc[gp.currentMap.getId()][i].update();
					if(gp.npc[gp.currentMap.getId()][i].worldY > gp.tileSize * 4){
						scenePhase++;
					}
				}
			}
			
		} else if (scenePhase == 3) {
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] == null) {
					gp.npc[gp.currentMap.getId()][i] = new NPC_Kyla(gp);
					gp.npc[gp.currentMap.getId()][i].worldX = gp.tileSize * 14;
					gp.npc[gp.currentMap.getId()][i].worldY = gp.tileSize * 3;
					gp.npc[gp.currentMap.getId()][i].direction = "down";
					break;
				}
			}
			scenePhase++;
		} else if (scenePhase == 4) {
			drawRoom();
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == NPC_Thay.npcName) {
					gp.npc[gp.currentMap.getId()][i].direction = "up";
					drawRoom();
					gp.npc[gp.currentMap.getId()][i].update();
				}
			}
			scenePhase++;
		} else if (scenePhase == 5) {
			gp.ui.selectedObject = gp.c;
			gp.ui.drawDialogueScreen();
			
		} else if (scenePhase == 6) {
			drawRoom();
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				//System.out.println(i +" "+ gp.npc[gp.currentMap.getId()][i].name);
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == NPC_Thay.npcName) {
					gp.npc[gp.currentMap.getId()][i].direction = "right";
					gp.npc[gp.currentMap.getId()][i].worldX += 1;
					gp.npc[gp.currentMap.getId()][i].update();
				}
				if(gp.npc[gp.currentMap.getId()][i].worldX > gp.tileSize * 15){
					gp.npc[gp.currentMap.getId()][i].direction = "down";
					scenePhase++;
				}
			}
			//scenePhase++;
		} else if (scenePhase == 7) {
			drawRoom();
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == NPC_Kyla.npcName) {
					gp.npc[gp.currentMap.getId()][i] = null;
					break;
				}
			}
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == NPC_Florence.npcName) {
					gp.npc[gp.currentMap.getId()][i].direction = "right";
					gp.npc[gp.currentMap.getId()][i].update();
					gp.npc[gp.currentMap.getId()][i].worldX += 1;
					if(gp.npc[gp.currentMap.getId()][i].worldX > gp.tileSize * 14){
						scenePhase++;
					}
				}
			}
		} else if (scenePhase == 8) {
			drawRoom();
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == NPC_Florence.npcName) {
					gp.npc[gp.currentMap.getId()][i].worldY -= 1;
					gp.npc[gp.currentMap.getId()][i].direction = "up";
					gp.npc[gp.currentMap.getId()][i].update();
					if(gp.npc[gp.currentMap.getId()][i].worldY < gp.tileSize * 3){
						scenePhase++;
					}
				}
			}
		} else if (scenePhase == 9) {
			gp.s.swh[read] = false;
			gp.player.drawing = false;
			gp.cutsceneOn = false;
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == PlayerDummy.npcName) {
					gp.player.worldX = gp.npc[gp.currentMap.getId()][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap.getId()][i].worldY;
					gp.npc[gp.currentMap.getId()][i] = null;
					break;
				}
			}
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == NPC_Florence.npcName) {
					gp.npc[gp.currentMap.getId()][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.s.part = 2;
			gp.gameState = GameState.playState;
		}
	}
	
	private void scene_c1_3() {
		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			read = 3;
			gp.c.setCutscene(4, read);
			gp.c.dialogueSet = 4;
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] == null) {
					gp.npc[gp.currentMap.getId()][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap.getId()][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap.getId()][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap.getId()][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			drawRoom();
			scenePhase++;
		} else if (scenePhase == 1) {
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] == null) {
					gp.npc[gp.currentMap.getId()][i] = new NPC_Florence(gp);
					gp.npc[gp.currentMap.getId()][i].worldX = gp.tileSize * 14;
					gp.npc[gp.currentMap.getId()][i].worldY = gp.tileSize * 3;
					gp.npc[gp.currentMap.getId()][i].direction = "down";
					break;
				}
			}
			scenePhase++;
		} else if (scenePhase == 2) {
			drawRoom();
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				//System.out.println(i +" "+ gp.npc[gp.currentMap.getId()][i].name);
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == NPC_Florence.npcName) {
					gp.npc[gp.currentMap.getId()][i].worldY += 1;
					gp.npc[gp.currentMap.getId()][i].update();
					if(gp.npc[gp.currentMap.getId()][i].worldY > gp.tileSize * 4){
						scenePhase++;
					}
				}
			}
		} else if (scenePhase == 3) {
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == NPC_Florence.npcName) {
					gp.npc[gp.currentMap.getId()][i].direction = "right";
					gp.npc[gp.currentMap.getId()][i].update();
					break;
				}
			}
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == PlayerDummy.npcName) {
					gp.npc[gp.currentMap.getId()][i].direction = "left";
					gp.npc[gp.currentMap.getId()][i].update();
					break;
				}
			}
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == NPC_Thay.npcName) {
					gp.npc[gp.currentMap.getId()][i].direction = "left";
					gp.npc[gp.currentMap.getId()][i].update();
					scenePhase++;
					break;
				}
			}
			drawRoom();
		} else if (scenePhase == 4) {
			gp.ui.selectedObject = gp.c;
			if(gp.c.dialogueIndex < 2) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 5) {
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == PlayerDummy.npcName) {

					for (int j = 0; j < gp.npc[gp.currentMap.getId()].length; j++) {
						if(gp.npc[gp.currentMap.getId()][j] != null && gp.npc[gp.currentMap.getId()][j].name == NPC_Thay.npcName) {
							if(gp.npc[gp.currentMap.getId()][i].worldX > gp.npc[gp.currentMap.getId()][j].worldX) {
								gp.npc[gp.currentMap.getId()][i].direction = "left";
								gp.npc[gp.currentMap.getId()][j].direction = "right";
							} else if(gp.npc[gp.currentMap.getId()][i].worldX < gp.npc[gp.currentMap.getId()][j].worldX) {
								gp.npc[gp.currentMap.getId()][i].direction = "right";
								gp.npc[gp.currentMap.getId()][j].direction = "left";
							} else if(gp.npc[gp.currentMap.getId()][i].worldY > gp.npc[gp.currentMap.getId()][j].worldY) {
								gp.npc[gp.currentMap.getId()][i].direction = "up";
								gp.npc[gp.currentMap.getId()][j].direction = "down";
							} else if(gp.npc[gp.currentMap.getId()][i].worldY < gp.npc[gp.currentMap.getId()][j].worldY) {
								gp.npc[gp.currentMap.getId()][i].direction = "down";
								gp.npc[gp.currentMap.getId()][j].direction = "up";
							}
							scenePhase++;
						}
					}
					
					gp.npc[gp.currentMap.getId()][i].update();
					break;
				}
			}
			drawRoom();
		} else if (scenePhase == 6) {
			System.out.println(gp.c.dialogueIndex);
			if(gp.c.dialogueIndex < 6) {
				gp.ui.drawDialogueScreen();
			} else {
				scenePhase++;
			}
		} else if (scenePhase == 7) {
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == PlayerDummy.npcName) {
					gp.npc[gp.currentMap.getId()][i].direction = "left";
					gp.npc[gp.currentMap.getId()][i].update();
					break;
				}
			}
			scenePhase++;
		} else if (scenePhase == 8) {
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 9) {
			gp.s.swh[read] = false;
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == PlayerDummy.npcName) {
					gp.player.worldX = gp.npc[gp.currentMap.getId()][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap.getId()][i].worldY;
					gp.npc[gp.currentMap.getId()][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			gp.gameState = GameState.playState;
		}
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
}