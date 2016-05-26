package chrislo27.remixer.init;

import ionium.registry.GlobalVariables;
import ionium.util.DebugSetting;

public class IoniumEngineTweaks {

	public static final void tweak() {
		GlobalVariables.ticks = 20;
		GlobalVariables.versionUrl = null;

		DebugSetting.showFPS = false;
	}
	
}
