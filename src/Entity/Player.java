package Entity;

import BattleField.BattleField;
import UserInput.KeyInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class Player extends Entity {
    private final KeyInput keyInput;
    private BufferedImage projectile;
    private int maxHealth;
    private int currentHealth;
    private int shootCooldown =4;
    private int shootCounter = 0;
    private int scaleOfProjectile = 2;
    private boolean dead = false;

    public Player(KeyInput keyInput, BattleField battleField) {
        this.currentHealth =1000 ;
        this.maxHealth = 1000;
        this.keyInput = keyInput;
        this.battleField = battleField;
        x = 100;
        y = 100;
        speed = 7;
        loadPng("player.png", "playerProjectile.png");
        rectangle = new Rectangle(x + 17, y + 17, 52, 42);
    }
    /**
     * Updates the game state based on user input and other conditions.
     * Handles movement, shooting, and updates the position of the hitbox.
     */
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
    /**
     * Draws the game elements onto the screen.
     * @param g2 the Graphics2D object used for drawing
     */
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
/**
 * Loads png images for the player and projectile sprites.
 */
    public void loadPng(String filename, String projectileSprite) {
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PlayerModel/" + filename)));
            projectile = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Projectiles/" + projectileSprite)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
/**
 * makes a projectile put it in a LinkedList to update in the battlefield
 * */
    public void shoot(LinkedList<Projectile> projectiles) {
        Projectile p = new Projectile(x + 40, y + 30, 10, projectile, false,18*scaleOfProjectile,4*scaleOfProjectile);
        projectiles.add(p);
    }

/**
 * decrements current health and if its 0 boolean dead is true
 * */
    public void hurt() {
        currentHealth--;
        if (currentHealth <= 0) {
            dead = true;
        }
    }

    /**
     * Returns whether the player is dead.
     *
     * @return {@code true} if the player is dead, {@code false} player is alive.
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Returns whether the game is paused.
     *
     * @return {@code true} if the game is paused, {@code false} game is unpaused.
     */
    public boolean isPaused() {
        return keyInput.pause;
    }
    /**
     * Heals the player by a specified amount.
     * This method increases the player's current health by the specified amount. If the resulting health
     * exceeds the maximum health, the current health is set to the maximum health.
     *
     *
     * @param amountOfHeal the amount of health to restore.
     */
    public void heal(int amountOfHeal) {
        this.currentHealth += amountOfHeal;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }
    /**
     * Increases the maximum health by a specified amount.
     *
     * @param maxHealth the amount to increase the maximum health by
     */
    public void increaseMaxHealth(int maxHealth) {
        this.maxHealth += maxHealth;
    }

    /**
     * Speeds up the shooting by setting the shoot cooldown to a faster value.
     */
    public void shootSpeedup() {
        setShootCooldown(2);
    }

    /**
     * Slows down the shooting by setting the shoot cooldown to a slower value.
     */
    public void shootSpeedDown() {
        setShootCooldown(4);
    }

    /**
     * Increases the scale of the projectile.
     */
    public void increaseProjectileScale() {
        setScaleOfProjectile(3);
    }

    /**
     * Decreases the scale of the projectile.
     */
    public void decreaseProjectileScale() {
        setScaleOfProjectile(2);
    }

    /**
     * Sets the scale of the projectile to a specified value.
     *
     * @param scaleOfProjectile the new scale of the projectile
     */
    public void setScaleOfProjectile(int scaleOfProjectile) {
        this.scaleOfProjectile = scaleOfProjectile;
    }

    /**
     * Sets the shoot cooldown to a specified value.
     *
     * @param shootCooldown the new shoot cooldown
     */
    public void setShootCooldown(int shootCooldown) {
        this.shootCooldown = shootCooldown;
    }


}