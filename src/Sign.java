import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.io.FileInputStream;
import java.security.Key;

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
    private ImageView message;

    private int answer = 0;

    private boolean questionSign;

    private boolean answeredCorrectly;

    /**
     * Class constructor that initializes variables and sets
     * the Node's texture to be the image specified
     * @param message the image of the message displayed when the sign is interacted with.
     * @param x The x coord of the platform
     * @param y The y coord of the platform
     */
    public Sign(Image message, int x, int y){
        imageNormal = new Image("assets/SignNormal.png");
        imageUsable = new Image("assets/SignOpen.png");
        this.message = new ImageView(message);
        node = new ImageView(imageNormal);
        pos = new Vector(x, y);
        questionSign = false;
    }
    /**
     * Class constructor that initializes variables and sets
     * the Node's texture to be the image specified
     * @param message the image of the message displayed when the sign is interacted with.
     * @param x The x coord of the platform
     * @param y The y coord of the platform
     * @param answer If this is a question
     */
    public Sign(Image message, int x, int y, Scene scene, int answer){
        imageNormal = new Image("assets/SignNormal.png");
        imageUsable = new Image("assets/SignOpen.png");
        node = new ImageView(imageNormal);
        pos = new Vector(x, y);
        this.message = new ImageView(message);
        this.answer = answer;
        answeredCorrectly = false;
        questionSign = true;
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
        if(!questionSign)
        {
            display();
        }
        else
        {
            displayQuestions();
        }
    }

    /**
     * Method to check whether the player is in range of the object
     * @param p The player
     * @return Whether or not the player is in range and therefore can interact with the sign
     */
    public boolean inRange(Player p) {
        return (Math.abs(p.getNode().getLayoutX() - node.getLayoutX()) < 200 && Math.abs(p.getNode().getLayoutY() - node.getLayoutY()) < 100);
    }

    /**
     * Displays the message on the sign for information signs
     */
    public void display(){
        if(Keyboard.isKeyDown(KeyCode.E)) {
            message.setX(150);
            message.setY(80);
            message.setFitHeight(650);
            message.setFitWidth(1000);
            message.setPreserveRatio(true);
        }
    }
    /**
     * Displays the message on the sign for question signs
     */
    public void displayQuestions()
    {
        int userAnswer = 0;
        boolean keyPressed = false;
        if(Keyboard.isKeyDown(KeyCode.E))
        {
            keyPressed = true;
        }
        if(keyPressed) {
            message.setX(150);
            message.setY(80);
            message.setFitHeight(650);
            message.setFitWidth(1000);
            message.setPreserveRatio(true);
            if(Keyboard.isKeyDown(KeyCode.DIGIT1))
            {
                userAnswer = 1;
            }
            else if(Keyboard.isKeyDown(KeyCode.DIGIT2))
            {
                userAnswer = 2;
            }
            else if(Keyboard.isKeyDown(KeyCode.DIGIT3))
            {
                userAnswer = 3;
            }
            else if(Keyboard.isKeyDown(KeyCode.DIGIT4))
            {
                userAnswer = 4;
            }

            if(userAnswer == answer && userAnswer != 0)
            {
                //tell upward it works
                answeredCorrectly = true;
                userAnswer = 0;
                keyPressed = false;
            }
            else
            {
                //tell upward it is false;
                answeredCorrectly = false;
                userAnswer = 0;
                keyPressed = false;
            }
        }
    }
    public boolean isAnsweredCorrectly()
    {
        return answeredCorrectly;
    }
}
