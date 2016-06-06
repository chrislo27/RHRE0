package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class ShrimpShuffle extends Game {

	public ShrimpShuffle(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("together", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "shrimpShuffle_to"));
				array.add(new SoundEffect(3 / 8f, "shrimpShuffle_ge"));
				array.add(new SoundEffect(1, "shrimpShuffle_ther"));
			}
		});

		this.patterns.put("123 321", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "shrimpShuffle_one1"));
				array.add(new SoundEffect(1, "shrimpShuffle_two1"));
				array.add(new SoundEffect(2, "shrimpShuffle_three1"));

				array.add(new SoundEffect(3.5f, "shrimpShuffle_three2"));
				array.add(new SoundEffect(4.375f, "shrimpShuffle_two2"));
				array.add(new SoundEffect(5.5f, "shrimpShuffle_one2"));
			}
		});
		
		this.patterns.put("123 ABC", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "shrimpShuffle_one1"));
				array.add(new SoundEffect(1, "shrimpShuffle_two1"));
				array.add(new SoundEffect(2, "shrimpShuffle_three1"));

				array.add(new SoundEffect(3.5f, "shrimpShuffle_a"));
				array.add(new SoundEffect(4.375f, "shrimpShuffle_b"));
				array.add(new SoundEffect(5.5f, "shrimpShuffle_c"));
			}
		});

		this.patterns.put("awaha uhn", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "shrimpShuffle_ah"));
				array.add(new SoundEffect(0.25f, "shrimpShuffle_wa"));
				array.add(new SoundEffect(0.75f, "shrimpShuffle_ha"));
				array.add(new SoundEffect(1.25f, "shrimpShuffle_uhn"));
			}
		});
	}

}
