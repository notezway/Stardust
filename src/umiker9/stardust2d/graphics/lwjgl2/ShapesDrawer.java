package umiker9.stardust2d.graphics.lwjgl2;

import umiker9.stardust2d.graphics.Color;
import umiker9.stardust2d.math.geometry.CircleShape;
import umiker9.stardust2d.math.Vec2;

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
        glBegin(GL_POLYGON);
        for(double a = 0; a < 2*Math.PI; a += 2*Math.PI / quality) {
            glVertex2d(r * Math.cos(a), r * Math.sin(a));
        }
        glEnd();
        renderer.popMatrix();
    }

    public static void drawVector(Renderer renderer, Vec2 vec, Color color) {
        Texture.none.bind();
        renderer.setColor(color);

        /*double a = Math.toDegrees(Math.acos(vec.dot(new Vec2(1, 0)) / vec.getLength()));
        double da = 20;*/

        glBegin(GL_LINES);
        glVertex2d(0, 0);
        glVertex2d(vec.getX(), vec.getY());
        glEnd();

        /*renderer.pushMatrix();
        renderer.translate(vec.getX(), vec.getY());
        renderer.rotate(-a - da);
        glBegin(GL_LINES);
        glVertex2d(0, 0);
        glVertex2d(-10, 0);
        glEnd();
        renderer.rotate(2 * da);
        glBegin(GL_LINES);
        glVertex2d(0, 0);
        glVertex2d(-10, 0);
        glEnd();
        renderer.popMatrix();*/
    }
}
