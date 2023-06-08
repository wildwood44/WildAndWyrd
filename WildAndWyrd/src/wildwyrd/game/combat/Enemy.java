package wildwyrd.game.combat;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.object.Dialoge;

public class Enemy extends Entity {
	public Enemy(GamePanel gp) {
		super(gp);
		name = "Cricket";
		maxHealth = 20;
		health = maxHealth;
		expDrop = 10;
		//private itemDrop = [{'item' : items.food[4], 'quantity':2}],
		baseAttack = 0;
		baseDefence = 8;
		baseAccuracy = 90;
		baseSpeed = 10;
		baseEvasion = 23;
        String desc = "Big grasshoppers with long antenna. Can jump a fair distance.";
		type = EntityType.Sprite;
		speed = 1;
		setDialogue();
	}

	public BufferedImage getImage() {
		BufferedImage sprite = null;

		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/res/sprite/combat/cricket.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sprite;
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = getImage();
		int screenX = gp.tileSize*6;
		int screenY = gp.tileSize*2;

		g2.drawImage(image, screenX, screenY, gp.tileSize*2, gp.tileSize*2, null);
	}
	
	public void setAction() {
		
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(name + " took " + " damage" ,1);
		dialogues[0][1] = new Dialoge("Fight Crickets?" ,2);
	}
	
	public void speak() {
		gp.ui.choiceSlot = 0;
		gp.ui.firstValue = 0;
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}
