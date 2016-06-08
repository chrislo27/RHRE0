package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class MicroRow extends Game {

	public MicroRow(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("go", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "microRow_go"));
				array.add(new SoundEffect(2, "microRow_go"));
			}
		});
		
		this.patterns.put("triple", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "microRow_ding"));
				
				array.add(new SoundEffect(1, "microRow_triple1"));
				array.add(new SoundEffect(2, "microRow_triple2"));
				array.add(new SoundEffect(3, "microRow_triple3"));
			}
		});
		
		this.patterns.put("dash (double)", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "microRow_dash1"));
				array.add(new SoundEffect(0.5f, "microRow_dash2"));
				
				array.add(new SoundEffect(1.5f, "microRow_go"));
				array.add(new SoundEffect(2, "microRow_go"));
			}
		});
	}

}
