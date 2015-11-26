package umiker9.stardust2d;

import umiker9.stardust2d.graphics.lwjgl2.Renderer;

/**
 * Created by miker9 on 26/11/2015.
 */
public interface Renderable {
    void render(Renderer renderer);

    double getDepth();
}
