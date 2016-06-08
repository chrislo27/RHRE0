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
				array.add(new SoundEffect(1 / 3f, "shrimpShuffle_ge"));
				array.add(new SoundEffect(1, "shrimpShuffle_ther"));
			}
		});

		this.patterns.put("123 321 swing", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "shrimpShuffle_one1"));
				array.add(new SoundEffect(1, "shrimpShuffle_two1"));
				array.add(new SoundEffect(2, "shrimpShuffle_three1"));

				array.add(new SoundEffect(3f + 4 / 6f, "shrimpShuffle_three2"));
				array.add(new SoundEffect(4 + 4 / 6f, "shrimpShuffle_two2"));
				array.add(new SoundEffect(6, "shrimpShuffle_one2"));
			}
		});
		
		this.patterns.put("123 ABC swing", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "shrimpShuffle_one1"));
				array.add(new SoundEffect(1, "shrimpShuffle_two1"));
				array.add(new SoundEffect(2, "shrimpShuffle_three1"));

				array.add(new SoundEffect(3f + 4 / 6f, "shrimpShuffle_a"));
				array.add(new SoundEffect(4 + 4 / 6f, "shrimpShuffle_b"));
				array.add(new SoundEffect(6, "shrimpShuffle_c"));
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
		
		this.patterns.put("123 321 no swing", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "shrimpShuffle_one1"));
				array.add(new SoundEffect(1, "shrimpShuffle_two1"));
				array.add(new SoundEffect(2, "shrimpShuffle_three1"));

				array.add(new SoundEffect(3f + 3 / 6f, "shrimpShuffle_three2"));
				array.add(new SoundEffect(4 + 3 / 6f, "shrimpShuffle_two2"));
				array.add(new SoundEffect(6, "shrimpShuffle_one2"));
			}
		});
		
		this.patterns.put("123 ABC no swing", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "shrimpShuffle_one1"));
				array.add(new SoundEffect(1, "shrimpShuffle_two1"));
				array.add(new SoundEffect(2, "shrimpShuffle_three1"));

				array.add(new SoundEffect(3f + 3 / 6f, "shrimpShuffle_a"));
				array.add(new SoundEffect(4 + 3 / 6f, "shrimpShuffle_b"));
				array.add(new SoundEffect(6, "shrimpShuffle_c"));
			}
		});
	}

}
