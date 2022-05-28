import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.image.*;
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

public class Player implements GameObject {
    /** The player's position stored as a Vector*/
    public Vector pos;
    /** The velocity stores as a Vector */
    public Vector vel;
    /** The player's current hp*/
    private int hp;
    /** The player's movement speed which is a constant*/
    private static final float MAX_SPEED = 3;
    /** The player's acceleration speed which is a constant*/
    private static final float ACCELERATION = 0.15f;
    /** The player's jump height which is a constant*/
    private static final int JUMP_HEIGHT = 50;
    /** The player's Sprite as an Image*/
    public Image sprite;
    /** The Node that is added to the scene and whose movement is updated*/
    public Node player;
    /** Whether the player is jumping or not*/
    private boolean isJumping;
    /** The direction the player is moving (1 right -1 left 0 idle) */
    private int moveDirection;
    /** Whether the player is dashing or not*/
    private boolean isDashing;
    /** Whether the player is interacting with an object or not*/
    private boolean interact;
    /** Whether the player is grounded or not*/
    private boolean isGrounded;
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
        vel = new Vector(0, 0);
        hp = 100;
        isJumping = false;
        isDashing = false;
        interact = false;
        isGrounded = true;
        moveDirection = 0;
        sprite = new Image("assets/player.png");
        player = new ImageView(sprite);
    }
    /** 
     * Updates the player's position Vector based on keyboard input, implementation 
     * of the method from the GameObject interface
     */
    public void update() {
        handleInput();
        vel = vel.add(new Vector(moveDirection, 0).mul(ACCELERATION));
        vel = vel.max(new Vector(MAX_SPEED, 0)).min(new Vector(-MAX_SPEED, 0));
        if (isDashing) {
            vel = vel.mul(1.5f);
        }
        pos = pos.add(vel);
        Vector diff = Vector.sub(vel, vel.mul(0.9f));
        diff = diff.max(new Vector(ACCELERATION*0.9f, 0)).min(new Vector(-ACCELERATION*0.9f, 0));
        vel = vel.sub(diff);
        System.out.println(vel);
        // if (!isGrounded) {
        //     dy = -1 * JUMP_HEIGHT;
        //     isGrounded = true;
        // }
        // if (isJumping && isGrounded) {
        //     isGrounded = false;
        //     dy += JUMP_HEIGHT;
        //     Timer jumpTimer = new Timer();
        //     TimerTask endJump = new TimerTask() {
        //         @Override
        //         public void run() {
        //             isJumping = false;
        //         }
        //     };
        //     jumpTimer.schedule(endJump, 700L);
        // }
        // pos.y -= dy;
    }
    /**
     * Draws the player on the screen, implementation of the method
     * from the GameObject interface
     */
    public void draw() {
        player.relocate(pos.x, pos.y);
    }
    /**
     * Handles the players interaction with keyboard input
     */
    private void handleInput() {
        if (Keyboard.isKeyDown(KeyCode.D)) {
            moveDirection = 1;
        } else if (Keyboard.isKeyDown(KeyCode.A)) {
            moveDirection = -1;
        } else {
            moveDirection = 0;
        }
        if (Keyboard.isKeyDown(KeyCode.W)) {
            isJumping = true;
        } else {
            isJumping = false;
        }
        if (Keyboard.isKeyDown(KeyCode.SHIFT)) {
            isDashing = true;
        } else {
            isDashing = false;
        }
        if (Keyboard.isKeyDown(KeyCode.E)) {
            interact = true;
        } else {
            interact = false;
        }
    }
    /**
     * Gets the node this player is attached to and returns it
     */
    public Node getNode() {
        return player;
    }
}