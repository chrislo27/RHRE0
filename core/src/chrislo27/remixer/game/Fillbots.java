package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class Fillbots extends Game {

	public Fillbots(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("medium bot", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "fillbots_mediumFall"));
				array.add(new SoundEffect(1, "fillbots_mediumFall"));
				array.add(new SoundEffect(2, "fillbots_mediumFall"));

				array.add(new SoundEffect(4, "fillbots_mediumBot"));
			}
		});

		this.patterns.put("large bot", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "fillbots_bigFall"));
				array.add(new SoundEffect(1, "fillbots_bigFall"));
				array.add(new SoundEffect(2, "fillbots_bigFall"));

				array.add(new SoundEffect(4, "fillbots_bigBot"));
			}
		});

		this.patterns.put("small bot", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "fillbots_smallFall"));
				array.add(new SoundEffect(1, "fillbots_smallFall"));
				array.add(new SoundEffect(2, "fillbots_smallFall"));

				array.add(new SoundEffect(4, "fillbots_smallBot"));
			}
		});
	}

}
