package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.cutscenes.Portrate;
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
	Portrate port = new Portrate();
	public Obj_Bookshelf(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/img_bookshelf", gp.tileSize, gp.tileSize*2);
		
		solidArea.width = 64;
		solidArea.y = 40;
		solidAreaDefaultY = solidArea.y;
		setDialogue();
		setBooks();
		getImage(image);
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("Alder","A \"Cohuleen Druith\" hat.",port.image_Alder);
		dialogues[0][1] = new Dialoge("Alder","The \"Ring of Eluned\".",port.image_Alder);
		dialogues[0][2] = new Dialoge("Alder","The \"Gae Bulg\" spear.",port.image_Alder);
		dialogues[0][3] = new Dialoge("Alder","Found it!",port.image_Alder);
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
		System.out.println(gp.s.c3Switch[1] +" "+ gp.s.part);
		if((!gp.s.c3Switch[1]) && gp.s.part == 1) {
			startDialogue(this, 0);
			gp.s.c3Switch[2] = true;
			gp.s.part = 2;
		}  else {
			gp.gameState = GameState.readingState;
			gp.keyH.enterPressed = false;
			gp.ui.selectedBookshelf = books;
		}
	}

	
}
