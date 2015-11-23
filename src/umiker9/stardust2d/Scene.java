package umiker9.stardust2d;

import umiker9.stardust2d.graphics.lwjgl2.Renderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miker9 on 22/11/2015.
 */

public class Scene {
    protected List<GameObject> actors = new ArrayList<>();

    public Scene() {

    }

    public void render(Renderer renderer) {
        for(GameObject actor : actors) {
            actor.render(renderer);
        }
    }

    public void update(long delta) {
        for(GameObject actor : actors) {
            actor.update(delta);
        }
    }

    public void add(GameObject actor) {
        actor.setScene(this);
        actors.add(actor);
    }

    public void remove(GameObject actor) {
        actors.remove(actor);
    }

    public List<GameObject> getActors() {
        return actors;
    }
}
