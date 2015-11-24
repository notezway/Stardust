package umiker9.stardust2d;

/**
 * Created by miker9 on 23/11/2015.
 */
public abstract class GameObject {
    protected Scene scene;

    public abstract void update(long delta);

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
