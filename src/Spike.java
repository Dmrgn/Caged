import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Spike extends CollidableObject {
    private static final float SPEED = 5;
    private static final int RANGE = 50; 
    Vector startingPos;
    int activationX;
    int state = 0;
    Node node;
    public Spike(int x, int y, int activationX) {
        pos = new Vector(x, y);
        startingPos = new Vector(x, y);
        this.activationX = activationX;
        node = new ImageView(new Image("assets/spike.png"));
    }
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
    @Override
    public Node getNode() {
        return node;
    }
}
