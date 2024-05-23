package Entity.Enemies;

import BattleField.BattleField;
import Coordination.Coords;
import Entity.*;
import java.awt.*;
import java.util.LinkedList;

public class Ghost extends Enemy {

    public Ghost(BattleField battleField,int x,int y,int speed,int shootCooldown,int health) {
        super(battleField,x,y,speed,shootCooldown,health);
        this.width = 32;
        this.height = 32;
        this.destination = new Coords[10];
        loadPng("ghost.png","playerProjectile.png"); //change so the method is in entity by changing the input to the method to file path instead of file name
        loadCoords("res/Coords/Pattern.csv");
        rectangle = new Rectangle(x, y, width, height);
    }
    @Override
    public boolean outsideDown() {
        return false;
    }

    @Override
    public boolean outsideUp() {
        return false;
    }

    @Override
    public boolean outsideRight() {
        return false;
    }

    @Override
    public boolean outsideLeft() {
        return false;
    }
    @Override
    public void shootPattern(LinkedList<Projectile> projectiles) {
        Projectile p = new Projectile(x, y,10 ,projectile, true);
        projectiles.add(p);
    }
}
