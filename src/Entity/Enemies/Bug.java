package Entity.Enemies;

import BattleField.BattleField;
import Entity.Projectile;

import java.awt.*;
import java.util.LinkedList;

public class Bug extends Enemy {
    private int specialMoveCounter;

    public Bug(BattleField battleField, int x, int y, int speed, int shootCooldown, int health, String patternFilePath,int score) {
        super(battleField, x, y, speed, shootCooldown, health, patternFilePath,score);
        this.width = 86;
        this.height = 49;
        loadPng("bug.png","eyeProjectile.png"); //change so the method is in entity by changing the input to the method to file path instead of file name
        rectangle = new Rectangle(x, y, width, height);
    }
/**
 * adds projectiles to the battlefield (shoots projectiles)
 */
    @Override
    public void shoot(LinkedList<Projectile> projectiles) {
        Projectile p = new Projectile(x,y,5,projectile, true, 12, 12);
        Projectile p1 = new Projectile(x,y,6,projectile, true, 12, 12);
        Projectile p2 = new Projectile(x,y,7,projectile, true, 12, 12);
        projectiles.add(p);
        projectiles.add(p1);
        projectiles.add(p2);
    }
/**
 *
 * */
    @Override
    public void move(int x, int y){
        int specialMoveCountdown = 10;
        if (specialMoveCounter > specialMoveCountdown){
            int specialMoveDuration = specialMoveCountdown + 10;
            if (specialMoveCounter < specialMoveDuration){

                specialMoveCounter++;
            } else {
                specialMoveCounter=0;
            }
        } else {
            specialMoveCounter++;
            super.move(x, y);
        }
    }
}
