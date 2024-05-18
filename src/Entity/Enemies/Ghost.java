package Entity.Enemies;

import BattleField.BattleField;
import Entity.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class Ghost extends Entity implements IEnemyMoves {
    private int moveCounter = 0;
    private int shootCounter = 0;
    private BattleField battleField;

    public Ghost(BattleField battleField) {
        this.battleField = battleField;
        x = 1000;
        y = 100;
        speed = 1;
        loadPng("ghost.png");
        rectangle = new Rectangle(x, y, 32, 32);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(bufferedImage, x, y, 32, 32, null);
    }

    @Override
    public void update() {
        if (shootCounter == 100) {
            shootPattern(battleField.getProjectiles());
            System.out.println("shoot");
            shootCounter = 0;
        } else {
            shootCounter++;
        }
        if (moveCounter == 1) {
            move(10,10);
            rectangle.setRect(x, y, 32, 32);
            moveCounter = 0;
        } else {
            moveCounter++;
        }
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
    public void move(int x, int y) {
        if (this.x > x) {
            this.x -= speed;
        }else if (this.x < x) {
            this.x += speed;
        }
        if (this.y > y) {
            this.y -= speed;
        }
        else if (this.y < y) {
            this.y += speed;
        }
    }

    @Override
    public void shootPattern(LinkedList<Projectile> projectiles) {
        Projectile p = new Projectile(x, y, "playerProjectile.png", true);
        projectiles.add(p);
    }
}
