package Boston;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Look
        extends Core
        implements KeyListener, MouseMotionListener{

    private Image _backGround;
    private Robot _robot;
    private Point _mouse;
    private Point _center;
    private Point _image;
    private boolean _centering;

    public static void main(String[] args) {
        new Look().run();
    }

    public void init(){
        super.init();
        _mouse = new Point();
        _center = new Point();
        _image = new Point();
        _centering = false;

        try{
            _robot = new Robot();
            recenterMouse();
            _mouse.x = _center.x;
            _mouse.y = _center.y;
        }catch (Exception ex){
            System.out.println("Exception 1");
        }

        Window window = _screenManager.getFullScreenWindow();
        window.addMouseMotionListener(this);
        window.addKeyListener(this);
        String homePath = System.getProperty("user.home");
        _backGround = new ImageIcon(
                homePath +
                        "\\Documents\\JavaGame\\Boston\\BostonGames\\images\\forest.jpg")
                .getImage();
    }

    public synchronized void draw(Graphics2D graphics){
        int width = _screenManager.getWidth();
        int height = _screenManager.getHeight();

        //module of image.x / width
        _image.x %= width;
        _image.y %= height;
        if(_image.x < 0){
            _image.x += width;
        }

        if(_image.y < 0){
            _image.y += height;
        }

        int x = _image.x;
        int y = _image.y;

        graphics.drawImage(_backGround, x, y, null);
        graphics.drawImage(_backGround, x - width, y, null);
        graphics.drawImage(_backGround, x, y - height, null);
        graphics.drawImage(_backGround, x - width, y - height, null);
    }

    //recenter the mouse using the robot
    private synchronized void recenterMouse() {
        Window window = _screenManager.getFullScreenWindow();
        if(_robot != null && window.isShowing()){
            _center.x = window.getWidth() / 2;
            _center.y = window.getHeight() / 2;
            SwingUtilities.convertPointToScreen(_center, window);
            _centering = true;
            _robot.mouseMove(_center.x, _center.y);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            super.stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public synchronized void mouseMoved(MouseEvent e) {
        if(_centering && _center.x == e.getX() && _center.y == e.getY()){
            _centering = false;
        }else{
            int dx = e.getX() - _mouse.x;
            int dy = e.getY() - _mouse.y;
            _image.x += dx;
            _image.y += dy;
            recenterMouse();
        }

        _mouse.x = e.getX();
        _mouse.y = e.getY();
    }
}
