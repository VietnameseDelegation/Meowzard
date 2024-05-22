package Entity;

import GameGraphics.GamePanel;
import UserInput.KeyInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class Projectile extends Entity {
    private final boolean enemy;//if enemy == go the other way
    public Projectile(int x, int y,int speed ,BufferedImage projectileSprite, boolean enemy) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        bufferedImage = projectileSprite;
        this.enemy = enemy;
        rectangle = new Rectangle(this.x,this.y,16,16);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(bufferedImage,x,y,32,32,null); //player offset 75x 45y
        rectangle.setRect(x,y,16,16);
    }

    @Override
    public void update() {
        if (enemy) {
            x-=speed;
        } else {
            x += speed;
        }
    }
    public void loadPng(String filename,String s){
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
        return this.x>1500;
    }

    @Override
    public boolean outsideLeft() {
        return this.x<0;
    }

    public boolean isEnemy() {
        return enemy;
    }
}
