package umiker9.stardust2d.util.math.vector;

/**
 * ===
 * Created by slimon on 30.07.2015.
 */
public class Vec2D {

    public double x, y;

    public Vec2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vec2D set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec2D add(Vec2D vec) {
        x += vec.x;
        y += vec.y;
        return this;
    }

    public Vec2D subtract(Vec2D vec) {
        x -= vec.x;
        y -= vec.y;
        return this;
    }

    public Vec2D multiply(double d) {
        x *= d;
        y *= d;
        return this;
    }

    public double dotProduct(Vec2D vec) {
        return x * vec.x + y * vec.y;
    }

    public double lengthSq() {
        return x * x + y * y;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public double lengthsProduct(Vec2D vec) {
        return Math.sqrt((x * x + y * y) * (vec.x * vec.x + vec.y * vec.y));
    }

    public double getAngle(Vec2D vec) {
        return Math.acos(dotProduct(vec) / lengthsProduct(vec));
    }

    public Vec2D copy() {
        return new Vec2D(x, y);
    }
}
