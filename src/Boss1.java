import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;

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
 * Daniel Morgan spent 30 minutes on June 13th modifying the update method
 * </p>
 */
public class Boss1 extends Boss {
    /** Node*/
    Node boss;
    /** Image for a brother screen after the boss*/
    Image brotherScreen;
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
        brotherScreen = new Image("assets/doors/BrotherTips4.png");
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
                ((Player)Game.player).heal(10);
                damage(10, Game.player.pos);
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
