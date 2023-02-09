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
		if (var10000 == 3) {
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
		int y = (512 - fm.getHeight()) / 2;
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
		int x = 64 * 3 / 2;
		this.gp.getClass();
		int y = 64 * 5;
		this.gp.getClass();
		this.gp.getClass();
		int width = 768 - 64 * 3;
		this.gp.getClass();
		int height = 64 * 3;
		String[][] dia = this.gp.c.dialogues;
		this.drawImageWindow(300, 0, 200, 400);
		this.drawDialogueWindow(x, y, width, height);
		this.g2.setFont(this.g2.getFont().deriveFont(0, 18.0F));
		this.g2.setColor(Color.white);
		this.gp.getClass();
		x += 64;
		this.gp.getClass();
		y += 64;
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
		int x = 64 * 3 / 2;
		this.gp.getClass();
		int y = 64 * 5;
		this.gp.getClass();
		this.gp.getClass();
		int width = 768 - 64 * 3;
		this.gp.getClass();
		int height = 64 * 3;
		this.drawDialogueWindow(x, y, width, height);
		this.g2.setFont(this.g2.getFont().deriveFont(0, 18.0F));
		this.g2.setColor(Color.white);
		this.gp.getClass();
		x += 64;
		this.gp.getClass();
		y += 64;
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
					UI var11;
					if (this.slotyn == 0) {
						var11 = this.gp.ui;
						binaryRes = true;
					} else {
						var11 = this.gp.ui;
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
			this.gp.getClass();
			if (var10000 == 7) {
				GamePanel var12 = this.gp;
				this.gp.getClass();
				var12.gameState = 1;
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
		this.gp.getClass();
		int frameWidth = 64 * 4;
		this.gp.getClass();
		int frameHeight = 64 * 7;
		this.drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int slotXstart = frameX + 0;
		int slotYstart = frameY + 15;
		this.gp.getClass();
		int cursorX = slotXstart + 64 * this.slotRow;
		double var10000 = (double) slotYstart;
		this.gp.getClass();
		int cursorY = (int) (var10000 + 64.0D * 0.75D * (double) this.slotCol);
		this.gp.getClass();
		int cursorWidth = 64 * 2;
		int cursorHeight = 30;
		this.g2.setFont(this.g2.getFont().deriveFont(0, 22.0F));
		this.g2.setColor(Color.white);
		this.g2.setStroke(new BasicStroke());
		Graphics2D var12 = this.g2;
		this.gp.getClass();
		var12.drawString("Save", 30, 64);
		var12 = this.g2;
		this.gp.getClass();
		var12.drawString("Stats", 30, (int) (64.0D * 1.75D));
		var12 = this.g2;
		this.gp.getClass();
		var12.drawString("Items", 30, (int) (64.0D * 2.5D));
		var12 = this.g2;
		this.gp.getClass();
		var12.drawString("Equipment", 30, (int) (64.0D * 3.25D));
		var12 = this.g2;
		this.gp.getClass();
		var12.drawString("Objecties", 30, 64 * 4);
		var12 = this.g2;
		this.gp.getClass();
		var12.drawString("Skill", 30, (int) (64.0D * 4.75D));
		var12 = this.g2;
		this.gp.getClass();
		var12.drawString("Glossary", 30, (int) (64.0D * 5.5D));
		var12 = this.g2;
		this.gp.getClass();
		var12.drawString("Quit", 30, (int) (64.0D * 6.25D));
		this.g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
	}

	public void drawInventoryScreen() {
		int frameX = 300;
		int frameY = 25;
		this.gp.getClass();
		int frameWidth = 64 * 7;
		this.gp.getClass();
		int frameHeight = 64 * 4;
		this.drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int slotXstart = frameX + 15;
		int slotYstart = frameY + 15;
		int slotX = slotXstart;
		int slotY = slotYstart;

		int i;
		for (i = 0; i < this.gp.player.inventory.size(); ++i) {
			this.g2.drawImage(((Entity) this.gp.player.inventory.get(i)).image, slotX, slotY, (ImageObserver) null);
			this.gp.getClass();
			slotX += 64;
			if (i == 6 || i == 13 || i == 20) {
				slotX = slotXstart;
				this.gp.getClass();
				slotY += 64;
			}
		}

		this.gp.getClass();
		i = slotXstart + 64 * this.slotCol2;
		this.gp.getClass();
		int cursorY = slotYstart + 64 * this.slotRow2;
		this.gp.getClass();
		int cursorWidth = (int) (64.0D / 1.65D);
		this.gp.getClass();
		int cursorHeight = (int) (64.0D / 1.65D);
		this.g2.setFont(this.g2.getFont().deriveFont(0, 22.0F));
		this.g2.setColor(Color.white);
		this.g2.setStroke(new BasicStroke());
		this.g2.drawRoundRect(i, cursorY, cursorWidth, cursorHeight, 10, 10);
		int dFrameY = frameY + frameHeight;
		this.gp.getClass();
		int dFrameHeight = 64 * 3;
		this.drawDialogueWindow(frameX, dFrameY, frameWidth, dFrameHeight);
		int textX = frameX + 20;
		this.gp.getClass();
		int textY = dFrameY + 64;
		int itemIndex = this.getItemIndexOnSlot();
		this.g2.setColor(Color.white);
		if (itemIndex < this.gp.player.inventory.size()) {
			String[] var23;
			int var22 = (var23 = ((Entity) this.gp.player.inventory.get(itemIndex)).description.split(":")).length;

			for (int var21 = 0; var21 < var22; ++var21) {
				String line = var23[var21];
				this.g2.drawString(line, textX, textY);
				textY += 40;
			}
		}

	}

	public void drawGlossaryScreen() {
		int frameX = 20;
		int frameY = 25;
		this.gp.getClass();
		int frameWidth = 64 * 4;
		this.gp.getClass();
		int frameHeight = 64 * 7;
		this.drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int slotXstart = frameX + 0;
		int slotYstart = frameY + 15;
		this.gp.getClass();
		int cursorX = slotXstart + 64 * this.slotRow;
		double var10000 = (double) slotYstart * 2.25D;
		this.gp.getClass();
		int cursorY = (int) (var10000 + (double) (64 * 1 * this.slotCol));
		this.gp.getClass();
		int cursorWidth = 64 * 2;
		int cursorHeight = 30;
		this.g2.setFont(this.g2.getFont().deriveFont(0, 22.0F));
		this.g2.setColor(Color.white);
		this.g2.setStroke(new BasicStroke());
		Graphics2D var17 = this.g2;
		String var10001 = this.gp.glossary.sections[this.section];
		this.gp.getClass();
		var17.drawString(var10001, 30, 64);
		this.gp.getClass();
		int pos = (int) (64.0D * 0.75D);
		this.bottomValue = this.gp.glossary.getSize(this.section);
		this.g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

		for (int i = 0; i < 6; ++i) {
			if (this.gp.glossary.page[this.section][i] != null) {
				this.gp.getClass();
				pos += 64;

				try {
					this.g2.drawString(this.gp.glossary.page[this.section][i + this.topValue].getTitle(), 30, pos);
				} catch (Exception var15) {
					System.out.println(var15);
				}
			}
		}

		int frameX = 300;
		frameY = 25;
		this.gp.getClass();
		frameWidth = 64 * 7;
		this.gp.getClass();
		frameHeight = 64 * 4;
		this.drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		this.g2.setFont(this.g2.getFont().deriveFont(0, 22.0F));
		this.g2.setColor(Color.white);

		try {
			this.g2.drawString(this.gp.glossary.page[this.section][this.slotCol + this.topValue].getTitle(),
					frameX + 40, frameY + 40);
			this.g2.drawString(this.gp.glossary.page[this.section][this.slotCol + this.topValue].getDesc(), frameX + 40,
					frameY + 80);
		} catch (Exception var14) {
			System.out.println(var14);
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
		int y = 512 / 3;
		this.g2.setColor(Color.white);
		this.g2.drawString(text, x, y);
		this.g2.setFont(this.g2.getFont().deriveFont(1, 40.0F));
		text = "New Game";
		x = this.getXforCenteredText(text);
		double var10000 = (double) y;
		this.gp.getClass();
		y = (int) (var10000 + 64.0D * 2.5D);
		this.g2.drawString(text, x, y);
		Graphics2D var4;
		if (this.commandNum == 0) {
			var4 = this.g2;
			this.gp.getClass();
			var4.drawString(">", x - 64, y);
		}

		text = "Load Game";
		x = this.getXforCenteredText(text);
		this.gp.getClass();
		y += 64;
		this.g2.drawString(text, x, y);
		if (this.commandNum == 1) {
			var4 = this.g2;
			this.gp.getClass();
			var4.drawString(">", x - 64, y);
		}

		text = "Quit";
		x = this.getXforCenteredText(text);
		this.gp.getClass();
		y += 64;
		this.g2.drawString(text, x, y);
		if (this.commandNum == 2) {
			var4 = this.g2;
			this.gp.getClass();
			var4.drawString(">", x - 64, y);
		}

	}

	public int getXforCenteredText(String text) {
		int length = (int) this.g2.getFontMetrics().getStringBounds(text, this.g2).getWidth();
		this.gp.getClass();
		int x = 768 / 2 - length / 2;
		return x;
	}

	public void resetSlots() {
		this.slotRow = 0;
		this.slotCol = 0;
	}
}