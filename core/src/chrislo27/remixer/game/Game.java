package chrislo27.remixer.game;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonValue.JsonIterator;

import chrislo27.remixer.registry.CueList;

public class Game {

	private static final JsonReader jsonReader = new JsonReader();

	public final String id;
	public final Array<Cue> cues = new Array<>();

	public Game(FileHandle dataFile) {
		this(dataFile.readString("UTF-8"));
	}

	public Game(String dataFile) {
		JsonValue jsonRoot = jsonReader.parse(dataFile);

		if (jsonRoot == null) throw new IllegalArgumentException("JSON value cannot be null!");

		id = jsonRoot.getString("id", null);

		JsonValue jsonCueList = jsonRoot.get("cues");
		if (jsonCueList != null && jsonCueList.isArray()) {
			JsonIterator it = jsonCueList.iterator();

			while (it.hasNext()) {
				JsonValue next = it.next();

				Cue c = new Cue(next);

				cues.add(c);
				CueList.instance().map.put(c.id, c);
			}
		}

		JsonValue jsonPatternList = jsonRoot.get("patterns");
		if (jsonPatternList != null) {

		}
	}

}
