package Entity.PowerUp;

import Entity.Entity;
import Entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public abstract class PowerUp extends Entity implements PowerUpStrategy {
    protected Player player;
    private int duration = 600;
    private int counter = 0;

    public PowerUp(Player player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.width = 32;
        this.height = 32;
        rectangle = new Rectangle(x, y, width, height);
    }

    public void loadPng(String filename) {
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PowerUp/" + filename)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(bufferedImage, x, y, width, height, null);
    }

    @Override
    public void update() {
        x -= 3;
        rectangle.setRect(x, y, width, height);
        if (counter == duration) {
            counter = 0;
            removePowerUp();
        } else {
            counter++;
        }
    }

    public void applyPowerUp() {
    }

    @Override
    public void removePowerUp() {
    }

    public static PowerUp createPowerUp(int i, Player player, int x, int y) {
        switch (i) {
            case 0:return new Heal(player, x, y);
            case 1:return new HealthUp(player, x, y);
            case 2:return new ShootSpeedUp(player, x, y);
            case 3: return new ProjectileSizeUp(player, x, y);
            default: return null;
        }
    }
}
