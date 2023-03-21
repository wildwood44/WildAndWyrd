package wildwyrd.game.cutscenes;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

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
					"The game will now begin. Press any key to continue.:You may skip the dialogue by pressing 'skip'.");
		} else if (scenePhase == 1) {
			gp.ui.drawBackground("/res/backgrounds/Forton_Backgound.png");
			gp.c.setCutscene(0, read);
			gp.c.setSprites();
			gp.ui.drawDialogueScreen();
		} else if (scenePhase == 2) {
			gp.s.chapter = 1;
			gp.cutsceneOn = false;
			gp.c.dialogueIndex = 0;
			sceneNum = 0;
			scenePhase = 0;
			GamePanel gp = this.gp;
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
			GamePanel gp = this.gp;
			gp.gameState = GameState.playState;
		}

	}
}