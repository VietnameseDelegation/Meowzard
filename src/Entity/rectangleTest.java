package Entity;

import java.awt.*;

public class rectangleTest extends Entity{
    public rectangleTest() {
        rectangle = new Rectangle(100,50,60,60);
    }

    @Override
    public void draw(Graphics2D g) {
        g.fillRect(100,50,60,60);
    }

    @Override
    public void update() {

    }

    @Override
    public void loadPng(String fileName) {

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
}
