package wildwyrd.game.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import wildwyrd.game.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int[][] mapTileNum;

	public TileManager(GamePanel gp) {
		this.gp = gp;
		this.tile = new Tile[10];
		gp.getClass();
		gp.getClass();
		this.mapTileNum = new int[26][12];
		this.getTileImage();
		this.loadMap("/res/maps/map01");
	}

	public void getTileImage() {
		try {
			this.tile[0] = new Tile();
			this.tile[0].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tiles/kitchen_tiles.png"));
			this.tile[1] = new Tile();
			this.tile[1].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tiles/Grass_Tile.png"));
			this.tile[2] = new Tile();
			this.tile[2].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tiles/Cottage_Wall_Tile.png"));
			this.tile[2].collision = true;
			this.tile[3] = new Tile();
			this.tile[3].image = ImageIO
					.read(this.getClass().getResourceAsStream("/res/tiles/Cottage_Ceiling1_Tile.png"));
			this.tile[3].collision = true;
			this.tile[4] = new Tile();
			this.tile[4].image = ImageIO
					.read(this.getClass().getResourceAsStream("/res/tiles/Cottage_Ceiling2_Tile.png"));
			this.tile[4].collision = true;
			this.tile[5] = new Tile();
			this.tile[5].image = ImageIO
					.read(this.getClass().getResourceAsStream("/res/tiles/Cottage_Window_Tile.png"));
			this.tile[5].collision = true;
		} catch (IOException var2) {
			var2.printStackTrace();
		}

	}

	public void loadMap(String filePath) {
		try {
			InputStream is = this.getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col = 0;
			int row = 0;

			while (true) {
				this.gp.getClass();
				if (col >= 26) {
					break;
				}

				this.gp.getClass();
				if (row >= 12) {
					break;
				}

				String line = br.readLine();

				while (true) {
					this.gp.getClass();
					if (col >= 26) {
						this.gp.getClass();
						if (col == 26) {
							col = 0;
							++row;
						}
						break;
					}

					String[] numbers = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					this.mapTileNum[col][row] = num;
					++col;
				}
			}

			br.close();
		} catch (Exception var9) {
			var9.printStackTrace();
		}

	}

	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;

		while (true) {
			this.gp.getClass();
			if (worldCol >= 26) {
				break;
			}

			this.gp.getClass();
			if (worldRow >= 12) {
				break;
			}

			int tileNum = this.mapTileNum[worldCol][worldRow];
			this.gp.getClass();
			int worldX = worldCol * 64;
			this.gp.getClass();
			int worldY = worldRow * 64;
			int screenX = worldX - this.gp.player.worldX + this.gp.player.screenX;
			int screenY = worldY - this.gp.player.worldY + this.gp.player.screenY;
			if (this.gp.player.screenX > this.gp.player.worldX) {
				screenX = worldX;
			}

			if (this.gp.player.screenY > this.gp.player.worldY) {
				screenY = worldY;
			}

			this.gp.getClass();
			int rightOffset = 768 - this.gp.player.screenX;
			this.gp.getClass();
			if (rightOffset > 1664 - this.gp.player.worldX) {
				this.gp.getClass();
				this.gp.getClass();
				screenX = 768 - (1664 - worldX);
			}

			this.gp.getClass();
			int bottomOffset = 512 - this.gp.player.screenY;
			this.gp.getClass();
			if (bottomOffset > 768 - this.gp.player.worldY) {
				this.gp.getClass();
				this.gp.getClass();
				screenY = 512 - (768 - worldY);
			}

			label60 : {
				this.gp.getClass();
				BufferedImage var10001;
				if (worldX + 64 > this.gp.player.worldX - this.gp.player.screenX) {
					this.gp.getClass();
					if (worldX - 64 < this.gp.player.worldX + this.gp.player.screenX) {
						this.gp.getClass();
						if (worldY + 64 > this.gp.player.worldY - this.gp.player.screenY) {
							this.gp.getClass();
							if (worldY - 64 < this.gp.player.worldY + this.gp.player.screenY) {
								var10001 = this.tile[tileNum].image;
								this.gp.getClass();
								this.gp.getClass();
								g2.drawImage(var10001, screenX, screenY, 64, 64, (ImageObserver) null);
								break label60;
							}
						}
					}
				}

				if (this.gp.player.screenX <= this.gp.player.worldX
						&& this.gp.player.screenY <= this.gp.player.worldY) {
					this.gp.getClass();
					if (rightOffset <= 1664 - this.gp.player.worldX) {
						this.gp.getClass();
						if (bottomOffset <= 768 - this.gp.player.worldY) {
							break label60;
						}
					}
				}

				var10001 = this.tile[tileNum].image;
				this.gp.getClass();
				this.gp.getClass();
				g2.drawImage(var10001, screenX, screenY, 64, 64, (ImageObserver) null);
			}

			++worldCol;
			this.gp.getClass();
			if (worldCol == 26) {
				worldCol = 0;
				++worldRow;
			}
		}

	}
}