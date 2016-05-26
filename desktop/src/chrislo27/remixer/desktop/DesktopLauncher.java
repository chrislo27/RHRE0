package chrislo27.remixer.desktop;

import com.badlogic.gdx.Files.FileType;

import chrislo27.remixer.Main;
import chrislo27.remixer.init.IoniumEngineTweaks;
import ionium.desktop.ArgumentInferredLwjglAppConfig;
import ionium.desktop.GameLwjglApp;
import ionium.registry.GlobalVariables;
import ionium.util.Logger;

public class DesktopLauncher {

	private static Logger logger;

	public static void main(String[] args) {
		IoniumEngineTweaks.tweak();

		ArgumentInferredLwjglAppConfig config = new ArgumentInferredLwjglAppConfig(args);
		config.title = "";
		config.width = GlobalVariables.defaultWidth;
		config.height = GlobalVariables.defaultHeight;
		config.fullscreen = false;
		config.foregroundFPS = GlobalVariables.maxFps;
		config.backgroundFPS = GlobalVariables.maxFps;
		config.resizable = false;
		config.vSyncEnabled = true;
		config.samples = 4;

		config.inferFromArguments();

		config.addIcon("images/icon/icon32.png", FileType.Internal);
		config.addIcon("images/icon/icon16.png", FileType.Internal);
		config.addIcon("images/icon/icon128.png", FileType.Internal);

		logger = new Logger("", com.badlogic.gdx.utils.Logger.DEBUG);
		new GameLwjglApp(new Main(logger), config, logger);
	}
}
