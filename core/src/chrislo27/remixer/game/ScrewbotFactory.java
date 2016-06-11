package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class ScrewbotFactory extends Game {

	public ScrewbotFactory(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("grey bot (oh yeah!)", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "screwbotFactory_"));
				array.add(new SoundEffect(0.5f, "screwbotFactory_"));
				
				array.add(new SoundEffect(2, "screwbotFactory_ohyeah"));
			}
		});
		
		this.patterns.put("white bot (let's go!)", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "screwbotFactory_"));
				array.add(new SoundEffect(0.5f, "screwbotFactory_"));
				
				array.add(new SoundEffect(2, "screwbotFactory_letsgo"));
			}
		});
	}

}
