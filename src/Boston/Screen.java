package Boston;

import java.awt.*;
import javax.swing.JFrame;
public class Screen {

    private GraphicsDevice _vc;

    public Screen(){
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        _vc = env.getDefaultScreenDevice();
    }

    public void setFullScreen(DisplayMode dm, JFrame window){
        window.setUndecorated(true);
        window.setResizable(false);
        _vc.setFullScreenWindow(window);

        if(dm != null && _vc.isDisplayChangeSupported()){
            try {
                _vc.setDisplayMode(dm);
            }catch (Exception ex){}
        }
    }

    public Window getFullScreenWindow(){
        return _vc.getFullScreenWindow();
    }

    public void restoreScreen(){
        Window w = _vc.getFullScreenWindow();

        if(w != null){
            w.dispose();
        }

        _vc.setFullScreenWindow(null);
    }
}
