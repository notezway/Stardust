package umiker9.stardust2d.systems.io.HID;

public class KeyboardEvent extends InputEvent{
    private int eventKey;
    private boolean eventKeyState;
    private char eventCharacter;
    private long eventNanoseconds;


    public KeyboardEvent(InputState inputState) {
        super(inputState);
    }

    public KeyboardEvent(KeyboardEvent event) {
        super(event);
        this.eventKey = event.getEventKey();
        this.eventKeyState = event.getEventKeyState();
        this.eventCharacter = event.getEventCharacter();
        this.eventNanoseconds = event.getEventNanoseconds();
    }

    public int getEventKey() {
        return eventKey;
    }

    public void setEventKey(int eventKey) {
        this.eventKey = eventKey;
    }

    public boolean getEventKeyState() {
        return eventKeyState;
    }

    public void setEventKeyState(boolean eventKeyState) {
        this.eventKeyState = eventKeyState;
    }

    public char getEventCharacter() {
        return eventCharacter;
    }

    public void setEventCharacter(char eventCharacter) {
        this.eventCharacter = eventCharacter;
    }

    public long getEventNanoseconds() {
        return eventNanoseconds;
    }

    public void setEventNanoseconds(long eventNanoseconds) {
        this.eventNanoseconds = eventNanoseconds;
    }
}
