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
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import wildwyrd.game.cutscenes.Cutscene;
import wildwyrd.game.library.Book;
import wildwyrd.game.object.Dialoge;
import wildwyrd.game.playable.Playable;
import wildwyrd.game.tile.UtilityTool;

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
	public int choiceSlot = 0;
	public int firstValue = 0;
	public int bottomValue = 0;
	public int topValue = 0;
	public boolean openEquipment = false;
	public boolean openInventory = false;
	public Entity selectedPlayable;
	public boolean openBook = false;
	public Book[] selectedBookshelf;
	public Book selectedBook;
	int charIndex = 0;
	String combinedText = "";
	public JPanel bgPanel[] = new JPanel[10];

	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Monospaced", 0, 40);
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(arial_40);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		if (gp.gameState == GameState.titleState) {
			drawTitleScreen();
		}

		if (gp.gameState == GameState.playState) {
			Integer var2 = gp.selectedObj;
		}

		if (gp.gameState == GameState.examineState && selectedObject != null) {
			drawExamineScreen();
		}

		if (gp.gameState == GameState.dialogueState) {
			drawDialogueScreen();
		}

		if (gp.gameState == GameState.menuState) {
			drawMenuBarScreen();
		}

		if (gp.gameState == GameState.statusState) {
			drawStatusScreen();
		}

		if (gp.gameState == GameState.inventoryState) {
			drawInventoryScreen();
		}

		if (gp.gameState == GameState.equipState) {
			drawEquipScreen();
		}

		if (gp.gameState == GameState.glossaryState) {
			drawGlossaryScreen();
		}
		
		if (gp.gameState == GameState.readingState) {
			drawBookshelfScreen();
		}

	}

	public void drawHeadingScreen(String text) {
		int x = 5;
		int y = 20;
		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);
		message = text;
		g2.drawString(message, x, y);
	}

	public void drawItemLabel() {
		FontMetrics fm = g2.getFontMetrics();
		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);
		if (gp.selectedObj != null) {
			selectedObject = gp.obj[gp.currentRoom][gp.selectedObj];
			drawDialogueWindow(
					selectedObject.x + gp.obj[gp.currentRoom][gp.selectedObj].width - 10,
					selectedObject.y - 35, fm.stringWidth(selectedObject.name), 50);
			g2.setFont(g2.getFont().deriveFont(0, 22.0F));
			g2.setColor(Color.white);
			g2.drawString(selectedObject.name,
					selectedObject.x + gp.obj[gp.currentRoom][gp.selectedObj].width,
					selectedObject.y);
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
			gp.csManager.scenePhase++;
		}
		for (String line : breakLines(message, 40)) {
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
		Dialoge[][] dia = gp.c.dialogues;
		drawImageWindow(300, 0, 200, 400);
		drawDialogueWindow(x, y, width, height);
		g2.setFont(g2.getFont().deriveFont(0, 18.0F));
		g2.setColor(Color.white);
		x += gp.tileSize;
		y += gp.tileSize;
		if (dia[gp.c.dialogueSet][gp.c.dialogueIndex] != null) {
			char[] characters = gp.c.dialogues[gp.c.dialogueSet][gp.c.dialogueIndex].getText().toCharArray();
			if (charIndex < characters.length) {
				String s = String.valueOf(characters[charIndex]);
				combinedText = combinedText + s;
				currentDialogue = combinedText;
				charIndex++;
				if (gp.keyH.enterPressed) {
					currentDialogue = gp.c.dialogues[gp.c.dialogueSet][gp.c.dialogueIndex].getText();
				}
			}

			if (gp.keyH.enterPressed == true) {
				this.charIndex = 0;
				this.combinedText = "";
				if (gp.gameState == GameState.dialogueState || 
					gp.gameState == GameState.cutsceneState) {
						gp.c.dialogueIndex++;
						gp.keyH.enterPressed = false;
				}
			} else if (gp.keyH.skipPressed) {
				charIndex = 0;
				combinedText = "";
				if (gp.gameState == GameState.dialogueState ||
					gp.gameState == GameState.cutsceneState) {
						gp.c.dialogueIndex = gp.c.dialogues.length - 1;
						gp.keyH.skipPressed = false;
					}
				}
		} else {
			c.dialogueIndex = 0;
			if (gp.gameState == GameState.dialogueState) {
				gp.gameState = GameState.playState;
			}
			if (gp.gameState == GameState.cutsceneState) {
				gp.csManager.scenePhase++;
			}
		}
		if(gp.c.dialogues[gp.c.dialogueSet][gp.c.dialogueIndex] != null) {
			if (gp.c.dialogues[gp.c.dialogueSet][gp.c.dialogueIndex].getSpeaker() != null) {
				g2.setFont(g2.getFont().deriveFont(1, 24.0F));
				g2.drawString(gp.c.dialogues[gp.c.dialogueSet][gp.c.dialogueIndex].getSpeaker(), x, y);
				y += 40;
			}
			for (String line : breakLines(currentDialogue,42)) {
				g2.setFont(g2.getFont().deriveFont(0, 18.0F));
				g2.drawString(line, x, y);
				y += 30;
			}
		}

	}

	public void drawExamineScreen() {
		int x = gp.tileSize * 3 / 2;
		int y = gp.tileSize * 5;
		int width = gp.screenWidth - gp.tileSize * 3;
		int height = gp.tileSize * 3;
		drawDialogueWindow(x, y, width, height);
		g2.setFont(g2.getFont().deriveFont(0, 18.0F));
		g2.setColor(Color.white);
		x += gp.tileSize;
		y += gp.tileSize;
		if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex] != null) {
			char[] characters = selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex]
					.getText().toCharArray();
			if (charIndex < characters.length) {
				String s = String.valueOf(characters[charIndex]);
				combinedText = combinedText + s;
				currentDialogue = combinedText;
				charIndex++;
				if (gp.keyH.enterPressed) {
					currentDialogue = selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex]
							.getText();
				}
			}

			if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex]
					.getType() == 1) {
				if (this.gp.keyH.enterPressed) {
					if (currentDialogue
							.length() == selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex]
									.getText().length()) {
						charIndex = 0;
						combinedText = "";
						if (gp.gameState == GameState.examineState) {
							selectedObject.dialogueIndex++;
							gp.keyH.enterPressed = false;
						}
					}
				} else if (gp.keyH.skipPressed) {
					charIndex = 0;
					combinedText = "";
					if (gp.gameState == GameState.examineState) {
						selectedObject.dialogueIndex = selectedObject.dialogues.length - 1;
						gp.keyH.skipPressed = false;
					}
				} 
				selectedObject.checkConditions();
			} else if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex]
					.getType() == 2) {
				if (gp.keyH.enterPressed) {
					charIndex++;
					if (choiceSlot == 0) {
						binaryRes = true;
					} else {
						binaryRes = false;
					}

					charIndex = 0;
					combinedText = "";
					if (gp.gameState == GameState.examineState) {
						selectedObject.choiceResponce();
						selectedObject.dialogueIndex++;
						gp.keyH.enterPressed = false;
					}
				}

				if (choiceSlot == 0) {
					g2.drawString(">", x, y + 40);
				} else {
					g2.drawString(">", x, y + 80);
				}

				g2.drawString("Yes", x + 20, y + 40);
				g2.drawString("No", x + 20, y + 80);
			} else if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex]
					.getType() == 3) {
				if (gp.keyH.enterPressed) {
					charIndex = 0;
					combinedText = "";
					if (gp.gameState == GameState.examineState) {
						//selectedObject.dialogueIndex++;
						selectedObject.choiceResponce();
						gp.keyH.enterPressed = false;
					}
				}
				//int startValue = 0;
				System.out.println(choiceSlot + " " + firstValue);
				for(int i = firstValue; i < selectedObject.options.length; i++) {
					if(y + 30 > 500) {
					//	startValue++;
						break;
					}
					if (choiceSlot == i) {
						g2.drawString(">", x, y);
					}
					int j = 1;
					if(selectedObject.options[i] != null) {
						for (String line : selectedObject.options[i].split(":")) {
							if (j > 1) {
								y += 30;
							}
							g2.setFont(g2.getFont().deriveFont(0, 18.0F));
							g2.drawString(line, x + 20, y);
							j++;
						}
					}
					y += 40;
				}
			}
		} else {
			selectedObject.dialogueIndex = 0;
			if (gp.gameState == GameState.examineState) {
				gp.gameState = GameState.playState;
			}
		}
		if(selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex] != null) {
			if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex].getSpeaker() != null) {
				g2.setFont(g2.getFont().deriveFont(1, 24.0F));
				g2.drawString(selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex].getSpeaker(), x, y);
				y += 40;
			}
			for (String line : breakLines(currentDialogue,40)) {
				g2.setFont(g2.getFont().deriveFont(0, 18.0F));
				g2.drawString(line, x, y);
				y += 30;
			}
		}
	}public void drawTalkingScreen() {
		int x = gp.tileSize * 3 / 2;
		int y = gp.tileSize * 5;
		int width = gp.screenWidth - gp.tileSize * 3;
		int height = gp.tileSize * 3;
		drawDialogueWindow(x, y, width, height);
		g2.setFont(g2.getFont().deriveFont(0, 18.0F));
		g2.setColor(Color.white);
		x += gp.tileSize;
		y += gp.tileSize;
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

			if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex]
					.getType() == 1) {
				if (gp.keyH.enterPressed) {
					if (currentDialogue
							.length() == selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex]
									.getText().length()) {
						charIndex = 0;
						combinedText = "";
						if (gp.gameState == GameState.examineState) {
							selectedObject.dialogueIndex++;
							gp.keyH.enterPressed = false;
						}
					}
				} else if (gp.keyH.skipPressed) {
					charIndex = 0;
					combinedText = "";
					if (gp.gameState == GameState.examineState) {
						selectedObject.dialogueIndex = selectedObject.dialogues.length - 1;
						gp.keyH.skipPressed = false;
					}
				}
				if (selectedObject.dialogueIndex >= selectedObject.dialogues[selectedObject.dialogueSet].length) {
					for (boolean checkConditions: selectedObject.contConditions) {
						if(checkConditions = false) {
							selectedObject.speak();
						}
					}
				}
				
			} else if (selectedObject.dialogues[selectedObject.dialogueSet][this.selectedObject.dialogueIndex]
					.getType() == 2) {
				if (gp.keyH.enterPressed) {
					charIndex++;
					if (choiceSlot == 0) {
						binaryRes = true;
					} else {
						binaryRes = false;
					}

					charIndex = 0;
					combinedText = "";
					if (gp.gameState == GameState.examineState) {
						selectedObject.choiceResponce();
						selectedObject.dialogueIndex++;
						gp.keyH.enterPressed = false;
					}
				}

				if (choiceSlot == 0) {
					g2.drawString(">", x, y + 40);
				} else {
					g2.drawString(">", x, y + 80);
				}

				g2.drawString("Yes", x + 20, y + 40);
				g2.drawString("No", x + 20, y + 80);
			} else if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex]
					.getType() == 3) {
				if (gp.keyH.enterPressed) {
					charIndex = 0;
					combinedText = "";
					if (gp.gameState == GameState.examineState) {
						//selectedObject.dialogueIndex++;
						selectedObject.choiceResponce();
						gp.keyH.enterPressed = false;
					}
				}
				for(int i = firstValue; i < selectedObject.options.length; i++) {
					int j = 1;
					for (String line : breakLines(selectedObject.options[i],40)) {
						if (j > 1) {
							y += 30;
						}
						g2.setFont(g2.getFont().deriveFont(0, 18.0F));
						g2.drawString(line, x + 20, y);
						j++;
					}
					y += 40;
				}
			}
		} else {
			selectedObject.dialogueIndex = 0;
			if (gp.gameState == GameState.examineState) {
				gp.gameState = GameState.playState;
			}
		}
		if(selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex] != null) {
			if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex].getSpeaker() != null) {
				g2.setFont(g2.getFont().deriveFont(1, 24.0F));
				g2.drawString(selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex].getSpeaker(), x, y);
				y += 40;
			}
			for (String line : breakLines(currentDialogue, 60)) {
				g2.setFont(g2.getFont().deriveFont(0, 18.0F));
				g2.drawString(line, x, y);
				y += 30;
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
		int cursorX = slotXstart + gp.tileSize * slotRow;
		double var10000 = slotYstart;
		int cursorY = (int) (var10000 + gp.tileSize * 0.75D * this.slotCol);
		int cursorWidth = gp.tileSize * 2;
		int cursorHeight = 30;
		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke());
		g2.drawString("Save", 30, gp.tileSize);
		g2.drawString("Stats", 30, (int) (gp.tileSize * 1.75D));
		g2.drawString("Items", 30, (int) (gp.tileSize * 2.5D));
		g2.drawString("Equipment", 30, (int) (gp.tileSize * 3.25D));
		g2.drawString("Objecties", 30, gp.tileSize * 4);
		g2.drawString("Skill", 30, (int) (gp.tileSize * 4.75D));
		g2.drawString("Glossary", 30, (int) (gp.tileSize * 5.5D));
		g2.drawString("Quit", 30, (int) (gp.tileSize * 6.25D));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
	}
	
	public void drawStatusScreen() {
		int frameX = 20;
		int frameY = 25;
		int frameWidth = gp.tileSize * 4;
		int frameHeight = gp.tileSize * 7;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int slotXstart = frameX + 0;
		int slotYstart = frameY + 15;
		int cursorX = slotXstart + gp.tileSize * slotRow;
		int cursorY = (int) (slotYstart + gp.tileSize * 0.75D * slotCol);
		int cursorWidth = gp.tileSize * 2;
		int cursorHeight = 30;
		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke());
		double characterSlot = 1.75;
		for(Playable p : gp.playable) {
			if(p != null) {
				g2.drawString(p.name, 30, (int) (gp.tileSize * characterSlot));
				characterSlot += 0.75;
			}
		}
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
		frameX = (int) (4.7 * gp.tileSize);
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		g2.setColor(Color.white);
		for(int i = 0; i < gp.playable.length; i++) {
			if(gp.playable[i] != null) {
				double lineNum  = 1;
				for (String line : gp.playable[i].toString().split("£")) {
					g2.drawString(line, gp.tileSize * 5, (int) (gp.tileSize * lineNum));
					lineNum += 0.5;
				}
				
			}
		}
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
			g2.drawImage(gp.player.inventory.get(i).image, slotX, slotY, null);
			slotX += gp.tileSize;
			if (i == 6 || i == 13 || i == 20) {
				slotX = slotXstart;
				slotY += gp.tileSize;
			}
		}

		i = slotXstart + gp.tileSize * slotCol2;
		int cursorY = slotYstart + gp.tileSize * slotRow2;
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
		int itemIndex = getItemIndexOnSlot();
		g2.setColor(Color.white);
		if (itemIndex < gp.player.inventory.size()) {

			for (String line : breakLines(gp.player.inventory.get(itemIndex).description,40)) {
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
	
	public void drawEquipScreen() {
		int frameX = 20;
		int frameY = 25;
		int frameWidth = gp.tileSize * 4;
		int frameHeight = gp.tileSize * 7;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int slotXstart = frameX + 0;
		int slotYstart = (int)((frameY + 15) + (gp.tileSize * 0.75D));
		int cursorX = slotXstart + gp.tileSize * slotRow;
		int cursorY = (int) (slotYstart + gp.tileSize * 0.75D * slotCol);
		int cursorWidth = gp.tileSize * 3;
		int cursorHeight = 30;

		frameX = 300;
		frameY = 25;
		frameWidth = gp.tileSize * 7;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);

		if(openEquipment) {
			cursorX = frameX;
		}

		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke());
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke());
		for(Playable p : gp.playable) {
			if(p != null) {
				g2.drawString(p.name, 30, (int) (gp.tileSize * 1.75D));
				g2.drawString("Head: " + p.getHead().getName(), frameX + 15, (int) (gp.tileSize * 1.75D));
				g2.drawString("Body: " + p.getBody().getName(), frameX + 15, (int) (gp.tileSize * 2.5D));
				g2.drawString("Legs: " + p.getLegs().getName(), frameX + 15, (int) (gp.tileSize * 3.25D));
				System.out.println(p.getWeapon_prime().getName());
				g2.drawString("Primary WP: " + p.getWeapon_prime().getName(), frameX + 15, (int) (gp.tileSize * 4));
				g2.drawString("Secondary WP: " +p.getWeapon_second().getName(), frameX + 15, (int) (gp.tileSize * 4.75D));
				//g2.drawImage(p.combatSpt, frameX + (frameWidth / 3), frameY + (frameHeight / 4), frameWidth / 2 , frameHeight/2, null);
			}
		}
		
	}

	public void drawGlossaryScreen() {
		int frameX = 20;
		int frameY = 25;
		int frameWidth = gp.tileSize * 4;
		int frameHeight = gp.tileSize * 7;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int slotXstart = frameX + 0;
		int slotYstart = frameY + 15;
		int cursorX = slotXstart + gp.tileSize * slotRow;
		int cursorY = (int) (slotYstart * 2.25D + gp.tileSize * slotCol);
		int cursorWidth = gp.tileSize * 2;
		int cursorHeight = 30;
		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke());
		g2.drawString(gp.glossary.sections[section], 30, gp.tileSize);
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
			g2.setFont(g2.getFont().deriveFont(0, 16.0F));
			int lineNum = 80;
			for (String line : breakLines((gp.glossary.page[section][slotCol + topValue].getDesc()), 35)){
				g2.drawString(line, frameX + 40,
					frameY + lineNum);
				lineNum += 20;
			}
			
			//htmlBuilder.append("<body><p>"+gp.glossary.page[section][slotCol + topValue].getDesc()+"</p></body>");
			//htmlBuilder.append("</html>");
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public void drawBookshelfScreen() {
		int frameX = 20;
		int frameY = 25;
		int frameWidth = gp.tileSize * 8;
		int frameHeight = gp.tileSize * 7;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int slotXstart = frameX + 10;
		int slotYstart = frameY + gp.tileSize;
		int cursorX = slotXstart + gp.tileSize * slotRow;
		int cursorY = (int) (slotYstart + gp.tileSize * 0.75D * slotCol);
		int cursorWidth = (int) (gp.tileSize * 7.5);
		int cursorHeight = 30;
		if(openBook) {
			drawBookScreen(selectedBookshelf[slotCol]);
		} else {
			g2.setFont(g2.getFont().deriveFont(0, 22.0F));
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke());
			double bookSlot = 1.75;
			for(Book s : selectedBookshelf) {
				if(s != null) {
					g2.drawString(s.getTitle(), 35, (int) (gp.tileSize * bookSlot));
					bookSlot += 0.75;
				}
			}
			g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
		}
		
	}
	
	public void drawBookScreen(Book selectedBook) {
		this.selectedBook = selectedBook;
		int frameX = 20;
		int frameY = 25;
		int frameWidth = gp.tileSize * 8;
		int frameHeight = gp.tileSize * 7;
		int y = gp.tileSize * 2;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke());
		g2.drawString(selectedBook.getTitle(), 35, (int) (gp.tileSize));
		for (String line : breakLines(selectedBook.getContent()[slotRow], 60)) {
			for (String list : line.split("£")) {
				g2.setFont(g2.getFont().deriveFont(0, 14.0F));
				g2.drawString(list, 35, y);
				y += 30;
			}
			
		}
	}

	public int getItemIndexOnSlot() {
		int itemIndex = slotCol2 + slotRow2 * 5;
		return itemIndex;
	}
	
	//Automatic line breaks
	public String[] breakLines(String text, int size) {
		ArrayList<String> lines = new ArrayList<String>();
		while(text.length() > 0){
			int pos = text.lastIndexOf(" ", size);
			if(text.contains("£")) {
				pos = text.lastIndexOf("£", size);
				System.out.println(pos);
				if (pos == -1) {
					pos = text.lastIndexOf(" ", size);
				}
			}
			if (size > text.length()) {
				pos = text.length() - 1;
			}
			String found = text.substring(0, pos + 1);
			System.out.println(found);
			text = text.substring(pos + 1);
			lines.add(found);
		}
		String[] lineBreaks = lines.toArray(new String[lines.size()]);
		return lineBreaks;
	}
	
	private void drawMenuWindow() {
		
	}

	private void drawImageWindow(int x, int y, int width, int height) {
		BufferedImage image = gp.c.sprites[gp.c.dialogueSet][gp.c.dialogueIndex];
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
		g2.setFont(g2.getFont().deriveFont(1, 80.0F));
		String text = "Wild and Wyrd";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight / 3;
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		g2.setFont(g2.getFont().deriveFont(1, 40.0F));
		text = "New Game";
		x = getXforCenteredText(text);
		y = (int) (y + gp.tileSize * 2.5D);
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "Load Game";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "Quit";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}

	}

	public void drawBackground(String image) {
		/*UtilityTool uTool = new UtilityTool();
		BufferedImage background = null;
		try {
			background = ImageIO.read(getClass().getResourceAsStream(image));
			background = uTool.scaleImage(background, gp.screenWidth, gp.screenHeight);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g2.drawImage(background, 0, 0, gp.screenWidth, gp.screenHeight, (ImageObserver) null);*/
		bgPanel[1] = new JPanel();
		bgPanel[1].setBounds(0,0,gp.screenWidth,gp.screenHeight);
		bgPanel[1].setBackground(Color.black);
		bgPanel[1].setLayout(null);
		//gp.window.add
		ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader().getResource(image));
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