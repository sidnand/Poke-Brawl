package model.types;

import model.Type;

// Class that represents a fire type, has a name and implements type
public class Fire implements Type {

    @Override
    public int damageOn(Type t) {
        if (t.getName().equalsIgnoreCase("Water") || t.getName().equalsIgnoreCase("Fire")) {
            return 10;
        } else if (t.getName().equalsIgnoreCase("Grass")) {
            return 40;
        } else {
            return 20;
        }
    }

    @Override
    public String getName() {
        return "Fire";
    }
}
