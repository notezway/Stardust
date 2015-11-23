package umiker9.stardust2d.systems.io.HID;

public class InputEvent {
    protected InputState inputState;
    protected boolean cancelled;

    public InputEvent(InputState inputState) {
        this.inputState = inputState;
    }

    public InputEvent(InputEvent event) {
        this(event.getInputState());
        this.cancelled = event.isCancelled();
    }

    public InputState getInputState() {
        return inputState;
    }

    public void setInputState(InputState inputState) {
        this.inputState = inputState;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
