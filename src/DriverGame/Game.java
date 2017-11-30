package DriverGame;

import DriverGame.BL.carController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alekseik on 15.11.2017.
 */
public class Game {

    /**game back ground image*/
    private BufferedImage gameBackGroundImg;

    private DriverGame.BL.carController carController;

    public Game(){
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
        Thread threadForInitGame = new Thread(){
            @Override
            public void run() {
                init();
                loadContent();
                Framework.gameState = Framework.GameState.PLAYING;
            }
        };
        threadForInitGame.start();
    }

    private void init(){
        carController = new carController();
    }

    /**Load all the requierd image*/
    private void loadContent(){
        URL backgroundImgUrl = this.getClass().getResource("/MoonLander/resources/images/background.jpg");
        try {
            gameBackGroundImg = ImageIO.read(backgroundImgUrl);
        }catch (IOException ex) {
            Logger.getLogger(MoonLander.Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateGame(long gameTime, Point mousePosition) {
        carController.update();
    }

    public void draw(Graphics2D graphics2D, Point point) {
        graphics2D.drawImage(gameBackGroundImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        carController.draw(graphics2D);

    }

    public void RestartGame() {

    }
}
