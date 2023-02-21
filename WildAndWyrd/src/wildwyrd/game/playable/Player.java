package wildwyrd.game.playable;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.KeyHandler;
import wildwyrd.game.items.Itm_Bandage;
import wildwyrd.game.items.Itm_Hazelnut;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public ArrayList<Entity> inventory = new ArrayList<Entity>();
	public final int inventorySize = 20;
	public Boolean approval;

	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.gp = gp;
		this.keyH = keyH;
		gp.getClass();
		this.screenX = 768 / 2;
		gp.getClass();
		this.screenY = 512 / 2;
		gp.getClass();
		gp.getClass();
		this.solidArea = new Rectangle(12, 0, 64, 64);
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		this.solidArea.width = 24;
		this.solidArea.height = 48;
		this.setDefaultValues();
		this.getPlayerImage(3, 3);
	}

	public void setDefaultValues() {
		this.gp.getClass();
		this.worldX = 64 * 5;
		this.gp.getClass();
		this.worldY = 64 * 5;
		this.speed = 5;
		this.direction = "down";
	}

	public BufferedImage getSpriteSheet() {
		BufferedImage sprite = null;

		try {
			sprite = ImageIO.read(this.getClass().getResourceAsStream("/res/sprite/WildWyrdSprites.png"));
		} catch (IOException var3) {
			var3.printStackTrace();
		}

		return sprite;
	}

	public BufferedImage getPlayerImage(int xGrid, int yGrid) {
		if (this.image == null) {
			this.image = this.getSpriteSheet();
		}
		return this.image.getSubimage(xGrid * 48, yGrid * 48, 48, 48);
	}

	public void update() {
		int objIndex;
		if (this.keyH.upPressed || this.keyH.downPressed || this.keyH.leftPressed || this.keyH.rightPressed) {
			if (this.keyH.upPressed) {
				this.direction = "up";
			} else if (this.keyH.downPressed) {
				this.direction = "down";
			} else if (this.keyH.leftPressed) {
				this.direction = "left";
			} else if (this.keyH.rightPressed) {
				this.direction = "right";
			}

			this.collisionOn = false;
			this.gp.cChecker.checkTile(this);
			objIndex = this.gp.cChecker.checkObject(this, true);
			this.pickUpObject(objIndex);
			if (!this.collisionOn) {
				String var2 = this.direction;
				switch (this.direction.hashCode()) {
					case 3739 :
						if (var2.equals("up")) {
							this.worldY -= this.speed;
						}
						break;
					case 3089570 :
						if (var2.equals("down")) {
							this.worldY += this.speed;
						}
						break;
					case 3317767 :
						if (var2.equals("left")) {
							this.worldX -= this.speed;
						}
						break;
					case 108511772 :
						if (var2.equals("right")) {
							this.worldX += this.speed;
						}
				}
			}
			
			spriteCounter++;
			if (spriteCounter > 10) {
				if(spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 3;
				} else if (spriteNum == 3) {
					spriteNum = 4;
				} else if (spriteNum == 4) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		} else {
			spriteNum = 1;
		}

		if (this.keyH.enterPressed) {
			objIndex = this.gp.cChecker.checkObject(this, true);
			this.pickUpObject(objIndex);
		}

	}

	public void pickUpObject(int i) {
		if (i != 999) {
			if (this.gp.obj[1][i].type == 3 && this.keyH.enterPressed) {
				this.gp.obj[1][i].interact();
			}
		}

	}

	public void setItems() {
		this.inventory.add(new Itm_Hazelnut(this.gp));
		this.inventory.add(new Itm_Bandage(this.gp));
	}

	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		image = this.getPlayerImage(4, 0);
		int x = this.screenX;
		int y = this.screenY;
		if (this.screenX > this.worldX) {
			x = this.worldX;
		}

		if (this.screenY > this.worldY) {
			y = this.worldY;
		}
		switch(this.direction) {
		case "up":
			if(spriteNum == 1) {
				image = getPlayerImage(4, 3);
			} else if(spriteNum == 2) {
				image = getPlayerImage(3, 3);
			} else if(spriteNum == 3) {
				image = getPlayerImage(4, 3);
			} else if(spriteNum == 4) {
				image = getPlayerImage(5, 3);
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = getPlayerImage(4, 0);
			} else if(spriteNum == 2) {
				image = getPlayerImage(3, 0);
			} else if(spriteNum == 3) {
				image = getPlayerImage(4, 0);
			} else if(spriteNum == 4) {
				image = getPlayerImage(5, 0);
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = getPlayerImage(4, 1);
			} else if(spriteNum == 2) {
				image = getPlayerImage(3, 1);
			} else if(spriteNum == 3) {
				image = getPlayerImage(4, 1);
			} else if(spriteNum == 4) {
				image = getPlayerImage(5, 1);
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = getPlayerImage(4, 2);
			} else if(spriteNum == 2) {
				image = getPlayerImage(3, 2);
			} else if(spriteNum == 3) {
				image = getPlayerImage(4, 2);
			} else if(spriteNum == 4) {
				image = getPlayerImage(5, 2);
			}
			break;
		}

		this.gp.getClass();
		int rightOffset = 768 - this.screenX;
		this.gp.getClass();
		if (rightOffset > 1664 - this.gp.player.worldX) {
			this.gp.getClass();
			this.gp.getClass();
			x = 768 - (1664 - this.worldX);
		}

		this.gp.getClass();
		int bottomOffset = 512 - this.screenY;
		this.gp.getClass();
		if (bottomOffset > 768 - this.gp.player.worldY) {
			this.gp.getClass();
			this.gp.getClass();
			y = 512 - (768 - this.worldY);
		}

		g2.drawImage(image, x, y, (ImageObserver) null);
	}
}