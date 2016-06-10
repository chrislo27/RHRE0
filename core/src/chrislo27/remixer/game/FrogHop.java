package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class FrogHop extends Game {

	public FrogHop(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("shake your hips", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "frogHop_shake"));
				array.add(new SoundEffect(1, "frogHop_shake"));
			}
		});

		this.patterns.put("yahoo cue", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "frogHop_yahoo1"));
				array.add(new SoundEffect(0.5f, "frogHop_yahoo2"));

				array.add(new SoundEffect(2, "frogHop_yahoo3"));
				array.add(new SoundEffect(2.5f, "frogHop_yahoo4"));
			}
		});

		this.patterns.put("yeah yeah yeah cue", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "frogHop_yeah1"));
				array.add(new SoundEffect(0.5f, "frogHop_yeah1"));
				array.add(new SoundEffect(1, "frogHop_yeah1"));

				array.add(new SoundEffect(2, "frogHop_yeah2"));
				array.add(new SoundEffect(2.5f, "frogHop_yeah2"));
				array.add(new SoundEffect(3, "frogHop_yeah2"));
			}
		});

		this.patterns.put("spin it, boys! cue", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "frogHop_spinitboys1"));
				array.add(new SoundEffect(0.5f, "frogHop_spinitboys2"));
				array.add(new SoundEffect(1, "frogHop_spinitboys3"));

				array.add(new SoundEffect(2, "frogHop_spinitboys4"));
				array.add(new SoundEffect(2.5f, "frogHop_spinitboys5"));
				array.add(new SoundEffect(3, "frogHop_spinitboys6"));
			}
		});
	}

}
