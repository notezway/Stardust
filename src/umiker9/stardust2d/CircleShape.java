package umiker9.stardust2d;

import umiker9.stardust2d.math.Vec2;

/**
 * Created by Notezway on 07.12.2015.
 */
public class CircleShape extends Shape {

    private Vec2 center;
    private double radius;

    public CircleShape(Vec2 center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public double getSize() {
        return 2 * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public Vec2 getCenterPoint() {
        return center;
    }

    @Override
    public void center() {
        center.setX(0);
        center.setY(0);
    }

    @Override
    public void scale(double factor) {
        radius *= factor;
    }

    @Override
    public void rotate(double rad) {
        //nothing
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
