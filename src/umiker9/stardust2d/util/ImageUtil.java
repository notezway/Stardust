package umiker9.stardust2d.util;

import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by slimon
 * on 10.05.2015.
 */
public class ImageUtil {

    public static ByteBuffer getImageData(String path) {
        try {
            File file = new File(path);
            if(!file.exists())
                return BufferUtils.createByteBuffer(1);
            BufferedImage image = ImageIO.read(file);

            int[] pixels = new int[image.getWidth() * image.getHeight()];
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

            ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4); //4 for RGBA, 3 for RGB

            for(int y = 0; y < image.getHeight(); y++){
                for(int x = 0; x < image.getWidth(); x++){
                    int pixel = pixels[y * image.getWidth() + x];
                    buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
                    buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
                    buffer.put((byte) (pixel & 0xFF));               // Blue component
                    buffer.put((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA
                }
            }

            buffer.flip();
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BufferUtils.createByteBuffer(1);
    }
}
