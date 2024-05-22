package Entity;

import BattleField.BattleField;
import GameGraphics.GamePanel;
import UserInput.KeyInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class Player extends Entity{
    GamePanel gp;
    KeyInput keyInput;
    protected BufferedImage projectile;
    public Player(GamePanel gp, KeyInput keyInput) {
        this.gp = gp;
        this.keyInput = keyInput;
        x = 100;
        y = 100;
        speed = 10;
        loadPng("player.png","playerProjectile.png");
        rectangle = new Rectangle(x+17,y+17,52,42);
    }
    public void update(){
        if (keyInput.up){
            if (outsideUp()){
                y -= speed;
            }
        }
         if (keyInput.down){
             if (!outsideDown()){
                 y += speed;
             }
        }
         if (keyInput.left){
             if (outsideLeft()){
                 x -= speed;
             }
        }
         if (keyInput.right){
            if (!outsideRight()) {
                 x += speed;
             }
        }
        rectangle.setRect(x+17,y+17,52,42);
    }
    public void draw(Graphics2D g2){
        g2.drawImage(bufferedImage,x,y,85,85,null);
    }
    public void loadPng(String filename,String projectileSprite){ //"/PlayerModel/"+filename "/Projectile/"+projectileSprite
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PlayerModel/"+filename)));
            projectile = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Projectiles/"+projectileSprite)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void shoot(LinkedList<Projectile> projectiles){
            Projectile p = new Projectile(x+40, y+30,10,projectile,false);
            projectiles.add(p);
    }

    @Override
    public boolean outsideDown (){
        return y > 500;
    }
    @Override

    public boolean outsideUp (){
        return y > -9;
    }
    @Override
    public boolean outsideRight (){
        return x > 1450;
    }
    @Override
    public boolean outsideLeft (){
        return x > -9;
    }

    public KeyInput getKeyInput() {
        return keyInput;
    }
}
