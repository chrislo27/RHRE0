package chrislo27.remixer.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import chrislo27.remixer.Keybinds;
import chrislo27.remixer.Main;
import chrislo27.remixer.game.Game;
import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.registry.GameList;
import chrislo27.remixer.track.Remix;
import chrislo27.remixer.track.SoundEffect;
import ionium.util.MathHelper;
import ionium.util.Utils;
import ionium.util.i18n.Localization;
import ionium.util.input.AnyKeyPressed;
import ionium.util.render.StencilMaskUtil;

public class Editor extends InputAdapter {

	public static final int BLOCK_SIZE_X = 256;
	public static final int BLOCK_SIZE_Y = 64;
	public static final int TRACK_COUNT = 5;
	public static final int SNAP_DISTANCE = 4;
	public static final float CAMERA_SPEED = 1024;
	public static final boolean SHOW_GAME_ICON_ALWAYS = true;
	public static final int SCREEN_EDGE_SCROLL = 64;
	public static final int SELECT_BAR_HEIGHT = 256;
	public static final int SELECT_BAR_WIDTH = (int) (SELECT_BAR_HEIGHT * 1.5f);
	public static final int SCROLL_EXTRA_ITEMS = SELECT_BAR_HEIGHT / 64;

	private static GlyphLayout tmpLayout = new GlyphLayout();
	private static Vector3 tmpVec3 = new Vector3();
	private final Main main;

	public OrthographicCamera camera;

	private Remix remix;

	private Array<SoundEffect> selection = new Array<>();
	private Vector2 selectionOrigin = new Vector2();
	private boolean isSelecting = false;

	public float lockingInterval = 0.5f;
	private Vector2 moveOrigin = new Vector2();
	private Array<Vector2> oldPositions = new Array<>();
	private Array<Vector2> tmpVec2Array = new Array<>();
	private Pool<Vector2> vec2Pool = new Pool<Vector2>() {

		@Override
		protected Vector2 newObject() {
			return new Vector2();
		}

	};
	private boolean isMoving = false;
	private Rectangle tmpBoundsCalc = new Rectangle();

	private int currentGame = 0;
	private float gameScroll = 0;
	private int currentPattern = 0;
	private float patternScroll = 0;
	private Array<Array<String>> storedPatterns = new Array<>();

	public Editor(Main main) {
		this.main = main;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);

		camera.position.x = camera.viewportWidth * 0.4f;

		Array<Game> games = GameList.instance().games.getAllValues();
		for (int i = 0; i < games.size; i++) {
			Game g = games.get(i);
			Array<String> patternList = new Array<>();

			for (int j = 0; j < g.patterns.getAllValues().size; j++) {
				String name = g.patterns.getAllKeys().get(j);
				patternList.add(name);
			}

			storedPatterns.add(patternList);
		}
	}

	public void clearSelection() {
		for (SoundEffect sfx : selection) {
			sfx.selected = false;
		}

		selection.clear();

		isSelecting = false;
	}

	public void deleteSelection() {
		for (int track = 0; track < remix.tracks.size; track++) {
			remix.tracks.get(track).removeAll(selection, true);
		}

		clearSelection();
	}

	private void clearOldPositionArray() {
		vec2Pool.freeAll(oldPositions);
		oldPositions.clear();
	}

	public void play() {
		remix.start();
		if (selection.size > 0) {
			selection.sort();
			remix.setCurrentBeat(selection.first().beat);
			SoundEffect last = selection.get(selection.size - 1);
			remix.setLastBeat(last.beat + last.cue.duration);
		}
	}

	public void setRemix(Remix r) {
		remix = r;
	}

	public Remix getRemix() {
		return remix;
	}

	public void render(SpriteBatch batch) {
		camera.position.y = 96;
		if (remix.isStarted()) {
			float tracker = remix.getCurrentBeat() * BLOCK_SIZE_X;

			if (tracker < camera.position.x - camera.viewportWidth * 0.5f) {
				camera.position.x = tracker - camera.viewportWidth * 0.5f;
			} else if (tracker > camera.position.x + camera.viewportWidth * 0.5f) {
				camera.position.x = tracker + camera.viewportWidth * 0.5f;
			}
		}
		camera.position.x = Math.max(0, camera.position.x);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		if (remix == null) return;

		batch.begin();

		for (int track = 0; track < remix.tracks.size; track++) {
			Game currentGame = null;

			for (SoundEffect sfx : remix.tracks.get(track)) {
				if (!isMoving) {
					sfx.position.set(sfx.beat * BLOCK_SIZE_X, track * BLOCK_SIZE_Y);
				}

				if (MathHelper.intersects(camera.position.x - camera.viewportWidth * 0.5f,
						camera.position.y - camera.viewportHeight * 0.5f, camera.viewportWidth,
						camera.viewportHeight, sfx.position.x, sfx.position.y,
						sfx.cue.duration * BLOCK_SIZE_X, BLOCK_SIZE_Y)) {
					renderSoundEffect(batch, currentGame, sfx);
				}

				if (currentGame != sfx.cue.game) {
					currentGame = sfx.cue.game;
				}
			}
		}

		batch.setColor(0, 0, 0, 1);

		for (int i = (int) ((camera.position.x - camera.viewportWidth * 0.5f)
				/ BLOCK_SIZE_X); i <= (camera.position.x + camera.viewportWidth * 0.5f)
						/ BLOCK_SIZE_X; i++) {
			Main.fillRect(batch, i * BLOCK_SIZE_X, 0, 2, BLOCK_SIZE_Y * remix.tracks.size);
		}

		for (int i = 0; i <= remix.tracks.size; i++) {
			Main.fillRect(batch, camera.position.x - camera.viewportWidth * 0.5f, i * BLOCK_SIZE_Y,
					camera.viewportWidth, 2);
		}

		// tracker
		if (remix.isStarted()) {
			batch.setColor(0, 1, 0, 1);
			Main.fillRect(batch, remix.getCurrentBeat() * BLOCK_SIZE_X, 0, 2,
					BLOCK_SIZE_Y * remix.tracks.size);
		}

		batch.setColor(1, 1, 1, 1);

		// beat numbers
		main.font.setColor(0, 0, 0, 1);
		for (int i = (int) Math.max(0, camera.position.x - camera.viewportWidth * 0.5f)
				/ BLOCK_SIZE_X; i <= (camera.position.x + camera.viewportWidth * 0.5f)
						/ BLOCK_SIZE_X; i++) {
			main.font.draw(main.batch, "" + i, i * BLOCK_SIZE_X,
					remix.tracks.size * BLOCK_SIZE_Y + main.font.getCapHeight() * 1.5f, 0,
					Align.left, false);
		}
		main.font.setColor(1, 1, 1, 1);

		if (Gdx.input.isButtonPressed(Buttons.LEFT) && isSelecting) {
			Vector3 mouse = unprojectMouse();
			float width = mouse.x - selectionOrigin.x;
			float height = mouse.y - selectionOrigin.y;

			batch.setColor(0.1f, 0.75f, 0.75f, 0.333f);
			Main.fillRect(batch, selectionOrigin.x, selectionOrigin.y, width, height);
			batch.setColor(0.1f, 0.85f, 0.85f, 1);
			Main.drawRect(batch, selectionOrigin.x, selectionOrigin.y, width, height, 4);
			batch.setColor(1, 1, 1, 1);

		}

		if (isMoving) {
			// selection bounds renderer
			batch.setColor(0.1f, 0.75f, 0.75f, 0.125f);
			Main.fillRect(batch, tmpBoundsCalc.x, tmpBoundsCalc.y, tmpBoundsCalc.width,
					tmpBoundsCalc.height);
			batch.setColor(1, 1, 1, 1);
		}

		batch.setProjectionMatrix(main.camera.combined);

		// select area
		batch.setColor(0, 0, 0, 0.5f);
		Main.fillRect(batch, 0, 0, Gdx.graphics.getWidth(), SELECT_BAR_HEIGHT);
		batch.setColor(1, 1, 1, 1);

		batch.end();

		StencilMaskUtil.prepareMask();
		main.shapes.setProjectionMatrix(main.camera.combined);
		main.shapes.begin(ShapeType.Filled);
		main.shapes.rect(0, 0, Gdx.graphics.getWidth(), SELECT_BAR_HEIGHT);
		main.shapes.end();

		// actual stuff
		main.batch.begin();
		StencilMaskUtil.useMask();

		// game list
		{
			Array<Game> games = GameList.instance().games.getAllValues();
			for (int i = Math.max(0, currentGame - SCROLL_EXTRA_ITEMS); i < Math.min(games.size,
					currentGame + SCROLL_EXTRA_ITEMS); i++) {
				Game g = games.get(i);

				float x = 32;
				float y = SELECT_BAR_HEIGHT * 0.5f - i * BLOCK_SIZE_Y + gameScroll * BLOCK_SIZE_Y;
				AtlasRegion region = GameList.getIcon(g.name);

				batch.draw(region, x - region.getRegionWidth() * 0.5f,
						y - region.getRegionHeight() * 0.5f);

				main.fontBordered.setColor(1, 1, 1, 1);
				if (i == currentGame) {
					main.fontBordered.setColor(0.4f, 1, 1, 1);
				}

				tmpLayout.setText(main.fontBordered, Localization.get("game." + g.name),
						main.fontBordered.getColor(), SELECT_BAR_WIDTH - x, Align.left, false);

				main.fontBordered.draw(batch, tmpLayout, x * 2, y + tmpLayout.height * 0.5f);

				main.fontBordered.setColor(1, 1, 1, 1);
			}
		}

		// line dividing game list and pattern list
		batch.setColor(1, 1, 1, 1);
		Main.fillRect(batch, SELECT_BAR_WIDTH, 0, 1, SELECT_BAR_HEIGHT);

		{
			Array<String> patternList = storedPatterns.get(currentGame);
			for (int i = Math.max(0, currentPattern - SCROLL_EXTRA_ITEMS); i < Math
					.min(patternList.size, currentPattern + SCROLL_EXTRA_ITEMS); i++) {
				String pattern = patternList.get(i);

				float x = SELECT_BAR_WIDTH + 32;
				float y = SELECT_BAR_HEIGHT * 0.5f - i * BLOCK_SIZE_Y
						+ patternScroll * BLOCK_SIZE_Y;

				main.fontBordered.setColor(1, 1, 1, 1);
				if (i == currentPattern) {
					main.fontBordered.setColor(0.4f, 1, 1, 1);
				}

				tmpLayout.setText(main.fontBordered, pattern, main.fontBordered.getColor(),
						SELECT_BAR_WIDTH - x, Align.left, false);

				main.fontBordered.draw(batch, tmpLayout, x, y + tmpLayout.height * 0.5f);

				main.fontBordered.setColor(1, 1, 1, 1);
			}
		}

		batch.end();
		StencilMaskUtil.resetMask();
	}

	public void renderSoundEffect(SpriteBatch batch, Game currentGame, SoundEffect sfx) {
		float x = sfx.position.x;
		float y = sfx.position.y;
		float width = sfx.cue.duration * BLOCK_SIZE_X;
		float height = BLOCK_SIZE_Y;
		int thickness = 4;
		int padding = 2;
		float textOffsetX = thickness + padding;

		if (!sfx.selected) {
			batch.setColor(0.75f, 0.75f, 0.75f, 0.75f);
		} else {
			batch.setColor(0.85f, 0.85f, 1f, 0.75f);
		}

		Main.fillRect(batch, x, y, width, height);

		if (!sfx.selected) {
			batch.setColor(0.25f, 0.25f, 0.25f, 0.5f);
		} else {
			batch.setColor(0.25f, 0.25f, 0.75f, 0.5f);
		}

		Main.drawRect(batch, x, y, width, height, thickness);

		batch.setColor(1, 1, 1, 1);

		if (currentGame != sfx.cue.game || SHOW_GAME_ICON_ALWAYS) {
			AtlasRegion region = GameList.getIcon(sfx.cue.game.name);

			float regionWidth = Math.min(region.getRegionWidth(),
					width - thickness * 2 - padding * 2);
			float regionHeight = (region.getRegionHeight() * 1f / region.getRegionWidth())
					* regionWidth;

			//textOffsetX += regionWidth + padding;

			batch.setColor(1, 1, 1, 0.25f);
			batch.draw(region, x + thickness + padding, y + height * 0.5f - regionHeight * 0.5f,
					regionWidth, regionHeight);
			batch.setColor(1, 1, 1, 1);
		}

		BitmapFont font = main.font;

		font.setColor(0, 0, 0, 1);
		font.getData().setScale(0.75f);

		tmpLayout.setText(font, Localization.get(sfx.cue.soundId), font.getColor(),
				width - thickness * 2 - padding - textOffsetX, Align.right, true);

		font.draw(batch, tmpLayout, x + textOffsetX, y + height * 0.5f + tmpLayout.height * 0.5f);

		font.getData().setScale(1);

	}

	public float getCursorBeat() {
		return Math.max(Math.round(unprojectMouse().x), 0);
	}

	public Vector3 unprojectMouse() {
		return camera.unproject(tmpVec3.set(Gdx.input.getX(), Gdx.input.getY(), 0));
	}

	public void renderUpdate() {
		if (remix.isStarted()) remix.update(Gdx.graphics.getDeltaTime(), selection.size > 0);

		float alpha = Gdx.graphics.getDeltaTime() * 16;

		gameScroll = MathUtils.lerp(gameScroll, currentGame, alpha);
		patternScroll = MathUtils.lerp(patternScroll, currentPattern, alpha);
	}

	public void updateMovementOfSelected() {
		if (!isMoving) return;
		if (selection.size == 0) return;

		// move selected objects relative to cursor
		Vector3 mouse = unprojectMouse();

		// set positions as relative to first object in list
		Vector2 firstOldPos = oldPositions.first();
		SoundEffect firstSfx = selection.first();

		// relative to where the mouse clicked to move
		firstSfx.position.set(firstOldPos).sub(moveOrigin);

		// offset first position by mouse
		firstSfx.position.add(mouse.x, mouse.y);

		firstSfx.position.x = MathHelper.lockAtIntervals(firstSfx.position.x,
				lockingInterval * BLOCK_SIZE_X);
		firstSfx.position.y = MathHelper.lockAtIntervals(firstSfx.position.y, BLOCK_SIZE_Y);

		// calculate the rectangle the selection encompasses
		Rectangle bounds = tmpBoundsCalc;
		bounds.set(firstSfx.position.x, firstSfx.position.y, firstSfx.cue.duration * BLOCK_SIZE_X,
				BLOCK_SIZE_Y);

		// initial set others relative to first
		for (int i = 1; i < selection.size; i++) {
			SoundEffect sfx = selection.get(i);
			Vector2 oldPos = oldPositions.get(i);

			// offset based on first as origin
			sfx.position.set(firstSfx.position).add(oldPos.x - firstOldPos.x,
					oldPos.y - firstOldPos.y);
		}

		// expand rectangle based on other selected objects
		for (int i = 1; i < selection.size; i++) {
			SoundEffect sfx = selection.get(i);

			Rectangle rect = Rectangle.tmp.set(sfx.position.x, sfx.position.y,
					sfx.cue.duration * BLOCK_SIZE_X, BLOCK_SIZE_Y);
			bounds.merge(Rectangle.tmp);

		}

		float relativeRectX = firstSfx.position.x - bounds.x;
		float relativeRectY = firstSfx.position.y - bounds.y;

		if (!Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)
				&& !Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT)) {
			outer: for (int track = 0; track < remix.tracks.size; track++) {
				mainsfx: for (SoundEffect sfx : remix.tracks.get(track)) {
					for (SoundEffect sel : selection) {
						if (sfx == sel) continue mainsfx;
					}

					Rectangle.tmp.set(sfx.position.x, sfx.position.y,
							sfx.cue.duration * BLOCK_SIZE_X, BLOCK_SIZE_Y);

					if (bounds.y + bounds.height > Rectangle.tmp.y
							&& bounds.y < Rectangle.tmp.y + Rectangle.tmp.height) {

						if (MathUtils.isEqual(bounds.x, Rectangle.tmp.x + Rectangle.tmp.width,
								SNAP_DISTANCE)) {
							bounds.x = Rectangle.tmp.x + Rectangle.tmp.width;
							break outer;
						} else if (MathUtils.isEqual(bounds.x + bounds.width, Rectangle.tmp.x,
								SNAP_DISTANCE)) {
							bounds.x = Rectangle.tmp.x - bounds.width;

							break outer;
						} else if (bounds.x <= 0) {
							bounds.x = 0;

							break outer;
						}

					}
				}
			}

		}

		// make the first item relative to bounds
		firstSfx.position.set(bounds.x + relativeRectX, bounds.y + relativeRectY);

		// other items relative to first
		for (int i = 1; i < selection.size; i++) {
			SoundEffect sfx = selection.get(i);
			Vector2 oldPos = oldPositions.get(i);

			// offset based on first as origin
			sfx.position.set(firstSfx.position).add(oldPos.x - firstOldPos.x,
					oldPos.y - firstOldPos.y);
		}
	}

	public void placeSelected() {
		if (!isMoving) return;
		// place the moved objects or reset them if they intersect others
		boolean rejectMovement = false;

		// check for OoB track or negative beat
		for (SoundEffect sfx : selection) {
			int track = (int) (sfx.position.y / BLOCK_SIZE_Y);

			if (track < 0 || track >= remix.tracks.size || sfx.position.x < 0) {
				rejectMovement = true;
				break;
			}
		}

		// check for intersection
		for (int track = 0; track < remix.tracks.size && !rejectMovement; track++) {
			mainsfx: for (SoundEffect sfx : remix.tracks.get(track)) {
				for (SoundEffect sel : selection) {
					if (sfx == sel) continue mainsfx;
				}

				Rectangle.tmp.set(sfx.position.x, sfx.position.y, sfx.cue.duration * BLOCK_SIZE_X,
						BLOCK_SIZE_Y);

				if (Rectangle.tmp.overlaps(tmpBoundsCalc)) {
					rejectMovement = true;
					break;
				}
			}
		}

		// place on toolbar area to delete
		if (Gdx.input.getY() >= Gdx.graphics.getHeight() - SELECT_BAR_HEIGHT) {
			deleteSelection();
		} else if (!rejectMovement) {
			// finally, move the thing
			// remove from track
			for (int track = 0; track < remix.tracks.size; track++) {
				remix.tracks.get(track).removeAll(selection, true);
			}

			for (SoundEffect sfx : selection) {
				sfx.beat = sfx.position.x / BLOCK_SIZE_X;

				// add to track based on position
				remix.tracks.get(MathUtils.clamp((int) (sfx.position.y / BLOCK_SIZE_Y), 0,
						remix.tracks.size - 1)).add(sfx);
			}
		} else if (rejectMovement) {
			// check old positions, if any of them are negative it means it was spawned so it should be deleted

			boolean shouldBeDeleted = false;

			for (int i = 0; i < selection.size; i++) {
				Vector2 oldPos = oldPositions.get(i);

				if (oldPos.x < 0 || oldPos.y < 0) {
					shouldBeDeleted = true;
					break;
				}
			}

			if (shouldBeDeleted) {
				deleteSelection();
			}
		}

		for (int track = 0; track < remix.tracks.size; track++) {
			remix.tracks.get(track).sort();
		}

		isMoving = false;
		clearOldPositionArray();
	}

	public void beginMoving(float mouseX, float mouseY) {
		moveOrigin.set(mouseX, mouseY);
		isMoving = true;

		// copy old positions over
		clearOldPositionArray();

		for (int i = 0; i < selection.size; i++) {
			SoundEffect sfx = selection.get(i);
			Vector2 vec2 = vec2Pool.obtain();

			vec2.set(sfx.position);

			oldPositions.add(vec2);
		}
	}

	public void inputUpdate() {
		if (Gdx.input.isKeyJustPressed(Keys.P)) {
			if (remix.isStarted()) {
				remix.stop();
			} else {
				if (selection.size > 0) {
					selection.sort();
					remix.setCurrentBeat(selection.first().beat);
					remix.setLastBeat(selection.get(selection.size - 1).beat
							+ selection.get(selection.size - 1).cue.duration);
				}
				remix.start();
			}
		}

		if (remix.isStarted()) return;

		if (Gdx.input.isButtonPressed(Buttons.LEFT) && isMoving) {
			updateMovementOfSelected();
		}

		if (!Gdx.input.isButtonPressed(Buttons.LEFT) && Utils.isButtonJustReleased(Buttons.LEFT)) {
			if (isSelecting) {
				Vector3 mouse = unprojectMouse();
				float width = mouse.x - selectionOrigin.x;
				float height = mouse.y - selectionOrigin.y;

				Rectangle.tmp.set(selectionOrigin.x, selectionOrigin.y, width, height);
				MathHelper.normalizeRectangle(Rectangle.tmp);

				if (!Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)
						&& !Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)) {
					clearSelection();
				}

				for (int track = 0; track < remix.tracks.size; track++) {
					for (SoundEffect sfx : remix.tracks.get(track)) {
						MathHelper.normalizeRectangle(Rectangle.tmp2.set(sfx.position.x,
								sfx.position.y, sfx.cue.duration * BLOCK_SIZE_X, BLOCK_SIZE_Y));

						if (Rectangle.tmp2.overlaps(Rectangle.tmp)) {
							sfx.selected = true;
							selection.add(sfx);
						}
					}
				}

				isSelecting = false;
			} else if (isMoving) {
				placeSelected();
			}
		}

		if (Gdx.input.isButtonPressed(Buttons.LEFT) && Utils.isButtonJustPressed(Buttons.LEFT)
				&& Gdx.input.getY() > 48) {
			Vector3 mouse = unprojectMouse();

			SoundEffect isPointIn = null;

			outer: for (int track = 0; track < remix.tracks.size; track++) {
				for (SoundEffect sfx : remix.tracks.get(track)) {
					if (!sfx.selected) continue;

					boolean in = sfx.isPointIn(mouse.x, mouse.y);

					if (in) {
						isPointIn = sfx;
						break outer;
					}
				}
			}

			if (Gdx.input.getY() >= Gdx.graphics.getHeight() - SELECT_BAR_HEIGHT) {
				int center = ((SELECT_BAR_HEIGHT / 2) - (BLOCK_SIZE_Y / 2));
				int properInput = Gdx.graphics.getHeight() - Gdx.input.getY();
				int relativeSpot = (int) -Math.floor((float) (properInput - center) / BLOCK_SIZE_Y);

				if (Gdx.input.getX() <= SELECT_BAR_WIDTH) {
					moveToGame(relativeSpot + currentGame, false);
				} else {
					moveToPattern(relativeSpot + currentPattern);
				}
			} else {
				if (isPointIn == null || selection.size == 0) {
					// start selecting
					selectionOrigin.set(mouse.x, mouse.y);
					isSelecting = true;
				} else {
					// start moving
					if (isPointIn != null) {
						beginMoving(mouse.x, mouse.y);
					}
				}
			}
		}

		if (AnyKeyPressed.isAKeyPressed(Keybinds.LEFT) || (Gdx.input.getX() <= SCREEN_EDGE_SCROLL
				&& Gdx.input.isButtonPressed(Buttons.LEFT))) {
			camera.position.x -= Gdx.graphics.getDeltaTime() * CAMERA_SPEED;
		}
		if (AnyKeyPressed.isAKeyPressed(Keybinds.RIGHT)
				|| (Gdx.graphics.getWidth() - Gdx.input.getX() <= SCREEN_EDGE_SCROLL
						&& Gdx.input.isButtonPressed(Buttons.LEFT))) {
			camera.position.x += Gdx.graphics.getDeltaTime() * CAMERA_SPEED;
		}

		if (Gdx.input.isKeyJustPressed(Keys.HOME)) {
			camera.position.x = 0;
		} else if (Gdx.input.isKeyJustPressed(Keys.END)) {
			remix.recalculate();
			camera.position.x = remix.getLastBeat() * BLOCK_SIZE_X;
		}

		if (AnyKeyPressed.isAKeyJustPressed(Keybinds.DELETE)) {
			deleteSelection();
		}

	}

	private void moveToPattern(int pattern) {
		currentPattern = MathUtils.clamp(pattern, 0, storedPatterns.get(currentGame).size - 1);
	}

	private void moveToGame(int game, boolean loop) {
		currentGame = game;

		currentPattern = 0;
		patternScroll = 0;

		if (loop) {
			if (currentGame < 0) {
				currentGame = GameList.instance().games.getAllValues().size - 1;
			} else if (currentGame >= GameList.instance().games.getAllValues().size) {
				currentGame = 0;
			}
		} else {
			currentGame = MathUtils.clamp(game, 0,
					GameList.instance().games.getAllValues().size - 1);
		}
	}

	@Override
	public boolean scrolled(int amount) {
		if (remix.isStarted()) return false;
		if (Gdx.graphics.getHeight() - Gdx.input.getY() > SELECT_BAR_HEIGHT) return false;

		if (Gdx.input.getX() <= SELECT_BAR_WIDTH) {
			moveToGame(currentGame + amount, false);
		} else {
			moveToPattern(currentPattern + amount);
		}

		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
			if (!isMoving && Gdx.input.getY() >= Gdx.graphics.getHeight() - SELECT_BAR_HEIGHT
					&& Gdx.input.getX() > SELECT_BAR_WIDTH) {
				// create new pattern
				clearSelection();
				isMoving = true;
				isSelecting = false;

				Pattern current = GameList.instance().games.getAllValues().get(currentGame).patterns
						.getAllValues().get(currentPattern);

				current.addPatternToArray(selection);

				for (SoundEffect sfx : selection) {
					sfx.selected = true;
					sfx.position.set(sfx.beat * BLOCK_SIZE_X, Short.MIN_VALUE);

					remix.tracks.first().add(sfx);
				}

				beginMoving(selection.first().position.x, selection.first().position.y);

				return true;
			}
		}

		return false;
	}

}
