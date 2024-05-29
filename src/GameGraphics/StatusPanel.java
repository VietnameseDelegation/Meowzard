package GameGraphics;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JFrame {
    public StatusPanel(String title,int size) throws HeadlessException {
        super(title);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(size,1));
        setVisible(true);

    }
    public JLabel writeBrokenFiles(int i) {
        JLabel label = new JLabel("wave" + i + ".csv is broken", JLabel.LEFT);
label.setForeground(Color.RED);
        return label;
    }
    public JLabel writeCorrextFiles(int i) {
        JLabel label = new JLabel("wave" + i + ".csv working and loading", JLabel.LEFT);
        label.setForeground(Color.GREEN);
        return label;
    }


}
