package BattleField;

import Coordination.Coords;
import Entity.Enemies.Enemy;
import Entity.Entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class WavesOfEnemies {

    public LinkedList<Entity> loadWave(String filePath,BattleField battleField) {
        LinkedList<Entity> wave = new LinkedList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath)); //"res/Coords/Pattern.csv"
            String s;
            br.readLine();
            while ((s=br.readLine())!=null){
                String[] tokens = s.split(",");
                String choice = tokens[0];
                int x = Integer.parseInt(tokens[1]);
                int y = Integer.parseInt(tokens[2]);
                int speed = Integer.parseInt(tokens[3]);
                int shootCooldown = Integer.parseInt(tokens[4]);
                int health = Integer.parseInt(tokens[5]);
                wave.add(Enemy.createEnemy(choice,battleField,x,y,speed,shootCooldown,health));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return wave;
    }
}
