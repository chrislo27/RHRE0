package chrislo27.remixer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.stage.EditorStageSetup;
import ionium.screen.Updateable;
import ionium.stage.Stage;

public class EditorScreen extends Updateable<Main> {

	private EditorStageSetup stageSetup;
	private Stage stage;

	public EditorScreen(Main m) {
		super(m);
	}

	@Override
	public void render(float delta) {
		if (stageSetup != null) stage.render(main.batch);
	}

	@Override
	public void renderUpdate() {
	}

	@Override
	public void tickUpdate() {
	}

	@Override
	public void getDebugStrings(Array<String> array) {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		if (stageSetup == null) {
			stageSetup = new EditorStageSetup(main);
			stage = stageSetup.getStage();
		}

		stage.onResize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		if (Gdx.input.getInputProcessor() instanceof InputMultiplexer) {
			InputMultiplexer plex = (InputMultiplexer) Gdx.input.getInputProcessor();

			stage.addSelfToInputMultiplexer(plex);
		}
	}

	@Override
	public void hide() {
		if (Gdx.input.getInputProcessor() instanceof InputMultiplexer && stage != null) {
			Gdx.app.postRunnable(new Runnable() {

				@Override
				public void run() {
					InputMultiplexer plex = (InputMultiplexer) Gdx.input.getInputProcessor();

					stage.removeSelfFromInputMultiplexer(plex);
				}

			});
		}
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
