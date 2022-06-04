/**
 * <p>
 * Helper class for handling collision detection
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 3.0
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
        if (b1.p1.x >= b2.p1.x - b1.getWidth() &&   // right of the left edge AND
            b1.p1.x <= b2.p2.x &&                   // left of the right edge AND
            b1.p1.y >= b2.p1.y - b1.getHeight() &&  // below the top AND
            b1.p1.y <= b2.p2.y) {                   // above the bottom
                return true;
        }
        return false;
    }
    /** 
     * Creates an array representing the distance of a point 
     * to each pair of adjacent sides. Ordered, Left, Top
     * Right, Bottom sides. 
     */
    public static float[] calculateDistanceToSides(HitBox b, Vector p) {
        HitBox[] sides = {
            new HitBox(b.p1, b.p1.add(new Vector(0,b.getHeight()))), // left face
            new HitBox(b.p1, b.p1.add(new Vector(b.getWidth(),0))),  // top face
            new HitBox(b.p1.add(new Vector(b.getWidth(),0)), b.p2),  // right face
            new HitBox(b.p1.add(new Vector(0,b.getHeight())), b.p2), // bottom face
        };
        float[] distances = new float[4];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Vector.dist(Vector.lerp(sides[i].p1, sides[i].p2, 0.5f), p);
        }
        return distances;
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