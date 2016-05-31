package chrislo27.remixer.track;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.utils.PreDiv;
import ionium.templates.Main;

public class Remix {

	public Array<Array<SoundEffect>> tracks = new Array<>();

	private float lastBeat = 0;

	private boolean isStopped = true;
	private boolean isPaused = false;
	private float beat = 0;

	public int bpm = 120;

	private Music music = null;
	public float musicStartTime = 0;

	public Remix(int trackCount) {
		for (int i = 0; i < trackCount; i++) {
			this.tracks.add(new Array<SoundEffect>());
		}
	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music mus) {
		music = mus;

		if (music != null) {
			music.stop();
			music.setLooping(false);
		}
	}

	public boolean isStarted() {
		return !isStopped;
	}

	public float getCurrentBeat() {
		return beat;
	}

	public void setCurrentBeat(float b) {
		beat = b;

		if (music != null) {
			music.setPosition(getSecFromBeat(beat, bpm));
		}
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void pause() {
		isStopped = true;
		isPaused = true;

		if (music != null) music.pause();
	}

	public void stop() {
		pause();

		isPaused = false;

		beat = music != null ? (musicStartTime < 0 ? getBeatFromSec(musicStartTime, bpm) : 0) : 0;
		if (music != null) music.stop();

		for (Array<SoundEffect> track : tracks) {
			for (SoundEffect a : track) {
				a.reset();
			}
		}

	}

	public void start() {
		isStopped = false;
		isPaused = false;
		recalculate();

		if (music != null && !music.isPlaying()) {
			music.setPosition(getSecFromBeat(beat, bpm) - musicStartTime);
			music.play();
		}
	}

	public void update(float delta, boolean onlySelected) {
		if (isStopped) return;

		if (music != null && music.isPlaying()) {
			beat = getBeatFromSec(music.getPosition() + musicStartTime, bpm);
		} else {
			beat += getBeatFromSec(delta, bpm);
		}

		for (Array<SoundEffect> track : tracks) {
			for (SoundEffect a : track) {
				if (a.isCompleted) continue;
				if (onlySelected && !a.selected) continue;
				if (a.beat <= beat && a.beat + a.cue.duration >= beat) a.onAction();
			}
		}

		if (beat >= lastBeat) {
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

	public void setLastBeat(float b) {
		lastBeat = b;
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
