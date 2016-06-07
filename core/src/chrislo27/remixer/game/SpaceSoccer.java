package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class SpaceSoccer extends Game {

	public SpaceSoccer(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("dispense", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "spaceSoccer_dispense1"));
				array.add(new SoundEffect(0.75f, "spaceSoccer_dispense2"));
				array.add(new SoundEffect(1, "spaceSoccer_dispense3"));
				array.add(new SoundEffect(1.25f, "spaceSoccer_dispense4"));
				array.add(new SoundEffect(1.5f, "spaceSoccer_dispense5"));
			}
		});

		this.patterns.put("kick kick", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "spaceSoccer_kick"));
				array.add(new SoundEffect(1, "spaceSoccer_kick"));
			}
		});

		this.patterns.put("high kick low", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "spaceSoccer_highkicklow1"));
				array.add(new SoundEffect(1, "spaceSoccer_highkicklow2"));
				array.add(new SoundEffect(1.5f, "spaceSoccer_highkicklow3"));
			}
		});
	}

}
