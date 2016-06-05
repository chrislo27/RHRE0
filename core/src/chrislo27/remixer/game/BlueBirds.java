package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class BlueBirds extends Game {

	public BlueBirds(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("peck your beak", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "blueBirds_peckyourbeak1"));
				array.add(new SoundEffect(0.5f, "blueBirds_peckyourbeak2"));
				array.add(new SoundEffect(1, "blueBirds_peckyourbeak3"));
				
				array.add(new SoundEffect(2, "blueBirds_peck"));
				array.add(new SoundEffect(2.5f, "blueBirds_peck"));
				array.add(new SoundEffect(3, "blueBirds_peck"));
			}
		});
		
		this.patterns.put("stretch out your neck", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				array.add(new SoundEffect(0, "blueBirds_stretchoutyourneck1"));
				array.add(new SoundEffect(5 / 6f, "blueBirds_stretchoutyourneck2"));
				array.add(new SoundEffect(1.5f, "blueBirds_stretchoutyourneck3"));
				array.add(new SoundEffect(2, "blueBirds_stretchoutyourneck4"));
				
				array.add(new SoundEffect(3, "blueBirds_stretch1"));
				array.add(new SoundEffect(4, "blueBirds_stretch2"));
			}
		});
		
		
	}

}
