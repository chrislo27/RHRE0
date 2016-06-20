package chrislo27.remixer.game;

import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.action.ActionSFX;
import ionium.util.i18n.Localization;

public class Pattern implements Comparable<Pattern> {

	public Array<ActionSFX> sfx = new Array<>();
	private Array<ActionSFX> tmp = new Array<>();
	public final String id;
	public final boolean isOnlyCue;

	public Pattern(String id, boolean onlyCue) {
		this.id = id;
		this.isOnlyCue = onlyCue;
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

	@Override
	public int compareTo(Pattern o) {
		if (this.isOnlyCue && !o.isOnlyCue) return 1;

		return Localization.get(id).compareToIgnoreCase(Localization.get(o.id));
	}

}
