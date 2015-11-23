package umiker9.stardust2d.util;

/**
 * User: slimon
 * Date: 17.11.13
 * Time: 19:33
 */
public class BytesUtil {

    public static int toInt(byte[] data) {
        if(data.length != 4) return 0;
        return  (int)data[0] << 24 |
                (int)data[1] << 16 |
                (int)data[2] << 8 |
                (int)data[3];
    }

    public static short toShort(byte[] data) {
        if(data.length != 2) return 0;
        return (short) ((short)data[0] << 8 |
                        (short)data[1]);
    }

    public static long toLong(byte[] data) {
        if(data.length != 8) return 0;
        return  (long)data[0] << 56 |
                (long)data[1] << 48 |
                (long)data[2] << 40 |
                (long)data[3] << 32 |
                (int)data[4] << 24 |
                (int)data[5] << 16 |
                (int)data[6] << 8 |
                (int)data[7];
    }

    public static double toDouble(byte[] data) {
        if(data.length != 8) return 0;
        return Double.longBitsToDouble(toLong(data));
    }

    public static byte[] toBytes(int data) {
        return new byte[] {
                (byte)(data >> 24),
                (byte)(data >> 16),
                (byte)(data >> 8),
                (byte)(data)
        };
    }

    public static byte[] toBytes(short data) {
        return new byte[] {
                (byte)(data >> 8),
                (byte)(data)
        };
    }

    public static byte[] toBytes(long data) {
        return new byte[] {
                (byte)(data >> 56),
                (byte)(data >> 48),
                (byte)(data >> 40),
                (byte)(data >> 32),
                (byte)(data >> 24),
                (byte)(data >> 16),
                (byte)(data >> 8),
                (byte)(data)
        };
    }

    public static byte[] toBytes(double data) {
        long l = Double.doubleToRawLongBits(data);
        return toBytes(l);
    }
}
