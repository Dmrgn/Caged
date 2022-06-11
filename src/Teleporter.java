import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * <p>
 * This class contains code for the Teleporters which switch between
 * screens and levels/
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
public class Teleporter extends GameObject {
    public static final float TELEPORT_DISTANCE = 30;
    public Level level;
    public int teleporterLocationIndex;
    public int screen;
    ImageView node;

    public Teleporter(int x, int y, int levelIndex, int screenIndex, int teleporterLocationIndex) {
        level = Game.getLevel(levelIndex);
        screen = screenIndex;
        this.teleporterLocationIndex = teleporterLocationIndex;
        pos = new Vector(x, y);
        Image image = new Image(Game.IS_DEBUG_MODE ? "assets/teleporterDebug.png" : "assets/teleporter.png");
        node = new ImageView(image);
    }

    @Override
    public void update() {
        Player player = (Player)Game.firstInstanceOfClass(new Player(0, 0));
        if (player != null) {
            Vector playerBoxDims = player.getHitBox().p2.sub(player.getHitBox().p1);
            if (player.pos.add(playerBoxDims.div(2)).dist(pos) < TELEPORT_DISTANCE) {
                Game.navigateLevel(level, screen, teleporterLocationIndex);
            }
        }
    }

    @Override
    public Node getNode() {
        return node;
    }
    
}
