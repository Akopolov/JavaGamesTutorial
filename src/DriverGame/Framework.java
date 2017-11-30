package DriverGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alekseik on 15.11.2017.
 */
public class Framework extends Canvas {

    /**Enum of all possible game statuses*/
    public enum GameState{
        STARTING, VISUALIZING, GAME_CONTENT_LOADING, MAIN_MENU,
        OPTIONS, PLAYING, GAME_OVER, DESTROYED, EXIT_GAME }

    /**Width of the current frame*/
    public static int frameWidth;

    /**Height of the current frame*/
    public static int frameHeight;

    /**Number of nanoseconds in 1 second*/
    public static final long secInNanoSec = 1000000000L;

    /**Number of nanoseconds in 1 millisecond*/
    public static final long milliSecInNanoSec = 1000000L;

    /**Current game status*/
    public static GameState gameState;

    /**Used to stop a the game loop*/
    public boolean gameLoop;

    /**Number of frames per second*/
    private final int GAME_FPS = 60;

    /**How often must the frame be updated*/
    private final long GAME_UPDATE_PERIOD = secInNanoSec / GAME_FPS;

    /**Length of the current game*/
    private long gameTime;

    /**Last time the game frame was updated*/
    private long lastTime;

    /**The game it self*/
    private Game game;

    /**Main menu image*/
    private BufferedImage pacMainMenuImg;


    public Framework(){
        /**Call the canvas*/
        super();

        /**Set gamestate to visualazing*/
        gameState = GameState.VISUALIZING;
        gameLoop = true;

        /**Init the thread where the game will be running and start it*/
        Thread gameThread = new Thread(() -> gameLoop());
        gameThread.start();
    }

    private void init(){

    }

    private void loadContent(){
        try{
            /**Load the menu BackGround*/
            URL packManMenuImgUrl = this.getClass().getResource("/MoonLander/resources/images/menu.jpg");
            pacMainMenuImg = ImageIO.read(packManMenuImgUrl);
        }catch (Exception ex){
            Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**Game loop will run here*/
    private void gameLoop() {

        /** will be used during the game loading*/
        long visualizingTime = 0, lastVisualizingTime = System.nanoTime();
        long beginTime, timeTaken, timeLeft;

        while (gameLoop){
            beginTime = System.nanoTime();
            switch (gameState){

                /**Loading program*/
                case VISUALIZING:

                    /**if frame width is bigger that 1 and game loop is running longer that 1 second
                     * set game status to STARTING
                     * Else calculate how long is the game running*/
                    if(getWidth() > 1 && visualizingTime > secInNanoSec){
                        frameWidth = this.getWidth();
                        frameHeight = this.getHeight();
                        gameState = GameState.STARTING;
                    }else{
                        visualizingTime += System.nanoTime() - lastVisualizingTime;
                        lastVisualizingTime = System.nanoTime();
                    }
                    break;

                /**Starting the program*/
                case STARTING:
                    init();
                    loadContent();
                    gameState = GameState.MAIN_MENU;
                    break;

                /**Playing the game*/
                case PLAYING:
                    gameTime += System.nanoTime() - lastTime;
                    game.updateGame(gameTime, mousePosition());
                    lastTime = System.nanoTime();
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

                case EXIT_GAME:
                    System.exit(0);
                    break;
            }

            repaint();

            /**Calculate how much time it took to run the 1 gameLoop cycle*/
            timeTaken = System.nanoTime() - beginTime;
            timeLeft = (GAME_UPDATE_PERIOD - timeTaken) / milliSecInNanoSec;

            /**put the gameLoop thread to sleep for at least 10 milliseconds*/
            if(timeLeft < 10){
                timeLeft = 10;
            }
            try{
                Thread.sleep(timeLeft);
            }catch (InterruptedException ex){}
        }
    }

    /**draw different things depending on the gameState*/
    @Override
    public void draw(Graphics2D graphics2D){
        switch (gameState){
            case MAIN_MENU:
                graphics2D.drawImage(pacMainMenuImg, 0, 0, frameWidth, frameHeight, null);
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawString("Start Game", frameWidth /2 - 117, frameHeight /2);
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

    /**Start new game from main menu*/
    private void newGame(){
        gameTime = 0;
        lastTime = System.nanoTime();
        game = new Game();
    }

    /**Start new game from previous*/
    private void restartGame(){
        gameTime = 0;
        lastTime = System.nanoTime();
        game.RestartGame();
        gameState = GameState.PLAYING;
    }

    /**Return the current position of the mouse in the Frame*/
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

    /**Key Listener BL*/
    @Override
    public void keyReleasedFramework(KeyEvent event){
        int keyCode = event.getKeyCode();
        switch (gameState){

            case MAIN_MENU:
                if(keyCode == KeyEvent.VK_ESCAPE){
                    gameState = GameState.EXIT_GAME;
                }else{
                    this.newGame();
                }
                break;

            case PLAYING:
                if(keyCode == KeyEvent.VK_ESCAPE){
                    gameState = GameState.MAIN_MENU;
                }
                break;


        }
    }

    /**Mouse clicked BL*/
    @Override
    public void mouseClicked(MouseEvent event){

    }
}
