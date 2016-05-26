package chrislo27.remixer.stage;

import chrislo27.remixer.Main;
import ionium.stage.Stage;

public class EditorStageSetup {

	private final Main main;

	private Stage stage;

	public EditorStageSetup(Main main) {
		this.main = main;

		create();
	}

	private void create() {

	}

	public Stage getStage() {
		return stage;
	}

}
