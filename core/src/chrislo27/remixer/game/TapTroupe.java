package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class TapTroupe extends Game {

	public TapTroupe(String name) {
		super(name);

		this.patterns.put("and-", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "tapTroupe_and"));
			}

		});

		this.patterns.put("ready!", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "tapTroupe_ready1"));
				array.add(new SoundEffect(1, "tapTroupe_ready2"));
			}

		});

		this.patterns.put("stepping", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "tapTroupe_step1"));
				array.add(new SoundEffect(1, "tapTroupe_step2"));
			}

		});

		this.patterns.put("normal OK!", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "tapTroupe_ok1"));
			}

		});

		this.patterns.put("excited OK!", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "tapTroupe_ok2"));
			}

		});

		this.patterns.put("tap tap tap!", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "tapTroupe_taptaptap1"));
				array.add(new SoundEffect(0.75f, "tapTroupe_taptaptap2"));
				array.add(new SoundEffect(1.5f, "tapTroupe_taptaptap3"));
			}

		});

		this.patterns.put("bom-bom", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "tapTroupe_bombom1"));
				array.add(new SoundEffect(0.75f, "tapTroupe_bombom2"));
			}

		});
	}

}
