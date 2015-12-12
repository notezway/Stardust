package umiker9.stardust2d.test;

/**
 * Created by Notezway on 23.11.2015.
 */
/*public class TestGame2 extends BasicGame {

    private static TestGame2 instance;
    private Texture2D tex;
    SoundSystem soundSystem;

    public static void main(String args[]) {
        instance = new TestGame2();
        instance.launch();
    }

    protected void init() {
        super.init();
        String path = "Assets/jackal.png";
        tex = TextureLoader.loadTexture(new Resource(path, FileIO.getFileAsBytes(path)));
        Logger.getInstance().setMinLevel(LogLevel.DEBUG);

        Ball ball1 = new Ball(new Vec2(-300, 0), 0, 10, 200);
        Ball ball2 = new Ball(new Vec2(300, 0), 0, 10, 200);
        Ball ball3 = new Ball(new Vec2(0, 0), 0, 30, 9000);
        Ball ball4 = new Ball(new Vec2(-325, 0), 0, 2, 5);
        Ball ball5 = new Ball(new Vec2(325, 0), 0, 2, 5);
        Ball ball6 = new Ball(new Vec2(0, 100), 0, 5, 1);
        ball1.setVelocity(new Vec2(0, 120));
        ball2.setVelocity(new Vec2(0, -120));
        ball3.setVelocity(new Vec2(0, 0));
        ball4.setVelocity(new Vec2(0, 120 + 63));
        ball5.setVelocity(new Vec2(0, -120 - 63));
        ball6.setVelocity(new Vec2(-180, 0));
        ball1.setColor(Color.RED);
        ball2.setColor(Color.BLUE);
        ball3.setColor(Color.RED.mix(Color.GREEN));
        ball4.setColor(Color.WHITE);
        ball5.setColor(Color.WHITE);
        ball6.setColor(Color.BLUE.mix(Color.RED));


        Scene scene = new Scene() {
            @Override
            public void update(long delta) {
                super.update(delta);
                GameObject actor1, actor2;

                for(int i = 0; i < actors.size(); i++) {
                    actor1 = actors.get(i);
                    for(int j = i + 1; j < actors.size(); j++) {
                        actor2 = actors.get(j);
                        if(actor1 instanceof PhysicalBody && actor2 instanceof PhysicalBody) {
                            PhysicalBody body1 = (PhysicalBody) actor1;
                            PhysicalBody body2 = (PhysicalBody) actor2;
                            //System.out.println("!");
                            boolean collide = body1.doesCollide(body2);
                            if (collide) {
                                System.out.println("collide!");
                                body1.onCollision(body2);
                            }
                            if(!collide) {
                                double K = body1.getMass() * body2.getMass() * 500;
                                double Rsq = body2.getPosition().subtract(body1.getPosition()).getLengthSq();
                                body1.applyForce(body2.getPosition().subtract(body1.getPosition()).normalize().scale(K / Rsq));
                                body2.applyForce(body1.getPosition().subtract(body2.getPosition()).normalize().scale(K / Rsq));
                            }
                        }
                    }
                }
            }
        };

        scene.addActor(ball1);
        scene.addActor(ball2);
        scene.addActor(ball3);
        scene.addActor(ball4);
        scene.addActor(ball5);
        scene.addActor(ball6);
        setCurrentScene(scene);

        SoundSystemConfig.setLogger(new AdvSoundSystemLogger(Logger.getInstance()));
        try {
            soundSystem = new SoundSystem(LibraryLWJGLOpenAL.class);
            SoundSystemConfig.setCodec("wav", CodecWav.class);
        } catch (SoundSystemException e) {
            e.printStackTrace();
        }
        try {
            soundSystem.backgroundMusic("background", new URL("file:///D:/Dev/Projects/Standalone/Stardust/Assets/Tobu_Colors.wav"), "Tobu_Colors.wav", true);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        soundSystem.setVolume("background", 0.05F);

    }

    @Override
    protected void exit() {
        soundSystem.cleanup();
        super.exit();
    }

    public TestGame2() {
        super(800, 700, "Test game 2");
    }
}*/
