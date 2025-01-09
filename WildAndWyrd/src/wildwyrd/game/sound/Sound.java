package wildwyrd.game.sound;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[40];
	FloatControl fc;
	public int volumeScale = 3;
	float volume;
	
	public Sound() {
		soundURL[0] = getClass().getResource("/res/sound/Robin-1.wav"); //Title Menu Up/Down
		soundURL[1] = getClass().getResource("/res/sound/blackbird.wav"); //Title Menu Select
		soundURL[2] = getClass().getResource("/res/sound/Treecreeper.wav"); // Up/Down
		soundURL[3] = getClass().getResource("/res/sound/Green_Woodpecker.wav"); // Save
		soundURL[4] = getClass().getResource("/res/sound/Wren.wav"); //Stats
		//Select
		soundURL[6] = getClass().getResource("/res/sound/Goldcrest.wav"); //Objectives
		soundURL[7] = getClass().getResource("/res/sound/Magpie.wav"); //Glossary
		//Options
		//Quit
		soundURL[8] = getClass().getResource("/res/sound/Door.wav"); // Door used
		soundURL[10] = getClass().getResource("/res/sound/Book_Open.wav"); // Open book
		soundURL[11] = getClass().getResource("/res/sound/pageflip.wav"); // Turn page
		soundURL[12] = getClass().getResource("/res/sound/Book_Close.wav"); // Close book
		soundURL[13] = getClass().getResource("/res/sound/attack.wav"); //Attack
		soundURL[14] = getClass().getResource("/res/sound/block.wav"); //Block
		soundURL[15] = getClass().getResource("/res/sound/move.wav"); //Advance/Retreat
		//Special
		soundURL[17] = getClass().getResource("/res/sound/use.wav"); //Use Items
		soundURL[18] = getClass().getResource("/res/sound/flee.wav"); //Flee
		soundURL[19] = getClass().getResource("/res/sound/victory.wav"); //Victory
		soundURL[20] = getClass().getResource("/res/sound/run.wav"); // Run
		soundURL[21] = getClass().getResource("/res/sound/Pickup.wav"); // Interact with item
		soundURL[22] = getClass().getResource("/res/sound/Chime.wav"); //Bell
		soundURL[23] = getClass().getResource("/res/sound/Unlock.wav"); //Unlock
		soundURL[24] = getClass().getResource("/res/sound/Unsheathe.wav"); //Unsheathe
		//Menu song
		soundURL[31] = getClass().getResource("/res/music/Casual-Theme.wav"); //Woodland song
		//Cottage song
		soundURL[33] = getClass().getResource("/res/music/HallOfTheScion.wav"); // Hall of the Scion
		soundURL[34] = getClass().getResource("/res/music/Combat-Theme.wav"); //Combat theme
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
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
	
	public void checkVolume() {
		switch(volumeScale) {
		case 0: volume = -90f; break;
		case 1: volume = -20f; break;
		case 2: volume = -12f; break;
		case 3: volume = -5f; break;
		case 4: volume = 1f; break;
		case 5: volume = 6f; break;
		}
		fc.setValue(volume);
	}
}
