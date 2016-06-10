package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class HoppingRoad extends Game {

	public HoppingRoad(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("pattern", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "hoppingRoad_tink").setSemitone(0));
				array.add(new SoundEffect(0.5f, "hoppingRoad_tink").setSemitone(0));
				array.add(new SoundEffect(1, "hoppingRoad_tink").setSemitone(0));
				array.add(new SoundEffect(1.5f, "hoppingRoad_tink").setSemitone(0));
				array.add(new SoundEffect(2, "hoppingRoad_tink").setSemitone(0));
				array.add(new SoundEffect(2.5f, "hoppingRoad_tink").setSemitone(0));
				array.add(new SoundEffect(3, "hoppingRoad_tink").setSemitone(0));
				array.add(new SoundEffect(3.5f, "hoppingRoad_tink").setSemitone(0));
				array.add(new SoundEffect(4, "hoppingRoad_tink").setSemitone(0));
				array.add(new SoundEffect(4.5f, "hoppingRoad_tink").setSemitone(0));
				array.add(new SoundEffect(5, "hoppingRoad_tink").setSemitone(0));
				array.add(new SoundEffect(5.5f, "hoppingRoad_tink").setSemitone(0));

				array.add(new SoundEffect(6, "hoppingRoad_tink").setSemitone(-4));
				array.add(new SoundEffect(6.5f, "hoppingRoad_tink").setSemitone(3));
				array.add(new SoundEffect(7, "hoppingRoad_cymbal").setSemitone(0));
			}
		});
	}

}
