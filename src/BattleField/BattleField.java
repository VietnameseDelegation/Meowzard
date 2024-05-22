package BattleField;

import Entity.*;
import Entity.Enemies.Enemy;
import Entity.Enemies.Ghost;
import Entity.Enemies.Octopus;
import Entity.Player;
import Entity.Projectile;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class BattleField {
    private Player player;
    private int waveNumber = 0;
    private LinkedList<Projectile> projectiles = new LinkedList<>(); // can be deleted but for now leave as it is
    private LinkedList<Entity> enemies = new LinkedList<>();
    WavesOfEnemies waves = new WavesOfEnemies();

    public BattleField(Player player) {
        this.player = player;
        // enemies.add(new Ghost(this,100,10,5,32,32,1));
        // enemies.add(Enemy.createEnemy("ghost",this,1000,100,5,1));
        // enemies.add(new Octopus(this));
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
        for (Entity e : enemies) {
            e.update();
            if (e.getRectangle().intersects(player.getRectangle())) {
                System.out.println("ouchie");
            }
            for (Projectile p : projectiles) {
                if (e.getRectangle().intersects(p.getRectangle()) && !p.isEnemy()) {
                    System.out.println("ouch");
                    projectilesToDelete.add(p);
                }
            }
        }
        for (Projectile p : projectiles) {
            p.update();
            if (p.outsideRight() || p.outsideLeft()) {
                projectilesToDelete.add(p);
            }
            if(p.getRectangle().intersects(player.getRectangle())&& p.isEnemy()) {
                System.out.println("OUWIE");
                projectilesToDelete.add(p);
            }
        }
        projectiles.removeAll(projectilesToDelete);
        player.update();
        if (player.getKeyInput().shoot) {
            player.shoot(projectiles);
        }
    }

    public LinkedList<Projectile> getProjectiles() {
        return projectiles;
    }
}

