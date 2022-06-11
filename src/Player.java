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
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 17th, 2022
 * <p>
 * File was created by Daniel Morgan on May 17th, 2022.
 * Daniel Morgan spent 30 minutes on this file on May 17th
 * Ryan Atlas spent 20 minutes on this file on May 20th, adding new comments and attributes
 * Ryan Atlas spent 2 hours on this file on May 25th and 26th adding comments, attributes
 * keyboard input and movement controls for JavaFX
 * 3 hours were spent by Daniel Morgan fixing physics, adding hitboxes and states
 * </p>
 */

public class Player extends CollidableObject {
    /** How long the player is unable to move after being damaged */
    private static final int DAMAGE_STUDDER_DURATION = 60;
    /** The dimensions of the player's hitbox in pixels */
    private static Vector HITBOX_SIZE = new Vector(30, 30);
    /** The player's movement speed which is a constant*/
    private static final float MAX_SPEED = 2.5f;
    /** Duration of the player's dash in frames */
    private static final float DASH_DURATION = 60;
    /** The number of frames until the player can dash again */
    private static final int DASH_COOL_DOWN = 60;
    /** The player's acceleration speed which is a constant*/
    private static final float ACCELERATION = 0.10f;
    /** The player's jump height which is a constant*/
    private static final float JUMP_HEIGHT = 4.0f;
    /** The velocity stores as a Vector */
    public Vector vel;
    /** The player's Sprite as an Image */
    public Image sprite;
    /** The Node that is added to the scene and whose movement is updated */
    public Node player;
    /** The player's current hp*/
    public int hp;
    /** The number of frames the player has been dashing for */
    private float dashingFrames = 0;
    /** The number of frames the player has been dashing for */
    private float dashCoolDown = 0;
    /** The direction the player is dashing in */
    private int dashDirection = -1;
    /** The direction the player is moving (1 right -1 left 0 idle) */
    private int moveDirection;
    /** The direction the player is facing */
    private int facingDirection = 1;
    /** Whether the player is interacting with an object or not */
    private boolean interact;
    /** Whether the player is interacting with an object or not */
    private boolean hasDash = false;
    /** Whether the player is grounded or not */
    private boolean isGrounded;
    /** The bottom hitbox of the player */
    private HitBox lowerHitBox;
    /** The top hitbox of the player */
    private HitBox upperHitBox;
    /** Player's healthbar*/
    public Healthbar hpBar;
    public static boolean playerMoving;
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
        hpBar = new Healthbar(hp);
    }
    /**
     * Method to create hitbox from 2 vectors
     * @param pos1 Top left corner
     * @param pos2 Bottom right corner
     */
    @Override
    public void createHitBox(Vector pos1, Vector pos2) {
        hitbox = new HitBox(pos1.add(new Vector(0, 5)), pos2.sub(new Vector(0,5)));
        lowerHitBox = new HitBox(pos1.add(new Vector(5, HITBOX_SIZE.y/2)), pos2.add(new Vector(-5, 0)));
        upperHitBox = new HitBox(pos1.add(new Vector(5, 0)), pos2.sub(new Vector(5, HITBOX_SIZE.y/2)));
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
        return state;
    }
    /**
     * Damage this player with the specified amount
     * @return If damaging was successful
     */
    public boolean damage(int amount, Vector location) {
        hp -= amount;
        boolean result = requestStateChange(PlayerState.DAMAGED) == PlayerState.DAMAGED;
        if (result)
            vel = location.sub(pos).mul(-0.04f);
        return result;
    }
    /**
     * Heal this player with the specified amount
     */
    public void heal(int amount) {
        hp += amount;
        hp = Math.min(hp, 100);
    }
    /**
     * Returns whether the current state is damagable or not
     * @return boolean whether the current state is damagable or not
     */
    public boolean isDamagableState() {
        return (state != PlayerState.DASHING);
    }
    /**
     * Updates the player's position Vector based on keyboard input, implementation
     * of the method from the GameObject interface
     */
    private long damagedFrames = 0;
    public void update() {
        hpBar.update();
        if(playerMoving) {
            // handle lateral movement keyboard input
            if (Keyboard.isKeyDown(KeyCode.D))
                moveDirection = 1;
            else if (Keyboard.isKeyDown(KeyCode.A))
                moveDirection = -1;
            else
                moveDirection = 0;
            dashingFrames -= 0.3;
            // handle logic based on player state
            switch (state) {
                case IDLE: {
                    // State logic
                    // Handle exiting this state
                    if (moveDirection != 0) requestStateChange(PlayerState.MOVING);
                    break;
                }
                case DASHING: {
                    // State Logic
                    if (dashingFrames <= 0) {
                        // just began the dash
                        dashDirection = facingDirection;
                        dashCoolDown = DASH_COOL_DOWN;
                        hasDash = false;
                        dashingFrames = 0;
                    }

                    vel = new Vector(dashDirection * 5, vel.y * 0.8f);

                    // Handle exiting this state
                    dashingFrames += 2;
                    if (dashingFrames > DASH_DURATION) requestStateChange(PlayerState.IDLE);
                    break;
                }
                case MOVING: {
                    // State logic
                    vel = vel.add(new Vector(moveDirection, 0).mul(ACCELERATION));
                    vel = vel.max(new Vector(MAX_SPEED, MAX_SPEED * 5)).min(new Vector(-MAX_SPEED, -MAX_SPEED * 5));
                    if (moveDirection != 0)
                        facingDirection = moveDirection;

                    // Handle exiting this state
                    if (moveDirection == 0) requestStateChange(PlayerState.IDLE);
                    if (Keyboard.isKeyDown(KeyCode.SHIFT) && dashingFrames <= 0 && moveDirection != 0)
                        requestStateChange(PlayerState.DASHING);
                    break;
                }
                case DAMAGED: {
                    // handle state logic
                    damagedFrames++;
                    // Handle exiting this state
                    if (damagedFrames > DAMAGE_STUDDER_DURATION) {
                        requestStateChange(PlayerState.IDLE);
                        if (hp <= 0)
                            Game.navigateLevel(Game.getLevel(Game.levelNum-1), Game.getLevel(Game.levelNum).levelScreen, 0);
                    }
                    break;
                }
            }

            boolean jumped = false;
            boolean hasTouchedGround = false;
            // State independent logic
            vel = vel.add(new Vector(0, Game.GRAVITY));

            boolean collided = true;
            // Handle vertical collisions
            if (Game.touchingCollidable(this, lowerHitBox)) {
                // collided = true;
                hasTouchedGround = true;
                pos.y = pos.y - (vel.y * 1.5f);
                createHitBox(pos, pos.add(HITBOX_SIZE));
                vel.y = 0;
                if (Keyboard.isKeyDown(KeyCode.W)) {
                    vel = vel.add(new Vector(0, -JUMP_HEIGHT));
                    jumped = true;
                }
            }
            if (Game.touchingCollidable(this, upperHitBox)) {
                // collided = true;
                pos.y = pos.y + Math.abs(vel.y * 1.5f);
                createHitBox(pos, pos.add(HITBOX_SIZE));
                vel.y = 0;
            }
            // Handle lateral collisions
            if (Game.touchingCollidable(this) && collided) {
                pos.x = pos.x - (vel.x * 1.5f);
                createHitBox(pos, pos.add(HITBOX_SIZE));
                vel.x = vel.x * -0.5f;
            }

            pos = pos.add(vel);
            createHitBox(pos, pos.add(HITBOX_SIZE));

            // reduce velocity for next frame
            Vector diff = Vector.sub(vel, vel.mul((hasTouchedGround ? 0.2f : 0.2f)));
            diff = diff.max(new Vector(ACCELERATION * (hasTouchedGround ? 0.2f : 0.2f), 0)).min(new Vector(-ACCELERATION * (hasTouchedGround ? 0.2f : 0.2f), 0));
            vel = vel.sub(diff);

            // create hitbox for next frame
            createHitBox(pos, pos.add(HITBOX_SIZE));
        }
    }
    /**
     * Gets the node this player is attached to and returns it
     */
    public Node getNode() {
        return player;
    }
}