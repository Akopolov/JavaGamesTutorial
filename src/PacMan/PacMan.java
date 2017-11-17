package PacMan;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class PacMan {

    public Random _random;

    public int _positionX;
    public int _positionY;

    public boolean _died;

    private int _speedX;
    private int _speedY;

    private int _pacManWidth;
    private int _pacManHeight;

    private BufferedImage _pacManImage;

    public PacMan(){
        init();
        loadContent();
        _positionX = _random.nextInt(Framework._frameWidth - _pacManWidth);
    }

    private void init(){
        _random = new Random();
        ResetPlayer();
    }


    private void loadContent(){
        try {
            URL pacManImageUrl = this.getClass().getResource("/MoonLander/resources/images/rocket.png");
            _pacManImage = ImageIO.read(pacManImageUrl);
            _pacManWidth = _pacManImage.getWidth();
            _pacManHeight = _pacManImage.getHeight();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    private void ResetPlayer() {
        _died = false;
        _positionX = _random.nextInt(Framework._frameWidth - _pacManWidth);
        _positionY = 10;

        _speedX = 0;
        _speedY = 0;
    }

    public void Update(){

    }
}
