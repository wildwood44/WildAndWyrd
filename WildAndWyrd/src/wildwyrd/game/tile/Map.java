package wildwyrd.game.tile;

import java.awt.image.BufferedImage;

import wildwyrd.game.GamePanel;

public class Map {
	GamePanel gp;
	BufferedImage worldMap[];
	private int id;
	private int maxWorldCol;
	private int maxWorldRow;
	private int worldWidth;
	private int worldHeight;
	public boolean miniMapOn = false;
	
	public Map(GamePanel gp, int id, int maxWorldCol, int maxWorldRow) {
		this.gp = gp;
		this.id = id;
		this.maxWorldCol = maxWorldCol;
		this.maxWorldRow = maxWorldRow;
		System.out.println(maxWorldCol + " ");
		worldWidth = this.maxWorldCol * gp.tileSize;
		worldHeight = this.maxWorldRow * gp.tileSize;
	}

	public int getId() {
		return id;
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
