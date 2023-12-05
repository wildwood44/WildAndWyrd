package wildwyrd.game.tile;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class InteractiveTile extends Entity {
	GamePanel gp;
	//public float alpha = 1f;
	//public Timer timer;
	public boolean transformable = false;
	public boolean illusion = false;
	public int animationCounter;
	public boolean animationComp = false;
	public boolean eventActive = false;
	
	public InteractiveTile(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		//solidArea.x = 48;
		solidArea.y = 10;
		//solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		return isCorrectItem;
	}
	
	public InteractiveTile transform() {
		InteractiveTile tile = this;
		return tile;
	}
	
	public void update() {
		
	} 
	
	public void changeAlpha(float alphaValue) {
		alpha = alphaValue;
	}

	public void uncoverIllusion(InteractiveTile truth) {
		// TODO Auto-generated method stub
		
	}

	public void coverIllusion(InteractiveTile truth) {
		// TODO Auto-generated method stub
		
	}
}
