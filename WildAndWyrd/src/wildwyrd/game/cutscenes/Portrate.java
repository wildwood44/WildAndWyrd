package wildwyrd.game.cutscenes;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Portrate {
	public BufferedImage image_Alder;
	public BufferedImage image_Florence;
	public BufferedImage image_Dilecto;
	public BufferedImage image_Thay;
	public BufferedImage image_Dean;
	public BufferedImage image_Ralph;
	public BufferedImage image_Plumm;
	public BufferedImage image_Kyla;
	public BufferedImage image_Trissie;
	public BufferedImage image_Agrimus;
	public BufferedImage image_Gowl_Rat;
	public BufferedImage image_Gowl_Weasel;
	public BufferedImage image_Jeb;
	
	public Portrate() {
		setImages();
	}

	public BufferedImage getImage(String name) {
		switch(name) {
		case "Alder": return image_Alder;
		case "Florence":
		case "Woman":
			return image_Florence;
		case "Dilecto": return image_Dilecto;
		case "Thay":
		case "Hedgehog":
			return image_Thay;
		case "Dean": 
		case "Shrew":
			return image_Dean;
		case "Ralph": 
		case "Vole":
			return image_Ralph;
		case "Plumm": return image_Plumm;
		case "Kyla": return image_Kyla;
		case "Trissie": return image_Trissie;
		case "Agrimus": return image_Agrimus;
		default : return null;
		}
	}

	public void setImages() {
		try {
			image_Alder = ImageIO.read(getClass().getResourceAsStream("/res/portrate/prt_Alder.png"));
			image_Florence = ImageIO.read(getClass().getResourceAsStream("/res/portrate/prt_Florence.png"));
			image_Dilecto = ImageIO.read(getClass().getResourceAsStream("/res/portrate/prt_Dilecto.png"));
			image_Thay = ImageIO.read(getClass().getResourceAsStream("/res/portrate/prt_Thay.png"));
			image_Dean = ImageIO.read(getClass().getResourceAsStream("/res/portrate/prt_Dean.png"));
			image_Ralph = ImageIO.read(getClass().getResourceAsStream("/res/portrate/prt_Ralph.png"));
			image_Plumm = ImageIO.read(getClass().getResourceAsStream("/res/portrate/prt_Plumm.png"));
			image_Kyla = ImageIO.read(getClass().getResourceAsStream("/res/portrate/prt_Kyla.png"));
			image_Trissie = ImageIO.read(getClass().getResourceAsStream("/res/portrate/prt_Trissie.png"));
			image_Agrimus = ImageIO.read(getClass().getResourceAsStream("/res/portrate/prt_Agrimus.png"));
			image_Gowl_Rat = ImageIO.read(getClass().getResourceAsStream("/res/portrate/prt_gowl_rat.png"));
			image_Gowl_Weasel = ImageIO.read(getClass().getResourceAsStream("/res/portrate/prt_gowl_weasel.png"));
			image_Jeb = ImageIO.read(getClass().getResourceAsStream("/res/portrate/prt_Jeb.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
