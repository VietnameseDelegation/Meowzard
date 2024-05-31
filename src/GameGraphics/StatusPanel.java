package GameGraphics;

import javax.swing.*;
import java.awt.*;

/**
 * The StatusPanel class represents a window that displays the status of game files.
 */
public class StatusPanel extends JFrame {

    /**
     * Constructs a new StatusPanel with the specified title and size.
     *
     * @param title The title of the status panel window.
     * @param size The number of rows in the grid layout of the status panel.
     */
    public StatusPanel(String title, int size) throws HeadlessException {
        super(title);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(size, 1));
        setVisible(true);
    }

    /**
     * Creates and returns a JLabel indicating that a wave file is broken.
     *
     * @param i The index of the broken wave file.
     * @return A JLabel indicating that the wave file is broken.
     */
    public JLabel writeBrokenFiles(int i) {
        JLabel label = new JLabel("wave" + i + ".csv is broken", JLabel.LEFT);
        label.setForeground(Color.RED);
        return label;
    }

    /**
     * Creates and returns a JLabel indicating that a wave file is working and loading.
     *
     * @param i The index of the working wave file.
     * @return A JLabel indicating that the wave file is working and loading.
     */
    public JLabel writeCorrectFiles(int i) {
        JLabel label = new JLabel("wave" + i + ".csv working and loading", JLabel.LEFT);
        label.setForeground(Color.GREEN);
        return label;
    }
}
