package umiker9.stardust2d.graphics.lwjgl2;

import umiker9.stardust2d.systems.log.LogLevel;
import umiker9.stardust2d.systems.log.Logger;
import umiker9.stardust2d.systems.log.Message;

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
        glClearColor(1, 0, 0, 0);
        glEnable(GL_TEXTURE_2D);

        updateMatrices();
    }

    public void updateMatrices() {
        if(invertYAxis) {
            glOrtho(0, width, height, 0, 0, 1);
        } else {
            glOrtho(0, width, 0, height, 0, 1);
        }
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

    //Draw functions

    public void clearScreen() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void drawQuad(int x, int y, int width, int height) {
        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x + width, y);
        glVertex2f(x + width, y + height);
        glVertex2f(x, y + height);
        glEnd();
    }

    public void drawTexturedQuad(int x, int y, int width, int height, Texture2D tex) {
        tex.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(1, 1); glVertex2f(x, y);
        glTexCoord2f(-1, 1); glVertex2f(x + width, y);
        glTexCoord2f(-1, -1); glVertex2f(x + width, y + height);
        glTexCoord2f(1, -1); glVertex2f(x, y + height);
        glEnd();
    }

    public void drawRotTexQuad(int x, int y, int width, int height, float rot, Texture2D tex) {
        glPushMatrix();
        glRotatef(rot, 1, 1, 0);
        drawTexturedQuad(x, y, width, height, tex);
        glPopMatrix();
    }


    //So many of them
}