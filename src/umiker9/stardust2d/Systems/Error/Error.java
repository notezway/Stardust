package umiker9.stardust2d.Systems.Error;

/**
 * Created by miker9 on 22/11/2015.
 */
public class Error {
    public static final int UNIMPORTANT = 0;
    public static final int WARNING = 1;
    public static final int ERROR = 2;
    public static final int CRITICAL_ERROR = 3;
    private int level;
    private String message;
    private Exception exception;
    private Object errorSource;
    private StackTraceElement[] stackTrace;

    protected Error() {

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean hasException() {
        return exception != null;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public boolean hasErrorSource() {
        return errorSource != null;
    }

    public Object getErrorSource() {
        return errorSource;
    }

    public void setErrorSource(Object errorSource) {
        this.errorSource = errorSource;
    }

    public StackTraceElement[] getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(StackTraceElement[] stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Error {\n");
        switch (level) {
            case UNIMPORTANT: {
                builder.append("\tLevel = UNIMPORTANT\n");
                break;
            }
            case WARNING: {
                builder.append("\tLevel = WARNING\n");
                break;
            }
            case ERROR: {
                builder.append("\tLevel = ERROR\n");
                break;
            }
            case CRITICAL_ERROR: {
                builder.append("\tLevel = CRITICAL ERROR\n");
                break;
            }
        }

        if (message != null) {
            builder.append("\tMessage = ").append(message).append("\n");
        }

        if (exception != null) {
            builder.append("\tException: ").append(exception.getMessage()).append("\n");
        }

        if(errorSource != null) {
            builder.append("\tError source: ").append(errorSource.toString()).append("\n");
        }


        if (level >= CRITICAL_ERROR || ErrorStack.isDebug()) {
            builder.append("\tStacktrace:\n");
            for (StackTraceElement element : stackTrace) {
                builder.append("\t\t").append(element).append("\n");
            }
        } else {
            builder.append("\tLocation = ").append(stackTrace[0]).append("\n");
        }

        builder.append("}");


        return builder.toString();
    }
}
