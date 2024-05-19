package Entity.Enemies;
import BattleField.BattleField;
import Coordination.Coords;
import Entity.*;
import java.awt.*;
import java.util.Base64;
import java.util.LinkedList;

public class Octopus extends Enemy {
    public Octopus(BattleField battleField) {
        this.battleField = battleField;
        width = 32;
        height = 29;
        x = 1200;
        y = 200;
        SHOOTCOOLDOWN = 300;
        this.destination = new Coords[10];
        speed = 5;
        loadPng("octopus.png"); //change so the method is in entity by changing the input to the method to file path instead of file name
        loadCoords("res/Coords/Pattern.csv");
        rectangle = new Rectangle(x, y, width, height+3);
    }

    @Override
    public void shootPattern(LinkedList<Projectile> projectiles) {

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
