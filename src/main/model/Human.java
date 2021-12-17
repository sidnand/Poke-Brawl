package model;

import java.util.List;

// Class that represents the human player
public class Human extends Player {

    // EFFECT: inherent all of Player's methods
    public Human() {
        super();
    }

    // EFFECT: validate the input
    //         set this.type to the type user picked
    @Override
    public void pickType(List<Type> types, String... s) {
        int typeIndex = 0;
        for (int i = 0; i < types.size(); i++) {
            if (s[0].equalsIgnoreCase(types.get(i).getName())) {
                typeIndex = i;
            }
        }

        this.setType(types.get(typeIndex));
    }
}
