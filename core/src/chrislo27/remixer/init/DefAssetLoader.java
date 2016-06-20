package chrislo27.remixer.init;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

import chrislo27.remixer.game.Game;
import chrislo27.remixer.registry.GameList;
import ionium.animation.Animation;
import ionium.registry.handler.IAssetLoader;
import ionium.util.AssetMap;

public class DefAssetLoader implements IAssetLoader {

	@Override
	public void addManagedAssets(AssetManager manager) {
		manager.load(AssetMap.add("mainMenuMusic", "music/Off to Osaka.ogg"), Music.class);
		manager.load(AssetMap.add("rhLogo", "images/logo/rhlogo.png"), Texture.class);
		manager.load(AssetMap.add("architectLogo", "images/logo/architect.png"), Texture.class);

		for (Game g : GameList.instance().map.getAllValues()) {
			manager.load(AssetMap.add(g.getIconId(), g.getIconLocation().path()), Texture.class);
		}
	}

	@Override
	public void addUnmanagedTextures(HashMap<String, Texture> textures) {
	}

	@Override
	public void addUnmanagedAnimations(HashMap<String, Animation> animations) {
	}

}
