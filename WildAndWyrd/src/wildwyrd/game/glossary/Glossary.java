package wildwyrd.game.glossary;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import wildwyrd.game.GamePanel;

public class Glossary {
	GamePanel gp;
	public GlossaryPage[][] page = new GlossaryPage[7][50];
	public BufferedImage[][] sprites = new BufferedImage[100][100];
	public int dialogueIndex = 0;
	public int dialogueSet = 0;
	public String[] sections = new String[]{"constructs", "mammal", "invertebrates", "plants", "folklore"};

	public Glossary() {
		this.setGlossary(this.dialogueIndex, "");
	}

	public void setGlossary(int dialogueSet, String read) {
		this.dialogueSet = dialogueSet;

		try {
			InputStream f = getClass().getResourceAsStream("/res/dialogue/Glossary.txt");
			BufferedReader b = new BufferedReader(new InputStreamReader(f));

			for (String i = b.readLine(); i != null; i = b.readLine()) {
				String[] line = i.split("\\$ ", 3);

				for (int j = 0; j < this.sections.length; ++j) {
					if (line[0].equals(this.sections[j])) {
						String name = line[1];
						String desc = line[2];

						for (int k = 0; k <= this.page[j].length; ++k) {
							if (page[j][k] == null) {
								page[j][k] = new GlossaryPage(j, name, desc);
								break;
							}
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public int getSize(int section) {
		int count = 0;

		for (int i = 0; i < this.page[section].length; ++i) {
			if (this.page[section][i] != null) {
				++count;
			}
		}

		return count;
	}
}