package umiker9.stardust2d;

import umiker9.stardust2d.graphics.lwjgl2.Renderer;
import umiker9.stardust2d.math.Vec2;
import umiker9.stardust2d.systems.io.HID.InputEvent;
import umiker9.stardust2d.systems.io.HID.InputRelay;
import umiker9.stardust2d.systems.io.HID.MouseEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miker9 on 22/11/2015.
 */

//todo Пофиксить то что active берется из InputRelay хз как

public class Scene extends InputRelay {
    private List<GameObject> actors = new ArrayList<>();
    private Camera camera;
    private String name;

    public Scene(String name) {
        this.name = name;
        this.setActive(false);
    }

    public Scene(String name, Camera camera) {
        this.name = name;
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
        if (camera != null) {
            camera.applyTransforms(renderer);
        }

        //render objects
        for (Renderable renderable : renderables) {
            renderable.render(renderer);
        }

        renderer.popMatrix();
    }

    public void update(double delta) {
        for(GameObject actor : actors) {
            actor.update(delta);
        }
        if (camera != null) {
            camera.update(delta);
        }
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (camera != null && event instanceof MouseEvent) {
            //make a copy
            MouseEvent newEvent = new MouseEvent((MouseEvent) event);
            Vec2 position = new Vec2(newEvent.getEventX(), newEvent.getEventY());
            if (Stardust2D.invertYAxis) {
                position = position.rotate(Math.toRadians(-camera.getRotation()));
            } else {
                position = position.rotate(Math.toRadians(camera.getRotation()));
            }
            position = position.scale(1 / camera.getScale());
            position = position.add(new Vec2(camera.getX(), camera.getY()));


            Vec2 deltaPosition = new Vec2(newEvent.getEventDX(), newEvent.getEventDY());
            if (Stardust2D.invertYAxis) {
                deltaPosition = deltaPosition.rotate(Math.toRadians(-camera.getRotation()));
            } else {
                deltaPosition = deltaPosition.rotate(Math.toRadians(camera.getRotation()));
            }
            deltaPosition = deltaPosition.scale(1 / camera.getScale());

            newEvent.setEventX((int) position.getX());
            newEvent.setEventY((int) position.getY());
            newEvent.setEventDX((int) deltaPosition.getX());
            newEvent.setEventDY((int) deltaPosition.getY());

            super.onInputEvent(newEvent);

            if (newEvent.isCancelled()) {
                event.setCancelled(true);
            }
        } else {
            super.onInputEvent(event);
        }
    }

    public void addActor(GameObject actor) {
        actor.setScene(this);
        actors.add(actor);
        addInputListener(actor);
    }

    public void removeActor(GameObject actor) {
        actor.setScene(null);
        actors.remove(actor);
        removeInputListener(actor);
    }

    public void onSceneEntered() {
        setActive(true);
    }

    public void onSceneLeft() {
        setActive(false);
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GameObject> getActors() {
        return actors;
    }
}
