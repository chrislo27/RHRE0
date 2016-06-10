package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class LaunchParty extends Game {

	public LaunchParty(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("one", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "launchParty_one"));
				
				array.add(new SoundEffect(2, "launchParty_one-go"));
			}
		});
		
		this.patterns.put("three", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "launchParty_three-3"));
				array.add(new SoundEffect(1, "launchParty_three-2"));
				array.add(new SoundEffect(2, "launchParty_three-1"));
				
				array.add(new SoundEffect(3, "launchParty_three-go"));
			}
		});
		
		this.patterns.put("five", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "launchParty_five-5"));
				array.add(new SoundEffect(2 / 3f, "launchParty_five-4"));
				array.add(new SoundEffect(1, "launchParty_five-3"));
				array.add(new SoundEffect(4 / 3f, "launchParty_five-2"));
				array.add(new SoundEffect(5 / 3f, "launchParty_five-1"));
				
				array.add(new SoundEffect(2, "launchParty_five-go"));
			}
		});
		
		this.patterns.put("seven", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "launchParty_seven-7"));
				array.add(new SoundEffect(1 + 1 / 8f, "launchParty_seven-6"));
				array.add(new SoundEffect(1 + 2 / 8f, "launchParty_seven-5"));
				array.add(new SoundEffect(1 + 3 / 8f, "launchParty_seven-4"));
				array.add(new SoundEffect(1 + 4 / 8f, "launchParty_seven-3"));
				array.add(new SoundEffect(1 + 5 / 8f, "launchParty_seven-2"));
				array.add(new SoundEffect(1 + 6 / 8f, "launchParty_seven-1"));
				
				array.add(new SoundEffect(2, "launchParty_seven-go"));
			}
		});
	}

}
