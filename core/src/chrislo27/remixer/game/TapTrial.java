package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class TapTrial extends Game {

	public TapTrial(String name) {
		super(name, null);

		this.patterns.put("single tap", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "tapTrial_ook"));
				array.add(new SoundEffect(1, "tapTrial_tap"));
			}
		});

		this.patterns.put("double tap", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "tapTrial_ookook1"));
				array.add(new SoundEffect(0.5f, "tapTrial_ookook2"));
				array.add(new SoundEffect(1, "tapTrial_tap"));
				array.add(new SoundEffect(1.5f, "tapTrial_tap"));
			}
		});

		this.patterns.put("triple tap", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "tapTrial_ooki1"));
				array.add(new SoundEffect(0.5f, "tapTrial_ooki2"));
				array.add(new SoundEffect(2, "tapTrial_tap"));
				array.add(new SoundEffect(2.5f, "tapTrial_tap"));
				array.add(new SoundEffect(3, "tapTrial_tap"));
			}
		});

		this.patterns.put("jump tap 1", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "tapTrial_jumptap1"));
				array.add(new SoundEffect(1, "tapTrial_jumptap1_tap"));
			}
		});

		this.patterns.put("jump tap 2", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "tapTrial_jumptap2"));
				array.add(new SoundEffect(1, "tapTrial_jumptap2_tap"));
			}
		});

	}

}
