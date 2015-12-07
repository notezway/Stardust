package umiker9.stardust2d.math;

/**
 * User: slimon
 * Date: 06.11.13
 * Time: 12:51
 */
public class MathUtil {

    public static double distance2Sq(double x1, double y1, double x2, double y2) {
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
    }

    public static double distance3Sq(double x1, double y1, double z1, double x2, double y2, double z2) {
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1);
    }

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

    public static double max(double... values) {
        double max = Double.MIN_VALUE;
        for(double v : values) {
            max = Math.max(v, max);
        }
        return max;
    }

    public static double min(double... values) {
        double min = Double.MAX_VALUE;
        for(double v : values) {
            min = Math.min(v, min);
        }
        return min;
    }

    public static double sum(double... values) {
        double sum = 0;
        for(double v : values) {
            sum += v;
        }
        return sum;
    }

    public static double sumSq(double... values) {
        double sum = 0;
        for(double v : values) {
            sum += v*v;
        }
        return sum;
    }

    public static double average(double... values) {
        return sum(values) / values.length;
    }

    public static double product(double... values) {
        double sum = 1;
        for(double v : values) {
            sum *= v;
        }
        return sum;
    }

    public static double geometricMean(double... values) {
        return Math.pow(product(values), 1. / values.length);
    }

}
