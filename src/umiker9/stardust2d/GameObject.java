package umiker9.stardust2d;

import umiker9.stardust2d.systems.io.HID.InputHandler;

/**
 * Created by miker9 on 23/11/2015.
 */
public abstract class GameObject extends InputHandler implements Updatable {
    protected Scene scene;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
