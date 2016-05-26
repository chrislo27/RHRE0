package chrislo27.remixer.track;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.utils.PreDiv;

public class Remix {

	private Array<Array<Action>> tracks = new Array<>();

	private float lastBeat = 0;

	private boolean isStopped = true;
	private float beat = 0;

	public int bpm = 120;

	public Remix() {

	}

	public void stop() {
		beat = 0;
		isStopped = true;

		for (Array<Action> track : tracks) {
			for (Action a : track) {
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

		for (Array<Action> track : tracks) {
			for (Action a : track) {
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

			for (Array<Action> track : tracks) {
				for (Action a : track) {
					if (a.beat > last) last = a.beat;
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
