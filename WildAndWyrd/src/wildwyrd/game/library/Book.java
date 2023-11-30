package wildwyrd.game.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Book extends Entity {
	public int id;
	public String title;
	public ReadType readType;
	protected String[] content;
	
	public Book(GamePanel gp) {
		super(gp);
	}
	
	public void setPages(String url) {
		int count = 0;

		try {
			InputStream f = getClass().getResourceAsStream("/res/dialogue/book/"+url+".txt");
			BufferedReader b = new BufferedReader(new InputStreamReader(f));
			for (String i = b.readLine(); i != null; i = b.readLine()) {
				String[] line = i.split("\\$ ", 3);
				content[count] = line[2];
				count++;
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public String getTitle() {
		return title;
	}

	public String readBook(int read) {
		return content[read];
	}

	public String[] getContent() {
		return content;
	}
}