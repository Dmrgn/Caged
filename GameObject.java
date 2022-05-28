import javafx.scene.Node;

/**
 * <p>
 * This interface is used so that all game objects can appear on the screen
 * properly and be updated when needed.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 2.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 17th, 2022
 * <p>
 * Ten minutes were spent by Ryan Atlas on this file on May 19th, 2022.
 * One minute was spent by Daniel Morgan on May 27th, 2022 adding the getNode() method
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
   /**
    * Gets the Node representing the object
    */
   public Node getNode();
}