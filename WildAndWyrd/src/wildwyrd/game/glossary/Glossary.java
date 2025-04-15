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
	private boolean updated = false;
	private int updatedCounter = 0;

	public Glossary(GamePanel gp) {
		this.gp = gp;
		setGlossary(dialogueIndex, "");
	}

	public void setGlossary(int dialogueSet, String read) {
		this.dialogueSet = dialogueSet;

		try {
			InputStream f = getClass().getResourceAsStream("/res/dialogue/Glossary.txt");
			BufferedReader b = new BufferedReader(new InputStreamReader(f));

			for (String i = b.readLine(); i != null; i = b.readLine()) {
				String[] line = i.split("\\$ ", 3);

				for (int j = 0; j < sections.length; ++j) {
					if (line[0].equals(sections[j])) {
						String name = line[1];
						String desc = line[2];

						for (int k = 0; k <= page[j].length; ++k) {
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
	
	public GlossaryPage getPage(String section, String name) {
		for (int i = 0; i < sections.length; i++) {
			for (int j = 0; j < getSize(i); j++) {
				if (page[i][j] != null) {
					if(sections[i].equals(section) &&
						page[i][j].getName().equals(name)){
						return page[i][j];
					}
				}
			}
		}
		return null;
	}
	
	public GlossaryPage getPage(int section, int itemNo) {
		if (gp.glossary.page[section][itemNo] != null) {
			return gp.glossary.page[section][itemNo];
		}
		return null;
	}
	public void unlock(String section, String name) {
		try{
			System.out.println(section + " " + name);
			if(!getPage(section, name).isFound()) {
				getPage(section, name).findGlossaryItem();
				gp.playSE(7);
				updated = true;
				updatedCounter = 200;
			}
		} catch (NullPointerException e) {
			System.out.println(e);
		}
	}

	public int getSize(int section) {
		int count = 0;

		for (int i = 0; i < page[section].length; ++i) {
			if (page[section][i] != null) {
				++count;
			}
		}

		return count;
	}
	
	public boolean isUpdated() {
		//If a new page is unlocked
		updatedCounter--;
		if(updatedCounter <= 0) {updated = false;}
		return updated;
	}
	
	public int progress() {
		int count = 0;
		int found = 0;
		for (int i = 0; i < sections.length; i++) {
			for (int j = 0; j < getSize(i); j++) {
				if (page[i][j] != null) {
					count++;
					if (page[i][j].isFound()) {
						found++;
					}
				}
			}
		}
		return (found * 100) / count;
	}
}