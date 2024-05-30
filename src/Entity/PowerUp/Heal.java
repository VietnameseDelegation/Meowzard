package Entity.PowerUp;

import Entity.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Heal extends PowerUp {

    public Heal(Player player, int x, int y) {
        super(player, x, y);
        loadPng("heal.png");
    }

    @Override
    public void applyPowerUp() {
        player.setCurrentHealth(3);
    }
}
