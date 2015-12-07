package umiker9.stardust2d.math.collision;

import org.lwjgl.opengl.GL11;
import umiker9.stardust2d.math.Vec2;

/**
 * Created by slimon
 * on 11.01.2015.
 */
public class AABB2D {

    public Vec2 pMin, pMax;

    public AABB2D(Vec2 pMin, Vec2 pMax) {
        update(pMin, pMax);
    }

    public AABB2D(Vec2[] points) {
        update(points);
    }

    public void update(Vec2 pMin, Vec2 pMax) {
        this.pMin = pMin;
        this.pMax = pMax;
    }

    public void update(Vec2[] points) {
        double minX = points[0].getX();
        double maxX = points[0].getX();
        double minY = points[0].getY();
        double maxY = points[0].getY();
        for(Vec2 point : points) {
            minX = Math.min(point.getX(), minX);
            maxX = Math.max(point.getX(), maxX);
            minY = Math.min(point.getY(), minY);
            maxY = Math.max(point.getY(), maxY);
        }
        update(new Vec2(minX, minY), new Vec2(maxX, maxY));
    }

    @Deprecated
    public void draw() {
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex2d(pMax.getX(), pMax.getY());
        GL11.glVertex2d(pMin.getX(), pMax.getY());
        GL11.glVertex2d(pMin.getX(), pMin.getY());
        GL11.glVertex2d(pMax.getX(), pMin.getY());
        GL11.glEnd();
    }
}
