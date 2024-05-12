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

    public Projectile(GamePanel gp,KeyInput keyInput,int x,int y,String filename, boolean enemy) {
        this.gp =gp;
        this.keyInput = keyInput;
        this.x = x;
        this.y = y;
        speed = 10;
        loadPng(filename);
        this.enemy = enemy;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(bufferedImage,x+75,y+40,32,32,null);
    }

    @Override
    public void update() {
            x += speed;
    }

    public void loadPng(String filename){
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Projectiles/playerProjectile.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        return this.x>400; //change to 1440 after test
    }

    @Override
    public boolean outsideLeft() {
        return false;
    }
}
