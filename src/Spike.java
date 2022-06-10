import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Spike extends CollidableObject {
    private static final int RANGE = 50;
    int activationX;
    Node node;
    public Spike(int x, int y, int activationX) {
        pos = new Vector(x, y);
        this.activationX = activationX;
        node = new ImageView(new Image("assets/spike.png"));
    }
    @Override
    public void update() {
        Player player = (Player)Game.firstInstanceOfClass(new Player(0, 0));
        if (player != null) {
            Vector playerBoxDims = player.getHitBox().p2.sub(player.getHitBox().p1);
            if (player.pos.add(playerBoxDims.div(2)).dist(new Vector(activationX, player.pos.y)) < ) {
                Game.navigateLevel(level, screen, teleporterLocationIndex);
            }
        }
    }
    @Override
    public Node getNode() {
        return node;
    }
}
