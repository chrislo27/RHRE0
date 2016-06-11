package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class Splashdown extends Game {

	public Splashdown(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("alley oop", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "splashdown_oooh"));
				array.add(new SoundEffect(2.5f, "splashdown_alleyoop"));
			}
		});

		this.patterns.put("dolphin", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "splashdown_yeah1"));
				array.add(new SoundEffect(5 / 6f, "splashdown_spin1"));
			}
		});
		
		this.patterns.put("dolphin end", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "splashdown_yeah2"));
				array.add(new SoundEffect(5 / 6f, "splashdown_spin2"));
			}
		});
	}

}
