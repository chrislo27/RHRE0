package chrislo27.remixer.registry;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.ObjectMap;

import chrislo27.remixer.game.BlueBirds;
import chrislo27.remixer.game.CountInGame;
import chrislo27.remixer.game.CropStomp;
import chrislo27.remixer.game.CustomSound;
import chrislo27.remixer.game.DonkDonk;
import chrislo27.remixer.game.Game;
import chrislo27.remixer.game.KarateMan;
import chrislo27.remixer.game.Lockstep;
import chrislo27.remixer.game.MunchyMonk;
import chrislo27.remixer.game.Ringside;
import chrislo27.remixer.game.SpaceDance;
import chrislo27.remixer.game.TapTrial;
import chrislo27.remixer.game.TapTroupe;
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
	public TextureAtlas atlas;
	public ObjectMap<String, AtlasRegion> allRegions;

	private void loadResources() {
		put(new CountInGame("countIn"));
		put(new CustomSound("custom"));

		put(new Lockstep("lockstep"));
		put(new MunchyMonk("munchyMonk"));
		put(new DonkDonk("donkDonk"));
		put(new TapTroupe("tapTroupe"));
		put(new TapTrial("tapTrial"));
		put(new SpaceDance("spaceDance", "megaminerzero"));
		put(new CropStomp("cropStomp", "Chocolate2890"));
		put(new BlueBirds("blueBirds", "Chocolate2890"));
		//put(new KarateMan("karateMan", "Chocolate2890"));
		put(new Ringside("ringside", null));

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
		return instance().allRegions.get(key);
	}

}
