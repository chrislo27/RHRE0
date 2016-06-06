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
				array.add(new SoundEffect(0, "dogNinja_fruit1"));
				array.add(new SoundEffect(1, "dogNinja_fruit2"));
			}
		});

		this.patterns.put("bone", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "dogNinja_bone1"));
				array.add(new SoundEffect(1, "dogNinja_bone2"));
			}
		});

		this.patterns.put("pan", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "dogNinja_pan1"));
				array.add(new SoundEffect(1, "dogNinja_pan2"));
			}
		});

		this.patterns.put("tire", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "dogNinja_tire1"));
				array.add(new SoundEffect(1, "dogNinja_tire2"));
			}
		});
	}

}
