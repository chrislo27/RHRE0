package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class ScrewbotFactory extends Game {

	public ScrewbotFactory(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("grey bot (oh yeah!)", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "screwbotFactory_clankclank1"));
				array.add(new SoundEffect(0.5f, "screwbotFactory_clankclank2"));

				array.add(new SoundEffect(2, "screwbotFactory_ohyeahPlace"));

				array.add(new SoundEffect(3f, "screwbotFactory_screwingLetsGo").setDuration(2.5f));
				array.add(new SoundEffect(5.5f, "screwbotFactory_complete"));
				array.add(new SoundEffect(6, "screwbotFactory_oh"));
				array.add(new SoundEffect(6.5f, "screwbotFactory_yeah"));
			}
		});

		this.patterns.put("white bot (let's go!)", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "screwbotFactory_clankclank1"));
				array.add(new SoundEffect(0.5f, "screwbotFactory_clankclank2"));

				array.add(new SoundEffect(2, "screwbotFactory_letsgoPlace"));

				array.add(new SoundEffect(3f, "screwbotFactory_screwingOhYeah").setDuration(1.0f));
				array.add(new SoundEffect(4, "screwbotFactory_complete"));
				array.add(new SoundEffect(4.5f, "screwbotFactory_lets"));
				array.add(new SoundEffect(5, "screwbotFactory_go"));
			}
		});
	}

}
