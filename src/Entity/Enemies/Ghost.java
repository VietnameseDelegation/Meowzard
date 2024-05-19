package Entity.Enemies;

import BattleField.BattleField;
import Coordination.Coords;
import Entity.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class Ghost extends Enemy {

    public Ghost(BattleField battleField) {
        this.battleField = battleField;
        width = 32;
        height = 32;
        x = 1000;
        y = 100;
        SHOOTCOOLDOWN = 100;
        this.destination = new Coords[10];
        speed = 5;
        loadPng("ghost.png"); //change so the method is in entity by changing the input to the method to file path instead of file name
        loadCoords("res/Coords/Pattern.csv");
        rectangle = new Rectangle(x, y, width, height);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(bufferedImage, x, y, width, height, null);
    }
    @Override
    public void loadPng(String fileName) {
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Enemy/" + fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        Projectile p = new Projectile(x, y, "playerProjectile.png", true);
        projectiles.add(p);
    }
}
