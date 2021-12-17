package ui;

import java.io.IOException;

// Main class
public class Main {
    public static void main(String[] args) {
        try {
            new Game("./data/game.json");
        } catch (IOException e) {
            System.out.println("ERROR: Missing file, please re-download this application");
        }
    }
}
