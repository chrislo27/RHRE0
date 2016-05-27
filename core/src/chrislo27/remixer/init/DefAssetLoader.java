package chrislo27.remixer.init;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import chrislo27.remixer.registry.CueList;
import chrislo27.remixer.registry.CueList.Cue;
import ionium.animation.Animation;
import ionium.registry.handler.IAssetLoader;
import ionium.util.AssetMap;

public class DefAssetLoader implements IAssetLoader {

	@Override
	public void addManagedAssets(AssetManager manager) {
		manager.load(AssetMap.add("gameIcons", "images/games/icons.pack"), TextureAtlas.class);

		for (Cue c : CueList.instance().getMap().getAllValues()) {
			manager.load(AssetMap.add("cue_" + c.name, "sounds/cues/" + c.path), Sound.class);
		}
	}

	@Override
	public void addUnmanagedTextures(HashMap<String, Texture> textures) {
	}

	@Override
	public void addUnmanagedAnimations(HashMap<String, Animation> animations) {
	}

}
