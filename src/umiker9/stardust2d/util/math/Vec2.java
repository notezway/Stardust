package umiker9.stardust2d.util.math;

import org.lwjgl.util.vector.Vector2f;



public class Vec2 extends Vector {
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
        return scale(-1F);
    }

    public Vec2 normalize() {
        return scale(1F / getLength());
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
