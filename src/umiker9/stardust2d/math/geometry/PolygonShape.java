package umiker9.stardust2d.math.geometry;

import umiker9.stardust2d.graphics.Color;
import umiker9.stardust2d.graphics.lwjgl2.Renderer;
import umiker9.stardust2d.graphics.lwjgl2.ShapesDrawer;
import umiker9.stardust2d.math.MathUtil;
import umiker9.stardust2d.math.Vec2;
import umiker9.stardust2d.math.VecUtil;
import umiker9.stardust2d.math.collision.AABB2D;
import umiker9.stardust2d.math.collision.Collision2D;

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
        return VecUtil.average(points);
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

    @Override
    public boolean doesCollide(Shape another, Vec2 pos1, Vec2 pos2) {
        if(another instanceof CircleShape) {
            Vec2[] shifted = new Vec2[points.length];
            for(int i = 0; i < points.length; i++) {
                shifted[i] = points[i].add(pos1);
            }
            return Collision2D.getShapeWithCircleCollision(shifted, pos2, ((CircleShape)another).getRadius()).length > 0;
        } else if(another instanceof PolygonShape) {
            Vec2[] shifted1 = new Vec2[points.length];
            for(int i = 0; i < points.length; i++) {
                shifted1[i] = points[i].add(pos1);
            }
            Vec2[] points2 = ((PolygonShape) another).getPoints();
            Vec2[] shifted2 = new Vec2[points2.length];
            for(int i = 0; i < points2.length; i++) {
                shifted2[i] = points2[i].add(pos2);
            }
            return Collision2D.getShapesCollision(shifted1, shifted2).length > 0;
        }
        return false;
    }

    public Vec2[] getPoints() {
        return points;
    }

    public void setPoints(Vec2... points) {
        this.points = points;
    }
}
