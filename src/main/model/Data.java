package model;

import model.types.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Data {
    private final List<Type> types = new ArrayList<>(Arrays.asList(
            new Water(), new Fire(), new Grass(), new Normal()
    ));

    public void addType(Type c) {
        types.add(c);
        EventLog.getInstance().logEvent(new Event("Added type: " + c.getName()));
    }

    public List<Type> getTypes() {
        return types;
    }

    public void removeType(String typeName) {
        types.removeIf(t -> t.getName().equalsIgnoreCase(typeName));
        EventLog.getInstance().logEvent(new Event("Removed type: " + typeName));
    }

    public StringBuilder getLogs() {
        StringBuilder s = new StringBuilder();
        for (Event e : EventLog.getInstance()) {
            s.append(e.getDescription()).append("\n");
        }

        return s;
    }
}
