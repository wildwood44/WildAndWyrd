package wildwyrd.game.object;

import wildwyrd.game.GamePanel;
import wildwyrd.game.tile.InteractiveTile;

public class IT_StoneDoor2 extends InteractiveTile {
	GamePanel gp;
	public static final int intId = 2;
	public static final String objName = "Cottage door";
	
	public IT_StoneDoor2(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = intId;
		name = objName;
		illusion = false;
		//image = setup("/res/objects/Rockwall_Door1");
		image = setup("/res/objects/Rockwall_Door4", gp.tileSize, gp.tileSize);
		transformable = true;
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 0;
		solidArea.height = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public void update() {
		if(illusion == false) {
			coverIllusion(new IT_StoneDoor(gp));
		}
	}
	
	public void coverIllusion(InteractiveTile mask) {
		animationCounter++;
		int i = 5;
		if(animationCounter <= i) {changeAlpha(0f);}
		if(animationCounter > i && animationCounter <= i*2) {changeAlpha(0.2f);}
		if(animationCounter > i*2 && animationCounter <= i*3) {changeAlpha(0.4f);}
		if(animationCounter > i*3 && animationCounter <= i*4) {changeAlpha(0.6f);}
		if(animationCounter > i*4 && animationCounter <= i*5) {changeAlpha(0.8f);}
		if(animationCounter > i*5 && animationCounter <= i*6) {changeAlpha(1f);}
		if(animationCounter > i*6) {mask = transform();}
	}
}
