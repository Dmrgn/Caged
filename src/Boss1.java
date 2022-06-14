import javafx.scene.*;
import javafx.scene.image.*;
/**
 * <p>
 * This class is used for the first boss.
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
public class Boss1 extends Boss {
    Node boss;
    /**
     * Constructor that initializes variables
     * @param x Starting x position
     * @param y Starting y position
     * @param HITBOX_SIZE Hitbox size
     */
    public Boss1(float x, float y, Vector HITBOX_SIZE, Image image){
        super(150, x, y, HITBOX_SIZE);
        sprite = image;
        createHitBox(pos, pos.add(HITBOX_SIZE));
        boss = new ImageView(sprite);
        boss.setVisible(true);
    }

    /**
     * Update method controls boss' behaviour, movement, states and health
     */
    public void update() {
        if (killed) {
            boss.setVisible(false);
            createHitBox(new Vector(0,0), new Vector(0,0));
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
                vel = vel.add(Game.player.pos.sub(pos).toUnit().mul(ACCELERATION));
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
        // touching player
        if (HitBox.areBoxesColliding(hitbox, ((Player)Game.player).getHitBox())) {
            if (((Player)Game.player).isDamagableState()) { // if damaging was successful
                ((Player)Game.player).damage(20, pos.add(hitbox.p2.sub(hitbox.p1).div(2)));
                vel = vel.add(Vector.sub(pos, Game.player.pos).mul(0.01f)); // bounce away from player
            } else if(invincibleFrames == 0) {
                ((Player)Game.player).heal(20);
                damage(20, Game.player.pos);
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
     * Getter for node
     * @return The node
     */
    public Node getNode() {
        return boss;
    }
}
