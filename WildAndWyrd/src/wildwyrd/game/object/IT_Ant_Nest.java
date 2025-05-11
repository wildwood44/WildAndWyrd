package wildwyrd.game.object;

import java.awt.Graphics2D;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.tile.InteractiveTile;

public class IT_Ant_Nest extends InteractiveTile {
	GamePanel gp;
	public static final int intId = 1;
	public static final String objName = "Cottage door";

	public IT_Ant_Nest(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = intId;
		name = objName;
		//image = setup("/res/objects/Rockwall_Door1", gp.tileSize, gp.tileSize);
		//timer = new Timer(20, this);
		//truth.changeAlpha(1f);
		animationCounter = 0;
		//down1 = image;
	}
	
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		//if(entity)
		return isCorrectItem;
	}
	
	public void update() {
		//int iTileIndex = gp.cChecker.checkEntity(gp.player,this);
	}
	
	public void draw(Graphics2D g2) {
		generateParticles(this, this);
	}
}
