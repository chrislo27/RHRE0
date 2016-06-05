package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class CropStomp extends Game {

	public CropStomp(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("beet stomp", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "cropStomp_stomp"));
				array.add(new SoundEffect(0.5f, "cropStomp_pick1"));
				array.add(new SoundEffect(1, "cropStomp_pick2"));
			}
		});
		
		this.patterns.put("mole fling", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "cropStomp_stomp"));
				array.add(new SoundEffect(0.5f, "cropStomp_molefling"));
			}
		});
	}

}
