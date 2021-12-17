package model;

import java.util.List;
import java.util.Random;

// Class represents the AI player
public class AI extends Player {

    // EFFECT: inherent all of Player's methods
    public AI() {
        super();
    }

    // EFFECT: randomly generate a number between 0 and types.size()
    //         assign this.type to the element with the index of the random number in types list
    @Override
    public void pickType(List<Type> types, String... s) {
        Random rand = new Random();
        int index = rand.nextInt(types.size() - 1);

        this.setType(types.get(index));
    }
}
