import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Teleporter extends GameObject {
    public static final float TELEPORT_DISTANCE = 100;
    public Level level;
    public int screen;
    ImageView node;

    public Teleporter(int x, int y, int levelIndex, int screenIndex) {
        level = Game.getLevel(levelIndex);
        screen = screenIndex;
        pos = new Vector(x, y);
        Image image = new Image("assets/player.png");
        node = new ImageView(image);
    }

    @Override
    public void update() {
        Player player = (Player)Game.firstInstanceOfClass(new Player(0,0));
        if (player != null) {
            if (player.pos.dist(pos) < 100) {
                System.out.println("here:" + player.pos.dist(pos));
                Game.navigateLevel(level, screen);
            }
        }
    }

    @Override
    public Node getNode() {
        return node;
    }
    
}
