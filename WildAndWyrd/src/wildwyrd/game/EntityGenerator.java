package wildwyrd.game;

import wildwyrd.game.items.Item;
import wildwyrd.game.items.Itm_Bandage;
import wildwyrd.game.items.Itm_Bow;
import wildwyrd.game.items.Itm_Bramble_Leaf;
import wildwyrd.game.items.Itm_Bug_Meat;
import wildwyrd.game.items.Itm_Hazelnut;
import wildwyrd.game.items.Itm_Hunting_Knife;
import wildwyrd.game.items.Itm_P_Mushroom;
import wildwyrd.game.items.Itm_Primative_Arrow;
import wildwyrd.game.npc.NPC;
import wildwyrd.game.npc.NPC_Cricket;
import wildwyrd.game.npc.NPC_Dilecto;
import wildwyrd.game.npc.NPC_Florence;
import wildwyrd.game.npc.NPC_Kyla;
import wildwyrd.game.npc.NPC_Thay;
import wildwyrd.game.npc.NPC_Trissie;
import wildwyrd.game.object.Obj_Alder_Bed;
import wildwyrd.game.object.Obj_Alder_Window;
import wildwyrd.game.object.Obj_Basin;
import wildwyrd.game.object.Obj_Blackberry;
import wildwyrd.game.object.Obj_Bookshelf;
import wildwyrd.game.object.Obj_Cauldron;
import wildwyrd.game.object.Obj_Chair;
import wildwyrd.game.object.Obj_Crate;
import wildwyrd.game.object.Obj_Cupboard;
import wildwyrd.game.object.Obj_Dummy;
import wildwyrd.game.object.Obj_Hearth;
import wildwyrd.game.object.Obj_Kitchen_Window;
import wildwyrd.game.object.Obj_Larder;
import wildwyrd.game.object.Obj_Oven;
import wildwyrd.game.object.Obj_P_Mushroom;
import wildwyrd.game.object.Obj_Pot;
import wildwyrd.game.object.Obj_Pots;
import wildwyrd.game.object.Obj_Rock;
import wildwyrd.game.object.Obj_SilverBirch;
import wildwyrd.game.object.Obj_Table;
import wildwyrd.game.object.Obj_Table_Left;
import wildwyrd.game.object.Obj_Table_Right;
import wildwyrd.game.object.Obj_Tree;
import wildwyrd.game.object.Obj_Window_Down;
import wildwyrd.game.object.Obj_Wooden_Bowl;
import wildwyrd.game.object.Obj_Worktable;
import wildwyrd.game.object.Plant_1;

public class EntityGenerator {

	GamePanel gp;
	public EntityGenerator(GamePanel gp) {
		this.gp = gp;
	}
	
	public Entity getObject(int itemId) {
		Entity obj = null;
		switch(itemId) {
		case Obj_Wooden_Bowl.objId: obj = new Obj_Wooden_Bowl(gp);break;//0
		case Obj_Table_Left.objId: obj = new Obj_Table_Left(gp);break;//1
		case Obj_Table_Right.objId: obj = new Obj_Table_Right(gp);break;//2
		case Obj_Oven.objId: obj = new Obj_Oven(gp);break;//3
		case Obj_Pots.objId: obj = new Obj_Pots(gp);break;//4
		case Obj_Hearth.objId: obj = new Obj_Hearth(gp);break;//5
		case Obj_Cauldron.objId: obj = new Obj_Cauldron(gp);break;//6
		case Obj_Cupboard.objId: obj = new Obj_Cupboard(gp);break;//7
		case Obj_Larder.objId: obj = new Obj_Larder(gp);break;//8
		case Obj_Kitchen_Window.objId: obj = new Obj_Kitchen_Window(gp);break;//9
		case Obj_Basin.objId: obj = new Obj_Basin(gp);break;//10
		case Obj_Bookshelf.objId: obj = new Obj_Bookshelf(gp);break;//11
		case Obj_Chair.objId: obj = new Obj_Chair(gp);break;//12
		case Obj_Table.objId: obj = new Obj_Table(gp);break;//13
		case Obj_Crate.objId: obj = new Obj_Crate(gp);break;//14
		case Obj_Pot.objId: obj = new Obj_Pot(gp);break;//15
		case Obj_Window_Down.objId: obj = new Obj_Window_Down(gp);break;//16
		case Obj_Worktable.objId: obj = new Obj_Worktable(gp);break;//17
		case Obj_Alder_Window.objId: obj = new Obj_Alder_Window(gp);break;//18
		case Obj_Alder_Bed.objId: obj = new Obj_Alder_Bed(gp);break;//19
		case Obj_P_Mushroom.objId: obj = new Obj_P_Mushroom(gp);break;//20
		case Obj_Rock.objId: obj = new Obj_Rock(gp,0);break;//21
		case Plant_1.objId: obj = new Plant_1(gp);break;//22
		case Obj_SilverBirch.objId: obj = new Obj_SilverBirch(gp);break;//23
		case Obj_Tree.objId: obj = new Obj_Tree(gp);break;//24
		case Obj_Dummy.objId: obj = new Obj_Dummy(gp);break;//25
		case Obj_Blackberry.objId: obj = new Obj_Blackberry(gp);break;//26
		case Itm_Hazelnut.itemId:obj = new Itm_Hazelnut(gp);break;//101
		case Itm_P_Mushroom.itemId:obj = new Itm_P_Mushroom(gp);break;//102
		case Itm_Bug_Meat.itemId:obj = new Itm_Bug_Meat(gp);break;//103
		case Itm_Bandage.itemId:obj = new Itm_Bandage(gp);break;//201
		case Itm_Hunting_Knife.itemId:obj = new Itm_Hunting_Knife(gp);break;//301
		case Itm_Bow.itemId:obj = new Itm_Bow(gp);break;//302
		case Itm_Primative_Arrow.itemId:obj = new Itm_Primative_Arrow(gp);break;//501
		case Itm_Bramble_Leaf.itemId:obj = new Itm_Bramble_Leaf(gp);break;//601
		//default
		}
		return obj;
	}
	
	public Item getItem(int itemId) {
		Item obj = null;
		switch(itemId) {
		case Itm_Hazelnut.itemId:obj = new Itm_Hazelnut(gp);break;//101
		case Itm_P_Mushroom.itemId:obj = new Itm_P_Mushroom(gp);break;//102
		case Itm_Bug_Meat.itemId:obj = new Itm_Bug_Meat(gp);break;//103
		case Itm_Bandage.itemId:obj = new Itm_Bandage(gp);break;//201
		case Itm_Hunting_Knife.itemId:obj = new Itm_Hunting_Knife(gp);break;//301
		case Itm_Bow.itemId:obj = new Itm_Bow(gp);break;//302
		case Itm_Primative_Arrow.itemId:obj = new Itm_Primative_Arrow(gp);break;//501
		case Itm_Bramble_Leaf.itemId:obj = new Itm_Bramble_Leaf(gp);break;//601
		//default
		}
		return obj;
	}
	
	public NPC getNpc(int npcId) {
		NPC npc = null;
		switch(npcId) {
		case NPC_Dilecto.npcId: npc = new NPC_Dilecto(gp);break;//0
		case NPC_Florence.npcId: npc = new NPC_Florence(gp);break;//1
		case NPC_Thay.npcId: npc = new NPC_Thay(gp);break;//2
		case NPC_Kyla.npcId: npc = new NPC_Kyla(gp);break;//3
		case NPC_Cricket.npcId: npc = new NPC_Cricket(gp);break;//4
		case NPC_Trissie.npcId: npc = new NPC_Trissie(gp);break;//5
		}
		return npc;
	}
}
