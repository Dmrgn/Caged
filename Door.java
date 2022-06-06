import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.image.Image;
/**
 * <p>
 * This class contains data about the interactable doors in the game
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 3.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 27th, 2022
 * <p>
 * File was created by Ryan Atlas on May 27th, 2022. 20 minutes spent. Comments were added, variables created,
 * the constructor was made and the getNode(), draw(), inRange(), display(), openDoor() and update() methods were added
 * Ten minutes were spent by Ryan Atlas on June 2nd restructuring the file to work with the new GameObject
 * </p>
 */
public class Door extends GameObject implements Interactable {
    /** JavaFX node for the platform*/
    private Node node;
    /** Image for the door's locked texture */
    private Image imageLocked;
    /** Image for the door's locked texture */
    private Image imageOpen;
    /** The Vector for the door's position*/
    private Vector pos;
    /** Whether the door is locked */
    private boolean isLocked;
    /**
     * Class constructor that initializes variables and sets
     * the Node's texture to be the image specified
     * @param imageFileLocked The file for the image of the door while locked
     * @param imageFileOpen  The file for the image of the door while open
     * @param x The x coord of the platform
     * @param y The y coord of the platform
     */
    public Door(String imageFileLocked, String imageFileOpen, int x, int y){
        imageLocked = new Image(imageFileLocked);
        imageOpen = new Image(imageFileOpen);
        node = new ImageView((isLocked) ? imageLocked : imageOpen);
        pos = new Vector(x, y);
    }
    /**
     * Getter method for the Node
     * @return The Node
     */
    public Node getNode() {
        return node;
    }
    /**
     * Overridden draw method from GameObject that draws the object at its position
     */
    public void draw() {
        node.relocate(pos.x, pos.y);
    }
    /**
     * Overridden update method from GameObject
     */
    public void update() {
        node = new ImageView((isLocked) ? imageLocked : imageOpen);
    }

    /**
     * Method to check whether the player is in range of the object
     * @param p The player
     * @return Whether or not the player is in range and therefore can interact with the door
     */
    public boolean inRange(Player p){
        return false;
    }
    /**
     * Opens the door
     */
    public void openDoor() {
        isLocked = false;
    }

    /**
     * Overridden method from Interactable, displays a message if
     * the door is locked or when the door is able to be opened
     */
    public void display(){
        if (isLocked){

        }
    }
}
