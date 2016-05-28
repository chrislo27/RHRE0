package chrislo27.remixer.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;

import chrislo27.remixer.Main;
import chrislo27.remixer.game.Game;
import chrislo27.remixer.registry.GameList;
import chrislo27.remixer.track.Remix;
import chrislo27.remixer.track.SoundEffect;

public class Editor {

	public static final int BLOCK_SIZE_X = 256;
	public static final int BLOCK_SIZE_Y = 64;
	public static final int TRACK_COUNT = 5;

	private static GlyphLayout tmpLayout = new GlyphLayout();
	private static Vector3 tmpVec3 = new Vector3();
	private final Main main;

	public OrthographicCamera camera;

	private Remix remix;

	public Editor(Main main) {
		this.main = main;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);

		camera.position.x = camera.viewportWidth * 0.4f;
	}

	public void setRemix(Remix r) {
		remix = r;
	}

	public Remix getRemix() {
		return remix;
	}

	public void render(SpriteBatch batch) {
		camera.position.y = -256;
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		if (remix == null) return;

		batch.begin();

		batch.setColor(0, 0, 0, 1);

		for (int i = (int) ((camera.position.x - camera.viewportWidth * 0.5f)
				/ BLOCK_SIZE_X); i <= (camera.position.x + camera.viewportWidth * 0.5f)
						/ BLOCK_SIZE_X; i++) {
			Main.fillRect(batch, i * BLOCK_SIZE_X, 0, 2, -BLOCK_SIZE_Y * remix.tracks.size);
		}

		for (int i = 0; i <= remix.tracks.size; i++) {
			Main.fillRect(batch, camera.position.x - camera.viewportWidth * 0.5f, -i * BLOCK_SIZE_Y,
					camera.viewportWidth, 2);
		}
		batch.setColor(1, 1, 1, 1);

		main.font.setColor(0, 0, 0, 1);
		for (int i = (int) Math.max(0,
				camera.position.x - camera.viewportWidth * 0.5f); i <= camera.position.x
						+ camera.viewportWidth * 0.5f; i++) {
			main.font.draw(main.batch, "" + i, i * BLOCK_SIZE_X, main.font.getCapHeight() * 1.5f, 0,
					Align.left, false);
		}

		for (int track = 0; track < remix.tracks.size; track++) {
			Game currentGame = null;

			for (SoundEffect sfx : remix.tracks.get(track)) {
				Rectangle bounds = sfx.getBounds(Rectangle.tmp, track);

				final int borderThickness = 4;

				batch.setColor(0.75f, 0.75f, 0.75f, 0.75f);
				Main.fillRect(batch, bounds.x, -bounds.y - bounds.height, bounds.width,
						bounds.height);
				batch.setColor(0.25f, 0.25f, 0.25f, 0.75f);
				Main.drawRect(batch, bounds.x, -bounds.y - bounds.height, bounds.width,
						bounds.height, borderThickness);
				batch.setColor(1, 1, 1, 1);

				float regionWidth = 0;

				if (currentGame != sfx.cue.game) {
					currentGame = sfx.cue.game;

					AtlasRegion region = GameList.getIcon(sfx.cue.game.name);
					regionWidth = Math.min(bounds.width - borderThickness * 2,
							region.getRegionWidth());
					float regionHeight = region.getRegionHeight();

					batch.setColor(1, 1, 1, 0.5f);
					batch.draw(
							region, bounds.x
									+ borderThickness,
							-bounds.y + borderThickness - bounds.height
									+ ((bounds.height - borderThickness * 2) * 0.5f
											- regionHeight * 0.5f),
							regionWidth, regionHeight);
					batch.setColor(1, 1, 1, 1);
				}

				float xOffset = borderThickness + regionWidth + borderThickness;
				float remainingTextWidth = bounds.width - xOffset;

				main.fontBordered.getData().setScale(0.75f);

				tmpLayout.setText(main.fontBordered, sfx.cue.file, main.fontBordered.getColor(),
						remainingTextWidth, Align.left, true);

				main.fontBordered.draw(batch, tmpLayout, bounds.x + xOffset,
						-bounds.y - bounds.height * 0.5f + tmpLayout.height * 0.5f);

				main.fontBordered.getData().setScale(1);
			}
		}

		batch.end();
	}

	public float getCursorBeat() {
		return Math.max(
				Math.round(camera.unproject(tmpVec3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x),
				0);
	}

	public void renderUpdate() {
		remix.update(Gdx.graphics.getDeltaTime());
	}

	public void inputUpdate() {
		if (Gdx.input.isKeyJustPressed(Keys.P)) {
			remix.start();
		}
	}

	public void resize(int width, int height) {

	}

}
