package chrislo27.remixer.track;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.MissingResourceException;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

import chrislo27.remixer.registry.CueList;
import ionium.templates.Main;

/**
 * Exports and imports Remixes from JSON files.
 * 
 *
 */
public class RemixPorter {

	private static final JsonReader reader = new JsonReader();
	private static final Json prettyPrinter = new Json();

	public static final String exportRemix(Remix remix, boolean prettyPrint) throws IOException {
		StringWriter stringWriter = new StringWriter();
		JsonWriter writer = new JsonWriter(stringWriter);

		writer.setOutputType(OutputType.json);

		writer.object();

		writer.name("musicStartTime").value(remix.musicStartTime);
		writer.name("bpm").value(remix.bpm);

		writer.array("tracks");

		for (int i = 0; i < remix.tracks.size; i++) {
			Array<SoundEffect> track = remix.tracks.get(i);

			writer.object();
			writer.name("number").value(i);

			writer.array("sfxs");

			for (SoundEffect sfx : track) {
				writer.object();

				writer.name("beat").value(sfx.beat);
				writer.name("cue").value(CueList.getKey(sfx.cue));

				writer.pop();
			}

			writer.pop().pop();
		}

		writer.pop();

		writer.close();
		String output = stringWriter.toString();

		if (prettyPrint) {
			return prettyPrinter.prettyPrint(output);
		} else {
			return output;
		}
	}

	public static Remix importRemix(String json) {
		JsonValue jsonValue = reader.parse(json);

		int bpm;
		float musicStartTime;
		Array<Array<SoundEffect>> parsedTracks = new Array<>();

		try {
			bpm = jsonValue.getInt("bpm");
			musicStartTime = jsonValue.getFloat("musicStartTime");

			if (!jsonValue.hasChild("tracks")) {
				throw new Exception("Missing tracks array");
			}

			JsonValue tracks = jsonValue.get("tracks");
			// iterator for the tracks array
			Iterator<JsonValue> tracksIterator = tracks.iterator();

			while (tracksIterator.hasNext()) {
				JsonValue eachTrack = tracksIterator.next();

				Array<SoundEffect> track = new Array<>();
				Iterator<JsonValue> trackIterator = eachTrack.iterator();

				while (trackIterator.hasNext()) {
					JsonValue trackValue = trackIterator.next();

					if (trackValue.isArray() && trackValue.name() != null
							&& trackValue.name.equals("sfxs")) {
						// iterate the array object
						for (int i = 0; i < trackValue.size; i++) {
							JsonValue cue = trackValue.get(i);
							String cueName = cue.getString("cue");

							if (CueList.getCue(cueName) == null) continue;

							track.add(new SoundEffect(cue.getFloat("beat"), cueName));
						}
					}
				}

				parsedTracks.insert(eachTrack.getInt("number"), track);
			}

			if (parsedTracks.size == 0) {
				throw new Exception("Incomplete tracks");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		Remix r = new Remix(bpm);

		r.musicStartTime = musicStartTime;
		r.tracks = parsedTracks;
		for (Array<SoundEffect> array : r.tracks) {
			array.sort();
		}

		return r;
	}

}
