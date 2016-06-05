package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class DonkDonk extends Game {

	public DonkDonk(String name) {
		super(name, null);

		this.patterns.put("donk-donk", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "donkDonk_donk1"));
				array.add(new SoundEffect(2 / 3f, "donkDonk_donk2"));
			}
		});

		this.patterns.put("donk-DWONK", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "donkDonk_dwonk1"));
				array.add(new SoundEffect(2 / 3f, "donkDonk_dwonk2"));
			}
		});

		this.patterns.put("deet-deet-duh", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "donkDonk_deetdeetduh1"));
				array.add(new SoundEffect(2 / 3f, "donkDonk_deetdeetduh2"));
				array.add(new SoundEffect(4 / 3f, "donkDonk_deetdeetduh3"));
			}
		});

		this.patterns.put("deet-deet-doot", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "donkDonk_deetdeetdoot1"));
				array.add(new SoundEffect(2 / 3f, "donkDonk_deetdeetdoot2"));
				array.add(new SoundEffect(4 / 3f, "donkDonk_deetdeetdoot3"));
			}
		});

		this.patterns.put("blastoff", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "donkDonk_blastoff"));
			}
		});
	}

}
