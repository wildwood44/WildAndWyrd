package wildwyrd.game.tile;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class InteractiveTile extends Entity {
	GamePanel gp;
	public boolean transformable = false;
	
	public InteractiveTile(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
	}
	
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		return isCorrectItem;
	}
	
	public InteractiveTile uncoverIllusion() {
		InteractiveTile tile = null;
		return tile;
	}
	
	public void update() {
		
	}
}
