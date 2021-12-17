package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.State;
import ui.UI;

import static org.junit.jupiter.api.Assertions.*;

public class TestHuman {

    private Human p;
    private Data d = new Data();
    private final State game = new State(d);

    @BeforeEach
    public void runBefore() {
        p = new Human();
        game.setUI(new UI(game, d));
    }

    @Test
    public void testGetType() {
        p.pickType(game.getTypes(), "Fire");
        assertEquals(p.getType().getName(), "Fire");

        p.pickType(game.getTypes(), "fire");
        assertEquals(p.getType().getName(), "Fire");

        p.pickType(game.getTypes(), "WATER");
        assertEquals(p.getType().getName(), "Water");
    }

}
