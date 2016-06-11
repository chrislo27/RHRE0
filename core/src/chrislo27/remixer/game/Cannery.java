package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class Cannery extends Game {

	public Cannery(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("ding can", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "cannery_ding"));
				array.add(new SoundEffect(1, "cannery_steam"));
			}
		});
	}

}
