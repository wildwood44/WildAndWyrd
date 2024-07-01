package wildwyrd.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import wildwyrd.game.combat.CombatStatus;
import wildwyrd.game.tile.TileManager;

public class KeyHandler implements KeyListener {
	GamePanel gp;
	public boolean upPressed;
	public boolean downPressed;
	public boolean leftPressed;
	public boolean rightPressed;
	public boolean enterPressed;
	public boolean skipPressed;
	public boolean showDebugText = false;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		GameState gameState = gp.gameState;
		if (gameState == GameState.titleState) {
			titleState(code);
		} else if (gp.gameState == GameState.menuState) {
			menuState(code);
		} else if (gp.gameState == GameState.saveState) {
			saveState(code);
		} else if (gp.gameState == GameState.statusState) {
			statusState(code);
		} else if (gameState == GameState.inventoryState) {
			playerInventory(code);
		} else if (gp.gameState == GameState.equipState) {
			equipState(code);
		}  else if (gp.gameState == GameState.skillState) {
			skillState(code);
		} else if (gp.gameState == GameState.objectiveState) {
			objectiveState(code);
		} else if (gp.gameState == GameState.glossaryState) {
			glossaryState(code);
		} else if (gp.gameState == GameState.optionsState) {
			optionsState(code);
		} else if (gp.gameState == GameState.tradeState) {
			tradeState(code);
		} else if (gp.gameState == GameState.readingState) {
			readingState(code);
		} else if (gp.gameState == GameState.combatState) {
			combatState(code);
		} else if (gp.gameState == GameState.targetState) {
			targetState(code);
		} else if (gp.gameState == GameState.rewardState) {
			rewardState(code);
		} else if (gp.gameState == GameState.gameOverState) {
			gameOverState(code);
		} else if (gp.gameState == GameState.pauseState) {
			pauseState(code);
		} else if (gp.gameState == GameState.playState) {
			playState(code);
		} else if (gp.gameState == GameState.dialogueState ||
			gp.gameState == GameState.cutsceneState ||
			gp.gameState == GameState.messageState ||
			gp.gameState == GameState.examineState) {
				dialogueState(code);
		}

	}
	
	public void titleState(int code) {
		if (code == KeyEvent.VK_ESCAPE) {
			if(gp.ui.subState == 1 || gp.ui.subState == 2) {
				gp.ui.commandNum = 1;
				gp.ui.subState = 0;
			}
		}
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			gp.playSE(0);
			gp.ui.commandNum--;
			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = 2;
			}
		}

		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			gp.playSE(0);
			gp.ui.commandNum++;
			if (gp.ui.commandNum > 2) {
				gp.ui.commandNum = 0;
			}
		}

		if (code == KeyEvent.VK_ENTER) {
			if(gp.ui.subState == 0) {
				if (gp.ui.commandNum == 0) {
					//gp = new GamePanel();
					gp.restart();
					gp.ui.resetSlots();
					gp.gameState = GameState.playState;
				}
				if (gp.ui.commandNum == 1) {
					gp.ui.commandNum = 0;
					gp.ui.subState = 1;
				}
				if (gp.ui.commandNum == 2) {
					System.exit(0);
				}
			} else if(gp.ui.subState == 1) {
				gp.saveLoad.load(gp.ui.commandNum);
				gp.ui.resetSlots();
				gp.tileM = new TileManager(gp);
				gp.eHandler = new EventHandler(gp);
				gp.playSE(1);
				gp.gameState = GameState.playState;
			}
		}
	}

	public void dialogueState(int code) {
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if (code == KeyEvent.VK_Q) {
			skipPressed = true;
		}
		if(gp.ui.selectedObject != null) {
			if (gp.ui.selectedObject.dialogues[gp.ui.selectedObject.dialogueSet][gp.ui.selectedObject.dialogueIndex]
					.getType() == 2) {
				if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
					gp.ui.choiceSlot = getPrev(gp.ui.choiceSlot, 1);
				}
		
				if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
					gp.ui.choiceSlot = getNext(gp.ui.choiceSlot, 1);
				}
			} else if (gp.ui.selectedObject.dialogues[gp.ui.selectedObject.dialogueSet][gp.ui.selectedObject.dialogueIndex]
					.getType() == 3) {
				if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
					gp.ui.choiceSlot--;
					if (gp.ui.choiceSlot < 0) {
						gp.ui.choiceSlot = gp.ui.selectedObject.options.length - 1;
						if(gp.ui.selectedObject.options.length > 2) {
							gp.ui.firstValue = gp.ui.choiceSlot - 2;
						}
					}
					if (gp.ui.firstValue > gp.ui.choiceSlot) {
						gp.ui.firstValue = gp.ui.choiceSlot;
					}
				}
		
				if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
					if (gp.ui.choiceSlot > 1 && gp.ui.choiceSlot == gp.ui.firstValue + 2) {
						gp.ui.firstValue++;
					}
					gp.ui.choiceSlot++;
					if (gp.ui.choiceSlot > gp.ui.selectedObject.options.length - 1) {
						gp.ui.choiceSlot = 0;
						gp.ui.firstValue = gp.ui.choiceSlot;
					}
				}
			}
		}

		if (code == 84) {
			if (!showDebugText) {
				showDebugText = true;
			} else if (showDebugText) {
				showDebugText = false;
			}
		}
	}
	
	public void	menuState(int code) {
		switch (code) {
		case KeyEvent.VK_ENTER :
			if (gp.ui.playerSlotCol == 0) { //Save game
				gp.gameState = GameState.saveState;
			} else if (gp.ui.playerSlotCol == 1) { //Open status screen
				gp.playSE(4);
				gp.gameState = GameState.statusState;
				gp.ui.drawStatusScreen();
			} else if (gp.ui.playerSlotCol == 2) { //Open inventory screen
				gp.gameState = GameState.inventoryState;
				gp.ui.drawInventoryScreen(gp.player, true);
			} else if (gp.ui.playerSlotCol == 3) { //Open objectives screen
				gp.ui.resetSlots();
				gp.gameState = GameState.objectiveState;
				gp.ui.drawObjectiveScreen();
			} else if (gp.ui.playerSlotCol == 4) { //Open glossary screen
				gp.ui.resetSlots();
				gp.gameState = GameState.glossaryState;
				gp.ui.drawGlossaryScreen();
			} else if (gp.ui.playerSlotCol == 5) { //Open options
				gp.ui.resetSlots();
				gp.gameState = GameState.optionsState;
			} else if (gp.ui.playerSlotCol == 6) { //Quit game
				gp.currentRoom = 0;
				gp.restart();
				gp.gameState = GameState.titleState;
			}
			break;
		case KeyEvent.VK_ESCAPE :
			gp.gameState = GameState.playState;
			break;
		case KeyEvent.VK_UP :
		case KeyEvent.VK_W :
			gp.playSE(2);
			if (gp.ui.playerSlotCol != 0) {
				--gp.ui.playerSlotCol;
			} else {
				gp.ui.playerSlotCol = 6;
			}
			break;
		case KeyEvent.VK_DOWN :
		case KeyEvent.VK_S :
			gp.playSE(2);
			if (gp.ui.playerSlotCol != 6) {
				++gp.ui.playerSlotCol;
			} else {
				gp.ui.playerSlotCol = 0;
			}
	}
	}
	
	public void saveState(int code) {
		switch (code) {
		case KeyEvent.VK_ESCAPE :
			gp.ui.resetSlots();
			gp.gameState = GameState.menuState;
			break;
		case KeyEvent.VK_ENTER :
			gp.playSE(3);
			if(gp.ui.commandNum == 0) {
				gp.saveLoad.save(1);
			} else if(gp.ui.commandNum == 1) {
				gp.saveLoad.save(2);
			} else if(gp.ui.commandNum == 2) {
				gp.saveLoad.save(3);
			}
			gp.saveLoad.save(0);
			gp.gameState = GameState.playState;
			break;
		case KeyEvent.VK_W :
		case KeyEvent.VK_UP :
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 2;
			}
			break;
		case KeyEvent.VK_DOWN :
		case KeyEvent.VK_S :
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 2) {
				gp.ui.commandNum = 0;
			}
			break;
		}
	}
	
	public void statusState(int code) {
		switch (code) {
			case KeyEvent.VK_ESCAPE :
				gp.ui.resetSlots();
				gp.gameState = GameState.menuState;
				break;
			case KeyEvent.VK_UP :
			case KeyEvent.VK_W :
				if (gp.ui.slotRow2 != 0) {
					--gp.ui.slotRow2;
				} else {
					gp.ui.slotRow2 = 3;
				}
				break;
			case KeyEvent.VK_DOWN :
			case KeyEvent.VK_S :
				if (gp.ui.slotRow2 != 3) {
					++gp.ui.slotRow2;
				} else {
					gp.ui.slotRow2 = 0;
				}
				break;
			case KeyEvent.VK_RIGHT :
			case KeyEvent.VK_D :
				gp.gameState = GameState.equipState;
				break;
			case KeyEvent.VK_LEFT :
			case KeyEvent.VK_A :
				gp.gameState = GameState.skillState;
				break;
		}
	}
	
	public void playerInventory(int code){
		switch (code) {
		case KeyEvent.VK_ESCAPE :
			gp.ui.resetSlots();
			gp.gameState = GameState.menuState;
			break;
		case KeyEvent.VK_LEFT :
		case KeyEvent.VK_A :
			gp.ui.slotCol2 = getNext(gp.ui.slotCol2, 4);
			break;
		case KeyEvent.VK_UP :
		case KeyEvent.VK_W :
			gp.ui.slotRow2 = getNext(gp.ui.slotRow2, 3);
			break;
		case KeyEvent.VK_RIGHT :
		case KeyEvent.VK_D :
			gp.ui.slotCol2 = getPrev(gp.ui.slotCol2, 4);
			break;
		case KeyEvent.VK_DOWN :
		case KeyEvent.VK_S :
			gp.ui.slotRow2 = getPrev(gp.ui.slotRow2, 3);
			break;
		case KeyEvent.VK_ENTER :
			gp.player.selectedItem();
			break;
		}
	}
	
	public void npcInventory(int code){
		switch (code) {
		case KeyEvent.VK_ESCAPE :
			gp.ui.resetSlots();
			gp.ui.subState = 0;
			break;
		case KeyEvent.VK_LEFT :
		case KeyEvent.VK_A :
			gp.ui.slotCol2 = getNext(gp.ui.slotCol2, 4);
			break;
		case KeyEvent.VK_UP :
		case KeyEvent.VK_W :
			gp.ui.slotRow2 = getNext(gp.ui.slotRow2, 3);
			break;
		case KeyEvent.VK_RIGHT :
		case KeyEvent.VK_D :
			gp.ui.slotCol2 = getPrev(gp.ui.slotCol2, 4);
			break;
		case KeyEvent.VK_DOWN :
		case KeyEvent.VK_S :
			gp.ui.slotRow2 = getPrev(gp.ui.slotRow2, 3);
			break;
		case KeyEvent.VK_ENTER :
			gp.player.selectedItem();
			break;
		}
	}
	
	public void equipState(int code) {
		switch (code) {
		case KeyEvent.VK_ESCAPE :
			if (gp.ui.openInventory) {
				gp.ui.openInventory = false;
			}
			else if (gp.ui.openEquipment) {
				gp.ui.openEquipment = false;
			} else {
				gp.ui.resetSlots();
				gp.gameState = GameState.menuState;
			}
			
			break;
		case KeyEvent.VK_ENTER:
			//gp.ui.selectedPlayable = Playable;
			if (!gp.ui.openEquipment) {
				gp.ui.openEquipment = true;
			}else if (!gp.ui.openInventory) {
				gp.ui.openInventory = true;
			}
			break;
		case KeyEvent.VK_UP :
		case KeyEvent.VK_W :
			if (gp.ui.slotRow2 != 0) {
				--gp.ui.slotRow2;
			} else {
				gp.ui.slotRow2 = 3;
			}
			break;
		case KeyEvent.VK_DOWN :
		case KeyEvent.VK_S :
			if (gp.ui.slotRow2 != 3) {
				++gp.ui.slotRow2;
			} else {
				gp.ui.slotRow2 = 0;
			}
			break;
		case KeyEvent.VK_RIGHT :
		case KeyEvent.VK_D :
			gp.gameState = GameState.skillState;
			break;
		case KeyEvent.VK_LEFT :
		case KeyEvent.VK_A :
			gp.gameState = GameState.statusState;
			break;
		}
	}
	
	public void skillState(int code) {
		switch (code) {
		case KeyEvent.VK_ESCAPE :
			gp.ui.resetSlots();
			gp.gameState = GameState.menuState;
			break;
		case KeyEvent.VK_UP :
		case KeyEvent.VK_W :
			if (gp.ui.slotRow2 != 0) {
				--gp.ui.slotRow2;
			} else {
				gp.ui.slotRow2 = 3;
			}
			break;
		case KeyEvent.VK_DOWN :
		case KeyEvent.VK_S :
			if (gp.ui.slotRow2 != 3) {
				++gp.ui.slotRow2;
			} else {
				gp.ui.slotRow2 = 0;
			}
			break;
		case KeyEvent.VK_RIGHT :
		case KeyEvent.VK_D :
			gp.gameState = GameState.statusState;
			break;
		case KeyEvent.VK_LEFT :
		case KeyEvent.VK_A :
			gp.gameState = GameState.equipState;
			break;
		}
	}
	
	public void objectiveState(int code) {
		switch (code) {
		case KeyEvent.VK_ESCAPE :
			gp.ui.resetSlots();
			gp.gameState = GameState.menuState;
			break;
		case KeyEvent.VK_UP :
		case KeyEvent.VK_W :
			if (gp.ui.playerSlotCol != 0) {
				gp.ui.playerSlotCol--;
			} else if (gp.ui.topValue != 0) {
				gp.ui.topValue--;
			} else {
				gp.ui.topValue = gp.ui.bottomValue - 6;
				gp.ui.playerSlotCol = 5;
			}
			break;
		case KeyEvent.VK_DOWN :
		case KeyEvent.VK_S :
			if (gp.ui.playerSlotCol != 5) {
				gp.ui.playerSlotCol++;
			} else {
				if (gp.ui.playerSlotCol + gp.ui.topValue != gp.ui.bottomValue - 1) {
					gp.ui.topValue++;
				} else {
					gp.ui.playerSlotCol = 0;
					gp.ui.topValue = 0;
				}
			}
		}
	}
	
	public void glossaryState(int code){
		switch (code) {
		case KeyEvent.VK_ESCAPE :
			gp.ui.resetSlots();
			gp.gameState = GameState.menuState;
			break;
		case KeyEvent.VK_LEFT :
		case KeyEvent.VK_A :
			if (gp.ui.section != 0) {
				if (gp.glossary.getSize(gp.ui.section) > gp.glossary
						.getSize(gp.ui.section - 1)) {
					gp.ui.playerSlotCol = 0;
					gp.ui.topValue = 0;
				}

				gp.ui.section--;
			} else {
				if (gp.glossary.getSize(gp.ui.section) > gp.glossary
						.getSize(gp.glossary.sections.length - 1)) {
					gp.ui.playerSlotCol = 0;
					gp.ui.topValue = 0;
				}

				gp.ui.section = gp.glossary.sections.length - 1;
			}
			break;
		case KeyEvent.VK_UP :
		case KeyEvent.VK_W :
			if (gp.ui.playerSlotCol != 0) {
				gp.ui.playerSlotCol--;
			} else if (gp.ui.topValue != 0) {
				gp.ui.topValue--;
			} else {
				gp.ui.topValue = gp.ui.bottomValue - 6;
				gp.ui.playerSlotCol = 5;
			}
			break;
		case KeyEvent.VK_RIGHT :
		case KeyEvent.VK_D :
			if (gp.ui.section < gp.glossary.sections.length - 1) {
				if (gp.glossary.getSize(gp.ui.section) > gp.glossary
						.getSize(gp.ui.section + 1)) {
					gp.ui.playerSlotCol = 0;
					gp.ui.topValue = 0;
					}
				gp.ui.section++;
			} else {
				if (gp.glossary.getSize(gp.ui.section) > gp.glossary.getSize(0)) {
					gp.ui.playerSlotCol = 0;
					gp.ui.topValue = 0;
				}
				gp.ui.section = 0;
			}
			break;
		case KeyEvent.VK_DOWN :
		case KeyEvent.VK_S :
			if (gp.ui.playerSlotCol != 5) {
				gp.ui.playerSlotCol++;
			} else {
				if (gp.ui.playerSlotCol + gp.ui.topValue != gp.ui.bottomValue - 1) {
					gp.ui.topValue++;
				} else {
					gp.ui.playerSlotCol = 0;
					gp.ui.topValue = 0;
				}
			}
		}
	}

	public void optionsState(int code) {
		int maxCommandNum = 0;
		switch (gp.ui.subState) {
		case 0: maxCommandNum =  4;
		}
		switch(code) {
		case KeyEvent.VK_ESCAPE: gp.gameState = GameState.menuState; break;
		case KeyEvent.VK_ENTER: enterPressed = true; break; 
		case KeyEvent.VK_UP :
		case KeyEvent.VK_W :
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
			break;
		case KeyEvent.VK_DOWN :
		case KeyEvent.VK_S :
			gp.ui.commandNum++;
			if(gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
			break;
		case KeyEvent.VK_A :
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
					gp.playSE(1);
				}
				if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
					gp.se.volumeScale--;
					gp.playSE(1);
				}
			}
			break;
		case KeyEvent.VK_D :
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
					gp.playSE(1);
				}
				if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
					gp.se.volumeScale++;
					gp.playSE(1);
				}
			}
		}
	}

	public void tradeState(int code) {
		switch (code) {
		case KeyEvent.VK_ESCAPE :
			if(gp.ui.subState == 0) {
				//gp.gameState = GameState.playState;
			} else if(gp.ui.subState == 1 || gp.ui.subState == 2) {
				gp.ui.subState = 0;
			}
			break;
		case KeyEvent.VK_ENTER : enterPressed = true;break;
		case KeyEvent.VK_W :
		case KeyEvent.VK_UP :
			if(gp.ui.subState == 0) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			} else if(gp.ui.subState == 1) {
				npcInventory(code);
			} else if(gp.ui.subState == 2) {
				playerInventory(code);
			}
			break;
		case KeyEvent.VK_DOWN :
		case KeyEvent.VK_S :
			if(gp.ui.subState == 0) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			} else if(gp.ui.subState == 1) {
				npcInventory(code);
			} else if(gp.ui.subState == 2) {
				playerInventory(code);
			}
			break;
		case KeyEvent.VK_LEFT :
		case KeyEvent.VK_A :
			if(gp.ui.subState == 1) {
				npcInventory(code);
			} else if(gp.ui.subState == 2) {
				playerInventory(code);
			}
			break;
		case KeyEvent.VK_RIGHT :
		case KeyEvent.VK_D :
			if(gp.ui.subState == 1) {
				npcInventory(code);
			} else if(gp.ui.subState == 2) {
				playerInventory(code);
			}
			break;
		}
	}
	
	public void readingState(int code){
		switch (code) {
		case KeyEvent.VK_ESCAPE :
			if(gp.ui.openBook) {
				gp.ui.openBook = false;
			} else {
				gp.ui.resetSlots();
				gp.gameState = GameState.playState;
			}
			break;
		case KeyEvent.VK_ENTER:
			if(!gp.ui.openBook) {
				gp.playSE(10);
				gp.ui.openBook = true;
			}
			break;
		case KeyEvent.VK_UP :
		case KeyEvent.VK_W :
			if(!gp.ui.openBook) {
				gp.ui.playerSlotCol = getNext(gp.ui.playerSlotCol, gp.ui.selectedBookshelf.length - 1);
			}
			break;
		case KeyEvent.VK_DOWN :
		case KeyEvent.VK_S :
			if(!gp.ui.openBook) {
				gp.ui.playerSlotCol = getPrev(gp.ui.playerSlotCol, gp.ui.selectedBookshelf.length - 1);
			}
			break;
		default :
			if(gp.ui.openBook) {
				if (gp.ui.playerSlotRow != gp.ui.selectedBookshelf.length - 1) {
					gp.playSE(11);
					gp.ui.playerSlotRow++;
				} else {
					gp.ui.playerSlotRow = 0;
				}
				if(gp.ui.selectedBook.getContent()[gp.ui.playerSlotRow] == null) {
					gp.playSE(12);
					gp.ui.openBook = false;
					gp.ui.playerSlotRow = 0;
				}
			}
			break;
		}
	}
	
	public void combatState(int code) {
		if(gp.combat.getCombatant().getCombatStatus() == CombatStatus.Using) {
			//Use items in combat menu
			combatUsingState(code);
		} else if(gp.combat.getCombatant().getCombatStatus() == CombatStatus.Specializing) {
			//Special State
			combatSpecialState(code);
		} else {
			//Base combat menu
			combatMenuState(code);
		}
	}
	
	public void combatUsingState(int code) {
		switch (code) {
		case KeyEvent.VK_ESCAPE :
			gp.ui.resetSlots();
			gp.combat.getCombatant().setCombatStatus(CombatStatus.Normal);
			break;
		case KeyEvent.VK_UP :
		case KeyEvent.VK_W :
			gp.ui.choiceSlot--;
			if (gp.ui.choiceSlot < 0) {
				gp.ui.choiceSlot = gp.player.combatItems(gp.ui.itemFilter).size() - 1;
				if(gp.player.combatItems(gp.ui.itemFilter).size() > 1) {
					gp.ui.firstValue = gp.ui.choiceSlot -1;
				}
			}
			if (gp.ui.firstValue > gp.ui.choiceSlot) {
				gp.ui.firstValue = gp.ui.choiceSlot;
			}
			break;
		case KeyEvent.VK_DOWN :
		case KeyEvent.VK_S :
			if (gp.ui.choiceSlot > 0 && gp.ui.choiceSlot == gp.ui.firstValue + 1) {
				gp.ui.firstValue++;
			}
			gp.ui.choiceSlot++;
			if (gp.ui.choiceSlot > gp.player.combatItems(gp.ui.itemFilter).size() - 1) {
				gp.ui.choiceSlot = 0;
				gp.ui.firstValue = gp.ui.choiceSlot;
			}
			break;
		case KeyEvent.VK_LEFT :
		case KeyEvent.VK_A :
			if (gp.ui.itemFilter == null) {
				gp.ui.itemFilter = EntityType.Projectile;
			} else if (gp.ui.itemFilter == EntityType.Projectile) {
				gp.ui.itemFilter = EntityType.Health;
			} else if (gp.ui.itemFilter == EntityType.Health) {
				gp.ui.itemFilter = EntityType.Food;
			} else {
				gp.ui.itemFilter = null;
			}
			gp.ui.choiceSlot = 0;
			gp.ui.firstValue = gp.ui.choiceSlot;
			break;
		case KeyEvent.VK_RIGHT :
		case KeyEvent.VK_D :
			if (gp.ui.itemFilter == null) {
				gp.ui.itemFilter = EntityType.Food;
			} else if (gp.ui.itemFilter == EntityType.Food) {
				gp.ui.itemFilter = EntityType.Health;
			} else if (gp.ui.itemFilter == EntityType.Health) {
				gp.ui.itemFilter = EntityType.Projectile;
			} else {
				gp.ui.itemFilter = null;
			}
			gp.ui.choiceSlot = 0;
			gp.ui.firstValue = gp.ui.choiceSlot;
			break;
		case KeyEvent.VK_ENTER :
			gp.player.selectedItem();
			break;
		}
	}
	public void combatSpecialState(int code) {
		switch (code) {
		case KeyEvent.VK_ESCAPE:
			gp.ui.resetSlots();
			gp.combat.getCombatant().setCombatStatus(CombatStatus.Normal);
			break;
		case KeyEvent.VK_UP :
		case KeyEvent.VK_W :
			if (gp.ui.slotRow2 != 0 && gp.ui.slotCol2 != 1) {
				gp.ui.slotRow2--;
			} else if(gp.ui.slotCol2 != 1) {
				gp.ui.slotRow2 = 1;
			}
			break;
		case KeyEvent.VK_DOWN :
		case KeyEvent.VK_S :
			if (gp.ui.slotRow2 != 1 && gp.ui.slotCol2 != 1) {
				gp.ui.slotRow2++;
			} else if(gp.ui.slotCol2 != 1) {
				gp.ui.slotRow2 = 0;
			}
			break;
		case KeyEvent.VK_LEFT :
		case KeyEvent.VK_A :
			if (gp.ui.slotCol2 != 0 && gp.ui.slotRow2 != 1) {
				gp.ui.slotCol2--;
			} else if(gp.ui.slotRow2 != 1) {
				gp.ui.slotCol2 = 1;
			}
			break;
		case KeyEvent.VK_RIGHT :
		case KeyEvent.VK_D :
			if (gp.ui.slotCol2 != 1 && gp.ui.slotRow2 != 1) {
				gp.ui.slotCol2++;
			}  else if(gp.ui.slotRow2 != 1) {
				gp.ui.slotCol2 = 0;
			}
			break;
		case KeyEvent.VK_ENTER :
			if(gp.ui.slotCol2 == 0 && gp.ui.slotRow2 == 0) {
				System.out.println("O");
				gp.playable.get(0).getOffencive().activate();
			} else if(gp.ui.slotCol2 == 1 && gp.ui.slotRow2 == 0) {
				System.out.println("S");
				gp.playable.get(0).getSupportive().activate();
			} else if(gp.ui.slotCol2 == 0 && gp.ui.slotRow2 == 1) {
				System.out.println("Self");
				gp.playable.get(0).getSelfie().activate();
			}
			break;
		}
	}
	public void combatMenuState(int code) {
		switch (code) {
		case KeyEvent.VK_ENTER :
			if(gp.combat.getCombatant().isAlive()) {
				if(gp.combat.getCombatant() == gp.playable.get(0)) {
					enterPressed = true;
					if (gp.ui.commandNum == 0) {
						if(gp.combat.enemies.size() > 1) {
							gp.gameState = GameState.targetState;
						} else {
							gp.combat.dealDamage(gp.playable.get(0),gp.combat.getTarget(),gp.playable.get(0).getAttack());
						}
					} else if (gp.ui.commandNum == 1) {
						gp.combat.blockAttack();	
					} else if (gp.ui.commandNum == 2) {
						gp.combat.getCombatant().changePos();
						gp.combat.incrementTurn();
					} else if (gp.ui.commandNum == 3) {
						gp.playable.get(0).setCombatStatus(CombatStatus.Specializing);	
					} else if (gp.ui.commandNum == 4) {
						gp.playable.get(0).setCombatStatus(CombatStatus.Using);		
					} else if (gp.ui.commandNum == 5) {
						gp.combat.getCombatant().setCombatStatus(CombatStatus.Escaping);
					}
					break;
				}
				if(gp.combat.getCombatant().isDying()) {
					enterPressed = true;
					break;
				}
			}
		case KeyEvent.VK_LEFT :
		case KeyEvent.VK_A :
			leftPressed = true;
			if (gp.ui.playerSlotCol != 0) {
				gp.ui.playerSlotCol = gp.ui.playerSlotCol - 3;
				gp.ui.commandNum = gp.ui.commandNum - 2;
			} else {
				gp.ui.playerSlotCol = 6;
				gp.ui.commandNum = gp.ui.commandNum + 4;
			}
			break;
		case KeyEvent.VK_UP :
		case KeyEvent.VK_W :
			upPressed = true;
			if (gp.ui.playerSlotRow != 0) {
				gp.ui.playerSlotRow--;
				gp.ui.commandNum--;
			} else {
				gp.ui.playerSlotRow = 1;
				gp.ui.commandNum++;
			}
			break;
		case KeyEvent.VK_RIGHT :
		case KeyEvent.VK_D :
			rightPressed = true;
			if (gp.ui.playerSlotCol != 6) {
				gp.ui.playerSlotCol = gp.ui.playerSlotCol + 3;
				gp.ui.commandNum = gp.ui.commandNum + 2;
			} else {
				gp.ui.playerSlotCol = 0;
				gp.ui.commandNum = gp.ui.commandNum - 4;
			}
			break;
		case KeyEvent.VK_DOWN :
		case KeyEvent.VK_S :
			downPressed = true;
			if (gp.ui.playerSlotRow != 1) {
				gp.ui.playerSlotRow++;
				gp.ui.commandNum++;
			} else {
				gp.ui.playerSlotRow = 0;
				gp.ui.commandNum--;
			}
			break;
		}
	}
	
	public void targetState(int code) {
		switch (code) {
		case KeyEvent.VK_UP :
		case KeyEvent.VK_W :
			gp.ui.slotCol2 = getNext(gp.ui.slotCol2, gp.combat.enemies.size() - 1);
			if(!gp.combat.enemies.get(gp.ui.slotCol2).isAlive()) {
				gp.ui.slotCol2 = getNext(gp.ui.slotCol2, gp.combat.enemies.size() - 1);
			}
			break;
		case KeyEvent.VK_DOWN :
		case KeyEvent.VK_S :
			gp.ui.slotCol2 = getPrev(gp.ui.slotCol2, gp.combat.enemies.size() - 1);
			if(!gp.combat.enemies.get(gp.ui.slotCol2).isAlive()) {
				gp.ui.slotCol2 = getPrev(gp.ui.slotCol2, gp.combat.enemies.size() - 1);
			}
			break;
		case KeyEvent.VK_ENTER :
			gp.combat.dealDamage(gp.playable.get(0),gp.combat.enemies.get(gp.ui.slotCol2),gp.playable.get(0).getAttack());
			break;
		}
	}
	
	public void rewardState(int code) {
		switch (code) {
		case KeyEvent.VK_ENTER :
			gp.combat.enemies.get(0).combatResult();
			gp.combat.cleanup();
			break;
		}
	}
	
	public void gameOverState(int code) {
		switch (code) {
		case KeyEvent.VK_ENTER :
			gp.restart();
			gp.gameState = GameState.titleState;
			break;
		}
	}
	
	public void pauseState(int code) {
		switch (code) {
		default :
			gp.gameState = GameState.playState;
		}
	}
	
	public void playState(int code) {
		switch (code) {
		case KeyEvent.VK_ENTER :
			enterPressed = true;
			if (gp.selectedObj != null) {
				gp.obj[1][gp.selectedObj].interact();
			}
			break;
		case KeyEvent.VK_ESCAPE :
			gp.selectedObj = null;
			gp.gameState = GameState.menuState;
			break;
		case KeyEvent.VK_LEFT :
		case KeyEvent.VK_A :
			leftPressed = true;
			break;
		case KeyEvent.VK_UP :
		case KeyEvent.VK_W :
			upPressed = true;
			break;
		case KeyEvent.VK_RIGHT :
		case KeyEvent.VK_D :
			rightPressed = true;
			break;
		case KeyEvent.VK_DOWN :
		case KeyEvent.VK_S :
			downPressed = true;
			break;
		case KeyEvent.VK_SPACE:
			gp.gameState = GameState.pauseState;
		case 82 :
			switch(gp.currentMap.getId())
			{
			case 0:
				gp.tileM.loadMap("/res/maps/map01",0);
				break;
			case 1:
				gp.tileM.loadMap("/res/maps/map02",1);
				break;
			}

			break;
		case KeyEvent.VK_T :
			if (!showDebugText) {
				showDebugText = true;
			} else if (showDebugText) {
				showDebugText = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_ENTER :
				enterPressed = false;
				break;
			case KeyEvent.VK_LEFT :
			case KeyEvent.VK_A :
				leftPressed = false;
				break;
			case KeyEvent.VK_UP :
			case KeyEvent.VK_W :
				upPressed = false;
				break;
			case KeyEvent.VK_RIGHT :
			case KeyEvent.VK_D :
				rightPressed = false;
				break;
			case KeyEvent.VK_DOWN :
			case KeyEvent.VK_S :
				downPressed = false;
			case KeyEvent.VK_Q :
				skipPressed = false;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public int getNext(int current, int size) {
		if (current != 0) {
			--current;
		} else {
			current = size;
		}

		return current;
	}

	public int getPrev(int current, int size) {
		if (current != size) {
			++current;
		} else {
			current = 0;
		}

		return current;
	}
}