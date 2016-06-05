package chrislo27.remixer.registry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
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
			Game custom = GameList.getGame("custom");
			FileHandle folder = Gdx.files.local("custom/");
			if (folder.exists() && folder.isDirectory()) {
				FileHandle[] all = folder.list(".ogg");

				Main.logger.info("Custom sound folder located with " + all.length + " legal files");

				for (FileHandle fh : all) {
					put(new Cue(custom, "", custom.name, fh.nameWithoutExtension(), 0.5f));

					Main.logger.info("Added custom sound " + fh.nameWithoutExtension());
				}
			}
		}

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
			put(new Cue(ls, ls.name, "corruptHai", 0.5f));
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
			put(new Cue(dd, dd.name, "deetdeetdoot1", third * 2));
			put(new Cue(dd, dd.name, "deetdeetdoot2", third * 2));
			put(new Cue(dd, dd.name, "deetdeetdoot3", third * 2));
			put(new Cue(dd, dd.name, "deetdeetduh1", third * 2));
			put(new Cue(dd, dd.name, "deetdeetduh2", third * 2));
			put(new Cue(dd, dd.name, "deetdeetduh3", third * 2));
			put(new Cue(dd, dd.name, "donk1", third * 2));
			put(new Cue(dd, dd.name, "donk2", third * 2));
			put(new Cue(dd, dd.name, "dwonk1", third * 2));
			put(new Cue(dd, dd.name, "dwonk2", third * 2));
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
			put(new Cue(tt, tt.name, "tapNook", 0.75f), "taptaptap3");
			put(new Cue(tt, tt.name, "taptaptap1", 0.75f));
			put(new Cue(tt, tt.name, "taptaptap2", 0.75f));
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

		{
			Game sd = GameList.getGame("spaceDance");

			put(new Cue(sd, sd.name, "turn1", 0.5f));
			put(new Cue(sd, sd.name, "right1", 0.5f));
			put(new Cue(sd, sd.name, "lets1", 0.5f));
			put(new Cue(sd, sd.name, "sit1", 0.5f));
			put(new Cue(sd, sd.name, "down1", 0.5f));
			put(new Cue(sd, sd.name, "pa1", 0.5f));
			put(new Cue(sd, sd.name, "punch1", 1.5f));
			put(new Cue(sd, sd.name, "turn2", 0.5f));
			put(new Cue(sd, sd.name, "right2", 0.5f));
			put(new Cue(sd, sd.name, "lets2", 0.5f));
			put(new Cue(sd, sd.name, "sit2", 0.5f));
			put(new Cue(sd, sd.name, "down2", 0.5f));
			put(new Cue(sd, sd.name, "pa2", 0.5f));
			put(new Cue(sd, sd.name, "punch2", 1.5f));
		}

		{
			Game bb = GameList.getGame("blueBirds");

			put(new Cue(bb, bb.name, "peck", 0.5f));
			put(new Cue(bb, bb.name, "peckyourbeak1", 0.5f));
			put(new Cue(bb, bb.name, "peckyourbeak2", 0.5f));
			put(new Cue(bb, bb.name, "peckyourbeak3", 0.5f));
			put(new Cue(bb, bb.name, "stretch1", 0.5f));
			put(new Cue(bb, bb.name, "stretch2", 0.5f));
			put(new Cue(bb, bb.name, "stretchoutyourneck1", 4 / 6f));
			put(new Cue(bb, bb.name, "stretchoutyourneck2", 0.5f));
			put(new Cue(bb, bb.name, "stretchoutyourneck3", 0.5f));
			put(new Cue(bb, bb.name, "stretchoutyourneck4", 0.4f));
		}

		{
			Game cs = GameList.getGame("cropStomp");

			put(new Cue(cs, cs.name, "molefling", 0.5f));
			put(new Cue(cs, cs.name, "pick1", 0.5f));
			put(new Cue(cs, cs.name, "pick2", 0.5f));
			put(new Cue(cs, cs.name, "stomp", 0.5f));
			put(new Cue(cs, cs.name, "walk", 0.5f));
		}

		{
			Game rs = GameList.getGame("ringside");
			final float third = 1 / 3f;

			put(new Cue(rs, rs.name, "wubba1-1", third));
			put(new Cue(rs, rs.name, "wubba1-2", third));
			put(new Cue(rs, rs.name, "dubba1-1", third));
			put(new Cue(rs, rs.name, "dubba1-2", third));
			put(new Cue(rs, rs.name, "dubba1-3", third));
			put(new Cue(rs, rs.name, "dubba1-4", third));
			put(new Cue(rs, rs.name, "that1", third * 2));
			put(new Cue(rs, rs.name, "true1", third));
			put(new Cue(rs, rs.name, "woah1", third * 2));
			put(new Cue(rs, rs.name, "you1", third));
			put(new Cue(rs, rs.name, "go1", third * 2));
			put(new Cue(rs, rs.name, "big1", third));
			put(new Cue(rs, rs.name, "guy1", 0.5f));
			put(new Cue(rs, rs.name, "pose1", 0.5f));
			put(new Cue(rs, rs.name, "for1", 1 / 6f));
			put(new Cue(rs, rs.name, "the1", third));
			put(new Cue(rs, rs.name, "fans1", 0.5f));
			put(new Cue(rs, rs.name, "wubba2-1", third));
			put(new Cue(rs, rs.name, "wubba2-2", third));
			put(new Cue(rs, rs.name, "dubba2-1", third));
			put(new Cue(rs, rs.name, "dubba2-2", third));
			put(new Cue(rs, rs.name, "dubba2-3", third));
			put(new Cue(rs, rs.name, "dubba2-4", third));
			put(new Cue(rs, rs.name, "that2", third * 2));
			put(new Cue(rs, rs.name, "true2", third));
			put(new Cue(rs, rs.name, "woah2", third * 2));
			put(new Cue(rs, rs.name, "you2", third));
			put(new Cue(rs, rs.name, "go2", third * 2));
			put(new Cue(rs, rs.name, "big2", third));
			put(new Cue(rs, rs.name, "guy2", 0.5f));
			put(new Cue(rs, rs.name, "pose2", 0.5f));
			put(new Cue(rs, rs.name, "for2", 1 / 6f));
			put(new Cue(rs, rs.name, "the2", third));
			put(new Cue(rs, rs.name, "fans2", 0.5f));
			put(new Cue(rs, rs.name, "wubba3-1", third));
			put(new Cue(rs, rs.name, "wubba3-2", third));
			put(new Cue(rs, rs.name, "dubba3-1", third));
			put(new Cue(rs, rs.name, "dubba3-2", third));
			put(new Cue(rs, rs.name, "dubba3-3", third));
			put(new Cue(rs, rs.name, "dubba3-4", third));
			put(new Cue(rs, rs.name, "that3", third * 2));
			put(new Cue(rs, rs.name, "true3", third));
			put(new Cue(rs, rs.name, "woah3", third * 2));
			put(new Cue(rs, rs.name, "you3", third));
			put(new Cue(rs, rs.name, "go3", third * 2));
			put(new Cue(rs, rs.name, "big3", third));
			put(new Cue(rs, rs.name, "guy3", 0.5f));
			put(new Cue(rs, rs.name, "pose3", 0.5f));
			put(new Cue(rs, rs.name, "for3", 1 / 6f));
			put(new Cue(rs, rs.name, "the3", third));
			put(new Cue(rs, rs.name, "fans3", 0.5f));
			put(new Cue(rs, rs.name, "poseAnd", 0.5f));
			put(new Cue(rs, rs.name, "ye", 1));
			put(new Cue(rs, rs.name, "yell1", 0.5f));
			put(new Cue(rs, rs.name, "yell2", 0.5f));
			put(new Cue(rs, rs.name, "yell3", 0.5f));
			put(new Cue(rs, rs.name, "camera1", 1));
			put(new Cue(rs, rs.name, "camera2", 1));
			put(new Cue(rs, rs.name, "camera3", 1));
			put(new Cue(rs, rs.name, "muscles1", 0.5f));
			put(new Cue(rs, rs.name, "muscles2", 0.5f));
		}

		{
			Game shoot = GameList.getGame("shootEmUp");

			put(new Cue(shoot, shoot.name, "spawn", 0.25f));
			put(new Cue(shoot, shoot.name, "shoot", 0.25f));
			put(new Cue(shoot, shoot.name, "commStart", 1));
			put(new Cue(shoot, shoot.name, "commEnd", 1));
		}
		
		{
			Game wd = GameList.getGame("workingDough");
			
			put(new Cue(wd, wd.name, "leftSmall", 0.25f));
			put(new Cue(wd, wd.name, "leftBig", 0.25f));
			put(new Cue(wd, wd.name, "rightSmall", 0.25f));
			put(new Cue(wd, wd.name, "rightBig", 0.25f));
		}
		
		{
			Game mdw = GameList.getGame("moaiDooWop2");
			
			put(new Cue(mdw, mdw.name, "d1", 0.125f));
			put(new Cue(mdw, mdw.name, "ooo1", 2.125f));
			put(new Cue(mdw, mdw.name, "wop1", 0.5f));
			put(new Cue(mdw, mdw.name, "pah1", 0.5f));
			
			put(new Cue(mdw, mdw.name, "d2", 0.125f));
			put(new Cue(mdw, mdw.name, "ooo2", 2.125f));
			put(new Cue(mdw, mdw.name, "wop2", 0.5f));
			put(new Cue(mdw, mdw.name, "pah2", 0.5f));
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

	public void put(Cue cue, String... deprecatedNames) {
		cues.put(cue.folder + "_" + cue.file, cue);

		if (deprecatedNames.length > 0) {
			for (String s : deprecatedNames) {
				cues.put(cue.folder + "_" + s, cue);
			}
		}
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
		public final String folderParent;
		public int pitchWithBpm = -1;

		public Cue(Game game, String folderParent, String folder, String file, float duration) {
			this.game = game;
			this.folder = folder;
			this.file = file;
			this.duration = duration;
			this.folderParent = folderParent;

			soundId = "cue_" + folder + "_" + file;
		}

		public Cue(Game game, String folder, String file, float duration) {
			this(game, "sounds/cues/", folder, file, duration);
		}

		public Cue setUsesPitch(int baseBpm) {
			pitchWithBpm = baseBpm;

			return this;
		}

		public Sound getSFX() {
			return AssetRegistry.getSound(soundId);
		}

	}
}
