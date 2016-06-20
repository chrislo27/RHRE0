package chrislo27.remixer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import chrislo27.remixer.utils.BpmCalc;
import ionium.audio.transition.MusicTransitioner;
import ionium.registry.AssetRegistry;
import ionium.screen.Updateable;
import ionium.util.i18n.Localization;

public class MainMenuScreen extends Updateable<Main> {

	private static final int ARCHITECT_LETTER_SPACING = 85;
	private static final float ARCHITECT_MOVE_AMOUNT = 16;
	private static final float MUSIC_BPM = 239 / 2f;
	private static final float MUSIC_SEC_PER_BEAT = (1f / MUSIC_BPM) * 60;

	private float architectMove = 0;
	private boolean invertMovement = false;

	public MainMenuScreen(Main m) {
		super(m);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.35f, 0.4f, 0.7f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Texture rhLogo = AssetRegistry.getTexture("rhLogo");
		Texture architectLogo = AssetRegistry.getTexture("architectLogo");
		float scale = 0.5f;

		main.batch.begin();
		main.batch.setColor(1, 1, 1, 1);

		float centerX = Gdx.graphics.getWidth() * 0.5f;

		main.batch.draw(rhLogo, centerX - rhLogo.getWidth() * 0.5f * scale,
				Gdx.graphics.getHeight() - rhLogo.getHeight() * scale
						+ architectLogo.getHeight() * scale * 0.75f,
				rhLogo.getWidth() * scale, rhLogo.getHeight() * scale);

		float architectX = centerX - architectLogo.getWidth() * 0.5f * scale;
		float architectY = Gdx.graphics.getHeight() - rhLogo.getHeight() * scale
				+ architectLogo.getHeight() * scale;
		float sectionWidth = ARCHITECT_LETTER_SPACING * scale;
		float sectionHeight = architectLogo.getHeight() * scale;

		for (int i = 0; i < 9; i++) {
			main.batch.draw(architectLogo, architectX + sectionWidth * i,
					architectY + architectMove * ARCHITECT_MOVE_AMOUNT * scale
							* (i % 2 == 0 ? -1 : 1) * (invertMovement ? -1 : 1),
					sectionWidth, sectionHeight, ARCHITECT_LETTER_SPACING * i, 0,
					ARCHITECT_LETTER_SPACING, architectLogo.getHeight(), false, false);
		}

		main.font.setColor(1, 1, 1, 1);
		main.font.getData().setScale(0.75f);
		main.font.draw(main.batch,
				Localization.get("mainMenu.kevinMacleodAttribution", "Off to Osaka"), 4,
				4 + main.font.getLineHeight() * 3);
		main.font.getData().setScale(1);
		main.font.draw(main.batch, Main.version, Gdx.graphics.getWidth() - 4,
				8 + main.font.getCapHeight(), 0, Align.right, false);

		Texture splash = AssetRegistry.getTexture("splashLogo");
		main.batch.draw(splash, Gdx.graphics.getWidth() - 4 - 64, 8 + main.font.getLineHeight(), 64,
				128);

		main.batch.end();
	}

	@Override
	public void renderUpdate() {
		Music m = AssetRegistry.getMusic("mainMenuMusic");
		float position = m.getPosition() + 0.25f;
		float beatPosition = BpmCalc.getBeatFromSec(position, MUSIC_BPM);
		float beatPositionDec = beatPosition - ((int) beatPosition);

		architectMove = beatPositionDec * 2;
		if (architectMove > 1) {
			architectMove = 0;
		} else {
			architectMove = 1f - architectMove;
		}

		invertMovement = ((int) beatPosition) % 2 == 0;
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
		Music m = AssetRegistry.getMusic("mainMenuMusic");

		m.setLooping(true);
		m.play();

		architectMove = 0;
	}

	@Override
	public void hide() {
		Music m = AssetRegistry.getMusic("mainMenuMusic");

		m.setLooping(false);
		MusicTransitioner.fade(m, 1, 0, 0.5f);
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
