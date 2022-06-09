import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TeleportLocation extends GameObject {
    int index;
    Node node;
    public TeleportLocation(float x, float y, int index) {
        this.index = index;
        pos = new Vector(x, y);
        node = new ImageView(new Image("assets/teleporterDebug.png"));
    }
    @Override
    public void update() {

    }
    @Override
    public Node getNode() {
        return node;
    }
}
