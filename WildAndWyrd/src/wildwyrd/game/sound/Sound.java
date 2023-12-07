package wildwyrd.game.sound;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/res/sound/Green_Woodpecker.wav");
		soundURL[1] = getClass().getResource("/res/sound/Door.wav");
		soundURL[10] = getClass().getResource("/res/sound/Book_Open.wav");
		soundURL[11] = getClass().getResource("/res/sound/page-flip-01a.wav");
		soundURL[12] = getClass().getResource("/res/sound/Book_Close.wav");
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
}
