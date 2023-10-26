package wildwyrd.game.tile;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class CollisionChecker {
	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		this.gp.getClass();
		int entityLeftCol = entityLeftWorldX / 64;
		this.gp.getClass();
		int entityRightCol = entityRightWorldX / 64;
		this.gp.getClass();
		int entityTopRow = entityTopWorldY / 64;
		this.gp.getClass();
		int entityBottomRow = entityBottomWorldY / 64;
		String var12 = entity.direction;
		int tileNum1;
		int tileNum2;
		int var10000;
		switch (entity.direction.hashCode()) {
			case 3739 :
				if (var12.equals("up")) {
					var10000 = entityTopWorldY - entity.speed;
					this.gp.getClass();
					entityTopRow = var10000 / 64;
					tileNum1 = this.gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
					tileNum2 = this.gp.tileM.mapTileNum[entityRightCol][entityTopRow];
					if (this.gp.tileM.tile[tileNum1].collision || this.gp.tileM.tile[tileNum2].collision) {
						entity.collisionOn = true;
					}
				}
				break;
			case 3089570 :
				if (var12.equals("down")) {
					var10000 = entityBottomWorldY + entity.speed;
					this.gp.getClass();
					entityBottomRow = var10000 / 64;
					tileNum1 = this.gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
					tileNum2 = this.gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
					if (this.gp.tileM.tile[tileNum1].collision || this.gp.tileM.tile[tileNum2].collision) {
						entity.collisionOn = true;
					}
				}
				break;
			case 3317767 :
				if (var12.equals("left")) {
					var10000 = entityLeftWorldX - entity.speed;
					this.gp.getClass();
					entityLeftCol = var10000 / 64;
					tileNum1 = this.gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
					tileNum2 = this.gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
					if (this.gp.tileM.tile[tileNum1].collision || this.gp.tileM.tile[tileNum2].collision) {
						entity.collisionOn = true;
					}
				}
				break;
			case 108511772 :
				if (var12.equals("right")) {
					var10000 = entityRightWorldX + entity.speed;
					this.gp.getClass();
					entityRightCol = var10000 / 64;
					tileNum1 = this.gp.tileM.mapTileNum[entityRightCol][entityTopRow];
					tileNum2 = this.gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
					if (this.gp.tileM.tile[tileNum1].collision || this.gp.tileM.tile[tileNum2].collision) {
						entity.collisionOn = true;
					}
				}
		}

	}

	public int checkObject(Entity entity, boolean player) {
		int index = 999;

		for (int i = 0; i < this.gp.obj[1].length; ++i) {
			if (this.gp.obj[1][i] != null) {
				entity.solidArea.x += entity.worldX;
				entity.solidArea.y += entity.worldY;
				this.gp.obj[1][i].solidArea.x += this.gp.obj[1][i].worldX;
				this.gp.obj[1][i].solidArea.y += this.gp.obj[1][i].worldY;
				String var5 = entity.direction;
				switch (entity.direction.hashCode()) {
					case 3739 :
						if (var5.equals("up")) {
							entity.solidArea.y -= entity.speed;
							if (entity.solidArea.intersects(this.gp.obj[1][i].solidArea)) {
								if (this.gp.obj[1][i].collision) {
									entity.collisionOn = true;
								}

								if (player) {
									index = i;
								}
							}
						}
						break;
					case 3089570 :
						if (var5.equals("down")) {
							entity.solidArea.y += entity.speed;
							if (entity.solidArea.intersects(this.gp.obj[1][i].solidArea)) {
								if (this.gp.obj[1][i].collision) {
									entity.collisionOn = true;
								}

								if (player) {
									index = i;
								}
							}
						}
						break;
					case 3317767 :
						if (var5.equals("left")) {
							entity.solidArea.x -= entity.speed;
							if (entity.solidArea.intersects(this.gp.obj[1][i].solidArea)) {
								if (this.gp.obj[1][i].collision) {
									entity.collisionOn = true;
								}

								if (player) {
									index = i;
								}
							}
						}
						break;
					case 108511772 :
						if (var5.equals("right")) {
							entity.solidArea.x += entity.speed;
							if (entity.solidArea.intersects(this.gp.obj[1][i].solidArea)) {
								if (this.gp.obj[1][i].collision) {
									entity.collisionOn = true;
								}

								if (player) {
									index = i;
								}
							}
						}
				}

				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				this.gp.obj[1][i].solidArea.x = this.gp.obj[1][i].solidAreaDefaultX;
				this.gp.obj[1][i].solidArea.y = this.gp.obj[1][i].solidAreaDefaultY;
			}
		}

		return index;
	}

	public int checkEntity(Entity entity, Entity[] target) {
		int index = 999;

		for (int i = 0; i < target.length; ++i) {
			if (target[i] != null) {
				entity.solidArea.x += entity.worldX;
				entity.solidArea.y += entity.worldY;
				target[i].solidArea.x += target[i].worldX;
				target[i].solidArea.y += target[i].worldY;
				String var5 = entity.direction;
				switch (entity.direction.hashCode()) {
					case 3739 :
						if (var5.equals("up")) {
							entity.solidArea.y -= entity.speed;
							if (entity.solidArea.intersects(target[i].solidArea)) {
								entity.collisionOn = true;
								index = i;
							}
						}
						break;
					case 3089570 :
						if (var5.equals("down")) {
							entity.solidArea.y += entity.speed;
							if (entity.solidArea.intersects(target[i].solidArea)) {
								entity.collisionOn = true;
								index = i;
							}
						}
						break;
					case 3317767 :
						if (var5.equals("left")) {
							entity.solidArea.x -= entity.speed;
							if (entity.solidArea.intersects(target[i].solidArea)) {
								entity.collisionOn = true;
								index = i;
							}
						}
						break;
					case 108511772 :
						if (var5.equals("right")) {
							entity.solidArea.x += entity.speed;
							if (entity.solidArea.intersects(target[i].solidArea)) {
								entity.collisionOn = true;
								index = i;
							}
						}
				}

				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
			}
		}

		return index;
	}

	public void checkPlayer(Entity entity) {
		entity.solidArea.x += entity.worldX;
		entity.solidArea.y += entity.worldY;
		this.gp.player.solidArea.x += this.gp.player.worldX;
		this.gp.player.solidArea.y += this.gp.player.worldY;
		String var2 = entity.direction;
		switch (entity.direction.hashCode()) {
			case 3739 :
				if (var2.equals("up")) {
					entity.solidArea.y -= entity.speed;
					if (entity.solidArea.intersects(this.gp.player.solidArea)) {
						entity.collisionOn = true;
					}
				}
				break;
			case 3089570 :
				if (var2.equals("down")) {
					entity.solidArea.y += entity.speed;
					if (entity.solidArea.intersects(this.gp.player.solidArea)) {
						entity.collisionOn = true;
					}
				}
				break;
			case 3317767 :
				if (var2.equals("left")) {
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(this.gp.player.solidArea)) {
						entity.collisionOn = true;
					}
				}
				break;
			case 108511772 :
				if (var2.equals("right")) {
					entity.solidArea.x += entity.speed;
					if (entity.solidArea.intersects(this.gp.player.solidArea)) {
						entity.collisionOn = true;
					}
				}
		}

		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		this.gp.player.solidArea.x = this.gp.player.solidAreaDefaultX;
		this.gp.player.solidArea.y = this.gp.player.solidAreaDefaultY;
	}
}