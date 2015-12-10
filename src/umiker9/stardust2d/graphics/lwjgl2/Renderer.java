package umiker9.stardust2d.graphics.lwjgl2;

import umiker9.stardust2d.Tile;
import umiker9.stardust2d.graphics.Color;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by miker9 on 22/11/2015.
 */
public class Renderer {
    protected int width;
    protected int height;
    protected boolean invertYAxis;

    public Renderer(int width, int height, boolean invertYAxis) {
        this.width = width;
        this.height = height;
        this.invertYAxis = invertYAxis;
    }

    public Renderer(int width, int height) {
        this(width, height, false);
    }

    public void init() {
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);

        updateMatrices();
    }

    public void updateMatrices() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        if(invertYAxis) {
            glOrtho(0, width, height, 0, 0, 1);
        } else {
            glOrtho(0, width, 0, height, 0, 1);
        }

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        updateMatrices();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        updateMatrices();
    }

    public boolean isInvertYAxis() {
        return invertYAxis;
    }

    public void setInvertYAxis(boolean invertYAxis) {
        this.invertYAxis = invertYAxis;
        updateMatrices();
    }

    public String getGLVersion() {
        return glGetString(GL_VERSION);
    }

    //matrix functions

    public void pushMatrix() {
        glPushMatrix();
    }

    public void popMatrix() {
        glPopMatrix();
    }

    public void translate(double x, double y) {
        translate(x, y, 0);
    }

    public void translate(double x, double y, double z) {
        glTranslated(x, y, z);
    }

    public void rotate(double angle) {
        rotate(angle, 0, 0, 1);
    }

    public void rotate(double angle, double x, double y, double z) {
        if(invertYAxis) {
            glRotated(angle, x, y, -z);
        } else {
            glRotated(angle, x, y, z);
        }
    }

    public void scale(double x, double y) {
        glScaled(x, y, 1);
    }

    //Draw functions

    public void clearScreen() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void drawQuad(double x, double y, double width, double height) {
        glBegin(GL_QUADS);
        glVertex2d(x, y);
        glVertex2d(x + width, y);
        glVertex2d(x + width, y + height);
        glVertex2d(x, y + height);
        glEnd();
    }

    public void drawTexturedQuad(double x, double y, double width, double height, Texture2D texture) {
        if (texture != null && texture.isInitialized()) {
            texture.bind();
        } else {
            Texture.none.bind();
        }
        glBegin(GL_QUADS);

        if(invertYAxis) {
            glTexCoord2d(1, 0); glVertex2d(x + width, y);
            glTexCoord2d(0, 0);
            glVertex2d(x, y);
            glTexCoord2d(0, 1); glVertex2d(x, y + height);
            glTexCoord2d(1, 1);
            glVertex2d(x + width, y + height);
        } else {
            glTexCoord2d(1, 0); glVertex2d(x + width, y + height);
            glTexCoord2d(0, 0); glVertex2d(x, y + height);
            glTexCoord2d(0, 1);
            glVertex2d(x, y);
            glTexCoord2d(1, 1);
            glVertex2d(x + width, y);
        }
        glEnd();
    }

    public void drawTexturedQuad(double x, double y, double width, double height, Tile tile) {
        if (tile == null) {
            tile = new Tile((Texture2D) Texture.none);
        }

        Texture texture = tile.getTexture();

        if (texture != null && texture.isInitialized()) {
            texture.bind();
        } else {
            Texture.none.bind();
        }

        double s1 = tile.getS1();
        double t1 = tile.getT1();
        double s2 = tile.getS2();
        double t2 = tile.getT2();

        glBegin(GL_QUADS);

        if (invertYAxis) {
            glTexCoord2d(s2, t1);
            glVertex2d(x + width, y);
            glTexCoord2d(s1, t1);
            glVertex2d(x, y);
            glTexCoord2d(s1, t2);
            glVertex2d(x, y + height);
            glTexCoord2d(s2, t2);
            glVertex2d(x + width, y + height);
        } else {
            glTexCoord2d(s2, t1);
            glVertex2d(x + width, y + height);
            glTexCoord2d(s1, t1);
            glVertex2d(x, y + height);
            glTexCoord2d(s1, t2);
            glVertex2d(x, y);
            glTexCoord2d(s2, t2);
            glVertex2d(x + width, y);
        }
        glEnd();
    }

    //Other functions

    public void setColor(Color color) {
        glColor4d(color.getR(), color.getG(), color.getB(), color.getA());
    }
}