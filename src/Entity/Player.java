package Entity;

import GameGraphics.GamePanel;
import UserInput.KeyInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gp;
    KeyInput keyInput;
    LinkedList<Projectile> projectiles = new LinkedList<>();
    public Player(GamePanel gp, KeyInput keyInput) {
        this.gp = gp;
        this.keyInput = keyInput;
        x = 100;
        y = 100;
        speed = 10;
        loadPng();
    }
    public void update(){
        if (keyInput.up){
            y -= speed;
        }
         if (keyInput.down){
            y += speed;
        }
         if (keyInput.left){
            x -= speed;
        }
         if (keyInput.right){
            x += speed;
        }  if (keyInput.shoot) {
            shoot();
        }
    }
    public void draw(Graphics2D g2){
        g2.drawImage(bufferedImage,x,y,85,80,null);
        for (Projectile p: projectiles){
            p.draw(g2);
        }
    }
    public void loadPng(){
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PlayerModel/player.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void shoot(){
            Projectile p = new Projectile(gp,keyInput, x, y);
            projectiles.add(p);
    }
}
