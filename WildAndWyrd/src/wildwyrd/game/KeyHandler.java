package wildwyrd.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
			if (code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if (gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}

			if (code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if (gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}

			if (code == KeyEvent.VK_ENTER) {
				if (this.gp.ui.commandNum == 0) {
					gp.gameState = GameState.playState;
				}

				//gameState = this.gp.ui.commandNum;
				if (this.gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
		} else if (gp.gameState == GameState.menuState) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_ENTER :
					if (gp.ui.slotCol == 1) {
						gp.gameState = GameState.statusState;
						gp.ui.drawStatusScreen();
					} else if (gp.ui.slotCol == 2) {
						gp.gameState = GameState.inventoryState;
						gp.ui.drawInventoryScreen();
					} else if (gp.ui.slotCol == 3) {
						gp.ui.resetSlots();
						gp.gameState = GameState.equipState;
						gp.ui.drawEquipScreen();
					} else if (gp.ui.slotCol == 6) {
						gp.ui.resetSlots();
						gp.gameState = GameState.glossaryState;
						gp.ui.drawGlossaryScreen();
					} else if (gp.ui.slotCol == 7) {
						gp.currentRoom = 0;
						gp.gameState = GameState.titleState;
					}
					break;
				case KeyEvent.VK_ESCAPE :
					gp.gameState = GameState.playState;
					break;
				case KeyEvent.VK_UP :
				case KeyEvent.VK_W :
					if (this.gp.ui.slotCol != 0) {
						--this.gp.ui.slotCol;
					} else {
						this.gp.ui.slotCol = 7;
					}
					break;
				case KeyEvent.VK_DOWN :
				case KeyEvent.VK_S :
					if (this.gp.ui.slotCol != 7) {
						++this.gp.ui.slotCol;
					} else {
						this.gp.ui.slotCol = 0;
					}
			}
		} else if (gp.gameState == GameState.statusState) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE :
					gp.ui.resetSlots();
					gp.gameState = GameState.menuState;
					break;
				case KeyEvent.VK_UP :
				case KeyEvent.VK_W :
					if (this.gp.ui.slotRow2 != 0) {
						--this.gp.ui.slotRow2;
					} else {
						this.gp.ui.slotRow2 = 3;
					}
					break;
				case KeyEvent.VK_DOWN :
				case KeyEvent.VK_S :
					if (this.gp.ui.slotRow2 != 3) {
						++this.gp.ui.slotRow2;
					} else {
						this.gp.ui.slotRow2 = 0;
					}
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
				if (gp.ui.slotCol != 0) {
					gp.ui.slotCol--;
				} else {
					gp.ui.slotCol = 4;
				}
				break;
			case KeyEvent.VK_DOWN :
			case KeyEvent.VK_S :
				if (gp.ui.slotCol != 4) {
					gp.ui.slotCol++;
				} else {
					gp.ui.slotCol = 0;
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
						//System.out.println(this.gp.glossary.getSize(this.gp.ui.section) + " "
						//		+ this.gp.glossary.getSize(this.gp.ui.section - 1));
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
					//System.out.println(this.gp.ui.topValue + " " + this.gp.ui.slotCol);
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
						//System.out.println(
						//		this.gp.ui.slotCol + this.gp.ui.topValue + " " + this.gp.ui.bottomValue);
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
						gp.ui.slotRow++;
					} else {
						gp.ui.slotRow = 0;
					}
					if(gp.ui.selectedBook.getContent()[gp.ui.slotRow] == null) {
						gp.ui.openBook = false;
						gp.ui.slotRow = 0;
					}
				}
				break;
			}
		} else if (gp.gameState == GameState.combatState) {
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
						}
						if (gp.ui.commandNum == 1) {
							gp.combat.blockAttack();	
						}
						if (gp.ui.commandNum == 5) {
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

		if (code == KeyEvent.VK_S) {
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