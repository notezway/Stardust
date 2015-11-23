package umiker9.stardust2d.test;

import umiker9.stardust2d.BasicGame;
import umiker9.stardust2d.Sprite;
import umiker9.stardust2d.graphics.lwjgl2.Renderer;
import umiker9.stardust2d.Scene;
import umiker9.stardust2d.Stardust2D;

/**
 * Created by miker9 on 22/11/2015.
 */

public class TestGame1 extends BasicGame {
    private static TestGame1 instance;

    private Scene mainScene;

    public static void main(String args[]) {
        instance = new TestGame1();
        instance.start();
    }

    public TestGame1() {
        super(800, 600, "Test game 1");
    }

    @Override
    protected void init() {
        super.init();

        mainScene = new Scene();
        Sprite testSprite = new Sprite(100, 100, 50, 50);
        testSprite.setRotation(10);
        mainScene.add(testSprite);
        setCurrentScene(mainScene);
    }
}
