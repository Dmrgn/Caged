import javafx.animation.PauseTransition;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.security.Key;
import java.util.Timer;
import java.util.TimerTask;

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
    /** The text on the sign */
    private Image message;
    private Vector normal;
    private boolean highlighted;
    private boolean accessing;
    private boolean answered;
    private int answer = 0;
    private int userAnswer;
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
        this.message = message;
        node = new ImageView(imageNormal);
        pos = new Vector(x, y);
        normal = new Vector(x, y);
        questionSign = false;
        answered = false;
        highlighted = false;
        accessing = false;
    }
    /**
     * Class constructor that initializes variables and sets
     * the Node's texture to be the image specified
     * @param message the image of the message displayed when the sign is interacted with.
     * @param x The x coord of the platform
     * @param y The y coord of the platform
     * @param answer If this is a question
     */
    public Sign(Image message, int x, int y, int answer){
        imageNormal = new Image("assets/SignNormal.png");
        imageUsable = new Image("assets/SignOpen.png");
        node = new ImageView(imageNormal);
        pos = new Vector(x, y);
        normal = new Vector(x, y);
        this.message = message;
        this.answer = answer;
        answeredCorrectly = false;
        questionSign = true;
        accessing = false;
        highlighted = false;
        userAnswer = 0;
        answered = false;
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

        if(!questionSign) {
            if (!highlighted && inRange((Player)Game.player)) {
                ((ImageView)node).setImage(imageUsable);
                highlighted = true;
            } else if (highlighted && !inRange((Player)Game.player)){
                ((ImageView)node).setImage(imageNormal);
                highlighted = false;
            }
            display();
            if (accessing) {
                ((ImageView)node).setImage(message);
                pos = Game.toWorld(new Vector(160, 20));
                Game.player.getNode().setVisible(false);
            } else if (!highlighted){
                ((ImageView)node).setImage(imageNormal);
                pos = normal;
                Game.player.getNode().setVisible(true);
            } else {
                ((ImageView)node).setImage(imageUsable);
                pos = normal;
                Game.player.getNode().setVisible(true);
            }
        }
        else {
            if (!highlighted && inRange((Player)Game.player)) {
                ((ImageView)node).setImage(imageUsable);
                highlighted = true;
            } else if (highlighted && !inRange((Player)Game.player)){
                ((ImageView)node).setImage(imageNormal);
                highlighted = false;
            }
            display();
            if (accessing && !answeredCorrectly) {
                ((ImageView)node).setImage(message);
                displayQuestions();
                pos = Game.toWorld(new Vector(160, 20));
                Game.player.getNode().setVisible(false);
            } else {
                pos = normal;
                Game.player.getNode().setVisible(true);
            }
        }
    }

    /**
     * Method to check whether the player is in range of the object
     * @param p The player
     * @return Whether or not the player is in range and therefore can interact with the sign
     */
    public boolean inRange(Player p) {
        return (Math.abs(p.getNode().getLayoutX() - node.getLayoutX()) < 100 && Math.abs(p.getNode().getLayoutY() - node.getLayoutY()) < 100);
    }

    /**
     * Displays the message on the sign for information signs
     */
    public void display(){
        if (inRange((Player)Game.player) && Keyboard.isKeyDown(KeyCode.E)) {
                accessing = true;

//                ((ImageView)node).setX(150);
//                ((ImageView)node).setY(80);
//                ((ImageView)node).setFitHeight(650);
//                ((ImageView)node).setFitWidth(1000);
//                ((ImageView)node).setPreserveRatio(true);
        }

        else if(Keyboard.isKeyUp(KeyCode.E))
        {
            accessing = false;
        }
    }
    /**
     * Displays the message on the sign for question signs
     */
    public void displayQuestions() {
//        ((ImageView)node).setImage(message);
//        ((ImageView)node).setX(150);
//        ((ImageView)node).setY(80);
//        ((ImageView)node).setFitHeight(650);
//        ((ImageView)node).setFitWidth(1000);
//        ((ImageView)node).setPreserveRatio(true);
        if (!answered) {
            if (Keyboard.isKeyDown(KeyCode.DIGIT1)) {
                userAnswer = 1;
                answered = true;
            } else if (Keyboard.isKeyDown(KeyCode.DIGIT2)) {
                userAnswer = 2;
                answered = true;
            } else if (Keyboard.isKeyDown(KeyCode.DIGIT3)) {
                userAnswer = 3;
                answered = true;
            } else if (Keyboard.isKeyDown(KeyCode.DIGIT4)) {
                userAnswer = 4;
                answered = true;
            }
        }
        if (answered) {
            if (userAnswer == answer && userAnswer != 0) {
                //tell upward it works
                answeredCorrectly = true;
                Game.questionsCorrect++;
            } else {
                //tell upward it is false;
                answeredCorrectly = false;
            }
            accessing = false;
            System.out.println(userAnswer);
            System.out.println(answeredCorrectly);
            answered = false;
            userAnswer = 0;

        }
    }
    /**
     * Displays whether or not the user got the answer correct
     */
    public void messageBox(boolean correct)
    {
        Image correctBox = new Image("assets/signs/Correct.png");
        if(correct) {
            ((ImageView)node).setImage(correctBox);
            Timer myTimer = new Timer();
            myTimer.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    node.setVisible(false);
                }
                }, 1500);
        }
        else {

        }
    }
}
