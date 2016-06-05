package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class Ringside extends Game {

	public Ringside(String name, String contributors) {
		super(name, contributors);

		this.patterns.put("wubba dubba 1", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				final float third = 1 / 3f;

				array.add(new SoundEffect(0, "ringside_wubba1-1"));
				array.add(new SoundEffect(third, "ringside_wubba1-2"));
				array.add(new SoundEffect(third * 2, "ringside_dubba1-1"));
				array.add(new SoundEffect(1, "ringside_dubba1-2"));
				array.add(new SoundEffect(1 + third, "ringside_dubba1-3"));
				array.add(new SoundEffect(1 + third * 2, "ringside_dubba1-4"));
				array.add(new SoundEffect(2, "ringside_that1"));
				array.add(new SoundEffect(2 + third * 2, "ringside_true1"));

				array.add(new SoundEffect(4, "ringside_ye"));
			}
		});

		this.patterns.put("woah you go big guy 1", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				final float third = 1 / 3f;
				final float sixth = 1 / 6f;

				array.add(new SoundEffect(0, "ringside_woah1"));
				array.add(new SoundEffect(third * 2, "ringside_you1"));
				array.add(new SoundEffect(1, "ringside_go1"));
				array.add(new SoundEffect(1 + third * 2, "ringside_big1"));
				array.add(new SoundEffect(2 + sixth, "ringside_guy1"));

				array.add(new SoundEffect(3, "ringside_muscles1"));
				array.add(new SoundEffect(3.5f, "ringside_muscles2"));
			}
		});

		this.patterns.put("pose for the fans 1", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				final float third = 1 / 3f;

				array.add(new SoundEffect(0, "ringside_pose1"));
				array.add(new SoundEffect(0.5f, "ringside_for1"));
				array.add(new SoundEffect(third * 2, "ringside_the1"));
				array.add(new SoundEffect(1, "ringside_fans1"));

				array.add(new SoundEffect(2, "ringside_yell1"));
				array.add(new SoundEffect(3, "ringside_camera1"));
			}
		});

		this.patterns.put("wubba dubba 2", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				final float third = 1 / 3f;

				array.add(new SoundEffect(0, "ringside_wubba2-1"));
				array.add(new SoundEffect(third, "ringside_wubba2-2"));
				array.add(new SoundEffect(third * 2, "ringside_dubba2-1"));
				array.add(new SoundEffect(1, "ringside_dubba2-2"));
				array.add(new SoundEffect(1 + third, "ringside_dubba2-3"));
				array.add(new SoundEffect(1 + third * 2, "ringside_dubba2-4"));
				array.add(new SoundEffect(2, "ringside_that2"));
				array.add(new SoundEffect(2 + third * 2, "ringside_true2"));

				array.add(new SoundEffect(4, "ringside_ye"));
			}
		});

		this.patterns.put("woah you go big guy 2", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				final float third = 1 / 3f;
				final float sixth = 1 / 6f;

				array.add(new SoundEffect(0, "ringside_woah2"));
				array.add(new SoundEffect(third * 2, "ringside_you2"));
				array.add(new SoundEffect(1, "ringside_go2"));
				array.add(new SoundEffect(1 + third * 2, "ringside_big2"));
				array.add(new SoundEffect(2 + sixth, "ringside_guy2"));

				array.add(new SoundEffect(3, "ringside_muscles1"));
				array.add(new SoundEffect(3.5f, "ringside_muscles2"));
			}
		});

		this.patterns.put("pose for the fans 2", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				final float third = 1 / 3f;

				array.add(new SoundEffect(0, "ringside_pose2"));
				array.add(new SoundEffect(0.5f, "ringside_for2"));
				array.add(new SoundEffect(third * 2, "ringside_the2"));
				array.add(new SoundEffect(1, "ringside_fans2"));

				array.add(new SoundEffect(2, "ringside_yell2"));
				array.add(new SoundEffect(3, "ringside_camera2"));
			}
		});

		this.patterns.put("wubba dubba 3", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				final float third = 1 / 3f;

				array.add(new SoundEffect(0, "ringside_wubba3-1"));
				array.add(new SoundEffect(third, "ringside_wubba3-2"));
				array.add(new SoundEffect(third * 2, "ringside_dubba3-1"));
				array.add(new SoundEffect(1, "ringside_dubba3-2"));
				array.add(new SoundEffect(1 + third, "ringside_dubba3-3"));
				array.add(new SoundEffect(1 + third * 2, "ringside_dubba3-4"));
				array.add(new SoundEffect(2, "ringside_that3"));
				array.add(new SoundEffect(2 + third * 2, "ringside_true3"));

				array.add(new SoundEffect(4, "ringside_ye"));
			}
		});

		this.patterns.put("woah you go big guy 3", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				final float third = 1 / 3f;
				final float sixth = 1 / 6f;

				array.add(new SoundEffect(0, "ringside_woah3"));
				array.add(new SoundEffect(sixth + third * 2, "ringside_you3"));
				array.add(new SoundEffect(sixth + 1, "ringside_go3"));
				array.add(new SoundEffect(sixth + 1 + third * 2, "ringside_big3"));
				array.add(new SoundEffect(sixth + 2 + sixth, "ringside_guy3"));

				array.add(new SoundEffect(3, "ringside_muscles1"));
				array.add(new SoundEffect(3.5f, "ringside_muscles2"));
			}
		});

		this.patterns.put("and pose for the fans", new Pattern(this) {

			@Override
			public void addPatternToArray(Array<SoundEffect> array) {
				final float third = 1 / 3f;

				array.add(new SoundEffect(-0.5f, "ringside_poseAnd"));
				array.add(new SoundEffect(0, "ringside_pose3"));
				array.add(new SoundEffect(0.5f, "ringside_for3"));
				array.add(new SoundEffect(third * 2, "ringside_the3"));
				array.add(new SoundEffect(1, "ringside_fans3"));

				array.add(new SoundEffect(2, "ringside_yell3"));
				array.add(new SoundEffect(3, "ringside_camera3"));
			}
		});

	}

}
