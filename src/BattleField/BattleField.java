package BattleField;

import Entity.Enemy;
import Entity.Player;
import Entity.Projectile;
import GameGraphics.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class BattleField {

    private Player player;
private LinkedList<Projectile> projectiles = new LinkedList<>();
private LinkedList<Enemy> enemies = new LinkedList<>();

    public BattleField(Player player) {
        this.player = player;
    }

    public void draw(Graphics2D g2){
        for (Enemy e:enemies){
            e.draw(g2);
        }
        for (Projectile p:projectiles){
            p.draw(g2);
        }
        player.draw(g2);
    }
    public void update() {
        for (Enemy e:enemies){
            e.update();
        }
        ArrayList<Projectile> projectilesToDelete = new ArrayList<>();
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
}
