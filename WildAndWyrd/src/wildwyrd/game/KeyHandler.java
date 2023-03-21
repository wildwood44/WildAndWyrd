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
		this.gp.getClass();
		GamePanel var3;
		if (gameState == GameState.titleState) {
			if (code == 87) {
				--this.gp.ui.commandNum;
				if (this.gp.ui.commandNum < 0) {
					this.gp.ui.commandNum = 2;
				}
			}

			if (code == 83) {
				++this.gp.ui.commandNum;
				if (this.gp.ui.commandNum > 2) {
					this.gp.ui.commandNum = 0;
				}
			}

			if (code == 10) {
				if (this.gp.ui.commandNum == 0) {
					var3 = this.gp;
					var3.gameState = GameState.playState;
				}

				//gameState = this.gp.ui.commandNum;
				if (this.gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
		} else {
			gameState = this.gp.gameState;
			if (gameState == GameState.menuState) {
				switch (e.getKeyCode()) {
					case 10 :
						if (this.gp.ui.slotCol == 2) {
							var3 = this.gp;
							var3.gameState = GameState.inventoryState;
							this.gp.ui.drawInventoryScreen();
						} else if (this.gp.ui.slotCol == 6) {
							this.gp.ui.resetSlots();
							var3 = this.gp;
							var3.gameState = GameState.glossaryState;
							this.gp.ui.drawGlossaryScreen();
						} else if (this.gp.ui.slotCol == 7) {
							this.gp.currentRoom = 0;
							var3 = this.gp;
							var3.gameState = GameState.titleState;
						}
						break;
					case 27 :
						var3 = this.gp;
						var3.gameState = GameState.playState;
						break;
					case 38 :
					case 87 :
						if (this.gp.ui.slotCol != 0) {
							--this.gp.ui.slotCol;
						} else {
							this.gp.ui.slotCol = 7;
						}
						break;
					case 40 :
					case 83 :
						if (this.gp.ui.slotCol != 7) {
							++this.gp.ui.slotCol;
						} else {
							this.gp.ui.slotCol = 0;
						}
				}
			} else {
				gameState = this.gp.gameState;
				if (gameState == GameState.inventoryState) {
					switch (e.getKeyCode()) {
						case 27 :
							this.gp.ui.resetSlots();
							var3 = this.gp;
							this.gp.getClass();
							var3.gameState = GameState.menuState;
							break;
						case 37 :
						case 65 :
							if (this.gp.ui.slotCol2 != 0) {
								--this.gp.ui.slotCol2;
							} else {
								this.gp.ui.slotCol2 = 6;
							}
							break;
						case 38 :
						case 87 :
							if (this.gp.ui.slotRow2 != 0) {
								--this.gp.ui.slotRow2;
							} else {
								this.gp.ui.slotRow2 = 3;
							}
							break;
						case 39 :
						case 68 :
							if (this.gp.ui.slotCol2 != 6) {
								++this.gp.ui.slotCol2;
							} else {
								this.gp.ui.slotCol2 = 0;
							}
							break;
						case 40 :
						case 83 :
							if (this.gp.ui.slotRow2 != 3) {
								++this.gp.ui.slotRow2;
							} else {
								this.gp.ui.slotRow2 = 0;
							}
					}
				} else {
					gameState = gp.gameState;
					if (gameState == GameState.glossaryState) {
						switch (e.getKeyCode()) {
							case 27 :
								this.gp.ui.resetSlots();
								var3 = this.gp;
								this.gp.getClass();
								var3.gameState = GameState.menuState;
								break;
							case 37 :
							case 65 :
								if (this.gp.ui.section != 0) {
									//System.out.println(this.gp.glossary.getSize(this.gp.ui.section) + " "
									//		+ this.gp.glossary.getSize(this.gp.ui.section - 1));
									if (this.gp.glossary.getSize(this.gp.ui.section) > this.gp.glossary
											.getSize(this.gp.ui.section - 1)) {
										this.gp.ui.slotCol = 0;
										this.gp.ui.topValue = 0;
									}

									--this.gp.ui.section;
								} else {
									if (this.gp.glossary.getSize(this.gp.ui.section) > this.gp.glossary
											.getSize(this.gp.glossary.sections.length - 1)) {
										this.gp.ui.slotCol = 0;
										this.gp.ui.topValue = 0;
									}

									this.gp.ui.section = this.gp.glossary.sections.length - 1;
								}
								break;
							case 38 :
							case 87 :
								//System.out.println(this.gp.ui.topValue + " " + this.gp.ui.slotCol);
								if (this.gp.ui.slotCol != 0) {
									--this.gp.ui.slotCol;
								} else if (this.gp.ui.topValue != 0) {
									--this.gp.ui.topValue;
								} else {
									this.gp.ui.topValue = this.gp.ui.bottomValue - 6;
									this.gp.ui.slotCol = 5;
								}
								break;
							case 39 :
							case 68 :
								if (this.gp.ui.section < this.gp.glossary.sections.length - 1) {
									if (this.gp.glossary.getSize(this.gp.ui.section) > this.gp.glossary
											.getSize(this.gp.ui.section + 1)) {
										this.gp.ui.slotCol = 0;
										this.gp.ui.topValue = 0;
									}

									++this.gp.ui.section;
								} else {
									if (this.gp.glossary.getSize(this.gp.ui.section) > this.gp.glossary.getSize(0)) {
										this.gp.ui.slotCol = 0;
										this.gp.ui.topValue = 0;
									}

									this.gp.ui.section = 0;
								}
								break;
							case 40 :
							case 83 :
								if (this.gp.ui.slotCol != 5) {
									++this.gp.ui.slotCol;
								} else {
									//System.out.println(
									//		this.gp.ui.slotCol + this.gp.ui.topValue + " " + this.gp.ui.bottomValue);
									if (this.gp.ui.slotCol + this.gp.ui.topValue != this.gp.ui.bottomValue - 1) {
										++this.gp.ui.topValue;
									} else {
										this.gp.ui.slotCol = 0;
										this.gp.ui.topValue = 0;
									}
								}
						}
					} else {
						gameState = this.gp.gameState;
						if (gameState == GameState.playState) {
							switch (e.getKeyCode()) {
								case 10 :
									this.enterPressed = true;
									if (this.gp.selectedObj != null) {
										this.gp.obj[1][this.gp.selectedObj].interact();
									}
									break;
								case 27 :
									this.gp.selectedObj = null;
									var3 = this.gp;
									this.gp.getClass();
									var3.gameState = GameState.menuState;
									break;
								case 37 :
								case 65 :
									this.leftPressed = true;
									break;
								case 38 :
								case 87 :
									this.upPressed = true;
									break;
								case 39 :
								case 68 :
									this.rightPressed = true;
									break;
								case 40 :
								case 83 :
									this.downPressed = true;
									break;
								case 82 :
									switch(gp.currentMap.getId())
									{
									case 0:
										this.gp.tileM.loadMap("/res/maps/map01",0);
										break;
									case 1:
										this.gp.tileM.loadMap("/res/maps/map02",1);
										break;
									}

									break;
								case 84 :
									if (!this.showDebugText) {
										this.showDebugText = true;
									} else if (this.showDebugText) {
										this.showDebugText = false;
									}
							}
						} else {
							gameState = this.gp.gameState;
							if (gameState != GameState.dialogueState) {
								gameState = this.gp.gameState;
								if (gameState != GameState.cutsceneState) {
									gameState = this.gp.gameState;
									if (gameState != GameState.messageState) {
										gameState = this.gp.gameState;
										if (gameState != GameState.examineState) {
											return;
										}
									}
								}
							}

							this.dialogueState(code);
						}
					}
				}
			}
		}

	}

	public void dialogueState(int code) {
		if (code == 10) {
			this.enterPressed = true;
		}

		if (code == 83) {
			this.skipPressed = true;
		}

		if (code == 38 || code == 87) {
			--this.gp.ui.slotyn;
			if (this.gp.ui.slotyn < 0) {
				this.gp.ui.slotyn = 1;
			}
		}

		if (code == 40 || code == 83) {
			++this.gp.ui.slotyn;
			if (this.gp.ui.slotyn > 1) {
				this.gp.ui.slotyn = 0;
			}
		}

		if (code == 84) {
			if (!this.showDebugText) {
				this.showDebugText = true;
			} else if (this.showDebugText) {
				this.showDebugText = false;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case 10 :
				this.enterPressed = false;
				break;
			case 37 :
			case 65 :
				this.leftPressed = false;
				break;
			case 38 :
			case 87 :
				this.upPressed = false;
				break;
			case 39 :
			case 68 :
				this.rightPressed = false;
				break;
			case 40 :
			case 83 :
				this.downPressed = false;
				this.skipPressed = false;
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