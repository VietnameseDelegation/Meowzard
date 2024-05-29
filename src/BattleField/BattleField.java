package BattleField;

import Entity.*;
import Entity.Enemies.Enemy;
import Entity.Player;
import Entity.Projectile;
import UserInput.KeyInput;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

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
    private WavesOfEnemies waves = new WavesOfEnemies();
    private boolean stageClear = false;
    private boolean victory = false;
    private ArrayList<Integer> waveNotLoaded = new ArrayList<>();
    private int totalScore = 0;

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
        player.draw(g2);
    }

    public void update() {
        ArrayList<Projectile> projectilesToDelete = new ArrayList<>();
        ArrayList<Enemy> enemiesToDelete = new ArrayList<>();
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
        player.update();

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
                System.out.println("add");
            }catch (ArrayIndexOutOfBoundsException e){
                waveNotLoaded.add(waveNumber);
                waveNumber++;
            }
            catch (RuntimeException e){
                //if waves of numbers is the same as numbers of files in package then return
                System.out.println("there was a problem loading waves"+waveNotLoaded);
                e.printStackTrace();
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

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public ArrayList<Integer> getWaveNotLoaded() {
        return waveNotLoaded;
    }
}

