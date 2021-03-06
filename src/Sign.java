import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

/**
 * <p>
 * This class contains data about the interactable signs in the game that help
 * teach and quiz the player
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
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
    /** Normal position of the sign*/
    private Vector normal;
    /** Whether the sign is highlighted*/
    private boolean highlighted;
    /**Whether the player is accessing the sign*/
    private boolean accessing;
    /** Whether the sign has been answered*/
    private boolean answered;
    /** Correct answer*/
    private int answer = 0;
    /** User's answer*/
    private int userAnswer;
    /** Whether the sign is question sign*/
    private boolean questionSign;
    /** Whether the sign has been answered correctly*/
    private boolean answeredCorrectly;
    /** Sign number */
    private int signNum;
    /**
     * Class constructor that initializes variables and sets
     * the Node's texture to be the image specified
     * @param message the image of the message displayed when the sign is interacted with.
     * @param x The x coord of the platform
     * @param y The y coord of the platform
     */
    public Sign(Image message, int x, int y, int signNum){
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
        this.signNum = signNum;
    }
    /**
     * Class constructor that initializes variables and sets
     * the Node's texture to be the image specified
     * @param message the image of the message displayed when the sign is interacted with.
     * @param x The x coord of the platform
     * @param y The y coord of the platform
     * @param answer If this is a question
     */
    public Sign(Image message, int x, int y, int answer, boolean question){
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
            if (!highlighted && (Vector.dist(Game.player.pos, this.pos) < 100)) {
                ((ImageView)node).setImage(imageUsable);
                highlighted = true;
            } else if (highlighted && !(Vector.dist(Game.player.pos, this.pos) < 100)){
                ((ImageView)node).setImage(imageNormal);
                highlighted = false;
            }
            display();
            if (accessing) {
                ((ImageView)node).setImage(message);
                pos = Game.toWorld(new Vector(20, 20));
                Game.player.getNode().setVisible(false);
                Enemy.canMove = false;
                for (GameObject obj : Game.gameObjects){
                    if (obj instanceof Enemy){
                        obj.getNode().setVisible(false);
                    }
                }
                //Game.player.getNode().setOpacity(0.0);
            } else if (!highlighted){
                ((ImageView)node).setImage(imageNormal);
                pos = normal;
                //Game.player.getNode().setVisible(true);
            } else {
                ((ImageView)node).setImage(imageUsable);
                pos = normal;
                Game.player.getNode().setVisible(true);
                Enemy.canMove = true;
                for (GameObject obj : Game.gameObjects){
                    if (obj instanceof Enemy){
                        obj.getNode().setVisible(true);
                    }
                }
            }
        }
        else {
            if (!highlighted && (Vector.dist(Game.player.pos, this.pos) < 100)) {
                ((ImageView)node).setImage(imageUsable);
                highlighted = true;
            } else if (highlighted && !(Vector.dist(Game.player.pos, this.pos) < 100)){
                ((ImageView)node).setImage(imageNormal);
                highlighted = false;
            }
            displayQuestions();
            if (accessing && !answeredCorrectly) {

                ((ImageView)node).setImage(message);
                checkQuestions();
                pos = Game.toWorld(new Vector(20, 20));

            } else {
                pos = normal;

            }
        }
    }

    /**
     * Redraws objects in correct positions
     */
    @Override
    public void draw() {
        clearTransformations();
        if (accessing && !answeredCorrectly) {
            setTranslate(Game.toWorld(new Vector(0,0)).mul(-1));
            getNode().relocate((pos.x), (pos.y));
        } else {
            setScale(Game.ZOOM, Main.getDims().div(2));
            setTranslate(Game.cameraPos);
            getNode().relocate((pos.x)*Game.ZOOM, (pos.y)*Game.ZOOM);
        }
    }

    /**
     * Whether the player is in range
     * @param player Current player
     * @return True if in range
     */
    public boolean inRange(Player player) {
        return (Vector.dist(Game.player.pos, this.pos) < 100);
    }
    /**
     * Displays the message on the sign for information signs (Level 1)
     */
    public void display(){
        if ((Vector.dist(Game.player.pos, this.pos) < 100) && Keyboard.isKeyDown(KeyCode.E)) {
                accessing = true;
                Enemy.canMove = false;
                for (GameObject obj : Game.gameObjects){
                    if (obj instanceof Enemy){
                        obj.getNode().setVisible(false);
                    }
                }
                Player.playerMoving = false;
                Game.signsRead[signNum] = true;
        }

        else if(Keyboard.isKeyUp(KeyCode.E))
        {
            accessing = false;
            Player.playerMoving = true;
            Enemy.canMove = true;
            for (GameObject obj : Game.gameObjects){
                if (obj instanceof Enemy){
                    obj.getNode().setVisible(true);
                }
            }
        }
    }
    /**
     * Displays the message on the sign for question signs (Level 2)
     */
    public void displayQuestions()
    {
        if ((Vector.dist(Game.player.pos, this.pos) < 100) && Keyboard.isKeyDown(KeyCode.E) && !answeredCorrectly) {
            accessing = true;
            Player.playerMoving = false;
            Game.player.getNode().setVisible(false);
            Enemy.canMove = false;
            for (GameObject obj : Game.gameObjects){
                if (obj instanceof Enemy){
                    obj.getNode().setVisible(false);
                }
            }
        }
    }
    /**
     * Displays the message on the sign for question signs
     */
    public void checkQuestions() {
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
                messageBox(true);
            } else {
                //tell upward it is false;
                messageBox(false);
            }
        }
    }
    /**
     * Displays whether or not the user got the answer correct
     */
    public void messageBox(boolean correct) {
        Image correctBox = new Image("assets/signs/Correct.png");
        Image incorrectBox = new Image("assets/signs/Incorrect.png");
        if(correct)
        {
            ((ImageView)node).setImage(correctBox);
        }
        else
        {
            ((ImageView)node).setImage(incorrectBox);
        }
        if(Keyboard.isKeyDown(KeyCode.H) && correct)
        {
            answeredCorrectly = true;
            Game.questionsCorrect++;
            accessing = false;
            answered = false;
            userAnswer = 0;
            Game.player.getNode().setVisible(true);
            Player.playerMoving = true;
            Enemy.canMove = true;
            for (GameObject obj : Game.gameObjects){
                if (obj instanceof Enemy){
                    obj.getNode().setVisible(true);
                }
            }
        }
        if(Keyboard.isKeyDown(KeyCode.H) && !correct)
        {
            answeredCorrectly = false;
            accessing = false;
            answered = false;
            userAnswer = 0;
            Game.player.getNode().setVisible(true);
            Player.playerMoving = true;
            Enemy.canMove = true;
            for (GameObject obj : Game.gameObjects){
                if (obj instanceof Enemy){
                    obj.getNode().setVisible(true);
                }
            }
        }
    }
}
