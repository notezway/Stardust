package umiker9.stardust2d.test;

import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.codecs.CodecWav;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;
import umiker9.stardust2d.BasicGame;
import umiker9.stardust2d.Camera;
import umiker9.stardust2d.GameObject;
import umiker9.stardust2d.Scene;
import umiker9.stardust2d.graphics.Color;
import umiker9.stardust2d.graphics.lwjgl2.Texture2D;
import umiker9.stardust2d.graphics.lwjgl2.TextureLoader;
import umiker9.stardust2d.math.Vec2;
import umiker9.stardust2d.physics.Ball;
import umiker9.stardust2d.physics.PhysicalBody;
import umiker9.stardust2d.systems.io.FileIO;
import umiker9.stardust2d.systems.io.Resource;
import umiker9.stardust2d.systems.log.AdvSoundSystemLogger;
import umiker9.stardust2d.systems.log.LogLevel;
import umiker9.stardust2d.systems.log.Logger;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Notezway on 23.11.2015.
 */
public class TestGame2 extends BasicGame {

    private static TestGame2 instance;
    SoundSystem soundSystem;
    private Texture2D tex;

    public TestGame2() {
        super(800, 700, "Test game 2");
    }

    public static void main(String args[]) {
        instance = new TestGame2();
        instance.launch();
    }

    protected void init() {
        super.init();
        String path = "Assets/jackal.png";
        tex = TextureLoader.loadTexture(new Resource(path, FileIO.getFileAsBytes(path)));
        Logger.getInstance().setMinLevel(LogLevel.DEBUG);

        Ball ball1 = new Ball(new Vec2(-300, 0), 0, 10, 200);
        Ball ball2 = new Ball(new Vec2(300, 0), 0, 10, 200);
        Ball ball3 = new Ball(new Vec2(0, 0), 0, 30, 9000);
        Ball ball4 = new Ball(new Vec2(-325, 0), 0, 2, 5);
        Ball ball5 = new Ball(new Vec2(325, 0), 0, 2, 5);
        Ball ball6 = new Ball(new Vec2(0, 100), 0, 5, 1);
        ball1.setVelocity(new Vec2(0, 120));
        ball2.setVelocity(new Vec2(0, -120));
        ball3.setVelocity(new Vec2(0, 0));
        ball4.setVelocity(new Vec2(0, 120 + 63));
        ball5.setVelocity(new Vec2(0, -120 - 63));
        ball6.setVelocity(new Vec2(-180, 0));
        ball1.setColor(Color.RED);
        ball2.setColor(Color.BLUE);
        ball3.setColor(Color.RED.mix(Color.GREEN));
        ball4.setColor(Color.WHITE);
        ball5.setColor(Color.WHITE);
        ball6.setColor(Color.BLUE.mix(Color.RED));


        final Scene scene = new Scene("main", new Camera()) {
            @Override
            public void update(double delta) {
                super.update(delta);
                GameObject actor1, actor2;

                for (int i = 0; i < getActors().size(); i++) {
                    actor1 = getActors().get(i);
                    for (int j = i + 1; j < getActors().size(); j++) {
                        actor2 = getActors().get(j);
                        if(actor1 instanceof PhysicalBody && actor2 instanceof PhysicalBody) {
                            PhysicalBody body1 = (PhysicalBody) actor1;
                            PhysicalBody body2 = (PhysicalBody) actor2;
                            //System.out.println("!");
                            boolean collide = body1.doesCollide(body2);
                            if (collide) {
                                System.out.println("collide!");
                                body1.onCollision(body2);
                            }
                            if(!collide) {
                                double K = body1.getMass() * body2.getMass() * 500;
                                double Rsq = body2.getPosition().subtract(body1.getPosition()).getLengthSq();
                                body1.applyForce(body2.getPosition().subtract(body1.getPosition()).normalize().scale(K / Rsq));
                                body2.applyForce(body1.getPosition().subtract(body2.getPosition()).normalize().scale(K / Rsq));
                            }
                        }
                    }
                }
            }
        };

        scene.addActor(ball1);
        scene.addActor(ball2);
        scene.addActor(ball3);
        scene.addActor(ball4);
        scene.addActor(ball5);
        scene.addActor(ball6);
        addScene(scene);
        setCurrentScene("main");

        SoundSystemConfig.setLogger(new AdvSoundSystemLogger(Logger.getInstance()));
        try {
            soundSystem = new SoundSystem(LibraryLWJGLOpenAL.class);
            SoundSystemConfig.setCodec("wav", CodecWav.class);
        } catch (SoundSystemException e) {
            e.printStackTrace();
        }
        try {
            soundSystem.backgroundMusic("background", new URL("file:///D:/Dev/Projects/Standalone/Stardust/Assets/Tobu_Colors.wav"), "Tobu_Colors.wav", true);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        soundSystem.setVolume("background", 0.05F);

    }

    @Override
    protected void exit() {
        soundSystem.cleanup();
        super.exit();
    }
}
