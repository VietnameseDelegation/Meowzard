package Entity.Enemies;

import Entity.Projectile;

import java.util.LinkedList;

public interface IEnemyMoves {
   void move(int x, int y);
   void shootPattern(LinkedList<Projectile> projectiles);
}
