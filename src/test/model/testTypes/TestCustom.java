package model.testTypes;

import model.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCustom {
    Custom type;

    @BeforeEach
    public void runBefore() {
        type = new Custom("C", new ArrayList<>(Collections.singletonList(new Fire())),
                new ArrayList<>(Collections.singletonList(new Water())));
    }

    @Test
    public void testName() {
        assertEquals("C", type.getName());
    }

    @Test
    public void testDamage() {
        // Super
        assertEquals(40, type.damageOn(new Fire()));

        // Normal
        assertEquals(20, type.damageOn(new Normal()));

        // Not
        assertEquals(10, type.damageOn(new Water()));
    }
}
