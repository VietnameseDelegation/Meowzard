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
    private Player player;
    private int waveNumber = 0;
    private LinkedList<Projectile> projectiles = new LinkedList<>(); // can be deleted but for now leave as it is
    private LinkedList<Enemy> enemies = new LinkedList<>();
   private WavesOfEnemies waves = new WavesOfEnemies();

    public BattleField(KeyInput keyInput) {
        player = new Player(keyInput,this);
        enemies.addAll(waves.loadWave("res/waves/wave" + waveNumber + ".csv", this)); // move this from constructor to the update so when the enemies will die you can add more or maybe not
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

        if (enemies.isEmpty()) {
            waveNumber++;
            try{
           enemies.addAll(waves.loadWave("res/waves/wave" + waveNumber + ".csv", this));
        }catch (Exception e){
                System.out.println("you won");
                //leave to main menu
            }
        }
    }

    public LinkedList<Projectile> getProjectiles() {
        return projectiles;
    }
}

