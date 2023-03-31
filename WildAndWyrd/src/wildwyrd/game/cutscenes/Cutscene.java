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
	BufferedImage image_Thay;
	BufferedImage image_Dean;
	BufferedImage image_Ralph;
	BufferedImage image_Plumm;

	public Cutscene(Story s) {
		this.s = s;
	}

	public void setCutscene(int dialogueSet, int read) {
		this.dialogueSet = dialogueSet;
		int count = 0;

		try {
			InputStream f = getClass().getResourceAsStream("/res/dialogue/Cutscenes.txt");
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
						text = line[4].trim();
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
		if (gp.keyH.enterPressed) {
			gp.ui.currentDialogue = text;
		}

		gp.keyH.enterPressed = false;
	}

	public void setImages() {
		try {
			image_Dilecto = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Dilecto.png"));
			image_Thay = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Thay.png"));
			image_Dean = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Dean.png"));
			image_Ralph = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Ralph.png"));
			image_Plumm = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Plumm.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setSprites() {
		setImages();
		sprites[0][5] = image_Dean;
		sprites[0][6] = image_Dean;
		sprites[0][7] = image_Dean;
		sprites[0][8] = image_Dilecto;
		sprites[0][9] = image_Dilecto;
		sprites[0][10] = image_Dean;
		sprites[0][11] = image_Ralph;
		sprites[0][12] = image_Ralph;
		sprites[0][13] = image_Dean;
		sprites[0][14] = image_Ralph;
		sprites[0][15] = image_Ralph;
		sprites[0][16] = image_Dilecto;
		sprites[0][17] = image_Dilecto;
		sprites[0][18] = image_Ralph;
		sprites[0][19] = image_Ralph;
		sprites[0][20] = image_Ralph;
		sprites[0][21] = image_Dilecto;
		sprites[0][22] = image_Dilecto;
		sprites[0][23] = image_Dilecto;
		sprites[0][24] = image_Dean;
		sprites[0][25] = image_Ralph;
		sprites[0][26] = image_Dean;
		sprites[0][27] = image_Dean;
		sprites[0][28] = image_Dilecto;
		sprites[0][29] = image_Dilecto;
		sprites[0][30] = image_Dilecto;
		sprites[0][31] = image_Ralph;
		sprites[0][32] = image_Dean;
		sprites[0][33] = image_Dean;
		sprites[0][34] = image_Ralph;
		sprites[0][35] = image_Ralph;
		sprites[0][36] = image_Dilecto;
		sprites[0][37] = image_Ralph;
		sprites[0][38] = image_Dilecto;
		sprites[0][39] = image_Dilecto;
		sprites[0][40] = image_Ralph;
		sprites[0][41] = image_Dilecto;
		sprites[0][42] = image_Dilecto;
		sprites[0][43] = image_Dilecto;
		sprites[0][44] = image_Plumm;
		sprites[0][45] = image_Dilecto;
		sprites[0][46] = image_Plumm;
		sprites[0][47] = image_Dilecto;
		sprites[0][48] = image_Dilecto;
		sprites[0][49] = image_Plumm;
		sprites[0][50] = image_Plumm;
		sprites[0][51] = image_Ralph;
		sprites[0][52] = image_Plumm;
		sprites[0][53] = image_Plumm;
		sprites[0][54] = image_Ralph;
		sprites[0][55] = image_Ralph;
		sprites[0][56] = image_Dean;
		sprites[0][57] = image_Dilecto;
		sprites[0][58] = image_Dilecto;
		sprites[0][59] = image_Dilecto;
		sprites[0][60] = image_Dilecto;
		sprites[0][61] = image_Dilecto;
		sprites[1][0] = image_Thay;
		sprites[1][1] = image_Thay;
		sprites[1][2] = image_Thay;
		sprites[1][6] = image_Thay;
		sprites[1][7] = image_Thay;
		sprites[1][9] = image_Thay;
	}
}