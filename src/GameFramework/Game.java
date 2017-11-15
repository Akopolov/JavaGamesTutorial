package GameFramework;

import java.awt.*;

/**
 * Created by alekseik on 15.11.2017.
 */
public class Game {

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

    }

    private void loadContent(){

    }

    public void updateGame(long gameTime, Point mousePosition) {

    }

    public void draw(Graphics2D graphics2D, Point point) {

    }

    public void RestartGame() {

    }
}
