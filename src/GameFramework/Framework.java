package GameFramework;

import com.sun.javaws.exceptions.CouldNotLoadArgumentException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by alekseik on 15.11.2017.
 */
public class Framework extends Canvas {
    public enum GameState{
        STARTING, VISUALIZING, GAME_CONTENT_LOADING, MAIN_MENU,
        OPTIONS, PLAYING, GAME_OVER, DESTROYED }

    public static int _frameWidth;
    public static int _frameHeight;
    public static final long _secInNanoSec = 1000000000L;
    public static final long _miliSecInNanoSec = 1000000L;
    public static GameState gameState;

    private final int GAME_FPS = 60;
    private final long GAME_UPDATE_PERIOD = _secInNanoSec / GAME_FPS;
    private long _gameTime;
    private long _lastTime;
    private Game game;

    public Framework(){
        super();
        gameState = GameState.VISUALIZING;
        Thread gameThread = new Thread(() -> gameLoop());
        gameThread.start();
    }

    private void init(){

    }

    private void loadContent(){

    }

    private void gameLoop() {
        long visualizingTime = 0, lastVisualizingTime = System.nanoTime();
        long beginTime, timeTaken, timeLeft;

        while (true){
            beginTime = System.nanoTime();
            switch (gameState){

                case VISUALIZING:
                    if(getWidth() > 1 && visualizingTime > _secInNanoSec){
                        _frameWidth = this.getWidth();
                        _frameHeight = this.getHeight();
                        gameState = GameState.STARTING;
                    }else{
                        visualizingTime += System.nanoTime() - lastVisualizingTime;
                        lastVisualizingTime = System.nanoTime();
                    }
                    break;

                case STARTING:
                    init();
                    loadContent();
                    gameState = GameState.MAIN_MENU;
                    break;

                case PLAYING:
                    _gameTime += System.nanoTime() - _lastTime;
                    game.updateGame(_gameTime, mousePosition());
                    _lastTime = System.nanoTime();
                    break;

                case MAIN_MENU:
                    break;

                case OPTIONS:
                    break;

                case GAME_CONTENT_LOADING:
                    break;

                case DESTROYED:
                    break;

                case GAME_OVER:
                    break;
            }

            repaint();
            timeTaken = System.nanoTime() - beginTime;
            timeLeft = (GAME_UPDATE_PERIOD - timeTaken) / _miliSecInNanoSec;

            if(timeLeft < 10){
                timeLeft = 10;
            }
            try{
                Thread.sleep(timeLeft);
            }catch (InterruptedException ex){}
        }
    }

    @Override
    public void draw(Graphics2D graphics2D){
        switch (gameState){
            case MAIN_MENU:
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawString("This is a test", 30, 30);
                break;

            case OPTIONS:
                break;

            case GAME_CONTENT_LOADING:
                break;

            case PLAYING:
                game.draw(graphics2D, mousePosition());
                break;

            case GAME_OVER:
                break;
        }
    }

    private void newGame(){
        _gameTime = 0;
        _lastTime = System.nanoTime();
        game = new Game();
    }

    private void restartGame(){
        _gameTime = 0;
        _lastTime = System.nanoTime();
        game.RestartGame();
        gameState = GameState.PLAYING;
    }

    private Point mousePosition() {
        try {
            Point mousePoint = this.getMousePosition();
            if(mousePoint != null){
                return this.getMousePosition();
            }else{
                return new Point(0, 0);
            }
        }catch (Exception ex){
            return new Point(0,0);
        }
    }

    @Override
    public void keyReleasedFramework(KeyEvent event){

    }

    @Override
    public void mouseClicked(MouseEvent event){

    }
}
