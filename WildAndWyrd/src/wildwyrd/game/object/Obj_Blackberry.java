package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Itm_Bramble_Leaf;

public class Obj_Blackberry extends Entity {
	GamePanel gp;
	public static final int objId = 26;
	public static final String objName = "Blackberry";

	public Obj_Blackberry(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.PickUp;
		solidArea.width = 65;
		solidArea.height = 65;
		//solidAreaDefaultX = solidArea.x;
		//solidAreaDefaultY = solidArea.y;
		//worldX = gp.tileSize * col;
		//worldY = gp.tileSize * row;
		image = setup("/res/objects/iTile_blackberry", gp.tileSize, gp.tileSize);
		setDialogue();
		getImage(image);
	}
	
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		return isCorrectItem;
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("Alder ate a blackberry!",1);
		dialogues[1][0] = new Dialoge("Alder picked some bramble leaves, careful to avoid the thorns. He wondered what potion Kyla was going to use them for.",1);
	}
	
	public void interact() {
		gp.glossary.unlock("plants", "bramble");
		System.out.println(gp.player.itemIsInInventory(id));
		if(gp.objective.quests[1].isAccepted() && !opened && 
				!gp.player.itemIsInInventory(Itm_Bramble_Leaf.itemId)) {
			startDialogue(this, 1);
			gp.player.pickUpObject(new Itm_Bramble_Leaf(gp));
			opened = true;
		} else {
			startDialogue(this, 0);
			destroy = true;
			gp.playable.get(0).eat(5);
		}
		gp.keyH.enterPressed = false;
	}
}
