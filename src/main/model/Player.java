package model;

import java.util.List;

// Super class for all players in game
public abstract class Player {
    private int health = 100;
    private Type type;

    // EFFECTS: returns the health
    public int getHealth() {
        return this.health;
    }

    // MODIFIES: this
    // EFFECTS: sets type to t
    public void setType(Type t) {
        this.type = t;
    }

    // EFFECTS: returns type
    public Type getType() {
        return this.type;
    }

    // REQUIRES: h > 0
    // MODIFIES: this
    // EFFECTS: decreases health by h
    //          if health < 0, then set health = 0
    public void decreaseHealth(int h) {
        setHealth(this.health - h);

        if (this.health < 0) {
            this.health = 0;
        }
    }

    // EFFECTS: sets health to h
    public void setHealth(int h) {
        this.health = h;
    }

    // MODIFIES: this
    // EFFECTS: resets health to 100
    public void resetHealth() {
        this.health = 100;
    }

    // MODIFIES: this
    // EFFECT: allows each player to pick a type
    public abstract void pickType(List<Type> types, String... s);
}
