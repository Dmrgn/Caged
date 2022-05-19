/**
 * <p>
 * The Vector class contains an x and y coordinate and methods to calculate
 * various properties of the coordinate pair.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 1.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 18th, 2022
 * <p>
 * Daniel Morgan spent one hour on May 18th, 2022.
 * </p>
 */
public class Vector {
    /**
     * Adds the x and y coordinates of one {@link Vector} to another and returns the result.
     * @param v1 The first {@link Vector} to be added
     * @param v2 The second {@link Vector} to be added
     * @return Sum of the two {@link Vector}s
     */
    static public Vector add(Vector v1, Vector v2) {
        return new Vector(v1.x + v2.x, v1.y + v2.y);
    }
    /**
     * Subtracts the x and y coordinates of {@link Vector} v2 from {@link Vector} v1 and returns the result.
     * @param v1 The {@link Vector} to be subtracted from
     * @param v2 The {@link Vector} to subtract by
     * @return Difference of the two {@link Vector}s
     */
    static public Vector sub(Vector v1, Vector v2) {
        return new Vector(v1.x - v2.x, v1.y - v2.y);
    }
    /**
     * Multiplies the {@link Vector} v1 by a scalar and returns the result.
     * @param v1 The {@link Vector} to be multiplied
     * @param scalar The scalar to multiply the {@link Vector} v1 by
     * @return Product of {@link Vector} v1 and int scalar
     */
    static public Vector mul(Vector v1, float scalar) {
        return new Vector(v1.x * scalar, v1.y * scalar);
    }
    /**
     * Divides the {@link Vector} v1 by a scalar and returns the result.
     * @param v1 The {@link Vector} to be divided
     * @param scalar The scalar to be divided by
     * @return Quotient of {@link Vector} v1 and int scalar
     */
    static public Vector div(Vector v1, float scalar) {
        return new Vector(v1.x / scalar, v1.y / scalar);
    }
    /**
     * Divides the {@link Vector} v1 by {@link Vector} v2 component wise and returns the result. 
     * @param v1 The {@link Vector} to be divided
     * @param v2 The {@link Vector} to divide by
     * @return Component wise quotient of {@link Vector} v1 and {@link Vector} v2
     */
    static public Vector div(Vector v1, Vector v2) {
        return new Vector(v1.x / v2.x, v1.y / v2.y);
    }
    /**
     * Computes the linear interpolation from {@link Vector} v1 to {@link Vector} v2 by float scalar.
     * @param v1 The {@link Vector} to be interpolated
     * @param v2 The {@link Vector} to interpolate towards
     * @param scalar The scalar representing the amount to interpolate by
     * @return Linear interpolation between {@link Vector} v1 and {@link Vector} v2 by float scalar
     */
    static public Vector lerp(Vector v1, Vector v2, float scalar) {
        return Vector.add(v1, Vector.sub(v2, v1).mul(scalar));
    }

    /** The x and y components of this vector. */
    public float x, y;
    
    /**
     * Constructor to create a vector at the specified x and y coordinates.
     * @param x
     * @param y
     */
    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }
    /** Instance implementation of {@link #add(Vector, Vector)} */
    public Vector add(Vector v1) {
        return Vector.add(this, v1);
    }
    /** Instance implementation of {@link #sub(Vector, Vector)} */
    public Vector sub(Vector v1) {
        return Vector.sub(this, v1);
    }
    /** Instance implementation of {@link #mul(Vector, float)} */
    public Vector mul(float scalar) {
        return Vector.mul(this, scalar);
    }
    /** Instance implementation of {@link #div(Vector, float)} */
    public Vector div(float scalar) {
        return Vector.div(this, scalar);
    }
    /** Instance implementation of {@link #div(Vector, Vector)} */
    public Vector div(Vector v1) {
        return Vector.div(this, v1);
    }
    /** Instance implementation of {@link #lerp(Vector, Vector, float)} */
    public Vector lerp(Vector v1, float scalar) {
        return Vector.lerp(this, v1, scalar);
    }
}
