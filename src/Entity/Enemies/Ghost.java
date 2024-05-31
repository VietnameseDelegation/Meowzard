package Entity.Enemies;

import BattleField.BattleField;
import Entity.*;

import java.awt.Rectangle;
import java.util.LinkedList;

public class Ghost extends Enemy {
    public Ghost(BattleField battleField,int x,int y,int speed,int shootCooldown,int health,String patternFilePath,int score) {
        super(battleField,x,y,speed,shootCooldown,health,patternFilePath,score);
        this.width = 32;
        this.height = 32;
        loadPng("ghost.png","ghostProjectile.png"); //change so the method is in entity by changing the input to the method to file path instead of file name
        rectangle = new Rectangle(x, y, width, height);
    }
    /**
     * adds projectile to the battlefield (shoot projectile)
     */
    @Override
    public void shoot(LinkedList<Projectile> projectiles) {
        Projectile p = new Projectile(x, y,10 ,projectile, true,23,6);
        projectiles.add(p);
    }
}
