package umiker9.stardust2d.math;


import org.lwjgl.util.vector.Vector3f;

public class Vec3 extends Vector {
    protected static final int X = 0;
    protected static final int Y = 1;
    protected static final int Z = 2;

    public Vec3() {
        super(3);
    }

    public Vec3(double x, double y, double z) {
        super(x, y, z);
    }

    public Vec3(Vec3 another) {
        this(another.getX(), another.getY(), another.getZ());
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

    public double getLengthSq() {
        return getX() * getX() + getY() * getY() + getZ() * getZ();
    }

    public double getLength() {
        return Math.sqrt(getLengthSq());
    }

    public Vec3 scale(double scale) {
        return new Vec3(getX() * scale, getY() * scale, getZ() * scale);
    }

    public Vec3 negate() {
        return scale(-1F);
    }

    public Vec3 normalize() {
        return scale(1F / getLength());
    }

    public Vec3 add(Vec3 another) {
        return new Vec3(getX() + another.getX(), getY() + another.getY(), getZ() + another.getZ());
    }

    public Vec3 subtract(Vec3 another) {
        return new Vec3(getX() - another.getX(), getY() - another.getY(), getZ() + another.getZ());
    }

    public Vec3 multiply(Vec3 another) {
        return new Vec3(getX() * another.getX(), getY() * another.getY(), getZ() + another.getZ());
    }

    public double dot(Vec3 another) {
        return getX() * another.getX() + getY() * another.getY() + getZ() * another.getZ();
    }

    public Vec3 cross(Vec3 another) {
        return new Vec3(getY() * another.getZ() - getZ() * another.getY(),
                        getZ() * another.getX() - getX() * another.getY(),
                        getX() * another.getY() - getY() * another.getX());
    }

    public Vector3f asGLVector() {
        return new Vector3f((float)getX(), (float)getY(), (float)getZ());
    }

    @Override
    public String toString() {
        return "Vec2{" +
                "x = " + getX() +
                ", y = " + getY() +
                ", z = " + getZ() +
                '}';
    }

}
