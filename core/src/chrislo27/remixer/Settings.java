package chrislo27.remixer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import ionium.registry.GlobalVariables;

public class Settings {

	public static final String PREFERENCE_PREFIX = "RhythmHeavenRemixBoard-";

	private static Settings instance;

	public float musicVolume = 1;
	public float soundVolume = 1;

	public int actualWidth = GlobalVariables.defaultWidth;
	public int actualHeight = GlobalVariables.defaultHeight;
	public boolean fullscreen = GlobalVariables.defaultFullscreen;

	private Settings() {
	}

	public static Settings instance() {
		if (instance == null) {
			instance = new Settings();
			instance.loadResources();
		}
		return instance;
	}

	private Preferences pref;

	private void loadResources() {
		pref = getPrefWithGamePrefix("settings");

		soundVolume = pref.getFloat("soundVolume", 1f);
		musicVolume = pref.getFloat("musicVolume", 1f);
		actualWidth = pref.getInteger("actualWidth", GlobalVariables.defaultWidth);
		actualHeight = pref.getInteger("actualHeight", GlobalVariables.defaultHeight);
		fullscreen = pref.getBoolean("fullscreen", GlobalVariables.defaultFullscreen);
	}

	public static Preferences getPrefWithGamePrefix(String ref) {
		return Gdx.app.getPreferences(PREFERENCE_PREFIX + ref);
	}

	public void save() {
		pref.putFloat("sound", soundVolume).putFloat("music", musicVolume)
				.putInteger("actualWidth", actualWidth).putInteger("actualHeight", actualHeight)
				.putBoolean("fullscreen", fullscreen).putBoolean("hasResolutionBeenSaved", true)
				.flush();
	}

	public static Preferences getSettingsPreferences() {
		return instance().pref;
	}
}
