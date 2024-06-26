package Entity.Enemies;
import BattleField.BattleField;
import Entity.*;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Octopus extends Enemy {
    public Octopus(BattleField battleField,int x,int y,int speed,int shootCooldown,int health,String patterFilePath,int score) {
        super(battleField,x,y,speed,shootCooldown,health,patterFilePath,score);
        this.width = 32;
        this.height = 29;
        loadPng("octopus.png","octopusProjectile.png"); //change so the method is in entity by changing the input to the method to file path instead of file name
        rectangle = new Rectangle(x, y, width, height+3);
    }
    /**
     * adds projectile to the battlefield (shoot projectile)
     */
    @Override
    public void shoot(LinkedList<Projectile> projectiles) {
        Projectile p = new Projectile(x, y, 3,projectile, true,32,32);
        projectiles.add(p);
    }
}
