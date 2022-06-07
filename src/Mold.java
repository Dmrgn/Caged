import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Mold extends GameObject {
    ImageView node;

    public Mold(Image image, int x, int y) {
        node = new ImageView(image);
        pos = new Vector(x, y);
    }

    @Override
    public void update() {

    }

    @Override
    public Node getNode() {
        return node;
    }
    
}
