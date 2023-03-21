package wildwyrd.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import wildwyrd.game.cutscenes.Cutscene;

public class UI {
	GamePanel gp;
	Cutscene c = new Cutscene(new Story());
	Font arial_40;
	Graphics2D g2;
	public boolean messageOn = false;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	public String currentDialogue = "";
	public String nameDialogue = "";
	public String title = "";
	public String message = "";
	public Entity selectedObject;
	public int selectedObjectX;
	public int selectedObjectY;
	public int commandNum = 0;
	public int slotCol = 0;
	public int slotRow = 0;
	public int slotCol2 = 0;
	public int slotRow2 = 0;
	public int section = 0;
	public int glossPage = 0;
	public static Boolean binaryRes = false;
	public int slotyn = 0;
	public int firstValue = 0;
	public int bottomValue = 0;
	public int topValue = 0;
	int charIndex = 0;
	String combinedText = "";

	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Monospaced", 0, 40);
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(arial_40);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		GameState gameState = gp.gameState;
		if (gameState == GameState.titleState) {
			drawTitleScreen();
		}

		gameState = gp.gameState;
		if (gameState == GameState.playState) {
			Integer var2 = gp.selectedObj;
		}

		gameState = gp.gameState;
		if (gameState == GameState.examineState && this.selectedObject != null) {
			drawExamineScreen();
		}

		gameState = gp.gameState;
		if (gameState == GameState.dialogueState) {
			drawDialogueScreen();
		}

		gameState = this.gp.gameState;
		if (gameState == GameState.menuState) {
			this.drawMenuBarScreen();
		}

		gameState = this.gp.gameState;
		if (gameState == GameState.inventoryState) {
			this.drawInventoryScreen();
		}

		gameState = this.gp.gameState;
		if (gameState == GameState.glossaryState) {
			this.drawGlossaryScreen();
		}

	}

	public void drawHeadingScreen(String text) {
		int x = 5;
		int y = 20;
		this.g2.setFont(this.g2.getFont().deriveFont(0, 22.0F));
		this.g2.setColor(Color.white);
		this.message = text;
		this.g2.drawString(this.message, x, y);
	}

	public void drawItemLabel() {
		FontMetrics fm = this.g2.getFontMetrics();
		this.g2.setFont(this.g2.getFont().deriveFont(0, 22.0F));
		this.g2.setColor(Color.white);
		if (this.gp.selectedObj != null) {
			this.selectedObject = this.gp.obj[this.gp.currentRoom][this.gp.selectedObj];
			this.drawDialogueWindow(
					this.selectedObject.x + this.gp.obj[this.gp.currentRoom][this.gp.selectedObj].width - 10,
					this.selectedObject.y - 35, fm.stringWidth(this.selectedObject.name), 50);
			this.g2.setFont(this.g2.getFont().deriveFont(0, 22.0F));
			this.g2.setColor(Color.white);
			this.g2.drawString(this.selectedObject.name,
					this.selectedObject.x + this.gp.obj[this.gp.currentRoom][this.gp.selectedObj].width,
					this.selectedObject.y);
		}

	}

	public void drawMessageScreen(String text) {
		FontMetrics fm = g2.getFontMetrics();
		getXforCenteredText(text);
		int y = (gp.screenHeight - fm.getHeight()) / 2;
		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);
		message = text;
		if (gp.keyH.enterPressed) {
			++gp.csManager.scenePhase;
		}

		String[] var8;
		int var7 = (var8 = message.split(":")).length;

		for (int var6 = 0; var6 < var7; ++var6) {
			String line = var8[var6];
			int x = getXforCenteredText(line);
			g2.drawString(line, x, y);
			y += 40;
		}

		gp.keyH.enterPressed = false;
	}

	public void drawDialogueScreen() {
		int x = gp.tileSize * 3 / 2;
		int y = gp.tileSize * 5;
		int width = gp.screenWidth - gp.tileSize * 3;
		int height = gp.tileSize * 3;
		String[][] dia = gp.c.dialogues;
		drawImageWindow(300, 0, 200, 400);
		drawDialogueWindow(x, y, width, height);
		g2.setFont(g2.getFont().deriveFont(0, 18.0F));
		g2.setColor(Color.white);
		x += gp.tileSize;
		y += gp.tileSize;
		GameState gameState;
		if (dia[this.gp.c.dialogueSet][this.gp.c.dialogueIndex] != null) {
			char[] characters = this.gp.c.dialogues[this.gp.c.dialogueSet][this.gp.c.dialogueIndex].toCharArray();
			if (this.charIndex < characters.length) {
				String s = String.valueOf(characters[this.charIndex]);
				this.combinedText = this.combinedText + s;
				this.currentDialogue = this.combinedText;
				++this.charIndex;
				if (this.gp.keyH.enterPressed) {
					this.currentDialogue = this.gp.c.dialogues[this.gp.c.dialogueSet][this.gp.c.dialogueIndex];
				}
			}

			if (this.gp.keyH.enterPressed) {
				label72 : {
					this.charIndex = 0;
					this.combinedText = "";
					gameState = this.gp.gameState;
					this.gp.getClass();
					if (gameState != GameState.dialogueState) {
						gameState = this.gp.gameState;
						this.gp.getClass();
						if (gameState != GameState.cutsceneState) {
							break label72;
						}
					}

					++this.gp.c.dialogueIndex;
					this.gp.keyH.enterPressed = false;
				}
			} else if (this.gp.keyH.skipPressed) {
				label67 : {
					this.charIndex = 0;
					this.combinedText = "";
					gameState = this.gp.gameState;
					this.gp.getClass();
					if (gameState != GameState.dialogueState) {
						gameState = this.gp.gameState;
						this.gp.getClass();
						if (gameState != GameState.cutsceneState) {
							break label67;
						}
					}

					this.gp.c.dialogueIndex = this.gp.c.dialogues.length - 1;
					this.gp.keyH.skipPressed = false;
				}
			}
		} else {
			this.c.dialogueIndex = 0;
			gameState = this.gp.gameState;
			this.gp.getClass();
			if (gameState == GameState.dialogueState) {
				GamePanel var12 = this.gp;
				this.gp.getClass();
				var12.gameState = GameState.playState;
			}

			gameState = this.gp.gameState;
			this.gp.getClass();
			if (gameState == GameState.cutsceneState) {
				++this.gp.csManager.scenePhase;
			}
		}

		String[] var9;
		int var8 = (var9 = this.currentDialogue.split(":")).length;

		for (int var11 = 0; var11 < var8; ++var11) {
			String line = var9[var11];
			if (!line.equals("Dilecto") && !line.equals("Dean") && !line.equals("Ralph") && !line.equals("Plumm")
					&& !line.equals("Thay") && !line.equals("Florence") && !line.equals("Alder") && !line.equals("Kyla")
					&& !line.equals("Shrew") && !line.equals("Vole") && !line.equals("Mole") && !line.equals("Hedgehog")
					&& !line.equals("Woman")) {
				this.g2.setFont(this.g2.getFont().deriveFont(0, 18.0F));
				this.g2.drawString(line, x, y);
				y += 30;
			} else {
				this.g2.setFont(this.g2.getFont().deriveFont(1, 24.0F));
				this.g2.drawString(line, x, y);
				y += 40;
			}
		}

	}

	public void drawExamineScreen() {
		int x = gp.tileSize * 3 / 2;
		int y = gp.tileSize * 5;
		int width = gp.screenWidth - gp.tileSize * 3;
		int height = gp.tileSize * 3;
		this.drawDialogueWindow(x, y, width, height);
		g2.setFont(g2.getFont().deriveFont(0, 18.0F));
		g2.setColor(Color.white);
		x += gp.tileSize;
		y += gp.tileSize;
		GameState gameState;
		if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex] != null) {
			char[] characters = selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex]
					.getText().toCharArray();
			if (charIndex < characters.length) {
				String s = String.valueOf(characters[this.charIndex]);
				combinedText = combinedText + s;
				currentDialogue = combinedText;
				++charIndex;
				if (this.gp.keyH.enterPressed) {
					this.currentDialogue = this.selectedObject.dialogues[this.selectedObject.dialogueSet][this.selectedObject.dialogueIndex]
							.getText();
				}
			}

			if (this.selectedObject.dialogues[this.selectedObject.dialogueSet][this.selectedObject.dialogueIndex]
					.getType() == 1) {
				if (this.gp.keyH.enterPressed) {
					if (this.currentDialogue
							.length() == this.selectedObject.dialogues[this.selectedObject.dialogueSet][this.selectedObject.dialogueIndex]
									.getText().length()) {
						this.charIndex = 0;
						this.combinedText = "";
						gameState = gp.gameState;
						if (gameState == GameState.examineState) {
							++this.selectedObject.dialogueIndex;
							this.gp.keyH.enterPressed = false;
						}
					}
				} else if (this.gp.keyH.skipPressed) {
					this.charIndex = 0;
					this.combinedText = "";
					gameState = this.gp.gameState;
					if (gameState == GameState.examineState) {
						this.selectedObject.dialogueIndex = this.selectedObject.dialogues.length - 1;
						this.gp.keyH.skipPressed = false;
					}
				}
			} else if (this.selectedObject.dialogues[this.selectedObject.dialogueSet][this.selectedObject.dialogueIndex]
					.getType() == 2) {
				if (this.gp.keyH.enterPressed) {
					++this.charIndex;
					if (this.slotyn == 0) {
						binaryRes = true;
					} else {
						binaryRes = false;
					}

					this.charIndex = 0;
					this.combinedText = "";
					gameState = this.gp.gameState;
					this.gp.getClass();
					if (gameState == GameState.examineState) {
						this.selectedObject.choiceResponce();
						++this.selectedObject.dialogueIndex;
						this.gp.keyH.enterPressed = false;
					}
				}

				if (this.slotyn == 0) {
					this.g2.drawString(">", x, y + 40);
				} else {
					this.g2.drawString(">", x, y + 80);
				}

				this.g2.drawString("Yes", x + 20, y + 40);
				this.g2.drawString("No", x + 20, y + 80);
			}
		} else {
			this.selectedObject.dialogueIndex = 0;
			gameState = this.gp.gameState;
			if (gameState == GameState.examineState) {
				GamePanel gp = this.gp;
				gp.gameState = GameState.playState;
			}
		}

		String[] var8;
		int var7 = (var8 = this.currentDialogue.split(":")).length;

		for (int var10 = 0; var10 < var7; ++var10) {
			String line = var8[var10];
			if (!line.equals("Dilecto") && !line.equals("Dean") && !line.equals("Ralph") && !line.equals("Plumm")
					&& !line.equals("Thay") && !line.equals("Florence") && !line.equals("Alder") && !line.equals("Kyla")
					&& !line.equals("Shrew") && !line.equals("Vole") && !line.equals("Mole") && !line.equals("Hedgehog")
					&& !line.equals("Woman")) {
				this.g2.setFont(this.g2.getFont().deriveFont(0, 18.0F));
				this.g2.drawString(line, x, y);
				y += 30;
			} else {
				this.g2.setFont(this.g2.getFont().deriveFont(1, 24.0F));
				this.g2.drawString(line, x, y);
				y += 40;
			}
		}

	}

	public void drawMenuBarScreen() {
		int frameX = 20;
		int frameY = 25;
		int frameWidth = gp.tileSize * 4;
		int frameHeight = gp.tileSize * 7;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int slotXstart = frameX + 0;
		int slotYstart = frameY + 15;
		int cursorX = slotXstart + gp.tileSize * this.slotRow;
		double var10000 = slotYstart;
		int cursorY = (int) (var10000 + gp.tileSize * 0.75D * this.slotCol);
		int cursorWidth = gp.tileSize * 2;
		int cursorHeight = 30;
		this.g2.setFont(this.g2.getFont().deriveFont(0, 22.0F));
		this.g2.setColor(Color.white);
		this.g2.setStroke(new BasicStroke());
		Graphics2D var12 = this.g2;
		var12.drawString("Save", 30, gp.tileSize);
		var12 = this.g2;
		var12.drawString("Stats", 30, (int) (gp.tileSize * 1.75D));
		var12 = this.g2;
		var12.drawString("Items", 30, (int) (gp.tileSize * 2.5D));
		var12 = this.g2;
		var12.drawString("Equipment", 30, (int) (gp.tileSize * 3.25D));
		var12 = this.g2;
		var12.drawString("Objecties", 30, gp.tileSize * 4);
		var12 = this.g2;
		var12.drawString("Skill", 30, (int) (gp.tileSize * 4.75D));
		var12 = this.g2;
		var12.drawString("Glossary", 30, (int) (gp.tileSize * 5.5D));
		var12 = this.g2;
		var12.drawString("Quit", 30, (int) (gp.tileSize * 6.25D));
		this.g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
	}

	public void drawInventoryScreen() {
		int frameX = 300;
		int frameY = 25;
		int frameWidth = gp.tileSize * 7;
		int frameHeight = gp.tileSize * 4;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int slotXstart = frameX + 15;
		int slotYstart = frameY + 15;
		int slotX = slotXstart;
		int slotY = slotYstart;

		int i;
		for (i = 0; i < gp.player.inventory.size(); ++i) {
			this.g2.drawImage(gp.player.inventory.get(i).image, slotX, slotY, (ImageObserver) null);
			slotX += gp.tileSize;
			if (i == 6 || i == 13 || i == 20) {
				slotX = slotXstart;
				slotY += gp.tileSize;
			}
		}

		i = slotXstart + gp.tileSize * this.slotCol2;
		int cursorY = slotYstart + gp.tileSize * this.slotRow2;
		int cursorWidth = (int) (gp.tileSize / 1.65D);
		int cursorHeight = (int) (gp.tileSize / 1.65D);
		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke());
		g2.drawRoundRect(i, cursorY, cursorWidth, cursorHeight, 10, 10);
		int dFrameY = frameY + frameHeight;
		int dFrameHeight = gp.tileSize * 3;
		drawDialogueWindow(frameX, dFrameY, frameWidth, dFrameHeight);
		int textX = frameX + 20;
		int textY = dFrameY + gp.tileSize;
		int itemIndex = this.getItemIndexOnSlot();
		g2.setColor(Color.white);
		if (itemIndex < gp.player.inventory.size()) {
			String[] var23;
			int var22 = (var23 = this.gp.player.inventory.get(itemIndex).description.split(":")).length;

			for (int var21 = 0; var21 < var22; ++var21) {
				String line = var23[var21];
				g2.drawString(line, textX, textY);
				textY += 40;
			}
		}
		frameX = 20;
		frameY = 25;
		frameWidth = gp.tileSize * 4;
		frameHeight = gp.tileSize * 7;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);
		g2.drawString("Shillings: " + gp.player.getShillings(), 30, gp.tileSize);

	}

	public void drawGlossaryScreen() {
		int frameX = 20;
		int frameY = 25;
		int frameWidth = gp.tileSize * 4;
		int frameHeight = gp.tileSize * 7;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int slotXstart = frameX + 0;
		int slotYstart = frameY + 15;
		int cursorX = slotXstart + gp.tileSize * this.slotRow;
		double var10000 = slotYstart * 2.25D;
		int cursorY = (int) (var10000 + gp.tileSize * 1 * this.slotCol);
		int cursorWidth = gp.tileSize * 2;
		int cursorHeight = 30;
		g2.setFont(this.g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke());
		Graphics2D g2 = this.g2;
		String var10001 = gp.glossary.sections[section];
		g2.drawString(var10001, 30, gp.tileSize);
		int pos = (int) (gp.tileSize * 0.75D);
		bottomValue = gp.glossary.getSize(section);
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

		for (int i = 0; i < 6; ++i) {
			if (gp.glossary.page[section][i] != null) {
				pos += gp.tileSize;

				try {
					g2.drawString(gp.glossary.page[section][i + topValue].getTitle(), 30, pos);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}

		StringBuilder htmlBuilder = new StringBuilder();
		frameX = 300;
		frameY = 25;
		frameWidth = gp.tileSize * 7;
		//frameHeight = gp.tileSize * 4;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);

		try {
			//htmlBuilder.append("<html>");
			//htmlBuilder.append("<head><title>"+gp.glossary.page[section][slotCol + topValue].getTitle()+"</title></head></br>");
			g2.drawString(gp.glossary.page[section][slotCol + topValue].getTitle(),
					frameX + 40, frameY + 40);
			g2.drawString(gp.glossary.page[section][slotCol + topValue].getDesc(), frameX + 40,
					frameY + 80);
			//htmlBuilder.append("<body><p>"+gp.glossary.page[section][slotCol + topValue].getDesc()+"</p></body>");
			//htmlBuilder.append("</html>");
			String html = htmlBuilder.toString();
			g2.drawString(html,
					frameX + 160, frameY + 40);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public int getItemIndexOnSlot() {
		int itemIndex = this.slotCol2 + this.slotRow2 * 5;
		return itemIndex;
	}

	private void drawImageWindow(int x, int y, int width, int height) {
		BufferedImage image = this.gp.c.sprites[this.gp.c.dialogueSet][this.gp.c.dialogueIndex];
		if (image != null) {
			g2.drawImage(image, x, y, width, height, (ImageObserver) null);
		}

	}

	public void drawDialogueWindow(int x, int y, int width, int height) {
		Color c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		c = new Color(0, 0, 0, 210);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5.0F));
		g2.fillRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
	}

	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
	}

	public void drawTitleScreen() {
		g2.setBackground(Color.green);
		this.g2.setFont(g2.getFont().deriveFont(1, 80.0F));
		String text = "Wild and Wyrd";
		int x = this.getXforCenteredText(text);
		int y = gp.screenHeight / 3;
		this.g2.setColor(Color.white);
		this.g2.drawString(text, x, y);
		this.g2.setFont(g2.getFont().deriveFont(1, 40.0F));
		text = "New Game";
		x = this.getXforCenteredText(text);
		double var10000 = y;
		this.gp.getClass();
		y = (int) (var10000 + gp.tileSize * 2.5D);
		this.g2.drawString(text, x, y);
		Graphics2D var4;
		if (this.commandNum == 0) {
			var4 = this.g2;
			var4.drawString(">", x - gp.tileSize, y);
		}

		text = "Load Game";
		x = this.getXforCenteredText(text);
		y += gp.tileSize;
		this.g2.drawString(text, x, y);
		if (this.commandNum == 1) {
			var4 = this.g2;
			var4.drawString(">", x - gp.tileSize, y);
		}

		text = "Quit";
		x = this.getXforCenteredText(text);
		y += gp.tileSize;
		this.g2.drawString(text, x, y);
		if (this.commandNum == 2) {
			var4 = this.g2;
			var4.drawString(">", x - gp.tileSize, y);
		}

	}

	public void drawBackground(String image) {
		BufferedImage background = null;
		try {
			background = ImageIO.read(getClass().getResourceAsStream(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g2.drawImage(background, 0, 0, gp.screenWidth, gp.screenHeight, (ImageObserver) null);
	}

	public int getXforCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, this.g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}

	public void resetSlots() {
		this.slotRow = 0;
		this.slotCol = 0;
	}
}