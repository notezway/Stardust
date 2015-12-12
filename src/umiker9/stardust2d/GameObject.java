package umiker9.stardust2d;

import umiker9.stardust2d.systems.io.HID.InputListener;

/**
 * Created by miker9 on 23/11/2015.
 */
public abstract class GameObject extends InputListener {
    protected Scene scene;

    public void update(double delta) {

    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
