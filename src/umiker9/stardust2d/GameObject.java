package umiker9.stardust2d;

import umiker9.stardust2d.graphics.lwjgl2.Renderer;

/**
 * Created by miker9 on 23/11/2015.
 */
public abstract class GameObject {
    protected Scene scene;

    public abstract void render(Renderer renderer);

    public abstract void update(long delta);

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
