package chrislo27.remixer.game;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonValue.JsonIterator;

import chrislo27.remixer.action.ActionSFX;
import chrislo27.remixer.registry.CueList;
import ionium.templates.Main;
import ionium.util.i18n.Localization;

public class Game {

	private static final JsonReader jsonReader = new JsonReader();

	public final String id;
	public final Array<Cue> cues = new Array<>();
	public final Array<Pattern> patterns = new Array<>();
	protected final FileHandle dataFileLocation;

	public Game(FileHandle dataFile) {
		this(dataFile.readString("UTF-8"), dataFile);
	}

	public Game(String dataFile, FileHandle loc) {
		dataFileLocation = loc;

		JsonValue jsonRoot = jsonReader.parse(dataFile);

		if (jsonRoot == null) throw new IllegalArgumentException("JSON value cannot be null!");

		id = jsonRoot.getString("gameId", null);

		if (id == null) throw new RuntimeException("Game ID was null!");

		JsonValue jsonCueList = jsonRoot.get("cues");
		if (jsonCueList != null && jsonCueList.isArray()) {
			JsonIterator it = jsonCueList.iterator();

			while (it.hasNext()) {
				JsonValue next = it.next();

				Cue c = new Cue(next, id, dataFileLocation);

				cues.add(c);
				CueList.instance().map.put(c.id, c);
			}
		}

		JsonValue jsonPatternList = jsonRoot.get("patterns");
		if (jsonPatternList != null && jsonPatternList.isArray()) {
			JsonIterator it = jsonPatternList.iterator();

			while (it.hasNext()) {
				JsonValue nextPattern = it.next();

				final String id = nextPattern.getString("id", null);
				if (id == null) {
					Main.logger.warn("Error while parsing " + loc.pathWithoutExtension()
							+ " data file: ID is null!");
					continue;
				}

				Pattern p = new Pattern(id);
				Localization.instance().addCustom(id,
						nextPattern.getString("localization", "<missing localization>"));

				JsonValue cues = nextPattern.get("cues");
				if (cues != null && cues.isArray()) {
					JsonIterator cuesIt = cues.iterator();

					while (cuesIt.hasNext()) {
						JsonValue cue = cuesIt.next();

						final String cueId = cue.getString("id", null);
						final float beat = cue.getFloat("beat", Float.NEGATIVE_INFINITY);
						final int track = cue.getInt("track", -1);
						final float volume = MathUtils.clamp(cue.getFloat("volume", 1), 0f, 1f);
						final int semitone = cue.getInt("semitone", 0);

						if (cueId == null || beat == Float.NEGATIVE_INFINITY || track < 0) {
							Main.logger.warn("Error while parsing " + id
									+ " data file: cue is missing vital data");
							continue;
						}

						ActionSFX asfx = new ActionSFX(CueList.getCue(cueId), beat, track);
						asfx.volume = volume;
						asfx.semitone = semitone;

						p.sfx.add(asfx);
					}

					patterns.add(p);
				} else {
					Main.logger
							.warn("Error while parsing " + id + " data file: missing cues array");
					continue;
				}
			}
		}
	}

	public FileHandle getIconLocation() {
		return dataFileLocation.sibling("icon.png");
	}

	public String getIconId() {
		return "icon_" + id;
	}

}
