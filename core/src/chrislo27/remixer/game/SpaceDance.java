package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class SpaceDance extends Game {

	public SpaceDance(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("dancers - turn right", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "spaceDance_turn1"));
				array.add(new SoundEffect(1, "spaceDance_right1"));
			}

		});
		this.patterns.put("dancers - let's sit down", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "spaceDance_lets1"));
				array.add(new SoundEffect(0.5f, "spaceDance_sit1"));
				array.add(new SoundEffect(1f, "spaceDance_down1"));
			}

		});
		this.patterns.put("dancers - pa-pa-pa punch!", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "spaceDance_pa1"));
				array.add(new SoundEffect(0.5f, "spaceDance_pa1"));
				array.add(new SoundEffect(1, "spaceDance_pa1"));

				array.add(new SoundEffect(1.5f, "spaceDance_punch1"));
			}

		});

		this.patterns.put("gramps - turn right", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "spaceDance_turn2"));
				array.add(new SoundEffect(1, "spaceDance_right2"));
			}

		});
		this.patterns.put("gramps - let's sit down", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "spaceDance_lets2"));
				array.add(new SoundEffect(0.5f, "spaceDance_sit2"));
				array.add(new SoundEffect(1, "spaceDance_down2"));
			}

		});
		this.patterns.put("gramps - pa-pa-pa punch!", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "spaceDance_pa2"));
				array.add(new SoundEffect(0.5f, "spaceDance_pa2"));
				array.add(new SoundEffect(1, "spaceDance_pa2"));

				array.add(new SoundEffect(1.5f, "spaceDance_punch2"));
			}

		});
	}

}
