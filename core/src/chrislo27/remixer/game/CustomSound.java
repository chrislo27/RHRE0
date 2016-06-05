package chrislo27.remixer.game;

public class CustomSound extends Game {

	public CustomSound(String name) {
		super(name, "YOU!");
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Game) {
			return -1;
		}

		return super.compareTo(o);
	}

}
