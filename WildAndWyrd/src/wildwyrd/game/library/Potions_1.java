package wildwyrd.game.library;

import wildwyrd.game.GamePanel;

public class Potions_1 extends Book {
	public static final int bookId = 3;
	public static final String bookTitle = "Potions";
	public static final ReadType readType = ReadType.randomPage;
	//private static final String[] content = new String[2];
	
	public Potions_1(GamePanel gp) {
		super(gp);
		id = bookId;
		title = bookTitle;
		content = new String[4];
		setPages("potions_1");
	}
}