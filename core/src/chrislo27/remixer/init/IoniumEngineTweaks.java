package chrislo27.remixer.init;

import ionium.registry.GlobalVariables;
import ionium.util.DebugSetting;

public class IoniumEngineTweaks {

	public static final void tweak() {
		GlobalVariables.ticks = 20;
		GlobalVariables.versionUrl = "https://raw.githubusercontent.com/chrislo27/VersionPlace/master/RHRE-version.txt";
		GlobalVariables.defaultFullscreen = false;
		GlobalVariables.defaultWidth = 1280;
		GlobalVariables.defaultHeight = 720;

		DebugSetting.showFPS = false;
	}

}
