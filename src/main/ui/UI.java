package ui;

import model.AI;
import model.Data;
import model.Human;
import model.Type;
import model.types.Custom;
import model.types.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.Timer;

// Class that holds the different components of the UI
public class UI {
    JFrame frame = new JFrame();
    JPanel gameMenuPanel = new JPanel();
    JPanel splashScreen = new JPanel();
    JPanel typeEditorPanel = new JPanel();
    JPanel addTypePanel = new JPanel();
    JPanel delTypePanel = new JPanel();

    Timer timer = new Timer();

    // EFFECTS: constructs a new UI
    public UI(State state, Data d) {
        initFrame(state, d);
    }

    // EFFECTS: inits all the frames
    public void initFrame(State state, Data data) {
        JLabel allTypes = new JLabel();

        allTypes.setText(typesToString(data.getTypes()));

        createGameMenuPanel(state, data);
        createAddTypePanel(allTypes, data);
        createDelTypePanel(allTypes, data);
        createTypeEditorPanel(allTypes);
        createSplashScreenPanel();

        gameMenuPanel.setLayout(new GridLayout(5, 1));
        typeEditorPanel.setLayout(new GridLayout(5, 1));
        addTypePanel.setLayout(new GridLayout(5, 1));
        delTypePanel.setLayout(new GridLayout(5, 1));

        frame.getContentPane().add(gameMenuPanel);
        frame.getContentPane().add(addTypePanel);
        frame.getContentPane().add(delTypePanel);
        frame.getContentPane().add(typeEditorPanel);
        frame.getContentPane().add(splashScreen);

        frame.setPreferredSize(new Dimension(600, 400));

        frame.setTitle("Poke Brawl");
        frame.pack();
    }

    // EFFECTS: shows the splash screen
    public void showSplashScreen() {
        frame.setContentPane(splashScreen);
        frame.validate();
        frame.setVisible(true);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                showGameMenuPanel();
            }
        }, 3 * 1000);
    }

    // EFFECTS: creates splash screen
    private void createSplashScreenPanel() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("./data/splash.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert image != null;
        JLabel label = new JLabel(new ImageIcon(image));
        splashScreen.add(label);
    }

    // EFFECTS: creates game menu
    private void createGameMenuPanel(State state, Data d) {
        JLabel title = new JLabel("Poke Brawl");

        JButton playBtn = new JButton("Play!");
        playBtn.addActionListener(e -> handlePlayBtnClick(state));

        JButton editTypesBtn = new JButton("Edit Types");
        editTypesBtn.addActionListener(e -> {
            frame.setContentPane(typeEditorPanel);
            frame.validate();
        });

        JButton quit = new JButton("Save & Quit");
        quit.addActionListener(e -> {
            StringBuilder s = d.getLogs();
            System.out.println(s);
            System.exit(0);
        });

        gameMenuPanel.add(title);
        gameMenuPanel.add(playBtn);
        gameMenuPanel.add(editTypesBtn);
        gameMenuPanel.add(quit);

    }

    // EFFECTS: runs when play btn is clicked
    private void handlePlayBtnClick(State state) {
        try {
            frame.setVisible(false);
            state.resetGame();
            state.runGameLoop();
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: Missing file, please re-download this application");
            state.quit();
        }
    }

    // EFFECTS: shows the game panel
    public void showGameMenuPanel() {
        frame.setContentPane(gameMenuPanel);
        frame.validate();
        frame.setVisible(true);
    }

    // EFFECT: resets the text in the add panel
    private void resetAddPanelInputText(JTextField typeNameAdd, JTextField superEffectiveInput,
                                        JTextField notEffectiveInput) {
        typeNameAdd.setText("Name of type");
        superEffectiveInput.setText("Super effective against, ie: Fire Water");
        notEffectiveInput.setText("Not effective against, ie: Normal Grass");
    }

    // EFFECT: creates an add type panel
    private void createAddTypePanel(JLabel allTypes, Data data) {
        JButton addNewType = new JButton("Create Type");
        JTextField typeNameAdd = new JTextField();
        JTextField superEffectiveInput = new JTextField();
        JTextField notEffectiveInput = new JTextField();

        resetAddPanelInputText(typeNameAdd, superEffectiveInput, notEffectiveInput);

        addNewType.addActionListener(e -> {
            Type c = getCustomType(typeNameAdd.getText(), superEffectiveInput.getText(), notEffectiveInput.getText());
            data.addType(c);

            allTypes.setText(typesToString(data.getTypes()));
            resetAddPanelInputText(typeNameAdd, superEffectiveInput, notEffectiveInput);

            allTypes.setText(typesToString(data.getTypes()));

            frame.setContentPane(typeEditorPanel);
            frame.validate();
        });

        addTypePanel.add(typeNameAdd);
        addTypePanel.add(superEffectiveInput);
        addTypePanel.add(notEffectiveInput);
        addTypePanel.add(addNewType);
    }

    // EFFECT: creates a delete type panel
    private void createDelTypePanel(JLabel allTypes, Data data) {
        JButton delTypeBtn = new JButton("Delete");
        JTextField typeNameDel = new JTextField();
        typeNameDel.setText("Type name to Delete");

        delTypeBtn.addActionListener(e -> {
            String typeName = typeNameDel.getText();

            typeNameDel.setText("Type name to Delete");

            data.removeType(typeName);
            allTypes.setText(typesToString(data.getTypes()));

            frame.setContentPane(typeEditorPanel);
            frame.validate();
        });

        delTypePanel.add(typeNameDel);
        delTypePanel.add(delTypeBtn);
    }

    // EFFECT: creates the main type editor panel
    private void createTypeEditorPanel(JLabel allTypes) {
        JButton addBTN = new JButton();
        JButton delBTN = new JButton();
        JButton doneBTN = new JButton();

        typeEditorPanel.add(allTypes);

        // add type
        addBTN.setText("Add Type");
        addBTN.addActionListener(e -> {
            frame.setContentPane(addTypePanel);
            frame.validate();
        });
        typeEditorPanel.add(addBTN);

        // delete custom type
        delBTN.setText("Delete Type");
        delBTN.addActionListener(e -> {
            frame.setContentPane(delTypePanel);
            frame.validate();
        });
        typeEditorPanel.add(delBTN);

        // done
        doneBTN.setText("Done");
        doneBTN.addActionListener(e -> {
            frame.setContentPane(gameMenuPanel);
            frame.validate();
        });
        typeEditorPanel.add(doneBTN);
    }

    // EFFECT: creates a new custom type
    public Type getCustomType(String name, String superE, String notE) {
        // convert types to string
        String[] superEffectiveTypesString = superE.split("\\s+");
        String[] notEffectiveTypesString = notE.split("\\s+");

        List<Type> superEffectiveTypes = new ArrayList<>();
        List<Type> notEffectiveTypes = new ArrayList<>();

        checkAndAddType(superEffectiveTypesString, superEffectiveTypes);
        checkAndAddType(notEffectiveTypesString, notEffectiveTypes);

        return new Custom(name, superEffectiveTypes, notEffectiveTypes);
    }

    // EFFECT: checks and add a type
    public void checkAndAddType(String[] types, List<Type> l) {
        for (String s : types) {
            if (s.equalsIgnoreCase("fire")) {
                l.add(new Fire());
            } else if (s.equalsIgnoreCase("water")) {
                l.add(new Water());
            } else if (s.equalsIgnoreCase("grass")) {
                l.add(new Grass());
            } else if (s.equalsIgnoreCase("normal")) {
                l.add(new Normal());
            }
        }
    }

    // EFFECT: converts types to string
    public String typesToString(List<Type> types) {
        StringBuilder customTypeNames = new StringBuilder("Your Types:\n");
        for (Type c : types) {
            if (c instanceof Custom) {
                customTypeNames.append(c.getName()).append("\n");
            }
        }

        return customTypeNames.toString();
    }

    // EFFECT: allows the user to type
    public String askHuman() {
        System.out.print("\nPlease pick a type or type 'quit' to return to menu: ");

        return getInput();
    }

    // EFFECT: displays a list of all types
    public void showTypes(List<Type> types) {
        System.out.println("\nAll Types:");
        for (Type t : types) {
            System.out.print(t.getName() + " ");
        }
    }

    // EFFECT: shows the health
    public void showHealth(Human human, AI ai) {
        System.out.println("\nYour health: " + human.getHealth());
        System.out.println("Your opponents health: " + ai.getHealth());
    }

    // EFFECT: displays each player's type
    public void showEachPlayersType(Human human, AI ai) {
        System.out.println("\nYou picked: " + human.getType().getName());
        System.out.println("Your opponent picked: " + ai.getType().getName());
    }

    // EFFECT: shows ui dialogue of whose turn it is
    public void showAttackTurn(boolean isHuman, Human human, AI ai) {
        if (isHuman) {
            System.out.println("Your turn to attack. You used a " + human.getType().getName() + " move.\n");
        } else {
            System.out.println("Your opponent's turn to attack. They used a " + ai.getType().getName() + " move.\n");
        }
    }

    // EFFECT: shows ui dialogue of how much damage was inflicted
    public void showAttackDamage(boolean isHuman, int damage) {
        if (isHuman) {
            System.out.println("You decreased your opponent's health by: " + damage + "\n");
        } else {
            System.out.println("Your opponent decreased your health by: " + damage + "\n");
        }
    }

    // EFFECT: asks if the player wants to play again
    public String askPlayAgain() {
        System.out.print("\nWant to play again? [Y / N]: ");

        return getInput();
    }

    // EFFECTS: gets user input and returns it
    public String getInput() {
        Scanner reader = new Scanner(System.in);
        return reader.next();
    }

    // REQUIRES: state = 0 means a draw
    //           state = 1 means human lost
    //           state = any other number means ai lost
    // EFFECTS: shows if human won, lost or game was a draw
    public void showEndGame(int state) {
        if (state == 0) {
            System.out.println("It's a draw!\n");
        } else if (state == 1) {
            System.out.println("You Lose!\n");
        } else {
            System.out.println("You Win!\n");
        }
    }
}
