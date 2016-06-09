package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class Lockstep2 extends Game {

	public Lockstep2(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("start", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "lockstep_hai"));
				array.add(new SoundEffect(1, "lockstep_march2"));
			}
		});

		this.patterns.put("march", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "lockstep_march1"));
				array.add(new SoundEffect(1, "lockstep_march2"));
			}
		});

		this.patterns.put("backbeat march", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "lockstep_march1_bkbt"));
				array.add(new SoundEffect(1, "lockstep_march2_bkbt"));
			}
		});

		this.patterns.put("switch", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "lockstep_hai"));
				array.add(new SoundEffect(1, "lockstep_hai"));
				array.add(new SoundEffect(2, "lockstep_hai"));
				array.add(new SoundEffect(3, "lockstep_bkbt_ha"));
				array.add(new SoundEffect(3f + 2 / 3f, "lockstep_bkbt_hoi"));
				array.add(new SoundEffect(4f + 2 / 3f, "lockstep_bkbt_boh"));
				array.add(new SoundEffect(5f + 2 / 3f, "lockstep_bkbt_boh"));
				array.add(new SoundEffect(6f + 2 / 3f, "lockstep_bkbt_boh"));
				array.add(new SoundEffect(7f + 2 / 3f, "lockstep_bkbt_boh"));
			}
		});

		this.patterns.put("return", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "lockstep_return_hee"));
				array.add(new SoundEffect(2 / 3f, "lockstep_return_ha"));
				array.add(new SoundEffect(1, "lockstep_return_hee"));
				array.add(new SoundEffect(1f + 2 / 3f, "lockstep_return_ha"));
				array.add(new SoundEffect(2, "lockstep_hai"));
			}
		});
	}

}
