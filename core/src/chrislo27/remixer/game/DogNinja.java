package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class DogNinja extends Game {

	public DogNinja(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("fruit", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "fruit1"));
				array.add(new SoundEffect(1, "fruit2"));
			}
		});

		this.patterns.put("bone", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "bone1"));
				array.add(new SoundEffect(1, "bone2"));
			}
		});

		this.patterns.put("pan", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "pan1"));
				array.add(new SoundEffect(1, "pan2"));
			}
		});

		this.patterns.put("tire", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "tire1"));
				array.add(new SoundEffect(1, "tire2"));
			}
		});
	}

}
