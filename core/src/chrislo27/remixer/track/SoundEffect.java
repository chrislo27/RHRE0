package chrislo27.remixer.track;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

import chrislo27.remixer.editor.Editor;
import chrislo27.remixer.registry.CueList;
import chrislo27.remixer.registry.CueList.Cue;

public final class SoundEffect implements Comparable {

	public float beat = 0;
	public Cue cue;
	public int isCompleted = 0;
	public Vector2 position = new Vector2();
	public boolean selected = false;
	public float duration = 1;

	private long soundId = -1;

	public SoundEffect(float beat, String cue) {
		this(beat, CueList.getCue(cue));

		position.x = beat * Editor.BLOCK_SIZE_X;
	}

	public SoundEffect(float beat, Cue cue) {
		this.beat = beat;
		this.cue = cue;

		duration = this.cue.duration;
	}

	/**
	 * Copies the beat, cue, position, and duration only.
	 * @param copy
	 */
	public SoundEffect(SoundEffect copy) {
		this(copy.beat, copy.cue);
		position.set(copy.position);
		duration = copy.duration;
	}

	public boolean isPointIn(float x, float y) {
		if (x >= position.x && y >= position.y && x <= position.x + duration * Editor.BLOCK_SIZE_X
				&& y <= position.y + Editor.BLOCK_SIZE_Y)
			return true;

		return false;
	}

	public void onAction(Remix remix) {
		isCompleted = 1;

		if (cue != null) {
			Sound sfx = cue.getSFX();
			Sound oneTime = cue.getOneTimeSFX();

			soundId = sfx.play(1,
					cue.pitchWithBpm > 0 ? Remix.getPitchFromBpm(remix.bpm, cue.pitchWithBpm) : 1,
					0);

			if (oneTime != null) {
				long id = oneTime.play(1, cue.pitchWithBpm > 0
						? Remix.getPitchFromBpm(remix.bpm, cue.pitchWithBpm) : 1, 0);

				if (id != -1) {
					oneTime.setLooping(id, false);
				}
			}

			if (soundId != -1) {
				if (cue.soundLoops) {
					sfx.setLooping(soundId, true);
				}
			}

		}
	}

	public void onEnd(Remix remix) {
		isCompleted = 2;

		if (cue != null) {
			Sound sfx = cue.getSFX();

			if (soundId != -1) {
				if (cue.soundLoops) {
					sfx.setLooping(soundId, false);
					sfx.stop(soundId);
				}
			}

		}
	}

	public void reset() {
		isCompleted = 0;

		if (soundId != -1) {
			Sound sfx = cue.getSFX();

			sfx.setLooping(soundId, false);
		}

		soundId = -1;
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
