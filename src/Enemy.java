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
    /** Whether the enemy is grounded or not */
    private boolean isGrounded;
    /** The bottom hitbox of the enemy */
    private HitBox lowerHitBox;
    /** The top hitbox of the enemy */
    private HitBox upperHitBox;
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
    private static final Vector HITBOX_SIZE = new Vector(30, 30);
    /** The enemy's movement speed which is a constant*/
    private static final float MAX_SPEED = 2.5f;
    /** The enemies's acceleration speed which is a constant*/
    private static final float ACCELERATION = 0.10f;
    /** The direction the enemy is moving (1 right -1 left 0 idle) */
    private int moveDirection;
    /** EnemyState represented in code*/
    private enum EnemyState {
        IDLE,
        HOSTILE,
        DAMAGED
    }
    /** Current state of the enemy */
    private EnemyState state;
    /** If the player is in agro range */
    public boolean inRange(Player p){
        return (Math.abs(p.getNode().getLayoutX() - enemy.getLayoutX()) < 200 && Math.abs(p.getNode().getLayoutY() - enemy.getLayoutY()) < 100);
    }
    /**
     * Update method to update the enemy's position and state
     */
    public void update(){
        if (!killed){
            switch (state){
                case IDLE:
                   if (inRange((Player) Game.player)) {

                   }
                   break;
                case HOSTILE:
                    break;
                case DAMAGED:
                    break;
            }
        } else {
            enemy.setVisible(false);
        }
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
        sprite = new Image("assets/player.png");
        enemy = new ImageView(sprite);
    }

    /**
     * Creates the enemy's hitbox
     * @param pos1 Top left corner
     * @param pos2 Bottom right corner
     */
    @Override
    public void createHitBox(Vector pos1, Vector pos2) {
        hitbox = new HitBox(pos1, pos2);
        lowerHitBox = new HitBox(pos1.add(new Vector(5, HITBOX_SIZE.y / 2)), pos2.add(new Vector(-5, 0)));
        upperHitBox = new HitBox(pos1.add(new Vector(5, 0)), pos2.sub(new Vector(-5, HITBOX_SIZE.y / 2)));
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