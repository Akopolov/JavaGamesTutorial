package Boston;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

//remove flickering in animation
public class ScreenManager {
    private GraphicsDevice _videoCard;

    //give videocard access to monitor screen
    public ScreenManager(){
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        _videoCard = environment.getDefaultScreenDevice();
    }

    //get all compatible displaymodes
    public DisplayMode[] getCompatibleDisplayModes(){
        return _videoCard.getDisplayModes();
    }

    //compares displaymodes passed in to your vidiocard displaymodes and see if they match
    public DisplayMode findFirstCompatibleMode(DisplayMode displayModeList[]){
        DisplayMode videoCardDisplayModesList[] = _videoCard.getDisplayModes();
        for(int x = 0; x < displayModeList.length; x++){
            for(int y = 0; y < videoCardDisplayModesList.length; y++){
                if(displayModesMatch(displayModeList[x], videoCardDisplayModesList[y])){
                    return videoCardDisplayModesList[y];
                }
            }
        }
        return null;
    }

    //check if two modes match each other
    private boolean displayModesMatch(DisplayMode dm1, DisplayMode dm2) {
        //check size
        if(dm1.getWidth() != dm2.getWidth() || dm1.getHeight() != dm2.getHeight()){
            return false;
        }

        //check BitDepth
        if(     dm1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI &&
                dm2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI &&
                dm1.getBitDepth() != dm2.getBitDepth()) {
            return false;
        }

        if(     dm1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN &&
                dm2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN &&
                dm1.getRefreshRate() != dm2.getRefreshRate()){
            return false;
        }

        return true;
    }

    //get current Display Mode
    public DisplayMode getCurrentDisplayMode(){
        return _videoCard.getDisplayMode();
    }

    //make frame full screen
    public void setFullScreen(DisplayMode displayMode){
        JFrame f = new JFrame();
        f.setUndecorated(true);
        f.setIgnoreRepaint(true);
        f.setResizable(false);
        _videoCard.setFullScreenWindow(f);

        if(displayMode != null && _videoCard.isDisplayChangeSupported()){
            try {
                _videoCard.setDisplayMode(displayMode);
            }catch (Exception ex){}
        }

        f.createBufferStrategy(2);
    }

    // we will set Graphics object = to this
    public Graphics2D getGraphics(){
        Window window = _videoCard.getFullScreenWindow();
        if(window != null){
            BufferStrategy bufferStrategy = window.getBufferStrategy();
            return (Graphics2D) bufferStrategy.getDrawGraphics();
        }else {
            return null;
        }
    }

    //update display
    public void update(){
        Window window = _videoCard.getFullScreenWindow();
        if(window != null){
            BufferStrategy bufferStrategy = window.getBufferStrategy();
            if(!bufferStrategy.contentsLost()){
                bufferStrategy.show();
            }
        }
    }

    //returns full screen window
    public Window getFullScreenWindow(){
        return _videoCard.getFullScreenWindow();
    }

    //return width of the window
    public int getWidth(){
        Window window = _videoCard.getFullScreenWindow();
        if(window != null){
            return window.getWidth();
        }else{
            return 0;
        }
    }

    //Return height of the window
    public int getHeight(){
        Window window = _videoCard.getFullScreenWindow();
        if(window != null){
            return window.getHeight();
        }else{
            return 0;
        }
    }

    //Get out of the fullscreen
    public void restoreScreen(){
        Window window = _videoCard.getFullScreenWindow();
        if(window != null){
            window.dispose();
        }

        _videoCard.setFullScreenWindow(null);
    }

    //Create image compatible with monitor
    public BufferedImage createCompatibleImage(int width, int height, int transparency){
        Window window = _videoCard.getFullScreenWindow();
        if(window != null){
            GraphicsConfiguration graphicsConfiguration = window.getGraphicsConfiguration();
            return graphicsConfiguration.createCompatibleImage(width,height,transparency);
        }else{
            return null;
        }
    }
}
