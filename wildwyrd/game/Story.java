package wildwyrd.game;

public class Story {
	public int chapter = 0;
	public int part = 1;
	public boolean[] swh = new boolean[]{true, false, false, false, false, false, false, false, false, false, false,
			false, false, false, false, false};
	public boolean[] tutorialSwitch = new boolean[]{true, true, true, true, true, true, true};
	public boolean[] c2Switch = new boolean[]{true, true, true, true, true, true};
	public boolean[] c3Switch = new boolean[]{true, true, true, true, true, true, true};
	public int[] branchSwitch = new int[1];
	public boolean[] PKSwitch = new boolean[]{true, true, true, true, true, true};
}