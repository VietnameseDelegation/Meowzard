package GameGraphics;

import BattleField.BattleField;
import UserInput.KeyInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class MainMenu extends JFrame {
    BattleField battleField;
    private KeyInput keyInput = new KeyInput();
    public MainMenu() {
        // Set the title of the window
        setTitle("Main Menu");
        // Set the size of the window
        setSize(800, 600);
        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Center the window
        setLocationRelativeTo(null);

        // Load the background image
        ImageIcon backgroundIcon = loadImg();

        // Create a label with the background image
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BoxLayout(backgroundLabel, BoxLayout.Y_AXIS));
        battleField = new BattleField(keyInput);
        // Create buttons for the menu
        JButton newGameButton = new JButton("Start New Game");
        JButton exitButton = new JButton("Exit");
        JButton statusButton = new JButton("Status");

        // Set the preferred size of the buttons to be the same
        Dimension buttonSize = new Dimension(300, 100); // Half the width and height of the window
        stylize(newGameButton);
        stylize(exitButton);
        stylize(statusButton);
        // Add action listeners to the buttons
        newGameButton.addActionListener(e -> startNewGame());
        exitButton.addActionListener(e -> exitGame());
        statusButton.addActionListener(e->checkStatus());

        // Center the buttons and add some spacing
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Add buttons to the label with spacing
        backgroundLabel.add(Box.createRigidArea(new Dimension(0, 200))); // Adjust the spacing as needed
        backgroundLabel.add(newGameButton);
        backgroundLabel.add(Box.createRigidArea(new Dimension(0, 20)));
        backgroundLabel.add(exitButton);
        backgroundLabel.add(Box.createRigidArea(new Dimension(0, 20))); // Adjust the spacing as needed
        backgroundLabel.add(statusButton);
        backgroundLabel.add(Box.createRigidArea(new Dimension(0, 200)));
        // Add the background label to the frame
        setContentPane(backgroundLabel);

        // Make the window visible
        setVisible(true);
    }

    private void startNewGame() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        GamePanel gp = new GamePanel(battleField,keyInput);
        window.add(gp);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gp.start();
        Component comp = SwingUtilities.getRoot(this);
        ((Window) comp).dispose();
    }

    private void exitGame() {
        System.exit(0);
    }

    public ImageIcon loadImg() {
        return new ImageIcon(Objects.requireNonNull(getClass().getResource("/Background/background.png")));
    }
    public void checkStatus(){
        checkWaves();
        //make a new window that will show the stages not loaded
    }
    public void checkWaves(){
        ArrayList<Integer> indexes = battleField.getWaveNotLoaded();
        int allWavesSize = indexes.size()+battleField.getAllWaves().size();
       StatusPanel status = new StatusPanel("status",allWavesSize);
        for(int i = 0; i<allWavesSize; i++){
            if (indexes.contains(i)){
                status.add(status.writeBrokenFiles(i));
            }else {
                status.add(status.writeCorrextFiles(i));
            }
        }
    }
    public void stylize(JButton jButton){
        Dimension buttonSize = new Dimension(300, 100);
        jButton.setMaximumSize(buttonSize);
        jButton.setBorder(BorderFactory.createEtchedBorder());
        jButton.setFocusable(false);
        jButton.setBackground(Color.LIGHT_GRAY);
    }
}