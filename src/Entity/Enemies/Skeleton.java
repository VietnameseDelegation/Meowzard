package Entity.Enemies;

import BattleField.BattleField;
import Entity.Projectile;

import java.awt.*;
import java.util.LinkedList;

public class Skeleton extends Enemy{
    public Skeleton(BattleField battleField, int x, int y, int speed, int shootCooldown, int health, String patternFilePath) {
        super(battleField, x, y, speed, shootCooldown, health, patternFilePath);
        this.width =79;
        this.height = 55;
        loadPng("skelly.png","skellyProjectile.png"); //change so the method is in entity by changing the input to the method to file path instead of file name
        rectangle = new Rectangle(x, y, width, height+3);
    }

    @Override
    public void shootPattern(LinkedList<Projectile> projectiles) {
        Projectile p = new Projectile(x, y, 5,projectile, true,13*2,4*2);
        Projectile p1 = new Projectile(x, y+16, 5,projectile, true,13*2,4*2);
        projectiles.add(p);
        projectiles.add(p1);
    }
}
