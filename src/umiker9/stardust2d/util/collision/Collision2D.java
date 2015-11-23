package umiker9.stardust2d.util.collision;

import umiker9.stardust2d.util.math.MathUtil;
import umiker9.stardust2d.util.math.Vec2;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by slimon
 * on 11.01.2015.
 */
public class Collision2D {

    public static final double LIMIT_VALUE = 10000F;
    public static final double ERROR_VALUE = 0.001F;

    public static boolean checkAABBCollision(AABB2D aabb1, AABB2D aabb2) {
        return aabb1.pMin.getX() <= aabb2.pMax.getX() &&
                aabb1.pMax.getX() >= aabb2.pMin.getX() &&
                aabb1.pMin.getY() <= aabb2.pMax.getY() &&
                aabb1.pMax.getY() >= aabb2.pMin.getY();
    }

    public static boolean checkAABBInside(AABB2D aabb1, AABB2D aabb2) {
        return aabb1.pMin.getX() > aabb2.pMin.getX() &&
                aabb1.pMax.getX() < aabb2.pMax.getX() &&
                aabb1.pMin.getY() > aabb2.pMin.getY() &&
                aabb1.pMax.getY() < aabb2.pMax.getY();
    }

    public static boolean checkAABBInside(Vec2[] points) {
        return points[0].getX() > points[4].getX() &&
                points[2].getX() < points[6].getX() &&
                points[0].getY() > points[4].getY() &&
                points[2].getY() < points[6].getY();
    }

    public static Vec2[] getAABBCollision(Vec2[] points) {
        Vec2 p;
        Vec2 lp1;
        Vec2 lp2;
        ArrayList<Vec2> ret = new ArrayList<Vec2>();
        if(checkAABBInside(points)) {
            ret.add(new Vec2((points[0].getX() + points[2].getX())/2, (points[0].getY() + points[2].getY())/2));
            ret.add(new Vec2((points[4].getX() + points[6].getX())/2, (points[4].getY() + points[6].getY())/2));
            return ret.toArray(new Vec2[ret.size()]);
        }
        for(int i = 0; i < 4; i++) {
            for(int k = 0; k < 2; k++) {
                int i2 = i + 1;
                if(i == 3) i2 = 0;
                int n, m, p1, p2;
                if(i % 2 == 0) {
                    if(k == 0) {
                        n = i; m = i2 + 4;
                        p1 = m; p2 = m + 1;
                    } else {
                        n = i; m = i + 4;
                        p1 = m; p2 = m - 1;
                    }
                } else {
                    if(k == 0) {
                        n = i2 + 4; m = i;
                        p1 = n; p2 = n + 1;
                    } else {
                        n = i + 4; m = i;
                        p1 = n; p2 = n - 1;
                    }
                }
                if(p2 == -1) p2 = 7;
                p = new Vec2(points[n].getX(), points[m].getY());
                lp1 = new Vec2(points[i].getX(), points[i].getY());
                lp2 = new Vec2(points[i2].getX(), points[i2].getY());
                if (checkPointAtAABBLineSegment(p, lp1, lp2)) {
                    lp1 = new Vec2(points[p1].getX(), points[p1].getY());
                    lp2 = new Vec2(points[p2].getX(), points[p2].getY());
                    if(checkPointAtAABBLineSegment(p, lp1, lp2))
                        ret.add(new Vec2(p));
                }
            }
        }
        return ret.toArray(new Vec2[ret.size()]);
    }

    public static Vec2[] getAABBCollision(AABB2D aabb1, AABB2D aabb2) {
        Vec2[] points = new Vec2[] {
                aabb1.pMin,
                new Vec2(aabb1.pMin.getX(), aabb1.pMax.getY()),
                aabb1.pMax,
                new Vec2(aabb1.pMax.getX(), aabb1.pMin.getY()),
                aabb2.pMin,
                new Vec2(aabb2.pMin.getX(), aabb2.pMax.getY()),
                aabb2.pMax,
                new Vec2(aabb2.pMax.getX(), aabb2.pMin.getY())
        };
        return getAABBCollision(points);
    }

    public static Vec2[] getShapesCollision(Vec2[] obj1, Vec2[] obj2) {
        ArrayList<Vec2> points = new ArrayList<Vec2>();
        Vec2 point;
        int i1, i2, k1, k2;
        for(int i = 0; i < obj1.length; i++) {
            for(int k = 0; k < obj2.length; k++) {
                i1 = i;
                if(i + 1 >= obj1.length) i2 = 0;
                else i2 = i + 1;
                k1 = k;
                if(k + 1 >= obj2.length) k2 = 0;
                else k2 = k + 1;
                if((point = getLineSegmentsCollisionPoint(obj1[i1], obj1[i2], obj2[k1], obj2[k2])) != null) {
                    points.add(point);
                }
            }
        }
        return points.toArray(new Vec2[points.size()]);
    }

    public static Vec2 getLineSegmentsCollisionPoint(Vec2 l1p1, Vec2 l1p2, Vec2 l2p1, Vec2 l2p2) {
        double k1 = getLineK(l1p1, l1p2);
        double k2 = getLineK(l2p1, l2p2);
        double b1 = l1p1.getY() - l1p1.getX() * k1;
        double b2 = l2p1.getY() - l2p1.getX() * k2;
        double x = (b2 - b1) / (k1 - k2);
        double y = k1 * x + b1;

        Vec2 point = new Vec2(x, y);
        if(checkPointAtLineSegment(point, l1p1, l1p2) && checkPointAtLineSegment(point, l2p1, l2p2)) {
            return point;
        }
        return null;
    }

    public static boolean checkPointAtLineSegment(Vec2 p, Vec2 lp1, Vec2 lp2) {
        double x1 = Math.min(lp1.getX(), lp2.getX());
        double x2 = Math.max(lp1.getX(), lp2.getX());
        double y1 = Math.min(lp1.getY(), lp2.getY());
        double y2 = Math.max(lp1.getY(), lp2.getY());

        return p.getX() >= x1 - ERROR_VALUE && p.getX() <= x2 + ERROR_VALUE && p.getY() >= y1 - ERROR_VALUE && p.getY() <= y2 + ERROR_VALUE;
    }

    public static boolean checkPointAtAABBLineSegment(Vec2 p, Vec2 lp1, Vec2 lp2) {
        if(lp1.getX() == lp2.getX()) {
            double x = lp1.getX();
            double y1 = Math.min(lp1.getY(), lp2.getY());
            double y2 = Math.max(lp1.getY(), lp2.getY());
            if(p.getX() == x && p.getY() >= y1 && p.getY() <= y2) {
                return true;
            }
        } else {
            double x1 = Math.min(lp1.getX(), lp2.getX());
            double x2 = Math.max(lp1.getX(), lp2.getX());
            double y = lp1.getY();
            if(p.getX() >= x1 && p.getX() <= x2 && p.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public static double getSqDistanceFromLineToPoint(Vec2 lp1, Vec2 lp2, Vec2 p) {
        double k1 = getLineK(lp1, lp2);
        double b1 = lp1.getY() - lp1.getX() * k1;
        double k2 = -1/k1;
        double b2 = p.getY() - p.getX() * k2;
        double x = (b2 - b1) / (k1 - k2);
        double y = k1 * x + b1;
        return (x - p.getX())*(x - p.getX()) + (y - p.getY())*(y - p.getY());
    }

    public static double getDistanceFromLineToPoint(Vec2 lp1, Vec2 lp2, Vec2 p) {
        return Math.sqrt(getSqDistanceFromLineToPoint(lp1, lp2, p));
    }

    public static double getSqDistanceFromLineSegmentToPoint(Vec2 lp1, Vec2 lp2, Vec2 p) {
        double k1 = getLineK(lp1, lp2);
        double b1 = lp1.getY() - lp1.getX() * k1;
        double k2 = -1/k1;
        double b2 = p.getY() - p.getX() * k2;
        double x = (b2 - b1) / (k1 - k2);
        double y = k1 * x + b1;

        if(checkPointAtLineSegment(new Vec2(x, y), lp1, lp2))
            return (x - p.getX())*(x - p.getX()) + (y - p.getY())*(y - p.getY());
        else
            return Math.min((lp1.getX() - p.getX())*(lp1.getX() - p.getX()) + (lp1.getY() - p.getY())*(lp1.getY() - p.getY()),
                    (lp2.getX() - p.getX())*(lp2.getX() - p.getX()) + (lp2.getY() - p.getY())*(lp2.getY() - p.getY()));
    }

    public static double getDistanceFromLineSegmentToPoint(Vec2 lp1, Vec2 lp2, Vec2 p) {
        return Math.sqrt(getSqDistanceFromLineSegmentToPoint(lp1, lp2, p));
    }

    public static boolean checkCirclesCollision(Vec2 o1, double r1, Vec2 o2, double r2) {
        return (o1.getX() - o2.getX())*(o1.getX() - o2.getX()) + (o1.getY() - o2.getY())*(o1.getY() - o2.getY()) <= (r1 + r2)*(r1 + r2);
    }


    //not working properly
    public static Vec2[] getCirclesCollision(Vec2 o1, double r1, Vec2 o2, double r2) {
        double ox = o2.getX() - o1.getX();
        double oy = o2.getY() - o1.getY();
        double A = -2 * ox;
        double B = -2 * oy;
        double C = ox*ox + oy*oy + r1*r1 - r2*r2;

        double lineK = - A / B;
        double lineB = - C / B;
        double a = lineK*lineK + 1;
        double b = 2 * (lineK * (lineB - oy) - ox);
        double c = ox*ox + (lineB - oy)*(lineB - oy) - r2*r2;
        double D = b*b - 4 * a * c;

        if(D < 0) {
            return new Vec2[] {};
        }
        if(D == 0) {
            double x, y;
            x = - b / (2 * a) + o1.getX();
            y = lineK * x + lineB + o1.getY();
            Vec2 point = new Vec2(x, y);
            return new Vec2[] {point};
        }
        double x1, x2, y1, y2;
        x1 = - (b + Math.sqrt(D)) / (2 * a) + o1.getX();
        y1 = lineK * x1 + lineB + o1.getY();
        x2 = - (b - Math.sqrt(D)) / (2 * a) + o1.getX();
        y2 = lineK * x2 + lineB + o1.getY();
        Vec2 point1 = new Vec2(x1, y1);
        Vec2 point2 = new Vec2(x2, y2);
        return new Vec2[] {point1, point2};
    }

    public static boolean checkCircleWithLineSegmentCollision(Vec2 o, double r, Vec2 lp1, Vec2 lp2) {
        return getSqDistanceFromLineSegmentToPoint(lp1, lp2, o) <= r*r;
    }

    public static Vec2[] getCircleWithLineSegmentCollision(Vec2 o, double r, Vec2 lp1, Vec2 lp2) {
        double lineK = getLineK(lp1, lp2);
        double lineB = lp1.getY() - lp1.getX() * lineK;
        double a = lineK*lineK + 1;
        double b = 2 * (lineK * (lineB - o.getY()) - o.getX());
        double c = o.getX()*o.getX() + (lineB - o.getY())*(lineB - o.getY()) - r*r;
        double D = b*b - 4 * a * c;

        if(D < 0) {
            return new Vec2[] {};
        }
        if(D == 0) {
            double x, y;
            x = - b / (2 * a);
            y = lineK * x + lineB;
            Vec2 point = new Vec2(x, y);
            if(checkPointAtLineSegment(point, lp1, lp2)) {
                return new Vec2[] {point};
            }
            return new Vec2[] {};
        }
        double x1, x2, y1, y2;
        double sqrtD = Math.sqrt(D);
        x1 = - (b + sqrtD) / (2 * a);
        y1 = lineK * x1 + lineB;
        x2 = - (b - sqrtD) / (2 * a);
        y2 = lineK * x2 + lineB;
        Vec2 point1 = new Vec2(x1, y1);
        Vec2 point2 = new Vec2(x2, y2);
        ArrayList<Vec2> points = new ArrayList<Vec2>();
        int i = 0;
        if(checkPointAtLineSegment(point1, lp1, lp2)) {
            points.add(point1);
            i++;
        }
        if(checkPointAtLineSegment(point2, lp1, lp2)) {
            points.add(point2);
            i++;
        }
        return points.toArray(new Vec2[i]);
    }

    public static double getLineK(Vec2 p1, Vec2 p2) {
        return MathUtil.clamp(-LIMIT_VALUE, (p2.getY() - p1.getY()) / (p2.getX() - p1.getX()), LIMIT_VALUE);
    }

    public static Vec2[] getShapeWithCircleCollision(Vec2[] shape, Vec2 o, double r) {
        ArrayList<Vec2> points = new ArrayList<Vec2>();
        Vec2[] buffer;
        for(int i = 0; i < shape.length; i++) {
            if(i == shape.length - 1) {
                buffer = getCircleWithLineSegmentCollision(o, r, shape[i], shape[0]);
            } else {
                buffer = getCircleWithLineSegmentCollision(o, r, shape[i], shape[i+1]);
            }
            Collections.addAll(points, buffer);
        }
        return points.toArray(new Vec2[points.size()]);
    }
}
