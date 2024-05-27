package Entity.Enemies;

import BattleField.BattleField;
import Entity.Projectile;

import java.awt.*;
import java.util.LinkedList;

public class Enemy3 extends Enemy{
    public Enemy3(BattleField battleField, int x, int y, int speed, int shootCooldown, int health, String patternFilePath) {
        super(battleField, x, y, speed, shootCooldown, health, patternFilePath);
        this.width = 32+10;
        this.height = 29+10;
        loadPng("octopus.png","octopusProjectile.png"); //change so the method is in entity by changing the input to the method to file path instead of file name
        rectangle = new Rectangle(x, y, width, height+3);
    }

    @Override
    public void shootPattern(LinkedList<Projectile> projectiles) {

    }
}
