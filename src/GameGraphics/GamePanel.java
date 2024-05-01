package GameGraphics;
import Entity.Player;
import Entity.Projectile;
import UserInput.KeyInput;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class GamePanel extends JPanel implements Runnable {
    Queue<Projectile> queue = new LinkedList<>();
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
    private Graphics2D g2;
    private Thread gameThread;
    private KeyInput keyInput = new KeyInput();
    private int fps = 60;
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
        // game loop that consist of update and draw this happens 30 times per sec (if 30 FPS)
double drawInterval = 1000000000/fps; //we have to do it so our charachter doesnt get flung to the stratophere
double nextDrawInterval = System.nanoTime() +drawInterval;
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
        despawn++; //very inconcistent remove
        if (despawn==1 && !queue.isEmpty()){
            queue.remove();
        }
        despawn = 0;
        for (Projectile p:queue){
            p.update();
        }
        player.update();
    }
    public void paintComponent(Graphics graphics){ //built in method
        super.paintComponent(graphics);
        g2 = (Graphics2D)graphics; //has more function so using that one
        player.draw(g2);
        if (keyInput.shoot) {
            Projectile p = new Projectile(this,keyInput, player.getX(), player.getY());
            queue.add(p);
        }
        for (Projectile p:queue){
            p.draw(g2);
        }
        g2.dispose();
    }

    public Graphics2D getG2() {
        return g2;
    }

    public int getOriginalTileSize() {
        return originalTileSize;
    }
}
