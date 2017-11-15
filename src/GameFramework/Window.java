package GameFramework;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alekseik on 15.11.2017.
 */
public class Window extends JFrame{
    private boolean _fullScreen;
    private GraphicsDevice _graphicsDevice;
    private DisplayMode _displayMode;

    public boolean getFullScreen() {
        return _fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        _fullScreen = fullScreen;
    }

    public DisplayMode getDisplayMode() {
        return _displayMode;
    }

    public void setDisplayMode(DisplayMode displayMode) {
        _displayMode = displayMode;
    }

    private Window(){
        _fullScreen = false;
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        _graphicsDevice = environment.getDefaultScreenDevice();

        _displayMode = new DisplayMode(800, 600, 32, 0);

        this.setTitle("Game Title");

        if(_fullScreen){
            this.setUndecorated(true);
            this.setResizable(false);
            this.setIgnoreRepaint(true);
            _graphicsDevice.setFullScreenWindow(this);
            if(_displayMode != null && _graphicsDevice.isDisplayChangeSupported()){
                try{
                    _graphicsDevice.setDisplayMode(_displayMode);
                }catch (Exception ex){}
            }
        }else{
            this.setUndecorated(false);
            this.setResizable(true);
            this.setLocationRelativeTo(null);
            this.setSize(_displayMode.getWidth(),_displayMode.getHeight());
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new Framework());
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Window());
    }
}
