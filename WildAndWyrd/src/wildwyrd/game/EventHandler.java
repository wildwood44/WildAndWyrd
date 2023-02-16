package wildwyrd.game;

import java.awt.Rectangle;
import wildwyrd.game.cutscenes.CutsceneManager;

public class EventHandler {
	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX;
	int eventRectDefaultY;

	public EventHandler(GamePanel gp) {
		this.gp = gp;
		this.eventRect = new Rectangle();
		this.eventRect.x = 200;
		this.eventRect.y = 200;
		this.eventRect.width = 400;
		this.eventRect.height = 200;
		this.eventRectDefaultX = this.eventRect.x;
		this.eventRectDefaultY = this.eventRect.y;
	}

	public void checkEvent() {
	}

	public void checkCutscene() {
		if (this.gp.s.chapter == 0 && this.gp.s.swh[0]) {
			this.prologueCutscene(0);
		}

		if (this.gp.s.chapter == 1 && this.gp.s.swh[0]) {
			this.c1s_Cutscene(0);
		}

	}

	public void prologueCutscene(int read) {
		GamePanel var10000 = this.gp;
		this.gp.getClass();
		var10000.gameState = 4;
		CutsceneManager var2 = this.gp.csManager;
		this.gp.csManager.getClass();
		var2.sceneNum = 1;
	}

	public void c1s_Cutscene(int read) {
		GamePanel var10000 = this.gp;
		this.gp.getClass();
		var10000.gameState = 4;
		System.out.println("Get Chapter 1");
		CutsceneManager var2 = this.gp.csManager;
		this.gp.csManager.getClass();
		var2.sceneNum = 2;
	}

	public void message() {
		GamePanel var10000 = this.gp;
		this.gp.getClass();
		var10000.gameState = 6;
	}

	public static void getResponce() {
		Boolean res = false;

		while (!res) {
			;
		}

	}
}