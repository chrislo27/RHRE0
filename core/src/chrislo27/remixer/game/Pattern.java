package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.action.ActionSFX;

public class Pattern {

	public Array<ActionSFX> sfx = new Array<>();
	private Array<ActionSFX> tmp = new Array<>();
	public final String id;

	public Pattern(String id) {
		this.id = id;
	}

	public Array<ActionSFX> getNewInstances(Array<ActionSFX> array) {
		for (ActionSFX a : sfx) {
			array.add(new ActionSFX(a));
		}

		return array;
	}

	public Array<ActionSFX> getNewInstancesTemp() {
		tmp.clear();

		return getNewInstances(tmp);
	}

}
