package umiker9.stardust2d.graphics.lwjgl2;

import org.lwjgl.opengl.*;
import umiker9.stardust2d.systems.error.Error;
import umiker9.stardust2d.systems.error.ErrorBuilder;
import umiker9.stardust2d.systems.error.ErrorStack;
import umiker9.stardust2d.systems.io.Resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT;
import static org.lwjgl.opengl.GL11.*;


public class TextureLoader {

    public static Texture2D loadTexture(Resource resource) {
        return loadTexture(resource, TextureParameters.defaultParameters);
    }

    public static Texture2D loadTexture(Resource resource, TextureParameters parameters) {
        Texture2D texture = new Texture2D();
        loadTexture(texture, resource, parameters);
        return texture;
    }

    public static void loadTexture(Texture2D texture, Resource resource) {
        upload(texture, new TextureData(readImage(resource)), TextureParameters.defaultParameters);
    }

    public static void loadTexture(Texture2D texture, Resource resource, TextureParameters parameters) {
        upload(texture, new TextureData(readImage(resource)), parameters);
    }

    public static void upload(Texture texture, TextureData textureData, TextureParameters parameters) {
        if(textureData == null) {
            ErrorStack.addError(new ErrorBuilder().setLevel(Error.ERROR).setMessage("TextureData is null").finish());
            return;
        }

        if(!corresponds(texture.getTarget(), textureData.getType())) {
            ErrorStack.addError(new ErrorBuilder().setLevel(Error.ERROR).setMessage("Incompatible TextureTarget:"
                    + texture.getTarget() + " and TextureData: " + textureData.getType().name())
                    .setErrorSource(textureData).finish());
            return;
        }

        if(!textureData.hasImageData()) {
            ErrorStack.addError(new ErrorBuilder().setLevel(Error.ERROR).setErrorSource(textureData)
                    .setMessage("TextureData does not contain any ImageData").finish());
            return;
        }

        texture.bind();
        setParameters(texture.getTarget(), parameters);

        if(texture.getTarget() == GL_TEXTURE_2D) {
            upload2d(texture, textureData, parameters);
        } else {
            ErrorStack.addError(new ErrorBuilder().setLevel(Error.ERROR).setMessage("Unknown or unsupported TextureTarget: "
                    + texture.getTarget()).setErrorSource(texture).finish());
            return;
        }
        //todo add more textures

        texture.setTextureData(textureData);
        texture.setInitialized(true);
    }

    protected static void upload2d(Texture texture, TextureData textureData, TextureParameters parameters) {
        ImageData image = textureData.getImageData();


        glTexImage2D(texture.getTarget(), 0, parameters.INTERNAL_FORMAT, image.getWidth(), image.getHeight(),
                parameters.BORDER, image.getFormat(), image.getDataType(), GLHelper.wrapDirectBuffer(image.getMipmap(0)));

        if (parameters.GENERATE_MIPMAPS) {
            GL30.glGenerateMipmap(texture.getTarget());
        } else {
            int width = image.getWidth();
            int height = image.getHeight();
            for (int i = 1; i < image.getMipmapCount(); i++) {
                width = (width + 1)/2;
                height = (height + 1)/2;

                glTexImage2D(texture.getTarget(), i, parameters.INTERNAL_FORMAT, width, height, parameters.BORDER,
                        image.getFormat(), image.getDataType(), GLHelper.wrapDirectBuffer(image.getMipmap(i)));
            }
        }
    }

    public static ImageData readImage(Resource resource) {
        BufferedImage image = null;

        if(resource.getSize() == 0) {
            ErrorStack.addError(new ErrorBuilder().setLevel(Error.ERROR).setErrorSource(resource)
                    .setMessage("Resource [" + resource.getPath() + "] is empty(Missing or unavailable)").finish());
        }

        try {
            image = ImageIO.read(resource.getInputStream());
        } catch (IOException e) {
            ErrorStack.addError(new ErrorBuilder().setLevel(Error.ERROR).setException(e).setErrorSource(resource)
                    .setMessage("Error reading image from resource [" + resource.getPath() + "]").finish());
        }

        return decodeImage(image);
    }

    public static ImageData decodeImage(BufferedImage image) {
        if (image == null) {
            return null;
        }

        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        ByteBuffer buffer = ByteBuffer.allocate(image.getWidth() * image.getHeight() * 4);

        for (int pixel : pixels) {
            buffer.put((byte) (pixel >> 16 & 0xFF));
            buffer.put((byte) (pixel >> 8 & 0xFF));
            buffer.put((byte) (pixel & 0xFF));
            buffer.put((byte) (pixel >> 24 & 0xFF));
        }

        buffer.flip();

        return new ImageData(image.getWidth(), image.getHeight(), GL_RGBA, GL_UNSIGNED_BYTE, buffer.array());
    }

    protected static void setParameters(int target, TextureParameters parameters) {
        glTexParameteri(target, GL_TEXTURE_MIN_FILTER, parameters.MIN_FILTER);
        glTexParameteri(target, GL_TEXTURE_MAG_FILTER, parameters.MAG_FILTER);
        glTexParameteri(target, GL_TEXTURE_WRAP_S, parameters.WRAP_S);
        glTexParameteri(target, GL_TEXTURE_WRAP_T, parameters.WRAP_T);
        glTexParameteri(target, GL12.GL_TEXTURE_WRAP_R, parameters.WRAP_R);
        glTexParameter(target, GL_TEXTURE_BORDER_COLOR, GLHelper.wrapDirectBuffer(
                parameters.BORDER_R, parameters.BORDER_G, parameters.BORDER_B, parameters.BORDER_A));

        glTexParameteri(target, GL12.GL_TEXTURE_MAX_LEVEL, parameters.MIPMAP_MAX_LEVEL);
        glTexParameteri(target, GL12.GL_TEXTURE_BASE_LEVEL, parameters.MIPMAP_BASE_LEVEL);
        glTexParameterf(target, GL_TEXTURE_MAX_ANISOTROPY_EXT, parameters.MAX_ANISOTROPY);
    }

    protected static boolean corresponds(int target, TextureDataType type) {
        if (type == TextureDataType.SINGLE_IMAGE) {
            return target == GL_TEXTURE_2D ||
                    target == GL_TEXTURE_1D ||
                    target == GL31.GL_TEXTURE_RECTANGLE;
        } else if (type == TextureDataType.CUBEMAP) {
            return target == GL13.GL_TEXTURE_CUBE_MAP;
        } else if (type == TextureDataType.IMAGE_ARRAY) {
            return target == GL30.GL_TEXTURE_2D_ARRAY ||
                    target == GL30.GL_TEXTURE_1D_ARRAY;
        } else if (type == TextureDataType.CUBEMAP_ARRAY) {
            return target == GL40.GL_TEXTURE_CUBE_MAP_ARRAY;
        } else {
            return false;
        }
    }
}
