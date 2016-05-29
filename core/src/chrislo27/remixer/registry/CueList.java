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

			put(new Cue(ls, ls.name, "hai", 0.5f));
			put(new Cue(ls, ls.name, "bkbt_ha", 0.5f));
			put(new Cue(ls, ls.name, "bkbt_hoi", 0.5f));
			put(new Cue(ls, ls.name, "bkbt_boh", 0.5f));
			put(new Cue(ls, ls.name, "return_hee", 0.5f));
			put(new Cue(ls, ls.name, "return_ha", 0.5f));
			put(new Cue(ls, ls.name, "march1", 0.5f));
			put(new Cue(ls, ls.name, "march2", 0.5f));
			put(new Cue(ls, ls.name, "march1_bkbt", 0.5f));
			put(new Cue(ls, ls.name, "march2_bkbt", 0.5f));
		}

		{
			Game mm = GameList.getGame("munchyMonk");

			put(new Cue(mm, mm.name, "gulp", 0.5f));
			put(new Cue(mm, mm.name, "gulp3-1", 0.5f));
			put(new Cue(mm, mm.name, "gulp3-2", 0.5f));
			put(new Cue(mm, mm.name, "gulp3-3", 0.5f));
			put(new Cue(mm, mm.name, "one", 0.5f));
			put(new Cue(mm, mm.name, "three", 0.5f));
			put(new Cue(mm, mm.name, "try", 0.5f));
			put(new Cue(mm, mm.name, "two", 0.5f));
		}

		{
			Game dd = GameList.getGame("donkDonk");
			float third = 1f / 3f;

			put(new Cue(dd, dd.name, "blastoff", 2f));
			put(new Cue(dd, dd.name, "deetdeetdoot1", third));
			put(new Cue(dd, dd.name, "deetdeetdoot2", third));
			put(new Cue(dd, dd.name, "deetdeetdoot3", third));
			put(new Cue(dd, dd.name, "deetdeetduh1", third));
			put(new Cue(dd, dd.name, "deetdeetduh2", third));
			put(new Cue(dd, dd.name, "deetdeetduh3", third));
			put(new Cue(dd, dd.name, "donk1", 0.5f));
			put(new Cue(dd, dd.name, "donk2", 0.5f));
			put(new Cue(dd, dd.name, "dwonk1", 0.5f));
			put(new Cue(dd, dd.name, "dwonk2", 0.5f));
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
