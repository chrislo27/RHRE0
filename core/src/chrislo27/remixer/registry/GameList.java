package chrislo27.remixer.registry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import chrislo27.remixer.game.Game;
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

	public final BiObjectMap<String, Game> map = new BiObjectMap<>();

	private void loadResources() {
		FileHandle gameListFile = Gdx.files.internal("gamedata/gameList.txt");

		if (!gameListFile.exists()) {
			throw new FileNotFoundException("Game data file not found!");
		} else if (gameListFile.isDirectory()) {
			throw new FileNotFoundException("Game data file is a directory!");
		}

		String[] lines = gameListFile.readString("UTF-8").split("[\\r\\n]+");

		int actuallyLoaded = 0;
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].trim();
			if (line.startsWith("#")) continue;
			if (line.equals("")) continue;

			FileHandle dataFile = Gdx.files.internal("gamedata/" + line + "/data.txt");

			if (!dataFile.exists()) {
				Main.logger.warn("Game not found! " + line);
				continue;
			}

			Game g = new Game(dataFile);

			map.put(line, g);
			actuallyLoaded++;
		}
		
		Main.logger.debug("Loaded " + actuallyLoaded + " / " + lines.length + " games");
	}

	public static Game getGame(String id) {
		return instance().map.getValue(id);
	}

	public static String getId(Game g) {
		return instance().map.getKey(g);
	}

	private static class FileNotFoundException extends RuntimeException {

		public FileNotFoundException(String msg) {
			super(msg);
		}
	}

}
