package wildwyrd.game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import wildwyrd.game.object.Dialoge;

public class Entity {
	public int speed;
	public BufferedImage image;
	public GamePanel gp;
	public int x;
	public int y;
	public int width;
	public int height;
	public String name;
	public int worldX;
	public int worldY;
	public Boolean selected;
	public Dialoge[][] dialogues = new Dialoge[5][20];
	public int dialogueSet = 0;
	public int dialogueIndex = 0;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	public int attackValue;
	public int defenceValue;
	public String description = "";
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public boolean collision = false;
	public boolean collisionOn = false;
	public int type;
	public final int type_player = 0;
	public final int type_object = 1;
	public final int type_npc = 2;
	public final int type_obstacle = 3;

	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public void update() {
		this.collisionOn = false;
	}

	public int getLeftX() {
		return this.worldX + this.solidArea.x;
	}

	public int getRightX() {
		return this.worldX + this.solidArea.x + this.solidArea.width;
	}

	public int getTopY() {
		return this.worldY + this.solidArea.y;
	}

	public int getBottomY() {
		return this.worldY + this.solidArea.y + this.solidArea.height;
	}

	public int getCol() {
		int var10000 = this.worldX + this.solidArea.x;
		this.gp.getClass();
		return var10000 / 64;
	}

	public int getRow() {
		int var10000 = this.worldY + this.solidArea.y;
		this.gp.getClass();
		return var10000 / 64;
	}

	public void draw(Graphics2D g2, GamePanel gp) {
		int screenX = this.worldX - gp.player.worldX + gp.player.screenX;
		int screenY = this.worldY - gp.player.worldY + gp.player.screenY;
		if (gp.player.worldX < gp.player.screenX) {
			screenX = this.worldX;
		}

		if (gp.player.worldY < gp.player.screenY) {
			screenY = this.worldY;
		}

		gp.getClass();
		int rightOffset = 768 - gp.player.screenX;
		gp.getClass();
		if (rightOffset > 1664 - gp.player.worldX) {
			gp.getClass();
			gp.getClass();
			screenX = 768 - (1664 - this.worldX);
		}

		gp.getClass();
		int bottomOffset = 512 - gp.player.screenY;
		gp.getClass();
		if (bottomOffset > 768 - gp.player.worldY) {
			gp.getClass();
			gp.getClass();
			screenY = 512 - (768 - this.worldY);
		}

		int var10000 = this.worldX;
		gp.getClass();
		if (var10000 + 64 > gp.player.worldX - gp.player.screenX) {
			var10000 = this.worldX;
			gp.getClass();
			if (var10000 - 64 < gp.player.worldX + gp.player.screenX) {
				var10000 = this.worldY;
				gp.getClass();
				if (var10000 + 64 > gp.player.worldY - gp.player.screenY) {
					var10000 = this.worldY;
					gp.getClass();
					if (var10000 - 64 < gp.player.worldY + gp.player.screenY) {
						BufferedImage var10001 = this.image;
						gp.getClass();
						gp.getClass();
						g2.drawImage(var10001, screenX, screenY, 64, 64, (ImageObserver) null);
					}
				}
			}
		}

	}

	public void startDialogue(Entity object, int setNum) {
		GamePanel var10000 = this.gp;
		this.gp.getClass();
		var10000.gameState = 7;
		this.gp.ui.selectedObject = object;
		this.dialogueSet = setNum;
	}

	public void interact() {
	}

	public int getDetected(Entity user, Entity[][] target, String targetName) {
		int index = 999;
		int nextWorldX = user.getLeftX();
		int nextWorldY = user.getTopY();
		String var7 = user.direction;
		switch (user.direction.hashCode()) {
			case -1383228885 :
				if (var7.equals("bottom")) {
					nextWorldY = user.getBottomY() + 1;
				}
				break;
			case 3739 :
				if (var7.equals("up")) {
					nextWorldY = user.getTopY() - 1;
				}
				break;
			case 3317767 :
				if (var7.equals("left")) {
					nextWorldX = user.getLeftX() - 1;
				}
				break;
			case 108511772 :
				if (var7.equals("right")) {
					nextWorldX = user.getRightX() + 1;
				}
		}

		this.gp.getClass();
		int col = nextWorldX / 64;
		this.gp.getClass();
		int row = nextWorldY / 64;

		for (int i = 0; i < target[i].length; ++i) {
			if (target[1][i] != null && target[1][i].getCol() == col && target[1][i].getRow() == row
					&& target[1][i].name.equals(targetName)) {
				index = i;
				break;
			}
		}

		return index;
	}

	public void choiceResponce() {
	}
}