package Entity;

import BattleField.BattleField;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected int width;
    protected int height;
    protected int x, y;
    protected int speed;
    protected BufferedImage bufferedImage;
    protected BattleField battleField;
    protected Rectangle2D rectangle;

    public abstract void draw(Graphics2D g);

    public abstract void update();

    public void loadPng(String sprite, String projectileSprite) {}

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

    /**
     * Checks if the entity is outside the lower boundary of the game area.
     */
    public boolean outsideDown() {
        return y > 500;
    }

    /**
     * Checks if the entity is outside the upper boundary of the game area.
     *
     */
    public boolean outsideUp() {
        return y > -9;
    }

    /**
     * Checks if the entity is outside the right boundary of the game area.
     *
     */
    public boolean outsideRight() {
        return x > 1355;
    }

    /**
     * Checks if the entity is outside the left boundary of the game area.
     *
     */
    public boolean outsideLeft() {
        return x > -9;
    }


    public Rectangle2D getRectangle() {
        return rectangle;
    }
}
