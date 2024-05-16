package Entity;

import java.util.LinkedList;

public interface IEnemyMoves {
   void move(int x, int y);
   void shootPattern(LinkedList<Projectile> projectiles);
}
