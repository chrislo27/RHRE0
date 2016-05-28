package chrislo27.remixer.pattern;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.game.Game;
import chrislo27.remixer.track.SoundEffect;

public abstract class Pattern {

	public final Game game;

	public Pattern(Game game) {
		this.game = game;
	}

	public abstract void addPatternToArray(Array<SoundEffect> array);

}
