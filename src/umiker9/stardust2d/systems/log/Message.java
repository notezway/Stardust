package umiker9.stardust2d.systems.log;

/**
 * Created by Notezway on 23.11.2015.
 */
public class Message {

    private LogLevel level;
    private String text;

    public Message(LogLevel level, String text) {
        this.level = level;
        this.text = text;
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getText() {
        return text;
    }

    public String toString() {
        return "[" + level.toString() + "] " + text;
    }
}
