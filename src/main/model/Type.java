package model;

// Interface for all other types in the game
public interface Type {
    // REQUIRES: t to have a string field called name
    // EFFECTS: returns either 10, 20 or 40 depending on how effective t is on this type
    int damageOn(Type t);

    // EFFECTS: returns the name
    String getName();
}
