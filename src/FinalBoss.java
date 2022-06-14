import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;

/**
 * <p>
 * This class is used for the final boss.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since June 12th, 2022
 * <p>
 * File was created by Ryan Atlas on June 12th, 30 minutes were spent
 * </p>
 */
public class FinalBoss extends Boss {
    /** The boss's acceleration speed which is a constant*/
    protected static final float ACCELERATION = 0.04f;
    /** how high the boss can jump */
    private static final int JUMP_HEIGHT = 8;
    /** The bottom hitbox of the player */
    private HitBox lowerHitBox;
    /** Node for boss to be displayed*/
    private Node boss;
    /** Image for final brother screen */
    private Image brotherScreen;
    /**
     * Constructor that initializes variables
     * @param x Starting x position
     * @param y Starting y position
     * @param HITBOX_SIZE Hitbox size
     */
    public FinalBoss(float x, float y, Vector HITBOX_SIZE, Image image) {
        super(10, x, y, HITBOX_SIZE);
        sprite = image;
        createHitBox(pos, pos.add(HITBOX_SIZE));
        boss = new ImageView(sprite);
        brotherScreen = new Image("assets/doors/BrotherTips6.png");
    }
    /**
     * Method to create hitbox from 2 vectors
     * @param pos1 Top left corner
     * @param pos2 Bottom right corner
     */
    @Override
    public void createHitBox(Vector pos1, Vector pos2) {
        hitbox = new HitBox(pos1.add(new Vector(0, 5)), pos2.sub(new Vector(0, 5)));
        lowerHitBox = new HitBox(pos1.add(new Vector(5, HITBOX_SIZE.y / 2)), pos2.add(new Vector(-5, 0)));
    }

    /**
     * Update method controls boss' behaviour, movement, states and health
     */
    public void update() {
        if (killed) {
            clearTransformations();
            ((ImageView) boss).setImage(brotherScreen);
            pos = new Vector(0, 0);
            Game.player.getNode().setVisible(false);
            Player.playerMoving = false;
            if(Keyboard.isKeyDown(KeyCode.H)) {
                Game.navigateLevel(Game.getLevel(2), 0, 0);
                Game.player.getNode().setVisible(true);
                Player.playerMoving = true;
                Game.breakGame = true;
                System.out.println("here");
            }
            return;
        }
        switch (state) {
            case IDLE:
                // handle state logic

                // Handle exiting this state
                requestStateChange(BossState.HOSTILE);
                break;
            case HOSTILE:
                // handle state logic
                vel = vel.add(new Vector(Game.player.pos.sub(pos).toUnit().mul(ACCELERATION).x, 0));
                // Handle exiting this state
                break;
            case DAMAGED:
                // handle state logic
                damagedFrames++;
                // Handle exiting this state
                if (damagedFrames > DAMAGE_STUTTER_DURATION) {
                    if (hp <= 0)
                        killed = true;
                    damagedFrames = 0;
                    requestStateChange(BossState.IDLE);
                }
                break;
        }
        // global logic
        boolean hasTouchedGround = false;
        boolean jumped = false;
        boolean collided = true;
        // touching player
        if (HitBox.areBoxesColliding(hitbox, ((Player) Game.player).getHitBox())) {
            if (((Player) Game.player).isDamagableState()) { // if damaging was successful
                ((Player) Game.player).damage(20, pos.add(hitbox.p2.sub(hitbox.p1).div(2)));
                vel = vel.add(new Vector(Vector.sub(pos, Game.player.pos).mul(-0.6f).x, 0)); // bounce away from player
            } else if (invincibleFrames == 0) {
                ((Player) Game.player).heal(20);
                damage(10, Game.player.pos);
            }
        }

        vel = vel.add(new Vector(0, Game.GRAVITY*2));
        // Handle vertical collisions
        if (Game.touchingCollidable(this, lowerHitBox)) {
            // collided = true;
            hasTouchedGround = true;
            pos.y = pos.y - (vel.y * 1.5f);
            createHitBox(pos, pos.add(HITBOX_SIZE));
            vel.y = 0;
            if ((int)(Math.random() * 50) == 1) {
                vel = vel.add(new Vector(0, -JUMP_HEIGHT));
                jumped = true;
            }
        }
        // Handle lateral collisions
        if (Game.touchingCollidable(this) && collided) {
            pos.x = pos.x - (vel.x * 1.5f);
            createHitBox(pos, pos.add(HITBOX_SIZE));
            vel.x = vel.x * -0.5f;
        }
        if (invincibleFrames > 0) invincibleFrames--;
        // add position
        pos = pos.add(vel);
        createHitBox(pos, pos.add(HITBOX_SIZE));
        // reduce velocity for next frame
        vel = vel.mul(0.98f);
        if (pos.y > 1000) {
            pos = new Vector(523, 494);
        }
    }
    /**
     * Method called when damaged
     * @param amount Amount of damage
     * @param location Location
     * @return Whether the state was changed to damaged
     */
    public boolean damage(int amount, Vector location) {
        hp -= amount;
        invincibleFrames = 20;
        boolean result = requestStateChange(BossState.DAMAGED) == BossState.DAMAGED;
        if (result)
            vel = vel.add(new Vector(Vector.sub(pos, Game.player.pos).mul(-0.6f).x, 0)); // bounce away from player
        return result;
    }
    /**
     * Getter for node
     * @return The node
     */
    public Node getNode() {
        return boss;
    }
}