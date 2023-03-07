package wildwyrd.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.DecimalFormat;
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
		this.arial_40 = new Font("Monospaced", 0, 40);
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(this.arial_40);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		int var10000 = this.gp.gameState;
		this.gp.getClass();
		if (var10000 == 0) {
			this.drawTitleScreen();
		}

		var10000 = this.gp.gameState;
		this.gp.getClass();
		if (var10000 == 1) {
			Integer var2 = this.gp.selectedObj;
		}

		var10000 = this.gp.gameState;
		this.gp.getClass();
		if (var10000 == 7 && this.selectedObject != null) {
			this.drawExamineScreen();
		}

		var10000 = this.gp.gameState;
		this.gp.getClass();
		if (var10000 == 5) {
			this.drawDialogueScreen();
		}

		var10000 = this.gp.gameState;
		this.gp.getClass();
		if (var10000 == gp.menuState) {
			this.drawMenuBarScreen();
		}

		var10000 = this.gp.gameState;
		this.gp.getClass();
		if (var10000 == 10) {
			this.drawInventoryScreen();
		}

		var10000 = this.gp.gameState;
		this.gp.getClass();
		if (var10000 == 11) {
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
		FontMetrics fm = this.g2.getFontMetrics();
		this.getXforCenteredText(text);
		this.gp.getClass();
		int y = (gp.screenHeight - fm.getHeight()) / 2;
		this.g2.setFont(this.g2.getFont().deriveFont(0, 22.0F));
		this.g2.setColor(Color.white);
		this.message = text;
		if (this.gp.keyH.enterPressed) {
			++this.gp.csManager.scenePhase;
		}

		String[] var8;
		int var7 = (var8 = this.message.split(":")).length;

		for (int var6 = 0; var6 < var7; ++var6) {
			String line = var8[var6];
			int x = this.getXforCenteredText(line);
			this.g2.drawString(line, x, y);
			y += 40;
		}

		this.gp.keyH.enterPressed = false;
	}

	public void drawDialogueScreen() {
		this.gp.getClass();
		int x = gp.tileSize * 3 / 2;
		this.gp.getClass();
		int y = gp.tileSize * 5;
		this.gp.getClass();
		this.gp.getClass();
		int width = gp.screenWidth - gp.tileSize * 3;
		this.gp.getClass();
		int height = gp.tileSize * 3;
		String[][] dia = this.gp.c.dialogues;
		this.drawImageWindow(300, 0, 200, 400);
		this.drawDialogueWindow(x, y, width, height);
		this.g2.setFont(this.g2.getFont().deriveFont(0, 18.0F));
		this.g2.setColor(Color.white);
		this.gp.getClass();
		x += gp.tileSize;
		this.gp.getClass();
		y += gp.tileSize;
		int var10000;
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
					var10000 = this.gp.gameState;
					this.gp.getClass();
					if (var10000 != 5) {
						var10000 = this.gp.gameState;
						this.gp.getClass();
						if (var10000 != 4) {
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
					var10000 = this.gp.gameState;
					this.gp.getClass();
					if (var10000 != 5) {
						var10000 = this.gp.gameState;
						this.gp.getClass();
						if (var10000 != 4) {
							break label67;
						}
					}

					this.gp.c.dialogueIndex = this.gp.c.dialogues.length - 1;
					this.gp.keyH.skipPressed = false;
				}
			}
		} else {
			this.c.dialogueIndex = 0;
			var10000 = this.gp.gameState;
			this.gp.getClass();
			if (var10000 == 5) {
				GamePanel var12 = this.gp;
				this.gp.getClass();
				var12.gameState = 1;
			}

			var10000 = this.gp.gameState;
			this.gp.getClass();
			if (var10000 == 4) {
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
		this.gp.getClass();
		int x = gp.tileSize * 3 / 2;
		this.gp.getClass();
		int y = gp.tileSize * 5;
		this.gp.getClass();
		this.gp.getClass();
		int width = gp.screenWidth - gp.tileSize * 3;
		this.gp.getClass();
		int height = gp.tileSize * 3;
		this.drawDialogueWindow(x, y, width, height);
		this.g2.setFont(this.g2.getFont().deriveFont(0, 18.0F));
		this.g2.setColor(Color.white);
		this.gp.getClass();
		x += gp.tileSize;
		this.gp.getClass();
		y += gp.tileSize;
		int var10000;
		if (this.selectedObject.dialogues[this.selectedObject.dialogueSet][this.selectedObject.dialogueIndex] != null) {
			char[] characters = this.selectedObject.dialogues[this.selectedObject.dialogueSet][this.selectedObject.dialogueIndex]
					.getText().toCharArray();
			if (this.charIndex < characters.length) {
				String s = String.valueOf(characters[this.charIndex]);
				this.combinedText = this.combinedText + s;
				this.currentDialogue = this.combinedText;
				++this.charIndex;
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
						var10000 = this.gp.gameState;
						this.gp.getClass();
						if (var10000 == 7) {
							++this.selectedObject.dialogueIndex;
							this.gp.keyH.enterPressed = false;
						}
					}
				} else if (this.gp.keyH.skipPressed) {
					this.charIndex = 0;
					this.combinedText = "";
					var10000 = this.gp.gameState;
					this.gp.getClass();
					if (var10000 == 7) {
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
					var10000 = this.gp.gameState;
					this.gp.getClass();
					if (var10000 == 7) {
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
			var10000 = this.gp.gameState;
			if (var10000 == 7) {
				GamePanel gp = this.gp;
				gp.gameState = 1;
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
		double var10000 = (double) slotYstart;
		int cursorY = (int) (var10000 + gp.tileSize * 0.75D * (double) this.slotCol);
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
			this.g2.drawImage(((Entity) gp.player.inventory.get(i)).image, slotX, slotY, (ImageObserver) null);
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
			int var22 = (var23 = ((Entity) this.gp.player.inventory.get(itemIndex)).description.split(":")).length;

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
		double var10000 = (double) slotYstart * 2.25D;
		this.gp.getClass();
		int cursorY = (int) (var10000 + (double) (gp.tileSize * 1 * this.slotCol));
		int cursorWidth = gp.tileSize * 2;
		int cursorHeight = 30;
		this.g2.setFont(this.g2.getFont().deriveFont(0, 22.0F));
		this.g2.setColor(Color.white);
		this.g2.setStroke(new BasicStroke());
		Graphics2D g2 = this.g2;
		String var10001 = gp.glossary.sections[section];
		g2.drawString(var10001, 30, gp.tileSize);
		int pos = (int) (gp.tileSize * 0.75D);
		bottomValue = gp.glossary.getSize(section);
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

		for (int i = 0; i < 6; ++i) {
			if (this.gp.glossary.page[this.section][i] != null) {
				pos += gp.tileSize;

				try {
					this.g2.drawString(this.gp.glossary.page[section][i + topValue].getTitle(), 30, pos);
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
			htmlBuilder.append("<html>");
			htmlBuilder.append("<head><title>"+gp.glossary.page[section][slotCol + topValue].getTitle()+"</title></head></br>");
			g2.drawString(gp.glossary.page[section][slotCol + topValue].getTitle(),
					frameX + 40, frameY + 40);
			g2.drawString(gp.glossary.page[section][slotCol + topValue].getDesc(), frameX + 40,
					frameY + 80);
			htmlBuilder.append("<body><p>"+gp.glossary.page[section][slotCol + topValue].getDesc()+"</p></body>");
			htmlBuilder.append("</html>");
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
			this.g2.drawImage(image, x, y, width, height, (ImageObserver) null);
		}

	}

	public void drawDialogueWindow(int x, int y, int width, int height) {
		Color c = new Color(255, 255, 255);
		this.g2.setColor(c);
		this.g2.fillRoundRect(x, y, width, height, 35, 35);
		c = new Color(0, 0, 0, 210);
		this.g2.setColor(c);
		this.g2.setStroke(new BasicStroke(5.0F));
		this.g2.fillRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
	}

	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(255, 255, 255);
		this.g2.setColor(c);
		this.g2.fillRoundRect(x, y, width, height, 35, 35);
	}

	public void drawTitleScreen() {
		this.g2.setBackground(Color.green);
		this.g2.setFont(this.g2.getFont().deriveFont(1, 80.0F));
		String text = "Wild and Wyrd";
		int x = this.getXforCenteredText(text);
		this.gp.getClass();
		int y = gp.screenHeight / 3;
		this.g2.setColor(Color.white);
		this.g2.drawString(text, x, y);
		this.g2.setFont(this.g2.getFont().deriveFont(1, 40.0F));
		text = "New Game";
		x = this.getXforCenteredText(text);
		double var10000 = (double) y;
		this.gp.getClass();
		y = (int) (var10000 + gp.tileSize * 2.5D);
		this.g2.drawString(text, x, y);
		Graphics2D var4;
		if (this.commandNum == 0) {
			var4 = this.g2;
			this.gp.getClass();
			var4.drawString(">", x - gp.tileSize, y);
		}

		text = "Load Game";
		x = this.getXforCenteredText(text);
		this.gp.getClass();
		y += gp.tileSize;
		this.g2.drawString(text, x, y);
		if (this.commandNum == 1) {
			var4 = this.g2;
			this.gp.getClass();
			var4.drawString(">", x - gp.tileSize, y);
		}

		text = "Quit";
		x = this.getXforCenteredText(text);
		this.gp.getClass();
		y += gp.tileSize;
		this.g2.drawString(text, x, y);
		if (this.commandNum == 2) {
			var4 = this.g2;
			this.gp.getClass();
			var4.drawString(">", x - gp.tileSize, y);
		}

	}

	public int getXforCenteredText(String text) {
		int length = (int) this.g2.getFontMetrics().getStringBounds(text, this.g2).getWidth();
		this.gp.getClass();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}

	public void resetSlots() {
		this.slotRow = 0;
		this.slotCol = 0;
	}
}