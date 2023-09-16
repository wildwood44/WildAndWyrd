package wildwyrd.game.object;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.tile.InteractiveTile;

public class IT_StoneDoor extends InteractiveTile {
	GamePanel gp;
	public static final int intId = 1;
	public static final String objName = "Cottage door";

	public IT_StoneDoor(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		id = intId;
		name = objName;
		worldX = gp.tileSize * col;
		worldY = gp.tileSize * row;
		illusion = true;
		image = setup("/res/objects/Rockwall_Door1", gp.tileSize, gp.tileSize);
		image2 = setup("/res/objects/Rockwall_Door4", gp.tileSize, gp.tileSize);
		transformable = true;
		collisionOn = true;
		//timer = new Timer(20, this);
		//truth.changeAlpha(1f);
		animationCounter = 0;
		down1 = image;
	}
	
	public void uncoverIllusion(InteractiveTile truth) {
		animationCounter++;
		int i = 5;
		if(animationCounter <= i) {changeAlpha(1f);}
		if(animationCounter > i && animationCounter <= i*2) {changeAlpha(0.8f);}
		if(animationCounter > i*2 && animationCounter <= i*3) {changeAlpha(0.6f);}
		if(animationCounter > i*3 && animationCounter <= i*4) {changeAlpha(0.4f);}
		if(animationCounter > i*4 && animationCounter <= i*5) {changeAlpha(0.2f);}
		if(animationCounter > i*5 && animationCounter <= i*6) {changeAlpha(0f);}
		if(animationCounter > i*6) {truth = transform();}
		System.out.println(truth);
	}
	
	public InteractiveTile transform() {
		InteractiveTile tile = new IT_StoneDoor2(gp,worldX/gp.tileSize,worldY/gp.tileSize);
		illusion = false;
		return tile;
	}
	
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		//if(entity)
		return isCorrectItem;
	}
	
	public void update() {
		//int iTileIndex = gp.cChecker.checkEntity(gp.player,this);
	}
}
