package umiker9.stardust2d;

import umiker9.stardust2d.graphics.lwjgl2.Renderer;

/**
 * Created by miker9 on 23/11/2015.
 */
public class Sprite extends GameObject {
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected double originX;
    protected double originY;
    protected double rotation;

    public Sprite(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void render(Renderer renderer) {
        renderer.pushMatrix();
        renderer.translate(x, y);
        renderer.rotate(rotation);

        renderer.drawQuad(-originX, -originY, width, height);

        renderer.popMatrix();
    }

    @Override
    public void update(long delta) {

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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getOriginX() {
        return originX;
    }

    public void setOriginX(double originX) {
        this.originX = originX;
    }

    public double getOriginY() {
        return originY;
    }

    public void setOriginY(double originY) {
        this.originY = originY;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }
}
