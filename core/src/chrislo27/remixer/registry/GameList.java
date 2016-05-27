package chrislo27.remixer.registry;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

import ionium.registry.AssetRegistry;

public class GameList {

	private static GameList instance;

	private GameList() {
	}

	public static GameList instance() {
		if (instance == null) {
			instance = new GameList();
			instance.loadResources();
		}
		return instance;
	}

	public Array<String> games = new Array<>();

	private void loadResources() {

	}

	public static AtlasRegion getIcon(String key) {
		return AssetRegistry.getAtlasRegion("gameIcons", key);
	}

}
