package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class StairCatch extends Game {

	public StairCatch(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("orange bounce", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "stairCatch_orange"));
				array.add(new SoundEffect(1, "stairCatch_orange"));
				array.add(new SoundEffect(2, "stairCatch_orange"));

				array.add(new SoundEffect(3, "stairCatch_clap"));
			}
		});

		this.patterns.put("melon bounce", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "stairCatch_melon"));
				array.add(new SoundEffect(2, "stairCatch_melon"));
				array.add(new SoundEffect(4, "stairCatch_melon"));

				array.add(new SoundEffect(6, "stairCatch_clap"));
			}
		});
	}

}
