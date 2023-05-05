package wildwyrd.npc;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.object.Dialoge;

public class NPC_Florence extends Entity {
	public NPC_Florence(GamePanel gp) {
		super(gp);
		//this.gp = gp;
		options = new String[2];
		contConditions = new boolean[2];
		name = "Florence";
		type = EntityType.Sprite;
		direction = "down";
		speed = 1;
		contConditions[0] = false;
		contConditions[1] = false;
		setDialogue();
		setDialogueOptions();
	}

	public BufferedImage getSpriteSheet() {
		BufferedImage sprite = null;

		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/res/sprite/WildWyrdSprites.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sprite;
	}

	public BufferedImage getPlayerImage(int xGrid, int yGrid) {
		if (image == null) {
			image = getSpriteSheet();
		}
		return image.getSubimage(xGrid * 48, yGrid * 48, 48, 48);
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

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
					image = getPlayerImage(7, 0);
				} else if(spriteNum == 2) {
					image = getPlayerImage(6, 0);
				} else if(spriteNum == 3) {
					image = getPlayerImage(7, 0);
				} else if(spriteNum == 4) {
					image = getPlayerImage(8, 0);
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
		}
	}
	
	public void setAction() {
		
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(" " ,3);
		dialogues[1][0] = new Dialoge("Florence", "Potions probably.", 1);
		dialogues[1][1] = new Dialoge("Florence", "He usually comes here for sanctuary or :potions.", 1);
		dialogues[2][0] = new Dialoge("Florence", "Well.", 1);
		dialogues[2][1] = new Dialoge("Florence", "Kyla’s cast several illusions on the :cottage, one of which makes it looks like a :boulder from the outside.", 1);
		dialogues[2][2] = new Dialoge("Florence", "She's also muted the rooms and made our :scents smell somewhat grassy.", 1);
		dialogues[2][3] = new Dialoge("Florence", "This place cannot be seen from the outside :world, so we tend to call it the burrow.", 1);
	}
	
	public void setDialogueOptions() {
		options[0] = "So what's Thay here for?";
		options[1] = "How does the magic on cottage work?";
	}
	
	public void checkConditions() {

		if (dialogues[dialogueSet][dialogueIndex] == null) {
			for (boolean checkCondition: contConditions) {
				if(checkCondition == false) {
					dialogueIndex = 0;
					speak();
				}
			}
		}
	}
	
	public void choiceResponce() {
		if (gp.ui.choiceSlot == 0) {
			startDialogue(this, 1);
			contConditions[0] = true;
		}
		if (gp.ui.choiceSlot == 1) {
			startDialogue(this, 2);
			contConditions[1] = true;
		}
	}
	
	public void speak() {
		facePlayer();
		gp.ui.choiceSlot = 0;
		gp.ui.firstValue = 0;
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}
