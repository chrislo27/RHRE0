package chrislo27.remixer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import chrislo27.remixer.game.Game;
import chrislo27.remixer.registry.GameList;
import ionium.screen.AssetLoadingScreen;
import ionium.templates.Main;
import ionium.util.DebugSetting;
import ionium.util.packer.TileTexturePacker;

public class LoadingScreen extends AssetLoadingScreen {

	public LoadingScreen(Main m) {
		super(m);
	}

	@Override
	public void onFinishLoading() {
		super.onFinishLoading();

		// pack game icons
		TileTexturePacker ttp = new TileTexturePacker();
		ttp.mustUsePowerOfTwo = true;
		ttp.maxTextureSize = 512;

		Array<Texture> temp = new Array<>();

		for (Game g : GameList.instance().games.getAllValues()) {
			ttp.addTexture(g.name, new Texture("images/games/" + g.name + ".png"));
		}

		ttp.pack();

		GameList.instance().atlas = ttp.getPackedTexture();
		Array<AtlasRegion> all = GameList.instance().atlas.getRegions();
		GameList.instance().allRegions = new ObjectMap<>();

		for (AtlasRegion region : all) {
			GameList.instance().allRegions.put(region.name, region);
		}

		for (Texture t : temp) {
			t.dispose();
		}

		if (DebugSetting.debug) {
			Main.filltex = new Texture("images/games/moaiDooWop2.png");
			Main.filltexRegion.setTexture(Main.filltex);
		}
	}

}
