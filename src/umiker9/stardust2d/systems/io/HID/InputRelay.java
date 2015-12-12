package umiker9.stardust2d.systems.io.HID;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by miker9 on 12/12/2015.
 */
public class InputRelay extends InputHandler {
    protected final Queue<InputHandler> inputListeners = new PriorityQueue<>((Comparator<InputHandler>) (o1, o2) -> o2.getPriority() - o1.getPriority());
    private boolean active = true;

    public InputRelay() {

    }

    public InputRelay(int priority) {
        super(priority);
    }


    public void addListener(InputHandler listener) {
        if (!inputListeners.contains(listener)) {
            inputListeners.add(listener);
        }
        listener.setInputSource(this);
    }

    public void removeListener(InputHandler listener) {
        inputListeners.remove(listener);
    }

    @Override
    public void onInputEvent(InputEvent event) {
        super.onInputEvent(event);
        if (isActive()) {
            for (InputHandler listener : inputListeners) {
                if (event.isCancelled()) {
                    break;
                }
                listener.onInputEvent(event);
            }
        }
    }

    public InputState getInputState() {
        return inputSource.getInputState();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
