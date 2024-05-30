package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Projectile extends Entity {
    private final boolean enemy;//if enemy == go the other way
    public Projectile(int x, int y,int speed ,BufferedImage projectileSprite, boolean enemy,int width,int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        bufferedImage = projectileSprite;
        this.enemy = enemy;
        rectangle = new Rectangle(this.x,this.y,width,height);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(bufferedImage,x,y,width,height,null); //player offset 75x 45y
        rectangle.setRect(x,y,width,height);
    }

    @Override
    public void update() {
        if (enemy) {
            x-=speed;
        } else {
            x += speed;
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
        return this.x>1400;
    }

    @Override
    public boolean outsideLeft() {
        return this.x<0;
    }

    public boolean isEnemy() {
        return enemy;
    }
}
