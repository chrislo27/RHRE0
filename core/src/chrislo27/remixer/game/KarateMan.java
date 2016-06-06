package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class KarateMan extends Game {

	public KarateMan(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("pot punch", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "karateMan_potout"));
				array.add(new SoundEffect(1, "karateMan_pothit"));
			}
		});
		
		this.patterns.put("offbeat pot punch", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "karateMan_offbeatpotout"));
				array.add(new SoundEffect(1, "karateMan_pothit"));
			}
		});
		
		this.patterns.put("lightbulb", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "karateMan_bulbout"));
				array.add(new SoundEffect(1, "karateMan_bulbhit"));
			}
		});
		
		this.patterns.put("hit 3", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "karateMan_hit3cue1"));
				array.add(new SoundEffect(0.5f, "karateMan_hit3cue2"));
			}
		});
		
		this.patterns.put("punch kick!", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "karateMan_punchkick1"));
				array.add(new SoundEffect(0.75f, "karateMan_punchkick2"));
			}
		});
	}

}
