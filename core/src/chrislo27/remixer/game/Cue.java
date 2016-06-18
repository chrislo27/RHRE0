package chrislo27.remixer.game;

import com.badlogic.gdx.utils.JsonValue;

import ionium.util.i18n.Localization;

public class Cue {

	public final String id;
	public final float duration;
	public final boolean resizable;
	public final boolean repitchable;
	public String oneTimeCue = null;
	public boolean hidden = false;

	public Cue(String id, float dur, boolean resizable, boolean repitchable) {
		this.id = id;

		duration = dur;
		this.repitchable = repitchable;
		this.resizable = resizable;
	}

	public Cue(JsonValue json) {
		if (json == null) throw new IllegalArgumentException("JsonValue cannot be null!");

		id = json.getString("id", null);

		String localization = json.getString("localization", null);
		if (localization != null) {
			Localization.instance().addCustom(id, localization);
		}

		duration = json.getFloat("duration");
		resizable = json.getBoolean("resizable", false);
		repitchable = json.getBoolean("repitchable", false);
		hidden = json.getBoolean("hidden", false);

	}

}
