package Entity.PowerUp;

import Entity.Player;

public class ProjectileSizeUp extends PowerUp {
    public ProjectileSizeUp(Player player, int x, int y) {
        super(player, x, y);
        loadPng("bulletSizeUp.png");
    }
    @Override
    public void applyPowerUp() {
        player.increaseProjectileScale();
    }

    @Override
    public void removePowerUp() {
        player.decreaseProjectileScale();
    }
}
