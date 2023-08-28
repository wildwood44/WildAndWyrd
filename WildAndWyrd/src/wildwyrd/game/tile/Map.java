package wildwyrd.game.tile;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import wildwyrd.game.GamePanel;

public class Map implements Serializable {
	GamePanel gp;
	BufferedImage worldMap[];
	private int id;
	private MapType type;
	private int maxWorldCol;
	private int maxWorldRow;
	private int worldWidth;
	private int worldHeight;
	public boolean miniMapOn = false;
	
	public Map(GamePanel gp, MapType type, int id, int maxWorldCol, int maxWorldRow) {
		this.gp = gp;
		this.id = id;
		this.type = type;
		this.maxWorldCol = maxWorldCol;
		this.maxWorldRow = maxWorldRow;
		worldWidth = this.maxWorldCol * gp.tileSize;
		worldHeight = this.maxWorldRow * gp.tileSize;
	}

	public int getId() {
		return id;
	}

	public MapType getType() {
		return type;
	}

	public int getMaxWorldCol() {
		return maxWorldCol;
	}

	public int getMaxWorldRow() {
		return maxWorldRow;
	}

	public int getWorldWidth() {
		return worldWidth;
	}

	public int getWorldHeight() {
		return worldHeight;
	}
	
}
