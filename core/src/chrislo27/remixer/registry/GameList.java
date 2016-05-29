package chrislo27.remixer.registry;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

import chrislo27.remixer.game.DonkDonk;
import chrislo27.remixer.game.Game;
import chrislo27.remixer.game.Lockstep;
import chrislo27.remixer.game.MunchyMonk;
import ionium.registry.AssetRegistry;
import ionium.templates.Main;
import ionium.util.BiObjectMap;

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

	public BiObjectMap<String, Game> games = new BiObjectMap<>();

	private void loadResources() {
		put(new Lockstep("lockstep"));
		put(new MunchyMonk("munchyMonk"));
		put(new DonkDonk("donkDonk"));

		games.getAllKeys().sort();
		games.getAllValues().sort();

	}

	public void put(Game game) {
		games.put(game.name, game);
	}

	public static Game getGame(String key) {
		return instance().games.getValue(key);
	}

	public static AtlasRegion getIcon(String key) {
		return AssetRegistry.getAtlasRegion("gameIcons", key);
	}

}
