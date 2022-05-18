public class Vector {
    static public Vector add(Vector v1, Vector v2) {
        return new Vector(v1.x + v2.x, v1.y + v2.y);
    }
    static public Vector sub(Vector v1, Vector v2) {
        return new Vector(v1.x - v2.x, v1.y - v2.y);
    }
    static public Vector mul(Vector v1, float scalar) {
        return new Vector(v1.x * scalar, v1.y * scalar);
    }
    static public Vector div(Vector v1, float scalar) {
        return new Vector(v1.x / scalar, v1.y / scalar);
    }
    static public Vector lerp(Vector v1, Vector v2, float scalar) {
        return Vector.add(v1, Vector.sub(v2, v1).mul(scalar));
    }

    public float x, y;
    
    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    } 
    public Vector add(Vector v1) {
        return Vector.add(this, v1);
    }
    public Vector sub(Vector v1) {
        return Vector.sub(this, v1);
    }
    public Vector mul(float scalar) {
        return Vector.mul(this, scalar);
    }
    public Vector lerp(Vector v1, float scalar) {
        return Vector.lerp(this, v1, scalar);
    }
}
