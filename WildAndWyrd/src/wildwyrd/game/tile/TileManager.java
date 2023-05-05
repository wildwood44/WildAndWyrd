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
		tile = new Tile[11];
		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/res/maps/map01",0);
		loadMap("/res/maps/map02",1);
		loadMap("/res/maps/map03",2);
		loadMap("/res/maps/map04",3);
	}

	public void getTileImage() {
		if(gp.currentMap.getType() == MapType.INTERIOR) {
			setup(0, "kitchen_tiles", false);
			setup(1, "blank_tiles", true);
			setup(2, "Cottage_Wall_Tile", true);
			setup(3, "Cottage_Ceiling1_Tile", true);
			setup(4, "Cottage_Ceiling2_Tile", true);
			setup(5, "Cottage_Window_Tile", true);
			setup(6, "stair_tile_horizontal", false);
			setup(7, "Balcony_Wood", true);
			setup(8, "Balcony_Wooden_Left", false);
		}
		else if(gp.currentMap.getType() == MapType.OUTSIDE) {
			setup(0, "dirt_grass_tile", false);
			setup(1, "forestFloor_tile", false);
			setup(2, "Rockwall_Tile", true);
			setup(3, "bramble_tile", true);
			setup(4, "nettle_tile", true);
			setup(5, "img_tree1", true);
			setup(6, "img_tree2", true);
			setup(7, "img_tree3", true);
			setup(8, "img_tree4", true);
			setup(9, "img_tree5", true);
			setup(10, "img_tree6", true);
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

			label60 : {
				if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
					worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
					worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
					g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
				}

				if (gp.player.screenX <= gp.player.worldX
						&& gp.player.screenY <= gp.player.worldY) {
					if (rightOffset <= gp.currentMap.getWorldWidth() - gp.player.worldX) {
						if (bottomOffset <= gp.currentMap.getWorldHeight() - gp.player.worldY) {
							break label60;
						}
					}
				}
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