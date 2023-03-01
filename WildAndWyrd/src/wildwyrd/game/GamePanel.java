package wildwyrd.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;
import wildwyrd.game.cutscenes.Cutscene;
import wildwyrd.game.cutscenes.CutsceneManager;
import wildwyrd.game.glossary.Glossary;
import wildwyrd.game.object.AssetSetter;
import wildwyrd.game.playable.Player;
import wildwyrd.game.rooms.Room;
import wildwyrd.game.tile.CollisionChecker;
import wildwyrd.game.tile.Map;
import wildwyrd.game.tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	final int originalTileSize = 16;
	final int scale = 4;
	public final int tileSize = 64;
	public final int maxScreenCol = 12;
	public final int maxScreenRow = 8;
	public final int screenWidth = 768;
	public final int screenHeight = 512;
	//public final int maxWorldCol = 22;
	//public final int maxWorldRow = 12;
	//public final int worldWidth = maxWorldCol * tileSize;
	//public final int worldHeight = maxWorldRow * tileSize;
	public final int maxMap = 5;
	public final int maxRoom = 2;
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	public EventHandler eHandler;
	int FPS = 60;
	public TileManager tileM;
	public KeyHandler keyH = new KeyHandler(this);
	public CollisionChecker cChecker;
	public UI ui = new UI(this);
	public Room room = new Room(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public CutsceneManager csManager = new CutsceneManager(this);
	public Glossary glossary = new Glossary();
	Thread gameThread;
	int playerY = 100;
	int playerX = 100;
	int playerSpeed = 4;
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int menuState = 3;
	public final int cutsceneState = 4;
	public final int dialogueState = 5;
	public final int messageState = 6;
	public final int examineState = 7;
	public final int talkingState = 8;
	public final int movingState = 9;
	public final int inventoryState = 10;
	public final int glossaryState = 11;
	public boolean cutsceneOn = false;
	public Map currentMap;
	public Integer selectedObj;
	public int currentRoom = 0;
	public Story s = new Story();
	public Cutscene c;
	public Entity[][] obj = new Entity[10][20];
	public Entity[][] npc = new Entity[10][5];
	public Room[] rm;
	public Map[] maps;
	public Player player = new Player(this, keyH);
	ArrayList<Entity> entityList = new ArrayList<>();

	public GamePanel() {
		this.c = new Cutscene(this.s);
		this.obj = new Entity[2][20];
		this.rm = new Room[2];
		this.maps = new Map[5];
		this.player = new Player(this, this.keyH);
		this.entityList = new ArrayList<>();
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(this.keyH);
		this.setFocusable(true);
	}

	public void setupGame() {
		this.aSetter.setRooms();
		this.aSetter.setObject();
		this.aSetter.setMaps();
		this.gameState = 0;
		currentMap = maps[0];
		System.out.println(maxMap +" "+ currentMap.getMaxWorldCol() + " " + currentMap.getMaxWorldRow());
		tileM = new TileManager(this);
		eHandler = new EventHandler(this);
		cChecker = new CollisionChecker(this);
		this.tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		this.g2 = (Graphics2D) this.tempScreen.getGraphics();
	}

	public void startGameThread() {
		this.gameThread = new Thread(this);
		this.gameThread.start();
	}

	public void run() {
		double drawInterval = (double) (1000000000 / this.FPS);
		double delta = 0.0D;
		long lastTime = System.nanoTime();
		long timer = 0L;
		int drawCount = 0;

		while (this.gameThread != null) {
			long currentTime = System.nanoTime();
			delta += (double) (currentTime - lastTime) / drawInterval;
			timer += currentTime - lastTime;
			lastTime = currentTime;
			if (delta >= 1.0D) {
				this.update();
				this.drawToTempScreen();
				this.drawToScreen();
				--delta;
				++drawCount;
			}

			if (timer >= 1000000000L) {
				System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0L;
			}
		}

	}

	public void update() {
		if (this.gameState == 1) {
			this.player.update();
			this.eHandler.checkCutscene();
		}

	}

	public void drawToTempScreen() {
		long drawStart = 0L;
		if (this.keyH.showDebugText) {
			drawStart = System.nanoTime();
		}

		if (this.gameState == 0) {
			this.rm[this.currentRoom].draw(this.g2);
			this.ui.draw(this.g2);
		} else {
			if (this.gameState == 5) {
				this.rm[this.currentRoom].draw(this.g2);
				this.ui.draw(this.g2);
			} else if (this.gameState == 4) {
				this.rm[this.currentRoom].draw(this.g2);
				this.csManager.draw(this.g2);
				this.ui.draw(this.g2);
			} else if (this.gameState == 7) {
				this.ui.draw(this.g2);
			} else if (this.gameState == menuState) {
				this.ui.draw(this.g2);
			} else if (this.gameState == 10) {
				this.ui.draw(this.g2);
			} else if (this.gameState == 11) {
				this.ui.draw(this.g2);
			} else if (this.gameState == playState) {
				this.rm[this.currentRoom].draw(this.g2);
				this.tileM.draw(this.g2);
				entityList.add(player);
				for (int i = 0; i < this.obj[currentMap.getId()].length; ++i) {
					if (this.obj[currentMap.getId()][i] != null) {
						entityList.add(this.obj[currentMap.getId()][i]);
						//this.obj[1][i].draw(this.g2, this);
					}
				}
				Collections.sort(entityList, new Comparator<Entity>() {
					@Override
					public int compare(Entity e1, Entity e2) {
						int result = Integer.compare(e1.worldY, e2.worldY);
						return result;
					}
					
				});
				
				for(int i = 0; i < entityList.size(); i++) {
					entityList.get(i).draw(this.g2);
				}
				for(int i = 0; i < entityList.size(); i++) {
					entityList.remove(i);
				}

				//this.player.draw(this.g2);
				this.ui.draw(this.g2);
			}
			this.ui.draw(this.g2);
		}

		if (this.keyH.showDebugText) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			this.g2.setFont(new Font("Arial", 0, 20));
			this.g2.setColor(Color.white);
			int x = 10;
			int y = 400;
			int lineHeight = 20;
			this.g2.drawString("WorldX" + this.player.worldX, x, y);
			y = y + lineHeight;
			this.g2.drawString("WorldY" + this.player.worldY, x, y);
			y += lineHeight;
			this.g2.drawString("Col" + (this.player.worldX + this.player.solidArea.x) / tileSize, x, y);
			y += lineHeight;
			this.g2.drawString("Row" + (this.player.worldY + this.player.solidArea.y) / tileSize, x, y);
			y += lineHeight;
			this.g2.drawString("Story: " + this.s.chapter + " " + this.s.part, x, y);
			y += lineHeight;
			this.g2.drawString("Draw Time: " + passed, x, y);
		}

	}

	public void drawToScreen() {
		Graphics g = this.getGraphics();
		g.drawImage(this.tempScreen, 0, 0, this.screenWidth2, this.screenHeight2, (ImageObserver) null);
		g.dispose();
	}

	public void removeTempEntity() {
	}
}