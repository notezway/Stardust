package umiker9.stardust2d.util.math;

import org.lwjgl.util.vector.Vector4f;

public class Vec4 extends Vector {
    protected static final int X = 0;
    protected static final int Y = 1;
    protected static final int Z = 2;
    protected static final int W = 3;

    public Vec4() {
        super(4);
    }

    public Vec4(double x, double y, double z, double w) {
        super(x, y, z, w);
    }

    public Vec4(Vec4 another) {
        super(another.getX(), another.getY(), another.getZ(), another.getW());
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

    public double getZ() {
        return getComponent(Z);
    }

    public void setZ(double value) {
        setComponent(Z, value);
    }

    public double getW() {
        return getComponent(W);
    }

    public void setW(double value) {
        setComponent(W, value);
    }

    public double getLengthSq() {
        return getX() * getX() + getY() * getY() + getZ() * getZ() + getW() * getW();
    }

    public double getLength() {
        return Math.sqrt(getLengthSq());
    }

    public Vec4 scale(double scale) {
        return new Vec4(getX() * scale, getY() * scale, getZ() * scale, getW() * scale);
    }

    public Vec4 negate() {
        return scale(-1F);
    }

    public Vec4 normalize() {
        return scale(1F / getLength());
    }

    public Vec4 add(Vec4 another) {
        return new Vec4(getX() + another.getX(),
                        getY() + another.getY(),
                        getZ() + another.getZ(),
                        getW() + another.getW());
    }

    public Vec4 subtract(Vec4 another) {
        return new Vec4(getX() - another.getX(),
                        getY() - another.getY(),
                        getZ() - another.getZ(),
                        getW() - another.getW());
    }

    public Vec4 multiply(Vec4 another) {
        return new Vec4(getX() * another.getX(),
                        getY() * another.getY(),
                        getZ() * another.getZ(),
                        getW() * another.getW());
    }

    public double dot(Vec4 another) {
        return getX() * another.getX() + getY() * another.getY() + getZ() * another.getZ() + getW() * another.getW();
    }

    public Vector4f asGLVector() {
        return new Vector4f((float)getX(), (float)getY(), (float)getZ(), (float)getW());
    }



    @Override
    public String toString() {
        return "Vec2{" +
                "x = " + getX() +
                ", y = " + getY() +
                ", z = " + getZ() +
                ", w = " + getW() +
                '}';
    }

}
