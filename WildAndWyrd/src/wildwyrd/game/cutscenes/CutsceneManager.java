package wildwyrd.game.cutscenes;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;

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
		this.g2 = g2;
		switch (sceneNum) {
			case 1 :
				scene_prologue();
				break;
			case 2 :
				scene_c1S();
		}

	}

	private void scene_prologue() {
		if (scenePhase == 0) {
			gp.cutsceneOn = true;
			gp.ui.drawHeadingScreen("Prologue");
			gp.ui.drawMessageScreen(
					"The game will now begin. Press ENTER to continue. You may skip the dialogue by pressing S.");
			gp.c.setSprites();
		} else if (scenePhase == 1) {
			//gp.ui.drawBackground("/res/backgrounds/Forton_Backgound.png");
			//gp.currentRoom = new Rm_Forton(gp);
			gp.currentRoom = 1;
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
			gp.cutsceneOn = true;
			gp.ui.drawHeadingScreen("Chapter " + gp.s.chapter);
			gp.ui.drawMessageScreen("");
		}

		if (scenePhase == 1) {
			gp.cutsceneOn = true;
			gp.c.setCutscene(1, read);
			gp.c.dialogueSet = 1;
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 2) {
			gp.s.swh[read] = false;
			gp.cutsceneOn = false;
			gp.gameState = GameState.playState;
		}

	}
	
	private void scene_c1_1() {
		
	}
}