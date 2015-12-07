package umiker9.stardust2d;

import umiker9.stardust2d.graphics.lwjgl2.Renderer;
import umiker9.stardust2d.graphics.lwjgl2.ShapesDrawer;
import umiker9.stardust2d.graphics.lwjgl2.Texture;
import umiker9.stardust2d.graphics.lwjgl2.Texture2D;
import umiker9.stardust2d.math.Vec2;

/**
 * Created by Notezway on 07.12.2015.
 */
public abstract class PhysicalBody extends Sprite {

    protected Shape shape;
    protected double mass;

    protected double velX;
    protected double velY;

    protected double accX;
    protected double accY;

    protected double forceX;
    protected double forceY;

    public PhysicalBody(Vec2 pos, double rot, Shape shape, double mass) {
        super();
        setPosition(pos);
        this.rotation = rot;
        this.shape = shape;
        this.mass = mass;
        this.width = shape.getSize();
        this.height = shape.getSize();
    }

    public Vec2 getPosition() {
        return new Vec2(x, y);
    }

    public void setPosition(Vec2 position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public Vec2 getVelocity() {
        return new Vec2(velX, velY);
    }

    public void setVelocity(Vec2 velocity) {
        this.velX = velocity.getX();
        this.velY = velocity.getY();
    }

    public void accelerate(Vec2 acceleration) {
        this.accX += acceleration.getX();
        this.accY += acceleration.getY();
    }

    public void applyForce(Vec2 force) {
        this.forceX += force.getX();
        this.forceY += force.getY();
    }

    public void update(long delta) {
        accX += forceX / mass;
        accY += forceY / mass;
        velX += accX;
        velY += accY;
        forceX = 0;
        forceY = 0;
        accX = 0;
        accY = 0;
        x += velX * delta / Stardust2D.timePrecission;
        y += velY * delta / Stardust2D.timePrecission;
    }

    public void draw(Renderer renderer) {
        if(shape instanceof CircleShape) {
            ShapesDrawer.drawCircle(renderer, (CircleShape) shape, 32);
        }
    }
}
