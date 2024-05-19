package Entity.Enemies;

import BattleField.BattleField;
import Coordination.Coords;
import Entity.Entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Enemy extends Entity implements IEnemyMoves {
    private int moveCounter = 0;
    private int shootCounter = 0;
    private static int SHOOTCOOLDOWN;
    private int index;
    private Coords[] destination;
    private BattleField battleField;
    private boolean arrivedToDestination = false;

    public void loadCoords(String filePath){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath)); //"res/Coords/Pattern.csv"
            String s;
            br.readLine();
            int index = 0;
            while ((s=br.readLine())!=null){
                int x = Integer.parseInt(s.split(",")[0]);
                int y = Integer.parseInt(s.split(",")[1]);
                destination[index] = new Coords(x,y);
                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
        if (this.x == x && this.y == y) {
            arrivedToDestination = true;
        }
    }
}
