package model;

import model.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import ui.Manager;
import ui.State;
import ui.UI;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestManager {
    private Manager manager;
    private State state;
    private UI ui;

    @BeforeEach
    public void runBefore() {
        manager = new Manager();
        Data d = new Data();
        state = new State(d);
        ui = new UI(state, d);
        state.setUI(ui);
        state.setManager(manager);
    }

    @Test
    public void flipFirstPlayer() {
        // test when human is first
        assertTrue(manager.getIsHumanFirst());

        // test when ai is first
        manager.flipFirstPlayer();
        assertFalse(manager.getIsHumanFirst());

        // test when it was ai and then negated to human
        manager.flipFirstPlayer();
        assertTrue(manager.getIsHumanFirst());
    }

    @Test
    public void testBattle() {
        Human human = state.getHuman();
        AI ai = state.getAI();

        // human first
        human.setType(new Water());
        ai.setType(new Fire());

        manager.battle(human, ai, ui);
        assertEquals(60, ai.getHealth());
        assertEquals(100, human.getHealth());

        manager.flipFirstPlayer();

        // ai next
        human.setType(new Fire());
        ai.setType(new Water());
        manager.battle(human, ai, ui);
        assertEquals(60, ai.getHealth());
        assertEquals(60, human.getHealth());
    }

    @Test
    public void testIsInputValid() {
        assertTrue(manager.isValidType("Fire", state.getTypes()));
        assertTrue(manager.isValidType("water", state.getTypes()));
        assertTrue(manager.isValidType("GRASS", state.getTypes()));
        assertTrue(manager.isValidType("NoRMal", state.getTypes()));
        assertFalse(manager.isValidType("122", state.getTypes()));
        assertFalse(manager.isValidType("markov", state.getTypes()));
    }

    @Test
    public void testGameEndDraw() {
        state.getHuman().decreaseHealth(100);
        state.getAI().decreaseHealth(100);

        assertTrue(manager.checkGameOver(state.getHuman(), state.getAI(), ui));
    }

    @Test
    public void testHumanWon() {
        state.getAI().decreaseHealth(100);

        assertTrue(manager.checkGameOver(state.getHuman(), state.getAI(), ui));
    }

    @Test
    public void testAIWon() {
        state.getHuman().decreaseHealth(100);

        assertTrue(manager.checkGameOver(state.getHuman(), state.getAI(), ui));
    }

    @Test
    public void testNoWin() {
        state.getHuman().decreaseHealth(50);

        assertFalse(manager.checkGameOver(state.getHuman(), state.getAI(), ui));
    }

    @Test
    public void testReset() {
        manager.reset(state.getHuman(), state.getAI());
        assertEquals(100, state.getAI().getHealth());
        assertEquals(100, state.getHuman().getHealth());
    }

    @Test
    public void testPlayAgain() {
        assertFalse(manager.playAgain("N"));
        assertTrue(manager.playAgain("Y"));
    }

    @Test
    public void testSaveGame() {
        State s = new State(new Data());
        JsonReader reader = new JsonReader("./data/tests/write/testForManager.json");
        manager.setPath("./data/tests/write/testForManager.json");

        try {
            manager.saveGame(s);
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException thrown");
        }

        try {
            State readS = reader.getState();
            assertEquals(100, readS.getHuman().getHealth());
            assertEquals(100, readS.getAI().getHealth());
        } catch (IOException e) {
            fail("IOException thrown");
        }
    }

    @Test
    public void testIsQuit() {
        assertTrue(manager.isQuit("quit"));
        assertFalse(manager.isQuit("q"));
    }

}
