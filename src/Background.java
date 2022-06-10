import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * <p>
 * This class is used for the game backgrounds
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 4.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since June 8th, 2022
 * <p>
 * File was created by Daniel Morgan on June 8th, 20 minutes were spent.
 * </p>
 */
public class Background extends GameObject {
    /** Node for the background*/
    private ImageView node;

    /**
     * Constructor. Initializes variables
     * @param image Image for the background
     */
    public Background(Image image) {
        node = new ImageView(image);
        pos = new Vector((float)-image.getWidth()/10, 0);
    }

    /**
     * Overridden update method from game that all GameObjects need. Background doesn't
     * change but still needs this method.
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
