package umiker9.stardust2d.Graphics.LWJGL2;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;

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

    public static int getX() {
        return Display.getX();
    }

    public static int getY() {
        return Display.getY();
    }

    public static int getWidth() {
        return Display.getWidth();
    }

    public static int getHeight() {
        return Display.getHeight();
    }

    public static void destroy() {
        Display.destroy();
    }

    public static int setIcon(ByteBuffer[] icons) {
        return Display.setIcon(icons);
    }

    public static void setResizable(boolean resizable) {
        Display.setResizable(resizable);
    }

    public static boolean isResizable() {
        return Display.isResizable();
    }

    public static void update() {
        Display.update();
    }

    public static void sync(int fps) {
        Display.sync(fps);
    }

    public static boolean isCloseRequested() {
        return Display.isCloseRequested();
    }

    public void setTitle(String title) {
        Display.setTitle(title);
    }

    public static String getTitle() {
        return Display.getTitle();
    }

    public static boolean isFullscreen() {
        return Display.isFullscreen();
    }

    public static void setFullscreen(boolean fullscreen) throws LWJGLException {
        Display.setFullscreen(fullscreen);
    }

    public static void setVSyncEnabled(boolean sync) {
        Display.setVSyncEnabled(sync);
    }

}
