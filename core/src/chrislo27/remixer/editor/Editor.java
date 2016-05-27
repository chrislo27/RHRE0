package chrislo27.remixer.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import chrislo27.remixer.Main;

public class Editor {

	private static Vector3 tmpVec3 = new Vector3();
	private final Main main;

	public OrthographicCamera camera;

	public Editor(Main main) {
		this.main = main;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 20f, 11.5f);

		camera.position.x = 5;
	}

	public void render(SpriteBatch batch) {
		camera.position.y = 0;
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		batch.setColor(0, 0, 0, 1);
		Main.fillRect(batch, camera.position.x - camera.viewportWidth * 0.5f, 0,
				camera.viewportWidth, (camera.viewportHeight / Gdx.graphics.getHeight()) * 2);
		batch.setColor(1, 1, 1, 1);

		batch.end();
	}

	public float getCursorBeat() {
		return Math.max((Math
				.round(camera.unproject(tmpVec3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x * 2))
				/ 2f, 0);
	}

	public void renderUpdate() {

	}

	public void inputUpdate() {

	}

	public void resize(int width, int height) {

	}

}
