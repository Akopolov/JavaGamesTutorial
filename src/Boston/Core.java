package Boston;

import java.awt.*;

public abstract class Core {
    private static DisplayMode _displayModeList[]={
            new DisplayMode(800,600,32,0),
            new DisplayMode(800,600,24,0),
            new DisplayMode(800,600,16,0),
            new DisplayMode(640,480,32,0),
            new DisplayMode(640,480,24,0),
            new DisplayMode(640,480,16,0),
    };

    private boolean _running;
    protected ScreenManager _screenManager;

    //stop method
    public void stop(){
        _running = false;
    }

    //call init nad gameloop
    public void run(){
        try {
            init();
            gameLoop();
        }finally {
            _screenManager.restoreScreen();
        }
    }

    //set to full screen
    public void init() {
        _screenManager = new ScreenManager();
        DisplayMode displayMode = _screenManager.findFirstCompatibleMode(_displayModeList);
        _screenManager.setFullScreen(displayMode);

        Window window = _screenManager.getFullScreenWindow();
        window.setFont(new Font("Arial", Font.PLAIN, 20));
        window.setBackground(Color.GREEN);
        window.setForeground(Color.WHITE);

        _running = true;
    }

    //main gameloop
    public void gameLoop() {
        long startTime = System.currentTimeMillis();
        long accumulativeTime = startTime;

        while (_running){
            long timePassed = System.currentTimeMillis() - accumulativeTime;
            accumulativeTime += timePassed;

            update(timePassed);

            Graphics2D graphics = _screenManager.getGraphics();
            draw(graphics);
            graphics.dispose();
            _screenManager.update();

            try {
                Thread.sleep(20);
            }catch (Exception ex){}
        }
    }

    //update animation
    public void update(long timePassed) {
    }

    //draw animation
    public void draw(Graphics2D graphics) {
    }
}
