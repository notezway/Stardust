package umiker9.stardust2d.util.math;

/**
 * User: slimon
 * Date: 06.11.13
 * Time: 12:51
 */
public class MathUtil {

    public static double distance2(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public static double distance3(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1));
    }

    public static double sin(double deg) {
        return Math.sin(Math.toRadians(deg));
    }

    public static double cos(double deg) {
        return Math.cos(Math.toRadians(deg));
    }

    public static double tan(double deg) {
        return Math.tan(Math.toRadians(deg));
    }

    public static double arcsin(double sin) {
        return Math.toDegrees(Math.asin(sin));
    }

    public static double arccos(double cos) {
        return Math.toDegrees(Math.acos(cos));
    }

    public static double arctan(double tan) {
        return Math.toDegrees(Math.atan(tan));
    }

    public static double arctan2(double f1, double f2) {
        return Math.toDegrees(Math.atan2(f1, f2));
    }

	public static int clamp(int min, int val, int max) {
		return Math.min(Math.max(val, min), max);
	}

    public static double clamp(double min, double val, double max) {
        return Math.min(Math.max(val, min), max);
    }

	public static double limitSigns(double d, int signs) {
		int i = (int)Math.pow(10, signs);
		return Math.floor(d * i) / i;
	}

    public static double[] getRotatedPos(double x, double y, double deg) {
        double ax;
        double ay;
        double adeg = (Math.toRadians(deg) + Math.atan2(y, x));
        double length = Math.sqrt(x * x + y * y);

        ax = (Math.cos(adeg) * length);
        ay = (Math.sin(adeg) * length);

        return new double[] {ax, ay};
    }

}
