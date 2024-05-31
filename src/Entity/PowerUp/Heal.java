package Entity.PowerUp;

import Entity.*;


public class Heal extends PowerUp {

    public Heal(Player player, int x, int y) {
        super(player, x, y);
        loadPng("heal.png");
    }

    @Override
    public void applyPowerUp() {
        player.heal(3);
    }
}
