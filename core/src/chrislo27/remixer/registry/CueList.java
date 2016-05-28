package chrislo27.remixer.registry;

import com.badlogic.gdx.audio.Sound;

import chrislo27.remixer.game.Game;
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

	public BiObjectMap<String, Cue> cues = new BiObjectMap<>();

	private void loadResources() {
		{
			Game ls = GameList.getGame("lockstep");

			put(new Cue(ls, "lockstep", "hai", 0.5f));
			put(new Cue(ls, "lockstep", "backbeat_ha", 0.5f));
			put(new Cue(ls, "lockstep", "backbeat_hoi", 0.5f));
			put(new Cue(ls, "lockstep", "backbeat_boh", 0.5f));
			put(new Cue(ls, "lockstep", "return_hee", 0.5f));
			put(new Cue(ls, "lockstep", "return_ha", 0.5f));
			put(new Cue(ls, "lockstep", "march", 0.5f));
			put(new Cue(ls, "lockstep", "march_backbeat", 0.5f));
		}
	}

	public void put(Cue cue) {
		cues.put(cue.folder + "_" + cue.file, cue);
	}

	public static Cue getCue(String key) {
		return instance().cues.getValue(key);
	}

	public static String getKey(Cue cue) {
		return instance().cues.getKey(cue);
	}

	public static class Cue {

		public final Game game;
		public final String folder;
		public final String file;
		public final String soundId;
		public final float duration;

		public Cue(Game game, String folder, String file, float duration) {
			this.game = game;
			this.folder = folder;
			this.file = file;
			this.duration = duration;

			soundId = "cue_" + folder + "_" + file;
		}

		public Sound getSFX() {
			return AssetRegistry.getSound(soundId);
		}

	}
}
