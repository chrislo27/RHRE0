package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class LoveLizards extends Game {

	public LoveLizards(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("original", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "countIn_cowbell"));
				array.add(new SoundEffect(0.5f, "countIn_cowbell"));
				array.add(new SoundEffect(1.5f, "countIn_cowbell"));

				array.add(new SoundEffect(2.5f, "loveLizards_bzzt1"));
				array.add(new SoundEffect(3, "loveLizards_bzzt2"));
				array.add(new SoundEffect(3.5f, "loveLizards_bzzt3"));
				array.add(new SoundEffect(4, "loveLizards_bzzt4"));

				array.add(new SoundEffect(5, "loveLizards_happy"));
			}
		});
		
		this.patterns.put("DS remix 3", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "countIn_cowbell"));
				array.add(new SoundEffect(0.5f, "countIn_cowbell"));
				array.add(new SoundEffect(1.5f, "countIn_cowbell"));

				array.add(new SoundEffect(2, "loveLizards_bzzt1"));
				array.add(new SoundEffect(2.5f, "loveLizards_bzzt2"));
				array.add(new SoundEffect(3, "loveLizards_bzzt3"));
				array.add(new SoundEffect(3.5f, "loveLizards_bzzt4"));

				array.add(new SoundEffect(4.5f, "loveLizards_happy"));
			}
		});
	}

}
