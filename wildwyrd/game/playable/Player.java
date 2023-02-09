package wildwyrd.game.playable;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public ArrayList<Entity> inventory = new ArrayList();
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
		this.solidArea = new Rectangle(0, 0, 64, 64);
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		this.solidArea.width = 56;
		this.solidArea.height = 56;
		this.setDefaultValues();
		this.getPlayerImage(0, 1);
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
			sprite = ImageIO.read(this.getClass().getResourceAsStream("/res/sprite/spt_Alder.png"));
		} catch (IOException var3) {
			var3.printStackTrace();
		}

		return sprite;
	}

	public BufferedImage getPlayerImage(int xGrid, int yGrid) {
		if (this.image == null) {
			this.image = this.getSpriteSheet();
		}

		return this.image.getSubimage(xGrid * 0, yGrid * 0, 48, 48);
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
		}

		if (this.keyH.enterPressed) {
			objIndex = this.gp.cChecker.checkObject(this, true);
			this.pickUpObject(objIndex);
		}

	}

	public void pickUpObject(int i) {
		if (i != 999) {
			System.out.println(this.gp.obj[1][i]);
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
		image = this.getPlayerImage(0, 0);
		int x = this.screenX;
		int y = this.screenY;
		if (this.screenX > this.worldX) {
			x = this.worldX;
		}

		if (this.screenY > this.worldY) {
			y = this.worldY;
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