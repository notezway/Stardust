package umiker9.stardust2d.Systems.Error;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by miker9 on 22/11/2015.
 */
public class ErrorStack {
    private static final Queue<Error> errors = new LinkedList<Error>();
    private static boolean debug = false;


    public static void addError(Error e) {
        errors.add(e);
    }

    public static boolean hasErrors() {
        return !errors.isEmpty();
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        ErrorStack.debug = debug;
    }

    public static Error getNextError() {
        return errors.poll();
    }
}
