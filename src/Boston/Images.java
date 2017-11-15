package Boston;

import javax.swing.*;
import java.awt.*;

public class Images extends JFrame {
    public static void main(String[] args) {
        DisplayMode dm = new DisplayMode(800,600,32, DisplayMode.REFRESH_RATE_UNKNOWN);
        Images i = new Images();
        i.run(dm);
    }

    private Screen _screen;
    private Image _backGround;
    private Image _picture;
    private boolean _loaded;

    //run method
    public void run(DisplayMode dm){
        setBackground(Color.PINK);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.PLAIN,24));
        _loaded = false;

        _screen = new Screen();
        try{
            _screen.setFullScreen(dm, this);
            loadpic();
            try{
                Thread.sleep(5000);
            }catch (Exception ex){}
        }finally {
            _screen.restoreScreen();
        }
    }

    private void loadpic() {
        String homePath = System.getProperty("user.home");
        _backGround = new ImageIcon(
                homePath +
                        "\\Documents\\JavaGame\\Boston\\BostonGames\\images\\back.jpg")
                .getImage();
        _picture = new ImageIcon(
                homePath +
                        "\\Documents\\JavaGame\\Boston\\BostonGames\\images\\apple.png")
                .getImage();
        _loaded = true;
        repaint();
    }

    public void paint(Graphics g){
        if(g instanceof Graphics2D){
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
        g.drawString("This is going to be awsome",200,200);

        if(_loaded){
            g.drawImage(_backGround, 0, 0, null);
            g.drawImage(_picture, 170, 180, null);
        }
    }
}
