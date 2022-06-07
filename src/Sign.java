import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.image.Image;
/**
 * <p>
 * This class contains data about the interactable signs in the game that help
 * teach and quiz the player
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 3.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 27th, 2022
 * <p>
 * File was created by Ryan Atlas on May 27th, 2022. 20 minutes spent. Comments were added, variables created,
 * the constructor was made and the getNode(), draw(), inRange(), display() and update() methods were added
 * </p>
 */
public class Sign extends GameObject implements Interactable {
    /** JavaFX node for the platform*/
    private Node node;
    /** Image for the sign's locked texture (Normal)*/
    private Image imageNormal;
    /** Image for the sign's locked texture (Use in range of the sign)*/
    private Image imageUsable;
    /** The Vector for the sign's position*/
    private Vector pos;
    /** The text on the sign */
    private String message;
    /**
     * Class constructor that initializes variables and sets
     * the Node's texture to be the image specified
     * @param image
     * @param x The x coord of the platform
     * @param y The y coord of the platform
     */
    public Sign(int x, int y, String message){
        imageNormal = new Image("assets/SignNormal.png");
        imageUsable = new Image("assets/SignOpen.png");
        node = new ImageView(imageNormal);
        pos = new Vector(x, y);
        this.message = message;
    }
    /**
     * Getter method for the Node
     * @return The Node
     */
    public Node getNode() {
        return node;
    }
    /**
     * Overridden update method from GameObject
     */
    public void update() {
        node = new ImageView(imageUsable);
    }

    /**
     * Method to check whether the player is in range of the object
     * @param p The player
     * @return Whether or not the player is in range and therefore can interact with the sign
     */
    public boolean inRange(Player p) {
        if (Math.abs(p.getNode().getLayoutX() - node.getLayoutX()) < 10 && Math.abs(p.getNode().getLayoutY() - node.getLayoutY()) < 5)
        {
            update();
            return true;
        }
        node = new ImageView(imageNormal);
        return false;
    }

    /**
     * Displays the message on the sign
     */
    public void display(){

    }
}