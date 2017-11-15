package Boston;

import java.awt.*;
import java.awt.event.*;

public class KeyTest extends Core implements KeyListener{

    public static void main(String[] args) {
        new KeyTest().run();
    }

    private String _message = "";

    //init also call init from superclass
    public void init(){
        super.init();
        Window window = _screenManager.getFullScreenWindow();
        window.setFocusTraversalKeysEnabled(false);
        window.addKeyListener(this);
        _message = "Press escape to exit";
    }

    @Override
    public void keyTyped(KeyEvent e) {
        e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_ESCAPE){
            super.stop();
        }else{
            _message = "Pressed : " + KeyEvent.getKeyText(keyCode);
            e.consume();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        _message = "Released : " + KeyEvent.getKeyText(keyCode);
        e.consume();
    }

    public synchronized void draw(Graphics2D graphics){
        Window window = _screenManager.getFullScreenWindow();
        graphics.setColor(window.getBackground());
        graphics.fillRect(0, 0, _screenManager.getWidth(), _screenManager.getHeight());
        graphics.setColor(window.getForeground());
        graphics.drawString(_message, 30, 30);
    }
}
