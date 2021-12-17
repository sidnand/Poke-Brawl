package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.State;

import static org.junit.jupiter.api.Assertions.*;

public class TestState {
    private State s;

    @BeforeEach
    public void runBefore() {
        s = new State(new Data());
    }

    // test toJSON
    @Test
    public void testToJson() {
        assertEquals("{\"ai\":{\"health\":100},\"human\":{\"health\":100}}", s.toJson().toString());
    }
}
