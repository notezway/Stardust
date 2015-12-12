package umiker9.stardust2d.systems.io.HID;

public class InputListener {
    protected InputRelay inputSource;
    protected int priority;

    public InputListener() {

    }

    public InputListener(int priority) {
       this.priority = priority;
    }

    public void onInputEvent(InputEvent event) {
        if(event instanceof MouseEvent) {
            MouseEvent e = (MouseEvent) event;

            if(e.getEventButton() != -1) {
                if(e.getEventButtonState()) {
                    onMouseButtonPressed(e);
                } else {
                    onMouseButtonReleased(e);
                }
            } else if(e.getEventDWheel() != 0) {
                onMouseWheelTurned(e);
            } else if(e.getEventDX() != 0 || e.getEventDY() != 0) {
                onMouseMoved(e);
            }
        } else if(event instanceof KeyboardEvent) {
            KeyboardEvent e = (KeyboardEvent) event;

            if(e.getEventKey() != -1) {
                if(e.getEventKeyState()) {
                    onKeyboardKeyPressed(e);
                } else {
                    onKeyboardKeyReleased(e);
                }
            }
        }
    }


    public void onMouseButtonPressed(MouseEvent event) {

    }

    public void onMouseButtonReleased(MouseEvent event) {

    }

    public void onMouseMoved(MouseEvent event) {

    }

    public void onMouseWheelTurned(MouseEvent event) {

    }

    public void onKeyboardKeyPressed(KeyboardEvent event) {

    }

    public void onKeyboardKeyReleased(KeyboardEvent event) {

    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    protected InputRelay getInputSource() {
        return inputSource;
    }

    protected void setInputSource(InputRelay source) {
        this.inputSource = source;
    }
}
