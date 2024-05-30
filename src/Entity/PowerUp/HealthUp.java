package Entity.PowerUp;

import Entity.Entity;
import Entity.Player;

import java.awt.*;

public class HealthUp extends PowerUp {
    private Player player;
    public HealthUp(Player player, int x, int y) {
        super(player, x, y);
        loadPng("healthUp.png");
    }

    @Override
    public void applyPowerUp() {
        player.increaseMaxHealth(2);
    }

    @Override
    public void removePowerUp() {

    }
}
