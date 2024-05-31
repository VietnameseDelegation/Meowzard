package GameGraphics;

import BattleField.BattleField;
import UserInput.KeyInput;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * The GamePanel class represents the main panel for the game, handling the game loop and rendering
 * This class is inspired by the tutorial series: https://www.youtube.com/watch?v=om59cwR7psI&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=1
 */
public class GamePanel extends JPanel implements Runnable {
    //region Screen Settings
    private final int screenWidth = 1440;
    private final int screenHeight = 576;
    private final Font font;
    private final int fps = 60;
    //endregion
    private Thread gameThread;
    private final BattleField battleField;

    /**
     * Constructs a new GamePanel with the specified BattleField and KeyInput.
     *
     * @param battleField The BattleField instance representing the game state.
     * @param keyInput The KeyInput instance for handling user input.
     */
    public GamePanel(BattleField battleField, KeyInput keyInput) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true); // Optimization for smoother rendering
        this.addKeyListener(keyInput);
        setBackground(Color.PINK);
        this.battleField = battleField;
        setFocusable(true); // Allows the panel to receive keyboard input
        Image background = loadImg();
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("Font/Deep Hero.ttf");
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(100f);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Starts the game thread.
     */
    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * The main game loop, which updates and repaints the game state at a fixed FPS.
     */
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / fps; // Calculate the interval between frames
        double nextDrawInterval = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            update();
            repaint(); // Calls paintComponent()

            if (battleField.isVictory()) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Dispose the current window and open the main menu
                Component comp = SwingUtilities.getRoot(this);
                ((Window) comp).dispose();
                new MainMenu();
                break;
            }

            if (battleField.isGameOver()) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Dispose the current window and open the main menu
                Component comp = SwingUtilities.getRoot(this);
                ((Window) comp).dispose();
                new MainMenu();
                break;
            }

            try {
                double remainingTime = nextDrawInterval - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                nextDrawInterval += drawInterval;
                Thread.sleep((long) remainingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Updates the game state.
     */
    public void update() {
        battleField.update();
    }

    /**
     * Renders the game state.
     *
     * @param graphics The Graphics object to draw on.
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2 = (Graphics2D) graphics; // Use Graphics2D for more functionality
        g2.setColor(Color.WHITE);
        g2.setFont(font);
        if (battleField.isVictory()) {
            g2.drawString("Victory", screenWidth / 2, screenHeight / 2);
        } else if (battleField.isStageClear() && !battleField.isVictory()) {
            g2.drawString("STAGE CLEAR!", screenWidth / 2, screenHeight / 2);
        } else if (battleField.isGameOver()) {
            g2.drawString("Dead :(", screenWidth / 2, screenHeight / 2);
        }
        battleField.draw(g2);
        g2.dispose();
    }

    /**
     * Loads the background image.
     * @return The loaded Image object.
     */
    public Image loadImg() {
        return new ImageIcon(Objects.requireNonNull(getClass().getResource("/Background/background.png"))).getImage();
    }
}
