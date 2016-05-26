package chrislo27.remixer.track;

public abstract class Action {

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

}
