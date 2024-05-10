package Entity;

import GameGraphics.GamePanel;
import UserInput.KeyInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.*;

public class Player extends Entity{
    GamePanel gp;
    KeyInput keyInput;
    ArrayList<Projectile> projectiles = new ArrayList<>();
    public Player(GamePanel gp, KeyInput keyInput) {
        this.gp = gp;
        this.keyInput = keyInput;
        x = 100;
        y = 100;
        speed = 10;
        loadPng("player.png");
    }
    public void update(){
        System.out.println(x);
        //move this
        ArrayList<Projectile> projectileToBeDeleted = new ArrayList<>();
        for (Projectile p: projectiles){
            p.update();
            if (p.outsideRight()){
                projectileToBeDeleted.add(p);
            }
        }
        projectiles.removeAll(projectileToBeDeleted);
        if (keyInput.up){
            if (outsideUp()){
                y -= speed;
            }
        }
         if (keyInput.down){
             if (!outsideDown()){
                 y += speed;
             } else {

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
        }  if (keyInput.shoot) {
            shoot();
        }
    }
    public void draw(Graphics2D g2){
        for (Projectile p: projectiles){
            p.draw(g2);
        }
        g2.drawImage(bufferedImage,x,y,85,85,null);

    }
    public void loadPng(String filename){
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PlayerModel/"+filename)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void shoot(){
            Projectile p = new Projectile(gp,keyInput, x, y,"playerProjectile.png",false);
            projectiles.add(p);
            if (projectiles.size()>100){
                projectiles.remove(0);
            }
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
}
