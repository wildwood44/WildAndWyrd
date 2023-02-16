package wildwyrd.game.cutscenes;

import java.awt.Graphics2D;
import wildwyrd.game.GamePanel;

public class CutsceneManager {
	GamePanel gp;
	Graphics2D g2;
	public int sceneNum;
	public int scenePhase;
	public int read = 0;
	public final int NA = 0;
	public final int prologue = 1;
	public final int chapter1Start = 2;

	public CutsceneManager(GamePanel gp) {
		this.gp = gp;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		switch (this.sceneNum) {
			case 1 :
				this.scene_prologue();
				break;
			case 2 :
				this.scene_c1S();
		}

	}

	private void scene_prologue() {
		if (this.scenePhase == 0) {
			this.gp.cutsceneOn = true;
			this.gp.ui.drawHeadingScreen("Prologue");
			this.gp.ui.drawMessageScreen(
					"The game will now begin. Press any key to continue.:You may skip the dialogue by pressing 'skip'.");
		} else if (this.scenePhase == 1) {
			this.gp.c.setCutscene(0, this.read);
			this.gp.c.setSprites();
			this.gp.ui.drawDialogueScreen();
		} else if (this.scenePhase == 2) {
			this.gp.s.chapter = 1;
			this.gp.cutsceneOn = false;
			this.gp.c.dialogueIndex = 0;
			this.sceneNum = 0;
			this.scenePhase = 0;
			GamePanel var10000 = this.gp;
			this.gp.getClass();
			var10000.gameState = 1;
		}

	}

	private void scene_c1S() {
		if (this.scenePhase == 0) {
			this.gp.cutsceneOn = true;
			this.gp.ui.drawHeadingScreen("Chapter " + this.gp.s.chapter);
			this.gp.ui.drawMessageScreen("");
		}

		if (this.scenePhase == 1) {
			this.gp.cutsceneOn = true;
			this.gp.c.setCutscene(1, this.read);
			this.gp.c.dialogueSet = 1;
			this.gp.ui.drawDialogueScreen();
		} else if (this.scenePhase == 2) {
			this.gp.s.swh[this.read] = false;
			this.gp.cutsceneOn = false;
			GamePanel var10000 = this.gp;
			this.gp.getClass();
			var10000.gameState = 1;
		}

	}
}