import javafx.scene.Node;

/**
 * <p>
 * This interface is used so that all game objects can appear on the screen
 * properly and be updated when needed.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 3.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 17th, 2022
 * <p>
 * Ten minutes were spent by Ryan Atlas on this file on May 19th, 2022.
 * Ten minutes were spent by Ryan Atlas on June 1st restructuring it to be an abstract class with the pos variable
 * </p>
 */
public abstract class GameObject {
    public Vector pos;
    /**
     * Draw method that draws the object
     */
    public abstract void draw();
    /**
     * Update method that updates where that object is drawn
     */
    public abstract void update();
    /**
     * Gets the Node representing the object
     */
    public abstract Node getNode();
}