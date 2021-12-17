package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayer {

    private Player p;

    @BeforeEach
    public void runBefore() {
        p = new Player() {
            @Override
            public void pickType(List<Type> types, String... s) {}
        };
    }

    // TEST DECREASE HEALTH

    @Test
    public void decreaseHealthBy1() {
        p.decreaseHealth(1);
        assertEquals(99, p.getHealth());
    }

    @Test
    public void decreaseHealthALittle() {
        p.decreaseHealth(10);
        assertEquals(90, p.getHealth());
    }

    @Test
    public void decreaseHealthTo0() {
        p.decreaseHealth(100);
        assertEquals(0, p.getHealth());
    }

    @Test
    public void decreaseHealthBelow0() {
        p.decreaseHealth(101);
        assertEquals(0, p.getHealth());
    }

    @Test
    public void testResetHealth() {
        p.decreaseHealth(20);
        p.resetHealth();
        assertEquals(100, p.getHealth());
    }

}
