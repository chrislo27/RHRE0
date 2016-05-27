package chrislo27.remixer.registry;

import com.badlogic.gdx.audio.Sound;

import ionium.registry.AssetRegistry;
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

	private BiObjectMap<String, Cue> map = new BiObjectMap<>();

	private void loadResources() {

	}

	public void put(Cue cue) {
		map.put(cue.name, cue);
	}

	public static String getCueName(String key) {
		return getCue(key).name;
	}

	public static String getCueGame(String key) {
		return getCue(key).game;
	}

	public static Sound getSFXSound(String key) {
		return AssetRegistry.getSound("cue_" + key);
	}

	public static Cue getCue(String key) {
		return instance().map.getValue(key);
	}

	public static String getKey(Cue cue) {
		return instance().map.getKey(cue);
	}

	public BiObjectMap<String, Cue> getMap() {
		return map;
	}

	public static class Cue {

		/**
		 * Game for icon
		 */
		public final String game;
		/**
		 * Name for localization and keys
		 */
		public final String name;
		/**
		 * Path in sounds/cues/
		 */
		public final String path;

		public Cue(String game, String name, String path) {
			this.game = game;
			this.name = name;
			this.path = path;
		}

	}

}
