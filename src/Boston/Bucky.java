package Boston;

import javax.swing.*;
import java.awt.*;

public class Bucky extends JFrame {
    public static void main(String[] args) {
        DisplayMode dm = new DisplayMode(800,600,32, DisplayMode.REFRESH_RATE_UNKNOWN);
        Bucky b = new Bucky();
        b.run(dm);
    }

    public void run(DisplayMode dm){
        setBackground(Color.PINK);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.PLAIN,24));

        Screen s = new Screen();
        try{
            s.setFullScreen(dm, this);
            try{
                Thread.sleep(5000);
            }catch (Exception ex){}
        }finally {
            s.restoreScreen();
        }
    }

    public void paint(Graphics g){
        if(g instanceof Graphics2D){
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
        g.drawString("This is going to be awsome",200,200);
    }
}
