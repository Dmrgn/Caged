import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
/**
 * <p>
 * This class contains data about the objects in the game that the
 * user has to collect
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 3.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since June 9th, 2022
 * <p>
 * File was created by Samuel Huang on June 9th, 2022. 30 minutes were spent
 * </p>
 */
public class CollectableObject extends GameObject implements Interactable
{

    private Node node;
    private boolean appear;
    private Image object;

    private Image emptyImage;

    public CollectableObject(Image objectImage, int x, int y)
    {
        object = objectImage;

        pos = new Vector(x, y);
        node = new ImageView(object);
        //emptyImage = new Image("assets/");
        appear = true;
    }

    public void update() {

        node = new ImageView((appear) ? object : emptyImage);
        if(Keyboard.isKeyDown(KeyCode.E))
        {
            //make a boolean true signalling that the object is found.
            appear = false;
        }
    }

    public Node getNode() {
        return node;
    }

    public boolean inRange(Player p) {
        return (Math.abs(p.getNode().getLayoutX() - node.getLayoutX()) < 75 && Math.abs(p.getNode().getLayoutY() - node.getLayoutY()) < 75);
    }

    public void display() {

    }
}
