import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Background extends GameObject {
    ImageView node;

    public Background(Image image) {
        node = new ImageView(image);
        pos = new Vector((float)-image.getWidth()/10, 0);
    }

    @Override
    public void update() {
        
    }

    // @Override
    // public void draw() {
    //     clearTransformations();
    //     node.relocate(pos.x, pos.y);
    // }

    @Override
    public Node getNode() {
        return node;
    }
    
}
