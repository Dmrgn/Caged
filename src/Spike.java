import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * <p>
 * The class for the spike in level 1
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since June 6th, 2022
 * <p>
 * File was created by Daniel Morgan on June 6th, 30 minutes were spent
 * </p>
 */
public class Spike extends CollidableObject {
    /** Range constant*/
    private static final int RANGE = 50;
    /** Speed of the spike */
    private static final float SPEED = 5;
    /** Starting position vector*/
    Vector startingPos;
    /** Activation x coord*/
    int activationX;
    /** State of the spike*/
    int state = 0;
    /** Node*/
    Node node;

    /**
     * Spike class constructor that inits variables
     * @param x X-coord of the spike
     * @param y Y-coord of the spike
     * @param activationX Activation x-coord
     */
    public Spike(int x, int y, int activationX) {
        pos = new Vector(x, y);
        startingPos = new Vector(x, y);
        this.activationX = activationX;
        node = new ImageView(new Image("assets/spike.png"));
    }
    /**
     * Update method to update the spike as it falls
     */
    @Override
    public void update() {
        createHitBox(pos, pos.add(new Vector(35, 62)));
        if (state == 0) { // before falling
            Player player = (Player)Game.firstInstanceOfClass(new Player(0, 0));
            if (player != null) {
                Vector playerBoxDims = player.getHitBox().p2.sub(player.getHitBox().p1);
                if (player.pos.add(playerBoxDims.div(2)).dist(new Vector(activationX, player.pos.y)) < RANGE) {
                    state = 1; // start falling
                }
            }
        } else if (state == 1) { // during falling
            pos = pos.add(new Vector(0, SPEED));
            if (Game.touchingCollidable(this) && startingPos.dist(pos) > 100) {
                state = 2; // finish falling
            }
        }
    }
    /**
     * Getter for the Node
     * @return The node
     */
    @Override
    public Node getNode() {
        return node;
    }
}
