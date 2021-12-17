package model.types;

import model.Type;

// Class that represents a water type, has a name and implements type
public class Water implements Type {

    @Override
    public int damageOn(Type t) {
        if (t.getName().equalsIgnoreCase("Grass") || t.getName().equalsIgnoreCase("Water")) {
            return 10;
        } else if (t.getName().equalsIgnoreCase("Fire")) {
            return 40;
        } else {
            return 20;
        }
    }

    @Override
    public String getName() {
        return "Water";
    }
}
