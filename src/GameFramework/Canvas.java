package GameFramework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * Created by alekseik on 15.11.2017.
 */
public abstract class Canvas
        extends JPanel
        implements KeyListener, MouseListener{

    private static boolean[] _keyboardState = new boolean[525];

    private static boolean[] _mouseState = new boolean[3];

    public Canvas(){
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setBackground(Color.WHITE);

        if(false){
            BufferedImage blankCursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                    blankCursorImg, new Point(0, 0), null);
            this.setCursor(blankCursor);
        }

        this.addKeyListener(this);
        this.addMouseListener(this);
    }

    public abstract void draw(Graphics2D graphics2D);

    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;
        super.paintComponent(graphics);
        draw(graphics2D);
    }

    public static boolean keyboardKeyState(int key){
        return _keyboardState[key];
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        _keyboardState[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        _keyboardState[e.getKeyCode()] = false;
        keyReleasedFramework(e);
    }

    public abstract void keyReleasedFramework(KeyEvent e);

    public static boolean mouseButtonState(int button){
        return _mouseState[button - 1];
    }

    private void mouseKeyStatus(MouseEvent e, boolean status){
        if(e.getButton() == MouseEvent.BUTTON1){
            _mouseState[0] = status;
        }else if(e.getButton() == MouseEvent.BUTTON2){
            _mouseState[1] = status;
        }else if(e.getButton() == MouseEvent.BUTTON3){
            _mouseState[2] = status;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        mouseKeyStatus(e, true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseKeyStatus(e, false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
