package wildwyrd.game.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import wildwyrd.game.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int[][][] mapTileNum;

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[50];
		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/res/maps/map01",0);
		loadMap("/res/maps/map02",1);
		loadMap("/res/maps/map03",2);
		loadMap("/res/maps/map04",3);
	}

	public void getTileImage() {
		if(gp.currentMap.getType() == MapType.INTERIOR) {
			setup(10, "kitchen_tiles", false);
			setup(11, "blank_tiles", true);
			setup(12, "Cottage_Wall_Tile", true);
			setup(13, "Cottage_Ceiling1_Tile", true);
			setup(14, "Cottage_Ceiling2_Tile", true);
			setup(15, "dirt_tile", false);
			setup(16, "stair_tile_horizontal", false);
			setup(17, "Balcony_Wood", true);
			setup(18, "Balcony_Wooden_Left", false);
		}
		else if(gp.currentMap.getType() == MapType.OUTSIDE) {
			setup(11, "forestFloor_tile", false);
			setup(12, "Rockwall_Tile", true);
			setup(13, "bramble_tileset", true);
			setup(15, "Rockwall_Door4", false);
			setup(20, "dirt_grass_tile", false);
			setup(21, "dirt_grass_tile-top_left", false);
			setup(22, "dirt_grass_tile-left", false);
			setup(23, "dirt_grass_tile-bottom_left", false);
			setup(24, "dirt_grass_tile-top", false);
			setup(25, "dirt_grass_tile", false);
			setup(26, "dirt_grass_tile-bottom", false);
			setup(27, "dirt_grass_tile-top_right", false);
			setup(28, "dirt_grass_tile-right", false);
			setup(29, "dirt_grass_tile-bottom_right", false);
			setup(40, "nettle_tile", true, true);
			setup(41, "nettle_tile-top_left", true, true);
			setup(42, "nettle_tile-left", true, true);
			setup(43, "nettle_tile-bottom_left", true, true);
			setup(44, "nettle_tile-top", true, true);
			setup(45, "nettle_tile", true, true);
			setup(46, "nettle_tile-bottom", true, true);
			setup(47, "nettle_tile-top_right", true, true);
			setup(48, "nettle_tile-right", true, true);
			setup(49, "nettle_tile-bottom_right", true, true);
		}
		
	}
	
	public void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/"+imageName+".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setup(int index, String imageName, boolean collision, boolean nettles) {
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/"+imageName+".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			tile[index].nettles = nettles;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String filePath, int map) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col = 0;
			int row = 0;

			while (col < gp.maps[map].getMaxWorldCol() && row < gp.currentMap.getMaxWorldRow()) {
				if (col >= gp.maps[map].getMaxWorldCol()) {
					break;
				}
				if (row >= gp.maps[map].getMaxWorldRow()) {
					break;
				}
				String line = br.readLine();
				while (true) {
					if (col >= gp.maps[map].getMaxWorldCol()) {
						if (col == gp.maps[map].getMaxWorldCol()) {
							col = 0;
							++row;
						}
						break;
					}
					String[] numbers = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[map][col][row] = num;
					col++;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;

		while (true) {
			if (worldCol >= gp.currentMap.getMaxWorldCol()) {
				break;
			}

			if (worldRow >= gp.currentMap.getMaxWorldRow()) {
				break;
			}

			int tileNum = mapTileNum[gp.currentMap.getId()][worldCol][worldRow];
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			if (gp.player.screenX > gp.player.worldX) {
				screenX = worldX;
			}

			if (gp.player.screenY > gp.player.worldY) {
				screenY = worldY;
			}

			int rightOffset = gp.screenWidth - gp.player.screenX;
			if (rightOffset > gp.currentMap.getWorldWidth() - gp.player.worldX) {
				screenX = gp.screenWidth - (gp.currentMap.getWorldWidth() - worldX);
			}

			int bottomOffset = gp.screenHeight - gp.player.screenY;
			if (bottomOffset > gp.currentMap.getWorldHeight() - gp.player.worldY) {
				screenY = gp.screenHeight - (gp.currentMap.getWorldHeight() - worldY);
			}

			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			} 
			else if (gp.player.screenX > gp.player.worldX ||
				gp.player.screenY > gp.player.worldY ||
				rightOffset > gp.currentMap.getWorldWidth() - gp.player.worldX ||
				bottomOffset > gp.currentMap.getWorldHeight() - gp.player.worldY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			worldCol++;
			if (worldCol == gp.currentMap.getMaxWorldCol()) {
				worldCol = 0;
				worldRow++;
			}
			
		}

	}
}