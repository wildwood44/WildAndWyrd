package wildwyrd.game.items;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;

public class Projectile extends Weapon {
	public Projectile(GamePanel gp) {
		super(gp);
		this.gp = gp;
		description = "";
		stackable = true;
		type = EntityType.Projectile;
	}
	
	public void use() {
		if(gp.gameState == GameState.combatState) {
			if(!gp.playable.get(0).projectileLoaded()) {
				gp.playable.get(0).loadProjectile(this);
				gp.player.removeFromInventory(this);
				gp.ui.playerSlotCol = 0;
				gp.ui.commandNum = 0;
			}
		}
	}
}
