package wildwyrd.game.glossary;

public class GlossaryPage {
	private int section;
	private String title;
	private String desc;

	public GlossaryPage(int section, String title, String desc) {
		this.section = section;
		this.title = title;
		this.desc = desc;
	}

	public int getSection() {
		return this.section;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDesc() {
		return this.desc;
	}
}