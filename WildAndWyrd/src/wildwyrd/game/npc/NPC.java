package wildwyrd.game.npc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.cutscenes.Portrate;
import wildwyrd.game.items.Item;
import wildwyrd.game.objective.Quest;

public class NPC extends Entity {
	public Portrate port = new Portrate();
	public Quest[] sidequest = new Quest[5];
	
	public NPC(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
	}
	
	public BufferedImage getSpecialSpriteSheet() {
		BufferedImage sprite = null;

		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/res/sprite/WildWyrdSpecialSprites.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sprite;
	}

	public BufferedImage getSpecialImage(int xGrid, int yGrid) {
		if (image != getSpecialSpriteSheet()) {
			image = getSpecialSpriteSheet();
		}
		return image.getSubimage(xGrid * gp.tileSize, yGrid * gp.tileSize, gp.tileSize, gp.tileSize);
	}

	public BufferedImage getSpriteSheet() {
		BufferedImage sprite = null;
		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/res/sprite/WildWyrdSprites.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sprite;
	}

	public BufferedImage getPlayerImage(int xGrid, int yGrid) {
		if (image == null) {
			image = getSpriteSheet();
		}
		return image.getSubimage(xGrid * gp.tileSize, yGrid * gp.tileSize, gp.tileSize, gp.tileSize);
	}
	
	public void getImage() {}
		
	public Item buy(Item buy) {return null;}
	public Item sell(Item sell) {return null;}
}
