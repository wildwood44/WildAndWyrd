package wildwyrd.game;

import java.io.Serializable;

public class Story implements Serializable {
	public int chapter;
	public int part;
	public boolean[] swh;
	public boolean[] c1Switch;
	public boolean[] c2Switch;
	public boolean[] c3Switch;
	public int[] branchSwitch;
	//public boolean[] PKSwitch = new boolean[]{true, true, true, true, true, true};
	
	public Story() {
		chapter = 0;
		part = 1;
		swh = new boolean[]{true, false, false, false, false, false, false, false, false, false, false,
			false, false, false, false, false};
		c1Switch = new boolean[]{true, true, true, true, true, true, true};
		c2Switch = new boolean[]{true, true, true, true, true, true};
		c3Switch = new boolean[]{true, true, true, true, true, true, true};
		branchSwitch = new int[1];
	}
	
	public int getSwitch() {
		for(int i = 0; i < swh.length; i++) {
			return i;
		}
		return -1;
	}

	public boolean[] getChapterSwitch() {
		return swh;
	}
	
	public void setSwitch(boolean swh2[]) {
		for(int i = 0; i < swh.length; i++) {
			swh[i] = swh2[i];
		}
	}
	
	public void setC1Switch(boolean swh2[]) {
		for(int i = 0; i < swh.length; i++) {
			c1Switch[i] = swh2[i];
		}
	}
}