package chrislo27.remixer.editor;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
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
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Pool;

import chrislo27.remixer.Keybinds;
import chrislo27.remixer.Main;
import chrislo27.remixer.game.Game;
import chrislo27.remixer.pattern.Pattern;
import chrislo27.remixer.registry.CueList;
import chrislo27.remixer.registry.GameList;
import chrislo27.remixer.track.Remix;
import chrislo27.remixer.track.SoundEffect;
import ionium.util.MathHelper;
import ionium.util.Utils;
import ionium.util.i18n.Localization;
import ionium.util.input.AnyKeyPressed;
import ionium.util.render.StencilMaskUtil;

public class Editor extends InputAdapter implements Disposable {

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
	public static final int RESIZE_BEAT_DISTANCE = 16;
	public static final float MIN_RESIZE = 0.25f;

	private static GlyphLayout tmpLayout = new GlyphLayout();
	private static Vector3 tmpVec3 = new Vector3();
	private final Main main;

	public OrthographicCamera camera;

	private Remix remix;
	private float startPos = 0;

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
	private int isResizing = 0;
	private float durationX = 0;
	private float originalDuration = 1;
	private Rectangle tmpBoundsCalc = new Rectangle();
	private Array<SoundEffect> copyArray = new Array<>();

	private int currentGame = 0;
	private float gameScroll = 0;
	private int currentPattern = 0;
	private float patternScroll = 0;
	private Array<StoredPattern> storedPatterns = new Array<>();

	private Music music;
	private FileHandle musicFile;

	public Editor(Main main) {
		this.main = main;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);

		camera.position.x = camera.viewportWidth * 0.4f;

		Array<Game> games = GameList.instance().games.getAllValues();
		for (int i = 0; i < games.size; i++) {
			Game g = games.get(i);

			storedPatterns.add(new StoredPattern(g));
		}
	}

	public Music getMusic() {
		return music;
	}

	public FileHandle getMusicFile() {
		return musicFile;
	}

	public boolean setMusic(FileHandle file) {
		if (music != null) {
			music.dispose();
		}

		musicFile = file;

		boolean success = true;

		if (file == null) {
			music = null;
		} else {
			try {
				Music mus = Gdx.audio.newMusic(file);
				music = mus;
			} catch (GdxRuntimeException e) {
				Main.logger.warn("Failed to load music file", e);

				success = false;
			}
		}

		remix.setMusic(music);

		return success;
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
		if (!remix.isPaused()) remix.setCurrentBeat(startPos);

		remix.start();
	}

	public void setRemix(Remix r) {
		remix = r;
		startPos = Math.min(0, r.musicStartTime);
		clearSelection();
		clearOldPositionArray();
	}

	public Remix getRemix() {
		return remix;
	}

	private void renderTracker(SpriteBatch batch, float position, String extraText, int linesUp) {
		main.fontBordered.draw(main.batch, String.format("%.2f", position), position * BLOCK_SIZE_X,
				remix.tracks.size * BLOCK_SIZE_Y + main.fontBordered.getCapHeight() * 3f, 0,
				Align.left, false);
		// draw seconds in format mm:ss
		float beatSecs = Remix.getSecFromBeat(position, remix.bpm);

		main.fontBordered.draw(main.batch,
				(beatSecs < 0 ? "-" : "") + String.format("%1$02d:%2$02.3f",
						(int) (Math.abs(beatSecs) / 60), Math.abs(beatSecs) % 60),
				position * BLOCK_SIZE_X,
				remix.tracks.size * BLOCK_SIZE_Y + main.fontBordered.getCapHeight() * 4.5f, 0,
				Align.left, false);

		if (extraText != null) {
			main.fontBordered.draw(main.batch, Localization.get(extraText), position * BLOCK_SIZE_X,
					remix.tracks.size * BLOCK_SIZE_Y
							+ main.fontBordered.getCapHeight() * (3f + linesUp * 1.5f),
					0, Align.right, false);
		}
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
						sfx.duration * BLOCK_SIZE_X, BLOCK_SIZE_Y)) {
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

		{
			// draw music start
			batch.setColor(1, 0, 0, 1);
			Main.fillRect(batch,
					Remix.getBeatFromSec(remix.musicStartTime, remix.bpm) * BLOCK_SIZE_X, 0, 4,
					BLOCK_SIZE_Y * (remix.tracks.size + 1));
			batch.setColor(1, 1, 1, 1);

			main.fontBordered.setColor(1, 0.65f, 0.65f, 1);
			renderTracker(batch, Remix.getBeatFromSec(remix.musicStartTime, remix.bpm),
					"editor.musicStart", 1);
		}

		{
			batch.setColor(0.25f, 0.85f, 0.85f, 1);
			Main.fillRect(batch, startPos * BLOCK_SIZE_X, 0, 4,
					BLOCK_SIZE_Y * (remix.tracks.size + 1));
			batch.setColor(1, 1, 1, 1);

			// draw start position
			main.fontBordered.setColor(0.25f, 0.85f, 0.85f, 1);
			renderTracker(batch, startPos, "editor.beat", 0);
			main.fontBordered.setColor(1, 1, 1, 1);

		}

		if (remix.isStarted() || remix.isPaused()) {
			// tracker
			batch.setColor(0, 1, 0, 1);
			Main.fillRect(batch, remix.getCurrentBeat() * BLOCK_SIZE_X, 0, 2,
					BLOCK_SIZE_Y * (remix.tracks.size + 1));

			// numbers
			main.fontBordered.setColor(0.25f, 0.85f, 0.25f, 1);
			renderTracker(batch, remix.getCurrentBeat(), "editor.beat", 0);
			main.fontBordered.setColor(1, 1, 1, 1);
		}

		{
			// beat numbers
			main.font.setColor(0, 0, 0, 1);
			for (int i = (int) (camera.position.x - camera.viewportWidth * 0.5f)
					/ BLOCK_SIZE_X; i <= (camera.position.x + camera.viewportWidth * 0.5f)
							/ BLOCK_SIZE_X; i++) {
				main.font.draw(main.batch, "" + i, i * BLOCK_SIZE_X,
						remix.tracks.size * BLOCK_SIZE_Y + main.font.getCapHeight() * 1.5f, 0,
						Align.left, false);
			}
			main.font.setColor(1, 1, 1, 1);
		}

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
						y - region.getRegionHeight() * 0.5f, Math.min(32, region.getRegionWidth()),
						Math.min(32, region.getRegionHeight()));

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
			Array<String> patternList = storedPatterns.get(currentGame).patterns;
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

		batch.flush();
		StencilMaskUtil.resetMask();

		{
			Game current = GameList.instance().games.getAllValues().get(currentGame);
			if (current.contributors != null) {
				float x = SELECT_BAR_WIDTH + 16;
				float y = SELECT_BAR_HEIGHT - 8;

				main.font.setColor(1, 1, 1, 0.5f);
				main.font.draw(batch,
						Localization.get("editor.contributions", current.contributors), x, y);

				if (current == GameList.getGame("custom")) {
					if (storedPatterns.get(currentGame).patterns.size == 0) {
						main.font.draw(batch,
								Localization.get("editor.missingCustomWarning",
										current.contributors),
								x, y - main.font.getLineHeight(),
								Gdx.graphics.getWidth() - SELECT_BAR_WIDTH * 1.25f, Align.left,
								true);
					}
				}
				main.font.setColor(1, 1, 1, 1);
			}
		}

		batch.end();
	}

	public void renderSoundEffect(SpriteBatch batch, Game currentGame, SoundEffect sfx) {
		float x = sfx.position.x;
		float y = sfx.position.y;
		float width = sfx.duration * BLOCK_SIZE_X;
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
		if (remix.isStarted()) remix.update(Gdx.graphics.getDeltaTime(), false);

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

		if (isResizing != 0) {
			firstSfx.position.y = firstOldPos.y;

			if (isResizing == 1) {
				firstSfx.position.x = MathUtils.clamp(MathHelper.snapToNearest(firstSfx.position.x,
						lockingInterval * BLOCK_SIZE_X), 0, durationX - MIN_RESIZE);
				firstSfx.duration = (durationX - firstSfx.position.x) / BLOCK_SIZE_X;
			} else if (isResizing == 2) {
				firstSfx.position.x = durationX;
				firstSfx.duration = (MathHelper.snapToNearest(mouse.x,
						lockingInterval * BLOCK_SIZE_X) - firstSfx.position.x) / BLOCK_SIZE_X;
			}

			firstSfx.duration = Math.max(MIN_RESIZE, firstSfx.duration);
		} else {
			firstSfx.position.x = MathHelper.lockAtIntervals(firstSfx.position.x,
					lockingInterval * BLOCK_SIZE_X);
			firstSfx.position.y = MathHelper.lockAtIntervals(firstSfx.position.y, BLOCK_SIZE_Y);
		}

		// calculate the rectangle the selection encompasses
		Rectangle bounds = tmpBoundsCalc;
		bounds.set(firstSfx.position.x, firstSfx.position.y, firstSfx.duration * BLOCK_SIZE_X,
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
					sfx.duration * BLOCK_SIZE_X, BLOCK_SIZE_Y);
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

					Rectangle.tmp.set(sfx.position.x, sfx.position.y, sfx.duration * BLOCK_SIZE_X,
							BLOCK_SIZE_Y);

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

		if (bounds.x < 0) bounds.x = 0;

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

				Rectangle.tmp.set(sfx.position.x, sfx.position.y, sfx.duration * BLOCK_SIZE_X,
						BLOCK_SIZE_Y);

				if (Rectangle.tmp.overlaps(tmpBoundsCalc)
						|| tmpBoundsCalc.overlaps(Rectangle.tmp)) {
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

				if (isResizing != 0 && i == 0) {
					selection.get(i).duration = originalDuration;
				}

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

		if (selection.size > 0) {
			selection.sort();
			remix.setCurrentBeat(selection.first().beat);
		}

		isMoving = false;
		isResizing = 0;
		clearOldPositionArray();
	}

	public void beginMoving(boolean copy, int resize, float mouseX, float mouseY) {
		if (copy && resize != 0)
			throw new IllegalStateException("Cannot be copying and resizing at the same time!");

		moveOrigin.set(mouseX, mouseY);
		isMoving = true;
		clearOldPositionArray();

		if (copy) {
			copyArray.clear();

			for (int i = 0; i < selection.size; i++) {
				SoundEffect sfx = selection.get(i);
				SoundEffect copied = new SoundEffect(sfx);

				copied.selected = true;
				copyArray.add(copied);
				remix.tracks.first().add(copied);
			}

			clearSelection();
			selection.addAll(copyArray);
			selection.sort();

			copyArray.clear();
		} else if (resize != 0) {
			isResizing = resize;

			if (isResizing == 1) {
				durationX = selection.first().position.x
						+ selection.first().duration * BLOCK_SIZE_X;
			} else if (isResizing == 2) {
				durationX = selection.first().position.x;
			}

			originalDuration = selection.first().duration;
		}

		// copy old positions over
		for (int i = 0; i < selection.size; i++) {
			SoundEffect sfx = selection.get(i);
			Vector2 vec2 = vec2Pool.obtain();

			vec2.set(sfx.position.x, copy ? Short.MIN_VALUE : sfx.position.y);

			oldPositions.add(vec2);
		}

		if (oldPositions.size > 0 && copy) {
			moveOrigin.set(oldPositions.first());
		}
	}

	public void inputUpdate() {
		if (remix.isStarted()) return;

		if (AnyKeyPressed.isAKeyPressed(Keybinds.LEFT) || (Gdx.input.getX() <= SCREEN_EDGE_SCROLL
				&& Gdx.input.isButtonPressed(Buttons.LEFT) && Gdx.input.getY() > 48)) {
			camera.position.x -= Gdx.graphics.getDeltaTime() * CAMERA_SPEED
					* (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)
							|| Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT) ? 4 : 1);
			camera.update();
		}
		if (AnyKeyPressed.isAKeyPressed(Keybinds.RIGHT)
				|| (Gdx.graphics.getWidth() - Gdx.input.getX() <= SCREEN_EDGE_SCROLL
						&& Gdx.input.isButtonPressed(Buttons.LEFT) && Gdx.input.getY() > 48)) {
			camera.position.x += Gdx.graphics.getDeltaTime() * CAMERA_SPEED
					* (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)
							|| Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT) ? 4 : 1);
			camera.update();
		}

		if (Gdx.input.isKeyJustPressed(Keys.HOME)) {
			camera.position.x = 0;
			camera.update();
		} else if (Gdx.input.isKeyJustPressed(Keys.END)) {
			remix.recalculate();
			camera.position.x = remix.getLastBeat() * BLOCK_SIZE_X;
			camera.update();
		} else if (Gdx.input.isKeyJustPressed(Keys.PAGE_UP)) {
			camera.position.x -= BLOCK_SIZE_X * 4;
			camera.update();
		} else if (Gdx.input.isKeyJustPressed(Keys.PAGE_DOWN)) {
			camera.position.x += BLOCK_SIZE_X * 4;
			camera.update();
		}

		if (Gdx.input.getY() > 48) {
			Vector3 mouse = unprojectMouse();

			if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
				startPos = MathHelper.snapToNearest(mouse.x / BLOCK_SIZE_X, lockingInterval);
			}

			if (Gdx.input.isButtonPressed(Buttons.MIDDLE) && !remix.isPaused()) {
				remix.musicStartTime = Remix.getSecFromBeat(
						MathHelper.snapToNearest(mouse.x / BLOCK_SIZE_X, lockingInterval),
						remix.bpm);
			}
		}

		if (remix.isPaused()) return;

		if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
			if (isMoving) {
				updateMovementOfSelected();
			}
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
								sfx.position.y, sfx.duration * BLOCK_SIZE_X, BLOCK_SIZE_Y));

						if (Rectangle.tmp2.overlaps(Rectangle.tmp)) {
							sfx.selected = true;
							selection.add(sfx);
						}
					}
				}

				if (selection.size > 0) {
					selection.sort();
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
						boolean tookAction = false;

						if (selection.size == 1 && isPointIn.cue.canAlterDuration) {
							int r = 0;

							if ((mouse.x >= isPointIn.position.x
									&& mouse.x <= isPointIn.position.x + RESIZE_BEAT_DISTANCE)) {
								r = 1;
							} else if ((mouse.x >= isPointIn.position.x
									+ isPointIn.duration * BLOCK_SIZE_X - RESIZE_BEAT_DISTANCE
									&& mouse.x <= isPointIn.position.x
											+ isPointIn.duration * BLOCK_SIZE_X)) {
								r = 2;
							}

							if (r != 0) {
								beginMoving(false, r, mouse.x, mouse.y);
								tookAction = true;
							}
						}

						if (!tookAction) {
							boolean copy = Gdx.input.isKeyPressed(Keys.ALT_LEFT)
									|| Gdx.input.isKeyPressed(Keys.ALT_RIGHT);

							beginMoving(copy, 0, mouse.x, mouse.y);

							tookAction = true;
						}
					}
				}
			}
		}

		if (AnyKeyPressed.isAKeyJustPressed(Keybinds.DELETE)) {
			deleteSelection();
		}

	}

	private void moveToPattern(int pattern) {
		currentPattern = MathUtils.clamp(pattern, 0,
				storedPatterns.get(currentGame).patterns.size - 1);
		storedPatterns.get(currentGame).patternScroll = currentPattern;
	}

	private void moveToGame(int game, boolean loop) {
		storedPatterns.get(currentGame).patternScroll = currentPattern;

		currentGame = game;

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

		currentPattern = storedPatterns.get(currentGame).patternScroll;
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
					&& Gdx.input.getX() > SELECT_BAR_WIDTH && !isSelecting) {
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

				beginMoving(false, 0, selection.first().position.x, selection.first().position.y);

				return true;
			}
		}

		return false;
	}

	@Override
	public void dispose() {
		if (music != null) music.dispose();
	}

	private class StoredPattern {

		final Game game;
		final Array<String> patterns;
		int patternScroll = 0;

		StoredPattern(Game g) {
			game = g;

			patterns = new Array<>();
			// initialize single cue patterns
			CueList.instance();
			for (String s : game.patterns.getAllKeys()) {
				patterns.add(s);
			}
		}
	}

}
