package umiker9.stardust2d.systems.io.HID;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import umiker9.stardust2d.Stardust2D;

public class InputManager extends InputRelay {
    protected InputState inputState;

    public InputManager() {
        inputState = new InputState();
    }

    public void handleInput() {
        inputState.setMouseDWheel(0);
        inputState.setMouseDX(0);
        inputState.setMouseDY(0);
        inputState.setMouseX(Mouse.getX());
        inputState.setMouseY(Mouse.getY());

        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == -1) {
                continue;
            }

            inputState.setKeyDown(Keyboard.getEventKey(), Keyboard.getEventKeyState());

            KeyboardEvent event = new KeyboardEvent(inputState);
            event.setEventKey(Keyboard.getEventKey());
            event.setEventKeyState(Keyboard.getEventKeyState());
            event.setEventCharacter(Keyboard.getEventCharacter());
            event.setEventNanoseconds(Keyboard.getEventNanoseconds());

            onInputEvent(event);
        }

        while (Mouse.next()) {
            if (Mouse.getEventButton() == -1) {
                if (Mouse.getEventDWheel() != 0) {
                    inputState.setMouseDWheel(inputState.getMouseDWheel() + Mouse.getEventDWheel());
                } else {
                    inputState.setMouseDX(inputState.getMouseDX() + Mouse.getEventDX());
                    inputState.setMouseDY(inputState.getMouseDY() + Mouse.getEventDY());
                }
            } else {
                inputState.setMouseButtonDown(Mouse.getEventButton(), Mouse.getEventButtonState());
            }

            MouseEvent event = new MouseEvent(inputState);
            event.setEventX(Mouse.getEventX() - Display.getWidth() / 2);
            event.setEventDX(Mouse.getEventDX() - Display.getWidth() / 2);

            if (Stardust2D.invertYAxis) {
                event.setEventY(Display.getHeight() / 2 - Mouse.getEventY());
                event.setEventDY(-Mouse.getEventDY());
            } else {
                event.setEventY(Mouse.getEventY());
                event.setEventDY(Mouse.getEventDY() - Display.getHeight() / 2);
            }

            event.setEventDWheel(Mouse.getEventDWheel());
            event.setEventButton(Mouse.getEventButton());
            event.setEventButtonState(Mouse.getEventButtonState());
            event.setEventNanoseconds(Mouse.getEventNanoseconds());

            onInputEvent(event);
        }
    }

    public InputState getInputState() {
        return inputState;
    }
}
