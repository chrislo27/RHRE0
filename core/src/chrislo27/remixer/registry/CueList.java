package chrislo27.remixer.registry;

import chrislo27.remixer.game.Cue;
import ionium.util.BiObjectMap;

public class CueList {

	private static CueList instance;

	private CueList() {
	}

	public static CueList instance() {
		if (instance == null) {
			instance = new CueList();
			instance.loadResources();
		}
		return instance;
	}

	public final BiObjectMap<String, Cue> map = new BiObjectMap<>();

	private void loadResources() {

	}

	public static Cue getCue(String id) {
		return instance().map.getValue(id);
	}

	public static String getKey(Cue c) {
		return instance().map.getKey(c);
	}

}
