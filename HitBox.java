/**
 * <p>
 * Helper class for handling collision detection
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 1.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 28th, 2022
 * <p>
 * 30 minutes were spent on this file by Daniel Morgan on May 28th 2022
 * </p>
 */
public class HitBox {
    /**
     * Uses point rectangle collision to determine if two 
     * hitboxes are overlapping.
     * @param b1 The first {@link HitBox} to check against
     * @param b2 The second {@link HitBox} to check against
     * @return true if the two hitboxes are overlapping otherwise false
     */
    public static boolean areBoxesColliding(HitBox b1, HitBox b2) {
        System.out.println("==================");
        System.out.println(b1.p1);
        System.out.println(b1.p2);
        System.out.println(b2.p1);
        System.out.println(b2.p2);
        if (b1.p1.x >= b2.p1.x - b1.getWidth() &&   // right of the left edge AND
            b1.p1.x <= b2.p2.x &&                   // left of the right edge AND
            b1.p1.y >= b2.p1.y - b1.getHeight() &&  // below the top AND
            b1.p1.y <= b2.p2.y) {                   // above the bottom
                return true;
        }
        return false;
    }
    public Vector p1, p2;
    public HitBox(Vector p1, Vector p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    /** Instance implementation of {@link #areBoxesColliding(HitBox, HitBox)} */
    public boolean isCollidingWith(HitBox b1) {
        return areBoxesColliding(b1, this);
    }
    /**
     * Returns the width of this hitbox
     * @return the width of this hitbox
     */
    public float getWidth() {
        return Math.abs(p2.x-p1.x);
    }
    /**
     * Returns the height of this hitbox
     * @return the height of this hitbox
     */
    public float getHeight() {
        return Math.abs(p2.y-p1.y);
    }
}