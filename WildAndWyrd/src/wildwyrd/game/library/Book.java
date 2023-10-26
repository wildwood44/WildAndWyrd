package wildwyrd.game.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Book extends Entity {
	private int bookId;
	private String title;
	private ReadType readType;
	private String[] content = new String[5];
	
	public Book(GamePanel gp, int bookId, ReadType readType) {
		super(gp);
		this.bookId = bookId;
		this.readType = readType;
		setPages();
	}
	
	public void setPages() {
		int count = 0;

		try {
			InputStream f = getClass().getResourceAsStream("/res/dialogue/books.txt");
			BufferedReader b = new BufferedReader(new InputStreamReader(f));
			for (String i = b.readLine(); i != null; i = b.readLine()) {
				String[] line = i.split("\\$ ", 6);
				if (line[0].equals(Integer.toString(bookId))) {
					title = line[1];
					content[count] = line[5];
					count++;
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public String readBook(int read) {
		return content[read];
	}

	public int getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
	}

	public String[] getContent() {
		return content;
	}
}