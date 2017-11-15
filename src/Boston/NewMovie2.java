package Boston;

import javax.swing.*;
import java.awt.*;

public class NewMovie2 {
    public static void main(String[] args) {
        NewMovie2 newMovie2 = new NewMovie2();
        newMovie2.run();
    }

    private Sprite _sprite;
    private Animation _animation;
    private ScreenManager _screenManager;
    private Image _backGround;

    private static final DisplayMode _displayModes[]={
            new DisplayMode(800,600,32,0),
            new DisplayMode(800,600,24,0),
            new DisplayMode(800,600,16,0),
            new DisplayMode(640,480,32,0),
            new DisplayMode(640,480,24,0),
            new DisplayMode(640,480,16,0),
    };

    //load images and aad scenes
    public void loadImages(){
        String homePath = System.getProperty("user.home");
        _backGround = new ImageIcon(homePath +
                "\\Documents\\JavaGame\\Boston\\BostonGames\\images\\back.jpg")
                .getImage();

        Image face01 = new ImageIcon(homePath +
                "\\Documents\\JavaGame\\Boston\\BostonGames\\images\\face00.png")
                .getImage();

        Image face02 = new ImageIcon(homePath +
                "\\Documents\\JavaGame\\Boston\\BostonGames\\images\\face01.png")
                .getImage();

        _animation = new Animation();
        _animation.addScene(face01, 250);
        _animation.addScene(face02, 250);

        _sprite = new Sprite(_animation);
        _sprite.setVelosityX(0.2f);
        _sprite.setVelosityY(0.1f);
    }

    //main method call from main
    public void run(){
        _screenManager = new ScreenManager();
        try{
            DisplayMode displayMode = _screenManager.findFirstCompatibleMode(_displayModes);
            _screenManager.setFullScreen(displayMode);
            loadImages();
            movieLoop();
        }finally {
            _screenManager.restoreScreen();
        }
    }

    //play movie
    public void movieLoop() {
        long startingTime = System.currentTimeMillis();
        long acumeTime = startingTime;
        while(acumeTime - startingTime < 10000){
            long timePassed = System.currentTimeMillis() - acumeTime;
            acumeTime += timePassed;
            update(timePassed);

            //draw and update screen
            Graphics2D graphics = _screenManager.getGraphics();
            draw(graphics);
            graphics.dispose();
            _screenManager.update();

            try{
                Thread.sleep(20);
            }catch (Exception ex){}
        }
    }

    public void draw(Graphics2D graphics) {
        graphics.drawImage(_backGround, 0, 0, null);
        graphics.drawImage(
                _sprite.getImage(),
                Math.round(_sprite.getPositionX()),
                Math.round(_sprite.getPositionY()),
                null);
    }

    //update sprite position
    private void update(long timePassed) {
        if(_sprite.getPositionX() <= 0){
            _sprite.setVelosityX(Math.abs(_sprite.getVelosityX()));
        }else if(_sprite.getPositionX() + _sprite.getWidth() >= _screenManager.getWidth()){
            _sprite.setVelosityX(-Math.abs(_sprite.getVelosityX()));
        }

        if(_sprite.getPositionY() <= 0){
            _sprite.setVelosityY(Math.abs(_sprite.getVelosityY()));
        }else if(_sprite.getPositionY() + _sprite.getHeight() >= _screenManager.getHeight()){
            _sprite.setVelosityY(-Math.abs(_sprite.getVelosityY()));
        }

        _sprite.update(timePassed);
    }
}
