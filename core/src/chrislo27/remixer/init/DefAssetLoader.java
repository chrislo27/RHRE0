package chrislo27.remixer.init;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ionium.animation.Animation;
import ionium.registry.handler.IAssetLoader;
import ionium.util.AssetMap;

public class DefAssetLoader implements IAssetLoader {

	@Override
	public void addManagedAssets(AssetManager manager) {
		manager.load(AssetMap.add("gameIcons", "images/games/icons.pack"), TextureAtlas.class);
	}

	@Override
	public void addUnmanagedTextures(HashMap<String, Texture> textures) {
	}

	@Override
	public void addUnmanagedAnimations(HashMap<String, Animation> animations) {
	}

}
