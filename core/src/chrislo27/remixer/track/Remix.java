package chrislo27.remixer.track;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.game.Game;
import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.registry.GameList;
import chrislo27.remixer.utils.PreDiv;

public class Remix {

	public Array<Array<SoundEffect>> tracks = new Array<>();

	private float lastBeat = 0;

	private boolean isStopped = true;
	private float beat = 0;

	public int bpm = 120;

	public Remix(int trackCount) {
		for (int i = 0; i < trackCount; i++) {
			this.tracks.add(new Array<SoundEffect>());
		}

		Game g = GameList.getGame("lockstep");

		Array<Pattern> patterns = g.patterns.getAllValues();
		for (int i = 0; i < patterns.size; i++) {
			Pattern p = patterns.get(i);

			p.addPatternToArray(this.tracks.get(i));
		}
	}

	public boolean isStarted() {
		return !isStopped;
	}

	public float getCurrentBeat() {
		return beat;
	}

	public void stop() {
		beat = 0;
		isStopped = true;

		for (Array<SoundEffect> track : tracks) {
			for (SoundEffect a : track) {
				a.reset();
			}
		}

	}

	public void start() {
		isStopped = false;
		recalculate();
	}

	public void update(float delta) {
		if (isStopped) return;

		beat += getBeatFromSec(delta, bpm);

		for (Array<SoundEffect> track : tracks) {
			for (SoundEffect a : track) {
				if (a.isCompleted) continue;
				if (a.beat <= beat) a.onAction();
			}
		}

		if (beat > lastBeat) {
			stop();
		}
	}

	public void recalculate() {
		// last beat
		{
			float last = 0;

			for (Array<SoundEffect> track : tracks) {
				for (SoundEffect a : track) {
					float stopArea = a.beat + (a.cue != null ? a.cue.duration : 0);
					
					if (stopArea > last) last = stopArea;
				}
			}

			lastBeat = last;
		}
	}

	public float getLastBeat() {
		return lastBeat;
	}

	public static float getBeatFromSec(float sec, float bpm) {
		return sec * (bpm * PreDiv.SIXTIETH);
	}

	public static float getSecFromBeat(float beat, float bpm) {
		return beat / (bpm * PreDiv.SIXTIETH);
	}

	public static float getPitchFromBpm(float actualBpm, float bpm) {
		return bpm * 1f / actualBpm;
	}

}
