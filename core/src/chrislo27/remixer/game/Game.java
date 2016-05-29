package chrislo27.remixer.game;

import chrislo27.remixer.pattern.Pattern;
import ionium.util.BiObjectMap;

public class Game implements Comparable {

	public final String name;
	public final BiObjectMap<String, Pattern> patterns = new BiObjectMap<>();

	public Game(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Game) {
			Game g = (Game) o;

			return this.name.compareTo(g.name);
		}

		return 0;
	}

}
