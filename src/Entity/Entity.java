package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected int x,y;
    protected int speed;
    protected BufferedImage bufferedImage;
    protected abstract void draw(Graphics2D g);
    public abstract void update();
    public abstract void loadPng(String fileName);

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
}
