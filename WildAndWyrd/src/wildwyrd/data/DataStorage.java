package wildwyrd.data;

import java.io.Serializable;
import java.util.ArrayList;

import wildwyrd.game.Story;
import wildwyrd.game.playable.Combatant;
import wildwyrd.game.playable.Player;
import wildwyrd.game.tile.Map;

public class DataStorage implements Serializable {
	//Character stats;
	public int maxHealth[];
	public int health[];
	public int maxStamina[];
	public int stamina[];
	public int baseAttack[];
	public int baseDefence[];
	public int baseAccuracy[];
	public int baseEvasion[];
	public int baseSpeed[];
	//Player character details
	public String direction;
	public Story story;
	public int currentMap;
	public int worldX, worldY;
	//Player inventory
	public ArrayList<Integer> itemId = new ArrayList<>();
	public ArrayList<Integer> itemAmount = new ArrayList<>();
	//Equipment
	public int currentHat[];
	public int currentShirt[];
	public int currentTrousers[];
	public int currentPrimary[];
	public int currentSecondary[];
	//Object on map
	public int mapObjectId[][];
	public int mapObjectName[][];
	public int mapObjectWorldX[][];
	public int mapObjectWorldY[][];
	public int mapObjectLootIds[][];
	public int mapObjectLootNames[][];
	public boolean mapObjectOpened[][];
	//NPCs on map
	public int mapNpcId[][];
	public int mapNpcName[][];
	public int mapNpcWorldX[][];
	public int mapNpcWorldY[][];
	public String mapNpcDirection[][];
}
