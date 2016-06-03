package chrislo27.remixer.registry;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.game.Game;
import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;
import ionium.registry.AssetRegistry;
import ionium.templates.Main;
import ionium.util.BiObjectMap;
import ionium.util.i18n.Localization;

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
			Game ci = GameList.getGame("countIn");

			put(new Cue(ci, ci.name, "and", 0.5f));
			put(new Cue(ci, ci.name, "cowbell", 0.5f));
			put(new Cue(ci, ci.name, "one", 0.5f));
			put(new Cue(ci, ci.name, "two", 0.5f));
			put(new Cue(ci, ci.name, "three", 0.5f));
			put(new Cue(ci, ci.name, "four", 0.5f));
		}

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

		{
			Game tt = GameList.getGame("tapTroupe");
			float dottedEighth = 0.75f;

			put(new Cue(tt, tt.name, "and", 0.25f));
			put(new Cue(tt, tt.name, "bombom1", 0.75f));
			put(new Cue(tt, tt.name, "bombom2", 0.75f));
			put(new Cue(tt, tt.name, "ready1", 0.5f));
			put(new Cue(tt, tt.name, "ready2", 0.5f));
			put(new Cue(tt, tt.name, "step1", 0.5f));
			put(new Cue(tt, tt.name, "step2", 0.5f));
			put(new Cue(tt, tt.name, "tapNook", 0.75f));
			put(new Cue(tt, tt.name, "taptaptap1", 0.75f));
			put(new Cue(tt, tt.name, "taptaptap2", 0.75f));
			putDeprecated("taptaptap3", "tapNook");
		}

		{
			Game tt = GameList.getGame("tapTrial");

			put(new Cue(tt, tt.name, "jumptap1_tap", 0.5f));
			put(new Cue(tt, tt.name, "jumptap1", 0.5f));
			put(new Cue(tt, tt.name, "jumptap2_tap", 0.5f));
			put(new Cue(tt, tt.name, "jumptap2", 0.5f));
			put(new Cue(tt, tt.name, "ook", 0.5f));
			put(new Cue(tt, tt.name, "ooki1", 0.5f));
			put(new Cue(tt, tt.name, "ooki2", 0.5f));
			put(new Cue(tt, tt.name, "ookook1", 0.5f));
			put(new Cue(tt, tt.name, "ookook2", 0.5f));
			put(new Cue(tt, tt.name, "tap", 0.5f));
		}

		// add individual cues as patterns too
		Array<SoundEffect> tmp = new Array<>();

		outer: for (final Cue c : cues.getAllValues()) {
			String id = Localization.get(c.soundId);
			id = "Cue - " + id;
			id = id.replace("\n", " - ");

			for (Pattern p : c.game.patterns.getAllValues()) {
				tmp.clear();
				p.addPatternToArray(tmp);

				if (tmp.size == 1) {
					if (tmp.first().beat == 0 && tmp.first().cue == c) {
						continue outer;
					}
				}
			}

			c.game.patterns.put(id, new Pattern(c.game) {

				@Override
				public void addPatternToArray(Array<SoundEffect> array) {
					array.add(new SoundEffect(0, c));
				}
			});
		}

		tmp.clear();
		tmp = null;
		System.gc();

	}

	public void put(Cue cue) {
		cues.put(cue.folder + "_" + cue.file, cue);
	}

	public void putDeprecated(String oldKey, String newKey) {
		if (cues.getValue(newKey) == null) return;

		cues.put(oldKey, cues.getValue(newKey));
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
