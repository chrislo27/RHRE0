package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class LoveLab extends Game {

	public LoveLab(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("one heart", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "loveLab_leftCatch"));
				array.add(new SoundEffect(1, "loveLab_leftThrow"));

				array.add(new SoundEffect(2, "loveLab_rightCatch"));
				array.add(new SoundEffect(3, "loveLab_rightThrow"));
			}
		});

		this.patterns.put("three hearts", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "loveLab_leftCatch"));
				array.add(new SoundEffect(1, "loveLab_shake1"));
				array.add(new SoundEffect(1.25f, "loveLab_shake2"));
				array.add(new SoundEffect(1.5f, "loveLab_shake1"));
				array.add(new SoundEffect(1.75f, "loveLab_shake2"));
				array.add(new SoundEffect(2, "loveLab_leftThrow"));

				array.add(new SoundEffect(4, "loveLab_rightCatch"));
				array.add(new SoundEffect(5, "loveLab_shake1"));
				array.add(new SoundEffect(5.25f, "loveLab_shake2"));
				array.add(new SoundEffect(5.5f, "loveLab_shake1"));
				array.add(new SoundEffect(5.75f, "loveLab_shake2"));
				array.add(new SoundEffect(6, "loveLab_rightThrow"));
			}
		});

		this.patterns.put("five hearts", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "loveLab_leftCatch"));
				array.add(new SoundEffect(0.5f, "loveLab_shake1"));
				array.add(new SoundEffect(0.75f, "loveLab_shake2"));
				array.add(new SoundEffect(1, "loveLab_shake1"));
				array.add(new SoundEffect(1.25f, "loveLab_shake2"));
				array.add(new SoundEffect(2, "loveLab_shake1"));
				array.add(new SoundEffect(2.25f, "loveLab_shake2"));
				array.add(new SoundEffect(2.5f, "loveLab_shake1"));
				array.add(new SoundEffect(2.75f, "loveLab_shake2"));
				array.add(new SoundEffect(3, "loveLab_leftThrow"));

				array.add(new SoundEffect(4, "loveLab_rightCatch"));
				array.add(new SoundEffect(4.5f, "loveLab_shake1"));
				array.add(new SoundEffect(4.75f, "loveLab_shake2"));
				array.add(new SoundEffect(5, "loveLab_shake1"));
				array.add(new SoundEffect(5.25f, "loveLab_shake2"));
				array.add(new SoundEffect(6, "loveLab_shake1"));
				array.add(new SoundEffect(6.25f, "loveLab_shake2"));
				array.add(new SoundEffect(6.5f, "loveLab_shake1"));
				array.add(new SoundEffect(6.75f, "loveLab_shake2"));
				array.add(new SoundEffect(7, "loveLab_rightThrow"));
			}
		});
	}

}
