import javafx.scene.image.*;
import javafx.scene.*;
/**
 * <p>
 * This class contains the frameworks for the enemies in the game.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since June 6th, 2022
 * <p>
 * File was created by Ryan Atlas on June 6th. He spent 1.5 hours creating the frame for this class
 * </p>
 */
public class Enemy extends CollidableObject {
    private static final int DAMAGE_STUTTER_DURATION = 120;
    /** Range where the enemies will aggro*/
    private static final int AGGRO_RANGE = 200;
    /** Whether the enemy is grounded or not */
    private boolean isGrounded;
    /** Possible enemy states */
    public Vector vel;
    /** The enemy's Sprite as an Image */
    public Image sprite;
    /** The Node that is added to the scene and whose movement is updated */
    public Node enemy;
    /** The enemy's current hp*/
    private int hp;
    /** Is the enemy killed? */
    private boolean killed;
    /** The dimensions of the enemy's hitbox in pixels */
    private static final Vector HITBOX_SIZE = new Vector(20, 28);
    /** The enemy's movement speed which is a constant*/
    private static final float MAX_SPEED = 2.5f;
    /** The enemies's acceleration speed which is a constant*/
    private static final float ACCELERATION = 0.01f;
    /** The direction the enemy is moving (1 right -1 left 0 idle) */
    private int moveDirection;
    /** Frames enemy has been damaged*/
    private long damagedFrames = 0;
    /** Frames enemy has been invincible */
    private long invincibleFrames = 0;
    /** EnemyState represented in code*/
    private enum EnemyState {
        IDLE,
        HOSTILE,
        DAMAGED
    }
    /** Current state of the enemy */
    private EnemyState state;
    /** If the player is in agro range */
    public boolean inRange(Player p) {
        return (Vector.dist(this.pos, p.pos) < AGGRO_RANGE);
    }
    /**
     * Damage this enemy with the specified amount
     * @return If damaging was successful
     */
    public boolean damage(int amount, Vector location) {
        hp -= amount;
        invincibleFrames = 20;
        boolean result = requestStateChange(EnemyState.DAMAGED) == EnemyState.DAMAGED;
        if (result)
            vel = location.sub(pos).mul(-0.1f);
        return result;
    }
    /**
     * Update method to update the enemy's position and state
     */
    public void update() {
        if (killed) {
            enemy.setVisible(false);
            createHitBox(new Vector(0,0), new Vector(0,0));
            return;
        }
        switch (state) {
            case IDLE:
                // handle state logic

                // Handle exiting this state
                if (inRange((Player) Game.player)) {
                    System.out.println("agro");
                    requestStateChange(EnemyState.HOSTILE);
                }
                break;
            case HOSTILE:
                // handle state logic
                vel = vel.add(Game.player.pos.sub(pos).toUnit().mul(ACCELERATION));
                // Handle exiting this state
                if (!inRange((Player) Game.player)) {
                    requestStateChange(EnemyState.IDLE);
                }
                break;
            case DAMAGED:
                // handle state logic
                damagedFrames++;
                // Handle exiting this state
                if (damagedFrames > DAMAGE_STUTTER_DURATION) {
                    if (hp <= 0)
                        killed = true;
                    damagedFrames = 0;
                    requestStateChange(EnemyState.IDLE);
                }
                break;
        }
        // global logic

        // touching player
        if (HitBox.areBoxesColliding(hitbox, ((Player)Game.player).getHitBox())) {
            if (((Player)Game.player).isDamagableState()) { // if damaging was sucessful
                ((Player)Game.player).damage(20, pos.add(hitbox.p2.sub(hitbox.p1).div(2)));
                vel = vel.add(Vector.sub(pos, Game.player.pos).mul(0.1f)); // bounce away from player
            } else if(invincibleFrames == 0) {
                ((Player)Game.player).heal(20);
                damage(100, Game.player.pos);
            }
        }
        if (invincibleFrames > 0) invincibleFrames--;

        // add position
        pos = pos.add(vel);
        createHitBox(pos, pos.add(HITBOX_SIZE));

        // reduce velocity for next frame
        vel = vel.mul(0.99f);
    }

    /**
     * Getter for the node
     * @return The node
     */
    public Node getNode() {
        return enemy;
    }

    /**
     * Enemy class constructor to initialize variables
     * @param x X-coord
     * @param y Y-coord
     */
    public Enemy(float x, float y) {
        this.pos = new Vector(x, y);
        vel = new Vector(0, 0);
        hp = 100;
        killed = false;
        state = EnemyState.IDLE;
        createHitBox(pos, pos.add(HITBOX_SIZE));
        moveDirection = 0;
        sprite = new Image("assets/enemys/enemyIdle.png");
        enemy = new ImageView(sprite);
    }

    /**
     * Method to ensure all changes between states are valid
     * @param newState State to change to
     * @return The updated state
     */
    private EnemyState requestStateChange(EnemyState newState) {
        switch (state) {
            case IDLE:
                return state = newState;
            case HOSTILE:
                switch (newState) {
                    case IDLE:
                        return state = EnemyState.IDLE;
                    case DAMAGED:
                        return state = EnemyState.DAMAGED;
                }
                break;
            case DAMAGED:
                switch (newState) {
                    case IDLE:
                        return state = EnemyState.IDLE;
                }
                break;
        }
        return state;
    }
}