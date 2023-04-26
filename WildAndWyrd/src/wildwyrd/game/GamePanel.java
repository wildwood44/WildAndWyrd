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
import wildwyrd.game.playable.Playable;
import wildwyrd.game.playable.Player;
import wildwyrd.game.rooms.Room;
import wildwyrd.game.tile.CollisionChecker;
import wildwyrd.game.tile.InteractiveTile;
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
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	//public final int worldWidth = maxWorldCol * tileSize;
	//public final int worldHeight = maxWorldRow * tileSize;
	public final int maxMap = 5;
	public final int maxRoom = 5;
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
	public GameState gameState;
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
	public BufferedImage background;
	public Story s = new Story();
	public Cutscene c;
	public Playable[] playable = new Playable[6];
	public Entity[][] obj = new Entity[10][25];
	public Entity[][] npc = new Entity[10][5];
	public InteractiveTile iTile[][] = new InteractiveTile[10][50];
	public Room[] rm;
	public Map[] maps;
	//public Backdrop backdrop = new Backdrop(this);
	public Player player = new Player(this, keyH);
	ArrayList<Entity> entityList = new ArrayList<>();

	public GamePanel() {
		c = new Cutscene(s);
		obj = new Entity[maxMap][25];
		rm = new Room[maxRoom];
		maps = new Map[maxMap];
		player = new Player(this, keyH);
		playable[0] = new Playable(this, "Alder", 60, 60,
				10, 10, 5, 10, 5);
		entityList = new ArrayList<>();
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);
		setDoubleBuffered(true);
		addKeyListener(keyH);
		setFocusable(true);
	}

	public void setupGame() {
		aSetter.setRooms();
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMaps();
		aSetter.setInteractiveTile();
		gameState = GameState.titleState;
		currentMap = maps[0];
		tileM = new TileManager(this);
		eHandler = new EventHandler(this);
		cChecker = new CollisionChecker(this);
		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D) tempScreen.getGraphics();
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double delta = 0.0D;
		long lastTime = System.nanoTime();
		long timer = 0L;
		int drawCount = 0;

		while (gameThread != null) {
			long currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += currentTime - lastTime;
			lastTime = currentTime;
			if (delta >= 1.0D) {
				update();
				drawToTempScreen();
				drawToScreen();
				--delta;
				++drawCount;
			}

			if (timer >= 1000000000L) {
				//System.out.println(gameState);
				System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0L;
			}
		}

	}

	public void update() {
		if (gameState == GameState.playState) {
			player.update();
			eHandler.checkCutscene();
			for(int i = 0; i < iTile[currentMap.getId()].length; i++) {
				if(iTile[currentMap.getId()][i] != null) {
					//System.out.println("Ping");
					iTile[currentMap.getId()][i].update();
				}
			}
		}

	}

	public void drawToTempScreen() {
		long drawStart = 0L;
		if (this.keyH.showDebugText) {
			drawStart = System.nanoTime();
		}

		if (gameState == GameState.titleState) {
			rm[currentRoom].draw(g2);
			ui.draw(g2);
		} else {
			if (gameState == GameState.dialogueState) {
				rm[currentRoom].draw(g2);
				ui.draw(g2);
			} else if (gameState == GameState.cutsceneState) {
				rm[currentRoom].draw(g2);
				//g2.drawImage(background, 0, 0, this);
				csManager.draw(g2);
				ui.draw(g2);
			} else if (gameState == GameState.examineState) {
				ui.draw(g2);
			} else if (gameState == GameState.talkingState) {
				ui.draw(g2);
			} else if (gameState == GameState.menuState) {
				ui.draw(g2);
			} else if (gameState == GameState.statusState) {
				ui.draw(g2);
			} else if (gameState == GameState.inventoryState) {
				ui.draw(g2);
			} else if (gameState == GameState.glossaryState) {
				ui.draw(g2);
			} else if (gameState == GameState.playState) {
				//rm[currentRoom].draw(g2);
				tileM.draw(g2);
				for(int i = 0; i < iTile[currentMap.getId()].length; i++) {
					if(iTile[currentMap.getId()][i] != null) {
						iTile[currentMap.getId()][i].draw(g2);
					}
				}
				entityList.add(player);
				//OBJECTS
				for (int i = 0; i < obj[currentMap.getId()].length; i++) {
					if (obj[currentMap.getId()][i] != null) {
						entityList.add(obj[currentMap.getId()][i]);
						//this.obj[1][i].draw(g2, this);
					}
				}
				//NPC
				for (int i = 0; i < npc[currentMap.getId()].length; i++) {
					if (npc[currentMap.getId()][i] != null) {
						//entityList.add(npc[currentMap.getId()][i]);
						npc[currentMap.getId()][i].draw(g2);
					}
				}
				Collections.sort(entityList, new Comparator<Entity>() {
					@Override
					public int compare(Entity e1, Entity e2) {
						int result = Integer.compare(e1.worldY, e2.worldY);
						return result;
					}

				});

				for (Entity element : entityList) {
					element.draw(g2);
				}
				entityList.clear();

				//this.player.draw(this.g2);
				ui.draw(g2);
			}
			ui.draw(g2);
		}

		if (keyH.showDebugText) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setFont(new Font("Arial", 0, 20));
			g2.setColor(Color.white);
			int x = 10;
			int y = 400;
			int lineHeight = 20;
			g2.drawString("WorldX" + player.worldX, x, y);
			y = y + lineHeight;
			g2.drawString("WorldY" + player.worldY, x, y);
			y += lineHeight;
			g2.drawString("Col" + (player.worldX + player.solidArea.x) / tileSize, x, y);
			y += lineHeight;
			g2.drawString("Row" + (player.worldY + player.solidArea.y) / tileSize, x, y);
			y += lineHeight;
			g2.drawString("Story: " + s.chapter + " " + s.part, x, y);
			y += lineHeight;
			g2.drawString("Draw Time: " + passed, x, y);
		}

	}

	public void drawToScreen() {
		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, (ImageObserver) null);
		g.dispose();
	}

	public void removeTempEntity() {
	}
}