package Entity.Enemies;

import Entity.Projectile;

import java.util.LinkedList;
/**
 * Interface of the design pattern Strategy
 */
public interface IEnemyMoves {
   void move(int x, int y);
   void shoot(LinkedList<Projectile> projectiles);
}
