package umiker9.stardust2d.graphics.lwjgl2;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;

/**
 * Created by miker9 on 22/11/2015.
 */
public class Window {

    public Window(int width, int height, boolean fullscreen) throws LWJGLException {

        if(!fullscreen) {
            Display.setDisplayMode(new DisplayMode(width, height));
        } else {
            Display.setDisplayModeAndFullscreen(new DisplayMode(width, height));
        }

        Display.create();
    }

    public int getX() {
        return Display.getX();
    }

    public int getY() {
        return Display.getY();
    }

    public int getWidth() {
        return Display.getWidth();
    }

    public int getHeight() {
        return Display.getHeight();
    }

    public void destroy() {
        Display.destroy();
    }

    public int setIcon(ByteBuffer[] icons) {
        return Display.setIcon(icons);
    }

    public void setResizable(boolean resizable) {
        Display.setResizable(resizable);
    }

    public boolean isResizable() {
        return Display.isResizable();
    }

    public void update() {
        Display.update();
    }

    public void sync(int fps) {
        Display.sync(fps);
    }

    public boolean isCloseRequested() {
        return Display.isCloseRequested();
    }

    public void setTitle(String title) {
        Display.setTitle(title);
    }

    public String getTitle() {
        return Display.getTitle();
    }

    public boolean isFullscreen() {
        return Display.isFullscreen();
    }

    public boolean wasResized() {
        return Display.wasResized();
    }

    public void updateViewport() {
        GL11.glViewport(0, 0, getWidth(), getHeight());
    }

    public void setFullscreen(boolean fullscreen) throws LWJGLException {
        Display.setFullscreen(fullscreen);
    }

    public void setVSyncEnabled(boolean sync) {
        Display.setVSyncEnabled(sync);
    }

}
