package umiker9.stardust2d.physics;

import umiker9.stardust2d.math.Vec2;

/**
 * Created by Notezway on 11.12.2015.
 */
public class Collider {

    public static void onCollide(PhysicalBody body1, PhysicalBody body2) {
        System.out.println("Body 1: " + body1.getClass().getName());
        System.out.println("Body 2: " + body2.getClass().getName());
        if(body1 instanceof Ball) {
            if(body2 instanceof Ball) {
                onCollideBalls((Ball)body1, (Ball)body2);
            } else if(body2 instanceof Polygon) {
                onCollidePolygon((Polygon)body2, body1);
            }
        } else if(body1 instanceof Polygon) {
            onCollidePolygon((Polygon)body1, body2);
        }
    }

    private static void onCollideBalls(Ball ball1, Ball ball2) {
        Vec2 o1 = ball1.getPosition();
        Vec2 o2 = ball2.getPosition();
        double r1 = ball1.shapes[0].getSize() / 2;
        double r2 = ball2.shapes[0].getSize() / 2;
        double m1 = ball1.mass;
        double m2 = ball2.mass;
        Vec2 v10 = ball1.getVelocity();
        Vec2 v20 = ball2.getVelocity();
        System.out.println("body 1:" + v10.getLength());
        System.out.println("body 2:" + v10.getLength());
        Vec2 v1;
        Vec2 v2 = v20.subtract(v10);
        Vec2 d = o2.subtract(o1);
        //double absV1 = v1.getLength();
        double absV2 = v2.getLength();
        double dot = d.dot(v2);
        if(dot >= 0) return;
        double dL = d.getLength();
        double cos = dot / (dL * absV2);
        double v2x = absV2 * cos;
        double v2y = Math.sqrt(absV2 * absV2 - v2x * v2x);
        double nv1x = 2 * m2 * v2x / (m1 + m2);
        double nv2x = (m2 - m1) * v2x / (m1 + m2);
        Vec2 nd = d.normalize();
        v1 = nd.scale(nv1x).add(v10);
        Vec2 nv2 = nd.rotate(-Math.PI / 2).scale(v2y);
        System.out.println(nv2.toString());
        //double cos2 = nv2.dot(new Vec2(1, 0));
        //double v2yx = d.normalize().negate().rotate(Math.PI / 2).scale(v2y);
        //double v2yy = Math.sqrt(v2y * v2y - v2yx * v2yx);
        v2 = nd.scale(nv2x).add(v10).add(nv2);
        ball1.setVelocity(v1);
        ball2.setVelocity(v2);
        System.out.println("body 1:" + v1.getLength());
        System.out.println("body 2:" + v1.getLength());
        //this.forceX = 0;
        //this.forceY = 0;
        //ball.forceX = 0;
        //ball.forceY = 0;
        ball1.setPosition(o1.add(d.negate().scale(((r1 + r2) - dL) / (r1 + r2) / 2)));
        ball2.setPosition(o2.add(d.scale(((r1 + r2) - dL) / (r1 + r2) / 2)));
    }

    private static void onCollidePolygon(Polygon polygon, PhysicalBody body2) {
        Vec2 o1 = polygon.getPosition();
        Vec2 o2 = body2.getPosition();
        Vec2 d1 = o2.subtract(o1); //from o1 to o2
        Vec2 d2 = d1.negate();     //from o2 to o1

        Vec2 v1 = polygon.getVelocity();
        Vec2 v2 = body2.getVelocity();

        Vec2 relV1 = d1.normalize().scale(v1.projection(d1));
        Vec2 relV2 = d2.normalize().scale(v2.projection(d2));

        System.out.println(relV1.getLength());
        System.out.println(relV2.getLength());

        polygon.setVelocity(v1.subtract(relV1));
        body2.setVelocity(v2.subtract(relV2));


        Vec2 acc1 = polygon.getAcceleration();
        Vec2 acc2 = body2.getAcceleration();
        Vec2 nAcc1 = acc1.add(relV1);
        Vec2 nAcc2 = acc2.add(relV2);

        //polygon.setAcceleration(nAcc1);
        //body2.setAcceleration(nAcc2);

        double dL = d1.getLength();

        polygon.setPosition(polygon.getPosition().add(d2.scale(1/dL)));
        body2.setPosition(body2.getPosition().add(d1.scale(1/dL)));

        /*double dL = d1.getLength();
        double sumSize = polygon.get
        polygon.setPosition(o1.add(d2.scale()));*/
    }
}
