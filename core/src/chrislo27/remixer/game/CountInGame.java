package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class CountInGame extends Game {

	public CountInGame(String name) {
		super(name);

		this.patterns.put("and", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "countIn_and"));
			}
		});
		this.patterns.put("cowbell", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "countIn_cowbell"));
			}
		});
		this.patterns.put("one", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "countIn_one"));
			}
		});
		this.patterns.put("two", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "countIn_two"));
			}
		});
		this.patterns.put("three", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "countIn_three"));
			}
		});
		this.patterns.put("four", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "countIn_four"));
			}
		});
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Game) {
			return -1;
		}

		return super.compareTo(o);
	}

}
