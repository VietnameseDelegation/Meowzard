package Entity;

import GameGraphics.GamePanel;
import UserInput.KeyInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Projectile extends Entity {
    GamePanel gp;
    KeyInput keyInput;
    private boolean enemy; //if enemy == go the other way

    public Projectile(GamePanel gp, KeyInput keyInput,int x,int y) {
        this.gp =gp;
        this.keyInput = keyInput;
        this.x = x;
        this.y = y;
        speed = 100;
        loadPng();
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(bufferedImage,x+75,y+40,32,32,null);
    }

    @Override
    public void update() {
        x += speed;
    }

    public void loadPng(){
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Projectiles/playerProjectile.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
