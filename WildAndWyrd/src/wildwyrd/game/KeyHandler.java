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
			if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if (gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}

			if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if (gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}

			if (code == KeyEvent.VK_ENTER) {
				if (gp.ui.commandNum == 0) {
					//gp = new GamePanel();
					gp.restart();
					gp.gameState = GameState.playState;
				}
				if (gp.ui.commandNum == 1) {
					gp.saveLoad.load();
					gp.ui.resetSlots();
					gp.tileM = new TileManager(gp);
					gp.eHandler = new EventHandler(gp);
					gp.gameState = GameState.playState;
				}
				if (gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
		} else if (gp.gameState == GameState.menuState) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_ENTER :
					if (gp.ui.slotCol == 0) { //Save game
						gp.gameState = GameState.saveState;
						gp.playSE(0);
						gp.saveLoad.save();
					} else if (gp.ui.slotCol == 1) { //Open status screen
						gp.gameState = GameState.statusState;
						gp.ui.drawStatusScreen();
					} else if (gp.ui.slotCol == 2) { //Open inventory screen
						gp.gameState = GameState.inventoryState;
						gp.ui.drawInventoryScreen();
					} else if (gp.ui.slotCol == 3) { //Open objectives screen
						gp.ui.resetSlots();
						gp.gameState = GameState.objectiveState;
						gp.ui.drawObjectiveScreen();
					} else if (gp.ui.slotCol == 4) { //Open glossary screen
						gp.ui.resetSlots();
						gp.gameState = GameState.glossaryState;
						gp.ui.drawGlossaryScreen();
					} else if (gp.ui.slotCol == 5) { //Quit game
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
					if (gp.ui.slotCol != 0) {
						--gp.ui.slotCol;
					} else {
						gp.ui.slotCol = 5;
					}
					break;
				case KeyEvent.VK_DOWN :
				case KeyEvent.VK_S :
					if (gp.ui.slotCol != 5) {
						++gp.ui.slotCol;
					} else {
						gp.ui.slotCol = 0;
					}
			}
		} else if (gp.gameState == GameState.saveState) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_ENTER :
				gp.gameState = GameState.playState;
				break;
			}
		} else if (gp.gameState == GameState.statusState) {
			switch (e.getKeyCode()) {
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
		} else if (gameState == GameState.inventoryState) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE :
					gp.ui.resetSlots();
					gp.gameState = GameState.menuState;
					break;
				case KeyEvent.VK_LEFT :
				case KeyEvent.VK_A :
					gp.ui.slotCol2 = getNext(gp.ui.slotCol2, 6);
					break;
				case KeyEvent.VK_UP :
				case KeyEvent.VK_W :
					gp.ui.slotRow2 = getNext(gp.ui.slotRow2, 3);
					break;
				case KeyEvent.VK_RIGHT :
				case KeyEvent.VK_D :
					gp.ui.slotCol2 = getPrev(gp.ui.slotCol2, 6);
					break;
				case KeyEvent.VK_DOWN :
				case KeyEvent.VK_S :
					gp.ui.slotRow2 = getPrev(gp.ui.slotRow2, 3);
					break;
				case KeyEvent.VK_ENTER :
					gp.player.selectedItem();
					break;
			}
		} else if (gp.gameState == GameState.equipState) {
			switch (e.getKeyCode()) {
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
		}  else if (gp.gameState == GameState.skillState) {
			switch (e.getKeyCode()) {
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
	} else if (gp.gameState == GameState.objectiveState) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE :
				gp.ui.resetSlots();
				gp.gameState = GameState.menuState;
				break;
			case KeyEvent.VK_UP :
			case KeyEvent.VK_W :
				if (gp.ui.slotCol != 0) {
					gp.ui.slotCol--;
				} else if (gp.ui.topValue != 0) {
					gp.ui.topValue--;
				} else {
					gp.ui.topValue = gp.ui.bottomValue - 6;
					gp.ui.slotCol = 5;
				}
				break;
			case KeyEvent.VK_DOWN :
			case KeyEvent.VK_S :
				if (gp.ui.slotCol != 5) {
					gp.ui.slotCol++;
				} else {
					if (gp.ui.slotCol + gp.ui.topValue != gp.ui.bottomValue - 1) {
						gp.ui.topValue++;
					} else {
						gp.ui.slotCol = 0;
						gp.ui.topValue = 0;
					}
				}
			}
		} else if (gp.gameState == GameState.glossaryState) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE :
					gp.ui.resetSlots();
					gp.gameState = GameState.menuState;
					break;
				case KeyEvent.VK_LEFT :
				case KeyEvent.VK_A :
					if (gp.ui.section != 0) {
						if (gp.glossary.getSize(gp.ui.section) > gp.glossary
								.getSize(gp.ui.section - 1)) {
							gp.ui.slotCol = 0;
							gp.ui.topValue = 0;
						}

						gp.ui.section--;
					} else {
						if (gp.glossary.getSize(gp.ui.section) > gp.glossary
								.getSize(gp.glossary.sections.length - 1)) {
							gp.ui.slotCol = 0;
							gp.ui.topValue = 0;
						}

						gp.ui.section = gp.glossary.sections.length - 1;
					}
					break;
				case KeyEvent.VK_UP :
				case KeyEvent.VK_W :
					if (gp.ui.slotCol != 0) {
						gp.ui.slotCol--;
					} else if (gp.ui.topValue != 0) {
						gp.ui.topValue--;
					} else {
						gp.ui.topValue = gp.ui.bottomValue - 6;
						gp.ui.slotCol = 5;
					}
					break;
				case KeyEvent.VK_RIGHT :
				case KeyEvent.VK_D :
					if (gp.ui.section < gp.glossary.sections.length - 1) {
						if (gp.glossary.getSize(gp.ui.section) > gp.glossary
								.getSize(gp.ui.section + 1)) {
							gp.ui.slotCol = 0;
							gp.ui.topValue = 0;
							}
						gp.ui.section++;
					} else {
						if (gp.glossary.getSize(gp.ui.section) > gp.glossary.getSize(0)) {
							gp.ui.slotCol = 0;
							gp.ui.topValue = 0;
						}
						gp.ui.section = 0;
					}
					break;
				case KeyEvent.VK_DOWN :
				case KeyEvent.VK_S :
					if (gp.ui.slotCol != 5) {
						gp.ui.slotCol++;
					} else {
						if (gp.ui.slotCol + gp.ui.topValue != gp.ui.bottomValue - 1) {
							gp.ui.topValue++;
						} else {
							gp.ui.slotCol = 0;
							gp.ui.topValue = 0;
						}
					}
				}
		} else if (gp.gameState == GameState.readingState) {
			switch (e.getKeyCode()) {
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
					gp.ui.slotCol = getNext(gp.ui.slotCol, gp.ui.selectedBookshelf.length - 1);
				}
				break;
			case KeyEvent.VK_DOWN :
			case KeyEvent.VK_S :
				if(!gp.ui.openBook) {
					gp.ui.slotCol = getPrev(gp.ui.slotCol, gp.ui.selectedBookshelf.length - 1);
				}
				break;
			default :
				if(gp.ui.openBook) {
					if (gp.ui.slotRow != gp.ui.selectedBookshelf.length - 1) {
						gp.playSE(11);
						gp.ui.slotRow++;
					} else {
						gp.ui.slotRow = 0;
					}
					if(gp.ui.selectedBook.getContent()[gp.ui.slotRow] == null) {
						gp.playSE(12);
						gp.ui.openBook = false;
						gp.ui.slotRow = 0;
					}
				}
				break;
			}
		} else if (gp.gameState == GameState.combatState) {
			//Use items in combat menu
			if(gp.combat.getCombatant().getCombatStatus() == CombatStatus.Using) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE :
					gp.ui.resetSlots();
					gp.combat.getCombatant().setCombatStatus(CombatStatus.Normal);
					break;
				case KeyEvent.VK_UP :
				case KeyEvent.VK_W :
					//gp.ui.slotCol2 = getNext(gp.ui.slotCol2, 1);
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
				}} else if(gp.combat.getCombatant().getCombatStatus() == CombatStatus.Specializing) {
					//Special State
					switch (e.getKeyCode()) {
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
				}} else {
					//Base combat menu
				switch (e.getKeyCode()) {
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
								gp.combat.openSpecial();	
							} else if (gp.ui.commandNum == 4) {
								gp.combat.openInventory();	
							} else if (gp.ui.commandNum == 5) {
								gp.combat.endCombat();
								gp.combat.win = false;
								gp.gameState = GameState.playState;	
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
					if (gp.ui.slotCol != 0) {
						gp.ui.slotCol = gp.ui.slotCol - 3;
						gp.ui.commandNum = gp.ui.commandNum - 2;
					} else {
						gp.ui.slotCol = 6;
						gp.ui.commandNum = gp.ui.commandNum + 4;
					}
					break;
				case KeyEvent.VK_UP :
				case KeyEvent.VK_W :
					upPressed = true;
					if (gp.ui.slotRow != 0) {
						gp.ui.slotRow--;
						gp.ui.commandNum--;
					} else {
						gp.ui.slotRow = 1;
						gp.ui.commandNum++;
					}
					break;
				case KeyEvent.VK_RIGHT :
				case KeyEvent.VK_D :
					rightPressed = true;
					if (gp.ui.slotCol != 6) {
						gp.ui.slotCol = gp.ui.slotCol + 3;
						gp.ui.commandNum = gp.ui.commandNum + 2;
					} else {
						gp.ui.slotCol = 0;
						gp.ui.commandNum = gp.ui.commandNum - 4;
					}
					break;
				case KeyEvent.VK_DOWN :
				case KeyEvent.VK_S :
					downPressed = true;
					if (gp.ui.slotRow != 1) {
						gp.ui.slotRow++;
						gp.ui.commandNum++;
					} else {
						gp.ui.slotRow = 0;
						gp.ui.commandNum--;
					}
					break;
				}
			}
		} else if (gp.gameState == GameState.targetState) {
			switch (e.getKeyCode()) {
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
		} else if (gp.gameState == GameState.rewardState) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_ENTER :
				gp.combat.enemies.get(0).combatResult();
				gp.combat.cleanup();
				break;
			}
		} else if (gp.gameState == GameState.gameOverState) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_ENTER :
				gp.restart();
				gp.gameState = GameState.titleState;
				break;
			}
		} else if (gp.gameState == GameState.playState) {
			switch (e.getKeyCode()) {
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
				case 84 :
					if (!showDebugText) {
						showDebugText = true;
					} else if (showDebugText) {
						showDebugText = false;
					}
			}
		} else if (gp.gameState == GameState.dialogueState ||
			gp.gameState == GameState.cutsceneState ||
			gp.gameState == GameState.messageState ||
			gp.gameState == GameState.examineState) {
				dialogueState(code);
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