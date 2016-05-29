package chrislo27.remixer.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Align;

import chrislo27.remixer.EditorScreen;
import chrislo27.remixer.Main;
import ionium.registry.AssetRegistry;
import ionium.stage.Group;
import ionium.stage.Stage;
import ionium.stage.ui.ImageButton;
import ionium.stage.ui.LocalizationStrategy;
import ionium.stage.ui.TextButton;
import ionium.stage.ui.TextLabel;
import ionium.stage.ui.skin.Palette;
import ionium.stage.ui.skin.Palettes;

public class EditorStageSetup {

	private final Main main;
	private final EditorScreen editorScreen;

	private Stage stage;

	private Group toolbar;
	private ImageButton saveButton;

	private Group confirmationGroup;
	private ImageButton confirmationYes;
	private ImageButton confirmationNo;
	private TextLabel confirmationLabel;
	private Runnable confirmationRun;

	private Runnable setInvisible = new Runnable() {

		@Override
		public void run() {
			confirmationGroup.setVisible(false);
			toolbar.setVisible(true);
		}
	};

	public EditorStageSetup(Main main, EditorScreen es) {
		this.main = main;
		this.editorScreen = es;

		create();
	}

	private void create() {
		stage = new Stage();
		Palette palette = Palettes.getIoniumDefault(main.font, main.fontBordered);

		toolbar = new Group(stage);

		// toolbar stuff
		{
			ImageButton newProject = new ImageButton(stage, palette,
					AssetRegistry.getAtlasRegion("ionium_ui-icons", "newFile")) {

				@Override
				public void onClickAction(float x, float y) {
					super.onClickAction(x, y);
				}

			};

			newProject.getColor().set(0.25f, 0.25f, 0.25f, 1);
			toolbar.addActor(newProject).align(Align.topLeft).setPixelOffset(8, 8, 32, 32);

			ImageButton openProject = new ImageButton(stage, palette,
					AssetRegistry.getAtlasRegion("ionium_ui-icons", "openFile")) {

				@Override
				public void onClickAction(float x, float y) {
					super.onClickAction(x, y);
				}

			};

			openProject.getColor().set(0.25f, 0.25f, 0.25f, 1);
			toolbar.addActor(openProject).align(Align.topLeft).setPixelOffset(8 + (32 + 8) * 1, 8,
					32, 32);

			saveButton = new ImageButton(stage, palette,
					AssetRegistry.getAtlasRegion("ionium_ui-icons", "saveFile")) {

				@Override
				public void onClickAction(float x, float y) {
					super.onClickAction(x, y);
				}

			};

			saveButton.getColor().set(0.25f, 0.25f, 0.25f, 1);
			toolbar.addActor(saveButton).align(Align.topLeft)
					.setPixelOffset(8 + (32 + 8) * 2, 8, 32, 32).setEnabled(false);

			ImageButton exitGame = new ImageButton(stage, palette,
					AssetRegistry.getAtlasRegion("ionium_ui-icons", "no")) {

				@Override
				public void onClickAction(float x, float y) {
					super.onClickAction(x, y);

					Gdx.app.exit();
				}

			};

			exitGame.getColor().set(0.85f, 0.25f, 0.25f, 1);
			toolbar.addActor(exitGame).align(Align.topRight).setPixelOffset(8, 8, 32, 32);

			TextButton interval = new TextButton(stage, palette, "") {

				private int interval = 2;

				{
					updateIntervalText();
				}

				@Override
				public void onClickAction(float x, float y) {
					super.onClickAction(x, y);

					interval++;

					if (interval > 4) {
						interval = 0;
					}

					if (interval == 1) interval = 2;

					if (interval == 0) {
						editorScreen.editor.lockingInterval = 0;
					} else {
						editorScreen.editor.lockingInterval = 1f / interval;
					}

					updateIntervalText();
				}

				private void updateIntervalText() {
					switch (interval) {
					case 2:
						setLocalizationKey("Snap: 1/2");
						break;
					case 3:
						setLocalizationKey("Snap: 1/3");
						break;
					case 4:
						setLocalizationKey("Snap: 1/4");
						break;
					case 0:
						setLocalizationKey("No snap");
						break;
					}
				}

			};

			interval.setI10NStrategy(new LocalizationStrategy() {

				@Override
				public String get(String key, Object... objects) {
					if (key == null) return "";

					return key;
				}

			});

			toolbar.addActor(interval).align(Align.topRight).setPixelOffset(48, 8, 128, 32);
		}

		stage.addActor(toolbar);

		confirmationGroup = new Group(stage);

		// confirmation group
		{
			confirmationYes = new ImageButton(stage, palette,
					AssetRegistry.getAtlasRegion("ionium_ui-icons", "yes")) {

				@Override
				public void onClickAction(float x, float y) {
					super.onClickAction(x, y);

					Gdx.app.postRunnable(setInvisible);
					if (confirmationRun != null) {
						Gdx.app.postRunnable(confirmationRun);
					}
				}

			};

			confirmationYes.align(Align.center).setPixelOffsetSize(64, 64).setScreenOffset(-0.1f,
					-0.1f);
			confirmationYes.getColor().set(0.25f, 0.75f, 0.25f, 1);
			confirmationGroup.addActor(confirmationYes);

			confirmationNo = new ImageButton(stage, palette,
					AssetRegistry.getAtlasRegion("ionium_ui-icons", "no")) {

				@Override
				public void onClickAction(float x, float y) {
					super.onClickAction(x, y);

					Gdx.app.postRunnable(setInvisible);
				}

			};

			confirmationNo.align(Align.center).setPixelOffsetSize(64, 64).setScreenOffset(0.1f,
					-0.1f);
			confirmationNo.getColor().set(0.9f, 0.25f, 0.25f, 1);
			confirmationGroup.addActor(confirmationNo);

			confirmationLabel = new TextLabel(stage, palette, null);
			confirmationLabel.setTextAlign(Align.center);
			confirmationLabel.align(Align.center).setScreenOffset(0, 0.1f, 1, 0.5f);
			confirmationGroup.addActor(confirmationLabel);
		}

		stage.addActor(confirmationGroup).setVisible(false);

	}

	protected void invokeConfirmation(String text, Runnable toRun) {
		confirmationRun = toRun;
		confirmationLabel.setLocalizationKey(text);

		toolbar.setVisible(false);
		confirmationGroup.setVisible(true);
	}

	public Stage getStage() {
		return stage;
	}

	public boolean shouldDim() {
		return confirmationGroup.isVisible();
	}

}
