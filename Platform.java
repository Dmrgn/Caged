import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
/**
 * <p>
 * This class contains data about the platforms and also will make sure that all of the platforms
 * have collision detection so the player may stand on them.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 3.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 27th, 2022
 * <p>
 * File was created by Ryan Atlas on May 27th, 2022. Comments were added, variables created,
 * the constructor was made and the getNode(), draw() and update() methods were added 
 * </p>
 */
public class Platform extends CollidableObject {
    /** JavaFX node for the platform*/
    private Node node;
    /** Image for the platform's texture */
    private Image image;
    /** If this platform is flipped */
    private boolean flipped;
    /**
     * Class constructor that initializes variables and sets
     * the Node's texture to be the image specified
     * @param imageFile The file for the image
     * @param x The x coord of the platform
     * @param y The y coord of the platform
     */
    public Platform(Image imageFile, boolean flipped, int x, int y) {
        this.flipped = flipped;
        image = imageFile;
        node = new ImageView(image);
        if (flipped) {
            node.setRotationAxis(Rotate.Y_AXIS);
            node.setRotate(180);
        }
        pos = new Vector(x, y);
        createHitBox(pos.add(new Vector(0,0)), pos.add(new Vector((float)image.getWidth(), (float)image.getHeight())));
    }
    /**
     * Getter method for the Node 
     * @return the {@link Node} representing this platform
     */
    public Node getNode() {
        return node;
    }
    /**
     * Implementation of the update method from GameObject
     */
    public void update() {

    }
    /**
     * Implementation of the draw method from GameObject that draws the object at its position
     */
    public void draw() {
        node.relocate(pos.x, pos.y);
    }
}