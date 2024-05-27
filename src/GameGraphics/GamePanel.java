package GameGraphics;
import BattleField.BattleField;
import Entity.Player;
import UserInput.KeyInput;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
//inspirováno https://www.youtube.com/watch?v=om59cwR7psI&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=1
public class GamePanel extends JPanel implements Runnable {
//region Screen Settings
    private final int screenWidth = 1440;
    private final int screenHeight = 576;
    private final Image backround;
    private Font font;

    //endregion
    private Thread gameThread;
    private KeyInput keyInput = new KeyInput();
    private int fps = 60;
    private BattleField battleField = new BattleField(keyInput);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setDoubleBuffered(true); //optimization stuff
        this.addKeyListener(keyInput);
        setBackground(Color.PINK);
        setFocusable(true);//with this game panel can listen to your keys
        this.backround = loadImg();
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("Font/Deep Hero.ttf");
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,inputStream).deriveFont(100f);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        battleField.update();
    }

    public void paintComponent(Graphics graphics){ //built in method
        super.paintComponent(graphics);
       Graphics2D g2 = (Graphics2D)graphics;//has more function so using that one
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        //graphics.drawImage(backround,0,0,screenWidth,screenHeight,null);
        battleField.draw(g2);
        if (battleField.isStageClear()){
            g2.drawString("STAGE CLEAR!",screenWidth/2,screenHeight/2);
        }
        if(battleField.isVictory()){
            g2.drawString("Victory",screenWidth/2,screenHeight/2);
        }
        g2.dispose();
    }
    public Image loadImg(){
        return new ImageIcon(Objects.requireNonNull(getClass().getResource("/Background/background.png"))).getImage();
    }
}
