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

/**
 * this is a blueprint for an enemy
 * */
public abstract class Enemy extends Entity implements IEnemyMoves {
    protected int health;
    protected int shootCounter = 0;
    protected int shootCooldown;
    protected int indexOfDestination;
    protected boolean isDead;
    protected Coords[] destination;
    protected BattleField battleField;
    protected boolean arrivedToDestination = false;
    protected BufferedImage projectile;
    protected int scoreAfterDefeat;

    public Enemy(BattleField battleField,int x,int y,int speed,int shootCooldown,int health,String patternFilePath,int scoreAfterDefeat){
        this.x = x;
        this. y = y;
        this.speed = speed;
        this.battleField = battleField;
        this.health = health;
        this.shootCooldown = shootCooldown;
        this.scoreAfterDefeat = scoreAfterDefeat;
        this.destination = new Coords[10];
        loadCoords(patternFilePath);
    }
/**
 * loads their moving pattern
 * */
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
    /**
     * Loads Png images for the enemy and projectile sprites.
     */
    @Override
    public void loadPng(String sprite,String projectileSprite) {
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Enemy/" + sprite)));
            projectile = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Projectiles/" + projectileSprite)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Draws the game elements onto the screen.
     * @param g the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g) {
        g.drawImage(bufferedImage, x, y, width, height, null);
    }
    /**
     * updates: position of the enemy
     *          shooting cooldown
     *          if it's assigned to its location if yes assign to a new location
     * */
    @Override
    public void update() {
        if (shootCounter == shootCooldown) {
            shoot(battleField.getProjectiles());
            shootCounter = 0;
        } else {
            shootCounter++;
        }
            Coords c = destination[indexOfDestination];
            if (c == null){
                indexOfDestination = 0;
                c=destination[indexOfDestination];
            }
            if (!arrivedToDestination) {
                move(c.getX(),c.getY());
            }else {
                indexOfDestination++;
                if (indexOfDestination == destination.length) {
                    indexOfDestination = 0;
                }
                arrivedToDestination = false;
            }
            rectangle.setRect(x, y,width, height);
    }

    /**
     * moves enemy
     * */
    @Override
    public void move(int x, int y) {
        moveX(x);
        moveY(y);
        if (this.x == x && this.y == y) {
            arrivedToDestination = true;
        }
    }
    public void moveX(int x){
        if (this.x > x) {
            this.x -= speed;
        }else if (this.x < x) {
            this.x += speed;
        }
    }
    public void moveY(int y){
        if (this.y > y) {
            this.y -= speed;
        }
        else if (this.y < y) {
            this.y += speed;
        }
    }

    /**
     * factory method for Enemy creation
     * */
    public static Enemy createEnemy(String choice,BattleField battleField,int x,int y,int speed,int shootCooldown,int health,String patternFilePath,int score){
        switch (choice){
            case "ghost":   return new Ghost(battleField,x,y,speed,shootCooldown,health,patternFilePath,score);
            case "octopus": return new Octopus(battleField,x,y,speed,shootCooldown,health,patternFilePath,score);
            case "bug":     return new Bug(battleField,x,y,speed,shootCooldown,health,patternFilePath,score);
            case "skeleton":return new Skeleton(battleField,x,y,speed,shootCooldown,health,patternFilePath,score);
            default:        return null;
        }
    }
    /**
     * when hit by a bullet decrement health when 0 isDead is true -> removed from the arraylist
     */
    public void hurt(){
        health--;
        if (health <= 0) {
            isDead = true;
        }
    }

    public boolean isDead() {
        return isDead;
    }

    public int getScoreAfterDefeat() {
        return scoreAfterDefeat;
    }
}
