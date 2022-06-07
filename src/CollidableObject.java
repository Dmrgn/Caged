import javafx.scene.*;
/**
 * <p>
 * This class provides an interface for easily interacting with collision
 * classes. It also provides a wrapper for to points
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 2.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 27th, 2022
 * <p>
 * File was created by Daniel Morgan on May 28th, 2022. 30 minutes were spent by Daniel Morgan.
 * </p>
 */
public abstract class CollidableObject extends GameObject {
    /**
     * Tests if the two passed collidable objects are touching
     * @param o1 The first object to test against
     * @param o2 The second object to test against
     * @return If the two objects are touching
     */
    public static boolean touching(CollidableObject o1, CollidableObject o2) {
        return HitBox.areBoxesColliding(o1.hitbox, o2.hitbox);
    }
    /**
     * The hitbox of this object
     */
    protected HitBox hitbox = new HitBox(new Vector(0, 0), new Vector(0, 0));
    /**
     * Creates a new {@link HitBox} starting at corner {@link Vector} p1 and going
     * to corner {@link Vector} p2. Assigns the created {@link HitBox} to this
     * object.
     * @param p1 Top left corner of the {@link HitBox} to be created
     * @param p2 Bottom right corner of the {@link HitBox} to be created
     */
    protected void createHitBox(Vector p1, Vector p2) {
        hitbox.p1 = p1;
        hitbox.p2 = p2;
    }
    /**
     * Returns the hitbox of this object
     * @return The hitbox of this object
     */
    public HitBox getHitBox() {
        return hitbox;
    }
}