import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
/**
 * <p>
 * This class contains data about the interactable doors in the game
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 4.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 27th, 2022
 * <p>
 * File was created by Ryan Atlas on May 27th, 2022. 20 minutes spent. Comments were added, variables created,
 * the constructor was made and the getNode(), draw(), inRange(), display(), openDoor() and update() methods were added
 * Ten minutes were spent by Ryan Atlas on June 2nd restructuring the file to work with the new GameObject
 * </p>
 */
public class Door extends CollidableObject implements Interactable {
    /** JavaFX node for the platform*/
    private Node node;
    /** Image for the door's locked texture */
    private Image imageLocked;
    /** Image for the door's locked texture */
    private Image imageOpen;
    /** Whether the door is locked */
    private boolean isLocked;
    /** Image for the door's message */
    private Image doorMessage;
    /** Vector for the normal position of the door */
    private Vector normalPos;
    /** Whether the door is being accessed */
    private boolean accessing;

    private boolean displayTips = false;
    private boolean off = false;
    /**
     * Class constructor that initializes variables and sets
     * the Node's texture to be the image specified
     * @param imageFileLocked The file for the image of the door while locked
     * @param imageFileOpen  The file for the image of the door while open
     * @param x The x coord of the platform
     * @param y The y coord of the platform
     */
    public Door(Image imageFileLocked, Image imageFileOpen, Image doorInfo, int x, int y){
        isLocked = true;
        imageLocked = imageFileLocked;
        imageOpen = imageFileOpen;
        node = new ImageView((isLocked) ? imageLocked : imageOpen);
        pos = new Vector(x, y);
        normalPos = new Vector(x, y);
        doorMessage = doorInfo;
        createHitBox(pos.add(new Vector(0, 0)), pos.add(new Vector((float)imageFileLocked.getWidth(), (float)imageFileLocked.getHeight())));
        accessing = false;
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
        if (inRange((Player)Game.player) && Keyboard.isKeyDown(KeyCode.E) && Game.canOpenDoor && Game.signsRead[0] && Game.signsRead[1] && Game.signsRead[2] && Game.signsRead[3]) {
            isLocked = false;
            ((ImageView)node).setImage(imageOpen);
            createHitBox(new Vector(0,0), new Vector(0,0));
        } else if (!isLocked && !displayTips){
            ((ImageView)node).setImage(imageOpen);
            displayTips = true;
        } else {
            ((ImageView)node).setImage(imageLocked);
        }
        if (isLocked && inRange((Player)Game.player)&&Keyboard.isKeyDown(KeyCode.E)){
            accessing = true;
            Player.playerMoving = false;
        } else if(isLocked && Keyboard.isKeyUp(KeyCode.E)) {
            accessing = false;
            Player.playerMoving = true;
        }
        if(isLocked) {
            display();
        }
        else {
            displayBrotherTips();
        }
    }
    /**
     * Method to check whether the player is in range of the object
     * @param p The player
     * @return Whether or not the player is in range and therefore can interact with the door
     */
    public boolean inRange(Player p){
        return (Math.abs(p.getNode().getLayoutX() - node.getLayoutX()) < 200 && Math.abs(p.getNode().getLayoutY() - node.getLayoutY()) < 200);
    }
    /**
     * Overridden method from Interactable, displays a message if
     * the door is locked or when the door is able to be opened
     */
    public void display(){
        if(accessing) {
            ((ImageView) node).setImage(doorMessage);
            pos = Game.toWorld(new Vector(325, 175));
            Game.player.getNode().setVisible(false);
        }
        else {
            ((ImageView)node).setImage(imageLocked);
            pos = normalPos;
            Game.player.getNode().setVisible(true);
        }
    }
    public void displayBrotherTips()
    {
        if(Keyboard.isKeyDown(KeyCode.H))
        {
            displayTips = false;
            off = true;
        }
        if(displayTips && !off) {
            ((ImageView) node).setImage(new Image("assets/BrotherTips Screens/BrotherTips1.png"));
            pos = Game.toWorld(new Vector(25, 20));
            Game.player.getNode().setVisible(false);
            Player.playerMoving = false;
        }
        else {
            ((ImageView)node).setImage(imageOpen);
            pos = normalPos;
            Game.player.getNode().setVisible(true);
            Player.playerMoving = true;
        }
    }

}
