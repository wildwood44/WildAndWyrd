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

		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		int tileNum1, tileNum2;
		switch (entity.direction) {
			case "up":
					entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
					tileNum1 = this.gp.tileM.mapTileNum[gp.currentMap.getId()][entityLeftCol][entityTopRow];
					tileNum2 = this.gp.tileM.mapTileNum[gp.currentMap.getId()][entityRightCol][entityTopRow];
					if (this.gp.tileM.tile[tileNum1].collision || this.gp.tileM.tile[tileNum2].collision) {
						entity.collisionOn = true;
					}
				break;
			case "down":
					entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
					tileNum1 = this.gp.tileM.mapTileNum[gp.currentMap.getId()][entityLeftCol][entityBottomRow];
					tileNum2 = this.gp.tileM.mapTileNum[gp.currentMap.getId()][entityRightCol][entityBottomRow];
					if (this.gp.tileM.tile[tileNum1].collision || this.gp.tileM.tile[tileNum2].collision) {
						entity.collisionOn = true;
					}
				break;
			case "left":
					entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
					tileNum1 = this.gp.tileM.mapTileNum[gp.currentMap.getId()][entityLeftCol][entityTopRow];
					tileNum2 = this.gp.tileM.mapTileNum[gp.currentMap.getId()][entityLeftCol][entityBottomRow];
					if (this.gp.tileM.tile[tileNum1].collision || this.gp.tileM.tile[tileNum2].collision) {
						entity.collisionOn = true;
					}
				break;
			case "right":
					entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
					tileNum1 = this.gp.tileM.mapTileNum[gp.currentMap.getId()][entityRightCol][entityTopRow];
					tileNum2 = this.gp.tileM.mapTileNum[gp.currentMap.getId()][entityRightCol][entityBottomRow];
					if (this.gp.tileM.tile[tileNum1].collision || this.gp.tileM.tile[tileNum2].collision) {
						entity.collisionOn = true;
					}
				}

	}

	public int checkObject(Entity entity, boolean player) {
		int index = 999;

		for (int i = 0; i < this.gp.obj[gp.currentMap.getId()].length; ++i) {
			if (this.gp.obj[gp.currentMap.getId()][i] != null) {
				entity.solidArea.x += entity.worldX;
				entity.solidArea.y += entity.worldY;
				this.gp.obj[gp.currentMap.getId()][i].solidArea.x += this.gp.obj[gp.currentMap.getId()][i].worldX;
				this.gp.obj[gp.currentMap.getId()][i].solidArea.y += this.gp.obj[gp.currentMap.getId()][i].worldY;
				switch (entity.direction) {
					case "up":
							entity.solidArea.y -= entity.speed;
							if (entity.solidArea.intersects(this.gp.obj[gp.currentMap.getId()][i].solidArea)) {
								if (this.gp.obj[gp.currentMap.getId()][i].collision) {
									entity.collisionOn = true;
								}

								if (player) {
									index = i;
								}
							}
						break;
					case "down":
							entity.solidArea.y += entity.speed;
							if (entity.solidArea.intersects(this.gp.obj[gp.currentMap.getId()][i].solidArea)) {
								if (this.gp.obj[gp.currentMap.getId()][i].collision) {
									entity.collisionOn = true;
								}

								if (player) {
									index = i;
								}
							}
						break;
					case "left":
							entity.solidArea.x -= entity.speed;
							if (entity.solidArea.intersects(this.gp.obj[gp.currentMap.getId()][i].solidArea)) {
								if (this.gp.obj[gp.currentMap.getId()][i].collision) {
									entity.collisionOn = true;
								}

								if (player) {
									index = i;
								}
							}
						break;
					case "right":
							entity.solidArea.x += entity.speed;
							if (entity.solidArea.intersects(this.gp.obj[gp.currentMap.getId()][i].solidArea)) {
								if (this.gp.obj[gp.currentMap.getId()][i].collision) {
									entity.collisionOn = true;
								}

								if (player) {
									index = i;
								}
							}
				}

				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				this.gp.obj[gp.currentMap.getId()][i].solidArea.x = this.gp.obj[gp.currentMap.getId()][i].solidAreaDefaultX;
				this.gp.obj[gp.currentMap.getId()][i].solidArea.y = this.gp.obj[gp.currentMap.getId()][i].solidAreaDefaultY;
			}
		}

		return index;
	}

	public int checkEntity(Entity entity, Entity[][] target) {
		int index = 999;

		for (int i = 0; i < target.length; ++i) {
			if (target[i] != null) {
				entity.solidArea.x += entity.worldX;
				entity.solidArea.y += entity.worldY;
				target[gp.currentMap.getId()][i].solidArea.x += target[gp.currentMap.getId()][i].worldX;
				target[gp.currentMap.getId()][i].solidArea.y += target[gp.currentMap.getId()][i].worldY;
				switch (entity.direction) {
					case "up":
							entity.solidArea.y -= entity.speed;
							if (entity.solidArea.intersects(target[gp.currentMap.getId()][i].solidArea)) {
								entity.collisionOn = true;
								index = i;
							}
						break;
					case "down":
							entity.solidArea.y += entity.speed;
							if (entity.solidArea.intersects(target[gp.currentMap.getId()][i].solidArea)) {
								entity.collisionOn = true;
								index = i;
							}
						break;
					case "left":
							entity.solidArea.x -= entity.speed;
							if (entity.solidArea.intersects(target[gp.currentMap.getId()][i].solidArea)) {
								entity.collisionOn = true;
								index = i;
							}
						break;
					case "right":
							entity.solidArea.x += entity.speed;
							if (entity.solidArea.intersects(target[gp.currentMap.getId()][i].solidArea)) {
								entity.collisionOn = true;
								index = i;
							}
						break;
				}

				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[gp.currentMap.getId()][i].solidArea.x = target[gp.currentMap.getId()][i].solidAreaDefaultX;
				target[gp.currentMap.getId()][i].solidArea.y = target[gp.currentMap.getId()][i].solidAreaDefaultY;
			}
		}

		return index;
	}

	public void checkPlayer(Entity entity) {
		entity.solidArea.x += entity.worldX;
		entity.solidArea.y += entity.worldY;
		this.gp.player.solidArea.x += this.gp.player.worldX;
		this.gp.player.solidArea.y += this.gp.player.worldY;
		switch (entity.direction) {
			case "up":
					entity.solidArea.y -= entity.speed;
					if (entity.solidArea.intersects(this.gp.player.solidArea)) {
						entity.collisionOn = true;
				}
				break;
			case "down":
					entity.solidArea.y += entity.speed;
					if (entity.solidArea.intersects(this.gp.player.solidArea)) {
						entity.collisionOn = true;
				}
				break;
			case "left":
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(this.gp.player.solidArea)) {
						entity.collisionOn = true;
					}
				break;
			case "right":
					entity.solidArea.x += entity.speed;
					if (entity.solidArea.intersects(this.gp.player.solidArea)) {
						entity.collisionOn = true;
					}
					break;
		}

		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		this.gp.player.solidArea.x = this.gp.player.solidAreaDefaultX;
		this.gp.player.solidArea.y = this.gp.player.solidAreaDefaultY;
	}
}