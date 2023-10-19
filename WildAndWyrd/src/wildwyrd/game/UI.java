package wildwyrd.game;

import java.awt.AlphaComposite;
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
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import wildwyrd.game.combat.CombatStatus;
import wildwyrd.game.combat.Enemy;
import wildwyrd.game.cutscenes.Cutscene;
import wildwyrd.game.library.Book;
import wildwyrd.game.playable.Combatant;
import wildwyrd.game.skill.Skill;

public class UI {
	GamePanel gp;
	Cutscene c;
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
	public EntityType itemFilter = null;
	public boolean openBook = false;
	public Book[] selectedBookshelf;
	public Book selectedBook;
	public ArrayList<Entity> droppedItems = new ArrayList<Entity>();;
	int charIndex = 0;
	String combinedText = "";
	public JPanel bgPanel[] = new JPanel[10];

	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Monospaced", 0, 40);
		/*Story s = gp.s;
		if(gp.s == null) {
			s = new Story();
		}	
		c = new Cutscene(gp, new Story());*/
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

		if (gp.gameState == GameState.objectiveState) {
			drawObjectiveScreen();
		}

		if (gp.gameState == GameState.skillState) {
			drawSkillScreen();
		}

		if (gp.gameState == GameState.glossaryState) {
			drawGlossaryScreen();
		}
		
		if (gp.gameState == GameState.readingState) {
			drawBookshelfScreen();
		}
		
		if (gp.gameState == GameState.combatState) {
			drawCombatScreen();
		}
		
		if (gp.gameState == GameState.targetState) {
			targetCombatant();
		}
		
		if (gp.gameState == GameState.rewardState) {
			drawRewardsScreen();
		}
		
		if (gp.gameState == GameState.gameOverState) {
			drawGameOverScreen();
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
		drawImageWindow(300, 0, 200, 400);
		drawDialogueWindow(x, y, width, height);
		g2.setFont(g2.getFont().deriveFont(0, 18.0F));
		g2.setColor(Color.white);
		x += gp.tileSize;
		y += gp.tileSize;
		//System.out.println(selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex].text + " " + selectedObject.dialogueSet+" "+selectedObject.dialogueIndex);
		if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex] != null) {
			//System.out.println(selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex].text);
			char[] characters = selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex].getText().toCharArray();
			if (charIndex < characters.length) {
				String s = String.valueOf(characters[charIndex]);
				combinedText = combinedText + s;
				currentDialogue = combinedText;
				charIndex++;
				//Complete Text
				if (gp.keyH.enterPressed) {
					combinedText = selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex].getText().substring(0, 
							selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex].getText().length() - 1);
					charIndex = characters.length - 1;
					gp.keyH.enterPressed = false;
				}
			}
			//Next Dialogue
			if (gp.keyH.enterPressed == true) {
				charIndex = 0;
				combinedText = "";
				if (gp.gameState == GameState.dialogueState || 
					gp.gameState == GameState.cutsceneState) {
					selectedObject.dialogueIndex++;
					gp.keyH.enterPressed = false;
				}
			//Skip Dialogue
			} else if (gp.keyH.skipPressed) {
				charIndex = 0;
				combinedText = "";
				if (gp.gameState == GameState.dialogueState ||
					gp.gameState == GameState.cutsceneState) {
					selectedObject.dialogueIndex = selectedObject.dialogues.length - 1;
						gp.keyH.skipPressed = false;
					}
				}
		} else {
			selectedObject.dialogueIndex = 0;
			if (gp.gameState == GameState.dialogueState) {
				//System.out.println("Enemies Active: " + gp.combat.enemiesActive());
				if(!gp.combat.enemiesActive()) {
					drawCombatants(g2);
					gp.combat.inCombat = false;
					gp.combat.win = true;
				} else if (!gp.combat.playableActive()) {
					drawCombatants(g2);
					gp.combat.inCombat = false;
					gp.combat.win = false;
				}
				if(gp.combat.inCombat) {
					gp.gameState = GameState.combatState;
					gp.combat.incrementTurn();
				} else {
					//System.out.println(selectedObject);
					gp.combat.endCombat();
				}
			}
			if (gp.gameState == GameState.cutsceneState) {
				gp.csManager.scenePhase++;
			}
		}
		if(selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex] != null) {
			if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex].getSpeaker() != null) {
				g2.setFont(g2.getFont().deriveFont(1, 24.0F));
				g2.drawString(selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex].getSpeaker(), x, y);
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
					combinedText = selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex].getText().substring(0, 
							selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex].getText().length() - 1);
					charIndex = characters.length - 1;
					gp.keyH.enterPressed = false;
				}
			}

			if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex]
					.getType() == 1) {
				//System.out.println(selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex].text + " " + selectedObject.dialogueSet + " " + selectedObject.dialogueIndex);
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
						selectedObject.choiceResponce();
						gp.keyH.enterPressed = false;
					}
				}
				for(int i = firstValue; i < selectedObject.options.length; i++) {
					if(y + 30 > 500) {
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
				//Display Up arrow
				if(firstValue > 0) {
					drawUpIcon((int)(width/1.65), 340, 20, 20);
				}
				//Display Down arrow
				if(firstValue < selectedObject.options.length - 3) {
					drawDownIcon((int)(width/1.65), 470, 20, 20);
				}
			} else if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex]
					.getType() == 4) {
				gp.combat.startCombat();
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
		int cursorY = (int) (slotYstart + gp.tileSize * 0.75D * slotCol);
		int cursorWidth = gp.tileSize * 2;
		int cursorHeight = 30;
		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke());
		//System.out.println(gp.tileSize);
		g2.drawString("Save", 30, gp.tileSize);
		g2.drawString("Stats", 30, (int) (gp.tileSize * 1.75D));
		g2.drawString("Items", 30, (int) (gp.tileSize * 2.5D));
		g2.drawString("Objecties", 30, (int) (gp.tileSize * 3.25D));
		g2.drawString("Glossary", 30, (int) (gp.tileSize * 4));
		g2.drawString("Quit", 30, (int) (gp.tileSize * 4.75D));
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
		int cursorWidth = gp.tileSize * 3;
		int cursorHeight = 30;
		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke());
		double characterSlot = 1.75;
		g2.drawString("Stats", 30, (int) (gp.tileSize));
		for(Combatant p : gp.playable) {
			if(p != null) {
				g2.drawString(p.name, 30, (int) (gp.tileSize * characterSlot));
				characterSlot += 0.75;
			}
		}
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
		frameX = (int) (4.7 * gp.tileSize);
		frameWidth = gp.tileSize * 7;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		g2.setColor(Color.white);
		for(int i = 0; i < gp.playable.size(); i++) {
			if(gp.playable.get(0) != null) {
				double lineNum  = 1;
				for (String line : gp.playable.get(0).toString().split("£")) {
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
			try {
			g2.drawImage(gp.player.inventory.get(i).image, slotX, slotY, null);
			if(gp.player.inventory.get(i).amount > 1) {
				g2.setFont(g2.getFont().deriveFont(32f));
				int amountX;
				int amountY;
				String s = "" + gp.player.inventory.get(i).amount;
				amountX = getXforAlignToRightText(s , slotX + 44);
				amountY = slotY + gp.tileSize;
				
				//Shadow
				g2.setColor(new Color(60,60,60));
				g2.drawString(s, amountX, amountY);
			}
			slotX += gp.tileSize;
			if (i == 6 || i == 13 || i == 20) {
				slotX = slotXstart;
				slotY += gp.tileSize;
			}
			} catch (NullPointerException e) {
				System.out.println(e);
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
			if(gp.player.inventory.get(itemIndex).type == EntityType.Primary) {
				g2.drawString("" + gp.playable.get(0).getAttack() + " - " + (gp.playable.get(0).getBaseAttack() + gp.player.inventory.get(itemIndex).attackValue), textX, textY);
			}
		}
		frameX = 20;
		frameY = 25;
		frameWidth = gp.tileSize * 4;
		frameHeight = gp.tileSize * 7;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);
		//System.out.println(gp.player.getShillings() + " " + gp.tileSize);
		g2.drawString("Shillings: " + gp.player.getShillings(), 30, gp.tileSize);

	}
	
	public void drawEquipScreen() {
		int frameX = 20;
		int frameY = 25;
		int frameWidth = gp.tileSize * 4;
		int frameHeight = gp.tileSize * 7;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int slotXstart = frameX + 0;
		int slotYstart = frameY + 15;
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
		double characterSlot = 1.75;
		g2.drawString("Equipment", 30, (int) (gp.tileSize));
		for(Combatant p : gp.playable) {
			if(p != null) {
				g2.drawString(p.name, 30, (int) (gp.tileSize * characterSlot));
				characterSlot += 0.75;
			}
		}
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke());
		for(Combatant p : gp.playable) {
			if(p != null) {
				//g2.drawString(p.name, 30, (int) (gp.tileSize * 1.75D));
				g2.drawString("Head: " + p.getHead().getName(), frameX + 15, (int) (gp.tileSize * 1.75D));
				g2.drawString("Body: " + p.getBody().getName(), frameX + 15, (int) (gp.tileSize * 2.5D));
				g2.drawString("Legs: " + p.getLegs().getName(), frameX + 15, (int) (gp.tileSize * 3.25D));
				g2.drawString("Primary WP: " + p.getWeapon_prime().getName(), frameX + 15, (int) (gp.tileSize * 4));
				g2.drawString("Secondary WP: " +p.getWeapon_second().getName(), frameX + 15, (int) (gp.tileSize * 4.75D));
				//g2.drawImage(p.combatSpt, frameX + (frameWidth / 3), frameY + (frameHeight / 4), frameWidth / 2 , frameHeight/2, null);
			}
		}
	}
	
	public void drawSkillScreen() {
		int frameX = 20;
		int frameY = 25;
		int frameWidth = gp.tileSize * 4;
		int frameHeight = gp.tileSize * 7;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int slotXstart = frameX + 0;
		int slotYstart = frameY + 15;
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
		double characterSlot = 1.75;
		g2.drawString("Skills", 30, (int) (gp.tileSize));
		for(Combatant p : gp.playable) {
			if(p != null) {
				g2.drawString(p.name, 30, (int) (gp.tileSize * characterSlot));
				characterSlot += 0.75;
			}
		}
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke());
		for(Combatant p : gp.playable) {
			if(p != null) {
				//g2.drawString(p.name, 30, (int) (gp.tileSize * 1.75D));
				g2.drawString("Auto:    " + p.getAutomatic().getName(), frameX + 15, (int) (gp.tileSize * 1.75D));
				g2.drawString("Offence: " + p.getOffencive().getName(), frameX + 15, (int) (gp.tileSize * 2.5D));
				g2.drawString("Support: " + p.getSupportive().getName(), frameX + 15, (int) (gp.tileSize * 3.25D));
				g2.drawString("Self:    " + p.getSelfie().getName(), frameX + 15, (int) (gp.tileSize * 4));
			}
		}
	}
	
	public void drawObjectiveScreen() {
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
		g2.drawString("Quests ", 30, gp.tileSize);
		int pos = (int) (gp.tileSize * 0.75D);
		bottomValue = gp.objective.quests.length;
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
		for (int i = 0; i < 6; ++i) {
			if (gp.objective.quests[i] != null) {
				pos += gp.tileSize;
				try {
					if(i + topValue != 0) {
						if(!gp.objective.quests[i + topValue].isAccepted()) {
							g2.drawString("???", 30, pos);
						} else {
							g2.drawString("Quest: " + gp.objective.quests[i + topValue].id, 30, pos);
						}
					} else {
						g2.drawString(gp.objective.quests[i + topValue].name, 30, pos);
						
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		//Display Up arrow
		if(topValue > 0) {
			drawUpIcon(frameX + 110, 75, 20, 20);
		}
		//Display Down arrow
		if(topValue < bottomValue - 6) {
			drawDownIcon(frameX + 110, 440, 20, 20);
		}

		frameX = 300;
		frameY = 25;
		frameWidth = gp.tileSize * 7;
		//frameHeight = gp.tileSize * 4;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);
		try {
			if(topValue != 0) {
				g2.drawString("Quest: " + gp.objective.quests[slotCol + topValue].id, frameX + 40, frameY + 40);
			} else {
				if(!gp.objective.quests[slotCol + topValue].isAccepted()) {
					g2.drawString("???", 30, pos);
				} else {
					g2.drawString(gp.objective.quests[slotCol + topValue].name, frameX + 40, frameY + 40);
				}
			}
			int lineNum = 80;
			System.out.println(slotCol + topValue);
			if(gp.objective.quests[slotCol + topValue].isAccepted()) {
				for (String line : breakLines((gp.objective.quests[slotCol + topValue].printQuest()), 35)){
					for (String list : line.split("£")) {
						g2.setFont(g2.getFont().deriveFont(0, 16.0F));
						g2.drawString(list, frameX + 40,
							frameY + lineNum);
						lineNum += 20;
					}
				}
				g2.drawString(gp.objective.quests[slotCol + topValue].printQuestStatus(), frameX + 40, frameY + lineNum + 20);
			}
		} catch (NullPointerException e) {
			System.out.println(e);
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
		//Display Up arrow
		if(topValue > 0) {
			drawUpIcon(frameX + 110, 75, 20, 20);
		}
		//Display Down arrow
		if(topValue < bottomValue - 6) {
			drawDownIcon(frameX + 110, 440, 20, 20);
		}

		frameX = 300;
		frameY = 25;
		frameWidth = gp.tileSize * 7;
		//frameHeight = gp.tileSize * 4;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);

		try {
			g2.drawString(gp.glossary.page[section][slotCol + topValue].getTitle(),
					frameX + 40, frameY + 40);
			int lineNum = 80;
			for (String line : breakLines((gp.glossary.page[section][slotCol + topValue].getDesc()), 35)){
				g2.setFont(g2.getFont().deriveFont(0, 16.0F));
				g2.drawString(line, frameX + 40,
					frameY + lineNum);
				lineNum += 20;
			}
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
	
	public void drawCombatScreen() {
		gp.rm[gp.currentRoom].draw(g2);
		int x = gp.tileSize * 3 / 2;
		int y = gp.tileSize * 5;
		int width = gp.screenWidth - gp.tileSize * 3;
		int height = gp.tileSize * 3;
		drawDialogueWindow(x, y, width, height);
		x += gp.tileSize;
		y += gp.tileSize;
		int slotXstart = x;
		int slotYstart = y;
		int cursorX = slotXstart + gp.tileSize * slotCol;
		int cursorY = slotYstart + gp.tileSize * slotRow;
		drawCombatants(g2);
		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);
		//Check if playable characters are alive
		for(Combatant p: gp.playable) {
			if(p.health <= 0 && p.isAlive()) {
				gp.combat.playerDeath(p);
			}
		} //Check if enemies are alive
		for(Enemy enemy: gp.combat.enemies) {
			if(enemy.health <= 0 && enemy.isAlive()) {
				gp.combat.enemyDeath(enemy);
			}
		} //Check if combatant is alive
		if(gp.combat.getCombatant().isAlive() || gp.combat.getCombatant().getHealth() > 0) {
			if(gp.combat.getCombatant() == gp.playable.get(0)) {
				if(gp.combat.getCombatant().getCombatStatus() == CombatStatus.Using) {
					drawCombatInventoryScreen(x,gp.tileSize * 5,width);
				} else if(gp.combat.getCombatant().getCombatStatus() == CombatStatus.Specializing) {
					drawCombatSpecialScreen(x,gp.tileSize * 5,width);
				} else {
					//Draw combat menu
					System.out.println(commandNum); 
					g2.drawString(" Attack", x, y);
					g2.drawString(" Block", x, y + (gp.tileSize));
					g2.drawString(" Position", x + (gp.tileSize*3), y);
					g2.drawString(" Special", x + (gp.tileSize*3), y + (gp.tileSize));
					g2.drawString(" Items", x + (gp.tileSize*6), y);
					g2.drawString(" Flee", x + (gp.tileSize*6), y + (gp.tileSize));
			
					g2.drawString(">", cursorX - 5, cursorY);
				}
			} else {
				//Get enemy response
				gp.combat.getCombatant().action();
			}
		} else {
			//If opponent is not alive next turn
			gp.combat.incrementTurn();
		}
		
	}
	
	public void drawCombatInventoryScreen(int x, int y, int width) {
		int slotYstart = y + gp.tileSize + 15;
		int slotY = slotYstart;
		ArrayList<Entity> items = gp.player.combatItems(itemFilter);
		for(int i = firstValue; i < items.size(); i++) {
			if(slotY + 30 > 500) {
				break;
			}
			if(filter(i)) {
				if (choiceSlot == i) {
					slotCol2 = gp.player.inventory.indexOf(items.get(i));;
					g2.drawString(">", x - 15, slotY);
				}
				if(itemFilter != null) {
					g2.setFont(g2.getFont().deriveFont(32f));
					g2.drawString(itemFilter.name(), x - 30, y + 35);
				}
				g2.setFont(g2.getFont().deriveFont(16f));
				g2.setColor(Color.white);
				g2.drawString(items.get(i).name, x, slotY);
				g2.drawString("x " + items.get(i).amount, x + (int)(gp.tileSize * 4), slotY);
				slotY += gp.tileSize;
				int itemIndex = getItemIndexOnSlot();
				if (itemIndex < items.size()) {
					//System.out.println(gp.player.inventory.get(itemIndex));
				}
			}
		}
		//Display Up arrow
		if(firstValue > 0) {
			drawUpIcon((int)(width/1.65), 340, 20, 20);
		}
		//Display Down arrow
		if(firstValue < gp.player.combatItems(itemFilter).size() - 2) {
			drawDownIcon((int)(width/1.65), 470, 20, 20);
		}
	}
	
	public void drawCombatSpecialScreen(int x, int y, int width) {
		int slotXstart = x;
		int slotYstart = y + gp.tileSize + 15;
		int cursorX = slotXstart + gp.tileSize * (slotCol2 * 4);
		int cursorY = slotYstart + gp.tileSize * slotRow2;
		//ArrayList<Skill> skills = gp.playable.get(0)
		g2.setFont(g2.getFont().deriveFont(16f));
		g2.setColor(Color.white);
		g2.drawString(" " + gp.playable.get(0).getOffencive().getName(), x, slotYstart);
		g2.drawString(" " + gp.playable.get(0).getSupportive().getName(), x + (gp.tileSize*4), slotYstart);
		g2.drawString(" " + gp.playable.get(0).getSelfie().getName(), x, slotYstart + (gp.tileSize));
		g2.drawString(">", cursorX - 5, cursorY);
	}
	
	public boolean filter(int i) {
		ArrayList<Entity> items = gp.player.combatItems(itemFilter);
		if(itemFilter == null) {
			if(items.get(i).type == EntityType.Food ||
				items.get(i).type == EntityType.Health ||
				items.get(i).type == EntityType.Projectile){
					return true;
			}
		} else {
			//System.out.println(items.get(i).type +" "+ itemFilter);
			while(i < items.size()) {
				if(items.get(i).type == itemFilter) {
					//firstValue = i - 1;
					return true;
				}
				i++;
			}
		}
		return false;
	}
	
	public void drawCombatants(Graphics2D g2) {
		//Draw Playable Characters
		gp.playable.get(0).draw(g2);
		//Draw Opponents
		for(int i = 0; i < gp.combat.enemies.size(); i++) {
			if(gp.combat.enemies.get(i) != null) {
				if(gp.combat.enemies.get(i).isAlive()) {
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
					gp.combat.enemies.get(i).draw(g2, i);
				}
			}
		}
	}
	
	public void targetCombatant() {
		int x = gp.tileSize*6, y = gp.tileSize*2;
		gp.rm[gp.currentRoom].draw(g2);
		drawCombatants(g2);
		if(!gp.combat.enemies.get(slotCol2).isAlive()) {
			slotCol2 = gp.keyH.getNext(slotCol2, gp.combat.enemies.size() - 1);
		}
		for(int i = 0; i < gp.combat.enemies.size(); i++) {
			if(gp.combat.enemies.get(i) != null) {
				if(gp.combat.enemies.get(i).isAlive()) {
					if (slotCol2 == 0) {
						x = gp.tileSize*6;
						y = gp.tileSize*2;
					} else if (slotCol2 == 1) {
						x = gp.tileSize*6;
						y = gp.tileSize*1;
					}
					g2.setColor(Color.white);
					g2.drawRoundRect(x, y, gp.tileSize*2, gp.tileSize*2, 10, 10);
				}
			}
		}
	}
	
	public void drawRewardsScreen() {
		gp.rm[gp.currentRoom].draw(g2);
		int frameX = 100;
		int frameY = 25;
		int frameWidth = gp.tileSize * 9;
		int frameHeight = gp.tileSize * 4;
		int x = frameX  + gp.tileSize;
		int y = frameY  + gp.tileSize;
		
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);

		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(28f));
		Map<String, Integer> hm = new HashMap<String, Integer>();
        for (Entity i : droppedItems) {
            Integer j = hm.get(i.name);
            hm.put(i.name, (j == null) ? 1 : j + 1);
        }
        // displaying the occurrence of elements in the arraylist
        for (Map.Entry<String, Integer> val : hm.entrySet()) {
			g2.drawString(val.getKey(), x, y);
			g2.drawString(": " + val.getValue(), (frameWidth - gp.tileSize), y);
			y += gp.tileSize  / 2;
        }
	}
	
	public void drawGameOverScreen() {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		int x;
		int y;
		String text = "Game Over";
		//Shadow
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.tileSize*4;
		//Main
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		//Quit
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Quit";
		x = getXforCenteredText(text);
		y += gp.tileSize*4;
		
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
				//System.out.println(pos);
				if (pos == -1) {
					pos = text.lastIndexOf(" ", size);
				}
				//text = text.replaceFirst("£", "");
				//pos -= 1;
			}
			if (size > text.length()) {
				pos = text.length() - 1;
			}
			try {
				String found = text.substring(0, pos + 1);
				text = text.substring(pos + 1);
				lines.add(found);
			} catch (StringIndexOutOfBoundsException e) {
				String found = text.substring(0, pos + 1);
				text = text.substring(pos + 1);
				lines.add(found);
			}
			//System.out.println(found);
		}
		String[] lineBreaks = lines.toArray(new String[lines.size()]);
		return lineBreaks;
	}

	private void drawImageWindow(int x, int y, int width, int height) {
		BufferedImage image = selectedObject.sprites[selectedObject.dialogueSet][selectedObject.dialogueIndex];
		if (image != null) {
			g2.drawImage(image, x, y, width, height, (ImageObserver) null);
		}

	}
	
	private void drawUpIcon(int x, int y, int width, int height) {
		BufferedImage image;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/icons/icons8-sort-up-30.png"));
			if (image != null) {
				g2.drawImage(image, x, y, width, height, null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void drawDownIcon(int x, int y, int width, int height) {
		BufferedImage image;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/icons/icons8-sort-down-30.png"));
			if (image != null) {
				g2.drawImage(image, x, y, width, height, null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawDialogueWindow(int x, int y, int width, int height) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
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
	
	public int countFrequencies(ArrayList<Entity> list) {
        // hashmap to store the frequency of element
        Map<Entity, Integer> hm = new HashMap<Entity, Integer>();
        int count = 0;
        for (Entity i : list) {
            Integer j = hm.get(i);
            hm.put(i, (j == null) ? 1 : j + 1);
            count = j;
        }
        // displaying the occurrence of elements in the arraylist
        for (Map.Entry<Entity, Integer> val : hm.entrySet()) {
            System.out.println("Element " + val.getKey() + " "
                               + "occurs"
                               + ": " + val.getValue() + " times");
        }
        return count;
    }

	public int getXforCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, this.g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}
	
	public int getXforAlignToRightText(String text, int tailX) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, this.g2).getWidth();
		int x = tailX - length;
		return x;
	}

	public void resetSlots() {
		slotRow = 0;
		slotCol = 0;
		slotRow2 = 0;
		slotCol2 = 0;
		commandNum = 0;
	}
}