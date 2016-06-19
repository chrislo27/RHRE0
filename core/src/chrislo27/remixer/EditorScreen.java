package chrislo27.remixer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import ionium.screen.Updateable;

public class EditorScreen extends Updateable<Main> {

	public EditorScreen(Main m) {
		super(m);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		main.batch.begin();

		main.font.setColor(1, 1, 1, 1);
		main.font.draw(main.batch, "タルン ライト\r\n" + "レツ シ ダウン\r\n"
				+ "パパパパンチ\ntesting\nワト ヂド ユ ジャスト セイ アバオト ミ ユ リタル ビチ?\nアイル ハヴウ ユ ノ アイ グラジュエイテド タポ アフ マイ クラス イン ダ ネイヴイ シルズ.\n"
				+ "イロ ハ ニホヘト チリヌル ヲ ワ カ ヨ タレ ソ ツネ ナラム ウヰノオクヤマ ケフ コエテ アサキ ユメ ミシ ヱヒ モ セス\n" + "歩く", 0,
				Gdx.graphics.getHeight() - 64, Gdx.graphics.getWidth(), Align.center, true);

		main.batch.end();
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
	}

	@Override
	public void hide() {
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
