package umiker9.stardust2d.test;

import org.lwjgl.input.Keyboard;
import umiker9.stardust2d.BasicGame;
import umiker9.stardust2d.Scene;
import umiker9.stardust2d.Sprite;
import umiker9.stardust2d.Stardust2D;
import umiker9.stardust2d.graphics.lwjgl2.Texture2D;
import umiker9.stardust2d.graphics.lwjgl2.TextureLoader;
import umiker9.stardust2d.systems.io.FileIO;
import umiker9.stardust2d.systems.io.HID.InputListener;
import umiker9.stardust2d.systems.io.HID.KeyboardEvent;

/**
 * Created by miker9 on 22/11/2015.
 */

public class TestGame1 extends BasicGame {
    private static TestGame1 instance;

    private Scene mainScene;

    public TestGame1() {
        super(800, 600, "Test game 1");
    }

    public static void main(String args[]) {
        instance = new TestGame1();
        instance.start();
    }

    @Override
    protected void init() {
        super.init();

        //renderer.setInvertYAxis(false);

        Texture2D texture = TextureLoader.loadTexture(FileIO.getResource("Assets/jackal.png"));
        Texture2D background = TextureLoader.loadTexture(FileIO.getResource("Assets/Stars.png"));

        mainScene = new Scene();
        Sprite testSprite = new Sprite(texture, 100, 100, 50, 50) {
            @Override
            public void update(long delta) {
                super.update(delta);

                x += delta/(double)Stardust2D.timePrecission*100;
                rotation += delta/(double)Stardust2D.timePrecission*40;
            }
        };

        inputManager.addListener(new InputListener() {
            @Override
            public void onKeyboardKeyReleased(KeyboardEvent event) {
                super.onKeyboardKeyReleased(event);
                if(event.getEventKey() == Keyboard.KEY_SPACE) {
                    testSprite.setX(testSprite.getX() - 30);
                }
            }
        });

        Sprite bg = new Sprite(background, renderer.getWidth() / 2, renderer.getHeight() / 2);
        bg.setDepth(1);
        //bg.setColor(new Color(1, 1, 1, 0.8));

        mainScene.add(testSprite);
        mainScene.add(bg);
        setCurrentScene(mainScene);
    }
}
