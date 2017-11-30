package DriverGame;

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

    /**List that contains the status of the key buttons on the key bord*/
    private static boolean[] keyboardState = new boolean[525];

    /**List that contains the status of the mouse buttons*/
    private static boolean[] mouseState = new boolean[3];

    /**Canvas that will draw the image and will react to buttons*/
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

        /**add Key bord listener to JPanel*/
        this.addKeyListener(this);

        /**add Mouse listener to JPanel*/
        this.addMouseListener(this);
    }

    /**This method is used in FrameWork class*/
    public abstract void draw(Graphics2D graphics2D);

    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;
        super.paintComponent(graphics);
        draw(graphics2D);
    }

    /**Checks if the key is pressed*/
    public static boolean keyboardKeyState(int key){
        return keyboardState[key];
    }

    /**Check if only 1 button pressed*/
    public static boolean onlyThisButtonPressed(int key){
        for(int keyNumber = 0; keyNumber < keyboardState.length; keyNumber ++){
            if(keyNumber != key && keyboardState[keyNumber]){
                return false;
            }
        }
        if(keyboardState[key]){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        keyboardState[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyboardState[e.getKeyCode()] = false;
        keyReleasedFramework(e);
    }

    public abstract void keyReleasedFramework(KeyEvent e);

    /**Check if tje mouse button is pressed
     * mouseState=[0,1,2]
     * mouseState[0] == MouseEvent.Button1*/
    public static boolean mouseButtonState(int button){
        return mouseState[button - 1];
    }

    private void mouseKeyStatus(MouseEvent e, boolean status){
        if(e.getButton() == MouseEvent.BUTTON1){
            mouseState[0] = status;
        }else if(e.getButton() == MouseEvent.BUTTON2){
            mouseState[1] = status;
        }else if(e.getButton() == MouseEvent.BUTTON3){
            mouseState[2] = status;
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
