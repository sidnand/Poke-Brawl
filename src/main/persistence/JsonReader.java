package persistence;

import model.Data;
import org.json.JSONObject;
import ui.State;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/* This code is based on the code from the JSONSerializationDemo found at the link below
*  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/

// Class that handles reading JSON data
public class JsonReader {
    private final String path;

    // EFFECTS: constructs a new JsonReader with a given file path
    public JsonReader(String p) {
        this.path = p;
    }

    // EFFECTS: read the data from path
    //          if path doesn't exist, throw a IOException
    //          convert data to JSON and return a parsed version of the dataD
    public State getState() throws IOException {
        String data = read();
        JSONObject obj = new JSONObject(data);
        return gameFromState(obj);
    }

    // EFFECTS: reads file at path and returns the data
    public String read() throws IOException {
        StringBuilder data = new StringBuilder();

        try (Stream<String> s = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            s.forEach(data::append);
        }

        return data.toString();
    }

    // EFFECT: read json data and creates new State from it
    public State gameFromState(JSONObject data) {
        State game = new State(new Data());

        JSONObject human = data.getJSONObject("human");
        int humanHealth = human.getInt("health");

        JSONObject ai = data.getJSONObject("ai");
        int aiHealth = ai.getInt("health");

        game.getHuman().setHealth(humanHealth);
        game.getAI().setHealth(aiHealth);

        return game;
    }
}
