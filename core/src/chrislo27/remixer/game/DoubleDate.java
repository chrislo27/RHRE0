package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class DoubleDate extends Game {

	public DoubleDate(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("soccer", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "doubleDate_soccerBounce"));
				array.add(new SoundEffect(1, "doubleDate_kick"));
			}
		});
		
		this.patterns.put("basketball", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "doubleDate_basketballBounce"));
				array.add(new SoundEffect(0.75f, "doubleDate_basketballBounce"));
				array.add(new SoundEffect(1, "doubleDate_kick"));
			}
		});
		
		this.patterns.put("football", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "doubleDate_footballBounce"));
				array.add(new SoundEffect(0.75f, "doubleDate_footballBounce"));
				array.add(new SoundEffect(1.5f, "doubleDate_footballKick"));
			}
		});
	}

}
