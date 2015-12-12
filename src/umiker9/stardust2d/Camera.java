package umiker9.stardust2d;

import umiker9.stardust2d.graphics.lwjgl2.Renderer;

/**
 * Created by miker9 on 23/11/2015.
 */
public class Camera {
    protected double x;
    protected double y;
    protected double rotation;
    protected double scale;

    public Camera() {
        this(0, 0);
    }

    public Camera(double x, double y) {
        this.x = x;
        this.y = y;
        this.scale = 1;
    }

    public void applyTransforms(Renderer renderer) {
        renderer.translate(renderer.getWidth() / 2, renderer.getHeight() / 2);
        renderer.scale(scale, scale);
        renderer.rotate(-rotation);
        renderer.translate(-x, -y);
    }

    public void update(double delta) {

    }

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
