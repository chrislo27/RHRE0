package chrislo27.remixer.editor;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import chrislo27.remixer.Main;

public class Editor {

	private final Main main;

	public OrthographicCamera camera;

	public Editor(Main main) {
		this.main = main;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 20f, 11.5f);
	}

	public void render(SpriteBatch batch) {
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		batch.end();
	}

	public void renderUpdate() {

	}

	public void inputUpdate() {

	}

	public void resize(int width, int height) {

	}

}
