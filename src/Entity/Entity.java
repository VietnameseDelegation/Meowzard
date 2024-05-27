package Entity;

import BattleField.BattleField;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected int width;
    protected int height;
    protected int x,y;
    protected int speed;
    protected BufferedImage bufferedImage;
    public BattleField battleField;
    public Rectangle2D rectangle;
    public abstract void draw(Graphics2D g);
    public abstract void update();
    public abstract void loadPng(String sprite,String projectileSprite);


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }
    public abstract boolean outsideDown();
    public abstract boolean outsideUp();
    public abstract boolean outsideRight();
    public abstract boolean outsideLeft();
    public Rectangle2D getRectangle() {
        return rectangle;
    }
}
