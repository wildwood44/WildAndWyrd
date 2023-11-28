package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.library.Book;
import wildwyrd.game.library.Book_Magic_1;
import wildwyrd.game.library.Dev_1;
import wildwyrd.game.library.Magical_Articles;
import wildwyrd.game.library.Potions_1;

public class Obj_Bookshelf extends Entity {
	GamePanel gp;
	public static final int objId = 11;
	public static final String objName = "Bookshelf";
	Book[] books = new Book[4]; 
	public Obj_Bookshelf(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/img_bookshelf", gp.tileSize, gp.tileSize);

		solidArea.width = 64;
		solidArea.y = 5;
		solidAreaDefaultY = solidArea.y;
		setDialogue();
		setBooks();
		getImage(image);
	}

	public void setDialogue() {
		
	}
	
	public void setBooks() {
		books[0] = new Book_Magic_1(gp);
		books[1] = new Magical_Articles(gp);
		books[2] = new Potions_1(gp);
		books[3] = new Dev_1(gp);
		//books[1] = new Book(gp, 2, ReadType.randomPage);
		//books[2] = new Book(gp, 3, ReadType.randomPage);
		//books[3] = new Book(gp, 4, ReadType.skippablePage);
		//books[4] = new Book(gp, 5, ReadType.skippablePage);
	}

	public void interact() {
		gp.gameState = GameState.readingState;
		gp.keyH.enterPressed = false;
		gp.ui.selectedBookshelf = books;
	}

	
}
