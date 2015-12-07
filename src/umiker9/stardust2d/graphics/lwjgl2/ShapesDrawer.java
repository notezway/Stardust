package umiker9.stardust2d.graphics.lwjgl2;

import umiker9.stardust2d.CircleShape;
import umiker9.stardust2d.graphics.Color;
import umiker9.stardust2d.math.Vec2;
import umiker9.stardust2d.systems.log.Logger;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Notezway on 07.12.2015.
 */
public class ShapesDrawer {

    public static void drawCircle(Renderer renderer, CircleShape circle, int quality) {
        Vec2 o = circle.getCenterPoint();
        double r = circle.getRadius();
        renderer.pushMatrix();
        renderer.translate(o.getX(), o.getY());
        Texture.none.bind();
        glBegin(GL_LINE_LOOP);
        for(double a = 0; a < 2*Math.PI; a += 2*Math.PI / quality) {
            glVertex2d(r * Math.cos(a), r * Math.sin(a));
        }
        glEnd();
        renderer.popMatrix();
    }
}
