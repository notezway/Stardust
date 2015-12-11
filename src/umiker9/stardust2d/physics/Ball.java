package umiker9.stardust2d.physics;

import umiker9.stardust2d.graphics.lwjgl2.Renderer;
import umiker9.stardust2d.graphics.lwjgl2.ShapesDrawer;
import umiker9.stardust2d.math.Vec2;
import umiker9.stardust2d.math.geometry.CircleShape;

/**
 * Created by Notezway on 08.12.2015.
 */
public class Ball extends PhysicalBody {

    public Ball(Vec2 pos, double rot, double radius, double mass) {
        super(pos, rot, mass, new CircleShape(new Vec2(), radius));
    }

    public void draw(Renderer renderer) {
        ShapesDrawer.drawCircle(renderer, (CircleShape) shapes[0], 32);
        //ShapesDrawer.drawVector(renderer, getVelocity().scale(0.25), Color.GREEN);
    }

    @Override
    public void onCollision(PhysicalBody another) {
        if(another instanceof Ball) {
            Ball ball = (Ball) another;
            Vec2 o1 = getPosition();
            Vec2 o2 = ball.getPosition();
            double r1 = shapes[0].getSize() / 2;
            double r2 = ball.shapes[0].getSize() / 2;
            double m1 = mass;
            double m2 = ball.mass;
            Vec2 v10 = getVelocity();
            Vec2 v20 = ball.getVelocity();
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
            Vec2 nv2 = nd.rotate(Math.PI / 2 * Math.signum(d.dot(new Vec2(0, 1))) * Math.signum(d.dot(new Vec2(1, 0)))).scale(v2y);
            System.out.println(nv2.toString());
            //double cos2 = nv2.dot(new Vec2(1, 0));
            //double v2yx = d.normalize().negate().rotate(Math.PI / 2).scale(v2y);
            //double v2yy = Math.sqrt(v2y * v2y - v2yx * v2yx);
            v2 = nd.scale(nv2x).add(v10).add(nv2);
            this.setVelocity(v1);
            ball.setVelocity(v2);
            System.out.println("body 1:" + v1.getLength());
            System.out.println("body 2:" + v1.getLength());
            this.forceX = 0;
            this.forceY = 0;
            ball.forceX = 0;
            ball.forceY = 0;
            this.setPosition(o1.add(d.negate().scale(((r1 + r2) - dL) / (r1 + r2) / 2)));
            ball.setPosition(o2.add(d.scale(((r1 + r2) - dL) / (r1 + r2) / 2)));
        }
    }
}
