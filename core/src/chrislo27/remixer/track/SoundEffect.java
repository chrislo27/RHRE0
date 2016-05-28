package chrislo27.remixer.track;

import com.badlogic.gdx.math.Rectangle;

import chrislo27.remixer.editor.Editor;
import chrislo27.remixer.registry.CueList;
import chrislo27.remixer.registry.CueList.Cue;

public class SoundEffect implements Comparable {

	public float beat = 0;
	public Cue cue;
	public boolean isCompleted = false;

	public SoundEffect(float beat, String cue) {
		this(beat, CueList.getCue(cue));
	}

	public SoundEffect(float beat, Cue cue) {
		this.beat = beat;
		this.cue = cue;
	}

	public void onAction() {
		isCompleted = true;

		if (cue != null) {
			cue.getSFX().play();
		}
	}

	public Rectangle getBounds(Rectangle r, int track) {
		return r.set(beat * Editor.BLOCK_SIZE_X, track * Editor.BLOCK_SIZE_Y,
				cue.duration * Editor.BLOCK_SIZE_X, Editor.BLOCK_SIZE_Y);
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
