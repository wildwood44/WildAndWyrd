package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.tile.InteractiveTile;

public class IT_StoneDoor extends InteractiveTile {
	GamePanel gp;
	
	public IT_StoneDoor(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		worldX = gp.tileSize * col;
		worldY = gp.tileSize * row;
		
		image = setup("/res/objects/Rockwall_Door1");
		//image2 = setup("/res/objects/Rockwall_Door4");
		transformable = true;
		collisionOn = true;
	}
	
	public InteractiveTile uncoverIllusion() {
		InteractiveTile tile = new IT_StoneDoor2(gp,worldX/gp.tileSize,worldY/gp.tileSize);
		return tile;
	}
	
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		//if(entity)
		return isCorrectItem;
	}
}
