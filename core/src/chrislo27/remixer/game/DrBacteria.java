package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class DrBacteria extends Game {

	public DrBacteria(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("appear and stab", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "drBacteria_appear"));
				array.add(new SoundEffect(1, "drBacteria_stab"));
			}
		});
	}

}
