package wildwyrd.game.cutscenes;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.Story;

public class Cutscene {
	GamePanel gp;
	Story s;
	public String[][] dialogues = new String[100][100];
	public BufferedImage[][] sprites = new BufferedImage[100][100];
	public int dialogueIndex = 0;
	public int dialogueSet = 0;
	BufferedImage image_Dilecto;

	public Cutscene(Story s) {
		this.s = s;
	}

	public void setCutscene(int dialogueSet, int read) {
		this.dialogueSet = dialogueSet;
		int count = 0;

		try {
			InputStream f = this.getClass().getResourceAsStream("/res/dialogue/Cutscenes.txt");
			BufferedReader b = new BufferedReader(new InputStreamReader(f));

			for (String i = b.readLine(); i != null; i = b.readLine()) {
				String[] line = i.split("\\$ ", 5);
				if (line[0].equals(Integer.toString(this.s.chapter)) && line[1].equals(Integer.toString(read))
						&& line[2].equals(Integer.toString(this.s.part))) {
					String[] newline = line[4].split("£");
					String name = "";
					String text = "";
					if (newline.length > 1) {
						name = newline[0];
						text = newline[1];
					} else {
						text = line[4].strip();
					}

					this.dialogues[dialogueSet][count] = name + text;
					++count;
				}
			}
		} catch (IOException var11) {
			System.out.println(var11);
		}

	}

	public void nextDialogue(String currentDialogue) {
		String[][] dia = this.dialogues;
		if (dia[this.dialogueIndex] == null) {
			this.dialogueIndex = 0;
		}

		++this.dialogueIndex;
	}

	public void startDialogue(Cutscene cut, int setNum) {
		GamePanel gp = this.gp;
		gp.gameState = GameState.dialogueState;
	}

	public void cutsceneDialog(String text) {
		if (this.gp.keyH.enterPressed) {
			this.gp.ui.currentDialogue = text;
		}

		this.gp.keyH.enterPressed = false;
	}

	public void setImages() {
		try {
			this.image_Dilecto = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Dilecto.png"));
		} catch (IOException var2) {
			var2.printStackTrace();
		}

	}

	public void setSprites() {
		this.setImages();
		this.sprites[0][8] = this.image_Dilecto;
		this.sprites[0][9] = this.image_Dilecto;
		this.sprites[0][16] = this.image_Dilecto;
		this.sprites[0][17] = this.image_Dilecto;
		this.sprites[0][21] = this.image_Dilecto;
		this.sprites[0][22] = this.image_Dilecto;
		this.sprites[0][23] = this.image_Dilecto;
		this.sprites[0][28] = this.image_Dilecto;
		this.sprites[0][29] = this.image_Dilecto;
		this.sprites[0][30] = this.image_Dilecto;
		this.sprites[0][36] = this.image_Dilecto;
		this.sprites[0][38] = this.image_Dilecto;
		this.sprites[0][39] = this.image_Dilecto;
		this.sprites[0][41] = this.image_Dilecto;
		this.sprites[0][42] = this.image_Dilecto;
		this.sprites[0][43] = this.image_Dilecto;
		this.sprites[0][45] = this.image_Dilecto;
		this.sprites[0][47] = this.image_Dilecto;
		this.sprites[0][48] = this.image_Dilecto;
		this.sprites[0][59] = this.image_Dilecto;
		this.sprites[0][60] = this.image_Dilecto;
		this.sprites[0][61] = this.image_Dilecto;
	}
}