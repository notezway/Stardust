package umiker9.stardust2d;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import paulscode.sound.SoundSystem;
import umiker9.stardust2d.graphics.lwjgl2.Renderer;
import umiker9.stardust2d.graphics.lwjgl2.Window;
import umiker9.stardust2d.systems.error.ErrorStack;
import umiker9.stardust2d.systems.io.HID.InputManager;
import umiker9.stardust2d.systems.log.Logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by miker9 on 22/11/2015.
 */
public class BasicGame {
    protected Window window;
    protected Renderer renderer;
    protected Scene currentScene;
    protected InputManager inputManager;
    protected SoundSystem soundSystem;
    private int width;
    private int height;
    private boolean fullScreen;
    private double graphicsScale;
    private String title;
    private long lastUpdateTime;

    public BasicGame(int width, int height, double graphicsScale, boolean fullScreen, String title) {
        this.width = width;
        this.height = height;
        this.fullScreen = fullScreen;
        this.title = title;
        this.graphicsScale = graphicsScale;
    }

    public BasicGame(int width, int height, double graphicsScale,  String title) {
        this(width, height, graphicsScale, false, title);
    }

    public BasicGame(int width, int height, String title) {
        this(width, height, 1, false, title);
    }

    public BasicGame(String title) {
        this(640, 480, title);
    }

    public void start() {
        boolean success = false;
        try {
            File f = new File("log.txt");
            if((!f.exists() || f.delete()) && f.createNewFile()) {
                PrintStream toFile = new PrintStream(f);
                Logger.createInstance(new Logger(System.out, toFile));
                success = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!success) {
            Logger.createDefaultInstance();
        }

        Logger.logInst("[Stardust] " + Stardust2D.getName() + " " + Stardust2D.getVersion());

        init();
        run();
        exit();
    }

    protected void init() {
        Logger.logInst("[Stardust] Initialising display");

        try {
            window = new Window(width, height, fullScreen);
        } catch (LWJGLException e) {
            e.printStackTrace();
            Logger.warnInst("[Stardust] Error during LWJGL initialisation. Exiting..");
            exit(1);
        }
        window.setTitle(title);
        window.setVSyncEnabled(true);

        Logger.logInst("[Stardust] initialising renderer");
        renderer = new Renderer((int)(width*graphicsScale), (int)(height*graphicsScale), true);
        renderer.init();
        Logger.logInst("[Stardust] Using OpenGL " + renderer.getGLVersion());

        Logger.logInst("[Stardust] Initialising input");
        inputManager = new InputManager();
        Logger.logInst("[Stardust] Engine initialisation is finished");
    }

    protected void run() {
        lastUpdateTime = System.nanoTime();

        while (!window.isCloseRequested()) {
            //Handle errors
            while(ErrorStack.hasErrors()) {
                Logger.warnInst("[Stardust] " + ErrorStack.getNextError());
            }

            //Check if window was resized
            if(window.wasResized()) {
                window.updateViewport();
                renderer.setWidth((int)(window.getWidth()*graphicsScale));
                renderer.setHeight((int)(window.getHeight()*graphicsScale));
            }

            //Calculate delta
            long currentTime = System.nanoTime();
            long delta = currentTime - lastUpdateTime;
            lastUpdateTime = currentTime;

            //Handle input
            inputManager.handleInput();

            //Update and render
            update(delta);
            render();

            //Update display
            Display.update();
        }
    }

    private void update(long delta) {
        if(currentScene != null) {
            currentScene.update(delta);
        }
    }

    private void render() {
        renderer.clearScreen();
        if(currentScene != null) {
            currentScene.render(renderer);
        }
    }

    protected void exit() {
        exit(0);
    }

    private void exit(int errorCode) {
        if(errorCode == 0) {
            Logger.logInst("[Stardust] Exiting with no errors");
        } else {
            Logger.warnInst("[Stardust] Exiting with error code: " + errorCode);
        }
        System.exit(errorCode);
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    public Window getWindow() {
        return window;
    }

    public Renderer getRenderer() {
        return renderer;
    }
}
