import javafx.scene.*;
import javafx.scene.image.*;

public class FinalBoss extends Boss {
    public FinalBoss(float x, float y, Vector HITBOX_SIZE){
        super(200, x, y, HITBOX_SIZE);
        sprite = new Image("assets/finalBoss.png");
        boss = new ImageView(sprite);
    }
    @Override
    public void draw() {

    }

    @Override
    public Node getNode() {
        return boss;
    }

    @Override
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
}
