package Entity.Enemies;

import BattleField.BattleField;
import Coordination.Coords;
import Entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

// this is basically a blueprint for an enemy
public abstract class Enemy extends Entity implements IEnemyMoves {
    protected int width;
    protected int height;
    protected int moveCounter = 0;
    protected int shootCounter = 0;
    protected static int SHOOTCOOLDOWN;
    protected int index;
    protected Coords[] destination;
    protected BattleField battleField;
    protected boolean arrivedToDestination = false;

    public void loadCoords(String filePath){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath)); //"res/Coords/Pattern.csv"
            String s;
            br.readLine();
            int index = 0;
            while ((s=br.readLine())!=null){
                int x = Integer.parseInt(s.split(",")[0]);
                int y = Integer.parseInt(s.split(",")[1]);
                destination[index] = new Coords(x,y);
                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void loadPng(String fileName) {
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Enemy/" + fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void draw(Graphics2D g) {
        g.drawImage(bufferedImage, x, y, width, height, null);
    }
    @Override
    public void update() {
        if (shootCounter == SHOOTCOOLDOWN) {
            shootPattern(battleField.getProjectiles());
            System.out.println("shoot");
            shootCounter = 0;
        } else {
            shootCounter++;
        }
        if (moveCounter == 0) {
            Coords c = destination[index];
            if (c == null){
                index = 0;
                c=destination[index];
            }
            if (!arrivedToDestination) {
                move(c.getX(),c.getY());
            }else {
                index++;
                if (index == destination.length) {
                    index = 0;
                }
                arrivedToDestination = false;
            }
            rectangle.setRect(x, y,width, height);
            moveCounter = 0;
        } else {
            moveCounter++;
        }
    }
    @Override
    public void move(int x, int y) {
        if (this.x > x) {
            this.x -= speed;
        }else if (this.x < x) {
            this.x += speed;
        }
        if (this.y > y) {
            this.y -= speed;
        }
        else if (this.y < y) {
            this.y += speed;
        }
        if (this.x == x && this.y == y) {
            arrivedToDestination = true;
        }
    }
}
