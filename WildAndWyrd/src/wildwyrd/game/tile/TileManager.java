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
		tile = new Tile[90];
		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/res/maps/map01",0);
		loadMap("/res/maps/map02",1);
		loadMap("/res/maps/map03",2);
		loadMap("/res/maps/map04",3);
		loadMap("/res/maps/map05",4);
		loadMap("/res/maps/map06",5);
		loadMap("/res/maps/map07",6);
		loadMap("/res/maps/map08",7);
		loadMap("/res/maps/map09",8);
		loadMap("/res/maps/map10",9);
		loadMap("/res/maps/map11",10);
		loadMap("/res/maps/map12",11);
		loadMap("/res/maps/map13",12);
		loadMap("/res/maps/map14",13);
	}

	public void getTileImage() {
		if(gp.currentMap.getType() == MapType.INTERIOR) {
			setup(10, "kitchen_tiles", false);
			setup(11, "blank_tile", true);
			setup(12, "Cottage_Wall_Tile", true);
			setup(13, "Cottage_Ceiling1_Tile", true);
			setup(14, "Cottage_Ceiling2_Tile", true);
			setup(15, "dirt_tile", false);
			setup(16, "stair_tile_horizontal", true);
			setup(17, "Balcony_Wood", true);
			setup(18, "Balcony_Wooden_Left", false);
			setup(20, "wall", true);
			setup(21, "wall_top", true);
			setup(22, "wall_bottom", true);
			setup(23, "wall_top-left", true);
			setup(24, "wall_top-right", true);
			setup(25, "wall_beam", true);
			setup(26, "wall_beam_top", true);
			setup(27, "wall_beam_bottom", true);
			setup(28, "med_wall_top", true);
			setup(29, "med_wall_bottom", true);
			setup(30, "med_wall_top-left", true);
			setup(31, "med_wall-left", true);
			setup(32, "med_wall_bottom-left", true);
			setup(33, "med_wall_top-right", true);
			setup(34, "med_wall-right", true);
			setup(35, "med_wall_bottom-right", true);
			setup(36, "med_wall_corner-top_left", true);
			setup(37, "med_wall_corner-bottom_left", true);
			setup(38, "med_wall_corner-top_right", true);
			setup(39, "med_wall_corner-bottom_right", true);
			setup(40, "med_wall", true);
		}
		else if(gp.currentMap.getType() == MapType.OUTSIDE) {
			setup(11, "forestFloor_tile", false);
			setup(12, "Rockwall_Tile", true);
			setup(13, "bramble_tileset", true);
			setup(15, "Rockwall_Door4", false);
			setup(16, "fallen_tree-top_left", true);
			setup(17, "fallen_tree-top_right", true);
			setup(18, "fallen_tree-bottom_left", true);
			setup(19, "fallen_tree-bottom_right", true);
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
			setup(50, "ditch_tile_center", false);
			setup(51, "ditch_tile_top-left", false);
			setup(52, "ditch_tile_left", false);
			setup(53, "ditch_tile_bottom-left", false);
			setup(54, "ditch_tile_top", false);
			setup(55, "ditch_tile_center", false);
			setup(56, "ditch_tile_bottom", false);
			setup(57, "ditch_tile_top-right", false);
			setup(58, "ditch_tile_right", false);
			setup(59, "ditch_tile_bottom-right", false);
			setup(60, "gorse_tile-centre", true);
			setup(61, "gorse_tile-top_left", true);
			setup(62, "gorse_tile-left", true);
			setup(63, "gorse_tile-bottom_left", true);
			setup(64, "gorse_tile-top", true);
			setup(65, "gorse_tile-centre", true);
			setup(66, "gorse_tile-bottom", true);
			setup(67, "gorse_tile-top_right", true);
			setup(68, "gorse_tile-right", true);
			setup(69, "gorse_tile-bottom_right", true);
			setup(70, "holly_male_tile-centre", true);
			setup(71, "holly_male_tile-top_left", true);
			setup(72, "holly_male_tile-left", true);
			setup(73, "holly_male_tile-bottom_left", true);
			setup(74, "holly_male_tile-top", true);
			setup(75, "holly_male_tile-centre", true);
			setup(76, "holly_male_tile-bottom", true);
			setup(77, "holly_male_tile-top_right", true);
			setup(78, "holly_male_tile-right", true);
			setup(79, "holly_male_tile-bottom_right", true);
			setup(80, "wood_ant_nest-centre", true);
			setup(81, "wood_ant_nest-top_left", false);
			setup(82, "wood_ant_nest-left", false);
			setup(83, "wood_ant_nest-bottom_left", false);
			setup(84, "wood_ant_nest-top", false);
			setup(85, "wood_ant_nest-centre", false);
			setup(86, "wood_ant_nest-bottom", false);
			setup(87, "wood_ant_nest-top_right", false);
			setup(88, "wood_ant_nest-right", false);
			setup(89, "wood_ant_nest-bottom_right", false);
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

	public void overlayMap(String filePath, int map) {
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