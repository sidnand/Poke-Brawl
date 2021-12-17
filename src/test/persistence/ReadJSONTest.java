package persistence;

import org.junit.jupiter.api.Test;
import ui.State;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReadJSONTest {
    JsonReader reader;

    // test if file doesn't exist
    @Test
    public void testNoFile() {
        reader = new JsonReader("./data/tests/read/noFile.json");

        try {
            reader.read();
            fail("Exception not thrown");
        } catch (IOException ignored) { }


    }

    // test reading file with empty game state
    @Test
    public void testEmptyGameState() {
        reader = new JsonReader("./data/tests/read/testEmptyState.json");
        State state;

        try {
            state = reader.getState();

            assertEquals(100, state.getHuman().getHealth());
            assertEquals(100, state.getAI().getHealth());
        } catch (IOException e) {
            fail("Exception thrown");
        }
    }


    // test reading file with a not empty game state
    @Test
    public void testNotEmptyGameState() {
        reader = new JsonReader("./data/tests/read/testNotEmptyState.json");
        State state;

        try {
            state = reader.getState();

            assertEquals(50, state.getHuman().getHealth());
            assertEquals(10, state.getAI().getHealth());
        } catch (IOException e) {
            fail("Exception thrown");
        }
    }
}
