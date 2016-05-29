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
				array.add(new SoundEffect(3, "lockstep_bkbt_ha"));
				array.add(new SoundEffect(3.5f, "lockstep_bkbt_hoi"));
				array.add(new SoundEffect(4.5f, "lockstep_bkbt_boh"));
				array.add(new SoundEffect(5.5f, "lockstep_bkbt_boh"));
				array.add(new SoundEffect(6.5f, "lockstep_bkbt_boh"));
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

		this.patterns.put("march", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "lockstep_march1"));
				array.add(new SoundEffect(1, "lockstep_march2"));
			}
		});

		this.patterns.put("bbeat_march", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "lockstep_march1_bkbt"));
				array.add(new SoundEffect(1, "lockstep_march2_bkbt"));
			}
		});
	}

}
