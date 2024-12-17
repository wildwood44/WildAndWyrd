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
	
	public void progress(int swh) {
		c1Switch[swh] = false;
		c1Switch[swh+1] = true;
	}
}