package wildwyrd.game.library;

import wildwyrd.game.GamePanel;

public class Magical_Articles extends Book {
	public static final int bookId = 2;
	public static final String bookTitle = "Magical Articles";
	public static final ReadType readType = ReadType.randomPage;
	//private static final String[] content = new String[2];
	
	public Magical_Articles(GamePanel gp) {
		super(gp);
		id = bookId;
		title = bookTitle;
		content = new String[2];
		setPages("magical_articles");
	}
}