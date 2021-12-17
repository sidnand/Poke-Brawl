package model;

import model.types.Custom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestData {
    Data d;

    @BeforeEach
    public void runBefore() {
        d = new Data();
    }

    @Test
    public void addType() {
        d.addType(new Custom("a", new ArrayList<>(), new ArrayList<>()));
        assertEquals(5, d.getTypes().size());
    }

    @Test
    public void removeType() {
        d.addType(new Custom("a", new ArrayList<>(), new ArrayList<>()));
        d.removeType("a");
        assertEquals(4, d.getTypes().size());
    }

    @Test
    public void getTypes() {
        assertNotNull(d.getTypes());
    }

    @Test
    public void getLogs() {
        d.addType(new Custom("b", new ArrayList<>(), new ArrayList<>()));
        StringBuilder s = d.getLogs();
        assertEquals("Added type: a\nAdded type: b\n", s.toString());
    }
}
