package umiker9.stardust2d.test.tankogame;

/**
 * Created by Notezway on 12.12.2015.
 */
/*public class Tankogame extends BasicGame {

    private static Tankogame instance;
    private Texture2D tex;
    SoundSystem soundSystem;

    public static void main(String args[]) {
        instance = new Tankogame();
        Display.setLocation(350, 20);
        instance.launch();
    }

    protected void init() {
        super.init();
        String path = "Assets/jackal.png";
        tex = TextureLoader.loadTexture(new Resource(path, FileIO.getFileAsBytes(path)));
        Logger.getInstance().setMinLevel(LogLevel.DEBUG);

        //to be continued...


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
                            //System.out.println(body2.getShapes()[0].getClass().getName());
                            boolean collide = body1.doesCollide(body2);
                            if (collide) {
                                System.out.println("collide!");
                                body1.onCollision(body2);
                            }
                            if(!collide) {
                                double K = body1.getMass() * body2.getMass() * 500;
                                double Rsq = body2.getPosition().subtract(body1.getPosition()).getLengthSq();
                                //body1.applyForce(body2.getPosition().subtract(body1.getPosition()).normalize().scale(K / Rsq));
                                //body2.applyForce(body1.getPosition().subtract(body2.getPosition()).normalize().scale(K / Rsq));
                            }
                        }
                    }
                }
            }
        };

        setCurrentScene(scene);

        SoundSystemConfig.setLogger(new AdvSoundSystemLogger(Logger.getInstance()));
        try {
            soundSystem = new SoundSystem(LibraryLWJGLOpenAL.class);
            SoundSystemConfig.setCodec("wav", CodecWav.class);
        } catch (SoundSystemException e) {
            e.printStackTrace();
        }
        /*try {
            soundSystem.backgroundMusic("background", new URL("file:///D:/Dev/Projects/Standalone/Stardust/Assets/Tobu_Colors.wav"), "Tobu_Colors.wav", true);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        soundSystem.setVolume("background", 0.01F);*/

 /*   }

    @Override
    protected void exit() {
        soundSystem.cleanup();
        super.exit();
    }

    public Tankogame() {
        super(1000, 680, "Tankogame");
    }
}*/
