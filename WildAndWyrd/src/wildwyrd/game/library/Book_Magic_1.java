package wildwyrd.game.library;

import wildwyrd.game.GamePanel;

public class Book_Magic_1 extends Book {
	public static final int bookId = 1;
	public static final String bookTitle = "Magic: The Basics";
	public static final ReadType readType = ReadType.pageByPage;
	
	public Book_Magic_1(GamePanel gp) {
		super(gp);
		id = bookId;
		title = bookTitle;
		content = new String[3];
		setPages("magic_the_basics");
	}
}