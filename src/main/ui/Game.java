package ui;

import model.Data;
import persistence.JsonReader;

import java.io.IOException;

// main game class
public class Game {

    // Game constructor which takes a file path to load game from
    public Game(String p) throws IOException {
        JsonReader jsonReader = new JsonReader(p);
        State s = jsonReader.getState();
        Data data = new Data();
        State state = new State(data);
        state.getHuman().setHealth(s.getHuman().getHealth());
        state.getAI().setHealth(s.getAI().getHealth());
        state.setUI(new UI(state, new Data()));
        state.setManager(new Manager());
        state.start();
    }
}
