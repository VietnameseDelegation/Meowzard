package BattleField;

import Entity.*;
import Entity.Enemies.Enemy;
import Entity.Player;
import Entity.PowerUp.Heal;
import Entity.PowerUp.PowerUp;
import Entity.PowerUp.ProjectileSizeUp;
import Entity.PowerUp.ShootSpeedUp;
import Entity.Projectile;
import UserInput.KeyInput;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class BattleField {
    /*
    * TO-DO: write text on screen if you won or not
    *        write more waves
    *        write more patterns
    *        main menu
    *        pause?
    *        rekurze for loading the waves
    *        method to write on screen
     */
    private int stageCounter = 0;
    private Player player;
    private int waveNumber = 0;
    private LinkedList<Projectile> projectiles = new LinkedList<>();// can be deleted but for now leave as it is
    private ArrayList<LinkedList<Enemy>> allWaves = new ArrayList<>();
    private LinkedList<Enemy> enemies = new LinkedList<>();
    private LinkedList<Entity> powerUp = new LinkedList<>();
    private WavesOfEnemies waves = new WavesOfEnemies();
    private boolean stageClear = false;
    private boolean victory = false;
    private boolean gameOver = false;
    private ArrayList<Integer> waveNotLoaded = new ArrayList<>();
    private int totalScore = 0;
    private Random rand = new Random();

    public BattleField(KeyInput keyInput) {
        player = new Player(keyInput,this);
        if (allWaves.isEmpty()) {
            this.allWaves = loadWaves();
        }
        waveNumber = 0;
        enemies = allWaves.get(waveNumber);
    }

    public void draw(Graphics2D g2) {
        for (Entity e : enemies) {
            e.draw(g2);
        }
        for (Projectile p : projectiles) {
            p.draw(g2);
        }
        for (Entity entity :powerUp){
            entity.draw(g2);
        }
        player.draw(g2);
    }

    public void update() {
        player.update();
        if (!player.isPaused()){
        ArrayList<Projectile> projectilesToDelete = new ArrayList<>();
        ArrayList<Enemy> enemiesToDelete = new ArrayList<>();
        ArrayList<Entity> powerUpsToDelete = new ArrayList<>();
            for (Entity entity : powerUp){
                entity.update();
                if (entity.getRectangle().intersects(player.getRectangle())){
                    PowerUp p = (PowerUp) entity;
                    p.applyPowerUp();
                    powerUpsToDelete.add(entity);
                }
            }
        for (Enemy e : enemies) {
            e.update();
            if (e.getRectangle().intersects(player.getRectangle())) {
                player.hurt();
            }
            for (Projectile p : projectiles) {
                if (e.getRectangle().intersects(p.getRectangle()) && !p.isEnemy()) {
                    e.hurt();
                    projectilesToDelete.add(p);
                }
            }
            if(e.isDead()){
                enemiesToDelete.add(e);
                int i = rand.nextInt(7);
                if (i < 4) {
                    powerUp.add(new ShootSpeedUp(player,e.getX(),e.getY()));
                }
            }
        }
        for(Enemy e:enemiesToDelete){
            totalScore+=e.getScoreAfterDefeat();
        }
        enemies.removeAll(enemiesToDelete);
        for (Projectile p : projectiles) {
            p.update();
            if (p.outsideRight() || p.outsideLeft()) {
                projectilesToDelete.add(p);
            }
            if(p.getRectangle().intersects(player.getRectangle())&& p.isEnemy()) {
                player.hurt();
                projectilesToDelete.add(p);
            }
        }
        projectiles.removeAll(projectilesToDelete);
            powerUp.removeAll(powerUpsToDelete);

        if (enemies.isEmpty()&&!victory) {
            stageClear = true;
            try{
                if (stageCounter == 100) {
                    waveNumber++;
                    stageCounter = 0;
                    stageClear = false;
                    enemies.addAll(allWaves.get(waveNumber));
                }else {
                    stageCounter++;
                }
            }catch(Exception e){
                victory = true;
            }
        }
        if (player.isDead()){
            gameOver = true;
        }
        }
    }
    public LinkedList<Projectile> getProjectiles() {
        return projectiles;
    }
    public ArrayList<LinkedList<Enemy>> loadWaves(){
        ArrayList<LinkedList<Enemy>> allWaves = new ArrayList<>();
        boolean b =true;
        while(b){
            try {
                allWaves.add(waves.loadWave("res/waves/wave" + waveNumber + ".csv", this));
                waveNumber++;
            }catch (ArrayIndexOutOfBoundsException e){
                waveNotLoaded.add(waveNumber);
                waveNumber++;
            }
            catch (RuntimeException e){
                return allWaves;
            }
        }
        return null;
    }
    public ArrayList<Integer> checkWaves(){
        loadWaves();
        return waveNotLoaded;
    }

    public boolean isStageClear() {
        return stageClear;
    }

    public void setStageClear(boolean stageClear) {
        this.stageClear = stageClear;
    }
    public boolean isVictory() {
        return victory;
    }
    public int getTotalScore() {
        return totalScore;
    }

    public ArrayList<Integer> getWaveNotLoaded() {
        return waveNotLoaded;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public ArrayList<LinkedList<Enemy>> getAllWaves() {
        return allWaves;
    }
}

