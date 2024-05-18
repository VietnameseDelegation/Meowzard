package BattleField;

import Entity.*;
import Entity.Enemies.Ghost;
import Entity.Player;
import Entity.Projectile;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class BattleField {

    private Player player;
private LinkedList<Projectile> projectiles = new LinkedList<>(); // can be deleted but for now leave as it is
private LinkedList<Entity> enemies = new LinkedList<>();

    public BattleField(Player player) {
        this.player = player;
        enemies.add(new Ghost(this));
        enemies.add(new rectangleTest());
    }

    public void draw(Graphics2D g2){
        for (Entity e:enemies){
            e.draw(g2);
        }
        for (Projectile p:projectiles){
            p.draw(g2);
        }
        player.draw(g2);
    }
    public void update() {
        ArrayList<Projectile> projectilesToDelete = new ArrayList<>();
        for (Entity e:enemies) {
            e.update();
            if (e.getRectangle().intersects(player.getRectangle())) {
                System.out.println("ouchie");
            }
            for (Projectile p:projectiles){
                if (e.getRectangle().intersects(p.getRectangle()) && !p.isEnemy()) {
                    System.out.println("ouch");
                    projectilesToDelete.add(p);
                }
            }
        }
        for (Projectile p:projectiles){
            p.update();
            if (p.outsideRight()){
                projectilesToDelete.add(p);
            }
        }
        projectiles.removeAll(projectilesToDelete);
        player.update();
        if (player.getKeyInput().shoot){
            player.shoot(projectiles);
        }
    }

    public LinkedList<Projectile> getProjectiles() {
        return projectiles;
    }
}

