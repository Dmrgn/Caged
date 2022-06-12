import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * <p>
 * This class contains code for the locations that can be teleported to via
 * Teleporters
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since June 6th, 2022
 * <p>
 * File was created by Daniel Morgan on June 6th. 20 minutes were spent
 * </p>
 */
public class TeleportLocation extends GameObject {
    /** Current index */
    int index;
    /** Node */
    Node node;
    /**
     * Constructor that inits variables
     * @param x X-coord
     * @param y Y-coord
     * @param index Index
     */
    public TeleportLocation(float x, float y, int index) {
        this.index = index;
        pos = new Vector(x, y);
        node = new ImageView(new Image("assets/teleporterDebug.png"));
    }
    /**
     * Update method
     */
    @Override
    public void update() {

    }
    /**
     * Getter for node
     * @return The node
     */
    @Override
    public Node getNode() {
        return node;
    }
}
