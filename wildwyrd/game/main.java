package wildwyrd.game;

import java.awt.Component;
import javax.swing.JFrame;

public class main {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(3);
		frame.setResizable(false);
		frame.setTitle("Wild and Wyrd");
		GamePanel gamePanel = new GamePanel();
		frame.add(gamePanel);
		frame.pack();
		frame.setLocationRelativeTo((Component) null);
		frame.setVisible(true);
		gamePanel.setupGame();
		gamePanel.startGameThread();
	}
}