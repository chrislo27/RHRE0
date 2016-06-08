package chrislo27.remixer.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class ClappyTrio extends Game {

	public ClappyTrio(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("how many are in the Clappy Trio?", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				for (int i = 0; i < MathUtils.random(3, 4); i++) {
					array.add(new SoundEffect(i, "clappyTrio_clap"));
				}
			}
		});
	}

}
