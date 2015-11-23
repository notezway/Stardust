package umiker9.stardust2d.graphics.lwjgl2;

public class ImageData {
    protected int width;
    protected int height;
    protected int format;
    protected int dataType;
    protected byte[][] mipmaps;

    public ImageData() {

    }

    public ImageData(int width, int height, int format, int dataType, byte[][] mipmaps) {
        this.width = width;
        this.height = height;
        this.format = format;
        this.dataType = dataType;
        this.mipmaps = mipmaps;
    }

    public ImageData(int width, int height, int format, int dataType, byte[] image) {
        this(width, height, format, dataType, new byte[][]{image});
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public boolean hasMipmaps() {
        return getMipmapCount() > 1;
    }

    public int getMipmapCount() {
        return mipmaps.length;
    }

    public byte[] getImage() {
        return getMipmap(0);
    }

    public byte[] getMipmap(int level) {
        return mipmaps[level];
    }

    public byte[][] getMipmaps() {
        return mipmaps;
    }

    public void setMipmaps(byte[][] mipmaps) {
        this.mipmaps = mipmaps;
    }
}
