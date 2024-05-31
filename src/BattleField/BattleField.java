package BattleField;

import Entity.*;
import Entity.Enemies.Enemy;
import Entity.Player;
import Entity.PowerUp.PowerUp;
import Entity.Projectile;
import UserInput.KeyInput;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
/**
 * class that handles game states updates all enemies projectiles and player
 * */
public class BattleField {
    private int stageCounter = 0;
    private final Player player;
    private int waveNumber = 0;
    private final LinkedList<Projectile> projectiles = new LinkedList<>();// can be deleted but for now leave as it is
    private ArrayList<LinkedList<Enemy>> allWaves = new ArrayList<>();
    private LinkedList<Enemy> enemies = new LinkedList<>();
    private final LinkedList<Entity> powerUp = new LinkedList<>();
    private final WavesOfEnemies waves = new WavesOfEnemies();
    private boolean stageClear = false;
    private boolean victory = false;
    private boolean gameOver = false;
    private final ArrayList<Integer> waveNotLoaded = new ArrayList<>();
    private int totalScore = 0;
    private final Random rand = new Random();

    public BattleField(KeyInput keyInput) {
        player = new Player(keyInput, this);
        if (allWaves.isEmpty()) {
            this.allWaves = preloadWaves();
        }
        waveNumber = 0;
        enemies = allWaves.get(waveNumber);
    }
    /**
     * Draws the game elements onto the screen.
     * @param g2 the Graphics2D object used for drawing
     */
    public void draw(Graphics2D g2) {
        for (Entity e : enemies) {
            e.draw(g2);
        }
        for (Projectile p : projectiles) {
            p.draw(g2);
        }
        for (Entity entity : powerUp) {
            entity.draw(g2);
        }
        player.draw(g2);
    }
    /**
     * Updates the game state.
     * This method updates the player's state and, if the game is not paused, performs a series of updates:
     * power-ups, enemies, projectiles, and the stage. It manages the deletion of projectiles through an intermediate
     * list.
     *
     */
    public void update() {
       player.update();
        if (!player.isPaused()) {
            updatePowerUp();
            ArrayList<Projectile> projectilesToDelete = new ArrayList<>();
            updateEnemies(projectilesToDelete);
            updateProjectile(projectilesToDelete);
            updateStage();

        }
    }

    public LinkedList<Projectile> getProjectiles() {
        return projectiles;
    }
    /**
     * Preloads all waves of enemies from CSV files.
     * This method continuously loads waves of enemies from CSV files until a runtime exception occurs.
     * If a  RuntimeException occurs, the method
     * returns the list of all successfully loaded waves.
     *
     *
     * @return an ArrayList of LinkedList<Enemy> representing all successfully preloaded waves.
     */
    public ArrayList<LinkedList<Enemy>> preloadWaves() {
        ArrayList<LinkedList<Enemy>> allWaves = new ArrayList<>();
        while (true) {
            try {
                allWaves.add(waves.loadWave("res/waves/wave" + waveNumber + ".csv", this));
                waveNumber++;
            } catch (ArrayIndexOutOfBoundsException e) {
                waveNotLoaded.add(waveNumber);
                waveNumber++;
            } catch (RuntimeException e) {
                return allWaves;
            }
        }
    }

    /**
     * Returns whether the stage is clear.
     * @return {@code true} if the stage is clear, {@code false} otherwise.
     */
    public boolean isStageClear() {
        return stageClear;
    }

    /**
     * Returns whether the game is won.
     * @return {@code true} if the game is won, {@code false} otherwise.
     */
    public boolean isVictory() {
        return victory;
    }

    /**
     * Returns the total score.
     * @return the total score.
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Returns a list of waves that have not been loaded.
     *
     * @return an {@code ArrayList} of integers representing the waves that have not been loaded.
     */
    public ArrayList<Integer> getWaveNotLoaded() {
        return waveNotLoaded;
    }

    /**
     * Returns whether the game is over.
     *
     * @return {@code true} if the game is over, {@code false} otherwise.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Returns all waves of enemies.
     *
     * @return an {@code ArrayList} of {@code LinkedList<Enemy>} representing all waves of enemies.
     */
    public ArrayList<LinkedList<Enemy>> getAllWaves() {
        return allWaves;
    }

    /**
     * Updates the state of power-ups in the game.
     * Each power-up is updated, checked for collision with the player, and applied if a collision occurs.
     * Removes power-ups that have been collected by the player.
     **/
    public void updatePowerUp() {
        ArrayList<Entity> powerUpsToDelete = new ArrayList<>();
        for (Entity entity : powerUp) {
            entity.update();
            if (entity.getRectangle().intersects(player.getRectangle())) {
                PowerUp p = (PowerUp) entity;
                p.applyPowerUp();
                powerUpsToDelete.add(entity);
            }
        }
        powerUp.removeAll(powerUpsToDelete);
    }
    /**
     * that moves enemies
     * method checks if: they were hit by a projectile
     **/

    public void updateEnemies(ArrayList<Projectile> projectilesToDelete) {
        ArrayList<Enemy> enemiesToDelete = new ArrayList<>();
        for (Enemy e : enemies) {
            e.update();
            for (Projectile p : projectiles) {
                if (e.getRectangle().intersects(p.getRectangle()) && !p.isEnemy()) {
                    e.hurt();
                    projectilesToDelete.add(p);
                }
            }
            if (e.isDead()) {
                enemiesToDelete.add(e);
                int i = rand.nextInt(7);
                if (i < 4) {
                    powerUp.add(PowerUp.createPowerUp(i, player, e.getX(), e.getY()));
                }
            }
        }
        for (Enemy e : enemiesToDelete) {
            totalScore += e.getScoreAfterDefeat();
        }
        enemies.removeAll(enemiesToDelete);
    }
    /**
     * method that moves projectiles and checks: if projectile is outside the playing field -> delete projectile,
     *                                           if projectile hit player -> hurt player and delete projectile,
     *
     * **/

    public void updateProjectile(ArrayList<Projectile> projectilesToDelete) {
        for (Projectile p : projectiles) {
            p.update();
           if (!(p.outsideRight() || p.outsideLeft())) {
                projectilesToDelete.add(p);
            }
            if (p.getRectangle().intersects(player.getRectangle()) && p.isEnemy()) {
                player.hurt();
                projectilesToDelete.add(p);
            }
        }
        projectiles.removeAll(projectilesToDelete);
    }
/**
 * method that checks if the user: cleared a wave -> load new wave,
 *                                 cleared all waves -> victory,
 *                                 is dead -> game over,
 **/
    public void updateStage() {
        if (enemies.isEmpty() && !victory) {
            stageClear = true;
            try {
                if (stageCounter == 100) {
                    waveNumber++;
                    stageCounter = 0;
                    stageClear = false;
                    enemies.addAll(allWaves.get(waveNumber));
                } else {
                    stageCounter++;
                }
            } catch (Exception e) {
                victory = true;
            }
        }
        if (player.isDead()) {
            gameOver = true;
        }
    }
    public Player getPlayer() {
        return player;
    }
}

