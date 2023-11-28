package wildwyrd.game.library;

import wildwyrd.game.GamePanel;

public class Dev_1 extends Book {
	public static final int bookId = 4;
	public static final String bookTitle = "Developers Note - Kyla's cottage";
	public static final ReadType readType = ReadType.skippablePage;
	
	public Dev_1(GamePanel gp) {
		super(gp);
		id = bookId;
		title = bookTitle;
		content = new String[3];
		setPages("dev_cottage");
	}
}