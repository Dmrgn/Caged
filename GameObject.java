/**
 * <p>
 * This interface is used so that all game objects can appear on the screen
 * properly and be updated when needed.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 1.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 17th, 2022
 * <p>
 * Ten minutes were spent by Ryan Atlas on this file on May 19th, 2022.
 * </p>
 */
public interface GameObject {
   /**
   * Draw method that draws the object
   */
   public void draw();
   /**
   * Update method that updates where that object is drawn
   */
   public void update();
}