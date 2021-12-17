package model.types;

import model.Type;

// Class that represents a grass type, has a name and implements type
public class Grass implements Type {

    @Override
    public int damageOn(Type t) {
        if (t.getName().equalsIgnoreCase("Fire") || t.getName().equalsIgnoreCase("Grass")) {
            return 10;
        } else if (t.getName().equalsIgnoreCase("Water")) {
            return 40;
        } else {
            return 20;
        }
    }

    @Override
    public String getName() {
        return "Grass";
    }
}
