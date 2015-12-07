package umiker9.stardust2d;

import umiker9.stardust2d.math.MathUtil;
import umiker9.stardust2d.math.Vec2;
import umiker9.stardust2d.math.collision.AABB2D;

/**
 * Created by Notezway on 07.12.2015.
 */
public class PolygonShape extends Shape {

    private Vec2[] points;

    public PolygonShape(Vec2... points) {
        setPoints(points);
    }

    @Override
    public double getSize() {
        AABB2D aabb = new AABB2D(points);
        return MathUtil.distance2(aabb.pMin.getX(), aabb.pMin.getY(), aabb.pMax.getX(), aabb.pMax.getY());
    }

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public Vec2 getCenterPoint() {
        return null;
    }

    @Override
    public void center() {

    }

    @Override
    public void scale(double factor) {
        for(int i = 0; i < points.length; i++) {
            points[i] = points[i].scale(factor);
        }
    }

    @Override
    public void rotate(double rad) {
        for(int i = 0; i < points.length; i++) {
            points[i] = points[i].rotate(rad);
        }
    }

    public Vec2[] getPoints() {
        return points;
    }

    public void setPoints(Vec2... points) {
        this.points = points;
    }
}
