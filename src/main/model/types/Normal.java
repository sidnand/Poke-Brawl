package model.types;

import model.Type;

// Class that represents a normal type, has a name and implements type
public class Normal implements Type {

    @Override
    public int damageOn(Type t) {
        return 20;
    }

    @Override
    public String getName() {
        return "Normal";
    }
}
