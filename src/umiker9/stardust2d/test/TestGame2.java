package umiker9.stardust2d.test;

import umiker9.stardust2d.BasicGame;
import umiker9.stardust2d.Scene;
import umiker9.stardust2d.Stardust2D;
import umiker9.stardust2d.graphics.lwjgl2.Renderer;
import umiker9.stardust2d.graphics.lwjgl2.Texture2D;
import umiker9.stardust2d.graphics.lwjgl2.TextureLoader;
import umiker9.stardust2d.systems.io.FileIO;
import umiker9.stardust2d.systems.io.Resource;
import umiker9.stardust2d.math.MathUtil;

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
    }

    public TestGame2() {
        super(800, 600, "Test game 2");
        final int[] i = {0, 0};
        setCurrentScene(new Scene() {
            @Override
            public void render(Renderer renderer) {
                super.render(renderer);
                renderer.drawTexturedQuad(i[0], (int) (100 + 20*MathUtil.sin(i[0] * 10.)), 50, 50, tex);
            }

            @Override
            public void update(long delta) {
                super.update(delta);
                if(i[0] > 600) i[1] = -2;
                if(i[0] < 100) i[1] = 1;
                i[0] += (delta / (float) Stardust2D.timePrecission * 100) * i[1];
            }
        });
    }
}
