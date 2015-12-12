package umiker9.stardust2d;

import umiker9.stardust2d.graphics.Color;
import umiker9.stardust2d.graphics.lwjgl2.Renderer;
import umiker9.stardust2d.graphics.lwjgl2.Texture2D;

/**
 * Created by miker9 on 23/11/2015.
 */
public class Sprite extends GameObject implements Renderable {
    protected double x;
    protected double y;
    protected double depth;
    protected double width;
    protected double height;
    protected double originX;
    protected double originY;
    protected double rotation;
    protected Tile image;
    protected Animation animation;
    protected Color color = Color.WHITE;

    public Sprite() {

    }

    public Sprite(Tile image, double x, double y, double width, double height) {
        this();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public Sprite(Texture2D texture, double x, double y, double width, double height) {
        this(new Tile(texture), x, y, width, height);
    }

    public Sprite(Texture2D texture, double x, double y) {
        this(texture, x, y, texture.getWidth(), texture.getHeight());
    }

    public Sprite(Tile image, double x, double y) {
        this(image, x, y, image.getPixelWidth(), image.getHeight());
    }

    public Sprite(Texture2D texture) {
        this(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }

    public Sprite(Tile image) {
        this(image, 0, 0, image.getPixelWidth(), image.getPixelHeight());
    }

    public void render(Renderer renderer) {
        renderer.setColor(color);

        renderer.pushMatrix();
        renderer.translate(x, y, -depth);
        renderer.rotate(rotation);

        draw(renderer);

        renderer.popMatrix();
    }

    public void draw(Renderer renderer) {
        if (animation != null) {
            renderer.drawTexturedQuad(-originX, -originY, width, height, animation.getCurrentTile());
        } else {
            renderer.drawTexturedQuad(-originX, -originY, width, height, image);
        }
    }

    public void update(double delta) {
        if (animation != null) {
            animation.update(delta);
        }
    }

    public void setCentered() {
        setOriginX(getWidth() / 2);
        setOriginY(getHeight() / 2);
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getOriginX() {
        return originX;
    }

    public void setOriginX(double originX) {
        this.originX = originX;
    }

    public double getOriginY() {
        return originY;
    }

    public void setOriginY(double originY) {
        this.originY = originY;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public Tile getImage() {
        return image;
    }

    public void setImage(Texture2D texture) {
        this.image = new Tile(texture);
    }

    public void setImage(Tile image) {
        this.image = image;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }
}
