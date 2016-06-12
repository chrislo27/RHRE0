package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class BuiltToScaleDS extends Game {

	public BuiltToScaleDS(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("do re mi fa so", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "builtToScaleDS_c").setSemitone(0));
				array.add(new SoundEffect(1, "builtToScaleDS_c").setSemitone(2));
				array.add(new SoundEffect(2, "builtToScaleDS_c").setSemitone(4));
				array.add(new SoundEffect(3, "builtToScaleDS_c").setSemitone(5));
				array.add(new SoundEffect(3, "builtToScaleDS_c").setSemitone(7));
			}
		});
	}

}
