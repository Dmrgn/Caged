import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * <p>
 * Class for horns that spawn enemies
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since June 9th, 2022
 * <p>
 * 20 minutes were spent by Daniel Morgan on this file June 9-10th
 * </p>
 */
public class Horn extends CollidableObject {
    /** Spawn rate constant*/
    private static final int SPAWN_RATE = 90;
    /** Direction facing*/
    int direction;
    /** Node*/
    Node node;
    /** Image*/
    Image img;
    /**
     * Constructor that inits variables
     * @param x X-coord
     * @param y Y-coord
     * @param direction Direction it is facing
     */
    public Horn(int x, int y, int direction) {
        pos = new Vector(x, y);
        this.direction = direction;
        node = new ImageView(img);
        img = new Image("assets/horn.png");
        createHitBox(pos, pos.add(new Vector((float)img.getWidth(), (float)img.getHeight())));
    }
    /** Frame count*/
    long frameCount = 0;
    /**
     * Update method to continue updating the horn so that enemies spawn
     */
    @Override
    public void update() {
        if (frameCount % SPAWN_RATE == 0) {
            if (direction == -1) { // going to left
                Vector spawnPos = pos.add(new Vector(-40, 20));
                Game.attachObject(new Enemy(spawnPos.x, spawnPos.y), Game.SceneLayer.FOREGROUND);
            } else { // going to right
                Vector spawnPos = pos.add(new Vector((float)img.getWidth()+40, 20));
                Game.attachObject(new Enemy(spawnPos.x, spawnPos.y), Game.SceneLayer.FOREGROUND);
            }
        }
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
