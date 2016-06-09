package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.track.SoundEffect;

public class CountInGame extends Game {

	public CountInGame(String name) {
		super(name, null);

		this.sortingPriority = Short.MAX_VALUE;
	}

}
