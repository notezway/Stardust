package umiker9.stardust2d.systems.log;

/**
 * Created by Notezway on 23.11.2015.
 */
public class Message {

    private LogLevel level;
    private Object data;

    public Message(LogLevel level, Object data) {
        this.level = level;
        this.data = data;
    }

    public LogLevel getLevel() {
        return level;
    }

    public Object getData() {
        return data;
    }

    public String toString() {
        return "[" + level.toString() + "] " + data.toString();
    }
}
