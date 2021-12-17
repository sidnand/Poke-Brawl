package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.State;
import ui.UI;

import static org.junit.jupiter.api.Assertions.*;

public class TestAI {

    private AI ai;
    private Data d = new Data();
    private final State game = new State(d);

    @BeforeEach
    public void runBefore() {
        ai = new AI();
        game.setUI(new UI(game, d));
    }

    @Test
    public void testGetType() {
        ai.pickType(game.getTypes());
        assertNotNull(ai.getType());
    }

}
