package Entity;

import BattleField.BattleField;
import UserInput.KeyInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class Player extends Entity {
    private KeyInput keyInput;
    private BufferedImage projectile;
    private int maxHealth;
    private int currentHealth;
    private int shootCooldown =4;
    private int shootCounter = 0;
    private int scaleOfProjectile = 2;
    private boolean dead=false;
    public Player(KeyInput keyInput, BattleField battleField) {
        this.currentHealth = 10;
        this.maxHealth = 10;
        this.keyInput = keyInput;
        this.battleField = battleField;
        x = 100;
        y = 100;
        speed = 7;
        loadPng("player.png", "playerProjectile.png");
        rectangle = new Rectangle(x + 17, y + 17, 52, 42);
    }

    public void update() {
        if (!keyInput.pause){
            if (keyInput.up) {
                if (outsideUp()) {
                    y -= speed;
                }
            }
            if (keyInput.down) {
                if (!outsideDown()) {
                    y += speed;
                }
            }
            if (keyInput.left) {
                if (outsideLeft()) {
                    x -= speed;
                }
            }
            if (keyInput.right) {
                if (!outsideRight()) {
                    x += speed;
                }
            }
            if (keyInput.shoot) {
                if (shootCooldown < shootCounter) {
                    shoot(battleField.getProjectiles());
                    shootCounter = 0;
                }
            }
            shootCounter++;
            rectangle.setRect(x + 17, y + 17, 52, 42);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(bufferedImage, x, y, 85, 85, null);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        String s = String.format("Health: "+currentHealth, Font.BOLD, Font.PLAIN, Font.ITALIC);
       g2.drawString("Score: "+battleField.getTotalScore(),100,50);
        g2.drawString(s,100,80);
        if(keyInput.pause){
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 48));
            g2.drawString("Pause",1000,250);
        }
    }

    public void loadPng(String filename, String projectileSprite) {
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PlayerModel/" + filename)));
            projectile = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Projectiles/" + projectileSprite)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void shoot(LinkedList<Projectile> projectiles) {
        Projectile p = new Projectile(x + 40, y + 30, 10, projectile, false,18*scaleOfProjectile,4*scaleOfProjectile);
        projectiles.add(p);
    }

    @Override
    public boolean outsideDown() {
        return y > 500;
    }

    @Override

    public boolean outsideUp() {
        return y > -9;
    }

    @Override
    public boolean outsideRight() {
        return x > 1355;
    }

    @Override
    public boolean outsideLeft() {
        return x > -9;
    }

    public void hurt() {
        currentHealth--;
        if (currentHealth <= 0) {
            dead = true;
        }
    }

    public boolean isDead() {
        return dead;
    }

    public boolean isPaused() {
        return keyInput.pause;
    }

    public void setCurrentHealth(int amountOfHeal) {
        this.currentHealth += amountOfHeal;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }
    public void increaseMaxHealth(int maxHealth) {
        this.maxHealth += maxHealth;
    }
    public void shootSpeedup(){
        setShootCooldown(2);
    }
    public void shootSpeedDown(){
        setShootCooldown(4);
    }
    public void increaseProjectileScale(){
        setScaleOfProjectile(3);
    }
    public void decreaseProjectileScale(){
        setScaleOfProjectile(2);
    }

    public void setScaleOfProjectile(int scaleOfProjectile) {
        this.scaleOfProjectile = scaleOfProjectile;
    }

    public void setShootCooldown(int shootCooldown) {
        this.shootCooldown = shootCooldown;
    }

}