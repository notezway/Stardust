package umiker9.stardust2d;

/**
 * Created by miker9 on 23/11/2015.
 */
public abstract class GameObject implements Updatable {
    protected Scene scene;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
