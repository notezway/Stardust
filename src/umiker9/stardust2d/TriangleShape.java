package umiker9.stardust2d;

import umiker9.stardust2d.math.MathUtil;
import umiker9.stardust2d.math.Vec2;

/**
 * Created by Notezway on 07.12.2015.
 */
public class TriangleShape extends Shape {

    private Vec2[] points;

    public TriangleShape(Vec2... points) {
        setPoints(points);
    }

    @Override
    public double getSize() {
        double s1 = MathUtil.distance2Sq(points[0].getX(), points[0].getY(), points[1].getX(), points[1].getY());
        double s2 = MathUtil.distance2Sq(points[1].getX(), points[1].getY(), points[2].getX(), points[2].getY());
        double s3 = MathUtil.distance2Sq(points[2].getX(), points[2].getY(), points[0].getX(), points[0].getY());
        return Math.sqrt(MathUtil.max(s1, s2, s3));
    }

    @Override
    public double getArea() {
        Vec2 v1 = points[1].subtract(points[0]);
        Vec2 v2 = points[2].subtract(points[0]);
        return v1.multiply(v2).getLength() / 2;
    }

    @Override
    public Vec2 getCenterPoint() {
        return points[0].add(points[1]).add(points[2]).scale(1./3);
    }

    @Override
    public void center() {
        Vec2 center = getCenterPoint();
        points[0] = points[0].subtract(center);
        points[1] = points[1].subtract(center);
        points[2] = points[2].subtract(center);
    }

    @Override
    public void scale(double factor) {
        points[0] = points[0].scale(factor);
        points[1] = points[1].scale(factor);
        points[2] = points[2].scale(factor);
    }

    @Override
    public void rotate(double rad) {
        points[0] = points[0].rotate(rad);
        points[1] = points[1].rotate(rad);
        points[2] = points[2].rotate(rad);
    }

    public Vec2[] getPoints() {
        return points;
    }

    public void setPoints(Vec2... points) {
        this.points = new Vec2[] {
                points[0], points[1], points[2]
        };
    }
}
