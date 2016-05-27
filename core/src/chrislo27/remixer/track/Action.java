package chrislo27.remixer.track;

public abstract class Action implements Comparable {

	public float beat = 0;
	public boolean isCompleted = false;

	public Action(float beat) {
		this.beat = beat;
	}

	public void onAction() {
		isCompleted = true;
	}

	public void reset() {
		isCompleted = false;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Action) {
			Action other = (Action) o;

			if (other.beat > this.beat) return -1;
			if (other.beat < this.beat) return 1;
		}

		return 0;
	}

}
