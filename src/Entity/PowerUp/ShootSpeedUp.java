package Entity.PowerUp;

import Entity.Player;

public class ShootSpeedUp extends PowerUp {


    public ShootSpeedUp(Player player, int x, int y) {
        super(player, x, y);
        loadPng("shotSpeedUp.png");
    }

    @Override
    public void applyPowerUp() {
        player.shootSpeedup();
    }

    @Override
    public void removePowerUp() {
        player.shootSpeedDown();
    }
}
