/**
 * <p>
 * The Vector class contains an x and y coordinate and methods to calculate
 * various properties of the coordinate pair.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 3.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 18th, 2022
 * <p>
 * Daniel Morgan spent one hour on May 18th, 2022 adding most functionality.
 * Daniel Morgan spent 20 minutes on May 30th adding dist()
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

    /**
     * Returns the lesser value between {@link Vector} v1 and {@link Vector} max
     * @param v1 The {@link Vector} to be clamped
     * @param max {@link Vector} representing the maximum values to clamp {@link Vector} v1 to
     * @return Lesser values between each component of {@link Vector} v1 and {@link Vector} max
     */
    static public Vector max(Vector v1, Vector max) {
        return new Vector(v1.x > max.x ? max.x : v1.x, v1.y > max.y ? max.y : v1.y);
    }

    /**
     * Returns the lesser value between {@link Vector} v1 and {@link Vector} max
     * @param v1 The {@link Vector} to be clamped
     * @param min {@link Vector} representing the minimum values to clamp {@link Vector} v1 to
     * @return Lesser values between each component of {@link Vector} v1 and {@link Vector} min
     */
    static public Vector min(Vector v1, Vector min) {
        return new Vector(v1.x < min.x ? min.x : v1.x, v1.y < min.y ? min.y : v1.y);
    }
    
    /**
     * Returns the distance between {@link Vector} v1 and {@link Vector} v2 as a float
     * @param v1 The first {@link Vector} to find the distance from
     * @param v2 The second {@link Vector} to find the distance from
     * @return The distance between {@link Vector} v1 and {@link Vector} v2 as a float
     */
    static public float dist(Vector v1, Vector v2) {
        return (float)Math.abs(Math.sqrt(Math.pow(v2.x - v1.x,2f) + Math.pow(v2.y - v1.y,2f)));
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
    /**
     * Returns the string representation of this vector
     */
    @Override
    public String toString() {
        return "Vector : " + x + ", " + y;
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
    /** Instance implementation of {@link #max(Vector, Vector)} */
    public Vector max(Vector max) {
        return Vector.max(this, max);
    }
    /** Instance implementation of {@link #min(Vector, Vector)} */
    public Vector min(Vector min) {
        return Vector.min(this, min);
    }
    /** Instance implementation of {@link #dist(Vector, Vector)} */
    public float dist(Vector v1) {
        return dist(this, v1);
    }
}
