public class Collision {
   public Vector leftBounds;
   public Vector rightBounds;
   public Collision(int x1, int y1, int x2, int y2) {
      leftBounds = new Vector(x1, y1);
      rightBounds = new Vector(x2, y2);
   }
   public boolean inBounds(Player player) {
      return player.pos.x > leftBounds.x && player.pos.x < rightBounds.x && player.pos.y < leftBounds.y && player.pos.y > rightBounds.y;
   }
}