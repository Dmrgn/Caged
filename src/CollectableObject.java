import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
/**
 * <p>
 * This class contains data about the objects in the game that the
 * user has to collect
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since June 9th, 2022
 * <p>
 * File was created by Samuel Huang on June 9th, 2022. 30 minutes were spent
 * Ryan Atlas spent 20 minutes on June 9th adjusted the update() method
 * </p>
 */
public class CollectableObject extends GameObject implements Interactable {
    /** Object's node*/
    private Node node;
    /** Object's image when not in range*/
    private Image objectOut;
    /** Object's image in range*/
    private Image objectIn;
    /** Whether the object should be highlighted*/
    private boolean highlighted;
    /**
     * Initializes variables and stores the position in the GameObject's
     * pos vector
     * @param objectOutRange Image
     * @param objectInRange Image
     * @param x X pos
     * @param y Y pos
     */
    public CollectableObject(Image objectOutRange, Image objectInRange, int x, int y) {
        objectOut = objectOutRange;
        objectIn = objectInRange;
        pos = new Vector(x, y);
        node = new ImageView(objectOut);
        highlighted = false;
    }
    /**
     * Updates the item every frame
     */
    public void update() {
        if (!Game.objectFound) {
            if (!highlighted && inRange((Player) Game.player)) {
                ((ImageView) node).setImage(objectIn);
                highlighted = true;
            } else if (highlighted && !inRange((Player) Game.player)) {
                ((ImageView) node).setImage(objectOut);
                highlighted = false;
            }
            if (inRange((Player) Game.player) && Keyboard.isKeyDown(KeyCode.E)) {
                //make a boolean true signalling that the object is found.
                node.setVisible(false);
                Game.objectFound = true;
                Game.canOpenDoor = true;
            }
        } else {
            node.setVisible(false);
        }
    }
    /**
     * Getter for node
     * @return The node
     */
    public Node getNode() {
        return node;
    }
    /**
     * Checks whether the player is in range to pick up the object
     * @param p The player
     * @return Whether they are in range
     */
    public boolean inRange(Player p) {
        return (Math.abs(p.getNode().getLayoutX() - node.getLayoutX()) < 75 && Math.abs(p.getNode().getLayoutY() - node.getLayoutY()) < 75);
    }
    /**
     * Display method
     */
    public void display() {

    }
}
