import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class Mold extends GameObject {
    Group node;
    Rectangle left, top, right, bottom;
    Image image;
    private static final int padding = 400;

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

    @Override
    public void draw() {
        left.getTransforms().clear();
        top.getTransforms().clear();
        right.getTransforms().clear();
        bottom.getTransforms().clear();

        Scale sca = new Scale();
        sca.setX(Game.ZOOM);
        sca.setY(Game.ZOOM);
        sca.setPivotX(Main.getDims().div(2).x);
        sca.setPivotY(Main.getDims().div(2).y);
        Translate trans = new Translate();
        trans.setX(Game.cameraPos.x);
        trans.setY(Game.cameraPos.y);
        left.getTransforms().addAll(sca, trans);
        top.getTransforms().addAll(sca, trans);
        right.getTransforms().addAll(sca, trans);
        bottom.getTransforms().addAll(sca, trans);
    }

    @Override
    public Node getNode() {
        return node;
    }
    
}
