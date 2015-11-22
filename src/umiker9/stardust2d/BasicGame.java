package umiker9.stardust2d;

import umiker9.stardust2d.Graphics.LWJGL2.Renderer;
import umiker9.stardust2d.Graphics.LWJGL2.Window;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import umiker9.stardust2d.Systems.Error.Error;
import umiker9.stardust2d.Systems.Error.ErrorBuilder;
import umiker9.stardust2d.Systems.Error.ErrorStack;

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
        System.out.println("[Stardust] " + Stardust2D.getName() + " " + Stardust2D.getVersion());

        init();
        run();
        exit();
    }

    protected void init() {
        System.out.println("[Stardust] Initialising display");
        try {
            window = new Window(width, height, fullScreen);
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.out.println("[Stardust] Error during LWJGL initialisation. Exiting..");
            exit(1);
        }
        window.setTitle(title);
        window.setVSyncEnabled(true);

        System.out.println("[Stardust] initialising renderer");
        renderer = new Renderer(width, height, true);
        renderer.init();
        System.out.println("[Stardust] Using OpenGL " + renderer.getGLVersion());
        System.out.println("[Stardust] Engine initialisation is finished");
    }

    protected void run() {
        lastUpdateTime = System.nanoTime();
        ErrorStack.addError(new ErrorBuilder().setLevel(Error.CRITICAL_ERROR).setMessage("Test").finish());

        while (!Window.isCloseRequested()) {

            while(ErrorStack.hasErrors()) {
                System.out.println("[Stardust] " + ErrorStack.getNextError());
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
            System.out.println("[Stardust] Exiting with no errors");
        } else {
            System.out.println("[Stardust] Exiting with error code " + errorCode);
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
