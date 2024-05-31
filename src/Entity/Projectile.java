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
    /**
     * Draws the game elements onto the screen.
     * @param g the Graphics2D object used for drawing
     */
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
    public boolean isEnemy() {
        return enemy;
    }
}
