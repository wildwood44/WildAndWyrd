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
		return section;
	}

	public String getTitle() {
		return title;
	}

	public String getDesc() {
		return desc;
	}
}