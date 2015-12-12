package umiker9.stardust2d.math;

import org.lwjgl.util.vector.Vector2f;


public class Vec2 extends Vector {
    public static final double ERROR_VALUE = 0.0001;
    protected static final int X = 0;
    protected static final int Y = 1;

    
    public Vec2() {
        super(2);
    }
    
    public Vec2(double x, double y) {
        super(x, y);
    }
    
    public Vec2(Vec2 another) {
       this(another.getX(), another.getY());
    }

    public double getX() {
        return getComponent(X);
    }

    public void setX(double value) {
        setComponent(X, value);
    }

    public double getY() {
        return getComponent(Y);
    }

    public void setY(double value) {
        setComponent(Y, value);
    }

    public double getLengthSq() {
        return getX() * getX() + getY() * getY();
    }

    public double getLength() {
        return Math.sqrt(getLengthSq());
    }

    public Vec2 scale(double scale) {
        return new Vec2(getX() * scale, getY() * scale);
    }

    public Vec2 negate() {
        return scale(-1D);
    }

    public Vec2 normalize() {
        double len = getLength();
        if(len < ERROR_VALUE) return new Vec2(0, 0);
        return scale(1D / getLength());
    }

    public Vec2 add(Vec2 another) {
        return new Vec2(getX() + another.getX(), getY() + another.getY());
    }

    public Vec2 subtract(Vec2 another) {
        return new Vec2(getX() - another.getX(), getY() - another.getY());
    }

    public Vec2 multiply(Vec2 another) {
        return new Vec2(getX() * another.getX(), getY() * another.getY());
    }

    public double dot(Vec2 another) {
        return getX() * another.getX() + getY() * another.getY();
    }

    public Vec2 rotate(double rad) {
        double sin = Math.sin(rad);
        double cos = Math.cos(rad);
        return new Vec2(
                getX() * cos - getY() * sin,
                getX() * sin + getY() * cos
        );
    }

    public double projection(Vec2 another) {
        double dot = this.dot(another);
        double len1 = this.getLength();
        double len2 = another.getLength();
        if(len1 < ERROR_VALUE || len2 < ERROR_VALUE) return 0;
        double cos = dot / (len1 * len2);
        return len1 * cos;
    }

    public Vector2f asGLVector() {
        return new Vector2f((float)getX(), (float)getY());
    }

    @Override
    public String toString() {
        return "Vec2{" +
                "x = " + getX() +
                ", y = " + getY() +
                '}';
    }
}
