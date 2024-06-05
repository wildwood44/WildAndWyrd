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
import wildwyrd.game.items.Item;
import wildwyrd.game.items.Itm_Bug_Meat;
import wildwyrd.game.library.Book;
import wildwyrd.game.npc.NPC;
import wildwyrd.game.playable.Combatant;
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
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	public int slotCol2 = 0;
	public int slotRow2 = 0;
	public int subState;
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
	public NPC npc;
	public boolean openBook = false;
	public Book[] selectedBookshelf;
	public Book selectedBook;
	public ArrayList<Entity> droppedItems = new ArrayList<Entity>();;
	int charIndex = 0;
	String combinedText = "";
	public GameState returnState;
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
			//Integer var2 = gp.selectedObj;
		}

		if (gp.gameState == GameState.pauseState) {
			drawPauseScreen();
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

		if (gp.gameState == GameState.saveState) {
			drawSaveScreen();
		}

		if (gp.gameState == GameState.statusState) {
			drawStatusScreen();
		}

		if (gp.gameState == GameState.inventoryState) {
			drawInventoryScreen(gp.player, true);
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

		if (gp.gameState == GameState.optionsState) {
			drawOptionsScreen();
		}
		
		if (gp.gameState == GameState.tradeState) {
			drawTradeScreen();
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
		drawDialogueWindow(x, y, width, height);
		drawImageWindow(550, y + 5, gp.tileSize, gp.tileSize);
		g2.setFont(g2.getFont().deriveFont(0, 18.0F));
		g2.setColor(Color.white);
		x += gp.tileSize;
		y += gp.tileSize;
		if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex] != null) {
			printDialogueText(x, y, width);
		} else {
			//post dialogue
			selectedObject.dialogueIndex = 0;
			if (gp.gameState == GameState.dialogueState) {
				if(!gp.combat.enemiesActive()) {
					//drawCombatants(g2);
					gp.combat.inCombat = false;
					gp.combat.win = true;
				} else if (!gp.combat.playableActive()) {
					//drawCombatants(g2);
					gp.combat.inCombat = false;
					gp.combat.win = false;
				}
				if(gp.combat.inCombat) {
					gp.gameState = GameState.combatState;
					gp.combat.incrementTurn();
				} else {
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
		drawImageWindow(550, y + 5, gp.tileSize, gp.tileSize);
		g2.setFont(g2.getFont().deriveFont(0, 18.0F));
		g2.setColor(Color.white);
		x += gp.tileSize;
		y += gp.tileSize;
		if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex] != null) {
			printDialogueText(x, y, width);
		} else {
			// End dialogue
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
			//Automatic line break
			for (String line : breakLines(currentDialogue,40)) {
				g2.setFont(g2.getFont().deriveFont(0, 18.0F));
				g2.drawString(line, x, y);
				y += 30;
			}
		}
	}
	
	public void printDialogueText(int x, int y, int width) {
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
		//Read dialogue line
		if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex]
				.getType() == 1) {
			dialogueRes();
			//selectedObject.checkConditions();
		// Yes/No options
		} else if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex]
				.getType() == 2) {
			binaryRes(x, y);
		// Dialogue options
		} else if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex]
				.getType() == 3) {
			optionsRes(x, y, width);
		//Initialise combat
		} else if (selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex]
				.getType() == 4) {
			gp.combat.startCombat();
		}
	}
	
	public void dialogueRes() {
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
				if (gp.gameState == GameState.dialogueState || 
						gp.gameState == GameState.cutsceneState) {
					selectedObject.dialogueIndex++;
					gp.keyH.enterPressed = false;
				}
			}
		//Skip dialogue
		} else if (gp.keyH.skipPressed) {
			if(selectedObject.skippable) {
				charIndex = 0;
				combinedText = "";
				if (gp.gameState == GameState.examineState) {
					selectedObject.dialogueIndex = selectedObject.dialogues.length - 1;
					gp.keyH.skipPressed = false;
				}
				if (gp.gameState == GameState.dialogueState ||
					gp.gameState == GameState.cutsceneState) {
					selectedObject.dialogueIndex = selectedObject.dialogues.length - 1;
					gp.keyH.skipPressed = false;
				}
			}
		} 
		selectedObject.checkConditions();
	}
	
	public void binaryRes(int x, int y) {
		if (gp.keyH.enterPressed) {
			charIndex++;
			if (choiceSlot == 0) {
				binaryRes = true;
			} else {
				binaryRes = false;
			}

			charIndex = 0;
			combinedText = "";
			//if (gp.gameState == GameState.examineState) {
				selectedObject.choiceResponce();
				selectedObject.dialogueIndex++;
				gp.keyH.enterPressed = false;
			//}
		}

		if (choiceSlot == 0) {
			g2.drawString(">", x, y + 40);
		} else {
			g2.drawString(">", x, y + 80);
		}

		g2.drawString("Yes", x + 20, y + 40);
		g2.drawString("No", x + 20, y + 80);
	}
	
	public void optionsRes(int x, int y, int width) {
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
	}
	
	// DRAW PAUSE SCREEN
	public void drawPauseScreen() {
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		g2.drawString(text, x, y);
	}

	public void drawMenuBarScreen() {
		int frameX = 20;
		int frameY = 25;
		int frameWidth = gp.tileSize * 4;
		int frameHeight = gp.tileSize * 7;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int slotXstart = frameX + 0;
		int slotYstart = frameY + 15;
		int cursorX = slotXstart + gp.tileSize * playerSlotRow;
		int cursorY = (int) (slotYstart + gp.tileSize * 0.75D * playerSlotCol);
		int cursorWidth = gp.tileSize * 2;
		int cursorHeight = 30;
		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke());
		g2.drawString("Save", 30, gp.tileSize);
		g2.drawString("Stats", 30, (int) (gp.tileSize * 1.75D));
		g2.drawString("Items", 30, (int) (gp.tileSize * 2.5D));
		g2.drawString("Objecties", 30, (int) (gp.tileSize * 3.25D));
		g2.drawString("Glossary", 30, (int) (gp.tileSize * 4));
		g2.drawString("Options", 30, (int) (gp.tileSize * 4.75D));
		g2.drawString("Quit", 30, (int) (gp.tileSize * 5.5D));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
	}
	
	public void drawSaveScreen() {
		int frameX = gp.tileSize * 5;
		int frameY = 25;
		int frameWidth = gp.tileSize * 6;
		int frameHeight = gp.tileSize * 2;
		int slotXstart = frameX + 15;
		int slotYstart = frameY + gp.tileSize + gp.originalTileSize;
		int cursorX = slotXstart;
		int cursorY = (int) (slotYstart + ((gp.tileSize * 2) * commandNum));
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int i = 1;
		g2.setColor(Color.white);
		g2.drawString("File " + i, frameX + gp.tileSize, frameY + gp.tileSize + gp.originalTileSize);
		frameY += gp.tileSize * 2;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		i++;
		g2.setColor(Color.white);
		g2.drawString("File " + i, frameX + gp.tileSize, frameY + gp.tileSize + gp.originalTileSize);
		frameY += gp.tileSize * 2;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		i++;
		g2.setColor(Color.white);
		g2.drawString("File " + i, frameX + gp.tileSize, frameY + gp.tileSize + gp.originalTileSize);
		g2.drawString(">", cursorX, cursorY);
		if(gp.keyH.enterPressed) {
			frameX = gp.tileSize * 4;
			frameY = gp.tileSize * 3;
			frameWidth = gp.tileSize * 4;
			frameHeight = gp.tileSize;
			drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
			g2.setFont(g2.getFont().deriveFont(0, 22.0F));
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke());
			g2.drawString("Game Saved!", frameX + 40, frameY + 40);
		}
	}
	
	public void drawStatusScreen() {
		int frameX = 20;
		int frameY = 25;
		int frameWidth = gp.tileSize * 4;
		int frameHeight = gp.tileSize * 7;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int slotXstart = frameX + 0;
		int slotYstart = frameY + 15;
		int cursorX = slotXstart + gp.tileSize * playerSlotRow;
		int cursorY = (int) (slotYstart + gp.tileSize * 0.75D * playerSlotCol);
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

	public void drawInventoryScreen(Entity entity, boolean cursor) {
		int frameX = 0;
		int frameY = 0;
		int frameWidth = 0;
		int frameHeight = 0;
		int slotX = 0;
		int slotY = 0;
		if(entity == gp.player) {
			frameX = (int)(gp.tileSize*6);
			frameY = 20;
			frameWidth = (int)(gp.tileSize * 5.5);
			frameHeight = gp.tileSize * 5;
			slotX = playerSlotCol;
			slotY = playerSlotRow;
		} else {
			frameX = 25;
			frameY = 20;
			frameWidth = (int)(gp.tileSize * 5.5);
			frameHeight = gp.tileSize * 5;
			slotX = npcSlotCol;
			slotY = npcSlotRow;
		}
		//FRAME
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int slotXstart = frameX + 15;
		int slotYstart = frameY + 15;
		slotX = slotXstart;
		slotY = slotYstart;

		int i;
		for (i = 0; i < entity.inventory.size(); ++i) {
			try {
				g2.drawImage(entity.inventory.get(i).image, slotX, slotY, null);
				if(entity.inventory.get(i).amount > 1) {
					g2.setFont(g2.getFont().deriveFont(32f));
					int amountX;
					int amountY;
					String s = "" + entity.inventory.get(i).amount;
					amountX = getXforAlignToRightText(s , slotX + 44);
					amountY = slotY + gp.tileSize;
					
					//Shadow
					g2.setColor(new Color(60,60,60));
					g2.drawString(s, amountX, amountY);
				}
				slotX += gp.tileSize;
				if (i == 4 || i == 9 || i == 14 || i == 19) {
					slotX = slotXstart;
					slotY += gp.tileSize;
				}
			} catch (NullPointerException e) {
				for (i = 0; i < entity.inventory.size(); ++i) {
					if(entity.inventory.get(i) == null) {
						entity.removeFromInventory(entity.inventory.get(i));
					}
				}
				System.out.println(e);
			}
		}
		//Cursor
		if(cursor) {
			i = slotXstart + gp.tileSize * slotCol2;
			int cursorY = slotYstart + gp.tileSize * slotRow2;
			int cursorWidth = (int) (gp.tileSize);
			int cursorHeight = (int) (gp.tileSize);
			g2.setFont(g2.getFont().deriveFont(0, 18.0F));
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke());
			g2.drawRoundRect(i, cursorY, cursorWidth, cursorHeight, 10, 10);
		}
		int dFrameY = frameY + frameHeight;
		int dFrameHeight = (int)(gp.tileSize * 2.5);
		drawDialogueWindow(frameX, dFrameY, frameWidth, dFrameHeight);
		int textX = frameX + 20;
		int textY = dFrameY + gp.tileSize;
		int itemIndex = getItemIndexOnSlot();
		g2.setColor(Color.white);
		//Print item description
		if (itemIndex < entity.inventory.size()) {
			try {
				for (String line : breakLines(entity.inventory.get(itemIndex).description,30)) {
					g2.drawString(line, textX, textY);
					textY += 40;
				}
				if(entity.inventory.get(itemIndex).type == EntityType.Primary) {
					g2.drawString("" + gp.playable.get(0).getAttack() + " - " + (gp.playable.get(0).getBaseAttack() + gp.player.inventory.get(itemIndex).attackValue), textX, textY);
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		//Print dialogue window
		if(entity == gp.player) {
			frameX = 40;
			frameY = 25;
			frameWidth = gp.tileSize * 4;
			frameHeight = gp.tileSize * 7;
			drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
			g2.setFont(g2.getFont().deriveFont(0, 22.0F));
			g2.setColor(Color.white);
			//g2.drawString("Shillings: " + gp.player.getShillings(), 30, gp.tileSize);
			drawCoin(gp.player.getShillings(), frameX + (gp.tileSize/2), frameY + (gp.tileSize/2));
		}
	}
	
	public void drawEquipScreen() {
		int frameX = 20;
		int frameY = 25;
		int frameWidth = gp.tileSize * 4;
		int frameHeight = gp.tileSize * 7;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int slotXstart = frameX + 0;
		int slotYstart = frameY + 15;
		int cursorX = slotXstart + gp.tileSize * playerSlotRow;
		int cursorY = (int) (slotYstart + gp.tileSize * 0.75D * playerSlotCol);
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
		int cursorX = slotXstart + gp.tileSize * playerSlotRow;
		int cursorY = (int) (slotYstart + gp.tileSize * 0.75D * playerSlotCol);
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
		int cursorX = slotXstart + gp.tileSize * playerSlotRow;
		int cursorY = (int) (slotYstart * 2.25D + gp.tileSize * playerSlotCol);
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
				g2.drawString("Quest: " + gp.objective.quests[playerSlotCol + topValue].id, frameX + 40, frameY + 40);
			} else {
				if(!gp.objective.quests[playerSlotCol + topValue].isAccepted()) {
					g2.drawString("???", 30, pos);
				} else {
					g2.drawString(gp.objective.quests[playerSlotCol + topValue].name, frameX + 40, frameY + 40);
				}
			}
			int lineNum = 80;
			if(gp.objective.quests[playerSlotCol + topValue].isAccepted()) {
				for (String line : breakLines((gp.objective.quests[playerSlotCol + topValue].printQuest()), 35)){
					for (String list : line.split("£")) {
						g2.setFont(g2.getFont().deriveFont(0, 16.0F));
						g2.drawString(list, frameX + 40,
							frameY + lineNum);
						lineNum += 20;
					}
				}
				if(gp.objective.quests[playerSlotCol + topValue].id != 0) {
					g2.drawString(gp.objective.quests[playerSlotCol + topValue].printQuestStatus(), frameX + 40, frameY + lineNum + 20);
				}
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
		int cursorX = slotXstart + gp.tileSize * playerSlotRow;
		int cursorY = (int) (slotYstart * 2.25D + gp.tileSize * playerSlotCol);
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
			g2.drawString(gp.glossary.page[section][playerSlotCol + topValue].getTitle(),
					frameX + 40, frameY + 40);
			int lineNum = 80;
			for (String line : breakLines((gp.glossary.page[section][playerSlotCol + topValue].getDesc()), 35)){
				g2.setFont(g2.getFont().deriveFont(0, 16.0F));
				g2.drawString(line, frameX + 40,
					frameY + lineNum);
				lineNum += 20;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public void drawOptionsScreen() {
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(22F));
		// SUB WINDOW
		int frameX = gp.tileSize * 2;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 8;
		int frameHeight = gp.tileSize * 6;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		switch(subState) {
		case 0: options_top(frameX, frameY); break;
		case 1: options_fullScreenNotification(frameX, frameY); break;
		case 2: options_control(frameX, frameY); break;
		}
		gp.keyH.enterPressed = false;
	}
	
	public void options_top(int frameX, int frameY) {
		g2.setColor(Color.white);
		int textX;
		int textY;
		//Title
		String text = "Options";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		// FULL SCREEN ON/OFF
		textX  = frameX + gp.tileSize;
		textY += gp.tileSize + 2;
		g2.drawString("Full Screen", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed) {
				if(!gp.fullScreenOn) {
					gp.fullScreenOn = true;
				} else {
					gp.fullScreenOn = false;
				}
				subState = 1;
			}
		}
		// MUSIC
		textY += gp.tileSize/2;
		g2.drawString("Music", textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
		}
		// SOUND EFFECTS
		textY += gp.tileSize/2;
		g2.drawString("SE", textX, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX-25, textY);
		}
		// CONTROL
		textY += gp.tileSize/2;
		g2.drawString("Control", textX, textY);
		if(commandNum == 3) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed) {
				subState = 2;
				commandNum = 0;
			}
		}
		// BACK
		textY += gp.tileSize * 2;
		g2.drawString("Back", textX, textY);
		if(commandNum == 4) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed) {
				gp.gameState = GameState.menuState;
			}
		}
		// FULL SCREEN CHECK BOX
		textX = frameX + gp.tileSize * 5;
		textY = frameY + gp.tileSize + 46;
		g2.setStroke(new BasicStroke());
		g2.drawRect(textX, textY, 24, 24);
		if(gp.fullScreenOn) {
			g2.fillRect(textX, textY, 24, 24);
		}
		// MUSIC VOLUME
		textY += gp.tileSize/2;
		g2.drawRect(textX, textY, 120, 24);
		int volumeWidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		// SOUND EFFECTS VOLUME
		textY += gp.tileSize/2;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * gp.se.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		gp.config.saveConfig();
	}
	
	public void options_fullScreenNotification(int frameX, int frameY) {
		g2.setColor(Color.white);
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*2;
		currentDialogue = "The change will take effect after restarting the game";
		for (String line : breakLines(currentDialogue,30)) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		textY  = frameY + gp.tileSize * 5;
		g2.drawString("Back", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed) {
				subState = 0;
			}
		}
	}
	
	public void options_control(int frameX, int frameY) {
		g2.setColor(Color.white);
		// TITLE
		String title = "Control";
		int textX = getXforCenteredText(title);
		int textY = frameY + gp.tileSize;
		g2.drawString(title, textX, textY);
		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.drawString("Move", textX, textY);textY+=gp.tileSize/2;
		g2.drawString("Confirm", textX, textY);textY+=gp.tileSize/2;
		g2.drawString("Menu", textX, textY);textY+=gp.tileSize/2;
		g2.drawString("Pause", textX, textY);textY+=gp.tileSize/2;
		textX = frameX + gp.tileSize * 6;
		textY = frameY + gp.tileSize * 2;
		g2.drawString("WASD", textX, textY);textY+=gp.tileSize/2;
		g2.drawString("ENTER", textX, textY);textY+=gp.tileSize/2;
		g2.drawString("ESC", textX, textY);textY+=gp.tileSize/2;
		g2.drawString("Space", textX, textY);textY+=gp.tileSize/2;
		// BACK
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize * 5;
		g2.drawString("Back", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed) {
				subState = 0;
			}
		}
	}
	//Trade
	public void drawTradeScreen() {
		switch(subState) {
		case 0: trade_select();break;
		case 1: trade_buy();break;
		case 2: trade_sell();break;
		}
		gp.keyH.enterPressed = false;
	}
	
	public void trade_select() {
		npc.dialogueSet = 0;
		selectedObject = npc;
		drawDialogueScreen();
		//Draw Window
		int x = gp.tileSize * 8;
		int y = gp.tileSize * 4;
		int width = gp.tileSize * 3;
		int height = (int)(gp.tileSize * 2.5);
		drawDialogueWindow(x, y, width, height);
		//Draw Text
		x += gp.tileSize;
		y += gp.tileSize;
		g2.setColor(Color.WHITE);
		g2.setFont(g2.getFont().deriveFont(0, 22.0F));
		g2.drawString("Buy", x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-24, y);
			if(gp.keyH.enterPressed) {
				subState = 1;
			}
		}
		y += gp.tileSize/2;
		g2.drawString("Sell", x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-24, y);
			if(gp.keyH.enterPressed) {
				subState = 2;
			}
		}
		y += gp.tileSize/2;
		g2.drawString("Leave", x, y);
		if(commandNum == 2) {
			g2.drawString(">", x-24, y);
			if(gp.keyH.enterPressed) {
				commandNum = 0;
				selectedObject.startDialogue(selectedObject, 1);
			}
		}
	}
	
	public void trade_buy() {
		// DRAW PLAYER INVENTORY
		drawInventoryScreen(gp.player, false);
		// DRAW NPC INVENTORY
		drawInventoryScreen(npc, true);
		// DRAW HINT WINDOW
		int x = gp.tileSize *4;
		int y = (int)(gp.tileSize * 7);
		int width = gp.tileSize * 2;
		int height = gp.tileSize;
		selectedObject = npc;
		drawDialogueWindow(x, y, width, height);
		g2.setColor(Color.WHITE);
		g2.drawString("[ESC] Back", x+5, y+30);
		// DRAW SHILLING WINDOW
		x = (int)(gp.tileSize*6);
		y = (gp.tileSize * 5) + 20;
		width = (int)(gp.tileSize * 5.5);
		height = (int)(gp.tileSize * 2.5);
		drawDialogueWindow(x, y, width, height);
		g2.setColor(Color.WHITE);
		//g2.drawString("Shillings: " + gp.player.getShillings(), x + 25, y + gp.tileSize);
		drawCoin(gp.player.getShillings(), x + 25, y + gp.tileSize/2);
		// DRAW PRICE WINDOW
		int itemIndex = getItemIndexOnSlot();
		if(itemIndex < npc.inventory.size()) {
			x = (int)(gp.tileSize * 3.5);
			y = (int)(gp.tileSize * 4.8);
			width = (int)(gp.tileSize * 2.5);
			height = gp.tileSize;
			drawDialogueWindow(x, y, width, height);
			try {
				int price = npc.inventory.get(itemIndex).price;
				String text = "";
				if(npc.buy(npc.inventory.get(itemIndex)) != null) {
					//text = npc.buy(npc.inventory.get(itemIndex)).name;
					x = getXforAlignToRightText(text, (int)(gp.tileSize*3.5) + 20);
					g2.drawImage(npc.buy(npc.inventory.get(itemIndex)).image, x, y, null);
					//drawCoin(price, x, y);
				} else {
					//text = "Price: " + price;
					x = getXforAlignToRightText(text, (int)(gp.tileSize*3.5) + 20);
					drawCoin(price, x, y);
				}
				g2.drawString(text, x, y+34);
				// BUY AN ITEM
				if(gp.keyH.enterPressed) {
					//Item not in inventory
					if(!gp.player.inInventory(npc.buy(npc.inventory.get(itemIndex))) && npc.buy(npc.inventory.get(itemIndex)) != null) {
						subState = 0;
						selectedObject.startDialogue(selectedObject, 2);
					//Not enough shillings
					} else if(price > gp.player.getShillings()) {
						subState = 0;
						selectedObject.startDialogue(selectedObject, 2);
					//Inventory is full
					} else if(gp.player.inventory.size() == gp.player.inventorySize) {
						subState = 0;
						selectedObject.startDialogue(selectedObject, 3);
					} else {
						//Trade Items
						if(npc.buy(npc.inventory.get(itemIndex)) != null) {
							Item selectedItem = gp.player.inventory.get(gp.player.searchItemInInventory(npc.buy(npc.inventory.get(itemIndex)).id));
							gp.player.removeFromInventory(selectedItem);
						//Spend shillings
						} else {
							gp.player.spendShillings(price);
						}
						gp.player.pickUpObject(npc.inventory.get(itemIndex));
					}
					
				}
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void trade_sell() {
		// DRAW PLAYER INVENTORY
		drawInventoryScreen(gp.player, true);
		// DRAW NPC INVENTORY
		drawInventoryScreen(npc, false);
		// DRAW HINT WINDOW
		int x = gp.tileSize *4;
		int y = (int)(gp.tileSize * 7);
		int width = gp.tileSize * 2;
		int height = gp.tileSize;
		selectedObject = npc;
		drawDialogueWindow(x, y, width, height);
		g2.setColor(Color.WHITE);
		g2.drawString("[ESC] Back", x+5, y+30);
		// DRAW SHILLING WINDOW
		x = (int)(gp.tileSize*6);
		y = (gp.tileSize * 5) + 20;
		width = (int)(gp.tileSize * 5.5);
		height = (int)(gp.tileSize * 2.5);
		drawDialogueWindow(x, y, width, height);
		drawCoin(gp.player.getShillings(), x + 25, y + gp.tileSize/2);
		// DRAW PRICE WINDOW
		int itemIndex = getItemIndexOnSlot();
		if(itemIndex < gp.player.inventory.size()) {
			x = (int)(gp.tileSize * 3.5);
			y = (int)(gp.tileSize * 4.8);
			width = (int)(gp.tileSize * 2.5);
			height = gp.tileSize;
			drawDialogueWindow(x, y, width, height);
			String text = "";
			int price = gp.player.inventory.get(itemIndex).price;
			if(npc.sell(gp.player.inventory.get(itemIndex)) != null) {
				//text = "" + npc.sell(gp.player.inventory.get(itemIndex)).name;
				x = getXforAlignToRightText(text, (int)(gp.tileSize*3.5) + 20);
				g2.drawImage(npc.sell(gp.player.inventory.get(itemIndex)).image, x, y, null);
			} else {
				//text = "" + price;
				x = getXforAlignToRightText(text, (int)(gp.tileSize*3.5) + 20);
				drawCoin(price, x, y);
			}
			g2.setColor(Color.WHITE);
			g2.drawString(text, x, y+34);
			// SELL AN ITEM
			if(gp.keyH.enterPressed) {
				if (npc.inInventory(gp.player.inventory.get(itemIndex))) {
					subState = 0;
					selectedObject.startDialogue(selectedObject, 4);
				} else if(npc.sell(gp.player.inventory.get(itemIndex)) != null) {
					gp.player.pickUpObject(npc.sell(gp.player.inventory.get(itemIndex)));
					gp.player.removeFromInventory(gp.player.inventory.get(itemIndex));
				} else {
					gp.player.pickUpShillings(price);
					gp.player.removeFromInventory(gp.player.inventory.get(itemIndex));
				}
			}
		}
	}
	//Reading
	public void drawBookshelfScreen() {
		int frameX = 20;
		int frameY = 25;
		int frameWidth = gp.tileSize * 8;
		int frameHeight = gp.tileSize * 7;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int slotXstart = frameX + 10;
		int slotYstart = frameY + gp.tileSize;
		int cursorX = slotXstart + gp.tileSize * playerSlotRow;
		int cursorY = (int) (slotYstart + gp.tileSize * 0.75D * playerSlotCol);
		int cursorWidth = (int) (gp.tileSize * 7.5);
		int cursorHeight = 30;
		if(openBook) {
			drawBookScreen(selectedBookshelf[playerSlotCol]);
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
		for (String line : breakLines(selectedBook.getContent()[playerSlotRow], 60)) {
			for (String list : line.split("£")) {
				g2.setFont(g2.getFont().deriveFont(0, 14.0F));
				g2.drawString(list, 35, y);
				y += 30;
			}
		}
	}
	
	//Combat
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
		int cursorX = slotXstart + gp.tileSize * playerSlotCol;
		int cursorY = slotYstart + gp.tileSize * playerSlotRow;
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
			if(!gp.combat.enemiesActive()) {
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
		ArrayList<Item> items = gp.player.combatItems(itemFilter);
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
		ArrayList<Item> items = gp.player.combatItems(itemFilter);
		if(itemFilter == null) {
			if(items.get(i).type == EntityType.Food ||
				items.get(i).type == EntityType.Health ||
				items.get(i).type == EntityType.Projectile){
					return true;
			}
		} else {
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
		try {
			while(text.length() > 0){
				int pos = text.lastIndexOf(" ", size);
				//Manual line break
				if(text.contains("£")) {
					pos = text.lastIndexOf("£", size);
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
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		String[] lineBreaks = lines.toArray(new String[lines.size()]);
		return lineBreaks;
	}

	private void drawImageWindow(int x, int y, int width, int height) {
		
		try {
			if(this.selectedObject.dialogues[this.selectedObject.dialogueSet][this.selectedObject.dialogueIndex] != null) {
				BufferedImage image = selectedObject.dialogues[selectedObject.dialogueSet][selectedObject.dialogueIndex].image;
				if (image != null) {
					g2.drawImage(image, x, y, width, height, (ImageObserver) null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	
	private void drawCoin(int amount, int x, int y) {
		BufferedImage image;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/items/img_Shillings.png"));
			if (image != null) {
				g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
				g2.setColor(Color.WHITE);
				g2.drawString(": " + amount, x + gp.tileSize, y + gp.tileSize/2);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawDialogueWindow(int x, int y, int width, int height) {
		try {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			Color c = new Color(255, 255, 255);
			g2.setColor(c);
			g2.fillRoundRect(x, y, width, height, 35, 35);
			c = new Color(0, 0, 0, 210);
			g2.setColor(c);
			g2.setStroke(new BasicStroke(5.0F));
			g2.fillRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0, 0, 0);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke());
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}

	public void drawTitleScreen() {
		switch(subState) {
		case 0 : drawTitleMenuScreen(); break;
		case 1 : drawLoadScreen(); break;
		}
	}
	
	public void drawTitleMenuScreen() {
		g2.setBackground(Color.green);
		g2.setFont(g2.getFont().deriveFont(1, 80.0F));
		String text = "Wild and Wyrd";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight / 3;
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		g2.setFont(g2.getFont().deriveFont(1, 40.0F));
		//New Game
		text = "New Game";
		x = getXforCenteredText(text);
		y = (int) (y + gp.tileSize * 2);
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		//Continue Game
		/*text = "Continue";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 3) {
			g2.drawString(">", x - gp.tileSize, y);

		}*/
		//Load Game
		text = "Load Game";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
			if(gp.keyH.enterPressed) {
				subState = 1;
			}
		}
		//Quit Game
		text = "Quit";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}
	}
	
	public void drawLoadScreen() {
		int frameX = gp.tileSize * 5;
		int frameY = 25;
		int frameWidth = gp.tileSize * 6;
		int frameHeight = gp.tileSize * 2;
		int slotXstart = frameX + 15;
		int slotYstart = frameY + gp.tileSize + gp.originalTileSize;
		int cursorX = slotXstart;
		int cursorY = (int) (slotYstart + ((gp.tileSize * 2) * commandNum));
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		int i = 1;
		g2.setColor(Color.white);
		g2.drawString("File " + i, frameX + gp.tileSize, slotYstart);
		frameY += gp.tileSize * 2;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		i++;
		g2.setColor(Color.white);
		g2.drawString("File " + i, frameX+ gp.tileSize, slotYstart + ((gp.tileSize * 2)));
		frameY += gp.tileSize * 2;
		drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
		i++;
		g2.setColor(Color.white);
		g2.drawString("File " + i, frameX+ gp.tileSize, slotYstart + ((gp.tileSize * 4)));
		g2.drawString(">", cursorX, cursorY);
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
		playerSlotRow = 0;
		playerSlotCol = 0;
		slotRow2 = 0;
		slotCol2 = 0;
		commandNum = 0;
	}
}