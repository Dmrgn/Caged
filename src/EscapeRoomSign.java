import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EscapeRoomSign extends GameObject implements Interactable{

    /** JavaFX node for the platform*/
    private Node node;
    /** Image for the sign's locked texture (Normal)*/
    private Image imageNormal;
    /** Image for the sign's locked texture (Use in range of the sign)*/
    private Image imageUsable;
    /** The text on the sign */
    private Image message;
    /** Normal position of the sign*/
    private Vector normal;

    public EscapeRoomSign(Image message, int x, int y, int answer)
    {
        imageNormal = new Image("assets/SignNormal.png");
        imageUsable = new Image("assets/SignOpen.png");
        this.message = message;
        node = new ImageView(imageNormal);
        pos = new Vector(x, y);
        normal = new Vector(x, y);
    }

    public void update() {
        
    }
    /**
     * Getter method for the Node
     * @return The Node
     */
    public Node getNode() {
        return node;
    }

    /**
     * Whether the player is in range
     * @param player Current player
     * @return True if in range
     */
    public boolean inRange(Player player) {
        return (Vector.dist(Game.player.pos, this.pos) < 100);
    }

    public void display() {

    }
}
