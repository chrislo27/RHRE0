package chrislo27.remixer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.editor.Editor;
import chrislo27.remixer.stage.EditorStageSetup;
import ionium.screen.Updateable;
import ionium.stage.Stage;

public class EditorScreen extends Updateable<Main> {

	private EditorStageSetup stageSetup;
	private Stage stage;

	private Editor editor;

	public EditorScreen(Main m) {
		super(m);

		editor = new Editor(main);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// render backgrounds
		main.batch.begin();

		main.batch.setColor(1f, 0.65f, 0.5f, 1);
		Main.fillRect(main.batch, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		main.batch.setColor(1, 1, 1, 1);

		main.batch.setColor(0.3f, 0.3f, 0.5f, 1);
		Main.fillRect(main.batch, 0, Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), -48);
		main.batch.setColor(1, 1, 1, 1);

		// beat counter
		float beatRulerY = Gdx.graphics.getHeight() - 96;

		main.font.getData().setScale(0.75f);
		main.font.setColor(0, 0, 0, 1);

		for (float beat = editor.camera.position.x
				- editor.camera.viewportWidth * 0.5f; beat <= editor.camera.position.x
						+ editor.camera.viewportWidth * 0.5f; beat++) {
			final int steps = 2;
			float units = (Gdx.graphics.getWidth() / editor.camera.viewportWidth)
					/ editor.camera.zoom;
			float relativeX = (Gdx.graphics.getWidth() * 0.5f)
					+ ((beat - editor.camera.position.x) * (units));

			// sub-beat lines
			main.batch.setColor(0.25f, 0.25f, 0.25f, 1);
			for (int i = 0; i < steps; i++) {
				float otherRelativeX = (Gdx.graphics.getWidth() * 0.5f)
						+ (((beat + (((float) i) / steps)) - editor.camera.position.x) * (units));
				Main.fillRect(main.batch, otherRelativeX, beatRulerY + 8, 2, -16);
			}

			// beat line
			main.batch.setColor(0, 0, 0, 1);
			Main.fillRect(main.batch, relativeX, beatRulerY + 8, 2, -24);

			main.font.draw(main.batch, "" + beat, relativeX, beatRulerY + main.font.getLineHeight(),
					0, Align.center, false);
		}
		
		// horizontal line
		main.batch.setColor(0, 0, 0, 1);
		Main.fillRect(main.batch, 0, beatRulerY, Gdx.graphics.getWidth(), 2);
		main.batch.setColor(1, 1, 1, 1);
		
		main.font.setColor(1, 1, 1, 1);
		main.batch.setColor(1, 1, 1, 1);

		main.batch.end();

		// render editor
		editor.render(main.batch);
		main.batch.setProjectionMatrix(main.camera.combined);

		// dim if confirmation dialog is visible
		if (stageSetup != null && stageSetup.shouldDim()) {
			main.batch.begin();
			main.batch.setColor(0.25f, 0.25f, 0.25f, 1);
			Main.fillRect(main.batch, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			main.batch.setColor(1, 1, 1, 1);
			main.batch.end();
		}

		if (stageSetup != null) stage.render(main.batch);
	}

	@Override
	public void renderUpdate() {
		editor.inputUpdate();
		editor.renderUpdate();
	}

	@Override
	public void tickUpdate() {
	}

	@Override
	public void getDebugStrings(Array<String> array) {
	}

	@Override
	public void resize(int width, int height) {
		editor.resize(width, height);
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
