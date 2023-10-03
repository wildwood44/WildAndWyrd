package wildwyrd.game.cutscenes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
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
	public int read = 0;
	public final int NA = 0;
	public final int prologue = 1;
	public final int chapter1Start = 2;
	public BufferedImage background;

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
			changeActorDirection(NPC_Thay.npcName, "up");
				drawRoom();
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
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == NPC_Florence.npcName) {
					gp.npc[gp.currentMap.getId()][i].worldY += 1;
					if(gp.npc[gp.currentMap.getId()][i].worldY > gp.tileSize * 4){
						gp.npc[gp.currentMap.getId()][i].direction = "right";
						gp.npc[gp.currentMap.getId()][i].spriteNum = 1;
						gp.npc[gp.currentMap.getId()][i].update();
						scenePhase++;
					}
					gp.npc[gp.currentMap.getId()][i].update();
				}
			}
		} else if (scenePhase == 3) {
			changeActorDirection(PlayerDummy.npcName, "left");
			changeActorDirection(NPC_Thay.npcName, "left");
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
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == NPC_Florence.npcName) {
					gp.npc[gp.currentMap.getId()][i].worldX -= 1;
					gp.npc[gp.currentMap.getId()][i].direction = "left";
					if(gp.npc[gp.currentMap.getId()][i].worldX < gp.tileSize * 13){
						gp.npc[gp.currentMap.getId()][i].direction = "down";
						gp.npc[gp.currentMap.getId()][i].spriteNum = 1;
						gp.npc[gp.currentMap.getId()][i].update();
						scenePhase++;
					}
					gp.npc[gp.currentMap.getId()][i].update();
				}
			}
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
			for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
				//System.out.println(i +" "+ gp.npc[gp.currentMap.getId()][i].name);
				if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == NPC_Thay.npcName) {
					gp.npc[gp.currentMap.getId()][i].direction = "down";
					gp.npc[gp.currentMap.getId()][i].worldY += 1;
					gp.npc[gp.currentMap.getId()][i].update();
					/*if(gp.npc[gp.currentMap.getId()][i].worldY > gp.tileSize * 14){
						scenePhase++;
					}*/
				}
			}
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 6) {

			createActor(new NPC_Kyla(gp), 0, gp.tileSize * 11, gp.tileSize * 4, "down");
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
		for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; i++) {
			if(gp.npc[gp.currentMap.getId()][i] != null && gp.npc[gp.currentMap.getId()][i].name == npcName) {
				gp.npc[gp.currentMap.getId()][i].direction = direction;
				gp.npc[gp.currentMap.getId()][i].update();
				break;
			}
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
}