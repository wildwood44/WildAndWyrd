package wildwyrd.game.cutscenes;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.object.Dialoge;

public class Cutscene extends Entity {
	GamePanel gp;
	BufferedImage image_Alder;
	BufferedImage image_Florence;
	BufferedImage image_Dilecto;
	BufferedImage image_Thay;
	BufferedImage image_Dean;
	BufferedImage image_Ralph;
	BufferedImage image_Plumm;
	BufferedImage image_Kyla;
	BufferedImage image_Trissie;
	BufferedImage image_Agrimus;

	public Cutscene(GamePanel gp) {
		super(gp);
		this.gp = gp;
	}

	public void setCutscene(int dialogueSet, int read) {
		this.dialogueSet = dialogueSet;
		int count = 0;
		try {
			InputStream f = getClass().getResourceAsStream("/res/dialogue/Cutscenes.txt");
			BufferedReader b = new BufferedReader(new InputStreamReader(f));

			for (String i = b.readLine(); i != null; i = b.readLine()) {
				String[] line = i.split("\\$ ", 6);
				//Check chapter, switch and part
				if (line[0].equals(Integer.toString(gp.s.chapter)) && line[1].equals(Integer.toString(read))
						&& line[2].equals(Integer.toString(gp.s.part))) {
					//Split dialogue
					String[] newline = line[4].split("£");
					String name = "";
					String text = "";
					
					if (newline.length > 1) {
						name = newline[0];
						text = newline[1];
					} else {
						text = line[4].trim();
					}
					if (name != "") {
						dialogues[dialogueSet][count] = new Dialoge(name, text, 1);
					} else {
						dialogues[dialogueSet][count] = new Dialoge(text, 1);
					}
					count++;
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public void nextDialogue(String currentDialogue) {
		Dialoge[][] dia = dialogues;
		if (dia[dialogueIndex] == null) {
			dialogueIndex = 0;
		}

		dialogueIndex++;
	}

	public void cutsceneDialog(String text) {
		if (gp.keyH.enterPressed) {
			gp.ui.currentDialogue = text;
		}

		gp.keyH.enterPressed = false;
	}

	public void setImages() {
		try {
			image_Alder = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Alder.png"));
			image_Florence = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Florence.png"));
			image_Dilecto = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Dilecto.png"));
			image_Thay = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Thay.png"));
			image_Dean = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Dean.png"));
			image_Ralph = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Ralph.png"));
			image_Plumm = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Plumm.png"));
			image_Kyla = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Kyla.png"));
			image_Trissie = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Trissie.png"));
			image_Agrimus = ImageIO.read(getClass().getResourceAsStream("/res/character/chr_Agrimus.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSprites(int spriteSet) {
		setImages();
		switch(spriteSet) {
		case 0:
			sprites[0][0] = image_Dean;
			sprites[0][1] = image_Dean;
			sprites[0][2] = image_Dean;
			sprites[0][3] = image_Dilecto;
			sprites[0][4] = image_Dilecto;
			sprites[0][5] = image_Dean;
			sprites[0][6] = image_Ralph;
			sprites[0][7] = image_Ralph;
			sprites[0][8] = image_Dean;
			sprites[0][9] = image_Ralph;
			sprites[0][10] = image_Ralph;
			sprites[0][11] = image_Dilecto;
			sprites[0][12] = image_Dilecto;
			sprites[0][13] = image_Ralph;
			sprites[0][14] = image_Dilecto;
			sprites[0][15] = image_Ralph;
			sprites[0][16] = image_Dilecto;
			sprites[0][17] = image_Dilecto;
			sprites[0][18] = image_Dean;
			sprites[0][19] = image_Ralph;
			sprites[0][20] = image_Dean;
			sprites[0][21] = image_Dilecto;
			sprites[0][22] = image_Dilecto;
			sprites[0][23] = image_Dilecto;
			sprites[0][24] = image_Ralph;
			sprites[0][25] = image_Dean;
			sprites[0][26] = image_Dean;
			sprites[0][27] = image_Ralph;
			sprites[0][28] = image_Ralph;
			sprites[0][29] = image_Dilecto;
			sprites[0][30] = image_Ralph;
			sprites[0][31] = image_Dilecto;
			sprites[0][32] = image_Dilecto;
			sprites[0][33] = image_Ralph;
			sprites[0][34] = image_Dilecto;
			sprites[0][35] = image_Dilecto;
			sprites[0][36] = image_Dilecto;
			sprites[0][37] = image_Dilecto;
			sprites[0][38] = image_Plumm;
			sprites[0][39] = image_Dilecto;
			sprites[0][40] = image_Dilecto;
			sprites[0][41] = image_Plumm;
			sprites[0][42] = image_Plumm;
			sprites[0][43] = image_Ralph;
			sprites[0][44] = image_Plumm;
			sprites[0][45] = image_Plumm;
			sprites[0][46] = image_Ralph;
			sprites[0][47] = image_Ralph;
			sprites[0][48] = image_Dean;
			sprites[0][49] = image_Dilecto;
			sprites[0][50] = image_Dilecto;
			sprites[0][51] = image_Dilecto;
			sprites[0][52] = image_Dilecto;
			sprites[0][53] = image_Dilecto;
			break;
		case 1:
			sprites[1][0] = image_Thay;
			sprites[1][1] = image_Florence;
			sprites[1][2] = image_Thay;
			sprites[1][3] = image_Thay;
			sprites[1][4] = image_Florence;
			sprites[1][5] = image_Thay;
			sprites[1][6] = image_Florence;
			sprites[1][7] = image_Florence;
			sprites[1][8] = image_Florence;
			break;
		case 2:
			sprites[2][0] = image_Thay;
			sprites[2][1] = image_Alder;
			sprites[2][2] = image_Thay;
			sprites[2][3] = image_Thay;
			sprites[2][4] = image_Alder;
			sprites[2][5] = image_Thay;
			sprites[2][6] = image_Thay;
			sprites[2][7] = image_Thay;
			break;
		case 3:
			sprites[3][0] = image_Thay;
			sprites[3][1] = image_Kyla;
			break;
		case 4:
			sprites[4][0] = image_Florence;
			sprites[4][1] = image_Florence;
			sprites[4][2] = image_Thay;
			sprites[4][3] = image_Thay;
			sprites[4][4] = image_Alder;
			sprites[4][5] = image_Alder;
			sprites[4][6] = image_Florence;
			sprites[4][7] = image_Florence;
			sprites[4][8] = image_Alder;
			sprites[4][9] = image_Alder;
			sprites[4][10] = image_Florence;
			sprites[4][11] = image_Florence;
			sprites[4][12] = image_Alder;
			break;
		case 5:
			sprites[5][0] = image_Thay;
			sprites[5][1] = image_Alder;
			sprites[5][2] = image_Alder;
			sprites[5][3] = image_Thay;
			sprites[5][4] = image_Thay;
			break;
		case 6:
			sprites[6][0] = image_Alder;
			sprites[6][1] = image_Trissie;
			sprites[6][2] = image_Alder;
			sprites[6][3] = image_Trissie;
			sprites[6][4] = image_Trissie;
			sprites[6][5] = image_Trissie;
			sprites[6][6] = image_Alder;
			sprites[6][7] = image_Trissie;
			sprites[6][8] = image_Trissie;
			sprites[6][9] = image_Trissie;
			sprites[6][10] = image_Alder;
			sprites[6][11] = image_Trissie;
			sprites[6][12] = image_Alder;
			sprites[6][13] = image_Florence;
			break;
		case 7:
			sprites[7][0] = image_Trissie;
			sprites[7][1] = image_Trissie;
			sprites[7][2] = image_Florence;
			sprites[7][3] = image_Kyla;
			sprites[7][4] = image_Kyla;
			sprites[7][5] = image_Trissie;
			sprites[7][6] = image_Trissie;
			sprites[7][7] = image_Trissie;
			sprites[7][8] = image_Kyla;
			sprites[7][9] = image_Kyla;
			sprites[7][10] = image_Trissie;
			sprites[7][11] = image_Trissie;
			sprites[7][12] = image_Kyla;
			sprites[7][13] = image_Kyla;
			sprites[7][14] = image_Trissie;
			sprites[7][15] = image_Alder;
			sprites[7][16] = image_Florence;
			sprites[7][17] = image_Florence;
			sprites[7][18] = image_Florence;
			sprites[7][19] = image_Florence;
			sprites[7][20] = image_Kyla;
			sprites[7][21] = image_Kyla;
			sprites[7][22] = image_Kyla;
			sprites[7][23] = image_Trissie;
			sprites[7][24] = image_Trissie;
			sprites[7][25] = image_Trissie;
			sprites[7][26] = image_Trissie;
			sprites[7][27] = image_Alder;
			sprites[7][28] = image_Trissie;
			break;
		case 8:
			break;
		case 9:
			sprites[9][0] = image_Alder;
			sprites[9][1] = image_Alder;
			sprites[9][2] = image_Alder;
			sprites[9][3] = image_Alder;
			sprites[9][4] = image_Alder;
			sprites[9][5] = image_Agrimus;
			sprites[9][6] = image_Alder;
			sprites[9][7] = image_Agrimus;
			sprites[9][8] = image_Alder;
			sprites[9][9] = image_Alder;
			sprites[9][10] = image_Alder;
			sprites[9][11] = image_Alder;
			sprites[9][12] = image_Agrimus;
			sprites[9][13] = image_Alder;
			sprites[9][14] = image_Alder;
			sprites[9][15] = image_Alder;
			sprites[9][17] = image_Agrimus;
			sprites[9][18] = image_Alder;
			sprites[9][19] = image_Agrimus;
			sprites[9][20] = image_Agrimus;
			break;
		case 10:
			sprites[10][2] = image_Agrimus;
			sprites[10][5] = image_Agrimus;
			break;
		}
	}
}