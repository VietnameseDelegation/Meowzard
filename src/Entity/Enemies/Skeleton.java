package Entity.Enemies;

import BattleField.BattleField;
import Entity.Player;
import Entity.Projectile;

import java.awt.Rectangle;
import java.util.LinkedList;

public class Skeleton extends Enemy{
    private final Player player;
    public Skeleton(BattleField battleField, int x, int y, int speed, int shootCooldown, int health, String patternFilePath,int score) {
        super(battleField, x, y, speed, shootCooldown, health, patternFilePath,score);
        this.player = battleField.getPlayer();
        this.width =79;
        this.height = 55;
        loadPng("skelly.png","skellyProjectile.png"); //change so the method is in entity by changing the input to the method to file path instead of file name
        rectangle = new Rectangle(x, y, width, height+3);
    }
    /**
     * adds projectile to the battlefield (shoot projectile)
     */
    @Override
    public void shoot(LinkedList<Projectile> projectiles) {
        Projectile p = new Projectile(x, y, 5,projectile, true,13*2,4*2);
        Projectile p1 = new Projectile(x, y+16, 5,projectile, true,13*2,4*2);
        projectiles.add(p);
        projectiles.add(p1);
    }
    /**
     * this specific enemy move only on the y-axis with the trade off that he know where the enemy is
     */
    @Override
    public void move(int x, int y){
        if ((this.y-speed>=player.getY()||this.y+speed<=player.getY())){
            moveY(player.getY());
        }
    }
}
