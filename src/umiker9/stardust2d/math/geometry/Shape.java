package umiker9.stardust2d.math.geometry;

import umiker9.stardust2d.math.Vec2;

/**
 * Created by Notezway on 07.12.2015.
 */
public abstract class Shape {

    public abstract double getSize();

    public abstract double getArea();

    public abstract Vec2 getCenterPoint();

    public abstract void center();

    public abstract void scale(double factor);

    public abstract void rotate(double rad);

    public abstract boolean doesCollide(Shape another, Vec2 pos1, Vec2 pos2);
}
