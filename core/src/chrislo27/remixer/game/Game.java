package chrislo27.remixer.game;

import chrislo27.remixer.pattern.Pattern;
import ionium.util.BiObjectMap;

public class Game {

	public final String name;
	public final BiObjectMap<String, Pattern> patterns = new BiObjectMap<>();

	public Game(String name) {
		this.name = name;
	}

}
