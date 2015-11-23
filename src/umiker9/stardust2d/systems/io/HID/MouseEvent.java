package umiker9.stardust2d.systems.io.HID;

public class MouseEvent extends InputEvent{
    private int eventX, eventY;
    private int eventDX, eventDY;
    private int eventDWheel;
    private int eventButton;
    private boolean eventButtonState;
    private long eventNanoseconds;

    public MouseEvent(InputState inputState) {
        super(inputState);
    }

    public MouseEvent(MouseEvent event) {
        super(event);
        this.eventX = event.getEventX();
        this.eventY = event.getEventY();
        this.eventDX = event.getEventDX();
        this.eventDY = event.getEventDY();
        this.eventDWheel = event.getEventDWheel();
        this.eventButton = event.getEventButton();
        this.eventButtonState = event.getEventButtonState();
        this.eventNanoseconds = event.getEventNanoseconds();
    }

    public int getEventX() {
        return eventX;
    }

    public void setEventX(int eventX) {
        this.eventX = eventX;
    }

    public int getEventY() {
        return eventY;
    }

    public void setEventY(int eventY) {
        this.eventY = eventY;
    }

    public int getEventDX() {
        return eventDX;
    }

    public void setEventDX(int eventDX) {
        this.eventDX = eventDX;
    }

    public int getEventDY() {
        return eventDY;
    }

    public void setEventDY(int eventDY) {
        this.eventDY = eventDY;
    }

    public int getEventDWheel() {
        return eventDWheel;
    }

    public void setEventDWheel(int eventDWheel) {
        this.eventDWheel = eventDWheel;
    }

    public int getEventButton() {
        return eventButton;
    }

    public void setEventButton(int eventButton) {
        this.eventButton = eventButton;
    }

    public boolean getEventButtonState() {
        return eventButtonState;
    }

    public void setEventButtonState(boolean eventButtonState) {
        this.eventButtonState = eventButtonState;
    }

    public long getEventNanoseconds() {
        return eventNanoseconds;
    }

    public void setEventNanoseconds(long eventNanoseconds) {
        this.eventNanoseconds = eventNanoseconds;
    }
}
