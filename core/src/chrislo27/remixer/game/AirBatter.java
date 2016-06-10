package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class AirBatter extends Game {

	public AirBatter(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("short", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "airBatter_shoot"));
				array.add(new SoundEffect(1, "airBatter_hit"));
			}
		});
		
//		this.patterns.put("long", new Pattern(this) {
//
//			@Override
//			public void addPatternToArray(Array<SoundEffect> array) {
//				array.add(new SoundEffect(0, "airBatter_shoot"));
//				array.add(new SoundEffect(2, "airBatter_hit"));
//			}
//		});
	}

}
