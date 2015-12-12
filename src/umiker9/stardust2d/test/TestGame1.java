package umiker9.stardust2d.test;

import org.lwjgl.input.Keyboard;
import umiker9.stardust2d.*;
import umiker9.stardust2d.graphics.Color;
import umiker9.stardust2d.graphics.lwjgl2.Texture2D;
import umiker9.stardust2d.graphics.lwjgl2.TextureLoader;
import umiker9.stardust2d.systems.io.FileIO;
import umiker9.stardust2d.systems.io.HID.InputListener;
import umiker9.stardust2d.systems.io.HID.KeyboardEvent;
import umiker9.stardust2d.systems.io.HID.MouseEvent;

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
        Stardust2D.invertYAxis = true;
        instance = new TestGame1();
        instance.launch();
    }

    @Override
    protected void init() {
        super.init();

        //renderer.setInvertYAxis(false);

        Texture2D texture = TextureLoader.loadTexture(FileIO.getResource("Assets/jackal.png"));
        TileSet tileset = new TileSet(texture, 16, 16);
        Animation animation = new Animation(tileset, 0, 3, 4, true);
        Texture2D background = TextureLoader.loadTexture(FileIO.getResource("Assets/Stars.png"));

        mainScene = new Scene("main");

        Sprite testSprite2 = new Sprite(tileset.getTile(0)) {
            @Override
            public void onMouseButtonPressed(MouseEvent event) {
                super.onMouseButtonPressed(event);
                setColor(Color.RED);
            }
        };
        Sprite testSprite = new Sprite(texture, 100, 100, 50, 50) {
            @Override
            public void update(double delta) {
                super.update(delta);

                x += delta * 100;
                rotation += delta * 40;
            }

            @Override
            public void onMouseButtonPressed(MouseEvent event) {
                super.onMouseButtonPressed(event);
                animation.stop();
            }

            @Override
            public void onMouseButtonReleased(MouseEvent event) {
                super.onMouseButtonReleased(event);
                animation.play();
            }
        };
        testSprite.setAnimation(animation);
        animation.play();

        mainScene.addInputListener(new InputListener() {
            @Override
            public void onKeyboardKeyReleased(KeyboardEvent event) {
                super.onKeyboardKeyReleased(event);
                if(event.getEventKey() == Keyboard.KEY_SPACE) {
                    testSprite.setX(testSprite.getX() - 30);
                }
            }
        });

        mainScene.addInputListener(new InputListener() {
            @Override
            public void onMouseMoved(MouseEvent event) {
                testSprite2.setX(event.getEventX());
                testSprite2.setY(event.getEventY());
            }
        });

        Sprite bg = new Sprite(background);
        bg.setDepth(1);
        //bg.setColor(new Color(1, 1, 1, 0.8));

        mainScene.addActor(testSprite);
        mainScene.addActor(bg);
        mainScene.addActor(testSprite2);
        FollowingCamera followingCamera = new FollowingCamera(testSprite);
        followingCamera.setFollowRotation(true);
        Camera camera1 = new Camera(200, 200);
        camera1.setRotation(70);
        camera1.setScale(1.5);
        mainScene.setCamera(followingCamera);


        addScene(mainScene);
        setCurrentScene("main");
    }
}
