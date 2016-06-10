package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class ExhibitionMatch extends Game {

	public ExhibitionMatch(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("throw and hit", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "exhibitionMatch_stance"));
				array.add(new SoundEffect(2, "exhibitionMatch_throw"));

				array.add(new SoundEffect(6, "exhibitionMatch_hit"));
			}
		});
	}

}
