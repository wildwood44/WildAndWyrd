package wildwyrd.game.object;

import wildwyrd.game.GamePanel;
import wildwyrd.game.tile.InteractiveTile;

public class IT_StoneDoor2 extends InteractiveTile {
	GamePanel gp;
	
	public IT_StoneDoor2(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		//image = setup("/res/objects/Rockwall_Door1");
		image = setup("/res/objects/Rockwall_Door4", gp.tileSize, gp.tileSize);
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 0;
		solidArea.height = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
}
