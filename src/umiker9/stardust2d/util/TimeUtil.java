package umiker9.stardust2d.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: slimon
 * Date: 08.11.13
 * Time: 11:17
 */
public class TimeUtil {

    public static long getSystemTimeInMillis() {
        return System.nanoTime() / 1000000;
    }

    public static String getTime(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }
}
