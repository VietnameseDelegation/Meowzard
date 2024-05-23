package Entity.Enemies;
import BattleField.BattleField;
import Coordination.Coords;
import Entity.*;
import java.awt.*;
import java.util.LinkedList;

public class Octopus extends Enemy {
    public Octopus(BattleField battleField,int x,int y,int speed,int shootCooldown,int health,String patterFilePath) {
        super(battleField,x,y,speed,shootCooldown,health,patterFilePath);
        this.width = 32;
        this.height = 29;
        loadPng("octopus.png","octopusProjectile.png"); //change so the method is in entity by changing the input to the method to file path instead of file name
        rectangle = new Rectangle(x, y, width, height+3);
    }

    @Override
    public void shootPattern(LinkedList<Projectile> projectiles) {
        Projectile p = new Projectile(x, y, 3,projectile, true);
        projectiles.add(p);
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
}
