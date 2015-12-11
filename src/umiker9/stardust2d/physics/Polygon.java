package umiker9.stardust2d.physics;

import umiker9.stardust2d.graphics.Color;
import umiker9.stardust2d.graphics.lwjgl2.Renderer;
import umiker9.stardust2d.graphics.lwjgl2.ShapesDrawer;
import umiker9.stardust2d.math.Vec2;
import umiker9.stardust2d.math.geometry.PolygonShape;
import umiker9.stardust2d.math.geometry.Shape;

/**
 * Created by Notezway on 11.12.2015.
 */
public class Polygon extends PhysicalBody {

    public Polygon(Vec2 pos, double rot, double mass, double size, Shape... shapes) {
        super(pos, rot, mass, size, shapes);
    }

    public void draw(Renderer renderer) {
        ShapesDrawer.drawPolygon(renderer, (PolygonShape) shapes[0], Color.GREEN);
    }
}
