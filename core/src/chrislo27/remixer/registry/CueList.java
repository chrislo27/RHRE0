package chrislo27.remixer.registry;

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

	private BiObjectMap<String, String> map = new BiObjectMap<>();

	private void loadResources() {

	}

	public static String getSFX(String key) {
		return instance().map.getValue(key);
	}

	public static String getKey(String sfx) {
		return instance().map.getKey(sfx);
	}

	public BiObjectMap getMap() {
		return map;
	}

}
