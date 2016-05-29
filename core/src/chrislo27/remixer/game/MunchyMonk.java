package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class MunchyMonk extends Game {

	public MunchyMonk(String name) {
		super(name);

		this.patterns.put("eat one", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "munchyMonk_one"));
				array.add(new SoundEffect(1, "munchyMonk_gulp"));
			}
		});
		this.patterns.put("eat two", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "munchyMonk_try"));
				array.add(new SoundEffect(0.5f, "munchyMonk_two"));
				array.add(new SoundEffect(1.5f, "munchyMonk_gulp"));
				array.add(new SoundEffect(2f, "munchyMonk_gulp"));
			}
		});
		this.patterns.put("eat three", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "munchyMonk_three"));
				array.add(new SoundEffect(1, "munchyMonk_gulp3-1"));
				array.add(new SoundEffect(2, "munchyMonk_gulp3-2"));
				array.add(new SoundEffect(3, "munchyMonk_gulp3-3"));
			}
		});
	}

}
