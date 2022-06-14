import javafx.scene.Node;
import javafx.scene.image.Image;

/**
 * <p>
 * This class acts as a generic template for the two bosses in the game
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 19th, 2022
 * <p>
 * Ten minutes were spent by Ryan Atlas on this file on May 19th, 2022.
 * Ten minutes were spent by Ryan Atlas on June 2nd restructuring the file to work with the new GameObject
 * 30 minutes were spent by Ryan Atlas on June 12th adding new methods and variables
 * </p>
 */
public abstract class Boss extends CollidableObject {
   /** The boss's hp*/
   protected int hp;
   /** Boss's velocity*/
   public Vector vel;
   /** The enemy's Sprite as an Image */
   public Image sprite;
   /** Is the boss killed? */
   protected boolean killed;
   /** The boss's movement speed which is a constant*/
   protected static final float MAX_SPEED = 2.5f;
   /** The boss's acceleration speed which is a constant*/
   protected static final float ACCELERATION = 0.01f;
   /** Duration of a damage stutter */
   protected static final int DAMAGE_STUTTER_DURATION = 120;
   /** Hitbox size */
   protected final Vector HITBOX_SIZE;
   /** The direction the boss is moving (1 right -1 left 0 idle) */
   protected int moveDirection;
   /** Frames boss has been damaged*/
   protected long damagedFrames = 0;
   /** Frames boss has been invincible */
   protected long invincibleFrames = 0;
   /** BossState represented in code*/
   protected enum BossState {
      IDLE,
      HOSTILE,
      DAMAGED
   }
   /** Current state of the enemy */
   protected BossState state;
   /**
   * Boss class constructor to be used by subclasses in their constructors
   */
   public Boss (int hp, float x, float y, Vector HITBOX_SIZE) {
      this.hp = hp;
      this.pos = new Vector(x, y);
      vel = new Vector(0,0);
      killed = false;
      moveDirection = 0;
      state = BossState.HOSTILE;
      this.HITBOX_SIZE = HITBOX_SIZE;
   }
   /**
    * Method to ensure all changes between states are valid
    * @param newState State to change to
    * @return The updated state
    */
   protected BossState requestStateChange(BossState newState) {
      switch (state) {
         case IDLE:
            return state = newState;
         case HOSTILE:
            switch (newState) {
               case IDLE:
                  return state = BossState.IDLE;
               case DAMAGED:
                  return state = BossState.DAMAGED;
            }
            break;
         case DAMAGED:
            switch (newState) {
               case IDLE:
                  return state = BossState.IDLE;
            }
            break;
      }
      return state;
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
         vel = location.sub(pos).mul(-0.01f);
      return result;
   }

}