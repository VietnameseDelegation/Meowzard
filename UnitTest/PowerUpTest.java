package Test;

import BattleField.BattleField;
import Entity.Player;
import Entity.PowerUp.*;
import UserInput.KeyInput;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PowerUpTest {
    KeyInput keyInput;
    BattleField battleField;
    Player player;
    ShootSpeedUp shootSpeedUp;

    @BeforeEach
    void setUp() {
            keyInput = new KeyInput();
         battleField = new BattleField(keyInput);
         player = new Player(keyInput,battleField);
         shootSpeedUp = new ShootSpeedUp(player, player.getX(), player.getY());
    }

    @Test
    void update() {
        shootSpeedUp.applyPowerUp();
        for (int i =0; i<=600;i++){
        assertEquals(2, player.getShootCooldown());
            shootSpeedUp.update();
    }
        assertEquals(4, player.getShootCooldown());
}
@Test
    void updatePowerUp(){
    battleField.getPowerUp().add(shootSpeedUp);
        battleField.updatePowerUp();
    assertTrue(battleField.getPowerUp().isEmpty());
}

}
