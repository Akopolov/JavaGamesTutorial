package Boston;

import javax.swing.*;
import java.awt.*;

//bucky class
public class Movie {
    public static void main(String[] args) {
        DisplayMode displayMode = new DisplayMode(800,600,32, DisplayMode.REFRESH_RATE_UNKNOWN);
        Movie movie = new Movie();
        movie.run(displayMode);
    }

    private Screen _screen;
    private Image _backGround;
    private Animation _animation;

    public void loadPics(){
        String homePath = System.getProperty("user.home");
        _backGround = new ImageIcon(
                homePath +
                        "\\Documents\\JavaGame\\Boston\\BostonGames\\images\\back.jpg")
                .getImage();

        Image face1 = new ImageIcon(
                homePath +
                        "\\Documents\\JavaGame\\Boston\\BostonGames\\images\\face00.png")
                .getImage();

        Image face2 = new ImageIcon(
                homePath +
                        "\\Documents\\JavaGame\\Boston\\BostonGames\\images\\face01.png")
                .getImage();

        _animation = new Animation();
        _animation.addScene(face1,250);
        _animation.addScene(face2, 250);
    }

    //main engine to run
    public void run(DisplayMode displayMode){
        _screen = new Screen();
        try {
            _screen.setFullScreen(displayMode, new JFrame());
            loadPics();
            movieLoop();
        }finally {
            _screen.restoreScreen();
        }
    }

    //main movie loop
    public void movieLoop(){
        long startingTime = System.currentTimeMillis();
        long acumeTime = startingTime;

        while (acumeTime - startingTime < 5000){
            long timePassed = System.currentTimeMillis() - acumeTime;
            acumeTime += timePassed;
            _animation.update(timePassed);

            Graphics graphics = _screen.getFullScreenWindow().getGraphics();
            draw(graphics);
            graphics.dispose();

            try{
                Thread.sleep(20);
            }catch (Exception ex){}
        }
    }

    public void draw(Graphics graphics){
        graphics.drawImage(_backGround, 0, 0, null);
        graphics.drawImage(_animation.getImage(), 100, 100, null);
    }
}
