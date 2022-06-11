import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
/**
 * <p>
 * This class is used for level molds and backgrounds
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since June 1st, 2022
 * <p>
 * File was created by Daniel Morgan on June 1st, 30 minutes were spent
 * </p>
 */
public class Mold extends GameObject {
    /** Node*/
    Group node;
    /** Rectangles for different parts of the mold*/
    Rectangle left, top, right, bottom;
    /** Mold's image*/
    Image image;
    /** Padding around parts of the mold*/
    private static final int padding = 400;
    /**
     * Mold constructor that inits variables
     * @param img Image for mold
     * @param x X-position
     * @param y Y-position
     */
    public Mold(Image img, int x, int y) {
        image = img;
        pos = new Vector(x, y);
        left = new Rectangle();
        left.setFill(Color.BLACK);
        top = new Rectangle();
        top.setFill(Color.BLACK);
        right = new Rectangle();
        right.setFill(Color.BLACK);
        bottom = new Rectangle();
        bottom.setFill(Color.BLACK);
        node = new Group(bottom, left, right, top, new ImageView(image));
    }
    /**
     * Update method to update what is visible
     */
    @Override
    public void update() {
        double w = image.getWidth(), h = image.getHeight();
        // left curtain
        left.setX(pos.x - padding);
        left.setY(pos.y - padding);
        left.setWidth(padding);
        left.setHeight(h + padding * 2);
        // top curtain
        top.setX(pos.x - padding);
        top.setY(pos.y - padding);
        top.setWidth(w + padding * 2);
        top.setHeight(padding);
        // right curtain
        right.setX(pos.x + w);
        right.setY(pos.y - padding);
        right.setWidth(padding);
        right.setHeight(h + padding * 2);
        // bottom curtain
        bottom.setX(pos.x - padding);
        bottom.setY(pos.y + h);
        bottom.setWidth(w + padding * 2);
        bottom.setHeight(padding);
    }
    /**
     * Draw method
     */
    @Override
    public void draw() {

    }
    /**
     * Getter for node
     * @return The current node
     */
    @Override
    public Node getNode() {
        return node;
    }
    
}
