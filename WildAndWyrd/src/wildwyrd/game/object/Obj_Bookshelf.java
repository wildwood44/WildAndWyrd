package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.library.Book;
import wildwyrd.game.library.ReadType;

public class Obj_Bookshelf extends Entity {
	GamePanel gp;
	Book[] books = new Book[5]; 
	public Obj_Bookshelf(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Bookshelf";
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/img_bookshelf", gp.tileSize, gp.tileSize);

		solidArea.width = 64;
		solidArea.y = 5;
		solidAreaDefaultY = solidArea.y;
		setDialogue();
		setBooks();
	}

	public void setDialogue() {
		
	}
	
	public void setBooks() {
		books[0] = new Book(gp, 1, ReadType.pageByPage);
		books[1] = new Book(gp, 2, ReadType.randomPage);
		books[2] = new Book(gp, 3, ReadType.randomPage);
		books[3] = new Book(gp, 4, ReadType.skippablePage);
		books[4] = new Book(gp, 5, ReadType.skippablePage);
	}

	public void interact() {
		//startDialogue(this, 0);
		gp.gameState = GameState.readingState;
		gp.keyH.enterPressed = false;
		gp.ui.selectedBookshelf = books;
	}

	
}
