package wildwyrd.game.npc;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.combat.En_Cricket;
import wildwyrd.game.combat.En_Wasp;
import wildwyrd.game.object.Dialoge;

public class NPC_Cricket extends NPC {
	public static final int npcId = 4;
	public static final String npcName = "Cricket";
	public NPC_Cricket(GamePanel gp) {
		super(gp);
		id = npcId;
		name = npcName;
		type = EntityType.Sprite;
		direction = "down";
		speed = 1;
		skippable = false;
		setDialogue();
	}

	public BufferedImage getSpriteSheet() {
		BufferedImage sprite = null;

		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/res/sprite/EnemySprites.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sprite;
	}

	public BufferedImage getPlayerImage(int xGrid, int yGrid) {
		if (image == null) {
			image = getSpriteSheet();
		}
		return image.getSubimage(xGrid * gp.tileSize, yGrid * gp.tileSize, gp.tileSize, gp.tileSize);
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		// STOP MOVING CAMERA
		if (gp.player.worldY < gp.player.screenY) {
			screenY = worldY;
		}
		int rightOffset = gp.screenWidth - gp.player.screenX;
		if (rightOffset > gp.currentMap.getWorldWidth() - gp.player.worldX) {
			screenX = gp.screenWidth - (gp.currentMap.getWorldWidth() - worldX);
		}
		int bottomOffset = gp.screenHeight - gp.player.screenY;
		if (bottomOffset > gp.currentMap.getWorldHeight() - gp.player.worldY) {
			screenY = gp.screenHeight - (gp.currentMap.getWorldHeight() - worldY);
		}
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			switch(direction) {
			case "up":
				if(spriteNum == 1) {
					image = getPlayerImage(7, 3);
				} else if(spriteNum == 2) {
					image = getPlayerImage(6, 3);
				} else if(spriteNum == 3) {
					image = getPlayerImage(7, 3);
				} else if(spriteNum == 4) {
					image = getPlayerImage(8, 3);
				}
				break;
			case "down":
				if(spriteNum == 1) {
					image = getPlayerImage(1, 0);
				} else if(spriteNum == 2) {
					image = getPlayerImage(0, 0);
				} else if(spriteNum == 3) {
					image = getPlayerImage(1, 0);
				} else if(spriteNum == 4) {
					image = getPlayerImage(2, 0);
				}
				break;
			case "left":
				if(spriteNum == 1) {
					image = getPlayerImage(7, 1);
				} else if(spriteNum == 2) {
					image = getPlayerImage(6, 1);
				} else if(spriteNum == 3) {
					image = getPlayerImage(7, 1);
				} else if(spriteNum == 4) {
					image = getPlayerImage(8, 1);
				}
				break;
			case "right":
				if(spriteNum == 1) {
					image = getPlayerImage(7, 2);
				} else if(spriteNum == 2) {
					image = getPlayerImage(6, 2);
				} else if(spriteNum == 3) {
					image = getPlayerImage(7, 2);
				} else if(spriteNum == 4) {
					image = getPlayerImage(8, 2);
				}
				break;
			}
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}else if(gp.player.worldX < gp.player.screenX ||
			    gp.player.worldY < gp.player.screenY ||
			    rightOffset > gp.currentMap.getWorldWidth() - gp.player.worldX ||
			    bottomOffset > gp.currentMap.getWorldHeight() - gp.player.worldY) {
			image = getPlayerImage(1, 0);
		}
	}
	
	public void setAction() {
		
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("Some crickets were in the area." ,1);
		dialogues[0][1] = new Dialoge("Fight Crickets?" ,2);
		dialogues[1][0] = new Dialoge("Hunt succesful.",1);
		dialogues[1][1] = new Dialoge("Wasp","Bzz!",1);
		dialogues[1][2] = new Dialoge("As Alder collected the slain cricket, a loud buzzing came at him from his side. Two hornets came at him.",1);
		dialogues[1][3] = new Dialoge("Alder","Ahhh!",port.image_Alder);
		dialogues[1][4] = new Dialoge("",4);
		dialogues[2][0] = new Dialoge("Alder","No, come back!",port.image_Alder);
		dialogues[2][1] = new Dialoge("Alder tried in vain to get the cricket, but it had already jumped out of reach.",1);
		dialogues[2][2] = new Dialoge("Wasp","Bzz!",1);
		dialogues[2][3] = new Dialoge("The loud buzzing of insect wings came from Alder's side. Two wasps came at him.",1);
		dialogues[2][4] = new Dialoge("Alder","Ahhh!",port.image_Alder);
		dialogues[2][5] = new Dialoge("",4);
	}
	
	public void combatResponce() {
		gp.combat.addEnemy(new En_Wasp(gp), new En_Wasp(gp));
		dialogueIndex = 0;
		if(gp.combat.win) {
			gp.glossary.unlock("invertebrates", "cricket");
			gp.glossary.unlock("invertebrates", "wasp");
			startDialogue(this, 1);
		} else {
			startDialogue(this, 2);
		}
		for (int i = 0; i < gp.npc[gp.currentMap.getId()].length; ++i) {
			if(gp.npc[gp.currentMap.getId()][i].id == npcId) {
				gp.npc[gp.currentMap.getId()][i] = null;
				break;
			}
		}
		gp.keyH.enterPressed = false;
		//gp.combat.startCombat();
	}

	public void choiceResponce() {
		if (gp.ui.choiceSlot == 0) {
			gp.combat.addEnemy(new En_Cricket(gp));
			gp.combat.startCombat();
			//gp.gameState = GameState.combatState;
		}

	}
	
	public void speak() {
		gp.ui.choiceSlot = 0;
		gp.ui.firstValue = 0;
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}
