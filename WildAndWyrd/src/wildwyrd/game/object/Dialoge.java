package wildwyrd.game.object;

public class Dialoge {
	public String speaker;
	public String text;
	public int type;

	public Dialoge(String text, int type) {
		this.text = text;
		this.type = type;
	}
	
	public Dialoge(String speaker, String text, int type) {
		this.speaker = speaker; 
		this.text = text;
		this.type = type;
	}

	public String getText() {
		return this.text;
	}

	public int getType() {
		return this.type;
	}

	public String getSpeaker() {
		return speaker;
	}
}