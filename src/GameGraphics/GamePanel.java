package GameGraphics;
import Entity.Player;
import Entity.Projectile;
import UserInput.KeyInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;

public class GamePanel extends JPanel implements Runnable {
    /*
    TO-DO: regulate bullets make it shoot less make despawn more regularly

     */
    int despawn; // remove prolly

//region Screen Settings
    private final int originalTileSize = 16;
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale; // one tile after scale
    private final int maxScreenCol = 40;
    private final int maxScreenRow = 12;
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;
    //endregion
    private Thread gameThread;
    private KeyInput keyInput = new KeyInput();
    private int fps = 60;
    //timer
    // region Player
    private Player player = new Player(this,keyInput);
    //endregion

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.white); // change
        this.setDoubleBuffered(true); //optimization stuff
        this.addKeyListener(keyInput);
        setFocusable(true); //with this game panel can listen to your keys
    }

    public void start(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        // game loop that consist of update and draw this happens 60 times per sec (if 60 FPS)
double drawInterval = 1000000000/fps; //we have to do it so our charachter doesnt get flung to the stratophere
double nextDrawInterval = System.nanoTime() + drawInterval;
        while (gameThread != null){
            update();
            repaint(); //for some reason you call paintComponent() with this

            try {
                double remainningTime = nextDrawInterval - System.nanoTime();
                remainningTime = remainningTime/1000000;

                if (remainningTime<0){
                    remainningTime=0;
                }
                nextDrawInterval += drawInterval;
                Thread.sleep((long)remainningTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update(){
        player.update();
    }

    public void paintComponent(Graphics graphics){ //built in method
        super.paintComponent(graphics);
        Graphics2D g2 = (Graphics2D)graphics; //has more function so using that one
        player.draw(g2);
        g2.dispose();
    }
}
