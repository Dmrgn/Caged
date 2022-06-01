import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
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

public class Player extends CollidableObject implements GameObject {
    /** The dimensions of the player's hitbox in pixels */
    private static Vector HITBOX_SIZE = new Vector(30, 30);
    /** The player's movement speed which is a constant*/
    private static final float MAX_SPEED = 4;
    /** The player's acceleration speed which is a constant*/
    private static final float ACCELERATION = 0.3f;
    /** The player's jump height which is a constant*/
    private static final float JUMP_HEIGHT = 50.0f;
    /** The player's position stored as a Vector*/
    public Vector pos;
    /** The velocity stores as a Vector */
    public Vector vel;
    /** The player's Sprite as an Image*/
    public Image sprite;
    /** The Node that is added to the scene and whose movement is updated*/
    public Node player;
    /** The player's current hp*/
    private int hp;
    /** The direction the player is moving (1 right -1 left 0 idle) */
    private int moveDirection;
    /** Whether the player is interacting with an object or not*/
    private boolean interact;
    /** Whether the player is grounded or not*/
    private boolean isGrounded;
    /** Possible player states */
    private enum PlayerState {
        IDLE,
        DASHING,
        MOVING,
        DAMAGED
    }
    /** Current state of the player */
    private PlayerState state;
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
        state = PlayerState.IDLE;
        createHitBox(pos, pos.add(HITBOX_SIZE));
        interact = false;
        isGrounded = false;
        moveDirection = 0;
        sprite = new Image("assets/player.png");
        player = new ImageView(sprite);
    }
    /**
     * Attempts to change the player's state to the given state
     * @return The new state of the player
     */
    private PlayerState requestStateChange(PlayerState newState) {
        switch (state) {
            case IDLE:
                return state = newState;
            case DASHING:
                switch (newState) {
                    case IDLE:
                        return state = PlayerState.IDLE;
                    case DAMAGED:
                        return state = PlayerState.DAMAGED;
                }
                break;
            case MOVING:
                return state = newState;
            case DAMAGED:
                switch (newState) {
                    case IDLE:
                        return state = PlayerState.IDLE;
                }
                break;
        }
        System.out.println("Changing state to " + state);
        return state;
    }

    int thing = 2;
    /** 
     * Updates the player's position Vector based on keyboard input, implementation 
     * of the method from the GameObject interface
     */
    public void update() {
        // handle lateral movement keyboard input
        if (Keyboard.isKeyDown(KeyCode.D))
            moveDirection = 1;
        else if (Keyboard.isKeyDown(KeyCode.A))
            moveDirection = -1;
        else
            moveDirection = 0;
        // handle logic based on player state
        switch (state) {
            case IDLE: {
                // State logic
                // Handle exiting this state
                if (moveDirection != 0) requestStateChange(PlayerState.MOVING);
                break;
            }
            case DASHING: {

                break;
            }
            case MOVING: {
                // State logic
                vel = vel.add(new Vector(moveDirection, 0).mul(ACCELERATION));
                vel = vel.max(new Vector(MAX_SPEED, MAX_SPEED)).min(new Vector(-MAX_SPEED, -MAX_SPEED));

                

                // Handle exiting this state
                if (moveDirection == 0) requestStateChange(PlayerState.IDLE);
                break;
            }
            case DAMAGED: {

                break;
            }
        }

        // State independent logic
        vel = vel.add(new Vector(0, Game.GRAVITY));
        pos = pos.add(vel);
        // update hitbox for next frame
        createHitBox(pos, pos.add(HITBOX_SIZE));

        if (Game.touchingCollidable(this)) {
            pos.y = pos.y-(vel.y*1.5f);
            createHitBox(pos, pos.add(HITBOX_SIZE));
            vel.y = 0;
            if (Keyboard.isKeyDown(KeyCode.W)) {
                vel = vel.add(new Vector(0, JUMP_HEIGHT));
            }
        }

        Vector diff = Vector.sub(vel, vel.mul(0.9f));
        diff = diff.max(new Vector(ACCELERATION*0.9f, 0)).min(new Vector(-ACCELERATION*0.9f, 0));
        vel = vel.sub(diff);

        createHitBox(pos, pos.add(HITBOX_SIZE));
    }
    /**
     * Draws the player on the screen, implementation of the method
     * from the GameObject interface
     */
    public void draw() {
        player.relocate(pos.x, pos.y);
    }
    /**
     * Gets the node this player is attached to and returns it
     */
    public Node getNode() {
        return player;
    }
}