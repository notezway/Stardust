package umiker9.stardust2d.Test;

import umiker9.stardust2d.BasicGame;
import umiker9.stardust2d.Graphics.LWJGL2.Renderer;
import umiker9.stardust2d.Scene;
import umiker9.stardust2d.Stardust2D;

/**
 * Created by miker9 on 22/11/2015.
 */

public class TestGame1 extends BasicGame {

    private static TestGame1 instance;

    public static void main(String args[]) {
        instance = new TestGame1();
        instance.start();
    }

    public TestGame1() {
        super(800, 600, "Test game 1");
        final int[] i = {0};
        setCurrentScene(new Scene() {
            @Override
            public void render(Renderer renderer) {
                super.render(renderer);
                renderer.drawQuad(100+i[0], 100, 50, 50);
            }

            @Override
            public void update(long delta) {
                super.update(delta);
                i[0] += delta/(float) Stardust2D.timePrecission*100;
            }
        });
    }
}
