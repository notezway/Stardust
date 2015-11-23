package umiker9.stardust2d.systems.io;

import java.io.ByteArrayInputStream;

public class Resource {
    private byte[] data;
    private String path;

    public Resource(String path, byte[] data) {
        this.path = path;
        this.data = data;
    }

    public int getSize() {
        return data.length;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ByteArrayInputStream getInputStream() {
        return new ByteArrayInputStream(data);
    }
}
