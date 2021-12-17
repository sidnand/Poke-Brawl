package ui;

import model.AI;
import model.Human;
import model.Type;
import persistence.JsonWriter;
import ui.State;
import ui.UI;

import java.io.FileNotFoundException;
import java.util.List;

// Class that has methods that runs the actual game
public class Manager {

    private final JsonWriter jsonWriter = new JsonWriter("./data/game.json");

    private boolean isHumanFirst = true;

    // EFFECT: returns isHuman
    public Boolean getIsHumanFirst() {
        return isHumanFirst;
    }

    // EFFECT: returns true if string is Y
    public boolean playAgain(String playAgain) {
        return playAgain.equalsIgnoreCase("Y");
    }

    // EFFECT: sets path for json writer
    public void setPath(String p) {
        jsonWriter.setPath(p);
    }

    // EFFECT: simulates a battle by changing the opponents health and showing dialogue
    public void battle(Human human, AI ai, UI ui) {
        Type htype = human.getType();
        Type aitype = ai.getType();
        int damage;

        if (isHumanFirst) {
            ui.showAttackTurn(true, human, ai);
            damage = htype.damageOn(aitype);
            ai.decreaseHealth(damage);

            ui.showAttackDamage(true, damage);
        } else {
            ui.showAttackTurn(false, human, ai);
            damage = aitype.damageOn(htype);
            human.decreaseHealth(damage);

            ui.showAttackDamage(false, damage);
        }
    }

    // EFFECTS: returns true if a player's health is 0 or false if not
    public boolean checkGameOver(Human human, AI ai, UI ui) {
        if ((human.getHealth() == 0) && (ai.getHealth() == 0)) {
            ui.showEndGame(0);
            return true;
        } else if (human.getHealth() == 0) {
            ui.showEndGame(1);
            return true;
        } else if (ai.getHealth() == 0)  {
            ui.showEndGame(2);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECT: negates who the first player is
    public void flipFirstPlayer() {
        isHumanFirst = !isHumanFirst;
    }

    // EFFECT: resets player's health
    public void reset(Human human, AI ai) {
        ai.resetHealth();
        human.resetHealth();
    }

    // EFFECTS: returns true if string is a type name, else false
    public boolean isValidType(String i, List<Type> types) {
        for (Type t : types) {
            if (t.getName().equalsIgnoreCase(i)) {
                return true;
            }
        }

        return false;
    }

    // EFFECTS: checks if human typed quit
    public boolean isQuit(String s) {
        return (s.equalsIgnoreCase("quit"));
    }

    // EFFECTS: opens writer, saves the given state and closes writer
    //         if writer throws FileNotFoundException, throw again
    public void saveGame(State state) throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter.saveState(state);
        jsonWriter.close();
    }
}