package chrislo27.remixer.action;

import chrislo27.remixer.game.Cue;

public class ActionSFX extends Action {

	public Cue cue;
	public float beat;
	public int track;
	public float volume = 1;
	public int semitone = 0;

	public ActionSFX(Cue cue, float beat, int track) {
		this.cue = cue;
		this.beat = beat;
		this.track = track;
	}

	public ActionSFX(ActionSFX other) {
		this(other.cue, other.beat, other.track);
	}

}
