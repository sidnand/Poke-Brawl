package model.testTypes;

import model.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestGrass {
    Grass type;

    @BeforeEach
    public void runBefore() {
        type = new Grass();
    }

    @Test
    public void testDamage() {
        // Grass
        assertEquals(10, type.damageOn(new Grass()));

        // Water
        assertEquals(40, type.damageOn(new Water()));

        // Fire
        assertEquals(10, type.damageOn(new Fire()));

        // Normal
        assertEquals(20, type.damageOn(new Normal()));
    }
}