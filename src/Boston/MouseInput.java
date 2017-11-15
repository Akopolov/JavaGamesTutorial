package Boston;

import java.awt.*;
import java.awt.event.*;

public class MouseInput
        extends Core
        implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener{

    private String _message = "";

    public static void main(String[] args) {
        new MouseInput().run();
    }

    public void init(){
        super.init();
        Window window = _screenManager.getFullScreenWindow();
        window.addMouseListener(this);
        window.addMouseMotionListener(this);
        window.addMouseWheelListener(this);
        window.addKeyListener(this);
    }

    public synchronized void draw(Graphics2D graphics){
        Window window =_screenManager.getFullScreenWindow();
        graphics.setColor(window.getBackground());
        graphics.fillRect(0, 0, _screenManager.getWidth(), _screenManager.getHeight());
        graphics.setColor(window.getForeground());
        graphics.drawString(_message, 40, 50);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_ESCAPE){
            super.stop();
        }else{
            _message = "Pressed : " + KeyEvent.getKeyText(keyCode);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        _message = "Released : " + KeyEvent.getKeyText(keyCode);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        _message = "You pressed down the mouse";
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        _message = "You released the mouse button";
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        _message = "Mouse entered the screen";
    }

    @Override
    public void mouseExited(MouseEvent e) {
        _message = "Mouse exited the screen";
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        _message = "You are dragging the mouse";
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        _message = "You are moving the mouse";
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        _message = "Mouse wheel is moved";
    }
}
