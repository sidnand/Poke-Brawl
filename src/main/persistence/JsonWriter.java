package persistence;

import org.json.JSONObject;
import ui.State;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/* This code is based on the code from the JSONSerializationDemo found at the link below
 *  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

// Class that writes to JSON file
public class JsonWriter {
    private static final int TAB = 4;
    private String path;
    private PrintWriter writer;

    // EFFECTS: constructs a new JsonWriter and takes the file path to write to
    public JsonWriter(String p) {
        this.path = p;
    }

    // MODIFIES: this
    // EFFECTS: opens a file from path
    //         if path doesn't exist, it throws a FileNotFoundException
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(path);
    }

    // EFFECT: saves the given state
    public void saveState(State s) {
        JSONObject json = s.toJson();
        save(json.toString(TAB));
    }

    // EFFECT: writes to json
    private void save(String json) {
        writer.print(json);
    }

    // MODIFIES: this
    // EFFECTS: closes file writing path
    public void close() {
        writer.close();
    }

    // EFFECTS: sets path
    public void setPath(String p) {
        this.path = p;
    }

    // EFFECTS: return path
    public String getPath() {
        return path;
    }
}
