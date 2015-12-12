package umiker9.stardust2d;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import umiker9.stardust2d.graphics.lwjgl2.Renderer;
import umiker9.stardust2d.graphics.lwjgl2.Window;
import umiker9.stardust2d.systems.error.Error;
import umiker9.stardust2d.systems.error.ErrorBuilder;
import umiker9.stardust2d.systems.error.ErrorStack;
import umiker9.stardust2d.systems.io.HID.InputManager;
import umiker9.stardust2d.systems.log.Logger;
import umiker9.stardust2d.util.TimeUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by miker9 on 22/11/2015.
 */
public class BasicGame {
    protected Window window;
    protected Renderer renderer;
    protected InputManager inputManager;
    protected Map<String, Scene> scenes = new HashMap<>();
    protected Scene currentScene;
    private String title;
    private int width;
    private int height;
    private boolean fullScreen;
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
        this(640, 480, title);
    }

    public void launch() {
        //Start logger

        try {
            File f = new File("log.txt");
            if((!f.exists() || f.delete()) && f.createNewFile()) {
                PrintStream toFile = new PrintStream(f);
                Logger.createInstance(new Logger(System.out, toFile));
            }
        } catch (IOException e) {
            ErrorStack.addError(new ErrorBuilder().setLevel(Error.WARNING)
                    .setMessage("Could not create log.txt, logging to system.out").setException(e).finish());

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
            ErrorStack.addError(new ErrorBuilder().setLevel(Error.CRITICAL_ERROR)
                    .setMessage("Could not initialise display").setException(e).finish());

            Logger.warnInst("[Stardust] Error during LWJGL initialisation. Exiting..");
            exit(1);
        }
        window.setTitle(title);
        window.setVSyncEnabled(true);

        Logger.logInst("[Stardust] initialising renderer");
        renderer = new Renderer(width, height);
        renderer.init();
        Logger.logInst("[Stardust] Using OpenGL " + renderer.getGLVersion());
        Logger.logInst("[Stardust] Initialising input");
        inputManager = new InputManager();
        Logger.logInst("[Stardust] Engine initialisation is finished");
    }

    protected void run() {
        lastUpdateTime = TimeUtil.getCurrentTime();

        while (!window.isCloseRequested()) {
            //Handle errors
            while(ErrorStack.hasErrors()) {
                logError(ErrorStack.getNextError());
            }

            //Check if window was resized
            if(window.wasResized()) {
                window.updateViewport();
                renderer.setWidth(window.getWidth());
                renderer.setHeight(window.getHeight());
            }

            //Calculate delta
            long currentTime = TimeUtil.getCurrentTime();
            double delta = (currentTime - lastUpdateTime) / (double) TimeUtil.getTimePrecission();
            lastUpdateTime = currentTime;

            //Handle input
            inputManager.handleInput();

            //Update and render
            double maxTickTime = 1.0 / Stardust2D.minTPS;

            //If we were lagging for too long, just skip some updates
            if (delta > 10 * maxTickTime) {
                delta = maxTickTime;
                Logger.logInst("[Stardust] Houston, it feels a bit laggy here");
            }

            //Spread delta between many udates, to avoid tunneling
            while (delta > maxTickTime) {
                update(maxTickTime);
                delta -= maxTickTime;
            }
            update(delta);

            //Render
            render();

            //Update display
            Display.update();
        }
    }

    private void update(double delta) {
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
        while (ErrorStack.hasErrors()) {
            logError(ErrorStack.getNextError());
        }

        if(errorCode == 0) {
            Logger.logInst("[Stardust] Exiting...");
        } else {
            Logger.warnInst("[Stardust] Exiting with error code: " + errorCode);
        }
        System.exit(errorCode);
    }

    private void logError(Error error) {
        Logger.warnInst("[Stardust] " + error.toString());
    }

    public void addScene(Scene scene) {
        scenes.put(scene.getName(), scene);
        inputManager.addInputListener(scene);
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(String sceneName) {
        if (scenes.containsKey(sceneName)) {
            Logger.logInst("[Stardust] Entering scene [" + sceneName + "]");
            if (this.currentScene != null) {
                this.currentScene.onSceneLeft();
            }

            this.currentScene = scenes.get(sceneName);
            currentScene.onSceneEntered();
        } else {
            ErrorStack.addError(new ErrorBuilder().setLevel(Error.WARNING)
                    .setMessage("Could not find scene [" + sceneName + "]").finish());
        }
    }

    public Scene getSceneByName(String sceneName) {
        return scenes.get(sceneName);
    }

    public Window getWindow() {
        return window;
    }

    public Renderer getRenderer() {
        return renderer;
    }
}
