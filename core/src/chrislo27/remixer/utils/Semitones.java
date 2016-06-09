package chrislo27.remixer.utils;

import com.badlogic.gdx.utils.IntMap;

import ionium.util.Utils;

public class Semitones {

	public static final int SEMITONES_IN_OCTAVE = 12;
	public static final float SEMITONE_VALUE = 1f / SEMITONES_IN_OCTAVE;
	private static final IntMap<Float> cachedPitches = new IntMap<>();

	public static String getSemitoneName(int semitone) {
		String thing;

		switch (Math.abs(Math.floorMod(semitone, SEMITONES_IN_OCTAVE))) {
		case 0:
			thing = "C";
			break;
		case 1:
			thing = "C#";
			break;
		case 2:
			thing = "D";
			break;
		case 3:
			thing = "D#";
			break;
		case 4:
			thing = "E";
			break;
		case 5:
			thing = "F";
			break;
		case 6:
			thing = "F#";
			break;
		case 7:
			thing = "G";
			break;
		case 8:
			thing = "G#";
			break;
		case 9:
			thing = "A";
			break;
		case 10:
			thing = "A#";
			break;
		case 11:
			thing = "B";
			break;
		default:
			thing = "N/A";
			break;
		}

		if (semitone >= 12 || semitone <= -1) {
			thing += Utils.repeat(semitone > 0 ? "+" : "-",
					Math.abs(Math.floorDiv(semitone, SEMITONES_IN_OCTAVE)));
		}

		return thing;
	}

	public static float getALPitch(int semitone) {
		if (cachedPitches.get(semitone) == null) {
			cachedPitches.put(semitone, (float) Math.pow(2.0, semitone * SEMITONE_VALUE));
		}

		return cachedPitches.get(semitone);
	}

}
