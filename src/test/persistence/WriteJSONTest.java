package persistence;

import model.Data;
import org.junit.jupiter.api.Test;
import ui.State;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriteJSONTest {

    JsonWriter writer;
    JsonReader reader;

    // test if given path is incorrect
    @Test
    public void testNoFile() {
        writer = new JsonWriter("./data/tests/write/\0/noFile.json");

        try {
            writer.open();
            fail("Exception not thrown");
        } catch (FileNotFoundException ignore) {}
    }

    // test writing a game state
    @Test
    public void testWritingGameState() {
        String p = "./data/tests/write/test.json";
        State currentS = new State(new Data());
        State fileS;
        writer = new JsonWriter(p);
        reader = new JsonReader(p);

        currentS.getHuman().setHealth(90);
        currentS.getAI().setHealth(95);

        try {
            writer.open();
            writer.saveState(currentS);
            writer.close();
        } catch (FileNotFoundException e) {
            fail("Exception should not be thrown");
        }

        try {
            fileS = reader.getState();
            assertEquals(90, fileS.getHuman().getHealth());
            assertEquals(95, fileS.getAI().getHealth());
        } catch (IOException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testSetPath() {
        String p = "./data/tests/test.json";
        writer = new JsonWriter(p);
        assertEquals(writer.getPath(), p);
        p = "./data";
        writer.setPath(p);
        assertEquals(writer.getPath(), p);
    }

}
