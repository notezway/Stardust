package umiker9.stardust2d;

import umiker9.stardust2d.Graphics.LWJGL2.Renderer;
import umiker9.stardust2d.Graphics.LWJGL2.Window;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import umiker9.stardust2d.Systems.Error.Error;
import umiker9.stardust2d.Systems.Error.ErrorBuilder;
import umiker9.stardust2d.Systems.Error.ErrorStack;
import umiker9.stardust2d.Systems.Log.LogLevel;
import umiker9.stardust2d.Systems.Log.Logger;
import umiker9.stardust2d.Systems.Log.Message;

import java.io.*;

/**
 * Created by miker9 on 22/11/2015.
 */
public class BasicGame {
    protected int width;
    protected int height;
    protected boolean fullScreen;
    protected String title;
    protected Window window;
    protected Renderer renderer;
    protected Scene currentScene;

    private long lastUpdateTime;

    public BasicGame(int width, int height, boolean fullScreen, String title) {
        this.width = width;
        this.height = height;
        this.fullScreen = fullScreen;
        this.title = title;
    }

    public BasicGame(int width, int height, String title) {
        this(width, height, false, title);
    }

    public BasicGame(String title) {
        this(640, 480, false, title);
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
        Logger.setDefaultLevelInst(LogLevel.INFO);
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
            Logger.logInst(Logger.newCritError("[Stardust] Error during LWJGL initialisation. Exiting.."));
            exit(1);
        }
        window.setTitle(title);
        window.setVSyncEnabled(true);

        Logger.logInst("[Stardust] initialising renderer");
        renderer = new Renderer(width, height, true);
        renderer.init();
        Logger.logInst("[Stardust] Using OpenGL " + renderer.getGLVersion());
        Logger.logInst("[Stardust] Engine initialisation is finished");
    }

    protected void run() {
        lastUpdateTime = System.nanoTime();
        ErrorStack.addError(new ErrorBuilder().setLevel(Error.CRITICAL_ERROR).setMessage("Test").finish());

        while (!Window.isCloseRequested()) {

            while(ErrorStack.hasErrors()) {
                Logger.setLevelInst(LogLevel.ERROR);
                Logger.logInst("[Stardust] " + ErrorStack.getNextError());
            }


            long currentTime = System.nanoTime();
            long delta = currentTime - lastUpdateTime;
            lastUpdateTime = currentTime;



            //handle input
            update(delta);
            render();
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
            Logger.logInst(new Message(LogLevel.ERROR, "[Stardust] Exiting with error code " + errorCode));
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
