package DriverGame;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    /**Run in full window*/
    private boolean isFullScreen;

    /**Holding graphics device*/
    private GraphicsDevice graphicsDevice;

    /**Display mode that will be used */
    private DisplayMode displayMode;

    /**Get isFullScreen value*/
    public boolean getFullScreen() {
        return isFullScreen;
    }

    /**Set full screen(True/False)*/
    public void setFullScreen(boolean fullScreen) {
        this.isFullScreen = fullScreen;
    }

    /**Get current DisplayMode*/
    public DisplayMode getDisplayMode() {
        return displayMode;
    }

    /**Set new DisplayMode*/
    public void setDisplayMode(DisplayMode displayMode) {
        this.displayMode = displayMode;
    }

    /**Window that will display the game*/
    private Window(){
        isFullScreen = false;
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphicsDevice = environment.getDefaultScreenDevice();
        displayMode = new DisplayMode(800, 600, 32, 0);
        this.setTitle("Game Name");

        /**if True display game in full mode if False display game in window mode*/
        if(isFullScreen){
            this.setUndecorated(true);
            this.setResizable(false);
            this.setIgnoreRepaint(true);
            graphicsDevice.setFullScreenWindow(this);
            if(displayMode != null && graphicsDevice.isDisplayChangeSupported()){
                try{
                    graphicsDevice.setDisplayMode(displayMode);
                }catch (Exception ex){}
            }
        }else{
            this.setLocationRelativeTo(null);
            this.setSize(displayMode.getWidth(), displayMode.getHeight());
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new Framework());
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Window());
    }
}
