package ui;

import model.AI;
import model.Data;
import model.Human;
import model.Type;
import model.types.*;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Class that manages the elements of the UI and the manager loop
public class State {

    private Manager manager;
    private UI ui;
    private Data data;

    private final Human human = new Human();
    private final AI ai = new AI();

    private boolean gameOver = false;

    public State(Data d) {
        this.data = d;
    }

    // EFFECT: sets the ui
    public void setUI(UI ui) {
        this.ui = ui;
    }

    // EFFECT: sets the manager
    public void setManager(Manager m) {
        this.manager = m;
    }

    // EFFECTS : returns the human
    public Human getHuman() {
        return human;
    }

    // EFFECTS : returns the ai
    public AI getAI() {
        return ai;
    }

    // EFFECT: returns all the types
    public List<Type> getTypes() {
        return data.getTypes();
    }

    // EFFECTS: resets the game loop
    public void resetGame() {
        gameOver = false;
    }

    // EFFECTS: starts the game
    public void start() {
        ui.showSplashScreen();
    }

    // EFFECTS: runs the whole game loop
    public void runGameLoop() throws FileNotFoundException {
        while (!gameOver) {
            ui.showHealth(human, ai);
            ui.showTypes(data.getTypes());

            String humanInput = getHumanInput();

            if (manager.isQuit(humanInput)) {
                gameOver = true;
                ui.showGameMenuPanel();
            } else {
                human.pickType(data.getTypes(), humanInput);

                ai.pickType(data.getTypes());
                ui.showEachPlayersType(human, ai);

                manager.battle(human, ai, ui);
                manager.flipFirstPlayer();

                if (manager.checkGameOver(human, ai, ui)) {
                    handleGameOver();
                }
            }
        }
    }

    // EFFECTS: handles the game menu
    private void handleGameOver() throws FileNotFoundException {
        String i = ui.askPlayAgain();

        if (manager.playAgain(i)) {
            manager.reset(human, ai);
            runGameLoop();
        } else {
            manager.reset(human, ai);
            manager.saveGame(this);
            gameOver = true;
            System.out.println("You've quit the game");
            ui.showGameMenuPanel();
        }
    }

    // EFFECT: keeps asking human for their type, until input is a valid type
    public String getHumanInput() throws FileNotFoundException {
        while (true) {
            String humansChoice = ui.askHuman();

            if (manager.isValidType(humansChoice, data.getTypes())) {
                return humansChoice;
            } else if (manager.isQuit(humansChoice)) {
                System.out.println("You've quit the game");
                manager.saveGame(this);
                return "quit";
            }
        }
    }

    // EFFECTS: exists out of the game
    public void quit() {
        System.exit(0);
    }

    // EFFECTS: converts this state object into JSON
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONObject humanJson = new JSONObject();
        JSONObject aiJson = new JSONObject();

        humanJson.put("health", human.getHealth());
        aiJson.put("health", ai.getHealth());

        json.put("human", humanJson);
        json.put("ai", aiJson);

        return json;
    }

}
