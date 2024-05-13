package Entity;

import GameGraphics.GamePanel;
import UserInput.KeyInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Ghost extends Entity implements IEnemyMoves {
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            move();
        }
    };
    public Ghost() {
        x = 100;
        y = 100;
        speed = 1;
        loadPng("ghost.png");
        rectangle = new Rectangle(x,y,32,32);
    }
    @Override
    public void draw(Graphics2D g) {
        g.drawImage(bufferedImage,x,y,32,32,null);
    }

    @Override
    public void update() {
      //  timer.scheduleAtFixedRate(task, 1000, 1000);
        move();
        rectangle.setRect(x,y,32,32);
    }

    @Override
    public void loadPng(String fileName) {
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Enemy/"+fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean outsideDown() {
        return false;
    }

    @Override
    public boolean outsideUp() {
        return false;
    }

    @Override
    public boolean outsideRight() {
        return false;
    }

    @Override
    public boolean outsideLeft() {
        return false;
    }

    @Override
    public void move() {
        x += speed;
    }

    @Override
    public void shootPattern() {

    }
}
