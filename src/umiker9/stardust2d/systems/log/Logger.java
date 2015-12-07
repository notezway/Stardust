package umiker9.stardust2d.systems.log;

import java.io.PrintStream;

/**
 * Created by Notezway on 23.11.2015.
 */
public class Logger {

    private static Logger instance;

    private int minLevel;
    private PrintStream[] out;
    private LogLevel currLevel, defaultLevel;

    public Logger() {
        this(System.out);
    }

    public Logger(PrintStream... out) {
        this(LogLevel.INFO, out);
    }

    public Logger(LogLevel minLevel, PrintStream... out) {
        this(minLevel, minLevel, out);
    }

    public Logger(LogLevel minLevel, LogLevel defaultLevel, PrintStream... out) {
        this.minLevel = minLevel.ordinal();
        this.out = out;
        this.currLevel = minLevel;
        this.defaultLevel = defaultLevel;
    }

    public void log(Message message) {
        if(message.getLevel().ordinal() >= minLevel || message.getLevel().equals(defaultLevel)) {
            for (PrintStream ps : out) {
                ps.println(message.toString());
            }
        }
    }

    public void log(Object data) {
        log(new Message(currLevel, data));
        currLevel = defaultLevel;
    }

    public void warn(Object data) {
        log(new Message(LogLevel.WARNING, data));
    }

    public void debug(Object data) {
        log(new Message(LogLevel.DEBUG, data));
    }

    public void info(Object data) {
        log(new Message(LogLevel.INFO, data));
    }

    public void setLevel(LogLevel level) {
        currLevel = level;
    }

    public void setMinLevel(LogLevel level) {
        minLevel = level.ordinal();
    }

    public void setDefaultLevel(LogLevel level) {
        defaultLevel = level;
    }

    public static void logInst(Message message) {
        instance.log(message);
    }

    public static void logInst(Object data) {
        instance.log(data);
    }

    public static void setLevelInst(LogLevel level) {
        instance.setLevel(level);
    }

    public static void setMinLevelInst(LogLevel level) {
        instance.setMinLevel(level);
    }

    public static void setDefaultLevelInst(LogLevel level) {
        instance.setDefaultLevel(level);
    }

    public static void createInstance(Logger logger) {
        instance = logger;
    }

    public static void createDefaultInstance() {
        instance = new Logger();
    }

    public static Logger getInstance() {
        return instance;
    }

    public static Message newDebug(Object data) {
        return new Message(LogLevel.DEBUG, data);
    }

    public static Message newInfo(Object data) {
        return new Message(LogLevel.INFO, data);
    }

    public static Message newWarn(Object data) {
        return new Message(LogLevel.WARNING, data);
    }

    public static void warnInst(Object data) {
        instance.log(new Message(LogLevel.WARNING, data));
    }

    public static void debugInst(Object data) {
        instance.log(new Message(LogLevel.DEBUG, data));
    }

    public static void infoInst(String text) {
        instance.log(new Message(LogLevel.INFO, text));
    }
}
