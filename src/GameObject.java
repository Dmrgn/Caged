import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

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
    protected float scale = 1.0f;
    private Vector translation = new Vector(0,0);
    /**
     * Update method that updates where that object is drawn
     */
    public abstract void update();
    /**
     * Gets the Node representing the object
     */
    public abstract Node getNode();
    /**
     * Draw method that draws the object
     */
    public void draw() {
        getNode().relocate((pos.x)*scale, (pos.y)*scale);
    }
    /**
     * Clears all active transformations
     */
    public void clearTransformations() {
        getNode().getTransforms().clear();
    }
    /**
     * Scales this object around the specified origin
     * @param scale The decimal number to set the scale to
     * @param origin The origin for the scale transformation
     */
    public void setScale(float amount, Vector origin) {
        Scale trans = new Scale();
        scale = amount;
        trans.setX(scale);
        trans.setY(scale);
        trans.setPivotX(origin.x);
        trans.setPivotY(origin.y);
        getNode().getTransforms().add(trans);
    }
    /**
     * Translates this object around the specified origin
     * @param Translate The decimal number to set the Translate to
     * @param origin The origin for the Translate transformation
     */
    public void setTranslate(Vector amount) {
        Translate trans = new Translate();
        translation = amount;
        trans.setX(translation.x);
        trans.setY(translation.y);
        getNode().getTransforms().add(trans);
    }
}