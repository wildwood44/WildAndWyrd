package wildwyrd.game.glossary;

public class GlossaryPage {
	private int section;
	private String title;
	private String desc;
	private boolean found;

	public GlossaryPage(int section, String title, String desc) {
		this.section = section;
		this.title = title;
		this.desc = desc;
		found = false;
	}

	public int getSection() {
		return section;
	}

	public String getTitle() {
		if(found) {
			return title;
		}
		return "???";
	}

	public String getName() {
		return title;
	}

	public String getDesc() {
		if(found) {
			return desc;
		}
		return "???";
	}
	
	public void findGlossaryItem() {
		if(!found) {
			found = true;
		}
	}
	
	public boolean isFound() {
		return found;
	}
}