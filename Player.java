
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;
/**
 * <p>
 * The player class contains state and behaviours for a player object.
 * The player object can be controled by the keyboard to perform actions such as
 * movement and slashing. Contains functionality for drawing the player.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 2.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 17th, 2022
 * <p>
 * File was created by Daniel Morgan on May 17th, 2022.
 * Daniel Morgan spent 30 minutes on this file on May 17th
 * Ryan Atlas spent 20 minutes on this file on May 20th, adding new comments and attributes
 * Ryan Atlas spent 2 hours on this file on May 25th and 26th adding comments, attributes
 * keyboard input and movement controls for JavaFX
 * </p>
 */

public class Player implements GameObject{
    /** The player's position stored as a Vector*/
    public Vector pos;
    /** The player's current hp*/
    private int hp;
    /** The player's movement speed which is a constant*/
    private static final int SPEED = 10;
    /** The player's jump height which is a constant*/
    private static final int JUMP_HEIGHT = 50;
    /** The player's Sprite as an Image*/
    public Image sprite;
    /** The Node that is added to the scene and whose movement is updated*/
    public Node player;
    /** Whether the player is jumping or not*/
    private boolean jump;
    /** Whether the player is moving left or not*/
    private boolean left;
    /** Whether the player is moving right or not*/
    private boolean right;
    /** Whether the player is dashing or not*/
    private boolean dash;
    /** Whether the player is interacting with an object or not*/
    private boolean interact;
    /** Whether the player is grounded or not*/
    private boolean grounded;
    /** Current scene*/
    private Scene scene;
    /**
     * The Player class constructor which takes in two floats
     * in order to initialize the position vector and sets all other
     * variables to their default. The constructor also initializes
     * the scene and EventHandlers for KeyEvents
     * @param x The player's x coordinate
     * @param y The player's y coordinate
     */
    public Player(float x, float y) {
        this.pos = new Vector(x, y);
        hp = 100;
        jump = false;
        dash = false;
        interact = false;
        grounded = true;
        right = false;
        left = false;
        sprite = new Image("Sprite.png");
        player = new ImageView(sprite);
        Group p = new Group(player);
        scene = new Scene(p, 1280, 720, Color.WHITE);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.D){
                    right = true;
                }
                if (e.getCode() == KeyCode.A) {
                    left = true;
                }
                if (e.getCode() == KeyCode.W) {
                    jump = true;
                }
                if (e.getCode() == KeyCode.SHIFT) {
                    dash = true;
                }
                if (e.getCode() == KeyCode.E){
                    interact = true;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.D){
                    right = false;
                }
                if (e.getCode() == KeyCode.A) {
                    left = false;
                }
                if (e.getCode() == KeyCode.W) {
                    jump = false;
                }
                if (e.getCode() == KeyCode.SHIFT) {
                    dash = false;
                }
                if (e.getCode() == KeyCode.E){
                    interact = false;
                }
            }
        });
    }
    /**
     * Updates the player's position Vector based on keyboard input, implementation
     * of the method from the GameObject interface
     */
    public void update() {
        int dx = 0, dy = 0;
        if (right) {
            dx += SPEED/5;
        } else if (left) {
            dx -= SPEED/5;
        }
        if (dash) {
            dx *= 2;
        }
        pos.x += dx;
        if (!grounded) {
            dy = -1*JUMP_HEIGHT;
            grounded = true;
        }
        if (jump && grounded) {
            grounded = false;
            dy += JUMP_HEIGHT;
            Timer jumpTimer = new Timer();
            TimerTask endJump = new TimerTask() {
                @Override
                public void run() {
                    jump = false;
                }
            };
            jumpTimer.schedule(endJump, 700L);
        }
        pos.y -= dy;
    }
    /**
     * Draws the player on the screen, implementation of the method
     * from the GameObject interface
     */
    public void draw() {
        player.relocate(pos.x, pos.y);
    }
    /**
     * Returns the scene with the player on it so it may be used
     * in the Main class
     * @return The current scene
     */
    public Scene getScene() {
        return scene;
    }
}