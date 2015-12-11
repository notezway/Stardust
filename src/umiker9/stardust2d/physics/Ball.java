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
        super(pos, rot, mass, radius*2, new CircleShape(new Vec2(), radius));
    }

    public void draw(Renderer renderer) {
        ShapesDrawer.drawCircle(renderer, (CircleShape) shapes[0], 32);
        //ShapesDrawer.drawVector(renderer, getVelocity().scale(0.25), Color.GREEN);
    }
}
