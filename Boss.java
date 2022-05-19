/**
 * <p>
 * This class acts as a generic template for the two bosses in the game
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 1.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 19th, 2022
 * <p>
 * Ten minutes were spent by Ryan Atlas on this file on May 19th, 2022.
 * </p>
 */
public abstract class Boss implements GameObject {
   /** The boss's hp*/
   protected int hp;
   /**
   * Boss class constructor to be used by subclasses in their constructors
   */
   public Boss (int hp) {
      this.hp = hp;
   }
   /**
   * Draw method from the interface GameObject that is to be overridden by the subclasses
   */
   public void draw(){
   }
   /**
   * Update method from the interface GameObject that is to be overridden by the subclasses
   */
   public void update(){
   }
}