package Entity.Enemies;

import BattleField.BattleField;
import Coordination.Coords;
import Entity.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class Ghost extends Entity implements IEnemyMoves {
    private int moveCounter = 0;
    private int shootCounter = 0;
    private int index;
    private Coords[] destination;
    private BattleField battleField;
    private boolean arrivedToDestination = false;

    public Ghost(BattleField battleField) {
        this.battleField = battleField;
        x = 1000;
        y = 100;
        this.destination = new Coords[4];
        speed = 5;
        loadPng("ghost.png"); //change so the method is in entity by changing the input to the method to file path instead of file name
        loadCoords("res/Coords/Pattern.csv");
        rectangle = new Rectangle(x, y, 32, 32);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(bufferedImage, x, y, 32, 32, null);
    }

    @Override
    public void update() {
        if (shootCounter == 100) {
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
            rectangle.setRect(x, y, 32, 32);
            moveCounter = 0;
        } else {
            moveCounter++;
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

    @Override
    public void shootPattern(LinkedList<Projectile> projectiles) {
        Projectile p = new Projectile(x, y, "playerProjectile.png", true);
        projectiles.add(p);
    }
}
