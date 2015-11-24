package umiker9.stardust2d;

/**
 * Created by miker9 on 23/11/2015.
 */
public class Camera {
    protected double x;
    protected double y;
    protected double rotation;
    protected double scale;


    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
}
