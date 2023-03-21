package wildwyrd.game.tile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class InteractiveTile extends Entity implements ActionListener {
	GamePanel gp;
	public float alpha = 1f;
	//public Timer timer;
	public boolean transformable = false;
	
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
	
	public InteractiveTile uncoverIllusion() {
		InteractiveTile tile = null;
		return tile;
	}
	
	public void update() {
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		alpha += -0.01f;
        if (alpha <= 0) {
            alpha = 0;
            timer.stop();
        }
        //repaint();
	}
}
