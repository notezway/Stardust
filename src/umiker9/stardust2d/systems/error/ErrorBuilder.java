package umiker9.stardust2d.systems.error;

import java.util.Arrays;

/**
 * Created by miker9 on 22/11/2015.
 */
public class ErrorBuilder {

    private Error error;

    public ErrorBuilder() {
        error = new Error();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        error.setStackTrace(Arrays.copyOfRange(stackTrace, 2, stackTrace.length));
    }

    public ErrorBuilder setLevel(int level) {
        error.setLevel(level);
        return this;
    }

    public ErrorBuilder setMessage(String message) {
        error.setMessage(message);
        return this;
    }

    public ErrorBuilder setException(Exception exception) {
        error.setException(exception);
        return this;
    }

    public ErrorBuilder setErrorSource(Object errorSource) {
        error.setErrorSource(errorSource);
        return this;
    }

    public Error finish() {
        return error;
    }
}
