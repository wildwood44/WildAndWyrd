package wildwyrd.data;

import java.io.Serializable;

import wildwyrd.game.Story;
import wildwyrd.game.playable.Playable;
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
	//public Player player;
	public String direction;
	public Story story;
	public int currentMap;
	public int worldX, worldY;
	//Object on map
	public int mapObjectId[][];
	public int mapObjectName[][];
	public int mapObjectWorldX[][];
	public int mapObjectWorldY[][];
	public int mapObjectLootIds[][];
	public int mapObjectLootNames[][];
	public boolean mapObjectOpened[][];
}
