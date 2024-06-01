package Test;

import BattleField.BattleField;
import BattleField.WavesOfEnemies;
import Entity.Enemies.Enemy;
import Entity.Enemies.Ghost;
import UserInput.KeyInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class WavesOfEnemiesTest {
    KeyInput keyInput;
    WavesOfEnemies wavesOfEnemies;
    BattleField battleField;
    @BeforeEach
    void setUp() {
        keyInput = new KeyInput();
        wavesOfEnemies = new WavesOfEnemies();
        battleField = new BattleField(keyInput);
    }
    @Test
    void loadWave() {
        LinkedList<Enemy> e = wavesOfEnemies.loadWave("res/waves/wave1.csv",battleField);
        assertNotNull(e);
        assertEquals(1, e.size());
    }
    @Test
    void loadAllWaves() {
        ArrayList<LinkedList<Enemy>> e = battleField.preloadWaves();
        LinkedList<Enemy> e1 = wavesOfEnemies.loadWave("res/waves/wave1.csv",battleField);
        assertNotNull(e);
        assertEquals(2, e.size());
        assertEquals(e.get(1).size(), e1.size());
    }
    @Test
    void hurt(){
        battleField.getEnemies().add(new Ghost(battleField,1,1,1,1,1,"res/Coords/Pattern.csv",1));
        battleField.getEnemies().get(4).hurt();
        battleField.updateEnemies(new ArrayList<>());
        assertEquals(4, battleField.getEnemies().size());
    }
}