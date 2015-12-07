package umiker9.stardust2d.test;

import umiker9.stardust2d.*;
import umiker9.stardust2d.graphics.Color;
import umiker9.stardust2d.graphics.lwjgl2.Renderer;
import umiker9.stardust2d.graphics.lwjgl2.ShapesDrawer;
import umiker9.stardust2d.graphics.lwjgl2.Texture2D;
import umiker9.stardust2d.graphics.lwjgl2.TextureLoader;
import umiker9.stardust2d.math.Vec2;
import umiker9.stardust2d.systems.io.FileIO;
import umiker9.stardust2d.systems.io.Resource;
import umiker9.stardust2d.math.MathUtil;
import umiker9.stardust2d.systems.log.LogLevel;
import umiker9.stardust2d.systems.log.Logger;

/**
 * Created by Notezway on 23.11.2015.
 */
public class TestGame2 extends BasicGame {

    private static TestGame2 instance;
    private Texture2D tex;

    public static void main(String args[]) {
        instance = new TestGame2();
        instance.start();
    }

    protected void init() {
        super.init();
        String path = "Assets/jackal.png";
        tex = TextureLoader.loadTexture(new Resource(path, FileIO.getFileAsBytes(path)));
        Logger.getInstance().setMinLevel(LogLevel.DEBUG);
        final int[] i = {0, 0};
        Shape shape = new CircleShape(new Vec2(), 20);
        Shape sun = new CircleShape(new Vec2(), 100);
        final PhysicalBody[] bodies = new PhysicalBody[400000];

        bodies[0] = new PhysicalBody(new Vec2(0, 0), 0, sun, 100) {
            public void update(long delta) {
                super.update(delta);
                //applyForce(bodies[1].getPosition().subtract(getPosition()).scale(10000D / bodies[1].getPosition().subtract(getPosition()).getLengthSq()));
                //applyForce(bodies[2].getPosition().subtract(getPosition()).scale(10000D / bodies[2].getPosition().subtract(getPosition()).getLengthSq()));
                //applyForce(bodies[3].getPosition().subtract(getPosition()).scale(10000D / bodies[3].getPosition().subtract(getPosition()).getLengthSq()));
            }
        };

        bodies[1] = new PhysicalBody(new Vec2(200, 0), 0, shape, 100) {
            public void update(long delta) {
                super.update(delta);
                applyForce(bodies[2].getPosition().subtract(getPosition()).scale(500D / bodies[2].getPosition().subtract(getPosition()).getLengthSq()));
                applyForce(bodies[0].getPosition().subtract(getPosition()).scale(10000D / bodies[0].getPosition().subtract(getPosition()).getLengthSq()));
                applyForce(bodies[3].getPosition().subtract(getPosition()).scale(500D / bodies[3].getPosition().subtract(getPosition()).getLengthSq()));
                if(bodies[0].getPosition().subtract(getPosition()).getLengthSq() < 200 * 200) {
                    //applyForce(getVelocity().normalize().scale(-1 * 10));
                }
                if(bodies[0].getPosition().subtract(getPosition()).getLengthSq() < 120 * 120) {
                    applyForce(getPosition().subtract(bodies[0].getPosition()).normalize().scale(1000));
                }
            }
        };

        bodies[2] = new PhysicalBody(new Vec2(275, 0), 0, shape, 100) {
            public void update(long delta) {

                super.update(delta);
                applyForce(bodies[1].getPosition().subtract(getPosition()).scale(500D / bodies[1].getPosition().subtract(getPosition()).getLengthSq()));
                applyForce(bodies[0].getPosition().subtract(getPosition()).scale(10000D / bodies[0].getPosition().subtract(getPosition()).getLengthSq()));
                applyForce(bodies[3].getPosition().subtract(getPosition()).scale(500D / bodies[3].getPosition().subtract(getPosition()).getLengthSq()));
                if(bodies[0].getPosition().subtract(getPosition()).getLengthSq() < 200 * 200) {
                    applyForce(getVelocity().normalize().scale(-1 * 10));
                }
                if(bodies[0].getPosition().subtract(getPosition()).getLengthSq() < 120 * 120) {
                    //applyForce(getPosition().subtract(bodies[0].getPosition()).normalize().scale(1000));
                }
            }
        };

        bodies[3] = new PhysicalBody(new Vec2(350, 0), 0, shape, 100) {
            public void update(long delta) {

                super.update(delta);
                applyForce(bodies[1].getPosition().subtract(getPosition()).scale(500D / bodies[1].getPosition().subtract(getPosition()).getLengthSq()));
                applyForce(bodies[0].getPosition().subtract(getPosition()).scale(10000D / bodies[0].getPosition().subtract(getPosition()).getLengthSq()));
                applyForce(bodies[2].getPosition().subtract(getPosition()).scale(500D / bodies[2].getPosition().subtract(getPosition()).getLengthSq()));

                if(bodies[0].getPosition().subtract(getPosition()).getLengthSq() < 200 * 200) {
                    applyForce(getVelocity().normalize().scale(-1 * 10));
                }
                if(bodies[0].getPosition().subtract(getPosition()).getLengthSq() < 120 * 120) {
                    applyForce(getPosition().subtract(bodies[0].getPosition()).normalize().scale(10000));
                }
            }
        };

        bodies[1].setVelocity(new Vec2(0, 80));
        bodies[2].setVelocity(new Vec2(0, 78));
        bodies[3].setVelocity(new Vec2(0, 45));
        bodies[0].setColor(new Color(1D, 1D, 0D));
        bodies[1].setColor(Color.RED);
        bodies[2].setColor(Color.GREEN);
        bodies[3].setColor(Color.BLUE);


        Sprite testSprite = new Sprite(tex, 100, 100, 50, 50) {
            @Override
            public void update(long delta) {
                super.update(delta);

                x += delta/(double)Stardust2D.timePrecission*100;
                rotation += delta/(double)Stardust2D.timePrecission*40;
            }
        };
        Scene scene = new Scene();
        scene.add(bodies[0]);
        scene.add(bodies[1]);
        scene.add(bodies[2]);
        scene.add(bodies[3]);
        scene.add(testSprite);
        setCurrentScene(scene);

    }

    public TestGame2() {
        super(800, 600, "Test game 2");
    }
}
