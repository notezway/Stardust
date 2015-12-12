package umiker9.stardust2d;

import umiker9.stardust2d.graphics.lwjgl2.Renderer;
import umiker9.stardust2d.systems.io.HID.InputRelay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miker9 on 22/11/2015.
 */

public class Scene extends InputRelay {
    protected List<GameObject> actors = new ArrayList<>();
    protected Camera camera;

    public Scene() {
        this(new Camera());
    }

    public Scene(Camera camera) {
        this.camera = camera;
    }

    public void render(Renderer renderer) {
        //Find renderables and sort them by depth
        List<Renderable> renderables = new ArrayList<>();

        for(GameObject actor : actors) {
            if (actor instanceof Renderable) {
                renderables.add((Renderable) actor);
            }
        }

        renderables.sort((o1, o2) -> (int) (Integer.MAX_VALUE * (o2.getDepth() - o1.getDepth())));

        //Apply camera
        renderer.pushMatrix();
        camera.applyTransforms(renderer);

        //render objects
        for (Renderable renderable : renderables) {
            renderable.render(renderer);
        }

        renderer.popMatrix();
    }

    public void update(long delta) {
        for(GameObject actor : actors) {
            actor.update(delta);
        }
        camera.update(delta);
    }

    public void add(GameObject actor) {
        actor.setScene(this);
        actors.add(actor);
        inputListeners.add(actor);
    }

    public void remove(GameObject actor) {
        actors.remove(actor);
        inputListeners.remove(actor);
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public List<GameObject> getActors() {
        return actors;
    }
}
