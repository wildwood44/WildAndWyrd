package wildwyrd.game.object;

import java.awt.event.ActionEvent;

import javax.swing.Timer;
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
		timer = new Timer(20, this);
	}
	
	public InteractiveTile uncoverIllusion() {
		System.out.println("Ping");
		InteractiveTile tile = new IT_StoneDoor2(gp,worldX/gp.tileSize,worldY/gp.tileSize);
		//image = setup("/res/objects/Rockwall_Door4");
		//collisionOn = false;
		timer.start();
		return tile;
	}
	
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		//if(entity)
		return isCorrectItem;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (startTime < 0) {
            startTime = System.currentTimeMillis();
        } else {

            long time = System.currentTimeMillis();
            long duration = time - startTime;
            if (duration >= RUNNING_TIME) {
                startTime = -1;
                ((Timer) e.getSource()).stop();
                alpha = 0f;
            } else {
                alpha = 1f - ((float) duration / (float) RUNNING_TIME);
            }
            //repaint();
        }
	}
}
