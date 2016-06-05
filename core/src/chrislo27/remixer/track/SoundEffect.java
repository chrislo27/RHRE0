package chrislo27.remixer.track;

import com.badlogic.gdx.math.Vector2;

import chrislo27.remixer.editor.Editor;
import chrislo27.remixer.registry.CueList;
import chrislo27.remixer.registry.CueList.Cue;

public final class SoundEffect implements Comparable {

	public float beat = 0;
	public Cue cue;
	public boolean isCompleted = false;
	public Vector2 position = new Vector2();
	public boolean selected = false;

	public SoundEffect(float beat, String cue) {
		this(beat, CueList.getCue(cue));

		position.x = beat * Editor.BLOCK_SIZE_X;
	}

	public SoundEffect(float beat, Cue cue) {
		this.beat = beat;
		this.cue = cue;
	}

	/**
	 * Copies the beat, cue, and position only.
	 * @param copy
	 */
	public SoundEffect(SoundEffect copy) {
		this(copy.beat, copy.cue);
		position.set(copy.position);
	}

	public boolean isPointIn(float x, float y) {
		if (x >= position.x && y >= position.y
				&& x <= position.x + cue.duration * Editor.BLOCK_SIZE_X
				&& y <= position.y + Editor.BLOCK_SIZE_Y)
			return true;

		return false;
	}

	public void onAction(Remix remix) {
		isCompleted = true;

		if (cue != null) {
			cue.getSFX().play(1, cue.pitchWithBpm > 0 ? Remix.getPitchFromBpm(remix.bpm, cue.pitchWithBpm) : 1, 0);
		}
	}

	public void reset() {
		isCompleted = false;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof SoundEffect) {
			SoundEffect other = (SoundEffect) o;

			if (other.beat > this.beat) return -1;
			if (other.beat < this.beat) return 1;
		}

		return 0;
	}

}
