package Entity.Enemies;

import BattleField.BattleField;
import Entity.Projectile;

import java.awt.*;
import java.util.LinkedList;

public class Bug extends Enemy {
    public Bug(BattleField battleField, int x, int y, int speed, int shootCooldown, int health, String patternFilePath,int score) {
        super(battleField, x, y, speed, shootCooldown, health, patternFilePath,score);
        this.width = 86;
        this.height = 49;
        loadPng("bug.png","octopusProjectile.png"); //change so the method is in entity by changing the input to the method to file path instead of file name
        rectangle = new Rectangle(x, y, width, height);
    }

    @Override
    public void shootPattern(LinkedList<Projectile> projectiles) {

    }
}
