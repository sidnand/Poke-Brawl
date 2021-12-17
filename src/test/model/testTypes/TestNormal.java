package model.testTypes;

import model.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestNormal {
    Normal type;

    @BeforeEach
    public void runBefore() {
        type = new Normal();
    }

    @Test
    public void testDamage() {
        // Grass
        assertEquals(20, type.damageOn(new Grass()));
    }
}