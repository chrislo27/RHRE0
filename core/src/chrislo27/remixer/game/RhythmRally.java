package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class RhythmRally extends Game {

	public RhythmRally(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("pattern", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "rhythmRally_hit1"));
				array.add(new SoundEffect(1, "rhythmRally_hit2"));
				array.add(new SoundEffect(2, "rhythmRally_hit3"));
				array.add(new SoundEffect(3, "rhythmRally_hit2"));
			}
		});

		this.patterns.put("slow pattern", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "rhythmRally_hit1"));
				array.add(new SoundEffect(2, "rhythmRally_hit2"));
				array.add(new SoundEffect(4, "rhythmRally_hit3"));
				array.add(new SoundEffect(6, "rhythmRally_hit2"));
			}
		});

		this.patterns.put("fast warning", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "rhythmRally_tonk"));
				array.add(new SoundEffect(0.5f, "rhythmRally_tink"));
				array.add(new SoundEffect(1, "rhythmRally_tonk"));
			}
		});

		this.patterns.put("fast pattern", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "rhythmRally_hit1"));
				array.add(new SoundEffect(0.5f, "rhythmRally_hit2"));
				array.add(new SoundEffect(1, "rhythmRally_hit3"));
				array.add(new SoundEffect(2, "rhythmRally_hit2"));
			}
		});

		this.patterns.put("turbo warning", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "rhythmRally_tonk"));
				array.add(new SoundEffect(0.5f, "rhythmRally_tink"));
				array.add(new SoundEffect(1, "rhythmRally_tonk"));
				array.add(new SoundEffect(1.5f, "rhythmRally_tink"));
				array.add(new SoundEffect(2, "rhythmRally_tonk"));
				array.add(new SoundEffect(2.5f, "rhythmRally_tink"));
				array.add(new SoundEffect(3, "rhythmRally_tonk"));
				array.add(new SoundEffect(3.5f, "rhythmRally_tink"));
			}
		});

		this.patterns.put("turbo pattern", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "rhythmRally_hit1"));
				array.add(new SoundEffect(0.5f, "rhythmRally_hit2"));
				array.add(new SoundEffect(1, "rhythmRally_hit3"));
				array.add(new SoundEffect(1.5f, "rhythmRally_hit2"));
			}
		});
	}

}
