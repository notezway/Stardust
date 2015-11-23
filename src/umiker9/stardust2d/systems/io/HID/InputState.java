package umiker9.stardust2d.systems.io.HID;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class InputState {
    protected boolean[] keyboard;
    protected boolean[] mouse;
    protected int mouseDX;
    protected int mouseDY;
    protected int mouseX;
    protected int mouseY;
    protected int mouseDWheel;

    public InputState() {
        keyboard = new boolean[Keyboard.KEYBOARD_SIZE];
        mouse = new boolean[Mouse.getButtonCount()];
    }

    public boolean isKeyDown(int key) {
        return keyboard[key];
    }

    public void setKeyDown(int key, boolean down) {
        keyboard[key] = down;
    }

    public boolean isMouseLeftDown() {
        return mouse[0];
    }

    public boolean isMouseRightDown() {
        return mouse[1];
    }

    public boolean isMouseMiddleDown() {
        return mouse[2];
    }

    public boolean isMouseButtonDown(int button) {
        return mouse[button];
    }

    public void setMouseButtonDown(int button, boolean down) {
        mouse[button] = down;
    }

    public int getMouseDX() {
        return mouseDX;
    }

    public void setMouseDX(int mouseDX) {
        this.mouseDX = mouseDX;
    }

    public int getMouseDY() {
        return mouseDY;
    }

    public void setMouseDY(int mouseDY) {
        this.mouseDY = mouseDY;
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public int getMouseDWheel() {
        return mouseDWheel;
    }

    public void setMouseDWheel(int mouseDWheel) {
        this.mouseDWheel = mouseDWheel;
    }
}
