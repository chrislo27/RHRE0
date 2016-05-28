package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class Lockstep extends Game {

	public Lockstep(String name) {
		super(name);

		addPatterns();
	}

	private void addPatterns() {
		this.patterns.put("start", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "lockstep_hai"));
			}
		});

		this.patterns.put("switch", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "lockstep_hai"));
				array.add(new SoundEffect(1, "lockstep_hai"));
				array.add(new SoundEffect(2, "lockstep_hai"));
				array.add(new SoundEffect(3, "lockstep_backbeat_ha"));
				array.add(new SoundEffect(3.5f, "lockstep_backbeat_hoi"));
			}
		});

		this.patterns.put("return", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "lockstep_return_hee"));
				array.add(new SoundEffect(0.5f, "lockstep_return_ha"));
				array.add(new SoundEffect(1, "lockstep_return_hee"));
				array.add(new SoundEffect(1.5f, "lockstep_return_ha"));
				array.add(new SoundEffect(2, "lockstep_hai"));
			}
		});
	}

}
