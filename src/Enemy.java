import javafx.scene.image.*;
import javafx.scene.*;
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

    public void update(){
    }
    public Node getNode() {
        return enemy;
    }
    public Enemy(float x, float y) {
        this.pos = new Vector(x, y);
        vel = new Vector(0, 0);
        hp = 100;
        state = EnemyState.IDLE;
        createHitBox(pos, pos.add(HITBOX_SIZE));
        moveDirection = 0;
        sprite = new Image("assets/player.png");
        enemy = new ImageView(sprite);
    }
    @Override
    public void createHitBox(Vector pos1, Vector pos2) {
        hitbox = new HitBox(pos1, pos2);
        lowerHitBox = new HitBox(pos1.add(new Vector(5, HITBOX_SIZE.y/2)), pos2.add(new Vector(-5, 0)));
        upperHitBox = new HitBox(pos1.add(new Vector(5, 0)), pos2.sub(new Vector(-5, HITBOX_SIZE.y/2)));
    }
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