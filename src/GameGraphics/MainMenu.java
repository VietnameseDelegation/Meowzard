package GameGraphics;

import BattleField.BattleField;
import UserInput.KeyInput;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The MainMenu class represents the main menu of the game, providing options to start a new game, exit the game, and check status of the waves loaded in.
 */
public class MainMenu extends JFrame {
    BattleField battleField;
    private final KeyInput keyInput = new KeyInput();

    /**
     * Constructs a new MainMenu.
     */
    public MainMenu() {
        setTitle("Main Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon backgroundIcon = loadImg();
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BoxLayout(backgroundLabel, BoxLayout.Y_AXIS));
        battleField = new BattleField(keyInput);

        JButton newGameButton = new JButton("Start New Game");
        JButton exitButton = new JButton("Exit");
        JButton statusButton = new JButton("Status");

        stylize(newGameButton);
        stylize(exitButton);
        stylize(statusButton);

        newGameButton.addActionListener(e -> startNewGame());
        exitButton.addActionListener(e -> exitGame());
        statusButton.addActionListener(e -> checkStatus());

        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        backgroundLabel.add(Box.createRigidArea(new Dimension(0, 200)));
        backgroundLabel.add(newGameButton);
        backgroundLabel.add(Box.createRigidArea(new Dimension(0, 20)));
        backgroundLabel.add(statusButton);
        backgroundLabel.add(Box.createRigidArea(new Dimension(0, 20)));
        backgroundLabel.add(exitButton);
        backgroundLabel.add(Box.createRigidArea(new Dimension(0, 200)));

        setContentPane(backgroundLabel);
        setVisible(true);
    }

    /**
     * Starts a new game by creating a new GamePanel and disposing of the main menu.
     */
    private void startNewGame() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        GamePanel gp = new GamePanel(battleField, keyInput);
        window.add(gp);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gp.start();
        Component comp = SwingUtilities.getRoot(this);
        ((Window) comp).dispose();
    }

    /**
     * Exits the game.
     */
    private void exitGame() {
        System.exit(0);
    }

    /**
     * Loads the background image for the main menu.
     *
     * @return The loaded ImageIcon object.
     */
    public ImageIcon loadImg() {
        return new ImageIcon(Objects.requireNonNull(getClass().getResource("/Background/background.png")));
    }

    /**
     * Checks the status of the game, specifically the waves that have not been loaded.
     */
    public void checkStatus() {
        checkWaves();
        // Make a new window that will show the stages not loaded
    }

    /**
     * Checks the waves and updates the status panel accordingly.
     */
    public void checkWaves() {
        ArrayList<Integer> indexes = battleField.getWaveNotLoaded();
        int allWavesSize = indexes.size() + battleField.getAllWaves().size();
        StatusPanel status = new StatusPanel("status", allWavesSize);
        for (int i = 0; i < allWavesSize; i++) {
            if (indexes.contains(i)) {
                status.add(status.writeBrokenFiles(i));
            } else {
                status.add(status.writeCorrectFiles(i));
            }
        }
    }

    /**
     * Stylizes the given JButton with specific properties.
     *
     * @param jButton The JButton to stylize.
     */
    public void stylize(JButton jButton) {
        Dimension buttonSize = new Dimension(300, 100);
        jButton.setMaximumSize(buttonSize);
        jButton.setBorder(BorderFactory.createEtchedBorder());
        jButton.setFocusable(false);
        jButton.setBackground(Color.LIGHT_GRAY);
    }
}
