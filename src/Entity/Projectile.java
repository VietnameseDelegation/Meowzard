package Entity;

import GameGraphics.GamePanel;
import UserInput.KeyInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Projectile extends Entity {
    GamePanel gp;
    KeyInput keyInput;
    private boolean enemy;

    public Projectile(GamePanel gp, KeyInput keyInput,int x,int y) {
        this.gp =gp;
        this.keyInput = keyInput;
        this.x = x;
        this.y = y;
        speed = 10;
        loadPng();
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(bufferedImage,x,y,32,32,null);
    }

    @Override
    public void update() {

    }

    public void loadPng(){
        try {
            bufferedImage = ImageIO.read(getClass().getResourceAsStream("/Projectiles/playerProjectile.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
