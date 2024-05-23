package Entity.Enemies;

import BattleField.BattleField;
import Coordination.Coords;
import Entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

// this is basically a blueprint for an enemy
public abstract class Enemy extends Entity implements IEnemyMoves {
    protected int health;
    protected int width;
    protected int height;
    protected int moveCounter = 0;
    protected int shootCounter = 0;
    protected int shootCooldown;
    protected int index;
    protected boolean isDead;
    protected Coords[] destination;
    protected BattleField battleField;
    protected boolean arrivedToDestination = false;
    protected BufferedImage projectile;
    protected int scoreAfterDefeat; // load via file
    public Enemy(BattleField battleField,int x,int y,int speed,int shootCooldown,int health,String patternFilePath){
        this.x = x;
        this. y = y;
        this.speed = speed;
        this.battleField = battleField;
        this.health = health;
        this.shootCooldown = shootCooldown;
        this.destination = new Coords[10];
        loadCoords(patternFilePath);
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
    public void loadPng(String sprite,String projectileSprite) {
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Enemy/" + sprite)));
            projectile = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Projectiles/" + projectileSprite)));
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
        if (shootCounter == shootCooldown) {
            shootPattern(battleField.getProjectiles());
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
    public static Enemy createEnemy(String choice,BattleField battleField,int x,int y,int speed,int shootCooldown,int health,String patternFilePath){
        switch (choice){
            case "ghost": return new Ghost(battleField,x,y,speed,shootCooldown,health,patternFilePath);
            case "octopus": return new Octopus(battleField,x,y,speed,shootCooldown,health,patternFilePath);
            default: return null;
        }
    }
    public void hurt(){
        health--;
        if (health <= 0) {
            isDead = true;
        }
    }

    public boolean isDead() {
        return isDead;
    }
}
