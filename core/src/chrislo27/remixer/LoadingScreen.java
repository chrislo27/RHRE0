package chrislo27.remixer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import ionium.registry.AssetRegistry;
import ionium.screen.AssetLoadingScreen;
import ionium.templates.Main;
import ionium.util.DebugSetting;
import ionium.util.packer.TileTexturePacker;

public class LoadingScreen extends AssetLoadingScreen {

	private static final float SPLASH_FADE = 0.5f;

	private float splashOpacity = 1;
	private float splashDecay = 2;

	public LoadingScreen(Main m) {
		super(m);
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		Texture splash = AssetRegistry.getTexture("splashLogo");

		main.batch.begin();

		main.batch.setColor(0, 0, 0, splashOpacity);
		Main.fillRect(main.batch, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		main.batch.setColor(1, 1, 1, splashOpacity);
		main.batch.draw(splash, Gdx.graphics.getWidth() * 0.5f - splash.getWidth() * 0.5f,
				Gdx.graphics.getHeight() * 0.5f - splash.getHeight() * 0.5f);

		main.batch.setColor(1, 1, 1, 1);

		main.batch.end();
	}

	@Override
	public void renderUpdate() {
		super.renderUpdate();

		if (shouldFinishLoading() && splashDecay > 0) splashDecay = 0;

		if (splashDecay <= 0) {
			splashDecay = 0;

			splashOpacity = Math.max(splashOpacity - Gdx.graphics.getDeltaTime() / SPLASH_FADE, 0);
		} else {
			splashDecay -= Gdx.graphics.getDeltaTime();
		}
	}

	@Override
	public boolean canFinishLoading() {
		return splashOpacity <= 0;
	}

	@Override
	public void onFinishLoading() {
		super.onFinishLoading();

		// pack game icons
		TileTexturePacker ttp = new TileTexturePacker();
		ttp.mustUsePowerOfTwo = true;
		ttp.maxTextureSize = 512;

		//		Array<Texture> temp = new Array<>();
		//
		//		for (Game g : GameList.instance().games.getAllValues()) {
		//			ttp.addTexture(g.name, new Texture("images/games/" + g.name + ".png"));
		//		}
		//
		//		ttp.pack();
		//
		//		GameList.instance().atlas = ttp.getPackedTexture();
		//		Array<AtlasRegion> all = GameList.instance().atlas.getRegions();
		//		GameList.instance().allRegions = new ObjectMap<>();
		//
		//		for (AtlasRegion region : all) {
		//			GameList.instance().allRegions.put(region.name, region);
		//		}
		//
		//		for (Texture t : temp) {
		//			t.dispose();
		//		}

		if (DebugSetting.debug) {
			Main.filltex = new Texture("images/games/moaiDooWop2.png");
			Main.filltexRegion.setTexture(Main.filltex);
		}
	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
