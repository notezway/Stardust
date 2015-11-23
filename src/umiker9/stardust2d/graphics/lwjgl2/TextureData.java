package umiker9.stardust2d.graphics.lwjgl2;

public class TextureData {
    protected TextureDataType type;
    protected ImageData[] layers;

    public TextureData(TextureDataType type, ImageData... layers) {
        this.type = type;
        this.layers = layers;
    }

    public TextureData(ImageData imageData) {
        this(TextureDataType.SINGLE_IMAGE, imageData);
    }

    public boolean hasImageData() {
        return layers != null && layers.length > 0 && layers[0] != null;
    }

    public TextureDataType getType() {
        return type;
    }

    public void setType(TextureDataType type) {
        this.type = type;
    }

    public ImageData getImageData() {
        return getLayer(0);
    }

    public ImageData getLayer(int layer) {
        return layers[layer];
    }

    public ImageData[] getLayers() {
        return layers;
    }

    public void setLayers(ImageData[] layers) {
        this.layers = layers;
    }
}
